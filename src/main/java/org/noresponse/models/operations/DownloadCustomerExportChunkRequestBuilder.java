package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.DownloadCustomerExportChunk;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class DownloadCustomerExportChunkRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final DownloadCustomerExportChunkRequest.Builder pojoBuilder;
    private DownloadCustomerExportChunkRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public DownloadCustomerExportChunkRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = DownloadCustomerExportChunkRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public DownloadCustomerExportChunkRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public DownloadCustomerExportChunkRequestBuilder exportId(@Nonnull String exportId) {
        this.pojoBuilder.exportId(exportId);
        this._setterCalled = true;
        return this;
    }

    public DownloadCustomerExportChunkRequestBuilder chunkIndex(long chunkIndex) {
        this.pojoBuilder.chunkIndex(chunkIndex);
        this._setterCalled = true;
        return this;
    }

    public DownloadCustomerExportChunkRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private DownloadCustomerExportChunkRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public DownloadCustomerExportChunkRequestBuilder header(String name, String value) {
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
    public DownloadCustomerExportChunkResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<DownloadCustomerExportChunkRequest, DownloadCustomerExportChunkResponse> operation =
                new DownloadCustomerExportChunk.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
