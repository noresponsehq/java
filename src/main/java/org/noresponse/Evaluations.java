package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.CancelPolicyEvaluationRequest;
import org.noresponse.models.operations.CancelPolicyEvaluationRequestBuilder;
import org.noresponse.models.operations.CancelPolicyEvaluationResponse;
import org.noresponse.models.operations.CreatePolicyEvaluationRequest;
import org.noresponse.models.operations.CreatePolicyEvaluationRequestBuilder;
import org.noresponse.models.operations.CreatePolicyEvaluationResponse;
import org.noresponse.models.operations.GetPolicyEvaluationRequest;
import org.noresponse.models.operations.GetPolicyEvaluationRequestBuilder;
import org.noresponse.models.operations.GetPolicyEvaluationResponse;
import org.noresponse.models.operations.GetPolicyEvaluationResultRequest;
import org.noresponse.models.operations.GetPolicyEvaluationResultRequestBuilder;
import org.noresponse.models.operations.GetPolicyEvaluationResultResponse;
import org.noresponse.models.operations.ListPolicyEvaluationsRequest;
import org.noresponse.models.operations.ListPolicyEvaluationsRequestBuilder;
import org.noresponse.models.operations.ListPolicyEvaluationsResponse;
import org.noresponse.models.operations.PublishPolicyEvaluationRequest;
import org.noresponse.models.operations.PublishPolicyEvaluationRequestBuilder;
import org.noresponse.models.operations.PublishPolicyEvaluationResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.PolicyEvaluationCancelRequest;
import org.noresponse.models.shared.PolicyEvaluationPublishRequest;
import org.noresponse.models.shared.PolicyEvaluationRequest;
import org.noresponse.operations.CancelPolicyEvaluation;
import org.noresponse.operations.CreatePolicyEvaluation;
import org.noresponse.operations.GetPolicyEvaluation;
import org.noresponse.operations.GetPolicyEvaluationResult;
import org.noresponse.operations.ListPolicyEvaluations;
import org.noresponse.operations.PublishPolicyEvaluation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Frozen policy evaluation evidence and reviewed publication.
 */
public class Evaluations {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncEvaluations asyncSDK;

