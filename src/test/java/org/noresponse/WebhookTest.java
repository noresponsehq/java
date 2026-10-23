package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.Test;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.AccountDecision;
import org.noresponse.models.shared.Account;
import org.noresponse.models.shared.AccountDecisionsByFlow;
import org.noresponse.webhooks.AccountDeleted;
import org.noresponse.webhooks.AccountRiskChanged;
import org.noresponse.webhooks.AssessmentExpired;
import org.noresponse.webhooks.EndpointDisabled;
import org.noresponse.webhooks.TenantDeleted;
import org.noresponse.webhooks.VerificationException;
import org.noresponse.webhooks.VerificationException.Code;
import org.noresponse.webhooks.Webhook;
import org.noresponse.webhooks.WebhookEvent;

class WebhookTest {
    private static final Path FIXTURES = Paths.get("tests", "fixtures");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final long NOW = 1791968402L;

    private static JsonNode fixture(String name) throws IOException {
        return MAPPER.readTree(Files.readAllBytes(FIXTURES.resolve(name)));
    }

    private static Map<String, List<String>> headers(Map<String, String> single) {
        Map<String, List<String>> out = new LinkedHashMap<>();
        single.forEach((k, v) -> out.put(k, new ArrayList<>(List.of(v))));
        return out;
    }

    private static Map<String, String> vectorHeaders(JsonNode vector) {
        Map<String, String> out = new LinkedHashMap<>();
        vector.path("headers").fields().forEachRemaining(e -> out.put(e.getKey(), e.getValue().asText()));
        return out;
    }

    private static byte[] raw(JsonNode body) throws IOException {
        return MAPPER.writeValueAsBytes(body);
    }

