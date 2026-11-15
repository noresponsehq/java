package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.RunSearchRequest;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.operations.RunSearch;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RunSearchRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RunSearchRequest.Builder pojoBuilder;
    private RunSearchRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RunSearchRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RunSearchRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RunSearchRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public RunSearchRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public RunSearchRequestBuilder searchId(@Nonnull String searchId) {
        this.pojoBuilder.searchId(searchId);
        this._setterCalled = true;
        return this;
    }

    public RunSearchRequestBuilder body(@Nonnull ScreeningCancelRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public RunSearchRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RunSearchRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
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
    public CompletableFuture<RunSearchResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<RunSearchRequest, RunSearchResponse> operation =
                new RunSearch.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
