package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.GetSettingsRequest;
import org.noresponse.models.operations.GetSettingsRequestBuilder;
import org.noresponse.models.operations.GetSettingsResponse;
import org.noresponse.models.operations.ListPolicyHistoryRequest;
import org.noresponse.models.operations.ListPolicyHistoryRequestBuilder;
import org.noresponse.models.operations.ListPolicyHistoryResponse;
import org.noresponse.models.operations.PreviewPolicyRequest;
import org.noresponse.models.operations.PreviewPolicyRequestBuilder;
import org.noresponse.models.operations.PreviewPolicyResponse;
import org.noresponse.models.operations.RestorePolicyRequest;
import org.noresponse.models.operations.RestorePolicyRequestBuilder;
import org.noresponse.models.operations.RestorePolicyResponse;
import org.noresponse.models.operations.UpdateSettingsRequest;
import org.noresponse.models.operations.UpdateSettingsRequestBuilder;
import org.noresponse.models.operations.UpdateSettingsResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.PolicyPreviewRequest;
import org.noresponse.models.shared.PolicyRestoreRequest;
import org.noresponse.models.shared.SettingsRequest;
import org.noresponse.operations.GetSettings;
import org.noresponse.operations.ListPolicyHistory;
import org.noresponse.operations.PreviewPolicy;
import org.noresponse.operations.RestorePolicy;
import org.noresponse.operations.UpdateSettings;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Environment policy, history and previews.
 */
public class Settings {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncSettings asyncSDK;

