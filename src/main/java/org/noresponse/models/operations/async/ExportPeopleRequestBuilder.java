package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ExportPeopleRequest;
import org.noresponse.operations.ExportPeople;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ExportPeopleRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private ExportPeopleRequest request;
    private final Options.Builder optionsBuilder;

    public ExportPeopleRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public ExportPeopleRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public ExportPeopleRequestBuilder request(@Nonnull ExportPeopleRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private ExportPeopleRequest _buildRequest() {
        return this.request;
    }

    public ExportPeopleRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ExportPeopleResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ExportPeopleRequest, ExportPeopleResponse> operation =
                new ExportPeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
