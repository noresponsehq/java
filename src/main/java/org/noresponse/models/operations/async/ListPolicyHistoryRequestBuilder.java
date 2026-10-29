package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ListPolicyHistoryRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.operations.ListPolicyHistory;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListPolicyHistoryRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListPolicyHistoryRequest.Builder pojoBuilder;
    private ListPolicyHistoryRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListPolicyHistoryRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListPolicyHistoryRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListPolicyHistoryRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ListPolicyHistoryRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public ListPolicyHistoryRequestBuilder beforeVersion(@Nullable Long beforeVersion) {
        this.pojoBuilder.beforeVersion(beforeVersion);
        this._setterCalled = true;
        return this;
    }

    public ListPolicyHistoryRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListPolicyHistoryRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListPolicyHistoryRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListPolicyHistoryRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ListPolicyHistoryResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ListPolicyHistoryRequest, ListPolicyHistoryResponse> operation =
                new ListPolicyHistory.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
