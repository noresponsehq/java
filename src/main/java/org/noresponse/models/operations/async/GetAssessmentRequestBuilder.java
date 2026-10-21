package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.GetAssessmentRequest;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.GetAssessment;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetAssessmentRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetAssessmentRequest.Builder pojoBuilder;
    private GetAssessmentRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetAssessmentRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetAssessmentRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetAssessmentRequestBuilder assessmentId(@Nonnull String assessmentId) {
        this.pojoBuilder.assessmentId(assessmentId);
        this._setterCalled = true;
        return this;
    }

    public GetAssessmentRequestBuilder responseMode(@Nullable ResponseMode responseMode) {
        this.pojoBuilder.responseMode(responseMode);
        this._setterCalled = true;
        return this;
    }

    public GetAssessmentRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetAssessmentRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetAssessmentRequestBuilder header(String name, String value) {
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
    public CompletableFuture<GetAssessmentResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<GetAssessmentRequest, GetAssessmentResponse> operation =
                new GetAssessment.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
