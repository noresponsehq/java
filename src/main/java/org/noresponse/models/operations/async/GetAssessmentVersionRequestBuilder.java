package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.GetAssessmentVersionRequest;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.GetAssessmentVersion;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetAssessmentVersionRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetAssessmentVersionRequest.Builder pojoBuilder;
    private GetAssessmentVersionRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetAssessmentVersionRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetAssessmentVersionRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetAssessmentVersionRequestBuilder assessmentId(@Nonnull String assessmentId) {
        this.pojoBuilder.assessmentId(assessmentId);
        this._setterCalled = true;
        return this;
    }

    public GetAssessmentVersionRequestBuilder responseMode(@Nullable ResponseMode responseMode) {
        this.pojoBuilder.responseMode(responseMode);
        this._setterCalled = true;
        return this;
    }

    public GetAssessmentVersionRequestBuilder version(long version) {
        this.pojoBuilder.version(version);
        this._setterCalled = true;
        return this;
    }

    public GetAssessmentVersionRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetAssessmentVersionRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetAssessmentVersionRequestBuilder header(String name, String value) {
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
    public CompletableFuture<GetAssessmentVersionResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<GetAssessmentVersionRequest, GetAssessmentVersionResponse> operation =
                new GetAssessmentVersion.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
