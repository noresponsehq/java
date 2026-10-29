package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ListInvites;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListInvitesRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListInvitesRequest.Builder pojoBuilder;
    private ListInvitesRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListInvitesRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListInvitesRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListInvitesRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ListInvitesRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListInvitesRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListInvitesRequestBuilder header(String name, String value) {
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
    public ListInvitesResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ListInvitesRequest, ListInvitesResponse> operation =
                new ListInvites.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
