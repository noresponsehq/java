package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.JudgeWorkspacePersonRecordRequest;
import org.noresponse.models.shared.RecordVerdictRequest;
import org.noresponse.operations.JudgeWorkspacePersonRecord;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class JudgeWorkspacePersonRecordRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final JudgeWorkspacePersonRecordRequest.Builder pojoBuilder;
    private JudgeWorkspacePersonRecordRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public JudgeWorkspacePersonRecordRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = JudgeWorkspacePersonRecordRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public JudgeWorkspacePersonRecordRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public JudgeWorkspacePersonRecordRequestBuilder body(@Nonnull RecordVerdictRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public JudgeWorkspacePersonRecordRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private JudgeWorkspacePersonRecordRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public JudgeWorkspacePersonRecordRequestBuilder header(String name, String value) {
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
    public CompletableFuture<JudgeWorkspacePersonRecordResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<JudgeWorkspacePersonRecordRequest, JudgeWorkspacePersonRecordResponse> operation =
                new JudgeWorkspacePersonRecord.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
