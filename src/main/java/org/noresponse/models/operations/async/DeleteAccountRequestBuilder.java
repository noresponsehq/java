package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.DeleteAccountRequest;
import org.noresponse.operations.DeleteAccount;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class DeleteAccountRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final DeleteAccountRequest.Builder pojoBuilder;
    private DeleteAccountRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public DeleteAccountRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = DeleteAccountRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public DeleteAccountRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public DeleteAccountRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private DeleteAccountRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public DeleteAccountRequestBuilder header(String name, String value) {
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
    public CompletableFuture<DeleteAccountResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<DeleteAccountRequest, DeleteAccountResponse> operation =
                new DeleteAccount.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
