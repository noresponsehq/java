package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.RemoveMemberRequest;
import org.noresponse.operations.RemoveMember;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class RemoveMemberRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final RemoveMemberRequest.Builder pojoBuilder;
    private RemoveMemberRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public RemoveMemberRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = RemoveMemberRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public RemoveMemberRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public RemoveMemberRequestBuilder memberId(@Nonnull String memberId) {
        this.pojoBuilder.memberId(memberId);
        this._setterCalled = true;
        return this;
    }

    public RemoveMemberRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private RemoveMemberRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public RemoveMemberRequestBuilder header(String name, String value) {
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
    public CompletableFuture<RemoveMemberResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<RemoveMemberRequest, RemoveMemberResponse> operation =
                new RemoveMember.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
