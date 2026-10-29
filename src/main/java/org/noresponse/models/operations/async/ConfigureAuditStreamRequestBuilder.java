package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ConfigureAuditStreamRequest;
import org.noresponse.models.shared.AuditStreamRequest;
import org.noresponse.models.shared.Environment;
import org.noresponse.operations.ConfigureAuditStream;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ConfigureAuditStreamRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ConfigureAuditStreamRequest.Builder pojoBuilder;
    private ConfigureAuditStreamRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ConfigureAuditStreamRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ConfigureAuditStreamRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ConfigureAuditStreamRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ConfigureAuditStreamRequestBuilder environment(@Nonnull Environment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public ConfigureAuditStreamRequestBuilder body(@Nonnull AuditStreamRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public ConfigureAuditStreamRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ConfigureAuditStreamRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ConfigureAuditStreamRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ConfigureAuditStreamResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ConfigureAuditStreamRequest, ConfigureAuditStreamResponse> operation =
                new ConfigureAuditStream.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
