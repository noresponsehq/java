package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.CancelInvestigationRequest;
import org.noresponse.models.operations.CancelInvestigationRequestBuilder;
import org.noresponse.models.operations.CancelInvestigationResponse;
import org.noresponse.models.operations.CreateInvestigationRequest;
import org.noresponse.models.operations.CreateInvestigationRequestBuilder;
import org.noresponse.models.operations.CreateInvestigationResponse;
import org.noresponse.models.operations.GetInvestigationRequest;
import org.noresponse.models.operations.GetInvestigationRequestBuilder;
import org.noresponse.models.operations.GetInvestigationResponse;
import org.noresponse.models.operations.ListInvestigationsRequest;
import org.noresponse.models.operations.ListInvestigationsRequestBuilder;
import org.noresponse.models.operations.ListInvestigationsResponse;
import org.noresponse.models.operations.ReviewInvestigationRequest;
import org.noresponse.models.operations.ReviewInvestigationRequestBuilder;
import org.noresponse.models.operations.ReviewInvestigationResponse;
import org.noresponse.models.shared.InvestigationRequest;
import org.noresponse.models.shared.PersonReviewRequest;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.operations.CancelInvestigation;
import org.noresponse.operations.CreateInvestigation;
import org.noresponse.operations.GetInvestigation;
import org.noresponse.operations.ListInvestigations;
import org.noresponse.operations.ReviewInvestigation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Person investigation jobs, retained results and contextual decisions.
 */
public class Investigations {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncInvestigations asyncSDK;

