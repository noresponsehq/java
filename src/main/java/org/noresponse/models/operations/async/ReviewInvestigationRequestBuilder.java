package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ReviewInvestigationRequest;
import org.noresponse.models.shared.PersonReviewRequest;
import org.noresponse.operations.ReviewInvestigation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ReviewInvestigationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ReviewInvestigationRequest.Builder pojoBuilder;
    private ReviewInvestigationRequest request;
    private boolean _setterCalled;

    public ReviewInvestigationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ReviewInvestigationRequest.builder();
    }

    public ReviewInvestigationRequestBuilder investigationId(@Nonnull String investigationId) {
        this.pojoBuilder.investigationId(investigationId);
        this._setterCalled = true;
        return this;
    }

    public ReviewInvestigationRequestBuilder body(@Nonnull PersonReviewRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private ReviewInvestigationRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ReviewInvestigationRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ReviewInvestigationResponse> call() {
        AsyncRequestOperation<ReviewInvestigationRequest, ReviewInvestigationResponse> operation =
                new ReviewInvestigation.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
