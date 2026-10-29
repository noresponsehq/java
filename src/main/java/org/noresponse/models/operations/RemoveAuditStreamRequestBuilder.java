package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.Environment;
import org.noresponse.operations.RemoveAuditStream;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RemoveAuditStreamRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RemoveAuditStreamRequest.Builder pojoBuilder;
    private RemoveAuditStreamRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RemoveAuditStreamRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RemoveAuditStreamRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RemoveAuditStreamRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public RemoveAuditStreamRequestBuilder environment(@Nonnull Environment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public RemoveAuditStreamRequestBuilder endpointId(@Nonnull String endpointId) {
        this.pojoBuilder.endpointId(endpointId);
        this._setterCalled = true;
        return this;
    }

    public RemoveAuditStreamRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RemoveAuditStreamRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RemoveAuditStreamRequestBuilder header(String name, String value) {
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
    public RemoveAuditStreamResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RemoveAuditStreamRequest, RemoveAuditStreamResponse> operation =
                new RemoveAuditStream.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
