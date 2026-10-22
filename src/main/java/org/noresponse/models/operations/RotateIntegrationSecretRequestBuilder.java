package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.IntegrationRotationRequest;
import org.noresponse.operations.RotateIntegrationSecret;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class RotateIntegrationSecretRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RotateIntegrationSecretRequest.Builder pojoBuilder;
    private RotateIntegrationSecretRequest request;
    private boolean _setterCalled;

    public RotateIntegrationSecretRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RotateIntegrationSecretRequest.builder();
    }

    public RotateIntegrationSecretRequestBuilder integrationId(@Nonnull String integrationId) {
        this.pojoBuilder.integrationId(integrationId);
        this._setterCalled = true;
        return this;
    }

    public RotateIntegrationSecretRequestBuilder body(@Nonnull IntegrationRotationRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private RotateIntegrationSecretRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RotateIntegrationSecretRequestBuilder header(String name, String value) {
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
    public RotateIntegrationSecretResponse call() {
        RequestOperation<RotateIntegrationSecretRequest, RotateIntegrationSecretResponse> operation =
                new RotateIntegrationSecret.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
