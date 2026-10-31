package org.noresponse.lateresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLSession;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.noresponse.NoResponse;
import org.noresponse.lateresults.LateResults.AccountStateAnswer;
import org.noresponse.lateresults.LateResults.ActionProvider;
import org.noresponse.lateresults.LateResults.EnforcementOptions;
import org.noresponse.lateresults.LateResults.EnforcementResult;
import org.noresponse.lateresults.LateResults.FlowPolicy;
import org.noresponse.lateresults.LateResults.IdentityProviderOptions;
import org.noresponse.lateresults.LateResults.ProviderActionException;
import org.noresponse.models.shared.Account;
import org.noresponse.utils.Blob;
import org.noresponse.utils.HTTPClient;
import org.noresponse.utils.Utils;
import org.noresponse.webhookverification.WebhookVerificationException;

/**
 * The shared late enforcement vectors through handleRiskChange, plus what the vectors cannot say in
 * JSON: verification, the custom provider and the credentials.
 */
class LateResultsTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final JsonNode VECTORS = read();
    private static final Map<String, String> SECRETS =
            Map.of(VECTORS.get("key_id").asText(), VECTORS.get("secret_utf8").asText());
    private static final Instant NOW = Instant.ofEpochSecond(VECTORS.get("now").asLong());

    private static JsonNode read() {
        try {
            return MAPPER.readTree(Files.readAllBytes(Paths.get("tests", "fixtures", "late-results-vectors.json")));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static final class Delivery {
        final byte[] raw;
        final Map<String, List<String>> headers;

        Delivery(JsonNode event) throws Exception {
            raw = MAPPER.writeValueAsBytes(event);
            String stamp = VECTORS.get("now").asText();
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(VECTORS.get("secret_utf8").asText().getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            mac.update((stamp + ".").getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : mac.doFinal(raw)) {
                hex.append(String.format("%02x", b));
            }
            headers = Map.of(
                    "NR-Webhook-Id", List.of(event.get("id").asText()),
                    "NR-Webhook-Timestamp", List.of(stamp),
                    "NR-Webhook-Key-Id", List.of(VECTORS.get("key_id").asText()),
                    "NR-Webhook-Signature", List.of("v1=" + hex));
        }
    }

    /** Method, path and query as an order-free set of decoded pairs. */
    private static String normalize(String method, URI uri) {
        String raw = uri.getRawQuery();
        String query = raw == null ? "" : Stream.of(raw.split("&"))
                .map(pair -> {
                    int eq = pair.indexOf('=');
                    String key = eq < 0 ? pair : pair.substring(0, eq);
                    String value = eq < 0 ? "" : pair.substring(eq + 1);
                    return URLDecoder.decode(key, StandardCharsets.UTF_8) + "=" + URLDecoder.decode(value, StandardCharsets.UTF_8);
                })
                .sorted()
                .collect(Collectors.joining("&"));
        return method + " " + uri.getScheme() + "://" + uri.getRawAuthority() + uri.getRawPath() + "?" + query;
    }

    private static byte[] body(HttpRequest request) {
        Optional<HttpRequest.BodyPublisher> publisher = request.bodyPublisher();
        if (publisher.isEmpty() || publisher.get().contentLength() == 0) {
            return new byte[0];
        }
        List<ByteBuffer> buffers = new ArrayList<>();
        CompletableFuture<Void> done = new CompletableFuture<>();
        publisher.get().subscribe(new Flow.Subscriber<ByteBuffer>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(ByteBuffer item) {
                buffers.add(item);
            }

            @Override
            public void onError(Throwable throwable) {
                done.completeExceptionally(throwable);
            }

            @Override
            public void onComplete() {
                done.complete(null);
            }
        });
        done.join();
        int size = buffers.stream().mapToInt(ByteBuffer::remaining).sum();
        ByteBuffer all = ByteBuffer.allocate(size);
        buffers.forEach(all::put);
        return all.array();
    }

    private static final class Answer implements HttpResponse<InputStream> {
        private final HttpRequest request;
        private final int status;
        private final HttpHeaders headers;
        private final byte[] body;

        Answer(HttpRequest request, int status, Map<String, List<String>> headers, byte[] body) {
            this.request = request;
            this.status = status;
            this.headers = HttpHeaders.of(headers, (a, b) -> true);
            this.body = body;
        }

        @Override
        public int statusCode() {
            return status;
        }

        @Override
        public HttpRequest request() {
            return request;
        }

        @Override
        public Optional<HttpResponse<InputStream>> previousResponse() {
            return Optional.empty();
        }

        @Override
        public HttpHeaders headers() {
            return headers;
        }

        @Override
        public InputStream body() {
            return new ByteArrayInputStream(body);
        }

        @Override
        public Optional<SSLSession> sslSession() {
            return Optional.empty();
        }

        @Override
        public URI uri() {
            return request.uri();
        }

        @Override
        public HttpClient.Version version() {
            return HttpClient.Version.HTTP_1_1;
        }
    }

    /** Serves the case's exchanges in order, to the NoResponse client and the provider alike, and fails on anything else. */
    private static final class Transcript implements HTTPClient {
        private final JsonNode exchanges;
        private int next;

        Transcript(JsonNode exchanges) {
            this.exchanges = exchanges;
        }

        @Override
        public HttpResponse<InputStream> send(HttpRequest request) throws IOException {
            String got = normalize(request.method(), request.uri());
            assertTrue(next < exchanges.size(), "unexpected request " + got);
            JsonNode expected = exchanges.get(next);
            JsonNode want = expected.get("request");
            assertEquals(normalize(want.get("method").asText(), URI.create(want.get("url").asText())), got, "request " + next);
            if (want.has("body")) {
                assertEquals(want.get("body"), MAPPER.readTree(body(request)), "body of request " + next);
            }
            next++;
            JsonNode response = expected.get("response");
            int status = response.get("status").asInt();
            Map<String, List<String>> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            if (response.has("headers")) {
                response.get("headers").fields().forEachRemaining(h -> headers.put(h.getKey(), List.of(h.getValue().asText())));
            }
            byte[] bytes = !response.has("body") || status == 204 ? new byte[0] : MAPPER.writeValueAsBytes(response.get("body"));
            return new Answer(request, status, headers, bytes);
        }

        @Override
        public CompletableFuture<HttpResponse<Blob>> sendAsync(HttpRequest request) {
            throw new UnsupportedOperationException("the handler is synchronous");
        }

        void done() {
            assertEquals(exchanges.size(), next, "every exchange was requested");
        }
    }

    private static NoResponse client(HTTPClient http) {
        return NoResponse.builder()
                .apiKey(VECTORS.get("api_key").asText())
                .serverURL(VECTORS.get("api_base_url").asText())
                .client(http)
                .build();
    }

    @TestFactory
    Stream<DynamicTest> vectors() {
        return StreamSupport.stream(VECTORS.get("cases").spliterator(), false)
                .map(vector -> DynamicTest.dynamicTest(vector.get("name").asText(), () -> run(vector)));
    }

    private static void run(JsonNode vector) throws Exception {
        Transcript http = new Transcript(vector.get("exchanges"));
        IdentityProviderOptions providerOptions = new IdentityProviderOptions(VECTORS.get("provider_token").asText()).http(http);
        if (vector.has("domain")) {
            providerOptions.domain(vector.get("domain").asText());
        }
        ActionProvider provider = LateResults.identityProvider(vector.get("provider").asText(), providerOptions);
        JsonNode state = vector.get("state");
        EnforcementOptions options = new EnforcementOptions(provider, account -> {
            assertEquals(vector.at("/event/data/account/id").asText(), account.id());
            if (state.isNull()) {
                return null;
            }
            return new AccountStateAnswer(
                    state.get("state").asText(),
                    state.has("user_id") ? state.get("user_id").asText() : null,
                    state.has("enforced_risk_version") ? state.get("enforced_risk_version").asLong() : null);
        }).now(NOW);
        if (vector.has("flows")) {
            vector.get("flows").fields().forEachRemaining(f -> options.flow(f.getKey(), new FlowPolicy(f.getValue().path("reinstate").asBoolean())));
        }
        Delivery delivery = new Delivery(vector.get("event"));
        NoResponse client = client(http);
        if (!vector.get("error").isNull()) {
            ProviderActionException e = assertThrows(ProviderActionException.class,
                    () -> LateResults.handleRiskChange(client, delivery.raw, delivery.headers, SECRETS, options));
            http.done();
            JsonNode error = vector.get("error");
            assertEquals(error.get("provider").asText(), e.provider());
            assertEquals(error.get("status").asInt(), e.status());
            assertEquals(Optional.of(error.get("code").asText()), e.code());
            return;
        }
        EnforcementResult result = LateResults.handleRiskChange(client, delivery.raw, delivery.headers, SECRETS, options);
        http.done();
        JsonNode skipped = vector.at("/result/skipped");
        assertEquals(skipped.isNull() ? Optional.empty() : Optional.of(skipped.asText()), result.skipped());
        List<JsonNode> got = new ArrayList<>();
        for (LateResults.Enforced enforced : result.enforcements()) {
            ObjectNode node = MAPPER.createObjectNode();
            node.put("flow", enforced.flow());
            node.put("action", enforced.action());
            node.put("status", enforced.status());
            enforced.errorCode().ifPresent(code -> node.put("error_code", code));
            got.add(node);
        }
        List<JsonNode> want = new ArrayList<>();
        vector.at("/result/enforcements").forEach(want::add);
        assertEquals(want, got);
    }

    @Test
    void aDeliveryThatFailsVerificationReadsAndActsOnNothing() throws Exception {
        Transcript http = new Transcript(MAPPER.createArrayNode());
        Delivery delivery = new Delivery(VECTORS.at("/cases/0/event"));
        byte[] tampered = (new String(delivery.raw, StandardCharsets.UTF_8) + " ").getBytes(StandardCharsets.UTF_8);
        EnforcementOptions options = new EnforcementOptions(LateResults.customProvider(Map.of()), account -> {
            fail("not called");
            return null;
        }).now(NOW);
        assertThrows(WebhookVerificationException.class,
                () -> LateResults.handleRiskChange(client(http), tampered, delivery.headers, SECRETS, options));
        http.done();
    }

    @Test
    void aCustomProviderRunsYourHandlersAndAMissingOneFailsTheAction() throws Exception {
        String api = VECTORS.get("api_base_url").asText();
        List<JsonNode> recorded = new ArrayList<>();
        HTTPClient http = new HTTPClient() {
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) throws IOException {
                assertEquals("POST " + api + "/v1/accounts/acct_7f3a9c/enforcements?", normalize(request.method(), request.uri()));
                JsonNode sent = MAPPER.readTree(body(request));
                recorded.add(sent);
                ObjectNode stored = ((ObjectNode) sent.deepCopy())
                        .put("id", "enf_01JA2ZF5N6P7Q8R9S0T1V2W3X4")
                        .put("account_id", "acct_7f3a9c")
                        .put("created_at", "2026-10-23T09:00:06.000Z");
                return new Answer(request, 201, Map.of("content-type", List.of("application/json")), MAPPER.writeValueAsBytes(stored));
            }
        };
        Account account = Utils.mapper().readValue(
                MAPPER.writeValueAsBytes(VECTORS.at("/cases/0/exchanges/0/response/body")), Account.class);
        List<String> ran = new ArrayList<>();
        ActionProvider provider = LateResults.customProvider(Map.of("restrict", userId -> {
            ran.add("restrict " + userId);
            return null;
        }));
        EnforcementOptions options = new EnforcementOptions(provider, a -> new AccountStateAnswer("active", "u_1", null));
        assertThrows(IllegalArgumentException.class, () -> LateResults.enforceAccount(client(http), account, options));
        assertEquals(List.of("restrict u_1"), ran);
        assertEquals(2, recorded.size());
        assertEquals("restrict", recorded.get(0).get("action").asText());
        assertEquals("applied", recorded.get(0).get("status").asText());
        assertTrue(recorded.get(0).path("error_code").isMissingNode());
        assertEquals("revoke_sessions", recorded.get(1).get("action").asText());
        assertEquals("failed", recorded.get(1).get("status").asText());
        assertEquals("custom.error", recorded.get(1).get("error_code").asText());
        assertEquals("custom", recorded.get(1).get("provider").asText());
    }

    @Test
    void identityProviderNeedsItsCredentials() {
        assertThrows(IllegalArgumentException.class, () -> LateResults.identityProvider("clerk", new IdentityProviderOptions("")));
        assertThrows(IllegalArgumentException.class, () -> LateResults.identityProvider("auth0", new IdentityProviderOptions("t")));
        assertThrows(IllegalArgumentException.class, () -> LateResults.identityProvider("okta", new IdentityProviderOptions("t")));
    }

    private static JsonNode vectorNamed(String name) {
        return StreamSupport.stream(VECTORS.get("cases").spliterator(), false)
                .filter(c -> c.get("name").asText().equals(name)).findFirst().orElseThrow();
    }

    @Test
    void withAccountLockCoversTheReadTheProviderChangeAndTheRecord() throws Exception {
        JsonNode vector = vectorNamed("workos_restricted_allow_reinstates_when_opted_in");
        Transcript http = new Transcript(vector.get("exchanges"));
        ActionProvider provider = LateResults.identityProvider(vector.get("provider").asText(),
                new IdentityProviderOptions(VECTORS.get("provider_token").asText()).http(http));
        List<Integer> seen = new ArrayList<>();
        EnforcementOptions options = new EnforcementOptions(provider,
                account -> new AccountStateAnswer("restricted", vector.at("/state/user_id").asText(), null))
                .now(NOW).flow("login", new FlowPolicy(true))
                .withAccountLock((accountId, enforce) -> {
                    seen.add(http.next);
                    enforce.get();
                    seen.add(http.next);
                });
        Delivery delivery = new Delivery(vector.get("event"));
        LateResults.EnforcementResult result = LateResults.handleRiskChange(client(http), delivery.raw, delivery.headers, SECRETS, options);
        http.done();
        assertEquals(List.of(0, vector.get("exchanges").size()), seen);
        assertEquals(1, result.enforcements().size());
        assertEquals("unrestrict", result.enforcements().get(0).action());
    }

    @Test
    void underWithAccountLockEnforceAccountActsOnTheCurrentRisk() throws Exception {
        JsonNode vector = vectorNamed("restricted_account_records_none");
        com.fasterxml.jackson.databind.node.ArrayNode exchanges = MAPPER.createArrayNode();
        JsonNode first = vector.get("exchanges").get(0).deepCopy();
        ((com.fasterxml.jackson.databind.node.ObjectNode) first.at("/response/body")).put("risk_version", 1);
        ((com.fasterxml.jackson.databind.node.ObjectNode) first.at("/response/body/decisions_by_flow")).put("login", "allow");
        exchanges.add(first);
        exchanges.addAll((com.fasterxml.jackson.databind.node.ArrayNode) vector.get("exchanges"));
        Transcript http = new Transcript(exchanges);
        NoResponse client = client(http);
        Account stale = client.accounts().get(vector.at("/event/data/account/id").asText()).account().orElseThrow();
        ActionProvider provider = LateResults.identityProvider(vector.get("provider").asText(),
                new IdentityProviderOptions(VECTORS.get("provider_token").asText()).http(http));
        EnforcementOptions options = new EnforcementOptions(provider, account -> new AccountStateAnswer("restricted", null, null))
                .flow("login", new FlowPolicy(true))
                .withAccountLock((accountId, enforce) -> enforce.get());
        LateResults.EnforcementResult result = LateResults.enforceAccount(client, stale, options);
        http.done();
        assertEquals("none", result.enforcements().get(0).action());
    }

    @Test
    void aWithAccountLockThatDoesNotRunEnforceFails() throws Exception {
        JsonNode vector = vectorNamed("restricted_account_records_none");
        Transcript http = new Transcript(vector.get("exchanges"));
        NoResponse client = client(http);
        Account account = client.accounts().get(vector.at("/event/data/account/id").asText()).account().orElseThrow();
        ActionProvider provider = LateResults.identityProvider(vector.get("provider").asText(),
                new IdentityProviderOptions(VECTORS.get("provider_token").asText()).http(http));
        EnforcementOptions options = new EnforcementOptions(provider, a -> null).withAccountLock((accountId, enforce) -> { });
        assertThrows(IllegalStateException.class, () -> LateResults.enforceAccount(client, account, options));
    }
}
