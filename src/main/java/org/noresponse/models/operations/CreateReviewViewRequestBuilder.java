package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewViewRequest;
import org.noresponse.operations.CreateReviewView;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class CreateReviewViewRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final CreateReviewViewRequest.Builder pojoBuilder;
    private CreateReviewViewRequest request;
    private boolean _setterCalled;

    public CreateReviewViewRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = CreateReviewViewRequest.builder();
    }

    public CreateReviewViewRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public CreateReviewViewRequestBuilder environment(@Nonnull KeyEnvironment environment) {
        this.pojoBuilder.environment(environment);
        this._setterCalled = true;
        return this;
    }

    public CreateReviewViewRequestBuilder body(@Nonnull ReviewViewRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private CreateReviewViewRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public CreateReviewViewRequestBuilder header(String name, String value) {
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
    public CreateReviewViewResponse call() {
        RequestOperation<CreateReviewViewRequest, CreateReviewViewResponse> operation =
                new CreateReviewView.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
