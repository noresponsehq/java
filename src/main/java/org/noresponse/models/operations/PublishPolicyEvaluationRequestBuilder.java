package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.PolicyEvaluationPublishRequest;
import org.noresponse.operations.PublishPolicyEvaluation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class PublishPolicyEvaluationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final PublishPolicyEvaluationRequest.Builder pojoBuilder;
    private PublishPolicyEvaluationRequest request;
    private boolean _setterCalled;

    public PublishPolicyEvaluationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = PublishPolicyEvaluationRequest.builder();
    }

    public PublishPolicyEvaluationRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public PublishPolicyEvaluationRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public PublishPolicyEvaluationRequestBuilder evaluationId(@Nonnull String evaluationId) {
        this.pojoBuilder.evaluationId(evaluationId);
        this._setterCalled = true;
        return this;
    }

    public PublishPolicyEvaluationRequestBuilder body(@Nonnull PolicyEvaluationPublishRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private PublishPolicyEvaluationRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public PublishPolicyEvaluationRequestBuilder header(String name, String value) {
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
    public PublishPolicyEvaluationResponse call() {
        RequestOperation<PublishPolicyEvaluationRequest, PublishPolicyEvaluationResponse> operation =
                new PublishPolicyEvaluation.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
