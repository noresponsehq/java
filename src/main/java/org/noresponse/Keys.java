package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import org.noresponse.models.operations.RotateApiKeyRequestBuilder;
import org.noresponse.models.operations.RotateApiKeyResponse;
import org.noresponse.models.shared.KeyRotationRequest;
import org.noresponse.operations.RotateApiKey;
import org.noresponse.utils.Headers;

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
}
