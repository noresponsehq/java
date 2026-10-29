package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.CreatePolicyEvaluationRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.PolicyEvaluationRequest;
import org.noresponse.operations.CreatePolicyEvaluation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class CreatePolicyEvaluationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CreatePolicyEvaluationRequest.Builder pojoBuilder;
    private CreatePolicyEvaluationRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public CreatePolicyEvaluationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CreatePolicyEvaluationRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public CreatePolicyEvaluationRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public CreatePolicyEvaluationRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public CreatePolicyEvaluationRequestBuilder idempotencyKey(@Nonnull String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public CreatePolicyEvaluationRequestBuilder body(@Nonnull PolicyEvaluationRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public CreatePolicyEvaluationRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private CreatePolicyEvaluationRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CreatePolicyEvaluationRequestBuilder header(String name, String value) {
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
    public CompletableFuture<CreatePolicyEvaluationResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<CreatePolicyEvaluationRequest, CreatePolicyEvaluationResponse> operation =
                new CreatePolicyEvaluation.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
