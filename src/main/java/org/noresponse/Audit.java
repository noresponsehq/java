package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.ConfigureAuditStreamRequest;
import org.noresponse.models.operations.ConfigureAuditStreamRequestBuilder;
import org.noresponse.models.operations.ConfigureAuditStreamResponse;
import org.noresponse.models.operations.Environment;
import org.noresponse.models.operations.GetAuditRetentionRequest;
import org.noresponse.models.operations.GetAuditRetentionRequestBuilder;
import org.noresponse.models.operations.GetAuditRetentionResponse;
import org.noresponse.models.operations.ListAuditEventsRequest;
import org.noresponse.models.operations.ListAuditEventsRequestBuilder;
import org.noresponse.models.operations.ListAuditEventsResponse;
import org.noresponse.models.operations.ListAuditStreamsRequest;
import org.noresponse.models.operations.ListAuditStreamsRequestBuilder;
import org.noresponse.models.operations.ListAuditStreamsResponse;
import org.noresponse.models.operations.RemoveAuditStreamRequest;
import org.noresponse.models.operations.RemoveAuditStreamRequestBuilder;
import org.noresponse.models.operations.RemoveAuditStreamResponse;
import org.noresponse.models.operations.ReplayAuditStreamRequest;
import org.noresponse.models.operations.ReplayAuditStreamRequestBuilder;
import org.noresponse.models.operations.ReplayAuditStreamResponse;
import org.noresponse.models.operations.UpdateAuditRetentionRequest;
import org.noresponse.models.operations.UpdateAuditRetentionRequestBuilder;
import org.noresponse.models.operations.UpdateAuditRetentionResponse;
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
public class Audit {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncAudit asyncSDK;

    Audit(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncAudit(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncAudit async() {
        return asyncSDK;
    }

    /**
     * List a workspace's audit log.
     *
     * <p>Read audit events within your current environment permissions. Filters apply before pagination.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAuditEventsResponse list(@Nonnull ListAuditEventsRequest request) {
        return list(request, null);
    }

    /**
     * List a workspace's audit log.
     *
     * <p>Read audit events within your current environment permissions. Filters apply before pagination.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAuditEventsResponse list(@Nonnull ListAuditEventsRequest request, @Nullable Options options) {
        RequestOperation<ListAuditEventsRequest, ListAuditEventsResponse> operation =
                new ListAuditEvents.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read audit retention and available history.
     *
     * <p>Read audit retention for the environment. The default is 365 days. Oldest available is an
     * observation; it does not establish uninterrupted capture.
     *
     * <p>Workspace-wide events follow the live storage policy.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAuditRetentionResponse getRetention(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAuditRetentionResponse getRetention(
            @Nonnull String workspaceId, @Nonnull AuditSettingsEnvironment environment, @Nullable Options options) {
        GetAuditRetentionRequest request = new GetAuditRetentionRequest(workspaceId, environment);
        RequestOperation<GetAuditRetentionRequest, GetAuditRetentionResponse> operation =
                new GetAuditRetention.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
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
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateAuditRetentionResponse updateRetention(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateAuditRetentionResponse updateRetention(
            @Nonnull String workspaceId,
            @Nonnull Environment environment,
            @Nonnull AuditSettingsRequest body,
            @Nullable Options options) {
        UpdateAuditRetentionRequest request = new UpdateAuditRetentionRequest(workspaceId, environment, body);
        RequestOperation<UpdateAuditRetentionRequest, UpdateAuditRetentionResponse> operation =
                new UpdateAuditRetention.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
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
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAuditStreamsResponse listStreams(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAuditStreamsResponse listStreams(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nullable Options options) {
        ListAuditStreamsRequest request = new ListAuditStreamsRequest(workspaceId, environment);
        RequestOperation<ListAuditStreamsRequest, ListAuditStreamsResponse> operation =
                new ListAuditStreams.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Configure an audit stream subscription.
     *
     * <p>Configure the action filter for one verified webhook endpoint. Requires audit_manage. Capture starts
     * at subscription creation; use an audit export for available earlier events.
     *
     * <p>Workspace-wide scope requires an unrestricted console member and is unavailable to API keys.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ConfigureAuditStreamResponse configureStream(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ConfigureAuditStreamResponse configureStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull AuditStreamRequest body,
            @Nullable Options options) {
        ConfigureAuditStreamRequest request = new ConfigureAuditStreamRequest(workspaceId, environment, body);
        RequestOperation<ConfigureAuditStreamRequest, ConfigureAuditStreamResponse> operation =
                new ConfigureAuditStream.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Remove an audit stream.
     *
     * <p>Remove an audit subscription and cancel queued deliveries. Requires audit_manage. An HTTP delivery
     * already in flight cannot be recalled.
     *
     * <p>The endpoint remains registered for other event types.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RemoveAuditStreamResponse removeStream(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RemoveAuditStreamResponse removeStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull String endpointId,
            @Nullable Options options) {
        RemoveAuditStreamRequest request = new RemoveAuditStreamRequest(workspaceId, environment, endpointId);
        RequestOperation<RemoveAuditStreamRequest, RemoveAuditStreamResponse> operation =
                new RemoveAuditStream.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Replay an audit delivery.
     *
     * <p>Replay a recorded delivery for the current audit subscription. Requires audit_manage. Source
     * retention and the current action filter are checked again before sending.
     *
     * <p>Expired or unsubscribed events are dropped.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ReplayAuditStreamResponse replayStream(
            @Nonnull String workspaceId,
            @Nonnull org.noresponse.models.shared.Environment environment,
            @Nonnull String endpointId,
            @Nonnull AuditStreamReplayRequest body) {
        ReplayAuditStreamRequest request = new ReplayAuditStreamRequest(workspaceId, environment, endpointId, body);
        RequestOperation<ReplayAuditStreamRequest, ReplayAuditStreamResponse> operation =
                new ReplayAuditStream.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
