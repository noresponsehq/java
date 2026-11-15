package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.JudgeRecordRequest;
import org.noresponse.models.shared.RecordVerdictRequest;
import org.noresponse.operations.JudgeRecord;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

public class JudgeRecordRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final JudgeRecordRequest.Builder pojoBuilder;
    private JudgeRecordRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public JudgeRecordRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = JudgeRecordRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public JudgeRecordRequestBuilder groupId(@Nonnull String groupId) {
        this.pojoBuilder.groupId(groupId);
        this._setterCalled = true;
        return this;
    }

    public JudgeRecordRequestBuilder personId(@Nonnull String personId) {
        this.pojoBuilder.personId(personId);
        this._setterCalled = true;
        return this;
    }

    public JudgeRecordRequestBuilder body(@Nonnull RecordVerdictRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    public JudgeRecordRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private JudgeRecordRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public JudgeRecordRequestBuilder header(String name, String value) {
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
    public CompletableFuture<JudgeRecordResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<JudgeRecordRequest, JudgeRecordResponse> operation =
                new JudgeRecord.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }
}
