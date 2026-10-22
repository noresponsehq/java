package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.IntegrationRequest;
import org.noresponse.operations.CreateIntegration;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class CreateIntegrationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private IntegrationRequest request;

    public CreateIntegrationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
    }

    public CreateIntegrationRequestBuilder request(@Nonnull IntegrationRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private IntegrationRequest _buildRequest() {
        return this.request;
    }

    public CreateIntegrationRequestBuilder header(String name, String value) {
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
    public CreateIntegrationResponse call() {
        RequestOperation<IntegrationRequest, CreateIntegrationResponse> operation =
                new CreateIntegration.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
