package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.DeleteWorkspacePerson;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class DeleteWorkspacePersonRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final DeleteWorkspacePersonRequest.Builder pojoBuilder;
    private DeleteWorkspacePersonRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public DeleteWorkspacePersonRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = DeleteWorkspacePersonRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public DeleteWorkspacePersonRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public DeleteWorkspacePersonRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private DeleteWorkspacePersonRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public DeleteWorkspacePersonRequestBuilder header(String name, String value) {
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
    public DeleteWorkspacePersonResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<DeleteWorkspacePersonRequest, DeleteWorkspacePersonResponse> operation =
                new DeleteWorkspacePerson.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
