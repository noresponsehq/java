package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.CredentialRequest;
import org.noresponse.operations.AssociateCredential;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class AssociateCredentialRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final AssociateCredentialRequest.Builder pojoBuilder;
    private AssociateCredentialRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public AssociateCredentialRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = AssociateCredentialRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public AssociateCredentialRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public AssociateCredentialRequestBuilder body(@Nonnull CredentialRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public AssociateCredentialRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private AssociateCredentialRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public AssociateCredentialRequestBuilder header(String name, String value) {
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
    public AssociateCredentialResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<AssociateCredentialRequest, AssociateCredentialResponse> operation =
                new AssociateCredential.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
