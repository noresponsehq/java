package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AssessmentAggregateRequest;
import org.noresponse.operations.AggregateAssessments;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class AggregateAssessmentsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private AssessmentAggregateRequest request;
    private final Options.Builder optionsBuilder;

    public AggregateAssessmentsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public AggregateAssessmentsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public AggregateAssessmentsRequestBuilder request(@Nonnull AssessmentAggregateRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private AssessmentAggregateRequest _buildRequest() {
        return this.request;
    }

    public AggregateAssessmentsRequestBuilder header(String name, String value) {
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
    public AggregateAssessmentsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<AssessmentAggregateRequest, AggregateAssessmentsResponse> operation =
                new AggregateAssessments.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
