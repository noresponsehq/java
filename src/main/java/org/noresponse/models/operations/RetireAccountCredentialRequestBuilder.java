package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.RetireAccountCredential;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RetireAccountCredentialRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RetireAccountCredentialRequest.Builder pojoBuilder;
    private RetireAccountCredentialRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RetireAccountCredentialRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RetireAccountCredentialRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RetireAccountCredentialRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public RetireAccountCredentialRequestBuilder credentialId(@Nonnull String credentialId) {
        this.pojoBuilder.credentialId(credentialId);
        this._setterCalled = true;
        return this;
    }

    public RetireAccountCredentialRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RetireAccountCredentialRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RetireAccountCredentialRequestBuilder header(String name, String value) {
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
    public RetireAccountCredentialResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<RetireAccountCredentialRequest, RetireAccountCredentialResponse> operation =
                new RetireAccountCredential.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
