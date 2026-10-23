package org.noresponse.lateresults;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** One provider's admin API over java.net.http. Package-private. */
final class ProviderCaller {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String name;
    private final String base;
    private final String authorization;
    private final HttpClient client;
    private final boolean retryRateLimits;
    /** Time spent waiting out 429s so far, in one action. */
    private long waitedMillis;

    /*
     * Rate limits. Auth0 answers 429 when the Management API rate limit is hit, and a 429 means it
     * did not act, so the call is safe to send again, a DELETE included. One revoke or ban waits at
     * most RATE_LIMIT_BUDGET_MILLIS in all, and each call retries at most RATE_LIMIT_RETRIES times:
     * it waits Retry-After seconds, else until X-RateLimit-Reset (Unix seconds), else 250 ms, 500
     * ms, 1 s, 2 s. A wait that would pass the budget is not started: the 429 throws at once as
     * ProviderActionException, the webhook answers 5xx and NoResponse delivers it again later. The
     * budget keeps the handler inside the 5 s a delivery has to be answered.
     */
    static final int RATE_LIMIT_RETRIES = 4;
    static final long RATE_LIMIT_BUDGET_MILLIS = 3_000;
    static final long RATE_LIMIT_BACKOFF_MILLIS = 250;

    ProviderCaller(String name, String baseUrl, String authorization, HttpClient client) {
        this(name, baseUrl, authorization, client, false);
    }

    private ProviderCaller(String name, String baseUrl, String authorization, HttpClient client, boolean retryRateLimits) {
        this.name = name;
        this.base = baseUrl.replaceAll("/+$", "");
        this.authorization = authorization;
        this.client = client != null ? client : HttpClient.newHttpClient();
        this.retryRateLimits = retryRateLimits;
    }

    /** A caller with the same settings that waits out 429s, with its own budget: make one per action. */
    ProviderCaller retryingRateLimits() {
        return new ProviderCaller(name, base, authorization, client, true);
    }

    /**
     * How long a 429 asks to wait, in milliseconds, capped at an hour so a huge header cannot overflow;
     * anything past the budget gives up anyway.
     */
    static long rateLimitWaitMillis(HttpResponse<?> response, int retry) {
        Double after = number(response.headers().firstValue("Retry-After").orElse(null));
        if (after != null && after >= 0) {
            return (long) Math.ceil(Math.min(after, 3600) * 1000);
        }
        Double reset = number(response.headers().firstValue("X-RateLimit-Reset").orElse(null));
        if (reset != null) {
            double left = reset - System.currentTimeMillis() / 1000.0;
            return (long) Math.ceil(Math.max(0, Math.min(left, 3600)) * 1000);
        }
        return RATE_LIMIT_BACKOFF_MILLIS << retry;
    }

    private static Double number(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        try {
            double value = Double.parseDouble(text.trim());
            return Double.isFinite(value) ? value : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Send one request. Returns the parsed JSON body, or null when empty, unparseable or an allowed 404. */
    JsonNode call(String method, String path, Map<String, String> query, Object body, boolean allow404) {
        StringBuilder url = new StringBuilder(base).append(path);
        if (query != null && !query.isEmpty()) {
            char sep = '?';
            for (Map.Entry<String, String> e : query.entrySet()) {
                url.append(sep).append(encode(e.getKey())).append('=').append(encode(e.getValue()));
                sep = '&';
            }
        }
        HttpRequest.Builder request = HttpRequest.newBuilder(URI.create(url.toString()))
                .timeout(Duration.ofSeconds(30))
                .header("Authorization", authorization)
                .header("Accept", "application/json");
        HttpRequest.BodyPublisher publisher = HttpRequest.BodyPublishers.noBody();
        if (body != null) {
            try {
                publisher = HttpRequest.BodyPublishers.ofString(MAPPER.writeValueAsString(body), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            request.header("Content-Type", "application/json");
        }
        request.method(method, publisher);
        HttpRequest built = request.build();
        HttpResponse<String> response;
        for (int retry = 0; ; retry++) {
            try {
                response = client.send(built, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
                if (response.statusCode() != 429 || !retryRateLimits || retry >= RATE_LIMIT_RETRIES) {
                    break;
                }
                long wait = rateLimitWaitMillis(response, retry);
                if (waitedMillis + wait > RATE_LIMIT_BUDGET_MILLIS) {
                    break;
                }
                waitedMillis += wait;
                Thread.sleep(wait);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(name + " " + method + " " + path + " was interrupted", e);
            }
        }
        int status = response.statusCode();
        if (allow404 && status == 404) {
            return null;
        }
        if (status < 200 || status > 299) {
            throw new ProviderActionException(name, status, name + " " + method + " " + path + " answered " + status,
                    errorCode(response.body()));
        }
        String text = response.body();
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            return MAPPER.readTree(text);
        } catch (IOException e) {
            return null;
        }
    }

    /** The error code in a provider's error body: Auth0's errorCode, or a top-level code; null when absent. */
    static String errorCode(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            JsonNode body = MAPPER.readTree(text);
            for (String field : new String[] {"errorCode", "code"}) {
                JsonNode code = body == null ? null : body.get(field);
                if (code != null && code.isTextual() && !code.asText().isEmpty()) {
                    return code.asText();
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    /** Percent-encode one path segment or query component; | becomes %7C and space %20. */
    static String encode(String value) {
        StringBuilder out = new StringBuilder();
        for (byte b : value.getBytes(StandardCharsets.UTF_8)) {
            char c = (char) (b & 0xff);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_'
                    || c == '.' || c == '~') {
                out.append(c);
            } else {
                out.append('%').append(String.format("%02X", b & 0xff));
            }
        }
        return out.toString();
    }

    static String segment(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("user id must be a non-empty string");
        }
        return encode(id);
    }

    /** The session ids with status active, from a bare array or a {"data": [...]} body. */
    static List<String> activeIds(JsonNode body, int[] count) {
        JsonNode list = body == null ? null : body.isArray() ? body : body.path("data");
        List<String> ids = new ArrayList<>();
        count[0] = 0;
        if (list == null || !list.isArray()) {
            return ids;
        }
        count[0] = list.size();
        for (JsonNode s : list) {
            if ("active".equals(s.path("status").asText(null)) && s.path("id").isTextual()
                    && !s.path("id").asText().isEmpty()) {
                ids.add(s.path("id").asText());
            }
        }
        return ids;
    }

    static Map<String, String> query(String... pairs) {
        Map<String, String> q = new LinkedHashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            q.put(pairs[i], pairs[i + 1]);
        }
        return q;
    }
}
