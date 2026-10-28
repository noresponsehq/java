package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.BindProvisionalAccountRequest;
import org.noresponse.models.operations.CreateAssessmentRequest;
import org.noresponse.models.operations.DeleteAssessmentRequest;
import org.noresponse.models.operations.GetAssessmentRequest;
import org.noresponse.models.operations.GetAssessmentVersionRequest;
import org.noresponse.models.operations.RecordAssessmentOutcomeRequest;
import org.noresponse.models.operations.async.BindProvisionalAccountRequestBuilder;
import org.noresponse.models.operations.async.BindProvisionalAccountResponse;
import org.noresponse.models.operations.async.CreateAssessmentRequestBuilder;
import org.noresponse.models.operations.async.CreateAssessmentResponse;
import org.noresponse.models.operations.async.DeleteAssessmentRequestBuilder;
import org.noresponse.models.operations.async.DeleteAssessmentResponse;
import org.noresponse.models.operations.async.GetAssessmentRequestBuilder;
import org.noresponse.models.operations.async.GetAssessmentResponse;
import org.noresponse.models.operations.async.GetAssessmentVersionRequestBuilder;
import org.noresponse.models.operations.async.GetAssessmentVersionResponse;
import org.noresponse.models.operations.async.QueryAccountAssessmentsRequestBuilder;
import org.noresponse.models.operations.async.QueryAccountAssessmentsResponse;
import org.noresponse.models.operations.async.RecordAssessmentOutcomeRequestBuilder;
import org.noresponse.models.operations.async.RecordAssessmentOutcomeResponse;
import org.noresponse.models.operations.async.SearchAssessmentsRequestBuilder;
import org.noresponse.models.operations.async.SearchAssessmentsResponse;
import org.noresponse.models.shared.AccountBindingRequest;
import org.noresponse.models.shared.AssessmentOutcomeRequest;
import org.noresponse.models.shared.AssessmentQuery;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentSearchRequest;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.BindProvisionalAccount;
import org.noresponse.operations.CreateAssessment;
import org.noresponse.operations.DeleteAssessment;
import org.noresponse.operations.GetAssessment;
import org.noresponse.operations.GetAssessmentVersion;
import org.noresponse.operations.QueryAccountAssessments;
import org.noresponse.operations.RecordAssessmentOutcome;
import org.noresponse.operations.SearchAssessments;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Assessments of account creation, login and purchase.
 */
public class AsyncAssessments {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Assessments syncSDK;

