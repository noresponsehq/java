package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ListBulkRequest;
import org.noresponse.operations.ChangeLists;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ChangeListsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ChangeListsRequest.Builder pojoBuilder;
    private ChangeListsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ChangeListsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ChangeListsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ChangeListsRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ChangeListsRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public ChangeListsRequestBuilder idempotencyKey(@Nonnull String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public ChangeListsRequestBuilder body(@Nonnull ListBulkRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public ChangeListsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ChangeListsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ChangeListsRequestBuilder header(String name, String value) {
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
    public ChangeListsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ChangeListsRequest, ChangeListsResponse> operation =
                new ChangeLists.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
