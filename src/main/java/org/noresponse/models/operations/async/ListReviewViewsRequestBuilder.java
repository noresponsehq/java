package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ListReviewViewsRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.operations.ListReviewViews;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListReviewViewsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListReviewViewsRequest.Builder pojoBuilder;
    private ListReviewViewsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListReviewViewsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListReviewViewsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListReviewViewsRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ListReviewViewsRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public ListReviewViewsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListReviewViewsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListReviewViewsRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ListReviewViewsResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ListReviewViewsRequest, ListReviewViewsResponse> operation =
                new ListReviewViews.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
