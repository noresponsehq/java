package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ReviewWorkspacePersonChangeRequest;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.operations.ReviewWorkspacePersonChange;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ReviewWorkspacePersonChangeRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ReviewWorkspacePersonChangeRequest.Builder pojoBuilder;
    private ReviewWorkspacePersonChangeRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ReviewWorkspacePersonChangeRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ReviewWorkspacePersonChangeRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ReviewWorkspacePersonChangeRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public ReviewWorkspacePersonChangeRequestBuilder changeId(@Nonnull String changeId) {
        this.pojoBuilder.changeId(changeId);
        this._setterCalled = true;
        return this;
    }

    public ReviewWorkspacePersonChangeRequestBuilder body(@Nonnull ScreeningCancelRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public ReviewWorkspacePersonChangeRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ReviewWorkspacePersonChangeRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ReviewWorkspacePersonChangeRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ReviewWorkspacePersonChangeResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ReviewWorkspacePersonChangeRequest, ReviewWorkspacePersonChangeResponse> operation =
                new ReviewWorkspacePersonChange.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
