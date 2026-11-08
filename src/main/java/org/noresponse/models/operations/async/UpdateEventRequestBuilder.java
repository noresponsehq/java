package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.UpdateEventRequest;
import org.noresponse.models.shared.EventUpdate;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.operations.UpdateEvent;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class UpdateEventRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final UpdateEventRequest.Builder pojoBuilder;
    private UpdateEventRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public UpdateEventRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = UpdateEventRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public UpdateEventRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public UpdateEventRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public UpdateEventRequestBuilder eventId(@Nonnull String eventId) {
        this.pojoBuilder.eventId(eventId);
        this._setterCalled = true;
        return this;
    }

    public UpdateEventRequestBuilder body(@Nonnull EventUpdate body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public UpdateEventRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private UpdateEventRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public UpdateEventRequestBuilder header(String name, String value) {
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
    public CompletableFuture<UpdateEventResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<UpdateEventRequest, UpdateEventResponse> operation =
                new UpdateEvent.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
