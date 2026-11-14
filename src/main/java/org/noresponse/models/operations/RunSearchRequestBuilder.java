package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.RunSearch;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RunSearchRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private RunSearchRequest request;
    private final Options.Builder optionsBuilder;

    public RunSearchRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public RunSearchRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public RunSearchRequestBuilder request(@Nonnull RunSearchRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private RunSearchRequest _buildRequest() {
        return this.request;
    }

    public RunSearchRequestBuilder header(String name, String value) {
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
    public RunSearchResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RunSearchRequest, RunSearchResponse> operation =
                new RunSearch.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
