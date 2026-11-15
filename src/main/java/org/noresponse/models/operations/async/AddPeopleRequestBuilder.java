package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.AddPeopleRequest;
import org.noresponse.models.shared.PersonBatchRequest;
import org.noresponse.operations.AddPeople;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class AddPeopleRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final AddPeopleRequest.Builder pojoBuilder;
    private AddPeopleRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public AddPeopleRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = AddPeopleRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public AddPeopleRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public AddPeopleRequestBuilder idempotencyKey(@Nonnull String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public AddPeopleRequestBuilder body(@Nonnull PersonBatchRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public AddPeopleRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private AddPeopleRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public AddPeopleRequestBuilder header(String name, String value) {
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
    public CompletableFuture<AddPeopleResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<AddPeopleRequest, AddPeopleResponse> operation =
                new AddPeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
