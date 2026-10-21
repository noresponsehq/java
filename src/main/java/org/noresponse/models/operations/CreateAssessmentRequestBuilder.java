package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.operations.CreateAssessment;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class CreateAssessmentRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CreateAssessmentRequest.Builder pojoBuilder;
    private CreateAssessmentRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public CreateAssessmentRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CreateAssessmentRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public CreateAssessmentRequestBuilder idempotencyKey(@Nonnull String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public CreateAssessmentRequestBuilder body(@Nonnull AssessmentRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public CreateAssessmentRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private CreateAssessmentRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CreateAssessmentRequestBuilder header(String name, String value) {
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
    public CreateAssessmentResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<CreateAssessmentRequest, CreateAssessmentResponse> operation =
                new CreateAssessment.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
