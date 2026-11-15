package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import org.noresponse.SDKConfiguration;
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
    public StartSearchResponse call() {
        RequestOperation<StartSearchRequest, StartSearchResponse> operation =
                new StartSearch.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
