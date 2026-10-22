package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
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
    public CompletableFuture<CreateWebhookEndpointResponse> call() {
        AsyncRequestOperation<WebhookEndpointRequest, CreateWebhookEndpointResponse> operation =
                new CreateWebhookEndpoint.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
