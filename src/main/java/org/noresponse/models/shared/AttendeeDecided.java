package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AttendeeDecided
 *
 * <p>A reviewer recorded a decision on an attendee. The decision's note stays in the API. Sent once per
 * recorded review; an attendee's latest review is its decision, so order by decided_at.
 */
public class AttendeeDecided {
    /**
     * The attendee's event.
     */
    @JsonProperty("event_id")
    private String eventId;

    /**
     * The attendee decided on.
     */
    @JsonProperty("attendee_id")
    private String attendeeId;

    /**
     * The recorded review.
     */
    @JsonProperty("review_id")
    private String reviewId;

    @JsonProperty("decision")
    private AttendeeDecision decision;

    /**
     * Who decided: the member's external user id, or the API key's label, as the attendee's decided_by.
     */
    @JsonProperty("decided_by")
    private String decidedBy;

    /**
     * When the decision was recorded; equal to the event's created_at.
     */
    @JsonProperty("decided_at")
    private String decidedAt;

    @JsonCreator
    public AttendeeDecided(
            @JsonProperty("event_id") @Nonnull String eventId,
            @JsonProperty("attendee_id") @Nonnull String attendeeId,
            @JsonProperty("review_id") @Nonnull String reviewId,
            @JsonProperty("decision") @Nonnull AttendeeDecision decision,
            @JsonProperty("decided_by") @Nonnull String decidedBy,
            @JsonProperty("decided_at") @Nonnull String decidedAt) {
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.attendeeId = Optional.ofNullable(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("attendeeId cannot be null"));
        this.reviewId = Optional.ofNullable(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("reviewId cannot be null"));
        this.decision = Optional.ofNullable(decision)
                .orElseThrow(() -> new IllegalArgumentException("decision cannot be null"));
        this.decidedBy = Optional.ofNullable(decidedBy)
                .orElseThrow(() -> new IllegalArgumentException("decidedBy cannot be null"));
        this.decidedAt = Optional.ofNullable(decidedAt)
                .orElseThrow(() -> new IllegalArgumentException("decidedAt cannot be null"));
    }

    /**
     * The attendee's event.
     */
    public String eventId() {
        return this.eventId;
    }

    /**
     * The attendee decided on.
     */
    public String attendeeId() {
        return this.attendeeId;
    }

    /**
     * The recorded review.
     */
    public String reviewId() {
        return this.reviewId;
    }

    public AttendeeDecision decision() {
        return this.decision;
    }

    /**
     * Who decided: the member's external user id, or the API key's label, as the attendee's decided_by.
     */
    public String decidedBy() {
        return this.decidedBy;
    }

    /**
     * When the decision was recorded; equal to the event's created_at.
     */
    public String decidedAt() {
        return this.decidedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The attendee's event.
     */
    public AttendeeDecided withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    /**
     * The attendee decided on.
     */
    public AttendeeDecided withAttendeeId(@Nonnull String attendeeId) {
        this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
        return this;
    }

    /**
     * The recorded review.
     */
    public AttendeeDecided withReviewId(@Nonnull String reviewId) {
        this.reviewId = Utils.checkNotNull(reviewId, "reviewId");
        return this;
    }

    public AttendeeDecided withDecision(@Nonnull AttendeeDecision decision) {
        this.decision = Utils.checkNotNull(decision, "decision");
        return this;
    }

    /**
     * Who decided: the member's external user id, or the API key's label, as the attendee's decided_by.
     */
    public AttendeeDecided withDecidedBy(@Nonnull String decidedBy) {
        this.decidedBy = Utils.checkNotNull(decidedBy, "decidedBy");
        return this;
    }

    /**
     * When the decision was recorded; equal to the event's created_at.
     */
    public AttendeeDecided withDecidedAt(@Nonnull String decidedAt) {
        this.decidedAt = Utils.checkNotNull(decidedAt, "decidedAt");
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
        AttendeeDecided other = (AttendeeDecided) o;
        return Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.attendeeId, other.attendeeId)
                && Utils.enhancedDeepEquals(this.reviewId, other.reviewId)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.decidedBy, other.decidedBy)
                && Utils.enhancedDeepEquals(this.decidedAt, other.decidedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(eventId, attendeeId, reviewId, decision, decidedBy, decidedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AttendeeDecided.class,
                "eventId",
                eventId,
                "attendeeId",
                attendeeId,
                "reviewId",
                reviewId,
                "decision",
                decision,
                "decidedBy",
                decidedBy,
                "decidedAt",
                decidedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String eventId;

        private String attendeeId;

        private String reviewId;

        private AttendeeDecision decision;

        private String decidedBy;

        private String decidedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The attendee's event.
         */
        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        /**
         * The attendee decided on.
         */
        public Builder attendeeId(@Nonnull String attendeeId) {
            this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
            return this;
        }

        /**
         * The recorded review.
         */
        public Builder reviewId(@Nonnull String reviewId) {
            this.reviewId = Utils.checkNotNull(reviewId, "reviewId");
            return this;
        }

        public Builder decision(@Nonnull AttendeeDecision decision) {
            this.decision = Utils.checkNotNull(decision, "decision");
            return this;
        }

        /**
         * Who decided: the member's external user id, or the API key's label, as the attendee's decided_by.
         */
        public Builder decidedBy(@Nonnull String decidedBy) {
            this.decidedBy = Utils.checkNotNull(decidedBy, "decidedBy");
            return this;
        }

        /**
         * When the decision was recorded; equal to the event's created_at.
         */
        public Builder decidedAt(@Nonnull String decidedAt) {
            this.decidedAt = Utils.checkNotNull(decidedAt, "decidedAt");
            return this;
        }

        public AttendeeDecided build() {
            return new AttendeeDecided(eventId, attendeeId, reviewId, decision, decidedBy, decidedAt);
        }
    }
}
