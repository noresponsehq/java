package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.noresponse.models.shared.AuthContext;
import org.noresponse.utils.JSON;

class AuthTest {
    private static final Path FIXTURES = Paths.get("tests", "fixtures");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static JsonNode fixture(String name) throws IOException {
        return MAPPER.readTree(Files.readAllBytes(FIXTURES.resolve(name)));
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> map(JsonNode node) {
        return MAPPER.convertValue(node, Map.class);
    }

    private static AuthContext run(JsonNode vector) {
        String provider = vector.get("provider").asText();
        Map<String, Object> claims = map(vector.get("claims"));
        Map<String, Object> overrides = map(vector.get("overrides"));
        switch (provider) {
            case "workos":
                return Auth.fromWorkOS(claims, overrides);
            case "clerk":
                return Auth.fromClerk(claims, overrides);
            case "auth0":
                return Auth.fromAuth0(claims, overrides);
            default:
                return Auth.fromOIDC(provider, claims, overrides);
        }
    }

    @Test
    void sharedVectors() throws IOException {
        JsonNode cases = fixture("auth-context-vectors.json").get("cases");
        assertTrue(cases.size() > 0);
        for (JsonNode vector : cases) {
            String name = vector.get("name").asText();
            if (vector.has("error")) {
                assertThrows(IllegalArgumentException.class, () -> run(vector), name);
                continue;
            }
            JsonNode got = MAPPER.readTree(JSON.getMapper().writeValueAsString(run(vector)));
            assertEquals(vector.get("expect"), got, name);
        }
    }

    @Test
    void embeddedClaimMapIsTheContractMap() throws IOException {
        ObjectNode table = (ObjectNode) fixture("auth-context-claim-map.json");
        table.remove("description");
        table.remove("rules");
        table.get("providers").forEach(provider -> ((ObjectNode) provider).remove("notes"));
        assertEquals(table, Auth.claimMap());
    }
}
