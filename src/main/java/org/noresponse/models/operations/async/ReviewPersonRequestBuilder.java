package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ReviewPersonRequest;
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
    public CompletableFuture<ReviewPersonResponse> call() {
        AsyncRequestOperation<ReviewPersonRequest, ReviewPersonResponse> operation =
                new ReviewPerson.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
