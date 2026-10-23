package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.CreateAssessmentRequest;
import org.noresponse.models.operations.CreateAssessmentRequestBuilder;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.operations.DeleteAssessmentRequest;
import org.noresponse.models.operations.DeleteAssessmentRequestBuilder;
import org.noresponse.models.operations.DeleteAssessmentResponse;
import org.noresponse.models.operations.GetAssessmentRequest;
import org.noresponse.models.operations.GetAssessmentRequestBuilder;
import org.noresponse.models.operations.GetAssessmentResponse;
import org.noresponse.models.operations.QueryAccountAssessmentsRequestBuilder;
import org.noresponse.models.operations.QueryAccountAssessmentsResponse;
import org.noresponse.models.operations.RecordAssessmentOutcomeRequest;
import org.noresponse.models.operations.RecordAssessmentOutcomeRequestBuilder;
import org.noresponse.models.operations.RecordAssessmentOutcomeResponse;
import org.noresponse.models.shared.AssessmentOutcomeRequest;
import org.noresponse.models.shared.AssessmentQuery;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.CreateAssessment;
import org.noresponse.operations.DeleteAssessment;
import org.noresponse.operations.GetAssessment;
import org.noresponse.operations.QueryAccountAssessments;
import org.noresponse.operations.RecordAssessmentOutcome;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Assessments of account creation, login and purchase.
 */
public class Assessments {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncAssessments asyncSDK;

