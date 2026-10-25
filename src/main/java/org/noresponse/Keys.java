package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.CreateKeyRequest;
import org.noresponse.models.operations.CreateKeyRequestBuilder;
import org.noresponse.models.operations.CreateKeyResponse;
import org.noresponse.models.operations.ListKeysRequest;
import org.noresponse.models.operations.ListKeysRequestBuilder;
import org.noresponse.models.operations.ListKeysResponse;
import org.noresponse.models.operations.RevokeKeyRequest;
import org.noresponse.models.operations.RevokeKeyRequestBuilder;
import org.noresponse.models.operations.RevokeKeyResponse;
import org.noresponse.models.operations.RotateApiKeyRequestBuilder;
import org.noresponse.models.operations.RotateApiKeyResponse;
import org.noresponse.models.shared.KeyRequest;
import org.noresponse.models.shared.KeyRotationRequest;
import org.noresponse.operations.CreateKey;
import org.noresponse.operations.ListKeys;
import org.noresponse.operations.RevokeKey;
import org.noresponse.operations.RotateApiKey;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Your own API keys.
 */
public class Keys {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncKeys asyncSDK;

    Keys(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncKeys(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncKeys async() {
        return asyncSDK;
    }

    /**
     * Rotate the API key that signs the request.
     *
     * <p>Mints a new key with the same scopes as the key that signs the request and returns it once. The
     * presented key keeps working for overlap_seconds (default 24 hours, from 60 seconds to 7 days), then
     * answers 401. A key accepts one rotation: rotating again with a key already in its overlap answers
     * 409 key_rotation_in_progress, so rotate with the new key.
     *
     * <p>Rotation is not idempotent: a retry after a lost answer mints another key.
     *
     * @return The call builder
     */
    public RotateApiKeyRequestBuilder rotate() {
        return new RotateApiKeyRequestBuilder(sdkConfiguration);
    }

    /**
     * Rotate the API key that signs the request.
     *
     * <p>Mints a new key with the same scopes as the key that signs the request and returns it once. The
     * presented key keeps working for overlap_seconds (default 24 hours, from 60 seconds to 7 days), then
     * answers 401. A key accepts one rotation: rotating again with a key already in its overlap answers
     * 409 key_rotation_in_progress, so rotate with the new key.
     *
     * <p>Rotation is not idempotent: a retry after a lost answer mints another key.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RotateApiKeyResponse rotate(@Nonnull KeyRotationRequest request) {
        RequestOperation<KeyRotationRequest, RotateApiKeyResponse> operation =
                new RotateApiKey.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Create an API key.
     *
     * <p>Creates an API key in the environment of the key that signs the request, with scopes that key holds
     * itself, and returns its secret once in key. Requires the management scope; the new key may hold it
     * too. A scope the signing key lacks answers 403 workspace_forbidden, and the other environment 404
     * not_found.
     *
     * <p>An environment holds at most 100 keys that authenticate (409 key_limit_reached). created_by is the
     * signing key's prefix. Not idempotent: a retry after a lost answer creates a second key, which you
     * can list and revoke.
     *
     * @return The call builder
     */
    public CreateKeyRequestBuilder create() {
        return new CreateKeyRequestBuilder(sdkConfiguration);
    }

    /**
     * Create an API key.
     *
     * <p>Creates an API key in the environment of the key that signs the request, with scopes that key holds
     * itself, and returns its secret once in key. Requires the management scope; the new key may hold it
     * too. A scope the signing key lacks answers 403 workspace_forbidden, and the other environment 404
     * not_found.
     *
     * <p>An environment holds at most 100 keys that authenticate (409 key_limit_reached). created_by is the
     * signing key's prefix. Not idempotent: a retry after a lost answer creates a second key, which you
     * can list and revoke.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param body POST /v1/workspaces/{workspace_id}/keys body. Creates an API key in one environment of the workspace with the scopes named, and returns its secret once. A live key needs the admin or owner role; a test key needs developer or above. An API key with the management scope creates keys in its own environment with scopes it holds itself. Each scope may appear once.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateKeyResponse create(@Nonnull String workspaceId, @Nonnull KeyRequest body) {
        CreateKeyRequest request = new CreateKeyRequest(workspaceId, body);
        RequestOperation<CreateKeyRequest, CreateKeyResponse> operation =
                new CreateKey.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List API keys.
     *
     * <p>Lists the keys of the signing key's environment, newest first, at most 200, revoked and expired keys
     * included, without secrets: each key's id, prefix, scopes, status, and who created and revoked it.
     * Requires the management scope. Read-only and safe to retry.
     *
     * @return The call builder
     */
    public ListKeysRequestBuilder list() {
        return new ListKeysRequestBuilder(sdkConfiguration);
    }

    /**
     * List API keys.
     *
     * <p>Lists the keys of the signing key's environment, newest first, at most 200, revoked and expired keys
     * included, without secrets: each key's id, prefix, scopes, status, and who created and revoked it.
     * Requires the management scope. Read-only and safe to retry.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListKeysResponse list(@Nonnull String workspaceId) {
        return list(workspaceId, null);
    }

    /**
     * List API keys.
     *
     * <p>Lists the keys of the signing key's environment, newest first, at most 200, revoked and expired keys
     * included, without secrets: each key's id, prefix, scopes, status, and who created and revoked it.
     * Requires the management scope. Read-only and safe to retry.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListKeysResponse list(@Nonnull String workspaceId, @Nullable Options options) {
        ListKeysRequest request = new ListKeysRequest(workspaceId);
        RequestOperation<ListKeysRequest, ListKeysResponse> operation =
                new ListKeys.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Revoke an API key.
     *
     * <p>Revokes a key of the signing key's environment at once: its next request answers 401. Requires the
     * management scope. A key of the other environment or of another workspace answers 404 not_found.
     *
     * <p>A key cannot revoke itself (409 self_revocation): rotate it with keys.rotate, or revoke it with
     * another management key. Revoking a revoked key changes nothing and answers the key as it was
     * revoked, so a retry is safe.
     *
     * @return The call builder
     */
    public RevokeKeyRequestBuilder revoke() {
        return new RevokeKeyRequestBuilder(sdkConfiguration);
    }

    /**
     * Revoke an API key.
     *
     * <p>Revokes a key of the signing key's environment at once: its next request answers 401. Requires the
     * management scope. A key of the other environment or of another workspace answers 404 not_found.
     *
     * <p>A key cannot revoke itself (409 self_revocation): rotate it with keys.rotate, or revoke it with
     * another management key. Revoking a revoked key changes nothing and answers the key as it was
     * revoked, so a retry is safe.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param keyId The key's id. A key of another workspace answers 404, as does a key of the other environment to an API key.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RevokeKeyResponse revoke(@Nonnull String workspaceId, @Nonnull String keyId) {
        return revoke(workspaceId, keyId, null);
    }

    /**
     * Revoke an API key.
     *
     * <p>Revokes a key of the signing key's environment at once: its next request answers 401. Requires the
     * management scope. A key of the other environment or of another workspace answers 404 not_found.
     *
     * <p>A key cannot revoke itself (409 self_revocation): rotate it with keys.rotate, or revoke it with
     * another management key. Revoking a revoked key changes nothing and answers the key as it was
     * revoked, so a retry is safe.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param keyId The key's id. A key of another workspace answers 404, as does a key of the other environment to an API key.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RevokeKeyResponse revoke(@Nonnull String workspaceId, @Nonnull String keyId, @Nullable Options options) {
        RevokeKeyRequest request = new RevokeKeyRequest(workspaceId, keyId);
        RequestOperation<RevokeKeyRequest, RevokeKeyResponse> operation =
                new RevokeKey.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
