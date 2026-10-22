package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.RotateWebhookSigningKeyRequest;
import org.noresponse.models.shared.WebhookKeyRotationRequest;
import org.noresponse.operations.RotateWebhookSigningKey;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class RotateWebhookSigningKeyRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RotateWebhookSigningKeyRequest.Builder pojoBuilder;
    private RotateWebhookSigningKeyRequest request;
    private boolean _setterCalled;

    public RotateWebhookSigningKeyRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RotateWebhookSigningKeyRequest.builder();
    }

    public RotateWebhookSigningKeyRequestBuilder endpointId(@Nonnull String endpointId) {
        this.pojoBuilder.endpointId(endpointId);
        this._setterCalled = true;
        return this;
    }

    public RotateWebhookSigningKeyRequestBuilder body(@Nonnull WebhookKeyRotationRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private RotateWebhookSigningKeyRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RotateWebhookSigningKeyRequestBuilder header(String name, String value) {
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
    public CompletableFuture<RotateWebhookSigningKeyResponse> call() {
        AsyncRequestOperation<RotateWebhookSigningKeyRequest, RotateWebhookSigningKeyResponse> operation =
                new RotateWebhookSigningKey.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
