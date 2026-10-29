package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.noresponse.models.shared.AccountDeletedEvent;
import org.noresponse.models.shared.AccountRiskChangedEvent;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.AssessmentUpdatedEvent;
import org.noresponse.models.shared.AuditEventEvent;
import org.noresponse.models.shared.EndpointVerificationEvent;
import org.noresponse.models.shared.WebhookEndpointDisabledEvent;
import org.noresponse.models.shared.WebhookEvent;
import org.noresponse.models.shared.WorkspaceDeletedEvent;
import org.noresponse.utils.Utils;
import org.noresponse.webhookverification.Webhook;
import org.noresponse.webhookverification.WebhookVerificationException;
import org.noresponse.webhookverification.WebhookVerificationException.Code;

/** The contract's signature vector through the generated webhook verifier. */
class WebhookVectorTest {
    private static final Path FIXTURES = Paths.get("tests", "fixtures");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final JsonNode VECTOR = read(FIXTURES.resolve("webhook-signature-vector.json"));
    private static final byte[] PAYLOAD = VECTOR.path("payload").asText().getBytes(StandardCharsets.UTF_8);
    private static final Map<String, String> SECRETS =
            Map.of(VECTOR.path("key_id").asText(), VECTOR.path("secret_utf8").asText());
    private static final Instant NOW = Instant.ofEpochSecond(VECTOR.path("timestamp").asLong());
    private static final Map<String, Class<? extends WebhookEvent>> TYPES = Map.of(
            "assessment.updated", AssessmentUpdatedEvent.class,
            "endpoint.verification", EndpointVerificationEvent.class,
            "account.deleted", AccountDeletedEvent.class,
            "workspace.deleted", WorkspaceDeletedEvent.class,
            "webhook_endpoint.disabled", WebhookEndpointDisabledEvent.class,
            "account.risk_changed", AccountRiskChangedEvent.class,
            "audit.event", AuditEventEvent.class);

    private static JsonNode read(Path path) {
        try {
            return MAPPER.readTree(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Map<String, List<String>> vectorHeaders() {
        Map<String, List<String>> headers = new LinkedHashMap<>();
        VECTOR.path("headers").fields().forEachRemaining(e -> headers.put(e.getKey(), List.of(e.getValue().asText())));
        return headers;
    }

    private static WebhookEvent construct(
            byte[] payload, Map<String, ? extends Collection<String>> headers, Map<String, String> secrets, Instant now) {
        return Webhook.constructEvent(payload, headers, secrets, Duration.ofSeconds(Webhook.DEFAULT_TOLERANCE_SECONDS), now);
    }

    private static void refused(Code code, byte[] payload, Map<String, List<String>> headers,
            Map<String, String> secrets, Instant now) {
        WebhookVerificationException e =
                assertThrows(WebhookVerificationException.class, () -> construct(payload, headers, secrets, now));
        assertEquals(code, e.code(), e.getMessage());
    }

    @Test
    void theVectorVerifiesIntoATypedAssessmentEvent() {
        WebhookEvent event = construct(PAYLOAD, vectorHeaders(), SECRETS, NOW);
        assertEquals("assessment.updated", event.type());
        Assessment assessment =
                assertInstanceOf(AssessmentUpdatedEvent.class, event).data().assessment().orElseThrow();
        assertEquals("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM", assessment.id());
        assertEquals(2L, assessment.detail().assessmentVersion());
    }

    @Test
    void aTamperedStaleUnknownRepeatedOrIncompleteDeliveryIsRefused() {
        byte[] trailingSpace = (VECTOR.path("payload").asText() + " ").getBytes(StandardCharsets.UTF_8);
        refused(Code.INVALID_SIGNATURE, trailingSpace, vectorHeaders(), SECRETS, NOW);
        refused(Code.INVALID_TIMESTAMP, PAYLOAD, vectorHeaders(), SECRETS, NOW.plusSeconds(301));
        refused(Code.UNKNOWN_KEY, PAYLOAD, vectorHeaders(),
                Map.of("whk_other0001", VECTOR.path("secret_utf8").asText()), NOW);

        Map<String, List<String>> repeated = vectorHeaders();
        repeated.put("nr-webhook-signature", repeated.get("NR-Webhook-Signature"));
        refused(Code.DUPLICATE_HEADER, PAYLOAD, repeated, SECRETS, NOW);

        Map<String, List<String>> missing = vectorHeaders();
        missing.remove("NR-Webhook-Id");
        refused(Code.MISSING_HEADER, PAYLOAD, missing, SECRETS, NOW);
    }

    static Stream<Path> typedEventFixtures() throws IOException {
        try (Stream<Path> files = Files.list(FIXTURES.resolve("valid"))) {
            List<Path> typed = files
                    .filter(p -> p.getFileName().toString().matches("webhook-.*\\.json"))
                    .filter(p -> read(p).hasNonNull("type"))
                    .sorted()
                    .collect(Collectors.toList());
            return typed.stream();
        }
    }

    @ParameterizedTest
    @MethodSource("typedEventFixtures")
    void everyContractEventFixtureParsesIntoItsTypedEvent(Path fixture) throws Exception {
        JsonNode body = read(fixture);
        byte[] raw = MAPPER.writeValueAsBytes(body);
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec("fixture-secret".getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        mac.update((NOW.getEpochSecond() + ".").getBytes(StandardCharsets.US_ASCII));
        StringBuilder hex = new StringBuilder("v1=");
        for (byte b : mac.doFinal(raw)) {
            hex.append(String.format("%02x", b));
        }
        Map<String, List<String>> headers = new LinkedHashMap<>();
        headers.put("NR-Webhook-Id", List.of(body.path("id").asText()));
        headers.put("NR-Webhook-Timestamp", List.of(Long.toString(NOW.getEpochSecond())));
        headers.put("NR-Webhook-Key-Id", List.of("whk_fixture0001"));
        headers.put("NR-Webhook-Signature", List.of(hex.toString()));

        WebhookEvent event = construct(raw, headers, Map.of("whk_fixture0001", "fixture-secret"), NOW);
        String type = body.path("type").asText();
        assertEquals(type, event.type());
        assertInstanceOf(TYPES.get(type), event);
        assertEquals(body, MAPPER.readTree(Utils.mapper().writeValueAsBytes(event)),
                "the typed event differs from " + fixture.getFileName());
    }
}
