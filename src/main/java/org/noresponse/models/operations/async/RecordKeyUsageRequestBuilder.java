package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.RecordKeyUsageRequest;
import org.noresponse.models.shared.KeyUsageBatchRequest;
import org.noresponse.operations.RecordKeyUsage;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RecordKeyUsageRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RecordKeyUsageRequest.Builder pojoBuilder;
    private RecordKeyUsageRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RecordKeyUsageRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RecordKeyUsageRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RecordKeyUsageRequestBuilder idempotencyKey(@Nonnull String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public RecordKeyUsageRequestBuilder body(@Nonnull KeyUsageBatchRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public RecordKeyUsageRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RecordKeyUsageRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RecordKeyUsageRequestBuilder header(String name, String value) {
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
    public CompletableFuture<RecordKeyUsageResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<RecordKeyUsageRequest, RecordKeyUsageResponse> operation =
                new RecordKeyUsage.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
