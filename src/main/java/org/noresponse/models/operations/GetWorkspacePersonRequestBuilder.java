package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.GetWorkspacePerson;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetWorkspacePersonRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetWorkspacePersonRequest.Builder pojoBuilder;
    private GetWorkspacePersonRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetWorkspacePersonRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetWorkspacePersonRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetWorkspacePersonRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public GetWorkspacePersonRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetWorkspacePersonRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetWorkspacePersonRequestBuilder header(String name, String value) {
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
    public GetWorkspacePersonResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<GetWorkspacePersonRequest, GetWorkspacePersonResponse> operation =
                new GetWorkspacePerson.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
