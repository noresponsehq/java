package org.noresponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.operations.GetAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

/**
 * Smoke test of the client against the mock server.
 *
 * <p>Skipped unless NORESPONSE_MOCK_URL names a running mock server
 * ({@code generate_sdks.py mockserver --serve}). The mock server answers with
 * the examples in the SDK document, so this proves the client's wiring (URL,
 * auth header, request encoding, response parsing) and nothing about the live
 * API.
 */
class MockServerTest {
    private static NoResponse client() {
        String url = System.getenv("NORESPONSE_MOCK_URL");
        assumeTrue(url != null && !url.isEmpty(), "set NORESPONSE_MOCK_URL to a running mock server");
        return NoResponse.builder().apiKey("nrk_mock_" + "k".repeat(20)).serverURL(url).build();
    }

    @Test
    void createReturnsTheDocumentedAssessment() throws Exception {
        CreateAssessmentResponse res = client().assessments().create()
                .idempotencyKey("mock-server-smoke-0001")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder().email("new.user@example.com").build())
                        .build())
                .call();
        assertTrue(res.assessment().isPresent());
        assertTrue(res.assessment().get().assessmentId().startsWith("asm_"));
        assertEquals("acct_7f3a9c", res.assessment().get().accountId());
    }

    @Test
    void getReturnsTheDocumentedAssessment() throws Exception {
        GetAssessmentResponse res = client().assessments().get()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();
        assertTrue(res.assessment().isPresent());
        assertTrue(res.assessment().get().assessmentId().startsWith("asm_"));
    }
}
