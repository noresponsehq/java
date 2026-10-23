package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.WebhookReplayRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ReplayWebhookDeliveryRequest {
    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=endpoint_id")
    private String endpointId;

    /**
     * The evt_ id of a delivery the endpoint's log holds, as GET /v1/webhooks/{endpoint_id}/deliveries
     * lists it. An id that is malformed, not sent to this endpoint, past the log's retention or another
     * tenant's answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=event_id")
    private String eventId;

    @NoResponseMetadata("request:mediaType=application/json")
    private WebhookReplayRequest body;

    @JsonCreator
    public ReplayWebhookDeliveryRequest(
            @Nonnull String endpointId, @Nonnull String eventId, @Nonnull WebhookReplayRequest body) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * The evt_ id of a delivery the endpoint's log holds, as GET /v1/webhooks/{endpoint_id}/deliveries
     * lists it. An id that is malformed, not sent to this endpoint, past the log's retention or another
     * tenant's answers 404.
     */
    public String eventId() {
        return this.eventId;
    }

    public WebhookReplayRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public ReplayWebhookDeliveryRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * The evt_ id of a delivery the endpoint's log holds, as GET /v1/webhooks/{endpoint_id}/deliveries
     * lists it. An id that is malformed, not sent to this endpoint, past the log's retention or another
     * tenant's answers 404.
     */
    public ReplayWebhookDeliveryRequest withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    public ReplayWebhookDeliveryRequest withBody(@Nonnull WebhookReplayRequest body) {
        this.body = Utils.checkNotNull(body, "body");
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReplayWebhookDeliveryRequest other = (ReplayWebhookDeliveryRequest) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, eventId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReplayWebhookDeliveryRequest.class, "endpointId", endpointId, "eventId", eventId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private String eventId;

        private WebhookReplayRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        /**
         * The evt_ id of a delivery the endpoint's log holds, as GET /v1/webhooks/{endpoint_id}/deliveries
         * lists it. An id that is malformed, not sent to this endpoint, past the log's retention or another
         * tenant's answers 404.
         */
        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        public Builder body(@Nonnull WebhookReplayRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ReplayWebhookDeliveryRequest build() {
            return new ReplayWebhookDeliveryRequest(endpointId, eventId, body);
        }
    }
}