    Investigations(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncInvestigations(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncInvestigations async() {
        return asyncSDK;
    }

    /**
     * List investigation history.
     *
     * <p>List investigation history. The API key determines the workspace and environment.
     *
     * @return The call builder
     */
    public ListInvestigationsRequestBuilder list() {
        return new ListInvestigationsRequestBuilder(sdkConfiguration);
    }

    /**
     * List investigation history.
     *
     * <p>List investigation history. The API key determines the workspace and environment.
     *
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListInvestigationsResponse listDirect() {
        return list(null, null, null, null);
    }

    /**
     * List investigation history.
     *
     * <p>List investigation history. The API key determines the workspace and environment.
     *
     * @param personId
     * @param before
     * @param limit
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListInvestigationsResponse list(
            @Nullable String personId, @Nullable String before, @Nullable Long limit, @Nullable Options options) {
        ListInvestigationsRequest request = new ListInvestigationsRequest(personId, before, limit);
        RequestOperation<ListInvestigationsRequest, ListInvestigationsResponse> operation =
                new ListInvestigations.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Start an investigation for a person.
     *
     * <p>Starts an investigation against a retained workspace person, independently of group membership.
     * enrichment overrides, field by field, how the environment's enrichment setting runs after this
     * investigation; the person's latest investigation decides the enrichment that runs on them.
     * Idempotency-Key is required: the same key and input return the same retained job in its current
     * state without repeating billable work.
     *
     * <p>Different input with that key answers 409 idempotency_key_reused; a deliberate new investigation
     * uses a new key. The response includes Location and Retry-After. The person's retention expiry limits
     * both execution and retained history.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The call builder
     */
    public CreateInvestigationRequestBuilder create() {
        return new CreateInvestigationRequestBuilder(sdkConfiguration);
    }

    /**
     * Start an investigation for a person.
     *
     * <p>Starts an investigation against a retained workspace person, independently of group membership.
     * enrichment overrides, field by field, how the environment's enrichment setting runs after this
     * investigation; the person's latest investigation decides the enrichment that runs on them.
     * Idempotency-Key is required: the same key and input return the same retained job in its current
     * state without repeating billable work.
     *
     * <p>Different input with that key answers 409 idempotency_key_reused; a deliberate new investigation
     * uses a new key. The response includes Location and Retry-After. The person's retention expiry limits
     * both execution and retained history.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body Runs a new investigation against one retained workspace person.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateInvestigationResponse create(@Nonnull String idempotencyKey, @Nonnull InvestigationRequest body) {
        return create(idempotencyKey, body, null);
    }

    /**
     * Start an investigation for a person.
     *
     * <p>Starts an investigation against a retained workspace person, independently of group membership.
     * enrichment overrides, field by field, how the environment's enrichment setting runs after this
     * investigation; the person's latest investigation decides the enrichment that runs on them.
     * Idempotency-Key is required: the same key and input return the same retained job in its current
     * state without repeating billable work.
     *
     * <p>Different input with that key answers 409 idempotency_key_reused; a deliberate new investigation
     * uses a new key. The response includes Location and Retry-After. The person's retention expiry limits
     * both execution and retained history.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body Runs a new investigation against one retained workspace person.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateInvestigationResponse create(
            @Nonnull String idempotencyKey, @Nonnull InvestigationRequest body, @Nullable Options options) {
        CreateInvestigationRequest request = new CreateInvestigationRequest(idempotencyKey, body);
        RequestOperation<CreateInvestigationRequest, CreateInvestigationResponse> operation =
                new CreateInvestigation.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read an investigation and its evidence.
     *
     * <p>Returns this investigation's status, coverage, source records, posts, media and contextual review
     * history. Full evidence is returned by default; explicit evidence.read denial keeps submitted person
     * details, status, coverage and decision summaries and hides collected evidence and free-text notes.
     * The read is durably audited as person.viewed.
     *
     * <p>The API key selects the workspace and environment. Missing, foreign or expired results answer 404.
     *
     * @return The call builder
     */
    public GetInvestigationRequestBuilder get() {
        return new GetInvestigationRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an investigation and its evidence.
     *
     * <p>Returns this investigation's status, coverage, source records, posts, media and contextual review
     * history. Full evidence is returned by default; explicit evidence.read denial keeps submitted person
     * details, status, coverage and decision summaries and hides collected evidence and free-text notes.
     * The read is durably audited as person.viewed.
     *
     * <p>The API key selects the workspace and environment. Missing, foreign or expired results answer 404.
     *
     * @param investigationId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetInvestigationResponse get(@Nonnull String investigationId) {
        return get(investigationId, null);
    }

    /**
     * Read an investigation and its evidence.
     *
     * <p>Returns this investigation's status, coverage, source records, posts, media and contextual review
     * history. Full evidence is returned by default; explicit evidence.read denial keeps submitted person
     * details, status, coverage and decision summaries and hides collected evidence and free-text notes.
     * The read is durably audited as person.viewed.
     *
     * <p>The API key selects the workspace and environment. Missing, foreign or expired results answer 404.
     *
     * @param investigationId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetInvestigationResponse get(@Nonnull String investigationId, @Nullable Options options) {
        GetInvestigationRequest request = new GetInvestigationRequest(investigationId);
        RequestOperation<GetInvestigationRequest, GetInvestigationResponse> operation =
                new GetInvestigation.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Record an investigation decision.
     *
     * <p>Record an investigation decision. The API key determines the workspace and environment.
     *
     * @return The call builder
     */
    public ReviewInvestigationRequestBuilder review() {
        return new ReviewInvestigationRequestBuilder(sdkConfiguration);
    }

    /**
     * Record an investigation decision.
     *
     * <p>Record an investigation decision. The API key determines the workspace and environment.
     *
     * @param investigationId
     * @param body Records a decision on a person.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ReviewInvestigationResponse review(@Nonnull String investigationId, @Nonnull PersonReviewRequest body) {
        ReviewInvestigationRequest request = new ReviewInvestigationRequest(investigationId, body);
        RequestOperation<ReviewInvestigationRequest, ReviewInvestigationResponse> operation =
                new ReviewInvestigation.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Cancel an investigation
     *
     * <p>Stops this investigation. A completed or already cancelled investigation keeps its current result.
     * Other investigations for the person keep running.
     *
     * @return The call builder
     */
    public CancelInvestigationRequestBuilder cancel() {
        return new CancelInvestigationRequestBuilder(sdkConfiguration);
    }

    /**
     * Cancel an investigation
     *
     * <p>Stops this investigation. A completed or already cancelled investigation keeps its current result.
     * Other investigations for the person keep running.
     *
     * @param investigationId
     * @param body An empty object.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelInvestigationResponse cancel(@Nonnull String investigationId, @Nonnull ScreeningCancelRequest body) {
        return cancel(investigationId, body, null);
    }

    /**
     * Cancel an investigation
     *
     * <p>Stops this investigation. A completed or already cancelled investigation keeps its current result.
     * Other investigations for the person keep running.
     *
     * @param investigationId
     * @param body An empty object.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelInvestigationResponse cancel(
            @Nonnull String investigationId, @Nonnull ScreeningCancelRequest body, @Nullable Options options) {
        CancelInvestigationRequest request = new CancelInvestigationRequest(investigationId, body);
        RequestOperation<CancelInvestigationRequest, CancelInvestigationResponse> operation =
                new CancelInvestigation.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