    AsyncAssessments(Assessments syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Assessments sync() {
        return syncSDK;
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<CreateAssessmentResponse>} - The async response
     */
    public CompletableFuture<CreateAssessmentResponse> create(
            @Nonnull String idempotencyKey, @Nonnull AssessmentRequest body) {
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
     * @return {@code CompletableFuture<CreateAssessmentResponse>} - The async response
     */
    public CompletableFuture<CreateAssessmentResponse> create(
            @Nonnull String idempotencyKey, @Nonnull AssessmentRequest body, @Nullable Options options) {
        CreateAssessmentRequest request = new CreateAssessmentRequest(idempotencyKey, body);
        AsyncRequestOperation<CreateAssessmentRequest, CreateAssessmentResponse> operation =
                new CreateAssessment.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<GetAssessmentResponse>} - The async response
     */
    public CompletableFuture<GetAssessmentResponse> get(@Nonnull String assessmentId) {
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
     * @return {@code CompletableFuture<GetAssessmentResponse>} - The async response
     */
    public CompletableFuture<GetAssessmentResponse> get(
            @Nonnull String assessmentId, @Nullable ResponseMode responseMode, @Nullable Options options) {
        GetAssessmentRequest request = new GetAssessmentRequest(assessmentId, responseMode);
        AsyncRequestOperation<GetAssessmentRequest, GetAssessmentResponse> operation =
                new GetAssessment.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<DeleteAssessmentResponse>} - The async response
     */
    public CompletableFuture<DeleteAssessmentResponse> delete(@Nonnull String assessmentId) {
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
     * @return {@code CompletableFuture<DeleteAssessmentResponse>} - The async response
     */
    public CompletableFuture<DeleteAssessmentResponse> delete(@Nonnull String assessmentId, @Nullable Options options) {
        DeleteAssessmentRequest request = new DeleteAssessmentRequest(assessmentId);
        AsyncRequestOperation<DeleteAssessmentRequest, DeleteAssessmentResponse> operation =
                new DeleteAssessment.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<RecordAssessmentOutcomeResponse>} - The async response
     */
    public CompletableFuture<RecordAssessmentOutcomeResponse> recordOutcome(
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
     * @return {@code CompletableFuture<RecordAssessmentOutcomeResponse>} - The async response
     */
    public CompletableFuture<RecordAssessmentOutcomeResponse> recordOutcome(
            @Nonnull String assessmentId,
            @Nonnull String idempotencyKey,
            @Nonnull AssessmentOutcomeRequest body,
            @Nullable Options options) {
        RecordAssessmentOutcomeRequest request = new RecordAssessmentOutcomeRequest(assessmentId, idempotencyKey, body);
        AsyncRequestOperation<RecordAssessmentOutcomeRequest, RecordAssessmentOutcomeResponse> operation =
                new RecordAssessmentOutcome.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<QueryAccountAssessmentsResponse>} - The async response
     */
    public CompletableFuture<QueryAccountAssessmentsResponse> query(@Nonnull AssessmentQuery request) {
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
     * @return {@code CompletableFuture<QueryAccountAssessmentsResponse>} - The async response
     */
    public CompletableFuture<QueryAccountAssessmentsResponse> query(
            @Nonnull AssessmentQuery request, @Nullable Options options) {
        AsyncRequestOperation<AssessmentQuery, QueryAccountAssessmentsResponse> operation =
                new QueryAccountAssessments.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Search assessments across this environment
     *
     * <p>Search retained assessments created in [from,to), over at most 366 days. Filters use each assessment
     * decision, rather than the current account decision. Results can change between pages.
     *
     * <p>Keep the assessment version to retrieve the exact decision later. This read does not require an
     * idempotency key.
     *
     * @return The async call builder
     */
    public SearchAssessmentsRequestBuilder search() {
        return new SearchAssessmentsRequestBuilder(sdkConfiguration);
    }

    /**
     * Search assessments across this environment
     *
     * <p>Search retained assessments created in [from,to), over at most 366 days. Filters use each assessment
     * decision, rather than the current account decision. Results can change between pages.
     *
     * <p>Keep the assessment version to retrieve the exact decision later. This read does not require an
     * idempotency key.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<SearchAssessmentsResponse>} - The async response
     */
    public CompletableFuture<SearchAssessmentsResponse> search(@Nonnull AssessmentSearchRequest request) {
        return search(request, null);
    }

    /**
     * Search assessments across this environment
     *
     * <p>Search retained assessments created in [from,to), over at most 366 days. Filters use each assessment
     * decision, rather than the current account decision. Results can change between pages.
     *
     * <p>Keep the assessment version to retrieve the exact decision later. This read does not require an
     * idempotency key.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<SearchAssessmentsResponse>} - The async response
     */
    public CompletableFuture<SearchAssessmentsResponse> search(
            @Nonnull AssessmentSearchRequest request, @Nullable Options options) {
        AsyncRequestOperation<AssessmentSearchRequest, SearchAssessmentsResponse> operation =
                new SearchAssessments.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Retrieve an exact assessment revision
     *
     * <p>Retrieve a retained assessment version with its original decision, policy, flow and depth. This
     * response excludes the current account decision. Current retention and response-mode permissions
     * still apply.
     *
     * @return The async call builder
     */
    public GetAssessmentVersionRequestBuilder retrieveVersion() {
        return new GetAssessmentVersionRequestBuilder(sdkConfiguration);
    }

    /**
     * Retrieve an exact assessment revision
     *
     * <p>Retrieve a retained assessment version with its original decision, policy, flow and depth. This
     * response excludes the current account decision. Current retention and response-mode permissions
     * still apply.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param version The stored assessment_version, in canonical decimal form. An unavailable or malformed version answers 404.
     * @return {@code CompletableFuture<GetAssessmentVersionResponse>} - The async response
     */
    public CompletableFuture<GetAssessmentVersionResponse> retrieveVersion(@Nonnull String assessmentId, long version) {
        return retrieveVersion(assessmentId, null, version, null);
    }

    /**
     * Retrieve an exact assessment revision
     *
     * <p>Retrieve a retained assessment version with its original decision, policy, flow and depth. This
     * response excludes the current account decision. Current retention and response-mode permissions
     * still apply.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param responseMode standard returns the consolidated risk response; detailed adds merged provider-neutral facts when the key has detailed scope. There is no raw mode.
     * @param version The stored assessment_version, in canonical decimal form. An unavailable or malformed version answers 404.
     * @param options additional options
     * @return {@code CompletableFuture<GetAssessmentVersionResponse>} - The async response
     */
    public CompletableFuture<GetAssessmentVersionResponse> retrieveVersion(
            @Nonnull String assessmentId,
            @Nullable ResponseMode responseMode,
            long version,
            @Nullable Options options) {
        GetAssessmentVersionRequest request = new GetAssessmentVersionRequest(assessmentId, responseMode, version);
        AsyncRequestOperation<GetAssessmentVersionRequest, GetAssessmentVersionResponse> operation =
                new GetAssessmentVersion.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Bind a provisional signup account
     *
     * <p>One-time association to an opaque stable customer account. The assessment is its natural idempotency
     * key. Repeating the same binding returns its first receipt; a conflicting target is refused.
     *
     * <p>No arbitrary account merging.
     *
     * @return The async call builder
     */
    public BindProvisionalAccountRequestBuilder bindAccount() {
        return new BindProvisionalAccountRequestBuilder(sdkConfiguration);
    }

    /**
     * Bind a provisional signup account
     *
     * <p>One-time association to an opaque stable customer account. The assessment is its natural idempotency
     * key. Repeating the same binding returns its first receipt; a conflicting target is refused.
     *
     * <p>No arbitrary account merging.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param body One-time association to an opaque stable customer account. The assessment is its natural idempotency key. Repeating the same binding returns its first receipt; a conflicting target is refused. No arbitrary account merging.
     * @return {@code CompletableFuture<BindProvisionalAccountResponse>} - The async response
     */
    public CompletableFuture<BindProvisionalAccountResponse> bindAccount(
            @Nonnull String assessmentId, @Nonnull AccountBindingRequest body) {
        return bindAccount(assessmentId, body, null);
    }

    /**
     * Bind a provisional signup account
     *
     * <p>One-time association to an opaque stable customer account. The assessment is its natural idempotency
     * key. Repeating the same binding returns its first receipt; a conflicting target is refused.
     *
     * <p>No arbitrary account merging.
     *
     * @param assessmentId Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.
     * @param body One-time association to an opaque stable customer account. The assessment is its natural idempotency key. Repeating the same binding returns its first receipt; a conflicting target is refused. No arbitrary account merging.
     * @param options additional options
     * @return {@code CompletableFuture<BindProvisionalAccountResponse>} - The async response
     */
    public CompletableFuture<BindProvisionalAccountResponse> bindAccount(
            @Nonnull String assessmentId, @Nonnull AccountBindingRequest body, @Nullable Options options) {
        BindProvisionalAccountRequest request = new BindProvisionalAccountRequest(assessmentId, body);
        AsyncRequestOperation<BindProvisionalAccountRequest, BindProvisionalAccountResponse> operation =
                new BindProvisionalAccount.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
