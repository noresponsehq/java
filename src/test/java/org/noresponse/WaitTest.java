package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.Status;

class WaitTest {
    private static final String ID = "asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM";
    private HttpServer server;
    private final List<String> authorizations = new ArrayList<>();

    private static byte[] body(String name) throws IOException {
        return Files.readAllBytes(Paths.get("tests", "fixtures", "valid", name));
    }

    /** Serves the answers in order, repeating the last; each answer is {body file, Retry-After or null}. */
    private NoResponse client(String[][] answers) throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/v1/assessments/" + ID, exchange -> {
            authorizations.add(exchange.getRequestHeaders().getFirst("Authorization"));
            String[] answer = answers[Math.min(authorizations.size(), answers.length) - 1];
            byte[] payload = body(answer[0]);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            if (answer[1] != null) {
                exchange.getResponseHeaders().add("Retry-After", answer[1]);
            }
            exchange.sendResponseHeaders(200, payload.length);
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
    void pollsUntilCompleteHonouringRetryAfter() throws Exception {
        NoResponse client = client(new String[][] {
            {"assessment-pending.json", "3"}, {"assessment-pending.json", "0"}, {"assessment-complete.json", null}});
        List<Long> slept = new ArrayList<>();
        AtomicLong now = new AtomicLong();
        Assessment result = Wait.wait(client, ID, Duration.ofSeconds(30), millis -> {
            slept.add(millis);
            now.addAndGet(Duration.ofMillis(millis).toNanos());
        }, now::get);
        assertEquals(Status.COMPLETE, result.status());
        assertEquals(List.of(3000L, 1000L), slept);
        assertEquals(3, authorizations.size());
        assertEquals("Bearer nrk_test_" + "k".repeat(20), authorizations.get(0));
    }

    @Test
    void timesOutWithTheLastPendingAssessment() throws Exception {
        NoResponse client = client(new String[][] {{"assessment-pending.json", "2"}});
        List<Long> slept = new ArrayList<>();
        AtomicLong now = new AtomicLong();
        WaitTimeoutException e = assertThrows(WaitTimeoutException.class, () -> Wait.wait(client, ID,
                Duration.ofSeconds(5), millis -> {
                    slept.add(millis);
                    now.addAndGet(Duration.ofMillis(millis).toNanos());
                }, now::get));
        assertEquals(Status.PENDING, e.last().status());
        assertEquals(List.of(2000L, 2000L), slept);
    }

    @Test
    void keepsPollingAPartialAnswerUntilComplete() throws Exception {
        NoResponse client = client(new String[][] {
            {"assessment-partial.json", null}, {"assessment-partial.json", "2"}, {"assessment-complete.json", null}});
        List<Long> slept = new ArrayList<>();
        AtomicLong now = new AtomicLong();
        Assessment result = Wait.wait(client, ID, Duration.ofSeconds(30), millis -> {
            slept.add(millis);
            now.addAndGet(Duration.ofMillis(millis).toNanos());
        }, now::get);
        assertEquals(Status.COMPLETE, result.status());
        assertEquals(List.of(1000L, 2000L), slept);
        assertEquals(3, authorizations.size());
    }

    @Test
    void returnsTheLatestPartialAnswerAtTheTimeout() throws Exception {
        NoResponse client = client(new String[][] {{"assessment-pending.json", "1"}, {"assessment-partial.json", null}});
        List<Long> slept = new ArrayList<>();
        AtomicLong now = new AtomicLong();
        Assessment result = Wait.wait(client, ID, Duration.ofMillis(3500), millis -> {
            slept.add(millis);
            now.addAndGet(Duration.ofMillis(millis).toNanos());
        }, now::get);
        assertEquals(Status.PARTIAL, result.status());
        assertEquals(List.of(1000L, 1000L, 1000L), slept);
        assertEquals(4, authorizations.size());
    }
}
