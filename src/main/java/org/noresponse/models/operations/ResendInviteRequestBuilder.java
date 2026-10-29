package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.InviteResendRequest;
import org.noresponse.operations.ResendInvite;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ResendInviteRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ResendInviteRequest.Builder pojoBuilder;
    private ResendInviteRequest request;
    private boolean _setterCalled;

    public ResendInviteRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ResendInviteRequest.builder();
    }

    public ResendInviteRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ResendInviteRequestBuilder inviteId(@Nonnull String inviteId) {
        this.pojoBuilder.inviteId(inviteId);
        this._setterCalled = true;
        return this;
    }

    public ResendInviteRequestBuilder body(@Nonnull InviteResendRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private ResendInviteRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ResendInviteRequestBuilder header(String name, String value) {
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
    public ResendInviteResponse call() {
        RequestOperation<ResendInviteRequest, ResendInviteResponse> operation =
                new ResendInvite.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
