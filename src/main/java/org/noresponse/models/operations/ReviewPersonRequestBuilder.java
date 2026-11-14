package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ReviewPerson;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ReviewPersonRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private ReviewPersonRequest request;

    public ReviewPersonRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
    }

    public ReviewPersonRequestBuilder request(@Nonnull ReviewPersonRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private ReviewPersonRequest _buildRequest() {
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
