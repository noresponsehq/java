package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;
import static org.noresponse.operations.Operations.AsyncRequestlessOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.DeleteWebhookEndpointRequest;
import org.noresponse.models.operations.ListWebhookDeliveriesRequest;
import org.noresponse.models.operations.ReplayWebhookDeliveryRequest;
import org.noresponse.models.operations.RotateWebhookSigningKeyRequest;
import org.noresponse.models.operations.SendWebhookTestEventRequest;
import org.noresponse.models.operations.async.CreateWebhookEndpointRequestBuilder;
import org.noresponse.models.operations.async.CreateWebhookEndpointResponse;
import org.noresponse.models.operations.async.DeleteWebhookEndpointRequestBuilder;
import org.noresponse.models.operations.async.DeleteWebhookEndpointResponse;
import org.noresponse.models.operations.async.ListWebhookDeliveriesRequestBuilder;
import org.noresponse.models.operations.async.ListWebhookDeliveriesResponse;
import org.noresponse.models.operations.async.ListWebhookEndpointsRequestBuilder;
import org.noresponse.models.operations.async.ListWebhookEndpointsResponse;
import org.noresponse.models.operations.async.ReplayWebhookDeliveryRequestBuilder;
import org.noresponse.models.operations.async.ReplayWebhookDeliveryResponse;
import org.noresponse.models.operations.async.RotateWebhookSigningKeyRequestBuilder;
import org.noresponse.models.operations.async.RotateWebhookSigningKeyResponse;
import org.noresponse.models.operations.async.SendWebhookTestEventRequestBuilder;
import org.noresponse.models.operations.async.SendWebhookTestEventResponse;
import org.noresponse.models.shared.WebhookEndpointRequest;
import org.noresponse.models.shared.WebhookKeyRotationRequest;
import org.noresponse.models.shared.WebhookReplayRequest;
import org.noresponse.models.shared.WebhookTestRequest;
import org.noresponse.operations.CreateWebhookEndpoint;
import org.noresponse.operations.DeleteWebhookEndpoint;
import org.noresponse.operations.ListWebhookDeliveries;
import org.noresponse.operations.ListWebhookEndpoints;
import org.noresponse.operations.ReplayWebhookDelivery;
import org.noresponse.operations.RotateWebhookSigningKey;
import org.noresponse.operations.SendWebhookTestEvent;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Your webhook endpoints: register, list, rotate signing keys, send test events and delete.
 */
public class AsyncWebhookEndpoints {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final WebhookEndpoints syncSDK;

