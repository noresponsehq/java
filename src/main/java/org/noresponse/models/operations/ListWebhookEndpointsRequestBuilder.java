package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestlessOperation;

import jakarta.annotation.Nullable;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ListWebhookEndpoints;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListWebhookEndpointsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final Options.Builder optionsBuilder;

    public ListWebhookEndpointsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public ListWebhookEndpointsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public ListWebhookEndpointsRequestBuilder header(String name, String value) {
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
    public ListWebhookEndpointsResponse call() {
        Options options = optionsBuilder.build();
        RequestlessOperation<ListWebhookEndpointsResponse> operation =
                new ListWebhookEndpoints.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest());
    }
}
