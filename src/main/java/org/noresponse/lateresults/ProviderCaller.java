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

    ProviderCaller(String name, String baseUrl, String authorization, HttpClient client) {
        this.name = name;
        this.base = baseUrl.replaceAll("/+$", "");
        this.authorization = authorization;
        this.client = client != null ? client : HttpClient.newHttpClient();
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
        HttpResponse<String> response;
        try {
            response = client.send(request.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(name + " " + method + " " + path + " was interrupted", e);
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
