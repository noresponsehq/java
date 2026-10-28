package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.GetAccountDecisionRequest;
import org.noresponse.operations.GetAccountDecision;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetAccountDecisionRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetAccountDecisionRequest.Builder pojoBuilder;
    private GetAccountDecisionRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetAccountDecisionRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetAccountDecisionRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetAccountDecisionRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public GetAccountDecisionRequestBuilder riskVersion(long riskVersion) {
        this.pojoBuilder.riskVersion(riskVersion);
        this._setterCalled = true;
        return this;
    }

    public GetAccountDecisionRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetAccountDecisionRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetAccountDecisionRequestBuilder header(String name, String value) {
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
    public CompletableFuture<GetAccountDecisionResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<GetAccountDecisionRequest, GetAccountDecisionResponse> operation =
                new GetAccountDecision.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
