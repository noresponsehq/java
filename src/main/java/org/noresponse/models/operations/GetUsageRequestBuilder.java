package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.GetUsage;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetUsageRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetUsageRequest.Builder pojoBuilder;
    private GetUsageRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetUsageRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetUsageRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetUsageRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public GetUsageRequestBuilder from(@Nullable String from) {
        this.pojoBuilder.from(from);
        this._setterCalled = true;
        return this;
    }

    public GetUsageRequestBuilder to(@Nullable String to) {
        this.pojoBuilder.to(to);
        this._setterCalled = true;
        return this;
    }

    public GetUsageRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetUsageRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetUsageRequestBuilder header(String name, String value) {
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
    public GetUsageResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<GetUsageRequest, GetUsageResponse> operation =
                new GetUsage.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
