package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.GetSettingsRequest;
import org.noresponse.models.operations.GetSettingsRequestBuilder;
import org.noresponse.models.operations.GetSettingsResponse;
import org.noresponse.models.operations.UpdateSettingsRequest;
import org.noresponse.models.operations.UpdateSettingsRequestBuilder;
import org.noresponse.models.operations.UpdateSettingsResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.SettingsRequest;
import org.noresponse.operations.GetSettings;
import org.noresponse.operations.UpdateSettings;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

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
     * <p>Reads the settings of the signing key's environment: retention in days; per flow (account_creation,
     * login, purchase) the step_up, review and block thresholds, the default depth and the admission; and
     * the sources the environment refuses. A flow without thresholds of its own shows account_creation's
     * with inherits_account_creation true. Requires the management scope; the other environment answers
     * 404 not_found.
     *
     * <p>Read-only and safe to retry.
     *
     * @return The call builder
     */
    public GetSettingsRequestBuilder get() {
        return new GetSettingsRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an environment's settings.
     *
     * <p>Reads the settings of the signing key's environment: retention in days; per flow (account_creation,
     * login, purchase) the step_up, review and block thresholds, the default depth and the admission; and
     * the sources the environment refuses. A flow without thresholds of its own shows account_creation's
     * with inherits_account_creation true. Requires the management scope; the other environment answers
     * 404 not_found.
     *
     * <p>Read-only and safe to retry.
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
     * <p>Reads the settings of the signing key's environment: retention in days; per flow (account_creation,
     * login, purchase) the step_up, review and block thresholds, the default depth and the admission; and
     * the sources the environment refuses. A flow without thresholds of its own shows account_creation's
     * with inherits_account_creation true. Requires the management scope; the other environment answers
     * 404 not_found.
     *
     * <p>Read-only and safe to retry.
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
     * <p>Changes only what the body names, in one transaction: retention_days, refused_sources, and per flow
     * step_up, review, block (null turns blocking off), default_depth, admission, hold_seconds, on_expiry,
     * or inherits_account_creation to return login or purchase to account_creation's thresholds. After the
     * change each flow keeps 0 &lt; step_up &lt; review &lt;= 100 and block off or review &lt;= block
     * &lt;= 100; a change that breaks it answers 400 naming the flow. A policy change is one new
     * policy_version with the signing key's prefix as updated_by, and every change is recorded in the
     * workspace's audit log with the key as its actor.
     *
     * <p>Requires the management scope; the other environment answers 404 not_found. A change reaches
     * assessments within a minute.
     *
     * @return The call builder
     */
    public UpdateSettingsRequestBuilder update() {
        return new UpdateSettingsRequestBuilder(sdkConfiguration);
    }

    /**
     * Change an environment's settings.
     *
     * <p>Changes only what the body names, in one transaction: retention_days, refused_sources, and per flow
     * step_up, review, block (null turns blocking off), default_depth, admission, hold_seconds, on_expiry,
     * or inherits_account_creation to return login or purchase to account_creation's thresholds. After the
     * change each flow keeps 0 &lt; step_up &lt; review &lt;= 100 and block off or review &lt;= block
     * &lt;= 100; a change that breaks it answers 400 naming the flow. A policy change is one new
     * policy_version with the signing key's prefix as updated_by, and every change is recorded in the
     * workspace's audit log with the key as its actor.
     *
     * <p>Requires the management scope; the other environment answers 404 not_found. A change reaches
     * assessments within a minute.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body PATCH /v1/workspaces/{workspace_id}/settings/{environment} body. Changes only what it names, in one transaction; a policy change is one new policy_version. Needs the admin or owner role, or an API key with the management scope for its own environment.
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
     * <p>Changes only what the body names, in one transaction: retention_days, refused_sources, and per flow
     * step_up, review, block (null turns blocking off), default_depth, admission, hold_seconds, on_expiry,
     * or inherits_account_creation to return login or purchase to account_creation's thresholds. After the
     * change each flow keeps 0 &lt; step_up &lt; review &lt;= 100 and block off or review &lt;= block
     * &lt;= 100; a change that breaks it answers 400 naming the flow. A policy change is one new
     * policy_version with the signing key's prefix as updated_by, and every change is recorded in the
     * workspace's audit log with the key as its actor.
     *
     * <p>Requires the management scope; the other environment answers 404 not_found. A change reaches
     * assessments within a minute.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body PATCH /v1/workspaces/{workspace_id}/settings/{environment} body. Changes only what it names, in one transaction; a policy change is one new policy_version. Needs the admin or owner role, or an API key with the management scope for its own environment.
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
}
