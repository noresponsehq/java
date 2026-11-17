package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.operations.RunWorkspacePersonSearch;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RunWorkspacePersonSearchRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RunWorkspacePersonSearchRequest.Builder pojoBuilder;
    private RunWorkspacePersonSearchRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RunWorkspacePersonSearchRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RunWorkspacePersonSearchRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RunWorkspacePersonSearchRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public RunWorkspacePersonSearchRequestBuilder searchId(@Nonnull String searchId) {
        this.pojoBuilder.searchId(searchId);
        this._setterCalled = true;
        return this;
    }

    public RunWorkspacePersonSearchRequestBuilder body(@Nonnull ScreeningCancelRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public RunWorkspacePersonSearchRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RunWorkspacePersonSearchRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RunWorkspacePersonSearchRequestBuilder header(String name, String value) {
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
    public RunWorkspacePersonSearchResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RunWorkspacePersonSearchRequest, RunWorkspacePersonSearchResponse> operation =
                new RunWorkspacePersonSearch.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
