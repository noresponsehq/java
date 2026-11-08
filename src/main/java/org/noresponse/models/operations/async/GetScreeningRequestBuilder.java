package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.GetScreeningRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.operations.GetScreening;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetScreeningRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetScreeningRequest.Builder pojoBuilder;
    private GetScreeningRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetScreeningRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetScreeningRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetScreeningRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public GetScreeningRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public GetScreeningRequestBuilder eventId(@Nonnull String eventId) {
        this.pojoBuilder.eventId(eventId);
        this._setterCalled = true;
        return this;
    }

    public GetScreeningRequestBuilder screeningId(@Nonnull String screeningId) {
        this.pojoBuilder.screeningId(screeningId);
        this._setterCalled = true;
        return this;
    }

    public GetScreeningRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetScreeningRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetScreeningRequestBuilder header(String name, String value) {
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
    public CompletableFuture<GetScreeningResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<GetScreeningRequest, GetScreeningResponse> operation =
                new GetScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
