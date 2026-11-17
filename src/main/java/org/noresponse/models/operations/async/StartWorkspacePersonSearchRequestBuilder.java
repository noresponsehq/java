package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.StartWorkspacePersonSearchRequest;
import org.noresponse.models.shared.SearchRequest;
import org.noresponse.operations.StartWorkspacePersonSearch;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class StartWorkspacePersonSearchRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final StartWorkspacePersonSearchRequest.Builder pojoBuilder;
    private StartWorkspacePersonSearchRequest request;
    private boolean _setterCalled;

    public StartWorkspacePersonSearchRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = StartWorkspacePersonSearchRequest.builder();
    }

    public StartWorkspacePersonSearchRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public StartWorkspacePersonSearchRequestBuilder idempotencyKey(@Nullable String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public StartWorkspacePersonSearchRequestBuilder body(@Nonnull SearchRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private StartWorkspacePersonSearchRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public StartWorkspacePersonSearchRequestBuilder header(String name, String value) {
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
    public CompletableFuture<StartWorkspacePersonSearchResponse> call() {
        AsyncRequestOperation<StartWorkspacePersonSearchRequest, StartWorkspacePersonSearchResponse> operation =
                new StartWorkspacePersonSearch.Async(sdkConfiguration, _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
