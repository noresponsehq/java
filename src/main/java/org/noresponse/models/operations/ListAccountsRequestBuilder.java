package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ListAccounts;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListAccountsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListAccountsRequest.Builder pojoBuilder;
    private ListAccountsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListAccountsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListAccountsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListAccountsRequestBuilder changedSince(@Nullable String changedSince) {
        this.pojoBuilder.changedSince(changedSince);
        this._setterCalled = true;
        return this;
    }

    public ListAccountsRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListAccountsRequestBuilder unenforced(@Nullable Boolean unenforced) {
        this.pojoBuilder.unenforced(unenforced);
        this._setterCalled = true;
        return this;
    }

    public ListAccountsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListAccountsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListAccountsRequestBuilder header(String name, String value) {
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
    public ListAccountsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ListAccountsRequest, ListAccountsResponse> operation =
                new ListAccounts.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
