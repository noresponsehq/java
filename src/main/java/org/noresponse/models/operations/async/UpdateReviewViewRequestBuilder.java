package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.UpdateReviewViewRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewViewRequest;
import org.noresponse.operations.UpdateReviewView;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class UpdateReviewViewRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final UpdateReviewViewRequest.Builder pojoBuilder;
    private UpdateReviewViewRequest request;
    private boolean _setterCalled;

    public UpdateReviewViewRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = UpdateReviewViewRequest.builder();
    }

    public UpdateReviewViewRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public UpdateReviewViewRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public UpdateReviewViewRequestBuilder viewId(@Nonnull String viewId) {
        this.pojoBuilder.viewId(viewId);
        this._setterCalled = true;
        return this;
    }

    public UpdateReviewViewRequestBuilder body(@Nonnull ReviewViewRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private UpdateReviewViewRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public UpdateReviewViewRequestBuilder header(String name, String value) {
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
    public CompletableFuture<UpdateReviewViewResponse> call() {
        AsyncRequestOperation<UpdateReviewViewRequest, UpdateReviewViewResponse> operation =
                new UpdateReviewView.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
