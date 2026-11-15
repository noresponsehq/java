package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.RemoveGroupPersonRequest;
import org.noresponse.operations.RemoveGroupPerson;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RemoveGroupPersonRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RemoveGroupPersonRequest.Builder pojoBuilder;
    private RemoveGroupPersonRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RemoveGroupPersonRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RemoveGroupPersonRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RemoveGroupPersonRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public RemoveGroupPersonRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public RemoveGroupPersonRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RemoveGroupPersonRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RemoveGroupPersonRequestBuilder header(String name, String value) {
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
    public CompletableFuture<RemoveGroupPersonResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<RemoveGroupPersonRequest, RemoveGroupPersonResponse> operation =
                new RemoveGroupPerson.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
