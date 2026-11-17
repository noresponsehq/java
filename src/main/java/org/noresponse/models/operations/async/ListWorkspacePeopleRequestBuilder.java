package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ListWorkspacePeopleRequest;
import org.noresponse.operations.ListWorkspacePeople;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ListWorkspacePeopleRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListWorkspacePeopleRequest.Builder pojoBuilder;
    private ListWorkspacePeopleRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListWorkspacePeopleRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListWorkspacePeopleRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListWorkspacePeopleRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListWorkspacePeopleRequestBuilder after(@Nullable String after) {
        this.pojoBuilder.after(after);
        this._setterCalled = true;
        return this;
    }

    public ListWorkspacePeopleRequestBuilder email(@Nullable String email) {
        this.pojoBuilder.email(email);
        this._setterCalled = true;
        return this;
    }

    public ListWorkspacePeopleRequestBuilder phone(@Nullable String phone) {
        this.pojoBuilder.phone(phone);
        this._setterCalled = true;
        return this;
    }

    public ListWorkspacePeopleRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListWorkspacePeopleRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListWorkspacePeopleRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ListWorkspacePeopleResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ListWorkspacePeopleRequest, ListWorkspacePeopleResponse> operation =
                new ListWorkspacePeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
