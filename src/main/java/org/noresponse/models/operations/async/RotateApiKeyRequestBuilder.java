package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyRotationRequest;
import org.noresponse.operations.RotateApiKey;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class RotateApiKeyRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private KeyRotationRequest request;

    public RotateApiKeyRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
    }

    public RotateApiKeyRequestBuilder request(@Nonnull KeyRotationRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private KeyRotationRequest _buildRequest() {
        return this.request;
    }

    public RotateApiKeyRequestBuilder header(String name, String value) {
        Utils.checkNotNull(name, "name");
        Utils.checkNotNull(value, "value");
        this._headers.add(name, value);
        return this;
    }

    /**
     * Executes the request and returns the response.
     *
     * @return The response from the server.
     */
    public CompletableFuture<RotateApiKeyResponse> call() {
        AsyncRequestOperation<KeyRotationRequest, RotateApiKeyResponse> operation =
                new RotateApiKey.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
