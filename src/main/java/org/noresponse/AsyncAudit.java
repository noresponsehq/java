package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.ConfigureAuditStreamRequest;
import org.noresponse.models.operations.Environment;
import org.noresponse.models.operations.GetAuditRetentionRequest;
import org.noresponse.models.operations.ListAuditEventsRequest;
import org.noresponse.models.operations.ListAuditStreamsRequest;
import org.noresponse.models.operations.RemoveAuditStreamRequest;
import org.noresponse.models.operations.ReplayAuditStreamRequest;
import org.noresponse.models.operations.UpdateAuditRetentionRequest;
import org.noresponse.models.operations.async.ConfigureAuditStreamRequestBuilder;
import org.noresponse.models.operations.async.ConfigureAuditStreamResponse;
import org.noresponse.models.operations.async.GetAuditRetentionRequestBuilder;
import org.noresponse.models.operations.async.GetAuditRetentionResponse;
import org.noresponse.models.operations.async.ListAuditEventsRequestBuilder;
import org.noresponse.models.operations.async.ListAuditEventsResponse;
import org.noresponse.models.operations.async.ListAuditStreamsRequestBuilder;
import org.noresponse.models.operations.async.ListAuditStreamsResponse;
import org.noresponse.models.operations.async.RemoveAuditStreamRequestBuilder;
import org.noresponse.models.operations.async.RemoveAuditStreamResponse;
import org.noresponse.models.operations.async.ReplayAuditStreamRequestBuilder;
import org.noresponse.models.operations.async.ReplayAuditStreamResponse;
import org.noresponse.models.operations.async.UpdateAuditRetentionRequestBuilder;
import org.noresponse.models.operations.async.UpdateAuditRetentionResponse;
import org.noresponse.models.shared.AuditSettingsEnvironment;
import org.noresponse.models.shared.AuditSettingsRequest;
import org.noresponse.models.shared.AuditStreamReplayRequest;
import org.noresponse.models.shared.AuditStreamRequest;
import org.noresponse.operations.ConfigureAuditStream;
import org.noresponse.operations.GetAuditRetention;
import org.noresponse.operations.ListAuditEvents;
import org.noresponse.operations.ListAuditStreams;
import org.noresponse.operations.RemoveAuditStream;
import org.noresponse.operations.ReplayAuditStream;
import org.noresponse.operations.UpdateAuditRetention;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Durable audit history, retention and delivery.
 */
public class AsyncAudit {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Audit syncSDK;

