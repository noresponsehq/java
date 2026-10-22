package org.noresponse.lateresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.Test;
import org.noresponse.webhooks.VerificationException;
import org.noresponse.webhooks.VerificationException.Code;
import org.noresponse.webhooks.Webhook;
import org.noresponse.webhooks.WebhookEvent;

class LateResultsTest {
    private static final Path FIXTURES = Paths.get("tests", "fixtures");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String SECRET = "late-result-secret";
    private static final String KEY = "whk_sdktest01";
    private static final long NOW = 1791968402L;
    private static final Map<String, String> SECRETS = Map.of(KEY, SECRET);

    private static JsonNode fixture(String name) throws IOException {
        return MAPPER.readTree(Files.readAllBytes(FIXTURES.resolve(name)));
    }

    /** A signed assessment.updated delivery, optionally overriding account_id. */
    private static final class Delivery {
        final byte[] raw;
        final Map<String, List<String>> headers;

        Delivery(JsonNode data, String accountId) throws Exception {
            ObjectNode payload = data.deepCopy();
            if (accountId != null) {
                payload.put("account_id", accountId);
            }
            ObjectNode body = MAPPER.createObjectNode();
            body.put("id", "evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ2");
            body.put("type", "assessment.updated");
            body.put("created_at", "2026-10-18T11:00:04Z");
            body.set("data", payload);
            raw = MAPPER.writeValueAsBytes(body);
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            mac.update((NOW + ".").getBytes(StandardCharsets.US_ASCII));
            StringBuilder hex = new StringBuilder();
            for (byte b : mac.doFinal(raw)) {
                hex.append(String.format("%02x", b));
            }
            headers = new LinkedHashMap<>();
            headers.put("NR-Webhook-Id", List.of("evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ2"));
            headers.put("NR-Webhook-Timestamp", List.of(Long.toString(NOW)));
            headers.put("NR-Webhook-Key-Id", List.of(KEY));
            headers.put("NR-Webhook-Signature", List.of("v1=" + hex));
        }
    }

    private static final class Call {
        String method;
        String path;
        Map<String, String> query = new LinkedHashMap<>();
        String auth;
        String accept;
        String contentType;
        String body;

        String line() {
            return method + " " + path;
        }
    }

    private static final class Reply {
        final int status;
        final String body;

        Reply(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }

    /** A local HTTP server standing in for a provider's admin API. */
    private static final class Mock implements AutoCloseable {
        final List<Call> calls = Collections.synchronizedList(new ArrayList<>());
        final HttpServer server;

        Mock(Function<Call, Reply> route) throws IOException {
            server = HttpServer.create(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 0), 0);
            server.createContext("/", exchange -> {
                Call call = new Call();
                call.method = exchange.getRequestMethod();
                call.path = exchange.getRequestURI().getRawPath();
                String rawQuery = exchange.getRequestURI().getRawQuery();
                if (rawQuery != null) {
                    for (String pair : rawQuery.split("&")) {
                        String[] kv = pair.split("=", 2);
                        call.query.put(kv[0], kv.length > 1 ? kv[1] : "");
                    }
                }
                call.auth = exchange.getRequestHeaders().getFirst("Authorization");
                call.accept = exchange.getRequestHeaders().getFirst("Accept");
                call.contentType = exchange.getRequestHeaders().getFirst("Content-Type");
                String text = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                call.body = text.isEmpty() ? null : text;
                calls.add(call);
                Reply reply = route.apply(call);
                if (reply == null) {
                    reply = new Reply(404, "{\"error\":\"not found\"}");
                }
                byte[] out = reply.body == null ? new byte[0] : reply.body.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                if (out.length == 0) {
                    exchange.sendResponseHeaders(reply.status, -1);
                } else {
                    exchange.sendResponseHeaders(reply.status, out.length);
                    exchange.getResponseBody().write(out);
                }
                exchange.close();
            });
            server.start();
        }

        String url() {
            return "http://127.0.0.1:" + server.getAddress().getPort();
        }

        List<String> lines() {
            List<String> out = new ArrayList<>();
            for (Call c : new ArrayList<>(calls)) {
                out.add(c.line());
            }
            return out;
        }

        void assertEveryCall(String auth) {
            for (Call c : calls) {
                assertEquals(auth, c.auth);
                assertEquals("application/json", c.accept);
                if (c.body != null) {
                    assertEquals("application/json", c.contentType);
                }
            }
        }

        @Override
        public void close() {
            server.stop(0);
        }
    }

