package org.noresponse.webhookverification;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.noresponse.models.shared.WebhookEvent;
import org.noresponse.utils.Utils;
import org.noresponse.webhookverification.WebhookVerificationException.Code;

/** Verifies webhook deliveries and parses them into {@link WebhookEvent}. */
public final class Webhook {
    /** How far, in seconds, the NR-Webhook-Timestamp header may be from the receiver's clock. */
    public static final long DEFAULT_TOLERANCE_SECONDS = 300;

    private static final String[] HEADER_NAMES =
            {"NR-Webhook-Id", "NR-Webhook-Timestamp", "NR-Webhook-Key-Id", "NR-Webhook-Signature"};

    private static final Pattern TIMESTAMP = Pattern.compile("[0-9]{1,12}");

    private Webhook() {}

    /**
     * Verifies a webhook delivery against the receiver's clock and {@link #DEFAULT_TOLERANCE_SECONDS}, and parses its body.
     *
     * @param payload the raw request body exactly as received: re-encoding parsed JSON breaks the signature
     * @param headers the request headers in any letter case; a header repeated or sent as a comma-joined list is refused
     * @param secrets each key id, sent in NR-Webhook-Key-Id, to its secret, so a receiver can hold two during a rotation
     * @throws WebhookVerificationException when the delivery must not be processed
     */
    public static WebhookEvent constructEvent(
            byte[] payload, Map<String, ? extends Collection<String>> headers, Map<String, String> secrets) {
        return constructEvent(payload, headers, secrets, Duration.ofSeconds(DEFAULT_TOLERANCE_SECONDS), Instant.now());
    }

    /**
     * Verifies a webhook delivery against an explicit tolerance and clock, and parses its body.
     *
     * @throws WebhookVerificationException when the delivery must not be processed
     */
    public static WebhookEvent constructEvent(
            byte[] payload,
            Map<String, ? extends Collection<String>> headers,
            Map<String, String> secrets,
            Duration tolerance,
            Instant now) {
        String[] values = headerValues(headers);
        int next = 0;
        String id = values[next++];
        String timestamp = values[next++];
        String keyId = values[next++];
        String signature = values[next];

        if (!TIMESTAMP.matcher(timestamp).matches()) {
            throw new WebhookVerificationException(
                    Code.INVALID_TIMESTAMP, "NR-Webhook-Timestamp is not a Unix time in seconds");
        }
        Duration skew = Duration.between(Instant.ofEpochSecond(Long.parseLong(timestamp)), now);
        if ((skew.isNegative() ? skew.negated() : skew).compareTo(tolerance) > 0) {
            throw new WebhookVerificationException(
                    Code.INVALID_TIMESTAMP, "NR-Webhook-Timestamp is outside the tolerance");
        }
        if (!secrets.containsKey(keyId)) {
            throw new WebhookVerificationException(
                    Code.UNKNOWN_KEY, "NR-Webhook-Key-Id names a key this receiver does not hold");
        }
        String secret = secrets.get(keyId);
        if (secret == null || secret.isEmpty()) {
            throw new WebhookVerificationException(Code.INVALID_SIGNATURE, "the webhook secret is empty");
        }

        String prefix = "v1=";
        byte[] received = signature.startsWith(prefix) ? decode(signature.substring(prefix.length())) : null;
        if (received == null) {
            throw new WebhookVerificationException(Code.INVALID_SIGNATURE, "NR-Webhook-Signature is malformed");
        }

        byte[] expected;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            mac.update(substitute("{timestamp}.", timestamp, id));
            mac.update(payload);
            expected = mac.doFinal();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("HmacSHA256 is unavailable", e);
        }
        if (!MessageDigest.isEqual(expected, received)) {
            throw new WebhookVerificationException(Code.INVALID_SIGNATURE, "NR-Webhook-Signature does not match");
        }

        try {
            JsonNode envelope = Utils.mapper()
                    .reader()
                    .with(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
                    .readTree(payload);
            JsonNode bodyId = envelope == null ? null : envelope.get("id");
            if (envelope == null
                    || !envelope.isObject()
                    || bodyId == null
                    || !bodyId.isTextual()
                    || !bodyId.textValue().equals(id)) {
                throw new WebhookVerificationException(
                        Code.INVALID_PAYLOAD, "the body's id does not equal NR-Webhook-Id");
            }
            WebhookEvent event = Utils.mapper()
                    .readerFor(WebhookEvent.class)
                    .with(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
                    .readValue(payload);
            if (event == null) {
                throw new WebhookVerificationException(Code.INVALID_PAYLOAD, "the body is not a known webhook event");
            }
            return event;
        } catch (WebhookVerificationException e) {
            throw e;
        } catch (Exception e) {
            throw new WebhookVerificationException(Code.INVALID_PAYLOAD, "the body is not a known webhook event");
        }
    }

    /** Like {@link #constructEvent(byte[], Map, Map)} for a body already read as text; it is verified as UTF-8. */
    public static WebhookEvent constructEvent(
            String payload, Map<String, ? extends Collection<String>> headers, Map<String, String> secrets) {
        return constructEvent(payload.getBytes(StandardCharsets.UTF_8), headers, secrets);
    }

    /** Like {@link #constructEvent(byte[], Map, Map, Duration, Instant)} for a body already read as text; it is verified as UTF-8. */
    public static WebhookEvent constructEvent(
            String payload,
            Map<String, ? extends Collection<String>> headers,
            Map<String, String> secrets,
            Duration tolerance,
            Instant now) {
        return constructEvent(payload.getBytes(StandardCharsets.UTF_8), headers, secrets, tolerance, now);
    }

    private static String[] headerValues(Map<String, ? extends Collection<String>> headers) {
        String[] values = new String[HEADER_NAMES.length];
        for (int i = 0; i < HEADER_NAMES.length; i++) {
            String name = HEADER_NAMES[i];
            List<String> found = new ArrayList<>();
            for (Map.Entry<String, ? extends Collection<String>> header : headers.entrySet()) {
                if (header.getKey() != null && header.getKey().equalsIgnoreCase(name) && header.getValue() != null) {
                    found.addAll(header.getValue());
                }
            }
            if (found.size() > 1 || (found.size() == 1 && found.get(0) != null && found.get(0).contains(","))) {
                throw new WebhookVerificationException(Code.DUPLICATE_HEADER, name + " is repeated");
            }
            if (found.isEmpty() || found.get(0) == null || found.get(0).isEmpty()) {
                throw new WebhookVerificationException(Code.MISSING_HEADER, name + " is missing");
            }
            values[i] = found.get(0);
        }
        return values;
    }

    private static byte[] substitute(String template, String timestamp, String id) {
        return template.replace("{timestamp}", timestamp).replace("{id}", id).getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] decode(String encoded) {
        if (encoded.length() % 2 != 0) {
            return null;
        }
        byte[] out = new byte[encoded.length() / 2];
        for (int i = 0; i < out.length; i++) {
            int hi = hexDigit(encoded.charAt(2 * i));
            int lo = hexDigit(encoded.charAt(2 * i + 1));
            if (hi < 0 || lo < 0) {
                return null;
            }
            out[i] = (byte) ((hi << 4) | lo);
        }
        return out;
    }

    private static int hexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        return -1;
    }
}
