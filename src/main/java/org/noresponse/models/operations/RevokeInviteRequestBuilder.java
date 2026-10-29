package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.RevokeInvite;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RevokeInviteRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RevokeInviteRequest.Builder pojoBuilder;
    private RevokeInviteRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RevokeInviteRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RevokeInviteRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RevokeInviteRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public RevokeInviteRequestBuilder inviteId(@Nonnull String inviteId) {
        this.pojoBuilder.inviteId(inviteId);
        this._setterCalled = true;
        return this;
    }

    public RevokeInviteRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RevokeInviteRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RevokeInviteRequestBuilder header(String name, String value) {
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
    public RevokeInviteResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RevokeInviteRequest, RevokeInviteResponse> operation =
                new RevokeInvite.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