    private static LateResultOptions opts() {
        return LateResultOptions.builder().nowEpochSeconds(NOW).build();
    }

    private static LateResultOptions opts(LateResultPolicy policy) {
        return LateResultOptions.builder().nowEpochSeconds(NOW).policy(policy).build();
    }

    private static WebhookEvent event(JsonNode data) throws Exception {
        Delivery d = new Delivery(data, null);
        return Webhook.constructEvent(d.raw, d.headers, SECRETS, 300, NOW);
    }

    private static ObjectNode with(JsonNode data, String status, String recommendation) {
        ObjectNode copy = data.deepCopy();
        copy.put("status", status);
        if (recommendation == null) {
            copy.remove("recommendation");
            copy.remove("risk");
        } else {
            copy.put("recommendation", recommendation);
            if (copy.has("risk")) {
                ((ObjectNode) copy.get("risk")).put("recommendation", recommendation);
            }
        }
        return copy;
    }

    @Test
    void defaultPolicyRevokesOnReviewAndDoesNothingOnAllowStepUpPendingOrExpiry() throws Exception {
        JsonNode review = fixture("valid/assessment-phone-uncovered-complete.json");
        JsonNode stepUp = fixture("valid/assessment-complete.json");
        JsonNode pending = fixture("valid/assessment-pending.json");
        JsonNode expired = fixture("valid/webhook-assessment-expired.json").path("data");
        assertEquals(LateResultAction.REVOKE_SESSIONS, LateResults.lateResultAction(event(with(review, "complete", "review"))));
        assertEquals(LateResultAction.NONE, LateResults.lateResultAction(event(with(stepUp, "complete", "step_up"))));
        assertEquals(LateResultAction.NONE, LateResults.lateResultAction(event(with(stepUp, "partial", "allow"))));
        assertEquals(LateResultAction.NONE, LateResults.lateResultAction(event(pending)));
        assertEquals(LateResultAction.NONE, LateResults.lateResultAction(event(expired)));
        LateResultPolicy stepUpBan = LateResultPolicy.fromWire(Map.of("step_up", "ban"));
        assertEquals(LateResultAction.BAN, LateResults.lateResultAction(event(with(stepUp, "complete", "step_up")), stepUpBan));
        assertEquals(LateResultAction.NONE, LateResults.lateResultAction(event(with(review, "complete", "review")), stepUpBan));
        assertThrows(IllegalArgumentException.class, () -> LateResultPolicy.fromWire(Map.of("review", "delete")));
        assertEquals(Map.of("allow", LateResultAction.NONE, "step_up", LateResultAction.NONE,
                "review", LateResultAction.REVOKE_SESSIONS), LateResultPolicy.DEFAULT.asMap());
        assertEquals("revoke_sessions", LateResultAction.REVOKE_SESSIONS.wire());

        // The recommendation falls back to risk.recommendation when the top-level one is absent.
        ObjectNode riskOnly = with(fixture("valid/assessment-risk.json"), "complete", "review");
        riskOnly.remove("recommendation");
        assertTrue(riskOnly.has("risk"));
        assertEquals(LateResultAction.REVOKE_SESSIONS, LateResults.lateResultAction(event(riskOnly)));
    }

    @Test
    void clerkReviewRevokesEachActiveSessionAndABanPolicyBansTheUser() throws Exception {
        JsonNode review = fixture("valid/assessment-phone-uncovered-complete.json");
        try (Mock mock = new Mock(c -> {
            if (c.method.equals("GET") && c.path.equals("/v1/sessions")) {
                return new Reply(200, "0".equals(c.query.get("offset"))
                        ? "[{\"id\":\"sess_1\",\"status\":\"active\"},{\"id\":\"sess_2\",\"status\":\"active\"}]" : "[]");
            }
            if (c.method.equals("POST") && c.path.matches("/v1/sessions/sess_\\d/revoke")) {
                return new Reply(200, "{\"id\":\"" + c.path.split("/")[3] + "\",\"status\":\"revoked\"}");
            }
            if (c.method.equals("POST") && c.path.equals("/v1/users/user_2EXAMPLE/ban")) {
                return new Reply(200, "{\"id\":\"user_2EXAMPLE\",\"banned\":true}");
            }
            return null;
        })) {
            ClerkActions clerk = new ClerkActions("sk_test_mock", mock.url() + "/v1", java.net.http.HttpClient.newHttpClient());
            Delivery d = new Delivery(review, "user_2EXAMPLE");
            LateResult result = LateResults.handleLateResult(d.raw, d.headers, SECRETS, clerk, opts());
            assertEquals(LateResultAction.REVOKE_SESSIONS, result.action());
            assertEquals("user_2EXAMPLE", result.userId().orElseThrow());
            assertEquals(review.path("assessment_id").asText(), result.event().assessment().orElseThrow().assessmentId());
            assertEquals(List.of("GET /v1/sessions", "POST /v1/sessions/sess_1/revoke", "POST /v1/sessions/sess_2/revoke"),
                    mock.lines());
            assertEquals(Map.of("user_id", "user_2EXAMPLE", "status", "active", "limit", "100", "offset", "0"),
                    mock.calls.get(0).query);
            mock.assertEveryCall("Bearer sk_test_mock");

            mock.calls.clear();
            LateResult banned = LateResults.handleLateResult(d.raw, d.headers, SECRETS, clerk,
                    opts(LateResultPolicy.fromWire(Map.of("review", "ban"))));
            assertEquals(LateResultAction.BAN, banned.action());
            assertEquals(List.of("POST /v1/users/user_2EXAMPLE/ban"), mock.lines());
        }
    }

