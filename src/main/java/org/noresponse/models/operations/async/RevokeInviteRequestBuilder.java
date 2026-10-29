package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.RevokeInviteRequest;
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
    public CompletableFuture<RevokeInviteResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<RevokeInviteRequest, RevokeInviteResponse> operation =
                new RevokeInvite.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
