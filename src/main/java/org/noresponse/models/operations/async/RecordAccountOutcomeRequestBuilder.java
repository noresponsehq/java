package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.RecordAccountOutcomeRequest;
import org.noresponse.models.shared.AccountOutcomeRequest;
import org.noresponse.operations.RecordAccountOutcome;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RecordAccountOutcomeRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RecordAccountOutcomeRequest.Builder pojoBuilder;
    private RecordAccountOutcomeRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RecordAccountOutcomeRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RecordAccountOutcomeRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RecordAccountOutcomeRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public RecordAccountOutcomeRequestBuilder idempotencyKey(@Nonnull String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public RecordAccountOutcomeRequestBuilder body(@Nonnull AccountOutcomeRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public RecordAccountOutcomeRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RecordAccountOutcomeRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RecordAccountOutcomeRequestBuilder header(String name, String value) {
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
    public CompletableFuture<RecordAccountOutcomeResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<RecordAccountOutcomeRequest, RecordAccountOutcomeResponse> operation =
                new RecordAccountOutcome.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
