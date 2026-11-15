package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.PersonReviewRequest;
import org.noresponse.operations.ReviewPerson;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ReviewPersonRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ReviewPersonRequest.Builder pojoBuilder;
    private ReviewPersonRequest request;
    private boolean _setterCalled;

    public ReviewPersonRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ReviewPersonRequest.builder();
    }

    public ReviewPersonRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public ReviewPersonRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public ReviewPersonRequestBuilder body(@Nonnull PersonReviewRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private ReviewPersonRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ReviewPersonRequestBuilder header(String name, String value) {
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
    public ReviewPersonResponse call() {
        RequestOperation<ReviewPersonRequest, ReviewPersonResponse> operation =
                new ReviewPerson.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
