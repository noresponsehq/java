package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ListRequestDiagnostics;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListRequestDiagnosticsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private ListRequestDiagnosticsRequest request;
    private final Options.Builder optionsBuilder;

    public ListRequestDiagnosticsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public ListRequestDiagnosticsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public ListRequestDiagnosticsRequestBuilder request(@Nonnull ListRequestDiagnosticsRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private ListRequestDiagnosticsRequest _buildRequest() {
        return this.request;
    }

    public ListRequestDiagnosticsRequestBuilder header(String name, String value) {
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
    public ListRequestDiagnosticsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ListRequestDiagnosticsRequest, ListRequestDiagnosticsResponse> operation =
                new ListRequestDiagnostics.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
