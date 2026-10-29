package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewViewDeleteRequest;
import org.noresponse.operations.DeleteReviewView;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class DeleteReviewViewRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final DeleteReviewViewRequest.Builder pojoBuilder;
    private DeleteReviewViewRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public DeleteReviewViewRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = DeleteReviewViewRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public DeleteReviewViewRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public DeleteReviewViewRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public DeleteReviewViewRequestBuilder viewId(@Nonnull String viewId) {
        this.pojoBuilder.viewId(viewId);
        this._setterCalled = true;
        return this;
    }

    public DeleteReviewViewRequestBuilder body(@Nonnull ReviewViewDeleteRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public DeleteReviewViewRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private DeleteReviewViewRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public DeleteReviewViewRequestBuilder header(String name, String value) {
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
    public DeleteReviewViewResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<DeleteReviewViewRequest, DeleteReviewViewResponse> operation =
                new DeleteReviewView.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
