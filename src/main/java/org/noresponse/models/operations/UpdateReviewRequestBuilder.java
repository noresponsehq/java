package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewUpdateRequest;
import org.noresponse.operations.UpdateReview;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class UpdateReviewRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final UpdateReviewRequest.Builder pojoBuilder;
    private UpdateReviewRequest request;
    private boolean _setterCalled;

    public UpdateReviewRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = UpdateReviewRequest.builder();
    }

    public UpdateReviewRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public UpdateReviewRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public UpdateReviewRequestBuilder reviewId(@Nonnull String reviewId) {
        this.pojoBuilder.reviewId(reviewId);
        this._setterCalled = true;
        return this;
    }

    public UpdateReviewRequestBuilder body(@Nonnull ReviewUpdateRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private UpdateReviewRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public UpdateReviewRequestBuilder header(String name, String value) {
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
    public UpdateReviewResponse call() {
        RequestOperation<UpdateReviewRequest, UpdateReviewResponse> operation =
                new UpdateReview.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
