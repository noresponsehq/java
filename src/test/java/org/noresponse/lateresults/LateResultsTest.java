package org.noresponse.lateresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    /** A complete assessment whose account decision is the one given. */
    private static ObjectNode view(String decision) throws IOException {
        return with(fixture("valid/assessment-view-complete.json"), "complete", decision);
    }

    /** A signed assessment.updated delivery, optionally overriding detail.account_id. */
    private static final class Delivery {
        final byte[] raw;
        final Map<String, List<String>> headers;

        Delivery(JsonNode data, String accountId) throws Exception {
            this(data, accountId, false);
        }

        /** With test true, the body carries "test": true as a send-test-event delivery does. */
        Delivery(JsonNode data, String accountId, boolean test) throws Exception {
            ObjectNode payload = data.deepCopy();
            if (accountId != null) {
                ((ObjectNode) payload.get("detail")).put("account_id", accountId);
            }
            ObjectNode body = MAPPER.createObjectNode();
            body.put("id", "evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ2");
            body.put("type", "assessment.updated");
            body.put("created_at", "2026-10-18T11:00:04Z");
            if (test) {
                body.put("test", true);
            }
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
        final Map<String, String> headers;

        Reply(int status, String body) {
            this(status, body, Map.of());
        }

        Reply(int status, String body, Map<String, String> headers) {
            this.status = status;
            this.body = body;
            this.headers = headers;
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
                reply.headers.forEach((k, v) -> exchange.getResponseHeaders().add(k, v));
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

    /** A copy with this status and account decision; a null decision removes the account. */
    private static ObjectNode with(JsonNode data, String status, String decision) {
        ObjectNode copy = data.deepCopy();
        copy.put("status", status);
        ((ObjectNode) copy.get("detail")).put("status", status);
        if (decision == null) {
            copy.remove("account");
        } else {
            ((ObjectNode) copy.get("account").get("risk")).put("decision", decision);
        }
        return copy;
    }

    @Test
    void defaultPolicyRevokesOnReviewOrBlockAndDoesNothingOnAllowStepUpPendingOrExpiry() throws Exception {
        JsonNode review = view("review");
        JsonNode stepUp = view("step_up");
        JsonNode pending = fixture("valid/assessment-view-pending.json");
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
                "review", LateResultAction.REVOKE_SESSIONS, "block", LateResultAction.REVOKE_SESSIONS),
                LateResultPolicy.DEFAULT.asMap());
        assertEquals("revoke_sessions", LateResultAction.REVOKE_SESSIONS.wire());

        // block is the account decision; the assessment's own recommendation is never read.
        JsonNode block = fixture("valid/assessment-view-block.json");
        assertEquals("review", block.path("detail").path("recommendation").asText());
        assertEquals(LateResultAction.REVOKE_SESSIONS, LateResults.lateResultAction(event(block)));
        assertEquals(LateResultAction.BAN,
                LateResults.lateResultAction(event(block), LateResultPolicy.fromWire(Map.of("block", "ban"))));
        // A pending assessment never acts, even when it carries an earlier decided account.
        assertEquals(LateResultAction.NONE, LateResults.lateResultAction(
                event(fixture("valid/assessment-view-pending-known-account.json")),
                LateResultPolicy.fromWire(Map.of("allow", "ban"))));
        assertEquals(LateResultAction.NONE, LateResults.lateResultAction(
                event(with(fixture("valid/assessment-view-pending-known-account.json"), "pending", "review"))));
    }

    @Test
    void clerkReviewRevokesEachActiveSessionAndABanPolicyBansTheUser() throws Exception {
        JsonNode review = view("review");
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
            assertEquals(review.path("id").asText(), result.event().assessment().orElseThrow().id());
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
        JsonNode stepUp = view("step_up");
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
        JsonNode review = view("review");
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
        JsonNode review = view("review");
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
            for (JsonNode data : new JsonNode[] {view("step_up"),
                    fixture("valid/assessment-view-pending.json"), fixture("valid/webhook-assessment-expired.json").path("data")}) {
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
    void aTestEventIsFlaggedAndMakesNoProviderCallEvenUnderABanPolicyWithAResolver() throws Exception {
        JsonNode sample = fixture("valid/webhook-assessment-updated-test.json").path("data");
        try (Mock mock = new Mock(c -> new Reply(200, "[]"))) {
            ClerkActions clerk = new ClerkActions("sk_test_mock", mock.url() + "/v1", java.net.http.HttpClient.newHttpClient());
            Delivery d = new Delivery(sample, null, true);
            LateResultOptions options = LateResultOptions.builder().nowEpochSeconds(NOW)
                    .policy(LateResultPolicy.fromWire(Map.of("step_up", "ban", "review", "ban", "allow", "ban")))
                    .userId((accountId, event) -> "user_2EXAMPLE")
                    .build();
            LateResult result = LateResults.handleLateResult(d.raw, d.headers, SECRETS, clerk, options);
            assertEquals(LateResultAction.NONE, result.action());
            assertTrue(result.event().isTest());
            assertTrue(result.userId().isEmpty());
            assertEquals(0, mock.calls.size());

            // The same body without the flag would ban, and an ordinary delivery is not a test.
            Delivery real = new Delivery(sample, null, false);
            assertFalse(Webhook.constructEvent(real.raw, real.headers, SECRETS, 300, NOW).isTest());
            assertEquals(LateResultAction.BAN, LateResults.handleLateResult(real.raw, real.headers, SECRETS, clerk, options).action());
        }
    }

    @Test
    void aRepeatedBanAfterTheUserIsGoneCompletesAndA500StillThrows() throws Exception {
        JsonNode review = view("review");
        LateResultOptions ban = opts(LateResultPolicy.fromWire(Map.of("review", "ban")));
        LateResultOptions revoke = opts();
        // Every user-scoped endpoint answers 404: the user was deleted by the first delivery.
        try (Mock mock = new Mock(c -> new Reply(404, "{\"message\":\"not found\"}"))) {
            WorkOSActions workos = new WorkOSActions("sk_test_workos", mock.url(), java.net.http.HttpClient.newHttpClient());
            ClerkActions clerk = new ClerkActions("sk_test_mock", mock.url() + "/v1", java.net.http.HttpClient.newHttpClient());
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "mgmt_token", java.net.http.HttpClient.newHttpClient());
            Delivery d = new Delivery(review, "user_GONE");
            for (int i = 0; i < 2; i++) {
                assertEquals(LateResultAction.BAN, LateResults.handleLateResult(d.raw, d.headers, SECRETS, workos, ban).action());
                assertEquals(LateResultAction.BAN, LateResults.handleLateResult(d.raw, d.headers, SECRETS, clerk, ban).action());
                assertEquals(LateResultAction.BAN, LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, ban).action());
                assertEquals(LateResultAction.REVOKE_SESSIONS,
                        LateResults.handleLateResult(d.raw, d.headers, SECRETS, clerk, revoke).action());
            }
            assertTrue(mock.lines().contains("GET /user_management/users/user_GONE/sessions"));
            assertTrue(mock.lines().contains("DELETE /user_management/users/user_GONE"));
            assertTrue(mock.lines().contains("POST /v1/users/user_GONE/ban"));
            assertTrue(mock.lines().contains("PATCH /api/v2/users/user_GONE"));
            assertTrue(mock.lines().contains("DELETE /api/v2/users/user_GONE/refresh-tokens"));
        }
        // A session that vanished between the list and its revoke also counts as done.
        try (Mock mock = new Mock(c -> c.method.equals("GET")
                ? new Reply(200, "[{\"id\":\"sess_1\",\"status\":\"active\"}]") : new Reply(404, null))) {
            ClerkActions clerk = new ClerkActions("sk_test_mock", mock.url() + "/v1", java.net.http.HttpClient.newHttpClient());
            clerk.revokeSessions("user_2EXAMPLE");
            assertEquals(List.of("GET /v1/sessions", "POST /v1/sessions/sess_1/revoke"), mock.lines());
        }
        try (Mock mock = new Mock(c -> new Reply(500, "{\"error\":\"boom\"}"))) {
            WorkOSActions workos = new WorkOSActions("sk_test_workos", mock.url(), java.net.http.HttpClient.newHttpClient());
            ClerkActions clerk = new ClerkActions("sk_test_mock", mock.url() + "/v1", java.net.http.HttpClient.newHttpClient());
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "mgmt_token", java.net.http.HttpClient.newHttpClient());
            for (LateResultProvider provider : new LateResultProvider[] {workos, clerk, auth0}) {
                ProviderActionException failed = assertThrows(ProviderActionException.class, () -> provider.ban("user_X"));
                assertEquals(500, failed.status());
            }
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

    @Test
    void logOnlyActionsLogWhatTheyWouldRevokeOrBanAndCallNothing() throws Exception {
        JsonNode review = view("review");
        List<String> lines = new ArrayList<>();
        LogOnlyActions logOnly = new LogOnlyActions(lines::add);
        assertEquals("log_only", logOnly.name());
        Delivery d = new Delivery(review, "usr_8f2k1");
        LateResult revoked = LateResults.handleLateResult(d.raw, d.headers, SECRETS, logOnly, opts());
        assertEquals(LateResultAction.REVOKE_SESSIONS, revoked.action());
        assertEquals("usr_8f2k1", revoked.userId().orElseThrow());
        LateResult banned = LateResults.handleLateResult(d.raw, d.headers, SECRETS, logOnly,
                opts(LateResultPolicy.fromWire(Map.of("review", "ban"))));
        assertEquals(LateResultAction.BAN, banned.action());
        assertEquals(List.of(
                "NoResponse late result (log only): would revoke every session of user usr_8f2k1",
                "NoResponse late result (log only): would ban user usr_8f2k1 and end their sessions"), lines);
        Delivery test = new Delivery(review, "usr_8f2k1", true);
        assertEquals(LateResultAction.NONE, LateResults.handleLateResult(test.raw, test.headers, SECRETS, logOnly, opts()).action());
        assertEquals(2, lines.size());
        assertThrows(IllegalArgumentException.class, () -> logOnly.ban(""));
        new LogOnlyActions().revokeSessions("usr_8f2k1");
    }

    private static final String FEATURE_NOT_ENABLED = "{\"statusCode\":403,\"error\":\"Forbidden\","
            + "\"message\":\"This feature is not enabled for this tenant.\",\"errorCode\":\"feature_not_enabled\"}";

    /**
     * An Auth0 tenant on a plan without the sessions API, as a Free tenant answers: both bulk endpoints
     * refuse with 403 feature_not_enabled, while /device-credentials and PATCH /users/{id} work. It keeps
     * the refresh tokens and the blocked flag, so a repeated delivery sees what the first left.
     */
    private static final class Auth0WithoutSessionsApi implements Function<Call, Reply> {
        final String escaped;
        final boolean refreshTokensApi;
        volatile boolean blocked;
        final List<String[]> credentials = Collections.synchronizedList(new ArrayList<>(List.of(
                new String[] {"dcr_a", "refresh_token"}, new String[] {"dcr_b", "refresh_token"},
                new String[] {"dcr_r", "rotating_refresh_token"})));

        Auth0WithoutSessionsApi(String escaped, boolean refreshTokensApi) {
            this.escaped = escaped;
            this.refreshTokensApi = refreshTokensApi;
        }

        @Override
        public synchronized Reply apply(Call c) {
            String user = "/api/v2/users/" + escaped;
            if (c.method.equals("DELETE") && c.path.equals(user + "/sessions")) {
                return new Reply(403, FEATURE_NOT_ENABLED);
            }
            if (c.method.equals("DELETE") && c.path.equals(user + "/refresh-tokens")) {
                if (!refreshTokensApi) {
                    return new Reply(403, FEATURE_NOT_ENABLED);
                }
                credentials.clear();
                return new Reply(202, null);
            }
            if (c.method.equals("PATCH") && c.path.equals(user)) {
                blocked = c.body.contains("\"blocked\":true");
                return new Reply(200, "{\"blocked\":" + blocked + "}");
            }
            if (c.method.equals("GET") && c.path.equals("/api/v2/device-credentials")) {
                StringBuilder out = new StringBuilder("[");
                for (String[] d : new ArrayList<>(credentials)) {
                    if (d[1].equals(c.query.get("type"))) {
                        out.append(out.length() > 1 ? "," : "").append("{\"id\":\"").append(d[0]).append("\",\"type\":\"")
                                .append(d[1]).append("\"}");
                    }
                }
                return new Reply(200, out.append("]").toString());
            }
            if (c.method.equals("DELETE") && c.path.startsWith("/api/v2/device-credentials/")) {
                String id = c.path.substring("/api/v2/device-credentials/".length());
                return credentials.removeIf(d -> d[0].equals(id)) ? new Reply(204, null)
                        : new Reply(404, "{\"errorCode\":\"inexistent_device_credential\"}");
            }
            return null;
        }
    }

    @Test
    void auth0WithoutTheSessionsApiBlocksDeletesDeviceCredentialsAndReportsBan() throws Exception {
        JsonNode review = view("review");
        String id = "auth0|64abc0de1234567890abcdef";
        String escaped = "auth0%7C64abc0de1234567890abcdef";
        Auth0WithoutSessionsApi tenant = new Auth0WithoutSessionsApi(escaped, false);
        try (Mock mock = new Mock(tenant)) {
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "mgmt_token", null);
            Delivery d = new Delivery(review, id);
            LateResult result = LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts());
            assertEquals(LateResultAction.BAN, result.action());
            assertEquals(id, result.userId().orElseThrow());
            assertTrue(tenant.blocked);
            assertTrue(tenant.credentials.isEmpty());
            assertEquals(List.of("DELETE /api/v2/users/" + escaped + "/sessions",
                    "PATCH /api/v2/users/" + escaped,
                    "DELETE /api/v2/users/" + escaped + "/refresh-tokens",
                    "GET /api/v2/device-credentials",
                    "GET /api/v2/device-credentials",
                    "DELETE /api/v2/device-credentials/dcr_a",
                    "DELETE /api/v2/device-credentials/dcr_b",
                    "DELETE /api/v2/device-credentials/dcr_r"), mock.lines());
            assertEquals("refresh_token", mock.calls.get(3).query.get("type"));
            assertEquals("rotating_refresh_token", mock.calls.get(4).query.get("type"));
            assertEquals(escaped, mock.calls.get(3).query.get("user_id"));
            mock.assertEveryCall("Bearer mgmt_token");

            // The same delivery again: still blocked, nothing left to delete, same report.
            mock.calls.clear();
            assertEquals(LateResultAction.BAN, LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts()).action());
            assertTrue(tenant.blocked);
            assertFalse(mock.lines().stream().anyMatch(l -> l.startsWith("DELETE /api/v2/device-credentials/")));

            // ban blocks first, and the plan refusing the sessions endpoint is no failure.
            tenant.blocked = false;
            tenant.credentials.add(new String[] {"dcr_c", "refresh_token"});
            mock.calls.clear();
            LateResult banned = LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0,
                    opts(LateResultPolicy.fromWire(Map.of("review", "ban"))));
            assertEquals(LateResultAction.BAN, banned.action());
            assertEquals("PATCH", mock.calls.get(0).method);
            assertTrue(tenant.blocked);
            assertTrue(tenant.credentials.isEmpty());

            // Called directly, revokeSessions takes the same fallback.
            tenant.blocked = false;
            auth0.revokeSessions(id);
            assertTrue(tenant.blocked);
        }
    }

    @Test
    void auth0WithRefreshTokensButNoSessionsApiStillBlocksAndAScopeRefusalNeverFallsBack() throws Exception {
        JsonNode review = view("review");
        Delivery d = new Delivery(review, "auth0|abc");
        Auth0WithoutSessionsApi tenant = new Auth0WithoutSessionsApi("auth0%7Cabc", true);
        try (Mock mock = new Mock(tenant)) {
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "t", null);
            assertEquals(LateResultAction.BAN, LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts()).action());
            assertTrue(tenant.blocked);
            assertFalse(mock.lines().contains("GET /api/v2/device-credentials"));
        }
        String scope = "{\"statusCode\":403,\"error\":\"Forbidden\",\"message\":\"Insufficient scope\",\"errorCode\":\"insufficient_scope\"}";
        try (Mock scoped = new Mock(c -> c.method.equals("DELETE") ? new Reply(403, scope) : new Reply(200, "{}"))) {
            Auth0Actions auth0 = new Auth0Actions(null, scoped.url() + "/api/v2", "t", null);
            ProviderActionException refused = assertThrows(ProviderActionException.class,
                    () -> LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts()));
            assertEquals(403, refused.status());
            assertEquals("insufficient_scope", refused.code());
            assertEquals(List.of("DELETE /api/v2/users/auth0%7Cabc/sessions"), scoped.lines());
        }
    }

    private static final String TOO_MANY = "{\"statusCode\":429,\"error\":\"Too Many Requests\","
            + "\"message\":\"Global limit has been reached\",\"errorCode\":\"too_many_requests\"}";

    @Test
    void auth0WaitsOutA429FromTheManagementApiAndRetriesTheCall() throws Exception {
        Auth0WithoutSessionsApi tenant = new Auth0WithoutSessionsApi("auth0%7Cabc", false);
        // One 429 each: Retry-After, an X-RateLimit-Reset already past, and no header (the backoff).
        Map<String, Map<String, String>> limited = Collections.synchronizedMap(new LinkedHashMap<>());
        limited.put("GET refresh_token", Map.of("Retry-After", "0"));
        limited.put("DELETE /api/v2/device-credentials/dcr_a",
                Map.of("X-RateLimit-Reset", Long.toString(System.currentTimeMillis() / 1000 - 5)));
        limited.put("DELETE /api/v2/device-credentials/dcr_b", Map.of());
        Function<Call, Reply> route = c -> {
            String key = c.method.equals("GET") ? "GET " + c.query.get("type") : c.line();
            Map<String, String> headers = limited.remove(key);
            return headers != null ? new Reply(429, TOO_MANY, headers) : tenant.apply(c);
        };
        try (Mock mock = new Mock(route)) {
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "mgmt_token", null);
            Delivery d = new Delivery(view("review"), "auth0|abc");
            assertEquals(LateResultAction.BAN, LateResults.handleLateResult(d.raw, d.headers, SECRETS, auth0, opts()).action());
            assertTrue(tenant.blocked);
            assertTrue(tenant.credentials.isEmpty());
            assertTrue(limited.isEmpty());
            List<String> lines = mock.lines();
            assertEquals(3, Collections.frequency(lines, "GET /api/v2/device-credentials"));
            assertEquals(2, Collections.frequency(lines, "DELETE /api/v2/device-credentials/dcr_a"));
            assertEquals(2, Collections.frequency(lines, "DELETE /api/v2/device-credentials/dcr_b"));
        }
    }

    @Test
    void auth0ThrowsA429AtOnceWhenTheWaitItAsksForPassesTheBudget() throws Exception {
        try (Mock mock = new Mock(c -> new Reply(429, TOO_MANY, Map.of("Retry-After", "60")))) {
            Auth0Actions auth0 = new Auth0Actions(null, mock.url() + "/api/v2", "mgmt_token", null);
            long started = System.nanoTime();
            ProviderActionException limited = assertThrows(ProviderActionException.class,
                    () -> auth0.act(LateResultAction.REVOKE_SESSIONS, "auth0|abc"));
            assertEquals(429, limited.status());
            assertEquals("too_many_requests", limited.code());
            assertTrue(System.nanoTime() - started < 1_000_000_000L);
            assertEquals(1, mock.calls.size());
        }
    }

    @Test
    void aProviderWithoutItsOwnActReportsTheActionAskedFor() throws Exception {
        List<String> done = new ArrayList<>();
        LateResultProvider plain = new LateResultProvider() {
            @Override
            public String name() {
                return "plain";
            }

            @Override
            public void revokeSessions(String userId) {
                done.add("revoke " + userId);
            }

            @Override
            public void ban(String userId) {
                done.add("ban " + userId);
            }
        };
        Delivery d = new Delivery(view("review"), "u_1");
        assertEquals(LateResultAction.REVOKE_SESSIONS, LateResults.handleLateResult(d.raw, d.headers, SECRETS, plain, opts()).action());
        assertEquals(LateResultAction.BAN, LateResults.handleLateResult(d.raw, d.headers, SECRETS, plain,
                opts(LateResultPolicy.fromWire(Map.of("review", "ban")))).action());
        assertEquals(List.of("revoke u_1", "ban u_1"), done);
    }
}
