package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.RevokeKey;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RevokeKeyRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RevokeKeyRequest.Builder pojoBuilder;
    private RevokeKeyRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RevokeKeyRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RevokeKeyRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RevokeKeyRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public RevokeKeyRequestBuilder keyId(@Nonnull String keyId) {
        this.pojoBuilder.keyId(keyId);
        this._setterCalled = true;
        return this;
    }

    public RevokeKeyRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RevokeKeyRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RevokeKeyRequestBuilder header(String name, String value) {
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
    public RevokeKeyResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RevokeKeyRequest, RevokeKeyResponse> operation =
                new RevokeKey.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