    @Test
    void workosRevokePagesThroughTheSessionsAndBanRevokesThenDeletesTheUser() throws Exception {
        JsonNode stepUp = fixture("valid/assessment-complete.json");
        try (Mock mock = new Mock(c -> {
            if (c.method.equals("GET") && c.path.equals("/user_management/users/user_01WORKOS/sessions")) {
                return c.query.get("after") == null
                        ? new Reply(200, "{\"object\":\"list\",\"data\":[{\"id\":\"session_A\",\"status\":\"active\"},"
                                + "{\"id\":\"session_X\",\"status\":\"revoked\"}],"
                                + "\"list_metadata\":{\"before\":null,\"after\":\"session_X\"}}")
                        : new Reply(200, "{\"object\":\"list\",\"data\":[{\"id\":\"session_B\",\"status\":\"active\"}],"
                                + "\"list_metadata\":{\"before\":\"session_B\",\"after\":null}}");
            }
            if (c.method.equals("POST") && c.path.equals("/user_management/sessions/revoke")) {
                return new Reply(200, "{\"status\":\"revoked\"}");
            }
            if (c.method.equals("DELETE") && c.path.equals("/user_management/users/user_01WORKOS")) {
                return new Reply(202, null);
            }
            return null;
        })) {
            WorkOSActions workos = new WorkOSActions("sk_test_workos", mock.url(), java.net.http.HttpClient.newHttpClient());
            Delivery d = new Delivery(stepUp, "acct_7f3a9c");
            LateResultOptions options = LateResultOptions.builder().nowEpochSeconds(NOW)
                    .policy(LateResultPolicy.fromWire(Map.of("step_up", "ban")))
                    .userId((accountId, event) -> accountId.equals("acct_7f3a9c") ? "user_01WORKOS" : null)
                    .build();
            LateResult result = LateResults.handleLateResult(d.raw, d.headers, SECRETS, workos, options);
            assertEquals(LateResultAction.BAN, result.action());
            assertEquals("user_01WORKOS", result.userId().orElseThrow());
            List<String> seen = new ArrayList<>();
            for (Call c : mock.calls) {
                seen.add(c.line() + " " + (c.body != null ? c.body : c.query));
            }
            assertEquals(List.of(
                    "GET /user_management/users/user_01WORKOS/sessions {limit=100}",
                    "GET /user_management/users/user_01WORKOS/sessions {limit=100, after=session_X}",
                    "POST /user_management/sessions/revoke {\"session_id\":\"session_A\"}",
                    "POST /user_management/sessions/revoke {\"session_id\":\"session_B\"}",
                    "DELETE /user_management/users/user_01WORKOS {}"), seen);
            mock.assertEveryCall("Bearer sk_test_workos");
        }
    }

