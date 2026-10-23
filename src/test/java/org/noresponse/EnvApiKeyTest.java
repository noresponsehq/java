package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A client built without an API key reads NORESPONSE_API_KEY. The environment
 * cannot be changed inside a running JVM, so each case runs {@link Probe} in a
 * child JVM with the environment it needs and checks the Authorization header
 * that reaches a local server.
 */
class EnvApiKeyTest {
    private static final String ENV_KEY = "nrk_env_" + "e".repeat(20);
    private static final String EXPLICIT_KEY = "nrk_explicit_" + "x".repeat(20);

    private HttpServer server;
    private final List<String> authorization = new CopyOnWriteArrayList<>();

    /** Builds a client with no key (or the key in args[1]) and makes one request to args[0]. */
    public static final class Probe {
        public static void main(String[] args) {
            NoResponse.Builder builder = NoResponse.builder().serverURL(args[0]);
            if (args.length > 1) {
                builder.apiKey(args[1]);
            }
            try {
                builder.build().assessments().get().assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM").call();
            } catch (Exception ignored) {
                // The local server answers 404; only the request it saw matters.
            }
        }
    }

    @BeforeEach
    void start() throws Exception {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            String header = exchange.getRequestHeaders().getFirst("Authorization");
            authorization.add(header == null ? "<none>" : header);
            byte[] body = "{\"type\":\"about:blank\",\"title\":\"Not Found\",\"status\":404}"
                    .getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/problem+json");
            exchange.sendResponseHeaders(404, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.start();
    }

    @AfterEach
    void stop() {
        server.stop(0);
    }

    private String seen(Map<String, String> env, String... keyArg) throws Exception {
        String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        List<String> command = new ArrayList<>(List.of(java, "-cp", System.getProperty("java.class.path"),
                Probe.class.getName(), "http://127.0.0.1:" + server.getAddress().getPort()));
        command.addAll(List.of(keyArg));
        ProcessBuilder process = new ProcessBuilder(command).redirectErrorStream(true)
                .redirectOutput(ProcessBuilder.Redirect.DISCARD);
        process.environment().remove("NORESPONSE_API_KEY");
        process.environment().putAll(env);
        Process child = process.start();
        if (!child.waitFor(60, TimeUnit.SECONDS)) {
            child.destroyForcibly();
            throw new AssertionError("probe did not finish");
        }
        assertEquals(0, child.exitValue());
        assertEquals(1, authorization.size(), authorization.toString());
        return authorization.get(0);
    }

    @Test
    void keyComesFromTheEnvironmentWhenNoneIsSet() throws Exception {
        assertEquals("Bearer " + ENV_KEY, seen(Map.of("NORESPONSE_API_KEY", ENV_KEY)));
    }

    @Test
    void anExplicitKeyWins() throws Exception {
        assertEquals("Bearer " + EXPLICIT_KEY, seen(Map.of("NORESPONSE_API_KEY", ENV_KEY), EXPLICIT_KEY));
    }

    @Test
    void noKeyAnywhereSendsNoAuthorization() throws Exception {
        assertEquals("<none>", seen(Map.of()));
    }

    @Test
    void anEmptyVariableIsIgnored() throws Exception {
        assertEquals("<none>", seen(Map.of("NORESPONSE_API_KEY", "")));
    }
}