    Evaluations(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncEvaluations(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncEvaluations async() {
        return asyncSDK;
    }

    /**
     * List retained policy evaluation jobs.
     *
     * <p>List retained policy evaluation jobs for this environment, newest first. At most five jobs are
     * retained for their 24-hour evidence lifetime. Use next_before as before on the next page.
     *
     * @return The call builder
     */
    public ListPolicyEvaluationsRequestBuilder list() {
        return new ListPolicyEvaluationsRequestBuilder(sdkConfiguration);
    }

    /**
     * List retained policy evaluation jobs.
     *
     * <p>List retained policy evaluation jobs for this environment, newest first. At most five jobs are
     * retained for their 24-hour evidence lifetime. Use next_before as before on the next page.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListPolicyEvaluationsResponse list(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return list(workspaceId, environment, null, null, null);
    }

    /**
     * List retained policy evaluation jobs.
     *
     * <p>List retained policy evaluation jobs for this environment, newest first. At most five jobs are
     * retained for their 24-hour evidence lifetime. Use next_before as before on the next page.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param limit
     * @param before
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListPolicyEvaluationsResponse list(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String before,
            @Nullable Options options) {
        ListPolicyEvaluationsRequest request =
                new ListPolicyEvaluationsRequest(workspaceId, environment, limit, before);
        RequestOperation<ListPolicyEvaluationsRequest, ListPolicyEvaluationsResponse> operation =
                new ListPolicyEvaluations.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Freeze an evaluation cohort and queue a bounded job.
     *
     * <p>Create a bounded evaluation with an Idempotency-Key, expected policy version, fixed candidates,
     * cohort, seed and cost assumptions. Evidence is frozen before evaluation and recorded by hashes.
     * Limits are 1000 accounts, 50000 request attempts and 64 MiB.
     *
     * <p>Capture completeness is currently unknown, so results are no_change and cannot be published. The
     * evaluation fetches no new source evidence and changes no live policy.
     *
     * @return The call builder
     */
    public CreatePolicyEvaluationRequestBuilder create() {
        return new CreatePolicyEvaluationRequestBuilder(sdkConfiguration);
    }

    /**
     * Freeze an evaluation cohort and queue a bounded job.
     *
     * <p>Create a bounded evaluation with an Idempotency-Key, expected policy version, fixed candidates,
     * cohort, seed and cost assumptions. Evidence is frozen before evaluation and recorded by hashes.
     * Limits are 1000 accounts, 50000 request attempts and 64 MiB.
     *
     * <p>Capture completeness is currently unknown, so results are no_change and cannot be published. The
     * evaluation fetches no new source evidence and changes no live policy.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreatePolicyEvaluationResponse create(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String idempotencyKey,
            @Nonnull PolicyEvaluationRequest body) {
        return create(workspaceId, environment, idempotencyKey, body, null);
    }

    /**
     * Freeze an evaluation cohort and queue a bounded job.
     *
     * <p>Create a bounded evaluation with an Idempotency-Key, expected policy version, fixed candidates,
     * cohort, seed and cost assumptions. Evidence is frozen before evaluation and recorded by hashes.
     * Limits are 1000 accounts, 50000 request attempts and 64 MiB.
     *
     * <p>Capture completeness is currently unknown, so results are no_change and cannot be published. The
     * evaluation fetches no new source evidence and changes no live policy.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreatePolicyEvaluationResponse create(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String idempotencyKey,
            @Nonnull PolicyEvaluationRequest body,
            @Nullable Options options) {
        CreatePolicyEvaluationRequest request =
                new CreatePolicyEvaluationRequest(workspaceId, environment, idempotencyKey, body);
        RequestOperation<CreatePolicyEvaluationRequest, CreatePolicyEvaluationResponse> operation =
                new CreatePolicyEvaluation.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read evaluation state and provenance.
     *
     * <p>Read a retained evaluation job and its capture and provenance metadata. Cancellation, source
     * erasure, retention reduction and expiry can make a result unavailable.
     *
     * @return The call builder
     */
    public GetPolicyEvaluationRequestBuilder get() {
        return new GetPolicyEvaluationRequestBuilder(sdkConfiguration);
    }

    /**
     * Read evaluation state and provenance.
     *
     * <p>Read a retained evaluation job and its capture and provenance metadata. Cancellation, source
     * erasure, retention reduction and expiry can make a result unavailable.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param evaluationId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPolicyEvaluationResponse get(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String evaluationId) {
        return get(workspaceId, environment, evaluationId, null);
    }

    /**
     * Read evaluation state and provenance.
     *
     * <p>Read a retained evaluation job and its capture and provenance metadata. Cancellation, source
     * erasure, retention reduction and expiry can make a result unavailable.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param evaluationId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPolicyEvaluationResponse get(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String evaluationId,
            @Nullable Options options) {
        GetPolicyEvaluationRequest request = new GetPolicyEvaluationRequest(workspaceId, environment, evaluationId);
        RequestOperation<GetPolicyEvaluationRequest, GetPolicyEvaluationResponse> operation =
                new GetPolicyEvaluation.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read the bounded completed evaluator report.
     *
     * <p>Read the completed evaluation report, including coverage, uncertainty and candidate results. Unknown
     * capture completeness produces no_change. A completed result is not an automatic policy change.
     *
     * @return The call builder
     */
    public GetPolicyEvaluationResultRequestBuilder getResult() {
        return new GetPolicyEvaluationResultRequestBuilder(sdkConfiguration);
    }

    /**
     * Read the bounded completed evaluator report.
     *
     * <p>Read the completed evaluation report, including coverage, uncertainty and candidate results. Unknown
     * capture completeness produces no_change. A completed result is not an automatic policy change.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param evaluationId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPolicyEvaluationResultResponse getResult(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String evaluationId) {
        return getResult(workspaceId, environment, evaluationId, null);
    }

    /**
     * Read the bounded completed evaluator report.
     *
     * <p>Read the completed evaluation report, including coverage, uncertainty and candidate results. Unknown
     * capture completeness produces no_change. A completed result is not an automatic policy change.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param evaluationId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPolicyEvaluationResultResponse getResult(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String evaluationId,
            @Nullable Options options) {
        GetPolicyEvaluationResultRequest request =
                new GetPolicyEvaluationResultRequest(workspaceId, environment, evaluationId);
        RequestOperation<GetPolicyEvaluationResultRequest, GetPolicyEvaluationResultResponse> operation =
                new GetPolicyEvaluationResult.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Cancel processing and destroy retained evidence and report.
     *
     * <p>Cancel a queued or running evaluation and invalidate its stored evidence. The operation does not
     * change policy. Send an empty JSON object.
     *
     * @return The call builder
     */
    public CancelPolicyEvaluationRequestBuilder cancel() {
        return new CancelPolicyEvaluationRequestBuilder(sdkConfiguration);
    }

    /**
     * Cancel processing and destroy retained evidence and report.
     *
     * <p>Cancel a queued or running evaluation and invalidate its stored evidence. The operation does not
     * change policy. Send an empty JSON object.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param evaluationId
     * @param body
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelPolicyEvaluationResponse cancel(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String evaluationId,
            @Nonnull PolicyEvaluationCancelRequest body) {
        return cancel(workspaceId, environment, evaluationId, body, null);
    }

    /**
     * Cancel processing and destroy retained evidence and report.
     *
     * <p>Cancel a queued or running evaluation and invalidate its stored evidence. The operation does not
     * change policy. Send an empty JSON object.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param evaluationId
     * @param body
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelPolicyEvaluationResponse cancel(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String evaluationId,
            @Nonnull PolicyEvaluationCancelRequest body,
            @Nullable Options options) {
        CancelPolicyEvaluationRequest request =
                new CancelPolicyEvaluationRequest(workspaceId, environment, evaluationId, body);
        RequestOperation<CancelPolicyEvaluationRequest, CancelPolicyEvaluationResponse> operation =
                new CancelPolicyEvaluation.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Publish an explicitly reviewed proposal with exact provenance.
     *
     * <p>Publish an explicitly reviewed candidate with its exact manifest, input and result hashes and
     * unchanged baseline policy version. Requires settings plus policy_publish, or legacy management.
     * Publication creates a fresh policy version and immutable provenance.
     *
     * <p>no_change, expired or invalidated results are refused.
     *
     * @return The call builder
     */
    public PublishPolicyEvaluationRequestBuilder publish() {
        return new PublishPolicyEvaluationRequestBuilder(sdkConfiguration);
    }

    /**
     * Publish an explicitly reviewed proposal with exact provenance.
     *
     * <p>Publish an explicitly reviewed candidate with its exact manifest, input and result hashes and
     * unchanged baseline policy version. Requires settings plus policy_publish, or legacy management.
     * Publication creates a fresh policy version and immutable provenance.
     *
     * <p>no_change, expired or invalidated results are refused.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param evaluationId
     * @param body
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public PublishPolicyEvaluationResponse publish(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String evaluationId,
            @Nonnull PolicyEvaluationPublishRequest body) {
        PublishPolicyEvaluationRequest request =
                new PublishPolicyEvaluationRequest(workspaceId, environment, evaluationId, body);
        RequestOperation<PublishPolicyEvaluationRequest, PublishPolicyEvaluationResponse> operation =
                new PublishPolicyEvaluation.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