    Settings(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncSettings(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncSettings async() {
        return asyncSDK;
    }

    /**
     * Read an environment's settings.
     *
     * <p>Read this environment retention, source selection and effective flow policies. A flow without its
     * own thresholds inherits account_creation. Requires settings or management.
     *
     * <p>Another environment answers404. Read-only and safe to retry.
     *
     * @return The call builder
     */
    public GetSettingsRequestBuilder get() {
        return new GetSettingsRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an environment's settings.
     *
     * <p>Read this environment retention, source selection and effective flow policies. A flow without its
     * own thresholds inherits account_creation. Requires settings or management.
     *
     * <p>Another environment answers404. Read-only and safe to retry.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetSettingsResponse get(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return get(workspaceId, environment, null);
    }

    /**
     * Read an environment's settings.
     *
     * <p>Read this environment retention, source selection and effective flow policies. A flow without its
     * own thresholds inherits account_creation. Requires settings or management.
     *
     * <p>Another environment answers404. Read-only and safe to retry.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetSettingsResponse get(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nullable Options options) {
        GetSettingsRequest request = new GetSettingsRequest(workspaceId, environment);
        RequestOperation<GetSettingsRequest, GetSettingsResponse> operation =
                new GetSettings.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Change an environment's settings.
     *
     * <p>Change named retention, source or flow settings in one transaction. Requires settings or management;
     * changes to active policy also require policy_publish, which legacy management includes. Use
     * expected_policy_version to reject concurrent changes and record a change_reason.
     *
     * <p>Each accepted change creates a fresh version and an atomic audit event. Retention-only edits do not
     * require policy publication authority. Flow thresholds preserve their declared ordering and bounds.
     *
     * @return The call builder
     */
    public UpdateSettingsRequestBuilder update() {
        return new UpdateSettingsRequestBuilder(sdkConfiguration);
    }

    /**
     * Change an environment's settings.
     *
     * <p>Change named retention, source or flow settings in one transaction. Requires settings or management;
     * changes to active policy also require policy_publish, which legacy management includes. Use
     * expected_policy_version to reject concurrent changes and record a change_reason.
     *
     * <p>Each accepted change creates a fresh version and an atomic audit event. Retention-only edits do not
     * require policy publication authority. Flow thresholds preserve their declared ordering and bounds.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Changes only named settings, atomically. Every successful write advances policy_version and retains an immutable settings snapshot. expected_policy_version optionally guards against stale edits; restore always requires it.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateSettingsResponse update(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull SettingsRequest body) {
        return update(workspaceId, environment, body, null);
    }

    /**
     * Change an environment's settings.
     *
     * <p>Change named retention, source or flow settings in one transaction. Requires settings or management;
     * changes to active policy also require policy_publish, which legacy management includes. Use
     * expected_policy_version to reject concurrent changes and record a change_reason.
     *
     * <p>Each accepted change creates a fresh version and an atomic audit event. Retention-only edits do not
     * require policy publication authority. Flow thresholds preserve their declared ordering and bounds.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Changes only named settings, atomically. Every successful write advances policy_version and retains an immutable settings snapshot. expected_policy_version optionally guards against stale edits; restore always requires it.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateSettingsResponse update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull SettingsRequest body,
            @Nullable Options options) {
        UpdateSettingsRequest request = new UpdateSettingsRequest(workspaceId, environment, body);
        RequestOperation<UpdateSettingsRequest, UpdateSettingsResponse> operation =
                new UpdateSettings.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read immutable configuration versions.
     *
     * <p>Read immutable configuration versions in one environment with settings or management. Follow
     * before_version for older records.
     *
     * @return The call builder
     */
    public ListPolicyHistoryRequestBuilder listHistory() {
        return new ListPolicyHistoryRequestBuilder(sdkConfiguration);
    }

    /**
     * Read immutable configuration versions.
     *
     * <p>Read immutable configuration versions in one environment with settings or management. Follow
     * before_version for older records.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListPolicyHistoryResponse listHistory(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return listHistory(workspaceId, environment, null, null, null);
    }

    /**
     * Read immutable configuration versions.
     *
     * <p>Read immutable configuration versions in one environment with settings or management. Follow
     * before_version for older records.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param beforeVersion
     * @param limit
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListPolicyHistoryResponse listHistory(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long beforeVersion,
            @Nullable Long limit,
            @Nullable Options options) {
        ListPolicyHistoryRequest request = new ListPolicyHistoryRequest(workspaceId, environment, beforeVersion, limit);
        RequestOperation<ListPolicyHistoryRequest, ListPolicyHistoryResponse> operation =
                new ListPolicyHistory.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Restore captured settings as a new guarded version.
     *
     * <p>Restore a retained configuration as a fresh version with the expected current version and a change
     * reason. Requires settings plus policy_publish, or legacy management. Restore preserves the
     * historical record and does not undo customer enforcement.
     *
     * @return The call builder
     */
    public RestorePolicyRequestBuilder restore() {
        return new RestorePolicyRequestBuilder(sdkConfiguration);
    }

    /**
     * Restore captured settings as a new guarded version.
     *
     * <p>Restore a retained configuration as a fresh version with the expected current version and a change
     * reason. Requires settings plus policy_publish, or legacy management. Restore preserves the
     * historical record and does not undo customer enforcement.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Publish a captured configuration as a new version. Version checks and publication are atomic. Longer retention does not revive expired records, and restored policy does not undo customer enforcement.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RestorePolicyResponse restore(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull PolicyRestoreRequest body) {
        RestorePolicyRequest request = new RestorePolicyRequest(workspaceId, environment, body);
        RequestOperation<RestorePolicyRequest, RestorePolicyResponse> operation =
                new RestorePolicy.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Preview exact retained-score threshold transitions without side effects.
     *
     * <p>Compare proposed policy with bounded retained first answers, including baseline, coverage and
     * uncertainty. Requires settings or management. This read starts no provider work and publishes no
     * changes.
     *
     * @return The call builder
     */
    public PreviewPolicyRequestBuilder preview() {
        return new PreviewPolicyRequestBuilder(sdkConfiguration);
    }

    /**
     * Preview exact retained-score threshold transitions without side effects.
     *
     * <p>Compare proposed policy with bounded retained first answers, including baseline, coverage and
     * uncertainty. Requires settings or management. This read starts no provider work and publishes no
     * changes.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Side-effect-free threshold preview for at most 31 days. Retained first answers are evaluated at their exact frozen scores using the production threshold and hold rules. Changes to admission, source selection, depth or retention are not simulated.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public PreviewPolicyResponse preview(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull PolicyPreviewRequest body) {
        return preview(workspaceId, environment, body, null);
    }

    /**
     * Preview exact retained-score threshold transitions without side effects.
     *
     * <p>Compare proposed policy with bounded retained first answers, including baseline, coverage and
     * uncertainty. Requires settings or management. This read starts no provider work and publishes no
     * changes.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Side-effect-free threshold preview for at most 31 days. Retained first answers are evaluated at their exact frozen scores using the production threshold and hold rules. Changes to admission, source selection, depth or retention are not simulated.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public PreviewPolicyResponse preview(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull PolicyPreviewRequest body,
            @Nullable Options options) {
        PreviewPolicyRequest request = new PreviewPolicyRequest(workspaceId, environment, body);
        RequestOperation<PreviewPolicyRequest, PreviewPolicyResponse> operation =
                new PreviewPolicy.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
