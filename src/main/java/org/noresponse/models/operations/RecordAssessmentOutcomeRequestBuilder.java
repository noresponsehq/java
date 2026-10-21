package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AssessmentOutcomeRequest;
import org.noresponse.operations.RecordAssessmentOutcome;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RecordAssessmentOutcomeRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RecordAssessmentOutcomeRequest.Builder pojoBuilder;
    private RecordAssessmentOutcomeRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RecordAssessmentOutcomeRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RecordAssessmentOutcomeRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RecordAssessmentOutcomeRequestBuilder assessmentId(@Nonnull String assessmentId) {
        this.pojoBuilder.assessmentId(assessmentId);
        this._setterCalled = true;
        return this;
    }

    public RecordAssessmentOutcomeRequestBuilder idempotencyKey(@Nonnull String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public RecordAssessmentOutcomeRequestBuilder body(@Nonnull AssessmentOutcomeRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public RecordAssessmentOutcomeRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RecordAssessmentOutcomeRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RecordAssessmentOutcomeRequestBuilder header(String name, String value) {
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
    public RecordAssessmentOutcomeResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RecordAssessmentOutcomeRequest, RecordAssessmentOutcomeResponse> operation =
                new RecordAssessmentOutcome.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
