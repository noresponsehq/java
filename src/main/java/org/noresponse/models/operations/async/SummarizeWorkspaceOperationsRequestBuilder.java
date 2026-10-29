package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.SummarizeWorkspaceOperationsRequest;
import org.noresponse.models.shared.WorkspaceOperationsSummaryRequest;
import org.noresponse.operations.SummarizeWorkspaceOperations;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class SummarizeWorkspaceOperationsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final SummarizeWorkspaceOperationsRequest.Builder pojoBuilder;
    private SummarizeWorkspaceOperationsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public SummarizeWorkspaceOperationsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = SummarizeWorkspaceOperationsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public SummarizeWorkspaceOperationsRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public SummarizeWorkspaceOperationsRequestBuilder body(@Nonnull WorkspaceOperationsSummaryRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public SummarizeWorkspaceOperationsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private SummarizeWorkspaceOperationsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public SummarizeWorkspaceOperationsRequestBuilder header(String name, String value) {
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
    public CompletableFuture<SummarizeWorkspaceOperationsResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<SummarizeWorkspaceOperationsRequest, SummarizeWorkspaceOperationsResponse> operation =
                new SummarizeWorkspaceOperations.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
