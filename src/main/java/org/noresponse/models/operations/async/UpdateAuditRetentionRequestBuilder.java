package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.Environment;
import org.noresponse.models.operations.UpdateAuditRetentionRequest;
import org.noresponse.models.shared.AuditSettingsRequest;
import org.noresponse.operations.UpdateAuditRetention;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class UpdateAuditRetentionRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final UpdateAuditRetentionRequest.Builder pojoBuilder;
    private UpdateAuditRetentionRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public UpdateAuditRetentionRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = UpdateAuditRetentionRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public UpdateAuditRetentionRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public UpdateAuditRetentionRequestBuilder environment(@Nonnull Environment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public UpdateAuditRetentionRequestBuilder body(@Nonnull AuditSettingsRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public UpdateAuditRetentionRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private UpdateAuditRetentionRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public UpdateAuditRetentionRequestBuilder header(String name, String value) {
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
    public CompletableFuture<UpdateAuditRetentionResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<UpdateAuditRetentionRequest, UpdateAuditRetentionResponse> operation =
                new UpdateAuditRetention.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
