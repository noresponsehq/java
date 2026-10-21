package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.noresponse.models.errors.NoResponseException;
import org.noresponse.models.errors.NotFoundException;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.Assessment;
import org.noresponse.utils.BackoffStrategy;
import org.noresponse.utils.RetryConfig;
import org.noresponse.models.shared.AssessmentQuery;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

/** Runs the README quick start and its error handling against a local server. */
class QuickStartTest {
    private HttpServer server;
    private final List<String> idempotencyKeys = new ArrayList<>();

    private NoResponse client(int status, String contentType, byte[] payload) throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/v1/assessments", exchange -> {
            idempotencyKeys.add(exchange.getRequestHeaders().getFirst("Idempotency-Key"));
            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(status, payload.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(payload);
            }
        });
        server.start();
        return NoResponse.builder()
                .apiKey("nrk_test_" + "k".repeat(20))
                .serverURL("http://127.0.0.1:" + server.getAddress().getPort())
                .build();
    }

    @AfterEach
    void stop() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void createReadsLikeTheReadme() throws IOException {
        byte[] body = Files.readAllBytes(Paths.get("tests", "fixtures", "valid", "assessment-complete.json"));
        NoResponse client = client(200, "application/json", body);

        CreateAssessmentResponse res = client.assessments().create(
                IdempotencyKey.generate(),
                AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder().email("new.user@example.com").build())
                        .build());

        Assessment assessment = res.assessment().orElseThrow();
        assertEquals(200, res.statusCode());
        assertTrue(assessment.recommendation().isPresent());
        assertEquals(1, idempotencyKeys.size());
        assertEquals(36, idempotencyKeys.get(0).length());
    }

    @Test
    void notFoundIsATypedExceptionWithTheRequestId() throws IOException {
        String problem = "{\"type\":\"https://docs.noresponse.org/problems/not_found\",\"title\":\"Not found\","
                + "\"status\":404,\"detail\":\"No such assessment.\",\"code\":\"not_found\","
                + "\"request_id\":\"req_01JA2Z8K6Q4V7W9XBCDEFGHJKM\"}";
        NoResponse client = client(404, "application/problem+json", problem.getBytes(StandardCharsets.UTF_8));

        NoResponseException ex = assertThrows(NoResponseException.class,
                () -> client.assessments().get("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM"));

        NotFoundException notFound = (NotFoundException) ex;
        assertEquals(404, notFound.code());
        assertEquals("req_01JA2Z8K6Q4V7W9XBCDEFGHJKM", notFound.requestId().orElseThrow());
    }

    @Test
    void allAssessmentsFollowsCursorsAndStopsEarly() throws IOException {
        String item = new String(Files.readAllBytes(Paths.get("tests", "fixtures", "valid", "assessment-complete.json")),
                StandardCharsets.UTF_8);
        List<String> requests = new ArrayList<>();
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/v1/assessments/query", exchange -> {
            String request = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            requests.add(request);
            String next = request.contains("cursor-two") ? "" : ",\"next_cursor\":\"cursor-two\"";
            byte[] payload = ("{\"schema_version\":\"1\",\"account_id\":\"acct_7f3a9c\",\"items\":[" + item + "," + item
                    + "]" + next + "}").getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, payload.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(payload);
            }
        });
        server.start();
        NoResponse client = NoResponse.builder()
                .apiKey("nrk_test_" + "k".repeat(20))
                .serverURL("http://127.0.0.1:" + server.getAddress().getPort())
                .build();
        AssessmentQuery query = AssessmentQuery.builder().accountId("acct_7f3a9c").build();

        assertEquals(4, AllAssessments.stream(client, query).count());
        assertEquals(2, requests.size());

        requests.clear();
        assertEquals(1, AllAssessments.stream(client, query).limit(1).count());
        assertEquals(1, requests.size());
    }

    @Test
    void retryConfigFromTheReadmeRetriesUnlessTurnedOff() throws IOException {
        NoResponse base = client(503, "application/problem+json", "{}".getBytes(StandardCharsets.UTF_8));
        NoResponse client = NoResponse.builder()
                .apiKey("nrk_test_" + "k".repeat(20))
                .serverURL("http://127.0.0.1:" + server.getAddress().getPort())
                .retryConfig(RetryConfig.builder()
                        .backoff(BackoffStrategy.builder()
                                .initialInterval(10, TimeUnit.MILLISECONDS)
                                .maxInterval(20, TimeUnit.MILLISECONDS)
                                .maxElapsedTime(300, TimeUnit.MILLISECONDS)
                                .baseFactor(2.0)
                                .jitterFactor(0.15)
                                .retryConnectError(true)
                                .build())
                        .build())
                .build();
        String id = "asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM";

        assertThrows(NoResponseException.class, () -> client.assessments().get().assessmentId(id)
                .retryConfig(RetryConfig.noRetries()).call());
        assertEquals(1, idempotencyKeys.size());

        idempotencyKeys.clear();
        assertThrows(NoResponseException.class, () -> client.assessments().get(id));
        assertTrue(idempotencyKeys.size() > 1, "retried " + idempotencyKeys.size() + " times");
    }

    @Test
    void idempotencyKeysAreUnique() {
        assertNotEquals(IdempotencyKey.generate(), IdempotencyKey.generate());
        assertTrue(IdempotencyKey.generate().matches("[A-Za-z0-9._:-]{16,128}"));
    }
}
