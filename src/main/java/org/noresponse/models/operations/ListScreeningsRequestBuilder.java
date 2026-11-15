package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ListScreenings;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListScreeningsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListScreeningsRequest.Builder pojoBuilder;
    private ListScreeningsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListScreeningsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListScreeningsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListScreeningsRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public ListScreeningsRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListScreeningsRequestBuilder before(@Nullable String before) {
        this.pojoBuilder.before(before);
        this._setterCalled = true;
        return this;
    }

    public ListScreeningsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListScreeningsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListScreeningsRequestBuilder header(String name, String value) {
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
    public ListScreeningsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ListScreeningsRequest, ListScreeningsResponse> operation =
                new ListScreenings.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
