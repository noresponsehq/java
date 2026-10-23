package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.noresponse.utils.BackoffStrategy;
import org.noresponse.utils.Blob;
import org.noresponse.utils.HTTPClient;
import org.noresponse.utils.RetryConfig;

/**
 * The JDK client reports a connect timeout as HttpConnectTimeoutException
 * ("HTTP connect timed out"). The client must retry it like any other
 * connection error.
 */
class RetryTest {
    private static final String SECOND = "second attempt reached";

    /** Times out connecting on the first attempt and fails for good on the second. */
    private static final class TimesOutOnce implements HTTPClient {
        final AtomicInteger attempts = new AtomicInteger();

        private IOException failure() {
            return attempts.incrementAndGet() == 1
                    ? new HttpConnectTimeoutException("HTTP connect timed out")
                    : new IOException(SECOND);
        }

        @Override
        public HttpResponse<InputStream> send(HttpRequest request) throws IOException {
            throw failure();
        }

        @Override
        public CompletableFuture<HttpResponse<Blob>> sendAsync(HttpRequest request) {
            CompletableFuture<HttpResponse<Blob>> future = new CompletableFuture<>();
            future.completeExceptionally(failure());
            return future;
        }
    }

    private static RetryConfig fastRetries() {
        return RetryConfig.builder()
                .backoff(BackoffStrategy.builder()
                        .initialInterval(1, TimeUnit.MILLISECONDS)
                        .maxInterval(5, TimeUnit.MILLISECONDS)
                        .baseFactor(1.5)
                        .maxElapsedTime(5, TimeUnit.SECONDS)
                        .retryConnectError(true)
                        .build())
                .build();
    }

    private static NoResponse client(HTTPClient http) {
        return NoResponse.builder()
                .apiKey("nrk_test_" + "k".repeat(20))
                .serverURL("http://127.0.0.1:9")
                .client(http)
                .retryConfig(fastRetries())
                .build();
    }

    private static boolean causedBy(Throwable e, String message) {
        for (Throwable t = e; t != null; t = t.getCause()) {
            if (message.equals(t.getMessage())) {
                return true;
            }
        }
        return false;
    }

    @Test
    void connectTimeoutIsRetried() {
        TimesOutOnce http = new TimesOutOnce();
        Exception e = assertThrows(Exception.class,
                () -> client(http).assessments().get().assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM").call());
        assertEquals(2, http.attempts.get());
        assertTrue(causedBy(e, SECOND), e.toString());
    }

    @Test
    void connectTimeoutIsRetriedAsync() {
        TimesOutOnce http = new TimesOutOnce();
        ExecutionException e = assertThrows(ExecutionException.class,
                () -> client(http).async().assessments().get().assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                        .call().get(10, TimeUnit.SECONDS));
        assertEquals(2, http.attempts.get());
        assertTrue(causedBy(e, SECOND), e.toString());
    }
}
