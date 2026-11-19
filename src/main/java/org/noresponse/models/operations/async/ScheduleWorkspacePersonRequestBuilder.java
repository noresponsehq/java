package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ScheduleWorkspacePersonRequest;
import org.noresponse.models.shared.PersonScheduleRequest;
import org.noresponse.operations.ScheduleWorkspacePerson;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class ScheduleWorkspacePersonRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ScheduleWorkspacePersonRequest.Builder pojoBuilder;
    private ScheduleWorkspacePersonRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ScheduleWorkspacePersonRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ScheduleWorkspacePersonRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ScheduleWorkspacePersonRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public ScheduleWorkspacePersonRequestBuilder body(@Nonnull PersonScheduleRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public ScheduleWorkspacePersonRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ScheduleWorkspacePersonRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ScheduleWorkspacePersonRequestBuilder header(String name, String value) {
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
    public CompletableFuture<ScheduleWorkspacePersonResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ScheduleWorkspacePersonRequest, ScheduleWorkspacePersonResponse> operation =
                new ScheduleWorkspacePerson.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
