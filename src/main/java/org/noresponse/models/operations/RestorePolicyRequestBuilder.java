package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.PolicyRestoreRequest;
import org.noresponse.operations.RestorePolicy;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class RestorePolicyRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RestorePolicyRequest.Builder pojoBuilder;
    private RestorePolicyRequest request;
    private boolean _setterCalled;

    public RestorePolicyRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RestorePolicyRequest.builder();
    }

    public RestorePolicyRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public RestorePolicyRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public RestorePolicyRequestBuilder body(@Nonnull PolicyRestoreRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private RestorePolicyRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RestorePolicyRequestBuilder header(String name, String value) {
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
    public RestorePolicyResponse call() {
        RequestOperation<RestorePolicyRequest, RestorePolicyResponse> operation =
                new RestorePolicy.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