    @Test
    void auth0ReviewDeletesSessionsAndRefreshTokensBanBlocksFirstAndThePipeIsEscaped() throws Exception {
        JsonNode review = fixture("valid/assessment-phone-uncovered-complete.json");
        String id = "auth0|64abc0de1234567890abcdef";
        String escaped = "auth0%7C64abc0de1234567890abcdef";
        try (Mock mock = new Mock(c -> {
            if (c.path.equals("/api/v2/users/" + escaped + "/sessions") && c.method.equals("DELETE")) {
                return new Reply(202, null);
            }
            if (c.path.equals("/api/v2/users/" + escaped + "/refresh-tokens") && c.method.equals("DELETE")) {
                return new Reply(202, null);
            }
            if (c.path.equals("/api/v2/users/" + escaped) && c.method.equals("PATCH")) {
                return new Reply(200, "{\"user_id\":\"" + id + "\",\"blocked\":true}");
            }
            return null;
        })) {
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "mgmt_token", java.net.http.HttpClient.newHttpClient());
            Delivery d = new Delivery(review, id);
            LateResult result = LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts());
            assertEquals(LateResultAction.REVOKE_SESSIONS, result.action());
            assertEquals(List.of("DELETE /api/v2/users/" + escaped + "/sessions",
                    "DELETE /api/v2/users/" + escaped + "/refresh-tokens"), mock.lines());

            mock.calls.clear();
            LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts(LateResultPolicy.fromWire(Map.of("review", "ban"))));
            List<String> seen = new ArrayList<>();
            for (Call c : mock.calls) {
                seen.add(c.line() + " " + c.body);
            }
            assertEquals(List.of("PATCH /api/v2/users/" + escaped + " {\"blocked\":true}",
                    "DELETE /api/v2/users/" + escaped + "/sessions null",
                    "DELETE /api/v2/users/" + escaped + "/refresh-tokens null"), seen);
            mock.assertEveryCall("Bearer mgmt_token");
        }
    }

    @Test
    void unverifiedQuietOrUnmappedEventsMakeNoProviderCallAndARefusalThrows() throws Exception {
        JsonNode review = fixture("valid/assessment-phone-uncovered-complete.json");
        try (Mock mock = new Mock(c -> c.method.equals("DELETE") ? new Reply(403, "{\"error\":\"insufficient_scope\"}") : null)) {
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "mgmt_token", java.net.http.HttpClient.newHttpClient());
            Delivery d = new Delivery(review, "auth0|abc");
            VerificationException wrong = assertThrows(VerificationException.class, () -> LateResults.handleLateResult(
                    d.raw, d.headers, Map.of(KEY, "wrong"), auth0, opts()));
            assertEquals(Code.INVALID_SIGNATURE, wrong.code());
            byte[] tampered = new byte[d.raw.length + 1];
            System.arraycopy(d.raw, 0, tampered, 0, d.raw.length);
            tampered[d.raw.length] = ' ';
            assertThrows(VerificationException.class,
                    () -> LateResults.handleLateResult(tampered, d.headers, SECRETS, auth0, opts()));
            VerificationException late = assertThrows(VerificationException.class, () -> LateResults.handleLateResult(
                    d.raw, d.headers, SECRETS, auth0, LateResultOptions.builder().nowEpochSeconds(NOW + 301).build()));
            assertEquals(Code.INVALID_TIMESTAMP, late.code());
            for (JsonNode data : new JsonNode[] {fixture("valid/assessment-complete.json"),
                    fixture("valid/assessment-pending.json"), fixture("valid/webhook-assessment-expired.json").path("data")}) {
                Delivery quiet = new Delivery(data, null);
                assertEquals(LateResultAction.NONE,
                        LateResults.handleLateResult(quiet.raw, quiet.headers, SECRETS, auth0, opts()).action());
            }
            LateResultOptions unmapped = LateResultOptions.builder().nowEpochSeconds(NOW).userId((a, e) -> null).build();
            assertEquals(LateResultAction.NONE,
                    LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, unmapped).action());
            assertEquals(0, mock.calls.size());
            ProviderActionException refused = assertThrows(ProviderActionException.class,
                    () -> LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts()));
            assertEquals(403, refused.status());
            assertEquals("auth0", refused.provider());
            assertTrue(!refused.getMessage().contains("insufficient_scope") && !refused.getMessage().contains("mgmt_token"));
            assertEquals(1, mock.calls.size());
        }
    }

    @Test
    void emptyUserIdAndMissingCredentialsAreArgumentErrors() {
        Auth0Actions auth0 = new Auth0Actions(null, "http://127.0.0.1:1/api/v2", "t", null);
        assertThrows(IllegalArgumentException.class, () -> auth0.revokeSessions(""));
        assertThrows(IllegalArgumentException.class, () -> new ClerkActions(""));
        assertThrows(IllegalArgumentException.class, () -> new WorkOSActions(null));
        assertThrows(IllegalArgumentException.class, () -> new Auth0Actions(null, "token"));
    }
}
