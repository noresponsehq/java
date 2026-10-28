package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AssessmentSearchRequest;
import org.noresponse.operations.SearchAssessments;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class SearchAssessmentsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private AssessmentSearchRequest request;
    private final Options.Builder optionsBuilder;

    public SearchAssessmentsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public SearchAssessmentsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public SearchAssessmentsRequestBuilder request(@Nonnull AssessmentSearchRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private AssessmentSearchRequest _buildRequest() {
        return this.request;
    }

    public SearchAssessmentsRequestBuilder header(String name, String value) {
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
    public CompletableFuture<SearchAssessmentsResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<AssessmentSearchRequest, SearchAssessmentsResponse> operation =
                new SearchAssessments.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
