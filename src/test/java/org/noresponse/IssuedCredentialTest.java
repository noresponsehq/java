package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class IssuedCredentialTest {
    @Test
    void matchesTheSharedVectors() throws IOException {
        JsonNode cases =
                new ObjectMapper()
                        .readTree(Files.readAllBytes(Paths.get("tests", "fixtures", "issued-credential-vectors.json")))
                        .get("cases");
        assertFalse(cases.isEmpty());
        for (JsonNode c : cases) {
            assertEquals(c.get("sha256").asText(), IssuedCredential.sha256(c.get("key").asText()));
        }
    }

    @Test
    void refusesAnEmptyKey() {
        assertThrows(IllegalArgumentException.class, () -> IssuedCredential.sha256(""));
    }
}
