package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.GetCustomerExport;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class GetCustomerExportRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final GetCustomerExportRequest.Builder pojoBuilder;
    private GetCustomerExportRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public GetCustomerExportRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = GetCustomerExportRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public GetCustomerExportRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public GetCustomerExportRequestBuilder exportId(@Nonnull String exportId) {
        this.pojoBuilder.exportId(exportId);
        this._setterCalled = true;
        return this;
    }

    public GetCustomerExportRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private GetCustomerExportRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public GetCustomerExportRequestBuilder header(String name, String value) {
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
    public GetCustomerExportResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<GetCustomerExportRequest, GetCustomerExportResponse> operation =
                new GetCustomerExport.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
