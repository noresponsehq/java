package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewSubjectRequest;
import org.noresponse.operations.AddReviewSubject;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class AddReviewSubjectRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final AddReviewSubjectRequest.Builder pojoBuilder;
    private AddReviewSubjectRequest request;
    private boolean _setterCalled;

    public AddReviewSubjectRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = AddReviewSubjectRequest.builder();
    }

    public AddReviewSubjectRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public AddReviewSubjectRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public AddReviewSubjectRequestBuilder reviewId(@Nonnull String reviewId) {
        this.pojoBuilder.reviewId(reviewId);
        this._setterCalled = true;
        return this;
    }

    public AddReviewSubjectRequestBuilder body(@Nonnull ReviewSubjectRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private AddReviewSubjectRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public AddReviewSubjectRequestBuilder header(String name, String value) {
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
    public AddReviewSubjectResponse call() {
        RequestOperation<AddReviewSubjectRequest, AddReviewSubjectResponse> operation =
                new AddReviewSubject.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
