package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.WebhookEndpointRequest;
import org.noresponse.operations.CreateWebhookEndpoint;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class CreateWebhookEndpointRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private WebhookEndpointRequest request;

    public CreateWebhookEndpointRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
    }

    public CreateWebhookEndpointRequestBuilder request(@Nonnull WebhookEndpointRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private WebhookEndpointRequest _buildRequest() {
        return this.request;
    }

    public CreateWebhookEndpointRequestBuilder header(String name, String value) {
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
    public CreateWebhookEndpointResponse call() {
        RequestOperation<WebhookEndpointRequest, CreateWebhookEndpointResponse> operation =
                new CreateWebhookEndpoint.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
