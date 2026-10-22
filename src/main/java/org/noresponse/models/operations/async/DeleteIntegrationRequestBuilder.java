package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.DeleteIntegrationRequest;
import org.noresponse.operations.DeleteIntegration;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class DeleteIntegrationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final DeleteIntegrationRequest.Builder pojoBuilder;
    private DeleteIntegrationRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public DeleteIntegrationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = DeleteIntegrationRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public DeleteIntegrationRequestBuilder integrationId(@Nonnull String integrationId) {
        this.pojoBuilder.integrationId(integrationId);
        this._setterCalled = true;
        return this;
    }

    public DeleteIntegrationRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private DeleteIntegrationRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public DeleteIntegrationRequestBuilder header(String name, String value) {
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
    public CompletableFuture<DeleteIntegrationResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<DeleteIntegrationRequest, DeleteIntegrationResponse> operation =
                new DeleteIntegration.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
