package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.GetInvestigationRequest;
import org.noresponse.operations.GetInvestigation;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetInvestigationRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetInvestigationRequest.Builder pojoBuilder;
    private GetInvestigationRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetInvestigationRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetInvestigationRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetInvestigationRequestBuilder investigationId(@Nonnull String investigationId) {
        this.pojoBuilder.investigationId(investigationId);
        this._setterCalled = true;
        return this;
    }

    public GetInvestigationRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetInvestigationRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetInvestigationRequestBuilder header(String name, String value) {
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
    public CompletableFuture<GetInvestigationResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<GetInvestigationRequest, GetInvestigationResponse> operation =
                new GetInvestigation.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
