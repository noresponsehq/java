package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.CancelCustomerExportRequest;
import org.noresponse.models.shared.CustomerExportActionRequest;
import org.noresponse.operations.CancelCustomerExport;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class CancelCustomerExportRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CancelCustomerExportRequest.Builder pojoBuilder;
    private CancelCustomerExportRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public CancelCustomerExportRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CancelCustomerExportRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public CancelCustomerExportRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public CancelCustomerExportRequestBuilder exportId(@Nonnull String exportId) {
        this.pojoBuilder.exportId(exportId);
        this._setterCalled = true;
        return this;
    }

    public CancelCustomerExportRequestBuilder body(@Nonnull CustomerExportActionRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public CancelCustomerExportRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private CancelCustomerExportRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CancelCustomerExportRequestBuilder header(String name, String value) {
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
    public CompletableFuture<CancelCustomerExportResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<CancelCustomerExportRequest, CancelCustomerExportResponse> operation =
                new CancelCustomerExport.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
