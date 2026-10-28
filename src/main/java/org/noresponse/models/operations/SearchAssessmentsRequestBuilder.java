package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
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
    public SearchAssessmentsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<AssessmentSearchRequest, SearchAssessmentsResponse> operation =
                new SearchAssessments.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
