package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AuditStreamReplayRequest {
    /**
     * Opaque, workspace-scoped webhook event id. A redelivery of the same event carries the same event_id,
     * so consumers deduplicate on it.
     */
    @JsonProperty("event_id")
    private String eventId;

    @JsonCreator
    public AuditStreamReplayRequest(@JsonProperty("event_id") @Nonnull String eventId) {
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
    }

    /**
     * Opaque, workspace-scoped webhook event id. A redelivery of the same event carries the same event_id,
     * so consumers deduplicate on it.
     */
    public String eventId() {
        return this.eventId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, workspace-scoped webhook event id. A redelivery of the same event carries the same event_id,
     * so consumers deduplicate on it.
     */
    public AuditStreamReplayRequest withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
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
        AuditStreamReplayRequest other = (AuditStreamReplayRequest) o;
        return Utils.enhancedDeepEquals(this.eventId, other.eventId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(eventId);
    }

    @Override
    public String toString() {
        return Utils.toString(AuditStreamReplayRequest.class, "eventId", eventId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String eventId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, workspace-scoped webhook event id. A redelivery of the same event carries the same event_id,
         * so consumers deduplicate on it.
         */
        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        public AuditStreamReplayRequest build() {
            return new AuditStreamReplayRequest(eventId);
        }
    }
}
