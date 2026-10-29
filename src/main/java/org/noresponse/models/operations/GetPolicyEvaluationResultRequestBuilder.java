package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.operations.GetPolicyEvaluationResult;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetPolicyEvaluationResultRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetPolicyEvaluationResultRequest.Builder pojoBuilder;
    private GetPolicyEvaluationResultRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetPolicyEvaluationResultRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetPolicyEvaluationResultRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetPolicyEvaluationResultRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public GetPolicyEvaluationResultRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public GetPolicyEvaluationResultRequestBuilder evaluationId(@Nonnull String evaluationId) {
        this.pojoBuilder.evaluationId(evaluationId);
        this._setterCalled = true;
        return this;
    }

    public GetPolicyEvaluationResultRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetPolicyEvaluationResultRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetPolicyEvaluationResultRequestBuilder header(String name, String value) {
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
    public GetPolicyEvaluationResultResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<GetPolicyEvaluationResultRequest, GetPolicyEvaluationResultResponse> operation =
                new GetPolicyEvaluationResult.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
