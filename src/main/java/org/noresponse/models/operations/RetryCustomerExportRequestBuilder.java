package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.CustomerExportActionRequest;
import org.noresponse.operations.RetryCustomerExport;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class RetryCustomerExportRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RetryCustomerExportRequest.Builder pojoBuilder;
    private RetryCustomerExportRequest request;
    private boolean _setterCalled;

    public RetryCustomerExportRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RetryCustomerExportRequest.builder();
    }

    public RetryCustomerExportRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public RetryCustomerExportRequestBuilder exportId(@Nonnull String exportId) {
        this.pojoBuilder.exportId(exportId);
        this._setterCalled = true;
        return this;
    }

    public RetryCustomerExportRequestBuilder body(@Nonnull CustomerExportActionRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private RetryCustomerExportRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RetryCustomerExportRequestBuilder header(String name, String value) {
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
    public RetryCustomerExportResponse call() {
        RequestOperation<RetryCustomerExportRequest, RetryCustomerExportResponse> operation =
                new RetryCustomerExport.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
