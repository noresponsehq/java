package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.noresponse.models.errors.NotFoundException;
import org.noresponse.models.shared.Account;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.AssessmentDetail;
import org.noresponse.models.shared.AssessmentQuery;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.Flow;
import org.noresponse.models.shared.Status;
import org.noresponse.webhookverification.Webhook;
import org.noresponse.webhookverification.WebhookVerificationException;

/**
 * Consumer smoke test of the client against a live API.
 *
 * <p>Skipped unless NORESPONSE_LIVE_URL and NORESPONSE_LIVE_API_KEY name an API
 * and a key with the assess and history scopes (a test key keeps it off real
 * sources). It assesses a synthetic account, replays the request, reads it
 * back, waits for it to settle, lists its history and checks the typed 404.
 * NORESPONSE_LIVE_WEBHOOK_VECTORS names deliveries the same API signed, in the
 * shape of webhook-signature-vector.json, for the verifier. Each step writes
 * its name to NORESPONSE_LIVE_REPORT, and "done" at the end, so a failure
 * names the operation (integrations/regression/suites/sdks.sh).
 */
class LiveTest {
    private static void step(String name) throws IOException {
        String report = System.getenv("NORESPONSE_LIVE_REPORT");
        if (report != null && !report.isEmpty()) {
            Files.writeString(Path.of(report), name);
        }
    }

    private static boolean hasAssessment(List<AssessmentDetail> items, String id) {
        return items.stream().anyMatch(item -> item.assessmentId().equals(id));
    }

    @Test
    void aConsumerAssessesWaitsForAndListsAnAccount() throws Exception {
        String url = System.getenv("NORESPONSE_LIVE_URL");
        String key = System.getenv("NORESPONSE_LIVE_API_KEY");
        assumeTrue(url != null && !url.isEmpty() && key != null && !key.isEmpty(),
                "set NORESPONSE_LIVE_URL and NORESPONSE_LIVE_API_KEY to run against a live API");
        NoResponse client = NoResponse.builder().apiKey(key).serverURL(url).build();
        String run = System.getenv().getOrDefault("NORESPONSE_LIVE_RUN", UUID.randomUUID().toString().substring(0, 8));
        String accountId = "sdk-live-java-" + run;
        String idempotencyKey = "sdk-live-" + UUID.randomUUID();
        AssessmentRequest request = AssessmentRequest.builder()
                .flow(Flow.ACCOUNT_CREATION)
                .accountId(accountId)
                .identifiers(AssessmentRequestIdentifiers.builder().email("sdk-live@example.com").build())
                .build();

        step("create");
        Assessment created = client.assessments().create(idempotencyKey, request).assessment().orElseThrow();
        assertTrue(created.id().startsWith("asm_"));
        assertEquals(accountId, created.detail().accountId());

        step("replay");
        assertEquals(created.id(), client.assessments().create(idempotencyKey, request).assessment().orElseThrow().id());

        step("get");
        assertEquals(created.id(), client.assessments().get(created.id()).assessment().orElseThrow().id());

        step("wait");
        Assessment settled = Wait.wait(client, created.id(), Duration.ofMinutes(1), Thread::sleep, System::nanoTime);
        assertTrue(Status.COMPLETE.equals(settled.status()) || Status.PARTIAL.equals(settled.status()), settled.status().toString());
        Account decided = settled.account().orElseThrow();

        step("query");
        List<AssessmentDetail> history = client.assessments().query()
                .request(AssessmentQuery.builder().accountId(accountId).limit(5L).build())
                .call().assessmentList().orElseThrow().items();
        assertTrue(hasAssessment(history, created.id()));
        assertFalse(history.stream().anyMatch(item -> !item.accountId().equals(accountId)));

        step("list_account_assessments");
        assertTrue(hasAssessment(client.accounts().listAssessments().accountId(accountId).limit(5L)
                .call().assessmentList().orElseThrow().items(), created.id()));

        step("get_account");
        Account account = client.accounts().get().accountId(accountId).call().account().orElseThrow();
        assertEquals(accountId, account.id());
        assertEquals(decided.risk().decision(), account.risk().decision());

        step("not_found");
        NotFoundException missing = assertThrows(NotFoundException.class,
                () -> client.assessments().get("asm_" + "0".repeat(26)));
        NotFoundException.Data problem = missing.data().orElseThrow();
        assertEquals(404L, problem.status());
        assertFalse(problem.requestId().isEmpty());

        String vectors = System.getenv("NORESPONSE_LIVE_WEBHOOK_VECTORS");
        if (vectors != null && !vectors.isEmpty()) {
            for (JsonNode vector : new ObjectMapper().readTree(Files.readAllBytes(Path.of(vectors)))) {
                String type = vector.path("type").asText();
                step("verify_webhook " + type);
                Map<String, List<String>> headers = new LinkedHashMap<>();
                vector.path("headers").fields().forEachRemaining(h -> headers.put(h.getKey(), List.of(h.getValue().asText())));
                Map<String, String> secrets = Map.of(vector.path("key_id").asText(), vector.path("secret_utf8").asText());
                Instant now = Instant.ofEpochSecond(vector.path("timestamp").asLong());
                Duration tolerance = Duration.ofSeconds(Webhook.DEFAULT_TOLERANCE_SECONDS);
                String payload = vector.path("payload").asText();
                assertEquals(type, Webhook.constructEvent(payload, headers, secrets, tolerance, now).type());
                WebhookVerificationException refused = assertThrows(WebhookVerificationException.class,
                        () -> Webhook.constructEvent(payload + " ", headers, secrets, tolerance, now));
                assertEquals(WebhookVerificationException.Code.INVALID_SIGNATURE, refused.code());
            }
        }
        step("done");
    }
}
