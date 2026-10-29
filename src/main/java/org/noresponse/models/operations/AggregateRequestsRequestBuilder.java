package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.RequestAggregateRequest;
import org.noresponse.operations.AggregateRequests;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class AggregateRequestsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private RequestAggregateRequest request;
    private final Options.Builder optionsBuilder;

    public AggregateRequestsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public AggregateRequestsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public AggregateRequestsRequestBuilder request(@Nonnull RequestAggregateRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private RequestAggregateRequest _buildRequest() {
        return this.request;
    }

    public AggregateRequestsRequestBuilder header(String name, String value) {
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
    public AggregateRequestsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RequestAggregateRequest, AggregateRequestsResponse> operation =
                new AggregateRequests.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
