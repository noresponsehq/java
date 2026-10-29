package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.DeleteReviewSubjectRequest;
import org.noresponse.operations.DeleteReviewSubject;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class DeleteReviewSubjectRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private DeleteReviewSubjectRequest request;
    private final Options.Builder optionsBuilder;

    public DeleteReviewSubjectRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public DeleteReviewSubjectRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public DeleteReviewSubjectRequestBuilder request(@Nonnull DeleteReviewSubjectRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private DeleteReviewSubjectRequest _buildRequest() {
        return this.request;
    }

    public DeleteReviewSubjectRequestBuilder header(String name, String value) {
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
    public CompletableFuture<DeleteReviewSubjectResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<DeleteReviewSubjectRequest, DeleteReviewSubjectResponse> operation =
                new DeleteReviewSubject.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
