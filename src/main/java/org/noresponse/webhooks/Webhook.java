package org.noresponse.webhooks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.noresponse.models.shared.Assessment;
import org.noresponse.utils.JSON;
import org.noresponse.webhooks.VerificationException.Code;

/**
 * Webhook verification. Hand-written beside the generated client.
 *
 * <p>Verify the exact bytes received: the signature is HMAC-SHA256 over
 * {@code <timestamp>.<raw body>}, so re-serialising parsed JSON breaks it.
 */
public final class Webhook {
    /** How far a delivery's timestamp may be from now. */
    public static final long DEFAULT_TOLERANCE_SECONDS = 300;

    private static final List<String> REQUIRED =
            Arrays.asList("nr-webhook-id", "nr-webhook-timestamp", "nr-webhook-key-id", "nr-webhook-signature");
    private static final Pattern KEY_ID = Pattern.compile("whk_[A-Za-z0-9]{8,64}");
    private static final Pattern SIGNATURE = Pattern.compile("v1=[0-9a-f]{64}");
    private static final Pattern TIMESTAMP = Pattern.compile("[0-9]{1,12}");
    private static final Pattern CHALLENGE = Pattern.compile("[A-Za-z0-9_-]{43}");
    private static final Pattern EVENT_ID = Pattern.compile("evt_[0-9A-HJKMNP-TV-Z]{26}");
    private static final Pattern ASSESSMENT_ID = Pattern.compile("asm_[0-9A-HJKMNP-TV-Z]{26}");
    private static final Pattern ACCOUNT_ID = Pattern.compile("[A-Za-z0-9][A-Za-z0-9._:|-]{0,127}");
    private static final Pattern ENDPOINT_ID = Pattern.compile("whe_[0-9A-HJKMNP-TV-Z]{26}");

    private Webhook() {
    }

    /**
     * Verify one delivery and return the typed event, with the default tolerance.
     *
     * @param payload the raw request body
     * @param headers header name to values, in any letter case; each NR-Webhook-* header must appear exactly once
     * @param secrets each whk_ key id NoResponse gave you to its secret; hold both during a rotation
     * @throws VerificationException when the delivery must be refused
     */
    public static WebhookEvent constructEvent(byte[] payload, Map<String, ? extends Collection<String>> headers,
            Map<String, String> secrets) {
        return constructEvent(payload, headers, secrets, DEFAULT_TOLERANCE_SECONDS, Instant.now().getEpochSecond());
    }

    /**
     * Verify one delivery against an explicit tolerance and clock.
     *
     * @throws VerificationException when the delivery must be refused
     */
    public static WebhookEvent constructEvent(byte[] payload, Map<String, ? extends Collection<String>> headers,
            Map<String, String> secrets, long toleranceSeconds, long nowEpochSeconds) {
        Map<String, String> found = collect(headers);
        String timestamp = found.get("nr-webhook-timestamp");
        if (!TIMESTAMP.matcher(timestamp).matches()
                || Math.abs(nowEpochSeconds - Long.parseLong(timestamp)) > toleranceSeconds) {
            throw new VerificationException(Code.INVALID_TIMESTAMP, "Webhook timestamp is outside the replay window");
        }
        String keyId = found.get("nr-webhook-key-id");
        if (!KEY_ID.matcher(keyId).matches() || !secrets.containsKey(keyId)) {
            throw new VerificationException(Code.UNKNOWN_KEY, "Webhook key id is not one this receiver holds");
        }
        String signature = found.get("nr-webhook-signature");
        if (!SIGNATURE.matcher(signature).matches()) {
            throw new VerificationException(Code.INVALID_SIGNATURE, "Webhook signature is malformed");
        }
        byte[] expected = hmac(secrets.get(keyId), timestamp, payload);
        byte[] given = signature.substring(3).getBytes(StandardCharsets.US_ASCII);
        if (!MessageDigest.isEqual(expected, given)) {
            throw new VerificationException(Code.INVALID_SIGNATURE, "Webhook signature does not match");
        }
        return event(payload, found.get("nr-webhook-id"));
    }

    /**
     * The body to answer an endpoint.verification event with, as JSON, status 200.
     *
     * @throws VerificationException for any other event
     */
    public static Map<String, String> verificationResponse(WebhookEvent event) {
        EndpointVerification verification = event.verification().orElseThrow(
                () -> new VerificationException(Code.INVALID_PAYLOAD, "Not an endpoint.verification event"));
        Map<String, String> body = new HashMap<>();
        body.put("challenge", verification.challenge());
        return body;
    }

