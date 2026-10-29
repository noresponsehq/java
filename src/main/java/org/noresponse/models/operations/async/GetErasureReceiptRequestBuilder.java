package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.GetErasureReceiptRequest;
import org.noresponse.operations.GetErasureReceipt;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetErasureReceiptRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetErasureReceiptRequest.Builder pojoBuilder;
    private GetErasureReceiptRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetErasureReceiptRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetErasureReceiptRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetErasureReceiptRequestBuilder erasureId(@Nonnull String erasureId) {
        this.pojoBuilder.erasureId(erasureId);
        this._setterCalled = true;
        return this;
    }

    public GetErasureReceiptRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetErasureReceiptRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetErasureReceiptRequestBuilder header(String name, String value) {
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
    public CompletableFuture<GetErasureReceiptResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<GetErasureReceiptRequest, GetErasureReceiptResponse> operation =
                new GetErasureReceipt.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
