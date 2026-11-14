package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.Rating;
import org.noresponse.models.shared.Record;
import org.noresponse.models.shared.RecordField;
import org.noresponse.models.shared.RecordKind;
import org.noresponse.models.shared.RecordRole;
import org.noresponse.models.shared.Verdict;
import org.noresponse.utils.Utils;

/** A person's report returns the contract's worked records as typed models. */
class RecordsTest {
    private static Map<String, RecordField> byField(Record record) {
        return record.fields().stream().collect(Collectors.toMap(RecordField::field, Function.identity()));
    }

    @Test
    void aPersonsReportReturnsTypedRecords() throws Exception {
        byte[] body = Files.readAllBytes(Paths.get("tests", "fixtures", "valid", "person-report-records.json"));
        JsonNode report = Utils.mapper().readTree(body);
        String group = report.path("person").path("group_id").asText();
        String person = report.path("person").path("id").asText();
        String workspace = "tnt_01JC8Y0A2B4C6D8E0F2G4H6J8K";
        String[] path = new String[1];
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/v1/", exchange -> {
            path[0] = exchange.getRequestURI().getPath();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (var out = exchange.getResponseBody()) { out.write(body); }
        });
        server.start();
        try {
            NoResponse client = NoResponse.builder().apiKey("nrk_test_records_fixture")
                    .serverURL("http://127.0.0.1:" + server.getAddress().getPort()).build();
            var lookup = client.groups().getPerson(workspace, KeyEnvironment.TEST, group, person)
                    .personReport().orElseThrow().lookup().orElseThrow();
            assertEquals("/v1/workspaces/" + workspace + "/groups/test/" + group + "/people/" + person, path[0]);
            List<Record> records = lookup.records().orElseThrow();
            Record account = records.get(0);
            Record row = records.get(1);

            assertEquals("platform", account.source().kind());
            assertEquals("github.com", account.source().site().orElseThrow());
            var fields = byField(account);
            RecordField login = fields.get("login");
            assertEquals(RecordKind.USERNAME, login.kind().orElseThrow());
            assertEquals(RecordRole.HANDLE, login.role().orElseThrow());
            assertEquals("jordan-ellis", login.value().orElseThrow());
            assertEquals(Rating.DISTINCTIVE, login.distinctive().orElseThrow().rating());
            assertEquals("Jordan Ellis", fields.get("name").value().orElseThrow());
            assertTrue(fields.get("hireable").role().isEmpty(), "a field only its source returns has no role");
            assertEquals(0.74, account.likelihood().orElseThrow());
            assertEquals(List.of("name", "twitter_username"), account.agreed().orElseThrow());
            assertTrue(account.joined().orElseThrow());
            assertEquals(Verdict.JOINED, account.verdict().orElseThrow());

            assertEquals("site_breach", row.source().kind());
            assertEquals("FORUM_EXAMPLE_NET_2019", row.source().table().orElseThrow());
            assertEquals("2019-03", row.source().date().orElseThrow());
            var columns = byField(row);
            assertEquals(RecordKind.PASSWORD, columns.get("password").kind().orElseThrow());
            assertEquals("Leeds1990!", columns.get("password").value().orElseThrow());
            assertEquals("bcrypt", columns.get("password_hash").detail().orElseThrow());
            assertEquals(RecordRole.LAST_IP, columns.get("lastip").role().orElseThrow());
            assertEquals("203.0.113.5", columns.get("lastip").value().orElseThrow());
            assertTrue(columns.get("birthday").value().isEmpty(), "an ambiguous date keeps its original and no value");
            assertEquals("07/03/1990", columns.get("birthday").original());
            assertEquals(0.99, row.likelihood().orElseThrow());
            assertTrue(row.verdict().isEmpty());

            var remaining = lookup.remaining().orElseThrow().get(0);
            assertEquals("credential_list", remaining.source().kind());
            assertEquals("username", remaining.by());
            assertEquals(37L, remaining.rows());
        } finally { server.stop(0); }
    }
}