    Assessments(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncAssessments(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncAssessments async() {
        return asyncSDK;
    }

    /**
     * Assess identifiers for a signup or gateway decision.
     *
     * <p>Assesses one account creation, login or purchase (flow) and answers with the account's one current
     * risk: account.risk.decision is the decision for this flow (allow, step_up, review or block, where
     * block means your block threshold was reached), and detail holds this assessment's own facts. depth
     * sets how long the answer waits for sources: quick waits only for the fast ones (500 ms), full for
     * the whole budget (3000 ms at most); it defaults to full for account_creation and purchase and quick
     * for login. 200 carries complete or partial, 202 carries pending with Location and Retry-After.
     *
     * <p>While the account's first assessment is pending, account is absent: apply your own fallback for
     * that, 429, 503 or a timeout. NoResponse blocks nothing itself; you decide. Requires the assess
     * scope.
     *
     * <p>Idempotency-Key is required and is bound to the body for 24 hours: the same key with the same body
     * replays the latest assessment with Idempotent-Replayed: true and starts no new work; the same key
     * with a different body answers 409 idempotency_key_reused. response_mode defaults to standard;
     * detailed needs the detailed scope.
     *
     * @return The call builder
     */
    public CreateAssessmentRequestBuilder create() {
        return new CreateAssessmentRequestBuilder(sdkConfiguration);
    }

    /**
     * Assess identifiers for a signup or gateway decision.
     *
     * <p>Assesses one account creation, login or purchase (flow) and answers with the account's one current
     * risk: account.risk.decision is the decision for this flow (allow, step_up, review or block, where
     * block means your block threshold was reached), and detail holds this assessment's own facts. depth
     * sets how long the answer waits for sources: quick waits only for the fast ones (500 ms), full for
     * the whole budget (3000 ms at most); it defaults to full for account_creation and purchase and quick
     * for login. 200 carries complete or partial, 202 carries pending with Location and Retry-After.
     *
     * <p>While the account's first assessment is pending, account is absent: apply your own fallback for
     * that, 429, 503 or a timeout. NoResponse blocks nothing itself; you decide. Requires the assess
     * scope.
     *
     * <p>Idempotency-Key is required and is bound to the body for 24 hours: the same key with the same body
     * replays the latest assessment with Idempotent-Replayed: true and starts no new work; the same key
     * with a different body answers 409 idempotency_key_reused. response_mode defaults to standard;
     * detailed needs the detailed scope.
     *
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body POST /v1/assessments body. At least one of email, username or phone is required; name and ip only refine an assessment. No field carries a password, hash, token or breach value, and unknown members are refused. The email local part is kept as received and only the domain is lower-cased; the username is compared case-insensitively; the phone is strict E.164 and is assessed asynchronously. The whole body is capped at 32768 bytes. client_context holds bounded hints from the client of the user; auth_context holds normalised claims from a session your backend has verified. Raw tokens are refused.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateAssessmentResponse create(@Nonnull String idempotencyKey, @Nonnull AssessmentRequest body) {
        return create(idempotencyKey, body, null);
    }

    /**
     * Assess identifiers for a signup or gateway decision.
     *
     * <p>Assesses one account creation, login or purchase (flow) and answers with the account's one current
     * risk: account.risk.decision is the decision for this flow (allow, step_up, review or block, where
     * block means your block threshold was reached), and detail holds this assessment's own facts. depth
     * sets how long the answer waits for sources: quick waits only for the fast ones (500 ms), full for
     * the whole budget (3000 ms at most); it defaults to full for account_creation and purchase and quick
     * for login. 200 carries complete or partial, 202 carries pending with Location and Retry-After.
     *
     * <p>While the account's first assessment is pending, account is absent: apply your own fallback for
     * that, 429, 503 or a timeout. NoResponse blocks nothing itself; you decide. Requires the assess
     * scope.
     *
     * <p>Idempotency-Key is required and is bound to the body for 24 hours: the same key with the same body
     * replays the latest assessment with Idempotent-Replayed: true and starts no new work; the same key
     * with a different body answers 409 idempotency_key_reused. response_mode defaults to standard;
     * detailed needs the detailed scope.
     *
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body POST /v1/assessments body. At least one of email, username or phone is required; name and ip only refine an assessment. No field carries a password, hash, token or breach value, and unknown members are refused. The email local part is kept as received and only the domain is lower-cased; the username is compared case-insensitively; the phone is strict E.164 and is assessed asynchronously. The whole body is capped at 32768 bytes. client_context holds bounded hints from the client of the user; auth_context holds normalised claims from a session your backend has verified. Raw tokens are refused.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateAssessmentResponse create(
            @Nonnull String idempotencyKey, @Nonnull AssessmentRequest body, @Nullable Options options) {
        CreateAssessmentRequest request = new CreateAssessmentRequest(idempotencyKey, body);
        RequestOperation<CreateAssessmentRequest, CreateAssessmentResponse> operation =
                new CreateAssessment.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Poll the authoritative assessment of one assessment.
     *
     * <p>Returns the highest assessment_version, with the account's current risk. Requires the assess or
     * history scope. While status is pending, or partial with a source still running, the response carries
     * Retry-After; poll no faster than it says.
     *
     * <p>Another workspace's assessment, an unknown or expired assessment and a malformed assessment_id all
     * answer 404 not_found. response_mode=detailed needs the detailed scope.
     *
     * @return The call builder
     */
    public GetAssessmentRequestBuilder get() {
        return new GetAssessmentRequestBuilder(sdkConfiguration);
    }

    /**
     * Poll the authoritative assessment of one assessment.
     *
     * <p>Returns the highest assessment_version, with the account's current risk. Requires the assess or
     * history scope. While status is pending, or partial with a source still running, the response carries
     * Retry-After; poll no faster than it says.
     *
     * <p>Another workspace's assessment, an unknown or expired assessment and a malformed assessment_id all
     * answer 404 not_found. response_mode=detailed needs the detailed scope.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAssessmentResponse get(@Nonnull String assessmentId) {
        return get(assessmentId, null, null);
    }

    /**
     * Poll the authoritative assessment of one assessment.
     *
     * <p>Returns the highest assessment_version, with the account's current risk. Requires the assess or
     * history scope. While status is pending, or partial with a source still running, the response carries
     * Retry-After; poll no faster than it says.
     *
     * <p>Another workspace's assessment, an unknown or expired assessment and a malformed assessment_id all
     * answer 404 not_found. response_mode=detailed needs the detailed scope.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param responseMode standard returns the consolidated risk response; detailed adds merged provider-neutral facts when the key has detailed scope. There is no raw mode.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAssessmentResponse get(
            @Nonnull String assessmentId, @Nullable ResponseMode responseMode, @Nullable Options options) {
        GetAssessmentRequest request = new GetAssessmentRequest(assessmentId, responseMode);
        RequestOperation<GetAssessmentRequest, GetAssessmentResponse> operation =
                new GetAssessment.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Delete one assessment.
     *
     * <p>Deletes one assessment in the environment of your key, with its versions, evidence and outcomes and
     * the identity-graph marks its outcomes set, and scores its account again from what remains.
     * Afterwards the assessment answers 404 and leaves the account's history. The answer names the account
     * and the assessment its current risk now comes from; current_assessment_id is absent when the account
     * has no assessment with a decision left.
     *
     * <p>Requires the history scope. An unknown, expired or already deleted assessment answers 404, so a
     * repeat answers 404; to erase a whole account idempotently, use accounts.delete.
     *
     * @return The call builder
     */
    public DeleteAssessmentRequestBuilder delete() {
        return new DeleteAssessmentRequestBuilder(sdkConfiguration);
    }

    /**
     * Delete one assessment.
     *
     * <p>Deletes one assessment in the environment of your key, with its versions, evidence and outcomes and
     * the identity-graph marks its outcomes set, and scores its account again from what remains.
     * Afterwards the assessment answers 404 and leaves the account's history. The answer names the account
     * and the assessment its current risk now comes from; current_assessment_id is absent when the account
     * has no assessment with a decision left.
     *
     * <p>Requires the history scope. An unknown, expired or already deleted assessment answers 404, so a
     * repeat answers 404; to erase a whole account idempotently, use accounts.delete.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteAssessmentResponse delete(@Nonnull String assessmentId) {
        return delete(assessmentId, null);
    }

    /**
     * Delete one assessment.
     *
     * <p>Deletes one assessment in the environment of your key, with its versions, evidence and outcomes and
     * the identity-graph marks its outcomes set, and scores its account again from what remains.
     * Afterwards the assessment answers 404 and leaves the account's history. The answer names the account
     * and the assessment its current risk now comes from; current_assessment_id is absent when the account
     * has no assessment with a decision left.
     *
     * <p>Requires the history scope. An unknown, expired or already deleted assessment answers 404, so a
     * repeat answers 404; to erase a whole account idempotently, use accounts.delete.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteAssessmentResponse delete(@Nonnull String assessmentId, @Nullable Options options) {
        DeleteAssessmentRequest request = new DeleteAssessmentRequest(assessmentId);
        RequestOperation<DeleteAssessmentRequest, DeleteAssessmentResponse> operation =
                new DeleteAssessment.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Report what was later learned about an assessed account.
     *
     * <p>Records what you later learned about an assessed account: confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw your earlier report; unknown is
     * recorded only. Outcomes are append-only and the latest one wins. Requires the assess scope.
     *
     * <p>Idempotency-Key is required and handled as on POST /v1/assessments: a retry with the same key and
     * body answers the same record with Idempotent-Replayed: true; a different body answers 409. An
     * unknown, expired or foreign assessment answers 404 not_found.
     *
     * @return The call builder
     */
    public RecordAssessmentOutcomeRequestBuilder recordOutcome() {
        return new RecordAssessmentOutcomeRequestBuilder(sdkConfiguration);
    }

    /**
     * Report what was later learned about an assessed account.
     *
     * <p>Records what you later learned about an assessed account: confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw your earlier report; unknown is
     * recorded only. Outcomes are append-only and the latest one wins. Requires the assess scope.
     *
     * <p>Idempotency-Key is required and handled as on POST /v1/assessments: a retry with the same key and
     * body answers the same record with Idempotent-Replayed: true; a different body answers 409. An
     * unknown, expired or foreign assessment answers 404 not_found.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body POST /v1/assessments/{assessment_id}/outcomes body: what the provider later learned about the account it assessed. The assessment is named by the path and the workspace by the API key, so neither is a body member. Idempotency-Key is required and binds the body as it does on POST /v1/assessments.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordAssessmentOutcomeResponse recordOutcome(
            @Nonnull String assessmentId, @Nonnull String idempotencyKey, @Nonnull AssessmentOutcomeRequest body) {
        return recordOutcome(assessmentId, idempotencyKey, body, null);
    }

    /**
     * Report what was later learned about an assessed account.
     *
     * <p>Records what you later learned about an assessed account: confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw your earlier report; unknown is
     * recorded only. Outcomes are append-only and the latest one wins. Requires the assess scope.
     *
     * <p>Idempotency-Key is required and handled as on POST /v1/assessments: a retry with the same key and
     * body answers the same record with Idempotent-Replayed: true; a different body answers 409. An
     * unknown, expired or foreign assessment answers 404 not_found.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body POST /v1/assessments/{assessment_id}/outcomes body: what the provider later learned about the account it assessed. The assessment is named by the path and the workspace by the API key, so neither is a body member. Idempotency-Key is required and binds the body as it does on POST /v1/assessments.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordAssessmentOutcomeResponse recordOutcome(
            @Nonnull String assessmentId,
            @Nonnull String idempotencyKey,
            @Nonnull AssessmentOutcomeRequest body,
            @Nullable Options options) {
        RecordAssessmentOutcomeRequest request = new RecordAssessmentOutcomeRequest(assessmentId, idempotencyKey, body);
        RequestOperation<RecordAssessmentOutcomeRequest, RecordAssessmentOutcomeResponse> operation =
                new RecordAssessmentOutcome.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Page through one account's unexpired assessments.
     *
     * <p>History is a POST with account_id in the body so an account identifier stays out of URLs and access
     * logs. Requires the history scope. Read-only and safe to retry.
     *
     * <p>Newest first, each at its latest assessment_version. An account with no assessments answers 200 with
     * empty items. The cursor is opaque and bound to your workspace and the account; a tampered or expired
     * cursor answers 400 invalid_cursor.
     *
     * <p>response_mode=detailed needs the detailed scope.
     *
     * @return The call builder
     */
    public QueryAccountAssessmentsRequestBuilder query() {
        return new QueryAccountAssessmentsRequestBuilder(sdkConfiguration);
    }

    /**
     * Page through one account's unexpired assessments.
     *
     * <p>History is a POST with account_id in the body so an account identifier stays out of URLs and access
     * logs. Requires the history scope. Read-only and safe to retry.
     *
     * <p>Newest first, each at its latest assessment_version. An account with no assessments answers 200 with
     * empty items. The cursor is opaque and bound to your workspace and the account; a tampered or expired
     * cursor answers 400 invalid_cursor.
     *
     * <p>response_mode=detailed needs the detailed scope.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public QueryAccountAssessmentsResponse query(@Nonnull AssessmentQuery request) {
        return query(request, null);
    }

    /**
     * Page through one account's unexpired assessments.
     *
     * <p>History is a POST with account_id in the body so an account identifier stays out of URLs and access
     * logs. Requires the history scope. Read-only and safe to retry.
     *
     * <p>Newest first, each at its latest assessment_version. An account with no assessments answers 200 with
     * empty items. The cursor is opaque and bound to your workspace and the account; a tampered or expired
     * cursor answers 400 invalid_cursor.
     *
     * <p>response_mode=detailed needs the detailed scope.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public QueryAccountAssessmentsResponse query(@Nonnull AssessmentQuery request, @Nullable Options options) {
        RequestOperation<AssessmentQuery, QueryAccountAssessmentsResponse> operation =
                new QueryAccountAssessments.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
