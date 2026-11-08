package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.CancelScreeningRequest;
import org.noresponse.operations.CancelScreening;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class CancelScreeningRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private CancelScreeningRequest request;
    private final Options.Builder optionsBuilder;

    public CancelScreeningRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public CancelScreeningRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public CancelScreeningRequestBuilder request(@Nonnull CancelScreeningRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private CancelScreeningRequest _buildRequest() {
        return this.request;
    }

    public CancelScreeningRequestBuilder header(String name, String value) {
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
    public CompletableFuture<CancelScreeningResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<CancelScreeningRequest, CancelScreeningResponse> operation =
                new CancelScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
