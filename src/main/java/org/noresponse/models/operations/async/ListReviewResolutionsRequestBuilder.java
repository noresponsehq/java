package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ListReviewResolutionsRequest;
import org.noresponse.operations.ListReviewResolutions;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListReviewResolutionsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private ListReviewResolutionsRequest request;
    private final Options.Builder optionsBuilder;

    public ListReviewResolutionsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public ListReviewResolutionsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public ListReviewResolutionsRequestBuilder request(@Nonnull ListReviewResolutionsRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private ListReviewResolutionsRequest _buildRequest() {
        return this.request;
    }

    public ListReviewResolutionsRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ListReviewResolutionsResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ListReviewResolutionsRequest, ListReviewResolutionsResponse> operation =
                new ListReviewResolutions.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