    private static Map<String, String> collect(Map<String, ? extends Collection<String>> headers) {
        Map<String, String> found = new HashMap<>();
        for (Map.Entry<String, ? extends Collection<String>> header : headers.entrySet()) {
            String key = header.getKey().toLowerCase(Locale.ROOT);
            if (!REQUIRED.contains(key)) {
                continue;
            }
            Collection<String> values = header.getValue();
            if (values.size() != 1 || found.containsKey(key)) {
                throw new VerificationException(Code.DUPLICATE_HEADER, "Webhook header " + key + " is repeated");
            }
            found.put(key, values.iterator().next());
        }
        for (String key : REQUIRED) {
            String value = found.get(key);
            if (value == null || value.isEmpty()) {
                throw new VerificationException(Code.MISSING_HEADER, "Webhook header " + key + " is missing");
            }
        }
        return found;
    }

    private static byte[] hmac(String secret, String timestamp, byte[] payload) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            mac.update((timestamp + ".").getBytes(StandardCharsets.US_ASCII));
            byte[] digest = mac.doFinal(payload);
            StringBuilder hex = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString().getBytes(StandardCharsets.US_ASCII);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("HmacSHA256 is unavailable", e);
        }
    }

    private static WebhookEvent event(byte[] payload, String headerId) {
        ObjectMapper mapper = JSON.getMapper();
        JsonNode body;
        try {
            body = mapper.readTree(payload);
        } catch (IOException e) {
            throw new VerificationException(Code.INVALID_PAYLOAD, "Webhook body is not JSON");
        }
        if (body == null || !body.isObject() || !body.path("id").isTextual()
                || !EVENT_ID.matcher(body.path("id").asText()).matches() || !body.path("id").asText().equals(headerId)) {
            throw new VerificationException(Code.INVALID_PAYLOAD, "Webhook event id does not match NR-Webhook-Id");
        }
        JsonNode data = body.path("data");
        if (!body.path("created_at").isTextual() || !validDateTime(body.path("created_at").asText())
                || !body.path("type").isTextual() || !data.isObject()) {
            throw new VerificationException(Code.INVALID_PAYLOAD, "Webhook event envelope is malformed");
        }
        String type = body.path("type").asText();
        return new WebhookEvent(headerId, type, body.path("created_at").asText(), data(mapper, type, data));
    }

    private static Object data(ObjectMapper mapper, String type, JsonNode data) {
        if ("endpoint.verification".equals(type)) {
            JsonNode endpointId = data.path("endpoint_id");
            JsonNode challenge = data.path("challenge");
            if (!endpointId.isTextual() || !ENDPOINT_ID.matcher(endpointId.asText()).matches()
                    || !challenge.isTextual() || !CHALLENGE.matcher(challenge.asText()).matches()) {
                throw new VerificationException(Code.INVALID_PAYLOAD, "Endpoint verification event is malformed");
            }
            return new EndpointVerification(endpointId.asText(), challenge.asText());
        }
        if (!"assessment.updated".equals(type)) {
            throw new VerificationException(Code.INVALID_PAYLOAD, "Unknown webhook event type");
        }
        if ("expired".equals(data.path("status").asText(null))) {
            if (!data.path("assessment_id").isTextual()
                    || !ASSESSMENT_ID.matcher(data.path("assessment_id").asText()).matches()
                    || !data.path("account_id").isTextual() || !ACCOUNT_ID.matcher(data.path("account_id").asText()).matches()
                    || !data.path("assessment_version").isIntegralNumber()
                    || data.path("assessment_version").asLong() < 1
                    || !"expired".equals(data.path("change_reason").asText(null))) {
                throw new VerificationException(Code.INVALID_PAYLOAD, "Expired assessment event is malformed");
            }
            return new AssessmentExpired(data.path("assessment_id").asText(), data.path("account_id").asText(),
                    data.path("assessment_version").asLong());
        }
        try {
            return mapper.treeToValue(data, Assessment.class);
        } catch (IOException | IllegalArgumentException e) {
            throw new VerificationException(Code.INVALID_PAYLOAD, "Webhook data is not an assessment");
        }
    }

    private static boolean validDateTime(String value) {
        try {
            Instant.parse(value);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
}
