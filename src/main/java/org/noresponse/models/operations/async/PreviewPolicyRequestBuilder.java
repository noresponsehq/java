package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.PreviewPolicyRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.PolicyPreviewRequest;
import org.noresponse.operations.PreviewPolicy;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class PreviewPolicyRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final PreviewPolicyRequest.Builder pojoBuilder;
    private PreviewPolicyRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public PreviewPolicyRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = PreviewPolicyRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public PreviewPolicyRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public PreviewPolicyRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public PreviewPolicyRequestBuilder body(@Nonnull PolicyPreviewRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public PreviewPolicyRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private PreviewPolicyRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public PreviewPolicyRequestBuilder header(String name, String value) {
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
    public CompletableFuture<PreviewPolicyResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<PreviewPolicyRequest, PreviewPolicyResponse> operation =
                new PreviewPolicy.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