    AsyncAudit(Audit syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Audit sync() {
        return syncSDK;
    }

    /**
     * List a workspace's audit log.
     *
     * <p>Read audit events within your current environment permissions. Filters apply before pagination.
     *
     * @return The async call builder
     */
    public ListAuditEventsRequestBuilder list() {
        return new ListAuditEventsRequestBuilder(sdkConfiguration);
    }

    /**
     * List a workspace's audit log.
     *
     * <p>Read audit events within your current environment permissions. Filters apply before pagination.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListAuditEventsResponse>} - The async response
     */
    public CompletableFuture<ListAuditEventsResponse> list(@Nonnull ListAuditEventsRequest request) {
        return list(request, null);
    }

    /**
     * List a workspace's audit log.
     *
     * <p>Read audit events within your current environment permissions. Filters apply before pagination.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListAuditEventsResponse>} - The async response
     */
    public CompletableFuture<ListAuditEventsResponse> list(
            @Nonnull ListAuditEventsRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListAuditEventsRequest, ListAuditEventsResponse> operation =
                new ListAuditEvents.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read audit retention and available history.
     *
     * <p>Read audit retention for the environment. The default is 365 days. Oldest available is an
     * observation; it does not establish uninterrupted capture.
     *
     * <p>Workspace-wide events follow the live storage policy.
     *
     * @return The async call builder
     */
    public GetAuditRetentionRequestBuilder getRetention() {
        return new GetAuditRetentionRequestBuilder(sdkConfiguration);
    }

    /**
     * Read audit retention and available history.
     *
     * <p>Read audit retention for the environment. The default is 365 days. Oldest available is an
     * observation; it does not establish uninterrupted capture.
     *
     * <p>Workspace-wide events follow the live storage policy.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @return {@code CompletableFuture<GetAuditRetentionResponse>} - The async response
     */
    public CompletableFuture<GetAuditRetentionResponse> getRetention(
            @Nonnull String workspaceId, @Nonnull AuditSettingsEnvironment environment) {
        return getRetention(workspaceId, environment, null);
    }

    /**
     * Read audit retention and available history.
     *
     * <p>Read audit retention for the environment. The default is 365 days. Oldest available is an
     * observation; it does not establish uninterrupted capture.
     *
     * <p>Workspace-wide events follow the live storage policy.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param options additional options
     * @return {@code CompletableFuture<GetAuditRetentionResponse>} - The async response
     */
    public CompletableFuture<GetAuditRetentionResponse> getRetention(
            @Nonnull String workspaceId, @Nonnull AuditSettingsEnvironment environment, @Nullable Options options) {
        GetAuditRetentionRequest request = new GetAuditRetentionRequest(workspaceId, environment);
        AsyncRequestOperation<GetAuditRetentionRequest, GetAuditRetentionResponse> operation =
                new GetAuditRetention.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Set bounded customer audit retention.
     *
     * <p>Set test-environment audit retention from 1 to 365 days with audit_manage scope. Live retention also
     * governs workspace-wide audit events and requires a dashboard member authorised in both environments.
     * Shortening immediately hides older events and invalidates audit exports; extending cannot restore
     * deleted events.
     *
     * <p>External receivers control copies already delivered.
     *
     * @return The async call builder
     */
    public UpdateAuditRetentionRequestBuilder updateRetention() {
        return new UpdateAuditRetentionRequestBuilder(sdkConfiguration);
    }

    /**
     * Set bounded customer audit retention.
     *
     * <p>Set test-environment audit retention from 1 to 365 days with audit_manage scope. Live retention also
     * governs workspace-wide audit events and requires a dashboard member authorised in both environments.
     * Shortening immediately hides older events and invalidates audit exports; extending cannot restore
     * deleted events.
     *
     * <p>External receivers control copies already delivered.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param body
     * @return {@code CompletableFuture<UpdateAuditRetentionResponse>} - The async response
     */
    public CompletableFuture<UpdateAuditRetentionResponse> updateRetention(
            @Nonnull String workspaceId, @Nonnull Environment environment, @Nonnull AuditSettingsRequest body) {
        return updateRetention(workspaceId, environment, body, null);
    }

    /**
     * Set bounded customer audit retention.
     *
     * <p>Set test-environment audit retention from 1 to 365 days with audit_manage scope. Live retention also
     * governs workspace-wide audit events and requires a dashboard member authorised in both environments.
     * Shortening immediately hides older events and invalidates audit exports; extending cannot restore
     * deleted events.
     *
     * <p>External receivers control copies already delivered.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param body
     * @param options additional options
     * @return {@code CompletableFuture<UpdateAuditRetentionResponse>} - The async response
     */
    public CompletableFuture<UpdateAuditRetentionResponse> updateRetention(
            @Nonnull String workspaceId,
            @Nonnull Environment environment,
            @Nonnull AuditSettingsRequest body,
            @Nullable Options options) {
        UpdateAuditRetentionRequest request = new UpdateAuditRetentionRequest(workspaceId, environment, body);
        AsyncRequestOperation<UpdateAuditRetentionRequest, UpdateAuditRetentionResponse> operation =
                new UpdateAuditRetention.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read audit stream subscriptions.
     *
     * <p>List audit subscriptions and their endpoint state and latest delivery attempt. Streams use verified
     * HTTPS webhook endpoints and signed audit.event bodies. Delivery is at least once and unordered;
     * deduplicate by webhook id.
     *
     * <p>An empty action filter accepts all actions within the chosen scope.
     *
     * @return The async call builder
     */
    public ListAuditStreamsRequestBuilder listStreams() {
        return new ListAuditStreamsRequestBuilder(sdkConfiguration);
    }

    /**
     * Read audit stream subscriptions.
     *
     * <p>List audit subscriptions and their endpoint state and latest delivery attempt. Streams use verified
     * HTTPS webhook endpoints and signed audit.event bodies. Delivery is at least once and unordered;
     * deduplicate by webhook id.
     *
     * <p>An empty action filter accepts all actions within the chosen scope.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @return {@code CompletableFuture<ListAuditStreamsResponse>} - The async response
     */
    public CompletableFuture<ListAuditStreamsResponse> listStreams(
            @Nonnull String workspaceId, @Nonnull org.noresponse.models.shared.Environment environment) {
        return listStreams(workspaceId, environment, null);
    }

    /**
     * Read audit stream subscriptions.
     *
     * <p>List audit subscriptions and their endpoint state and latest delivery attempt. Streams use verified
     * HTTPS webhook endpoints and signed audit.event bodies. Delivery is at least once and unordered;
     * deduplicate by webhook id.
     *
     * <p>An empty action filter accepts all actions within the chosen scope.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param options additional options
     * @return {@code CompletableFuture<ListAuditStreamsResponse>} - The async response
     */
    public CompletableFuture<ListAuditStreamsResponse> listStreams(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nullable Options options) {
        ListAuditStreamsRequest request = new ListAuditStreamsRequest(workspaceId, environment);
        AsyncRequestOperation<ListAuditStreamsRequest, ListAuditStreamsResponse> operation =
                new ListAuditStreams.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Configure an audit stream subscription.
     *
     * <p>Configure the action filter for one verified webhook endpoint. Requires audit_manage. Capture starts
     * at subscription creation; use an audit export for available earlier events.
     *
     * <p>Workspace-wide scope requires an unrestricted console member and is unavailable to API keys.
     *
     * @return The async call builder
     */
    public ConfigureAuditStreamRequestBuilder configureStream() {
        return new ConfigureAuditStreamRequestBuilder(sdkConfiguration);
    }

    /**
     * Configure an audit stream subscription.
     *
     * <p>Configure the action filter for one verified webhook endpoint. Requires audit_manage. Capture starts
     * at subscription creation; use an audit export for available earlier events.
     *
     * <p>Workspace-wide scope requires an unrestricted console member and is unavailable to API keys.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param body
     * @return {@code CompletableFuture<ConfigureAuditStreamResponse>} - The async response
     */
    public CompletableFuture<ConfigureAuditStreamResponse> configureStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull AuditStreamRequest body) {
        return configureStream(workspaceId, environment, body, null);
    }

    /**
     * Configure an audit stream subscription.
     *
     * <p>Configure the action filter for one verified webhook endpoint. Requires audit_manage. Capture starts
     * at subscription creation; use an audit export for available earlier events.
     *
     * <p>Workspace-wide scope requires an unrestricted console member and is unavailable to API keys.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param body
     * @param options additional options
     * @return {@code CompletableFuture<ConfigureAuditStreamResponse>} - The async response
     */
    public CompletableFuture<ConfigureAuditStreamResponse> configureStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull AuditStreamRequest body,
            @Nullable Options options) {
        ConfigureAuditStreamRequest request = new ConfigureAuditStreamRequest(workspaceId, environment, body);
        AsyncRequestOperation<ConfigureAuditStreamRequest, ConfigureAuditStreamResponse> operation =
                new ConfigureAuditStream.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Remove an audit stream.
     *
     * <p>Remove an audit subscription and cancel queued deliveries. Requires audit_manage. An HTTP delivery
     * already in flight cannot be recalled.
     *
     * <p>The endpoint remains registered for other event types.
     *
     * @return The async call builder
     */
    public RemoveAuditStreamRequestBuilder removeStream() {
        return new RemoveAuditStreamRequestBuilder(sdkConfiguration);
    }

    /**
     * Remove an audit stream.
     *
     * <p>Remove an audit subscription and cancel queued deliveries. Requires audit_manage. An HTTP delivery
     * already in flight cannot be recalled.
     *
     * <p>The endpoint remains registered for other event types.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param endpointId
     * @return {@code CompletableFuture<RemoveAuditStreamResponse>} - The async response
     */
    public CompletableFuture<RemoveAuditStreamResponse> removeStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull String endpointId) {
        return removeStream(workspaceId, environment, endpointId, null);
    }

    /**
     * Remove an audit stream.
     *
     * <p>Remove an audit subscription and cancel queued deliveries. Requires audit_manage. An HTTP delivery
     * already in flight cannot be recalled.
     *
     * <p>The endpoint remains registered for other event types.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param endpointId
     * @param options additional options
     * @return {@code CompletableFuture<RemoveAuditStreamResponse>} - The async response
     */
    public CompletableFuture<RemoveAuditStreamResponse> removeStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull String endpointId,
            @Nullable Options options) {
        RemoveAuditStreamRequest request = new RemoveAuditStreamRequest(workspaceId, environment, endpointId);
        AsyncRequestOperation<RemoveAuditStreamRequest, RemoveAuditStreamResponse> operation =
                new RemoveAuditStream.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Replay an audit delivery.
     *
     * <p>Replay a recorded delivery for the current audit subscription. Requires audit_manage. Source
     * retention and the current action filter are checked again before sending.
     *
     * <p>Expired or unsubscribed events are dropped.
     *
     * @return The async call builder
     */
    public ReplayAuditStreamRequestBuilder replayStream() {
        return new ReplayAuditStreamRequestBuilder(sdkConfiguration);
    }

    /**
     * Replay an audit delivery.
     *
     * <p>Replay a recorded delivery for the current audit subscription. Requires audit_manage. Source
     * retention and the current action filter are checked again before sending.
     *
     * <p>Expired or unsubscribed events are dropped.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment
     * @param endpointId
     * @param body
     * @return {@code CompletableFuture<ReplayAuditStreamResponse>} - The async response
     */
    public CompletableFuture<ReplayAuditStreamResponse> replayStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull String endpointId,
            @Nonnull AuditStreamReplayRequest body) {
        ReplayAuditStreamRequest request = new ReplayAuditStreamRequest(workspaceId, environment, endpointId, body);
        AsyncRequestOperation<ReplayAuditStreamRequest, ReplayAuditStreamResponse> operation =
                new ReplayAuditStream.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
