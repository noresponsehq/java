package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.CancelPolicyEvaluationRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.PolicyEvaluationCancelRequest;
import org.noresponse.operations.CancelPolicyEvaluation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class CancelPolicyEvaluationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CancelPolicyEvaluationRequest.Builder pojoBuilder;
    private CancelPolicyEvaluationRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public CancelPolicyEvaluationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CancelPolicyEvaluationRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public CancelPolicyEvaluationRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public CancelPolicyEvaluationRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public CancelPolicyEvaluationRequestBuilder evaluationId(@Nonnull String evaluationId) {
        this.pojoBuilder.evaluationId(evaluationId);
        this._setterCalled = true;
        return this;
    }

    public CancelPolicyEvaluationRequestBuilder body(@Nonnull PolicyEvaluationCancelRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public CancelPolicyEvaluationRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private CancelPolicyEvaluationRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CancelPolicyEvaluationRequestBuilder header(String name, String value) {
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
    public CompletableFuture<CancelPolicyEvaluationResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<CancelPolicyEvaluationRequest, CancelPolicyEvaluationResponse> operation =
                new CancelPolicyEvaluation.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
