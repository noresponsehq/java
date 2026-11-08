package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.operations.StartScreening;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class StartScreeningRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private StartScreeningRequest request;
    private final Options.Builder optionsBuilder;

    public StartScreeningRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public StartScreeningRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public StartScreeningRequestBuilder request(@Nonnull StartScreeningRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private StartScreeningRequest _buildRequest() {
        return this.request;
    }

    public StartScreeningRequestBuilder header(String name, String value) {
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
    public CompletableFuture<StartScreeningResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<StartScreeningRequest, StartScreeningResponse> operation =
                new StartScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
