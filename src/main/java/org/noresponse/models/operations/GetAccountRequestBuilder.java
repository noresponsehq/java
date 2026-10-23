package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.GetAccount;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetAccountRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetAccountRequest.Builder pojoBuilder;
    private GetAccountRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetAccountRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetAccountRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetAccountRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public GetAccountRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetAccountRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetAccountRequestBuilder header(String name, String value) {
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
    public GetAccountResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<GetAccountRequest, GetAccountResponse> operation =
                new GetAccount.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
