package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.operations.CancelScreening;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class CancelScreeningRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CancelScreeningRequest.Builder pojoBuilder;
    private CancelScreeningRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public CancelScreeningRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CancelScreeningRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public CancelScreeningRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public CancelScreeningRequestBuilder screeningId(@Nonnull String screeningId) {
        this.pojoBuilder.screeningId(screeningId);
        this._setterCalled = true;
        return this;
    }

    public CancelScreeningRequestBuilder body(@Nonnull ScreeningCancelRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public CancelScreeningRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private CancelScreeningRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CancelScreeningRequestBuilder header(String name, String value) {
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
    public CancelScreeningResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<CancelScreeningRequest, CancelScreeningResponse> operation =
                new CancelScreening.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