    private static Map<String, List<String>> signed(byte[] raw, String secret, String id, String keyId)
            throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        mac.update((NOW + ".").getBytes(StandardCharsets.US_ASCII));
        StringBuilder hex = new StringBuilder();
        for (byte b : mac.doFinal(raw)) {
            hex.append(String.format("%02x", b));
        }
        Map<String, String> h = new LinkedHashMap<>();
        h.put("NR-Webhook-Id", id);
        h.put("NR-Webhook-Timestamp", Long.toString(NOW));
        h.put("NR-Webhook-Key-Id", keyId);
        h.put("NR-Webhook-Signature", "v1=" + hex);
        return headers(h);
    }

    private static void refused(Code code, byte[] payload, Map<String, List<String>> headers,
            Map<String, String> secrets, long now) {
        VerificationException e = assertThrows(VerificationException.class,
                () -> Webhook.constructEvent(payload, headers, secrets, Webhook.DEFAULT_TOLERANCE_SECONDS, now));
        assertEquals(code, e.code(), e.getMessage());
    }

    @Test
    void sharedVectorYieldsTypedAssessment() throws IOException {
        JsonNode vector = fixture("webhook-signature-vector.json");
        byte[] payload = vector.path("payload").asText().getBytes(StandardCharsets.UTF_8);
        Map<String, String> secrets = Map.of(vector.path("key_id").asText(), vector.path("secret_utf8").asText());
        Map<String, String> h = vectorHeaders(vector);
        WebhookEvent event = Webhook.constructEvent(payload, headers(h), secrets, 300, NOW);
        assertEquals("assessment.updated", event.type());
        assertEquals(h.get("NR-Webhook-Id"), event.id());
        assertEquals("2026-10-14T09:00:02.000Z", event.createdAt());
        Assessment assessment = event.assessment().orElseThrow();
        assertEquals("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM", assessment.id());
        assertEquals(2L, assessment.detail().assessmentVersion());
        assertEquals(AccountDecision.STEP_UP, assessment.account().orElseThrow().risk().decision());
        assertFalse(event.isExpired());
    }

    @Test
    void refusals() throws IOException {
        JsonNode vector = fixture("webhook-signature-vector.json");
        byte[] payload = vector.path("payload").asText().getBytes(StandardCharsets.UTF_8);
        String keyId = vector.path("key_id").asText();
        Map<String, String> secrets = Map.of(keyId, vector.path("secret_utf8").asText());
        Map<String, String> h = vectorHeaders(vector);

        byte[] tampered = (vector.path("payload").asText() + " ").getBytes(StandardCharsets.UTF_8);
        refused(Code.INVALID_SIGNATURE, tampered, headers(h), secrets, NOW);
        refused(Code.INVALID_TIMESTAMP, payload, headers(h), secrets, NOW + 301);
        refused(Code.INVALID_TIMESTAMP, payload, headers(h), secrets, NOW - 301);
        assertEquals(h.get("NR-Webhook-Id"), Webhook.constructEvent(payload, headers(h), secrets, 600, NOW + 301).id());

        Map<String, List<String>> repeated = headers(h);
        repeated.get("NR-Webhook-Id").add("duplicate");
        refused(Code.DUPLICATE_HEADER, payload, repeated, secrets, NOW);
        Map<String, List<String>> spelled = headers(h);
        spelled.put("nr-webhook-ID", List.of(h.get("NR-Webhook-Id")));
        refused(Code.DUPLICATE_HEADER, payload, spelled, secrets, NOW);
        Map<String, List<String>> missing = headers(h);
        missing.remove("NR-Webhook-Signature");
        refused(Code.MISSING_HEADER, payload, missing, secrets, NOW);
        refused(Code.UNKNOWN_KEY, payload, headers(h), Map.of("whk_other0001", "x"), NOW);
        refused(Code.INVALID_SIGNATURE, payload, headers(h), Map.of(keyId, "other-secret"), NOW);
        Map<String, List<String>> malformed = headers(h);
        malformed.put("NR-Webhook-Signature", List.of("v2=abc"));
        refused(Code.INVALID_SIGNATURE, payload, malformed, secrets, NOW);
    }

    @Test
    void aTestEventSaysSoAndARealOneDoesNot() throws Exception {
        Map<String, String> secrets = Map.of("whk_sdktest01", "secret");
        JsonNode sample = fixture("valid/webhook-assessment-updated-test.json");
        byte[] body = raw(sample);
        WebhookEvent event = Webhook.constructEvent(body,
                signed(body, "secret", sample.path("id").asText(), "whk_sdktest01"), secrets, 300, NOW);
        assertTrue(event.isTest());
        assertTrue(event.assessment().isPresent());

        JsonNode real = fixture("valid/webhook-assessment-updated.json");
        byte[] realBody = raw(real);
        assertFalse(Webhook.constructEvent(realBody,
                signed(realBody, "secret", real.path("id").asText(), "whk_sdktest01"), secrets, 300, NOW).isTest());

        JsonNode expired = fixture("valid/webhook-assessment-expired.json");
        byte[] expiredBody = raw(expired);
        assertFalse(Webhook.constructEvent(expiredBody,
                signed(expiredBody, "secret", expired.path("id").asText(), "whk_sdktest01"), secrets, 300, NOW).isTest());
    }

    @Test
    void emptyHeaderIsMissing() throws IOException {
        JsonNode vector = fixture("webhook-signature-vector.json");
        byte[] payload = vector.path("payload").asText().getBytes(StandardCharsets.UTF_8);
        Map<String, String> secrets = Map.of(vector.path("key_id").asText(), vector.path("secret_utf8").asText());
        for (String name : List.of("NR-Webhook-Id", "NR-Webhook-Timestamp", "NR-Webhook-Key-Id", "NR-Webhook-Signature")) {
            Map<String, List<String>> noValues = headers(vectorHeaders(vector));
            noValues.put(name, new ArrayList<>());
            refused(Code.MISSING_HEADER, payload, noValues, secrets, NOW);
            Map<String, List<String>> emptyValue = headers(vectorHeaders(vector));
            emptyValue.put(name, List.of(""));
            refused(Code.MISSING_HEADER, payload, emptyValue, secrets, NOW);
            Map<String, List<String>> emptyThenReal = new LinkedHashMap<>();
            emptyThenReal.put(name.toLowerCase(Locale.ROOT), new ArrayList<>());
            emptyThenReal.putAll(headers(vectorHeaders(vector)));
            refused(Code.DUPLICATE_HEADER, payload, emptyThenReal, secrets, NOW);
        }
    }

    @Test
    void rotationAndEventId() throws Exception {
        JsonNode complete = fixture("valid/assessment-view-complete.json");
        ObjectNode event = MAPPER.createObjectNode();
        event.put("id", "evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ2");
        event.put("type", "assessment.updated");
        event.put("created_at", "2026-10-18T11:00:04Z");
        event.set("data", complete);
        Map<String, String> both = new HashMap<>();
        both.put("whk_sdktest01", "old-secret");
        both.put("whk_sdktest02", "new-secret");
        byte[] body = raw(event);
        WebhookEvent verified = Webhook.constructEvent(body,
                signed(body, "new-secret", "evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ2", "whk_sdktest02"), both, 300, NOW);
        assertEquals(complete.path("id").asText(), verified.assessment().orElseThrow().id());
        refused(Code.INVALID_PAYLOAD, body, signed(body, "new-secret", "evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ9", "whk_sdktest02"),
                both, NOW);

        ObjectNode pointer = event.deepCopy();
        ObjectNode data = MAPPER.createObjectNode();
        data.put("assessment_id", complete.path("id").asText());
        data.put("assessment_url", "/v1/assessments/x");
        pointer.set("data", data);
        byte[] pointerBody = raw(pointer);
        refused(Code.INVALID_PAYLOAD, pointerBody,
                signed(pointerBody, "new-secret", "evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ2", "whk_sdktest02"), both, NOW);
    }

    @Test
    void expiredAndVerificationEvents() throws Exception {
        JsonNode expired = fixture("valid/webhook-assessment-expired.json");
        byte[] body = raw(expired);
        Map<String, String> secrets = Map.of("whk_sdktest01", "secret");
        WebhookEvent event = Webhook.constructEvent(body,
                signed(body, "secret", expired.path("id").asText(), "whk_sdktest01"), secrets, 300, NOW);
        assertTrue(event.isExpired());
        assertEquals(new AssessmentExpired(expired.path("data").path("assessment_id").asText(),
                expired.path("data").path("account_id").asText(), 2), event.expired().orElseThrow());
        assertThrows(VerificationException.class, () -> Webhook.verificationResponse(event));

        JsonNode ping = fixture("valid/webhook-endpoint-verification.json");
        byte[] pingBody = raw(ping);
        WebhookEvent verification = Webhook.constructEvent(pingBody,
                signed(pingBody, "secret", ping.path("id").asText(), "whk_sdktest01"), secrets, 300, NOW);
        assertEquals("endpoint.verification", verification.type());
        assertEquals(ping.path("data").path("endpoint_id").asText(), verification.verification().orElseThrow().endpointId());
        assertEquals(Map.of("challenge", ping.path("data").path("challenge").asText()),
                Webhook.verificationResponse(verification));
    }

    @Test
    void accountDeletedEvent() throws Exception {
        JsonNode erased = fixture("valid/webhook-account-deleted.json");
        byte[] body = raw(erased);
        Map<String, String> secrets = Map.of("whk_sdktest01", "secret");
        WebhookEvent event = Webhook.constructEvent(body,
                signed(body, "secret", erased.path("id").asText(), "whk_sdktest01"), secrets, 300, NOW);
        assertEquals("account.deleted", event.type());
        assertFalse(event.isExpired());
        assertFalse(event.isTest());
        AccountDeleted deleted = event.accountDeleted().orElseThrow();
        assertEquals("acct_7f3a9c", deleted.accountId());
        assertEquals(3L, deleted.assessmentsDeleted());
        assertEquals("2026-10-23T09:00:00.000Z", deleted.deletedAt());
        assertThrows(VerificationException.class, () -> Webhook.verificationResponse(event));
        for (java.util.function.Consumer<ObjectNode> mutate : List.<java.util.function.Consumer<ObjectNode>>of(
                data -> data.put("assessments_deleted", 0),
                data -> data.put("assessments_deleted", 1.5),
                data -> data.put("account_id", "jane@example.com"),
                data -> data.put("deleted_at", "tomorrow"))) {
            ObjectNode bad = (ObjectNode) fixture("valid/webhook-account-deleted.json");
            mutate.accept((ObjectNode) bad.path("data"));
            byte[] payload = raw(bad);
            refused(Code.INVALID_PAYLOAD, payload, signed(payload, "secret", bad.path("id").asText(), "whk_sdktest01"), secrets, NOW);
        }
    }

    private static final String TENANT_DELETED = "{\"id\":\"evt_01JA2ZD3K4M5N6P7Q8R9S0T1V2\",\"type\":\"tenant.deleted\","
            + "\"created_at\":\"2026-10-23T10:00:00.000Z\",\"data\":{\"workspace_id\":\"tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM\","
            + "\"environment\":\"live\",\"deleted_at\":\"2026-10-23T10:00:00.000Z\"}}";
    private static final String ENDPOINT_DISABLED = "{\"id\":\"evt_01JA2ZD3K4M5N6P7Q8R9S0T1V3\",\"type\":\"webhook_endpoint.disabled\","
            + "\"created_at\":\"2026-10-23T10:00:00.000Z\",\"data\":{\"endpoint_id\":\"whe_01JA2ZD0E1F2G3H4J5K6M7N8P9\","
            + "\"failing_since\":\"2026-10-20T10:00:00.000Z\",\"disabled_at\":\"2026-10-23T10:00:00.000Z\"}}";

    private static void refusesChangedData(String original, List<java.util.function.Consumer<ObjectNode>> mutations) throws Exception {
        Map<String, String> secrets = Map.of("whk_sdktest01", "secret");
        for (java.util.function.Consumer<ObjectNode> mutate : mutations) {
            ObjectNode bad = (ObjectNode) MAPPER.readTree(original);
            mutate.accept((ObjectNode) bad.path("data"));
            byte[] payload = raw(bad);
            refused(Code.INVALID_PAYLOAD, payload, signed(payload, "secret", bad.path("id").asText(), "whk_sdktest01"), secrets, NOW);
        }
    }

    @Test
    void tenantDeletedEvent() throws Exception {
        byte[] body = TENANT_DELETED.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        WebhookEvent event = Webhook.constructEvent(body,
                signed(body, "secret", "evt_01JA2ZD3K4M5N6P7Q8R9S0T1V2", "whk_sdktest01"), Map.of("whk_sdktest01", "secret"), 300, NOW);
        assertEquals("tenant.deleted", event.type());
        assertEquals("evt_01JA2ZD3K4M5N6P7Q8R9S0T1V2", event.id());
        assertEquals("2026-10-23T10:00:00.000Z", event.createdAt());
        assertFalse(event.isExpired());
        assertFalse(event.isTest());
        assertFalse(event.accountDeleted().isPresent());
        TenantDeleted deleted = event.tenantDeleted().orElseThrow();
        assertEquals("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM", deleted.workspaceId());
        assertEquals("live", deleted.environment());
        assertEquals("2026-10-23T10:00:00.000Z", deleted.deletedAt());
        assertThrows(VerificationException.class, () -> Webhook.verificationResponse(event));
        refusesChangedData(TENANT_DELETED, List.<java.util.function.Consumer<ObjectNode>>of(
                data -> data.remove("workspace_id"),
                data -> data.put("workspace_id", "-tnt"),
                data -> data.put("workspace_id", "tnt/01"),
                data -> data.put("environment", "staging"),
                data -> data.remove("environment"),
                data -> data.put("deleted_at", "tomorrow"),
                data -> data.remove("deleted_at")));
    }

    @Test
    void endpointDisabledEvent() throws Exception {
        byte[] body = ENDPOINT_DISABLED.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        WebhookEvent event = Webhook.constructEvent(body,
                signed(body, "secret", "evt_01JA2ZD3K4M5N6P7Q8R9S0T1V3", "whk_sdktest01"), Map.of("whk_sdktest01", "secret"), 300, NOW);
        assertEquals("webhook_endpoint.disabled", event.type());
        assertFalse(event.isExpired());
        assertFalse(event.isTest());
        EndpointDisabled disabled = event.endpointDisabled().orElseThrow();
        assertEquals("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9", disabled.endpointId());
        assertEquals("2026-10-20T10:00:00.000Z", disabled.failingSince());
        assertEquals("2026-10-23T10:00:00.000Z", disabled.disabledAt());
        assertThrows(VerificationException.class, () -> Webhook.verificationResponse(event));
        refusesChangedData(ENDPOINT_DISABLED, List.<java.util.function.Consumer<ObjectNode>>of(
                data -> data.put("endpoint_id", "whk_01JA2ZD0E1F2G3H4J5K6M7N8P9"),
                data -> data.remove("endpoint_id"),
                data -> data.put("failing_since", "three days ago"),
                data -> data.remove("failing_since"),
                data -> data.put("disabled_at", "now"),
                data -> data.remove("disabled_at")));
    }

    @Test
    void accountRiskChangedEvent() throws Exception {
        JsonNode changed = fixture("valid/webhook-account-risk-changed.json");
        byte[] body = raw(changed);
        Map<String, String> secrets = Map.of("whk_sdktest01", "secret");
        WebhookEvent event = Webhook.constructEvent(body,
                signed(body, "secret", changed.path("id").asText(), "whk_sdktest01"), secrets, 300, NOW);
        assertEquals("account.risk_changed", event.type());
        assertEquals(changed.path("id").asText(), event.id());
        assertFalse(event.isExpired());
        assertFalse(event.isTest());
        assertTrue(event.assessment().isEmpty());
        assertTrue(event.accountDeleted().isEmpty());
        AccountRiskChanged data = event.accountRiskChanged().orElseThrow();
        // data.account() is the same type accounts().get returns.
        Account account = data.account();
        assertEquals("acct_7f3a9c", account.id());
        assertEquals("1", account.schemaVersion());
        assertEquals(70L, account.risk().score());
        assertEquals(AccountDecision.of("review"), account.risk().decision());
        assertEquals(2, account.risk().reasons().size());
        assertEquals(55L, account.risk().reasons().get(0).weight());
        assertEquals("recent_password_exposure", account.risk().reasons().get(0).code().value());
        assertEquals("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM", account.assessmentId());
        assertEquals("2026-10-23T09:00:04.812Z", account.updatedAt());
        AccountDecisionsByFlow now = account.decisionsByFlow();
        assertEquals(List.of("review", "review", "review"),
                List.of(now.signup().value(), now.login().value(), now.purchase().value()));
        AccountDecisionsByFlow before = data.previousDecisionsByFlow();
        assertEquals(List.of("allow", "allow", "allow"),
                List.of(before.signup().value(), before.login().value(), before.purchase().value()));
        assertThrows(VerificationException.class, () -> Webhook.verificationResponse(event));
        for (java.util.function.Consumer<ObjectNode> mutate : List.<java.util.function.Consumer<ObjectNode>>of(
                data2 -> data2.remove("previous_decisions_by_flow"),
                data2 -> ((ObjectNode) data2.path("previous_decisions_by_flow")).remove("login"),
                data2 -> ((ObjectNode) data2.path("account")).remove("decisions_by_flow"),
                data2 -> ((ObjectNode) data2.path("account").path("risk")).remove("score"),
                data2 -> data2.put("account", "acct_7f3a9c"),
                data2 -> ((ObjectNode) data2.path("account")).put("id", "jane@example.com"))) {
            ObjectNode bad = (ObjectNode) fixture("valid/webhook-account-risk-changed.json");
            mutate.accept((ObjectNode) bad.path("data"));
            byte[] payload = raw(bad);
            refused(Code.INVALID_PAYLOAD, payload, signed(payload, "secret", bad.path("id").asText(), "whk_sdktest01"), secrets, NOW);
        }
    }

    // The API adds fields without a new version, so keys this SDK does not know
    // must not stop a signed event from verifying.
    @Test
    void unknownFieldsAreAccepted() throws Exception {
        Map<String, String> secrets = Map.of("whk_sdktest01", "secret");
        for (String name : List.of(
                "invalid/webhook-tenant-id.json",
                "invalid/webhook-identifier-leak.json",
                "invalid/webhook-expired-with-assessment-url.json",
                "invalid/webhook-account-deleted-assessment-id.json")) {
            JsonNode body = fixture(name);
            byte[] payload = raw(body);
            String id = body.path("id").asText();
            assertEquals(id, Webhook.constructEvent(payload, signed(payload, "secret", id, "whk_sdktest01"), secrets, 300, NOW).id());
        }
    }

    @Test
    void contractInvalidFixturesAndMalformedIdentifiersAreRefused() throws Exception {
        Map<String, String> secrets = Map.of("whk_sdktest01", "secret");
        for (String name : List.of(
                "invalid/webhook-update-names-source.json",
                "invalid/webhook-unknown-event-type.json",
                "invalid/webhook-update-pointer-only.json",
                "invalid/webhook-update-with-expired-status.json",
                "invalid/webhook-verification-without-challenge.json",
                "invalid/webhook-account-deleted-none.json")) {
            JsonNode body = fixture(name);
            byte[] payload = raw(body);
            refused(Code.INVALID_PAYLOAD, payload,
                    signed(payload, "secret", body.path("id").asText(), "whk_sdktest01"), secrets, NOW);
        }

        ObjectNode endpoint = (ObjectNode) fixture("valid/webhook-endpoint-verification.json");
        endpoint.put("id", "evt_bad");
        byte[] payload = raw(endpoint);
        refused(Code.INVALID_PAYLOAD, payload,
                signed(payload, "secret", "evt_bad", "whk_sdktest01"), secrets, NOW);

        endpoint = (ObjectNode) fixture("valid/webhook-endpoint-verification.json");
        ((ObjectNode) endpoint.path("data")).put("endpoint_id", "whe_bad");
        payload = raw(endpoint);
        refused(Code.INVALID_PAYLOAD, payload,
                signed(payload, "secret", endpoint.path("id").asText(), "whk_sdktest01"), secrets, NOW);

        endpoint = (ObjectNode) fixture("valid/webhook-endpoint-verification.json");
        endpoint.put("created_at", "tomorrow");
        payload = raw(endpoint);
        refused(Code.INVALID_PAYLOAD, payload,
                signed(payload, "secret", endpoint.path("id").asText(), "whk_sdktest01"), secrets, NOW);

        endpoint = (ObjectNode) fixture("valid/webhook-endpoint-verification.json");
        endpoint.put("created_at", "2026-10-14");
        payload = raw(endpoint);
        refused(Code.INVALID_PAYLOAD, payload,
                signed(payload, "secret", endpoint.path("id").asText(), "whk_sdktest01"), secrets, NOW);
    }
}
