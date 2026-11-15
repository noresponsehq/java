package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.WorkspacePersonRequest;
import org.noresponse.operations.CreateWorkspacePerson;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class CreateWorkspacePersonRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CreateWorkspacePersonRequest.Builder pojoBuilder;
    private CreateWorkspacePersonRequest request;
    private boolean _setterCalled;

    public CreateWorkspacePersonRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CreateWorkspacePersonRequest.builder();
    }

    public CreateWorkspacePersonRequestBuilder idempotencyKey(@Nullable String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public CreateWorkspacePersonRequestBuilder body(@Nonnull WorkspacePersonRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private CreateWorkspacePersonRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CreateWorkspacePersonRequestBuilder header(String name, String value) {
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
    public CreateWorkspacePersonResponse call() {
        RequestOperation<CreateWorkspacePersonRequest, CreateWorkspacePersonResponse> operation =
                new CreateWorkspacePerson.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
