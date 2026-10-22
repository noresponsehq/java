package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.WebhookTestRequest;
import org.noresponse.operations.SendWebhookTestEvent;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class SendWebhookTestEventRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final SendWebhookTestEventRequest.Builder pojoBuilder;
    private SendWebhookTestEventRequest request;
    private boolean _setterCalled;

    public SendWebhookTestEventRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = SendWebhookTestEventRequest.builder();
    }

    public SendWebhookTestEventRequestBuilder endpointId(@Nonnull String endpointId) {
        this.pojoBuilder.endpointId(endpointId);
        this._setterCalled = true;
        return this;
    }

    public SendWebhookTestEventRequestBuilder body(@Nonnull WebhookTestRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private SendWebhookTestEventRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public SendWebhookTestEventRequestBuilder header(String name, String value) {
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
    public SendWebhookTestEventResponse call() {
        RequestOperation<SendWebhookTestEventRequest, SendWebhookTestEventResponse> operation =
                new SendWebhookTestEvent.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
