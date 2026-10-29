package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.CommentReviewRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewCommentRequest;
import org.noresponse.operations.CommentReview;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class CommentReviewRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CommentReviewRequest.Builder pojoBuilder;
    private CommentReviewRequest request;
    private boolean _setterCalled;

    public CommentReviewRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CommentReviewRequest.builder();
    }

    public CommentReviewRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public CommentReviewRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public CommentReviewRequestBuilder reviewId(@Nonnull String reviewId) {
        this.pojoBuilder.reviewId(reviewId);
        this._setterCalled = true;
        return this;
    }

    public CommentReviewRequestBuilder body(@Nonnull ReviewCommentRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private CommentReviewRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CommentReviewRequestBuilder header(String name, String value) {
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
    public CompletableFuture<CommentReviewResponse> call() {
        AsyncRequestOperation<CommentReviewRequest, CommentReviewResponse> operation =
                new CommentReview.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
