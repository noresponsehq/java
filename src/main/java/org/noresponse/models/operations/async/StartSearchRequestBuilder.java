package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.StartSearchRequest;
import org.noresponse.operations.StartSearch;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class StartSearchRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private StartSearchRequest request;

    public StartSearchRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
    }

    public StartSearchRequestBuilder request(@Nonnull StartSearchRequest request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private StartSearchRequest _buildRequest() {
        return this.request;
    }

    public StartSearchRequestBuilder header(String name, String value) {
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
    public CompletableFuture<StartSearchResponse> call() {
        AsyncRequestOperation<StartSearchRequest, StartSearchResponse> operation =
                new StartSearch.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
