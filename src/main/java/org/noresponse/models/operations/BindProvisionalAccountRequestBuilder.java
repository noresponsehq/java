package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AccountBindingRequest;
import org.noresponse.operations.BindProvisionalAccount;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class BindProvisionalAccountRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final BindProvisionalAccountRequest.Builder pojoBuilder;
    private BindProvisionalAccountRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public BindProvisionalAccountRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = BindProvisionalAccountRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public BindProvisionalAccountRequestBuilder assessmentId(@Nonnull String assessmentId) {
        this.pojoBuilder.assessmentId(assessmentId);
        this._setterCalled = true;
        return this;
    }

    public BindProvisionalAccountRequestBuilder body(@Nonnull AccountBindingRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public BindProvisionalAccountRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private BindProvisionalAccountRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public BindProvisionalAccountRequestBuilder header(String name, String value) {
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
    public BindProvisionalAccountResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<BindProvisionalAccountRequest, BindProvisionalAccountResponse> operation =
                new BindProvisionalAccount.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
