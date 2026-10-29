package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AccountDirectorySearchRequest;
import org.noresponse.operations.SearchAccounts;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class SearchAccountsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private AccountDirectorySearchRequest request;
    private final Options.Builder optionsBuilder;

    public SearchAccountsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public SearchAccountsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public SearchAccountsRequestBuilder request(@Nonnull AccountDirectorySearchRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private AccountDirectorySearchRequest _buildRequest() {
        return this.request;
    }

    public SearchAccountsRequestBuilder header(String name, String value) {
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
    public SearchAccountsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<AccountDirectorySearchRequest, SearchAccountsResponse> operation =
                new SearchAccounts.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