    AsyncWebhookEndpoints(WebhookEndpoints syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public WebhookEndpoints sync() {
        return syncSDK;
    }

    /**
     * List the workspace's webhook endpoints.
     *
     * <p>Lists your webhook endpoints in every state, ordered by endpoint_id, with the ids of their signing
     * keys and without secrets. Requires the webhooks scope.
     *
     * @return The async call builder
     */
    public ListWebhookEndpointsRequestBuilder list() {
        return new ListWebhookEndpointsRequestBuilder(sdkConfiguration);
    }

    /**
     * List the workspace's webhook endpoints.
     *
     * <p>Lists your webhook endpoints in every state, ordered by endpoint_id, with the ids of their signing
     * keys and without secrets. Requires the webhooks scope.
     *
     * @return {@code CompletableFuture<ListWebhookEndpointsResponse>} - The async response
     */
    public CompletableFuture<ListWebhookEndpointsResponse> listDirect() {
        return list(null);
    }

    /**
     * List the workspace's webhook endpoints.
     *
     * <p>Lists your webhook endpoints in every state, ordered by endpoint_id, with the ids of their signing
     * keys and without secrets. Requires the webhooks scope.
     *
     * @param options additional options
     * @return {@code CompletableFuture<ListWebhookEndpointsResponse>} - The async response
     */
    public CompletableFuture<ListWebhookEndpointsResponse> list(@Nullable Options options) {
        AsyncRequestlessOperation<ListWebhookEndpointsResponse> operation =
                new ListWebhookEndpoints.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest().thenCompose(operation::handleResponse);
    }

    /**
     * Register a webhook endpoint.
     *
     * <p>Registers a webhook endpoint and returns it with its first signing secret, shown only in this
     * answer: store it where your receiver reads it. The URL is https on port 443 or 8443 with a DNS host
     * whose every address is public; it is checked now and on every delivery. The endpoint starts
     * unverified and receives assessment events once it echoes an endpoint.verification challenge, which
     * sendTestEvent sends.
     *
     * <p>You can hold 5 endpoints. Requires the webhooks scope, and response_mode detailed also the detailed
     * scope. Registration is not idempotent: a retry after a lost answer registers a second endpoint,
     * which you can list and delete.
     *
     * @return The async call builder
     */
    public CreateWebhookEndpointRequestBuilder create() {
        return new CreateWebhookEndpointRequestBuilder(sdkConfiguration);
    }

    /**
     * Register a webhook endpoint.
     *
     * <p>Registers a webhook endpoint and returns it with its first signing secret, shown only in this
     * answer: store it where your receiver reads it. The URL is https on port 443 or 8443 with a DNS host
     * whose every address is public; it is checked now and on every delivery. The endpoint starts
     * unverified and receives assessment events once it echoes an endpoint.verification challenge, which
     * sendTestEvent sends.
     *
     * <p>You can hold 5 endpoints. Requires the webhooks scope, and response_mode detailed also the detailed
     * scope. Registration is not idempotent: a retry after a lost answer registers a second endpoint,
     * which you can list and delete.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<CreateWebhookEndpointResponse>} - The async response
     */
    public CompletableFuture<CreateWebhookEndpointResponse> create(@Nonnull WebhookEndpointRequest request) {
        AsyncRequestOperation<WebhookEndpointRequest, CreateWebhookEndpointResponse> operation =
                new CreateWebhookEndpoint.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Delete a webhook endpoint.
     *
     * <p>Deletes the endpoint and its signing keys. Deliveries still queued for it are dropped. Requires the
     * webhooks scope.
     *
     * <p>An endpoint that is already gone answers 404.
     *
     * @return The async call builder
     */
    public DeleteWebhookEndpointRequestBuilder delete() {
        return new DeleteWebhookEndpointRequestBuilder(sdkConfiguration);
    }

    /**
     * Delete a webhook endpoint.
     *
     * <p>Deletes the endpoint and its signing keys. Deliveries still queued for it are dropped. Requires the
     * webhooks scope.
     *
     * <p>An endpoint that is already gone answers 404.
     *
     * @param endpointId The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     * @return {@code CompletableFuture<DeleteWebhookEndpointResponse>} - The async response
     */
    public CompletableFuture<DeleteWebhookEndpointResponse> delete(@Nonnull String endpointId) {
        return delete(endpointId, null);
    }

    /**
     * Delete a webhook endpoint.
     *
     * <p>Deletes the endpoint and its signing keys. Deliveries still queued for it are dropped. Requires the
     * webhooks scope.
     *
     * <p>An endpoint that is already gone answers 404.
     *
     * @param endpointId The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     * @param options additional options
     * @return {@code CompletableFuture<DeleteWebhookEndpointResponse>} - The async response
     */
    public CompletableFuture<DeleteWebhookEndpointResponse> delete(
            @Nonnull String endpointId, @Nullable Options options) {
        DeleteWebhookEndpointRequest request = new DeleteWebhookEndpointRequest(endpointId);
        AsyncRequestOperation<DeleteWebhookEndpointRequest, DeleteWebhookEndpointResponse> operation =
                new DeleteWebhookEndpoint.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Rotate an endpoint's signing key.
     *
     * <p>Issues a new signing key for the endpoint and returns its secret once. Every delivery from now on is
     * signed with it; the key it replaces keeps verifying for 7 days, so your receiver can hold both while
     * it switches. Requires the webhooks scope.
     *
     * <p>Rotation is not idempotent: a retry after a lost answer issues another key, which replaces the
     * first.
     *
     * @return The async call builder
     */
    public RotateWebhookSigningKeyRequestBuilder rotateSigningKey() {
        return new RotateWebhookSigningKeyRequestBuilder(sdkConfiguration);
    }

    /**
     * Rotate an endpoint's signing key.
     *
     * <p>Issues a new signing key for the endpoint and returns its secret once. Every delivery from now on is
     * signed with it; the key it replaces keeps verifying for 7 days, so your receiver can hold both while
     * it switches. Requires the webhooks scope.
     *
     * <p>Rotation is not idempotent: a retry after a lost answer issues another key, which replaces the
     * first.
     *
     * @param endpointId The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     * @param body POST /v1/webhooks/{endpoint_id}/rotate body: an empty object. The old key keeps verifying for 7 days.
     * @return {@code CompletableFuture<RotateWebhookSigningKeyResponse>} - The async response
     */
    public CompletableFuture<RotateWebhookSigningKeyResponse> rotateSigningKey(
            @Nonnull String endpointId, @Nonnull WebhookKeyRotationRequest body) {
        RotateWebhookSigningKeyRequest request = new RotateWebhookSigningKeyRequest(endpointId, body);
        AsyncRequestOperation<RotateWebhookSigningKeyRequest, RotateWebhookSigningKeyResponse> operation =
                new RotateWebhookSigningKey.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Send a test event to an endpoint.
     *
     * <p>endpoint.verification sends a signed challenge now and answers whether the endpoint echoed it within
     * 5 seconds; an echo verifies the endpoint, including a disabled one. assessment.updated queues one
     * signed event with test: true and a fixed example assessment in the endpoint's response mode,
     * delivered and retried like any other event; the endpoint must be verified. Both use the endpoint's
     * current signing key.
     *
     * <p>Requires the webhooks scope.
     *
     * @return The async call builder
     */
    public SendWebhookTestEventRequestBuilder sendTestEvent() {
        return new SendWebhookTestEventRequestBuilder(sdkConfiguration);
    }

    /**
     * Send a test event to an endpoint.
     *
     * <p>endpoint.verification sends a signed challenge now and answers whether the endpoint echoed it within
     * 5 seconds; an echo verifies the endpoint, including a disabled one. assessment.updated queues one
     * signed event with test: true and a fixed example assessment in the endpoint's response mode,
     * delivered and retried like any other event; the endpoint must be verified. Both use the endpoint's
     * current signing key.
     *
     * <p>Requires the webhooks scope.
     *
     * @param endpointId The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     * @param body POST /v1/webhooks/{endpoint_id}/test body: which event to send.
     * @return {@code CompletableFuture<SendWebhookTestEventResponse>} - The async response
     */
    public CompletableFuture<SendWebhookTestEventResponse> sendTestEvent(
            @Nonnull String endpointId, @Nonnull WebhookTestRequest body) {
        SendWebhookTestEventRequest request = new SendWebhookTestEventRequest(endpointId, body);
        AsyncRequestOperation<SendWebhookTestEventRequest, SendWebhookTestEventResponse> operation =
                new SendWebhookTestEvent.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List an endpoint's delivery attempts.
     *
     * <p>Every POST the endpoint received, newest first: the event id and type, which attempt it was, whether
     * it was a replay, delivered or failed, the response code, why it failed and how long it took.
     * Attempts are kept for 30 days, or your retention_days when that is shorter, and are deleted with the
     * assessment they carried. Requires the webhooks scope.
     *
     * <p>Read-only and safe to retry. The cursor is opaque and bound to your workspace and the endpoint; a
     * tampered or expired cursor answers 400 invalid_cursor.
     *
     * @return The async call builder
     */
    public ListWebhookDeliveriesRequestBuilder listDeliveries() {
        return new ListWebhookDeliveriesRequestBuilder(sdkConfiguration);
    }

    /**
     * List an endpoint's delivery attempts.
     *
     * <p>Every POST the endpoint received, newest first: the event id and type, which attempt it was, whether
     * it was a replay, delivered or failed, the response code, why it failed and how long it took.
     * Attempts are kept for 30 days, or your retention_days when that is shorter, and are deleted with the
     * assessment they carried. Requires the webhooks scope.
     *
     * <p>Read-only and safe to retry. The cursor is opaque and bound to your workspace and the endpoint; a
     * tampered or expired cursor answers 400 invalid_cursor.
     *
     * @param endpointId The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     * @return {@code CompletableFuture<ListWebhookDeliveriesResponse>} - The async response
     */
    public CompletableFuture<ListWebhookDeliveriesResponse> listDeliveries(@Nonnull String endpointId) {
        return listDeliveries(endpointId, null, null, null);
    }

    /**
     * List an endpoint's delivery attempts.
     *
     * <p>Every POST the endpoint received, newest first: the event id and type, which attempt it was, whether
     * it was a replay, delivered or failed, the response code, why it failed and how long it took.
     * Attempts are kept for 30 days, or your retention_days when that is shorter, and are deleted with the
     * assessment they carried. Requires the webhooks scope.
     *
     * <p>Read-only and safe to retry. The cursor is opaque and bound to your workspace and the endpoint; a
     * tampered or expired cursor answers 400 invalid_cursor.
     *
     * @param endpointId The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     * @param limit Attempts per page.
     * @param cursor next_cursor from the previous page, unchanged.
     * @param options additional options
     * @return {@code CompletableFuture<ListWebhookDeliveriesResponse>} - The async response
     */
    public CompletableFuture<ListWebhookDeliveriesResponse> listDeliveries(
            @Nonnull String endpointId, @Nullable Long limit, @Nullable String cursor, @Nullable Options options) {
        ListWebhookDeliveriesRequest request = new ListWebhookDeliveriesRequest(endpointId, limit, cursor);
        AsyncRequestOperation<ListWebhookDeliveriesRequest, ListWebhookDeliveriesResponse> operation =
                new ListWebhookDeliveries.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Send one delivery again.
     *
     * <p>Queues the event for the endpoint again: the same event id and body it received, with a new
     * NR-Webhook-Timestamp and a signature from the endpoint's current signing key, retried like any other
     * delivery and listed with replay: true. Your receiver deduplicates on the event id. The endpoint must
     * be verified.
     *
     * <p>Requires the webhooks scope. Not idempotent: each call, a retry included, sends the event again.
     *
     * @return The async call builder
     */
    public ReplayWebhookDeliveryRequestBuilder replayDelivery() {
        return new ReplayWebhookDeliveryRequestBuilder(sdkConfiguration);
    }

    /**
     * Send one delivery again.
     *
     * <p>Queues the event for the endpoint again: the same event id and body it received, with a new
     * NR-Webhook-Timestamp and a signature from the endpoint's current signing key, retried like any other
     * delivery and listed with replay: true. Your receiver deduplicates on the event id. The endpoint must
     * be verified.
     *
     * <p>Requires the webhooks scope. Not idempotent: each call, a retry included, sends the event again.
     *
     * @param endpointId The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     * @param eventId The evt_ id of a delivery the endpoint's log holds, as GET /v1/webhooks/{endpoint_id}/deliveries lists it. An id that is malformed, not sent to this endpoint, past the log's retention or another workspace's answers 404.
     * @param body POST /v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay body: an empty object.
     * @return {@code CompletableFuture<ReplayWebhookDeliveryResponse>} - The async response
     */
    public CompletableFuture<ReplayWebhookDeliveryResponse> replayDelivery(
            @Nonnull String endpointId, @Nonnull String eventId, @Nonnull WebhookReplayRequest body) {
        ReplayWebhookDeliveryRequest request = new ReplayWebhookDeliveryRequest(endpointId, eventId, body);
        AsyncRequestOperation<ReplayWebhookDeliveryRequest, ReplayWebhookDeliveryResponse> operation =
                new ReplayWebhookDelivery.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
