package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestlessOperation;

import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ListIntegrations;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListIntegrationsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final Options.Builder optionsBuilder;

    public ListIntegrationsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public ListIntegrationsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public ListIntegrationsRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ListIntegrationsResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestlessOperation<ListIntegrationsResponse> operation =
                new ListIntegrations.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest().thenCompose(operation::handleResponse);
    }
}
