package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.operations.CancelInvestigation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class CancelInvestigationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CancelInvestigationRequest.Builder pojoBuilder;
    private CancelInvestigationRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public CancelInvestigationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CancelInvestigationRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public CancelInvestigationRequestBuilder investigationId(@Nonnull String investigationId) {
        this.pojoBuilder.investigationId(investigationId);
        this._setterCalled = true;
        return this;
    }

    public CancelInvestigationRequestBuilder body(@Nonnull ScreeningCancelRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public CancelInvestigationRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private CancelInvestigationRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CancelInvestigationRequestBuilder header(String name, String value) {
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
    public CancelInvestigationResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<CancelInvestigationRequest, CancelInvestigationResponse> operation =
                new CancelInvestigation.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
