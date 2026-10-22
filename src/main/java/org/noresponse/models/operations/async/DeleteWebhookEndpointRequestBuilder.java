package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.DeleteWebhookEndpointRequest;
import org.noresponse.operations.DeleteWebhookEndpoint;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class DeleteWebhookEndpointRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final DeleteWebhookEndpointRequest.Builder pojoBuilder;
    private DeleteWebhookEndpointRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public DeleteWebhookEndpointRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = DeleteWebhookEndpointRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public DeleteWebhookEndpointRequestBuilder endpointId(@Nonnull String endpointId) {
        this.pojoBuilder.endpointId(endpointId);
        this._setterCalled = true;
        return this;
    }

    public DeleteWebhookEndpointRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private DeleteWebhookEndpointRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public DeleteWebhookEndpointRequestBuilder header(String name, String value) {
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
    public CompletableFuture<DeleteWebhookEndpointResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<DeleteWebhookEndpointRequest, DeleteWebhookEndpointResponse> operation =
                new DeleteWebhookEndpoint.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
