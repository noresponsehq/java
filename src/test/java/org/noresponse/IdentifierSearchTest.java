package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.noresponse.models.shared.AccountDirectorySearchRequest;
import org.noresponse.models.shared.AssessmentSearchRequest;
import org.noresponse.models.shared.IdentifierMatch;
import org.noresponse.models.shared.IdentifierSearch;
import org.noresponse.utils.Utils;

/** Generated named models and HTTP methods, with deterministic transport fixtures. */
class IdentifierSearchTest {
    @Test
    void identifierRequestsAndOriginalMatchEvidenceSurviveBothOperations() throws Exception {
        var mapper = Utils.mapper();
        JsonNode revision = mapper.readTree(Files.readAllBytes(Paths.get("tests", "fixtures", "investigation-responses.json"))).get("revision");
        JsonNode identifier = mapper.readTree("{\"kind\":\"username\",\"value\":\"janedoe\",\"match\":\"canonical\",\"namespace\":\"customer\"}");
        ObjectNode matched = mapper.createObjectNode().put("kind", "username").put("value", "JaneDoe")
                .put("match_basis", "canonical").put("namespace", "customer").put("provenance", "submitted")
                .put("assessment_id", revision.path("id").asText());
        ObjectNode assessmentItem = revision.deepCopy();
        assessmentItem.set("matched_identifier", matched);
        ObjectNode accountItem = mapper.createObjectNode().put("account_id", "acct_7f3a9c").put("assessments", 2);
        accountItem.set("latest", revision);
        accountItem.set("matched_identifier", matched);
        List<JsonNode> requests = new ArrayList<>();
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/v1/", exchange -> {
            assertEquals("POST", exchange.getRequestMethod());
            assertEquals("Bearer nrk_test_identifier_fixture", exchange.getRequestHeaders().getFirst("Authorization"));
            requests.add(mapper.readTree(exchange.getRequestBody()));
            ObjectNode page = mapper.createObjectNode().put("schema_version", "1").put("total", 1).put("total_capped", false);
            if (exchange.getRequestURI().getPath().equals("/v1/assessments/search")) page.put("retention_days", 365);
            page.putArray("items").add(exchange.getRequestURI().getPath().equals("/v1/accounts/search") ? accountItem : assessmentItem);
            byte[] payload = mapper.writeValueAsBytes(page);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, payload.length);
            try (var out = exchange.getResponseBody()) { out.write(payload); }
        });
        server.start();
        try {
            NoResponse client = NoResponse.builder().apiKey("nrk_test_identifier_fixture")
                    .serverURL("http://127.0.0.1:" + server.getAddress().getPort()).build();
            IdentifierSearch query = mapper.treeToValue(identifier, IdentifierSearch.class);
            var accounts = client.accounts().search(AccountDirectorySearchRequest.builder().identifier(query).build()).accountDirectoryPage().orElseThrow();
            ObjectNode request = mapper.createObjectNode().put("from", "2026-10-01T00:00:00Z").put("to", "2026-10-02T00:00:00Z");
            request.set("identifier", identifier);
            var assessments = client.assessments().search(mapper.treeToValue(request, AssessmentSearchRequest.class)).assessmentSearchPage().orElseThrow();
            for (IdentifierMatch result : List.of(accounts.items().get(0).matchedIdentifier().orElseThrow(), assessments.items().get(0).matchedIdentifier().orElseThrow())) {
                assertEquals(matched, mapper.valueToTree(result));
                assertEquals("JaneDoe", result.value());
            }
            assertTrue(accounts.items().get(0).current().isEmpty());
            assertEquals(2, requests.size());
            for (JsonNode sent : requests) { assertEquals(identifier, sent.path("identifier")); }
        } finally { server.stop(0); }
    }
}
