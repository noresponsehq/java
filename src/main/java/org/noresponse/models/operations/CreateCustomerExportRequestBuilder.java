package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.CustomerExportRequest;
import org.noresponse.operations.CreateCustomerExport;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class CreateCustomerExportRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CreateCustomerExportRequest.Builder pojoBuilder;
    private CreateCustomerExportRequest request;
    private boolean _setterCalled;

    public CreateCustomerExportRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CreateCustomerExportRequest.builder();
    }

    public CreateCustomerExportRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public CreateCustomerExportRequestBuilder idempotencyKey(@Nullable String idempotencyKey) {
        this.pojoBuilder.idempotencyKey(idempotencyKey);
        this._setterCalled = true;
        return this;
    }

    public CreateCustomerExportRequestBuilder body(@Nonnull CustomerExportRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private CreateCustomerExportRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CreateCustomerExportRequestBuilder header(String name, String value) {
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
    public CreateCustomerExportResponse call() {
        RequestOperation<CreateCustomerExportRequest, CreateCustomerExportResponse> operation =
                new CreateCustomerExport.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
