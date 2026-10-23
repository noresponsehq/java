package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookReplay
 *
 * <p>The 202 answer of POST /v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay: the event queued to
 * be sent again.
 */
public class WebhookReplay {
    /**
     * Opaque id of the endpoint registration.
     */
    @JsonProperty("endpoint_id")
    private String endpointId;

    /**
     * The replayed event's id. The receiver sees the same id and body as the first time, with a new
     * NR-Webhook-Timestamp and signature.
     */
    @JsonProperty("event_id")
    private String eventId;

    /**
     * The event's type, as its body's type: assessment.updated, account.deleted or account.risk_changed.
     */
    @JsonProperty("event_type")
    private String eventType;

    @JsonCreator
    public WebhookReplay(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("event_id") @Nonnull String eventId,
            @JsonProperty("event_type") @Nonnull String eventType) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.eventType = Optional.ofNullable(eventType)
                .orElseThrow(() -> new IllegalArgumentException("eventType cannot be null"));
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * The replayed event's id. The receiver sees the same id and body as the first time, with a new
     * NR-Webhook-Timestamp and signature.
     */
    public String eventId() {
        return this.eventId;
    }

    /**
     * The event's type, as its body's type: assessment.updated, account.deleted or account.risk_changed.
     */
    public String eventType() {
        return this.eventType;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public WebhookReplay withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * The replayed event's id. The receiver sees the same id and body as the first time, with a new
     * NR-Webhook-Timestamp and signature.
     */
    public WebhookReplay withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    /**
     * The event's type, as its body's type: assessment.updated, account.deleted or account.risk_changed.
     */
    public WebhookReplay withEventType(@Nonnull String eventType) {
        this.eventType = Utils.checkNotNull(eventType, "eventType");
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
        WebhookReplay other = (WebhookReplay) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.eventType, other.eventType);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, eventId, eventType);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookReplay.class, "endpointId", endpointId, "eventId", eventId, "eventType", eventType);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private String eventId;

        private String eventType;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque id of the endpoint registration.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        /**
         * The replayed event's id. The receiver sees the same id and body as the first time, with a new
         * NR-Webhook-Timestamp and signature.
         */
        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        /**
         * The event's type, as its body's type: assessment.updated, account.deleted or account.risk_changed.
         */
        public Builder eventType(@Nonnull String eventType) {
            this.eventType = Utils.checkNotNull(eventType, "eventType");
            return this;
        }

        public WebhookReplay build() {
            return new WebhookReplay(endpointId, eventId, eventType);
        }
    }
}
