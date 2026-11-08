package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ReviewAttendeeRequest;
import org.noresponse.operations.ReviewAttendee;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ReviewAttendeeRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private ReviewAttendeeRequest request;

    public ReviewAttendeeRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
    }

    public ReviewAttendeeRequestBuilder request(@Nonnull ReviewAttendeeRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private ReviewAttendeeRequest _buildRequest() {
        return this.request;
    }

    public ReviewAttendeeRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ReviewAttendeeResponse> call() {
        AsyncRequestOperation<ReviewAttendeeRequest, ReviewAttendeeResponse> operation =
                new ReviewAttendee.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
