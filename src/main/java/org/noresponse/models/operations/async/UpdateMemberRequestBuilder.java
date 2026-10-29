package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.UpdateMemberRequest;
import org.noresponse.models.shared.MemberUpdateRequest;
import org.noresponse.operations.UpdateMember;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class UpdateMemberRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final UpdateMemberRequest.Builder pojoBuilder;
    private UpdateMemberRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public UpdateMemberRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = UpdateMemberRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public UpdateMemberRequestBuilder workspaceId(@Nonnull String workspaceId) {
        this.pojoBuilder.workspaceId(workspaceId);
        this._setterCalled = true;
        return this;
    }

    public UpdateMemberRequestBuilder memberId(@Nonnull String memberId) {
        this.pojoBuilder.memberId(memberId);
        this._setterCalled = true;
        return this;
    }

    public UpdateMemberRequestBuilder body(@Nonnull MemberUpdateRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public UpdateMemberRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private UpdateMemberRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public UpdateMemberRequestBuilder header(String name, String value) {
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
    public CompletableFuture<UpdateMemberResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<UpdateMemberRequest, UpdateMemberResponse> operation =
                new UpdateMember.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
