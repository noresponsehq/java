package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewResolveRequest;
import org.noresponse.operations.ResolveReview;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ResolveReviewRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ResolveReviewRequest.Builder pojoBuilder;
    private ResolveReviewRequest request;
    private boolean _setterCalled;

    public ResolveReviewRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ResolveReviewRequest.builder();
    }

    public ResolveReviewRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ResolveReviewRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public ResolveReviewRequestBuilder reviewId(@Nonnull String reviewId) {
        this.pojoBuilder.reviewId(reviewId);
        this._setterCalled = true;
        return this;
    }

    public ResolveReviewRequestBuilder body(@Nonnull ReviewResolveRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private ResolveReviewRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ResolveReviewRequestBuilder header(String name, String value) {
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
    public ResolveReviewResponse call() {
        RequestOperation<ResolveReviewRequest, ResolveReviewResponse> operation =
                new ResolveReview.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
