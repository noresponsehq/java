package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AuditStreamReplayRequest;
import org.noresponse.models.shared.Environment;
import org.noresponse.operations.ReplayAuditStream;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ReplayAuditStreamRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ReplayAuditStreamRequest.Builder pojoBuilder;
    private ReplayAuditStreamRequest request;
    private boolean _setterCalled;

    public ReplayAuditStreamRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ReplayAuditStreamRequest.builder();
    }

    public ReplayAuditStreamRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public ReplayAuditStreamRequestBuilder environment(@Nonnull Environment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public ReplayAuditStreamRequestBuilder endpointId(@Nonnull String endpointId) {
        this.pojoBuilder.endpointId(endpointId);
        this._setterCalled = true;
        return this;
    }

    public ReplayAuditStreamRequestBuilder body(@Nonnull AuditStreamReplayRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private ReplayAuditStreamRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ReplayAuditStreamRequestBuilder header(String name, String value) {
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
    public ReplayAuditStreamResponse call() {
        RequestOperation<ReplayAuditStreamRequest, ReplayAuditStreamResponse> operation =
                new ReplayAuditStream.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
