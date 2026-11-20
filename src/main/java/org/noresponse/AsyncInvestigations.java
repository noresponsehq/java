package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.CancelInvestigationRequest;
import org.noresponse.models.operations.CreateInvestigationRequest;
import org.noresponse.models.operations.GetInvestigationRequest;
import org.noresponse.models.operations.ListInvestigationsRequest;
import org.noresponse.models.operations.async.CancelInvestigationRequestBuilder;
import org.noresponse.models.operations.async.CancelInvestigationResponse;
import org.noresponse.models.operations.async.CreateInvestigationRequestBuilder;
import org.noresponse.models.operations.async.CreateInvestigationResponse;
import org.noresponse.models.operations.async.GetInvestigationRequestBuilder;
import org.noresponse.models.operations.async.GetInvestigationResponse;
import org.noresponse.models.operations.async.ListInvestigationsRequestBuilder;
import org.noresponse.models.operations.async.ListInvestigationsResponse;
import org.noresponse.models.shared.InvestigationRequest;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.operations.CancelInvestigation;
import org.noresponse.operations.CreateInvestigation;
import org.noresponse.operations.GetInvestigation;
import org.noresponse.operations.ListInvestigations;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Person investigation jobs and retained results.
 */
public class AsyncInvestigations {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Investigations syncSDK;

    AsyncInvestigations(Investigations syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Investigations sync() {
        return syncSDK;
    }

    /**
     * List investigation history.
     *
     * <p>List investigation history. The API key determines the workspace and environment.
     *
     * @return The async call builder
     */
    public ListInvestigationsRequestBuilder list() {
        return new ListInvestigationsRequestBuilder(sdkConfiguration);
    }

    /**
     * List investigation history.
     *
     * <p>List investigation history. The API key determines the workspace and environment.
     *
     * @return {@code CompletableFuture<ListInvestigationsResponse>} - The async response
     */
    public CompletableFuture<ListInvestigationsResponse> listDirect() {
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
     * @return {@code CompletableFuture<ListInvestigationsResponse>} - The async response
     */
    public CompletableFuture<ListInvestigationsResponse> list(
            @Nullable String personId, @Nullable String before, @Nullable Long limit, @Nullable Options options) {
        ListInvestigationsRequest request = new ListInvestigationsRequest(personId, before, limit);
        AsyncRequestOperation<ListInvestigationsRequest, ListInvestigationsResponse> operation =
                new ListInvestigations.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<CreateInvestigationResponse>} - The async response
     */
    public CompletableFuture<CreateInvestigationResponse> create(
            @Nonnull String idempotencyKey, @Nonnull InvestigationRequest body) {
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
     * @return {@code CompletableFuture<CreateInvestigationResponse>} - The async response
     */
    public CompletableFuture<CreateInvestigationResponse> create(
            @Nonnull String idempotencyKey, @Nonnull InvestigationRequest body, @Nullable Options options) {
        CreateInvestigationRequest request = new CreateInvestigationRequest(idempotencyKey, body);
        AsyncRequestOperation<CreateInvestigationRequest, CreateInvestigationResponse> operation =
                new CreateInvestigation.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read an investigation and its evidence.
     *
     * <p>Returns this investigation's status, coverage, source records, posts and media. Full evidence is
     * returned by default; explicit evidence.read denial keeps submitted person details, status and
     * coverage, and hides collected evidence. The read is durably audited as person.viewed.
     *
     * <p>The API key selects the workspace and environment. Missing, foreign or expired results answer 404.
     *
     * @return The async call builder
     */
    public GetInvestigationRequestBuilder get() {
        return new GetInvestigationRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an investigation and its evidence.
     *
     * <p>Returns this investigation's status, coverage, source records, posts and media. Full evidence is
     * returned by default; explicit evidence.read denial keeps submitted person details, status and
     * coverage, and hides collected evidence. The read is durably audited as person.viewed.
     *
     * <p>The API key selects the workspace and environment. Missing, foreign or expired results answer 404.
     *
     * @param investigationId
     * @return {@code CompletableFuture<GetInvestigationResponse>} - The async response
     */
    public CompletableFuture<GetInvestigationResponse> get(@Nonnull String investigationId) {
        return get(investigationId, null);
    }

    /**
     * Read an investigation and its evidence.
     *
     * <p>Returns this investigation's status, coverage, source records, posts and media. Full evidence is
     * returned by default; explicit evidence.read denial keeps submitted person details, status and
     * coverage, and hides collected evidence. The read is durably audited as person.viewed.
     *
     * <p>The API key selects the workspace and environment. Missing, foreign or expired results answer 404.
     *
     * @param investigationId
     * @param options additional options
     * @return {@code CompletableFuture<GetInvestigationResponse>} - The async response
     */
    public CompletableFuture<GetInvestigationResponse> get(@Nonnull String investigationId, @Nullable Options options) {
        GetInvestigationRequest request = new GetInvestigationRequest(investigationId);
        AsyncRequestOperation<GetInvestigationRequest, GetInvestigationResponse> operation =
                new GetInvestigation.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Cancel an investigation
     *
     * <p>Stops this investigation. A completed or already cancelled investigation keeps its current result.
     * Other investigations for the person keep running.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<CancelInvestigationResponse>} - The async response
     */
    public CompletableFuture<CancelInvestigationResponse> cancel(
            @Nonnull String investigationId, @Nonnull ScreeningCancelRequest body) {
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
     * @return {@code CompletableFuture<CancelInvestigationResponse>} - The async response
     */
    public CompletableFuture<CancelInvestigationResponse> cancel(
            @Nonnull String investigationId, @Nonnull ScreeningCancelRequest body, @Nullable Options options) {
        CancelInvestigationRequest request = new CancelInvestigationRequest(investigationId, body);
        AsyncRequestOperation<CancelInvestigationRequest, CancelInvestigationResponse> operation =
                new CancelInvestigation.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
