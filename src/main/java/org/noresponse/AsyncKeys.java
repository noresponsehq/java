package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.async.RotateApiKeyRequestBuilder;
import org.noresponse.models.operations.async.RotateApiKeyResponse;
import org.noresponse.models.shared.KeyRotationRequest;
import org.noresponse.operations.RotateApiKey;
import org.noresponse.utils.Headers;

/**
 * Your own API keys.
 */
public class AsyncKeys {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Keys syncSDK;

    AsyncKeys(Keys syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Keys sync() {
        return syncSDK;
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<RotateApiKeyResponse>} - The async response
     */
    public CompletableFuture<RotateApiKeyResponse> rotate(@Nonnull KeyRotationRequest request) {
        AsyncRequestOperation<KeyRotationRequest, RotateApiKeyResponse> operation =
                new RotateApiKey.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
