package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.SearchRequest;
import org.noresponse.operations.StartSearch;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class StartSearchRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final StartSearchRequest.Builder pojoBuilder;
    private StartSearchRequest request;
    private boolean _setterCalled;

    public StartSearchRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = StartSearchRequest.builder();
    }

    public StartSearchRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public StartSearchRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public StartSearchRequestBuilder idempotencyKey(@Nullable String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public StartSearchRequestBuilder body(@Nonnull SearchRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private StartSearchRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
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
