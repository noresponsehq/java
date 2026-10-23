package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import java.lang.String;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.WebhookReplayRequest;
import org.noresponse.operations.ReplayWebhookDelivery;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

public class ReplayWebhookDeliveryRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ReplayWebhookDeliveryRequest.Builder pojoBuilder;
    private ReplayWebhookDeliveryRequest request;
    private boolean _setterCalled;

    public ReplayWebhookDeliveryRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ReplayWebhookDeliveryRequest.builder();
    }

    public ReplayWebhookDeliveryRequestBuilder endpointId(@Nonnull String endpointId) {
        this.pojoBuilder.endpointId(endpointId);
        this._setterCalled = true;
        return this;
    }

    public ReplayWebhookDeliveryRequestBuilder eventId(@Nonnull String eventId) {
        this.pojoBuilder.eventId(eventId);
        this._setterCalled = true;
        return this;
    }

    public ReplayWebhookDeliveryRequestBuilder body(@Nonnull WebhookReplayRequest body) {
        this.pojoBuilder.body(body);
        this._setterCalled = true;
        return this;
    }

    private ReplayWebhookDeliveryRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ReplayWebhookDeliveryRequestBuilder header(String name, String value) {
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
    public ReplayWebhookDeliveryResponse call() {
        RequestOperation<ReplayWebhookDeliveryRequest, ReplayWebhookDeliveryResponse> operation =
                new ReplayWebhookDelivery.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }
}
