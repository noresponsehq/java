package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AttendeeFlagged
 *
 * <p>A screening recorded a finding on an attendee. It says which attendee and how severe, not what they
 * posted, who they are or why: GET the attendee report for the findings. Sent once per screening that
 * flags the attendee, so a re-screen of someone on watch announces them again.
 */
public class AttendeeFlagged {
    /**
     * The attendee's event.
     */
    @JsonProperty("event_id")
    private String eventId;

    /**
     * The flagged attendee.
     */
    @JsonProperty("attendee_id")
    private String attendeeId;

    /**
     * The screening that flagged them.
     */
    @JsonProperty("screening_id")
    private String screeningId;

    @JsonProperty("highest_band")
    private HighestBand highestBand;

    /**
     * How sure the screening is that the accounts it read are the attendee's. A finding on a possible
     * match is at most moderate, on a probable match at most high.
     */
    @JsonProperty("match_state")
    private AttendeeFlaggedMatchState matchState;

    /**
     * When the screening recorded the finding; equal to the event's created_at.
     */
    @JsonProperty("flagged_at")
    private String flaggedAt;

    @JsonCreator
    public AttendeeFlagged(
            @JsonProperty("event_id") @Nonnull String eventId,
            @JsonProperty("attendee_id") @Nonnull String attendeeId,
            @JsonProperty("screening_id") @Nonnull String screeningId,
            @JsonProperty("highest_band") @Nonnull HighestBand highestBand,
            @JsonProperty("match_state") @Nonnull AttendeeFlaggedMatchState matchState,
            @JsonProperty("flagged_at") @Nonnull String flaggedAt) {
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.attendeeId = Optional.ofNullable(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("attendeeId cannot be null"));
        this.screeningId = Optional.ofNullable(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("screeningId cannot be null"));
        this.highestBand = Optional.ofNullable(highestBand)
                .orElseThrow(() -> new IllegalArgumentException("highestBand cannot be null"));
        this.matchState = Optional.ofNullable(matchState)
                .orElseThrow(() -> new IllegalArgumentException("matchState cannot be null"));
        this.flaggedAt = Optional.ofNullable(flaggedAt)
                .orElseThrow(() -> new IllegalArgumentException("flaggedAt cannot be null"));
    }

    /**
     * The attendee's event.
     */
    public String eventId() {
        return this.eventId;
    }

    /**
     * The flagged attendee.
     */
    public String attendeeId() {
        return this.attendeeId;
    }

    /**
     * The screening that flagged them.
     */
    public String screeningId() {
        return this.screeningId;
    }

    public HighestBand highestBand() {
        return this.highestBand;
    }

    /**
     * How sure the screening is that the accounts it read are the attendee's. A finding on a possible
     * match is at most moderate, on a probable match at most high.
     */
    public AttendeeFlaggedMatchState matchState() {
        return this.matchState;
    }

    /**
     * When the screening recorded the finding; equal to the event's created_at.
     */
    public String flaggedAt() {
        return this.flaggedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The attendee's event.
     */
    public AttendeeFlagged withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    /**
     * The flagged attendee.
     */
    public AttendeeFlagged withAttendeeId(@Nonnull String attendeeId) {
        this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
        return this;
    }

    /**
     * The screening that flagged them.
     */
    public AttendeeFlagged withScreeningId(@Nonnull String screeningId) {
        this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
        return this;
    }

    public AttendeeFlagged withHighestBand(@Nonnull HighestBand highestBand) {
        this.highestBand = Utils.checkNotNull(highestBand, "highestBand");
        return this;
    }

    /**
     * How sure the screening is that the accounts it read are the attendee's. A finding on a possible
     * match is at most moderate, on a probable match at most high.
     */
    public AttendeeFlagged withMatchState(@Nonnull AttendeeFlaggedMatchState matchState) {
        this.matchState = Utils.checkNotNull(matchState, "matchState");
        return this;
    }

    /**
     * When the screening recorded the finding; equal to the event's created_at.
     */
    public AttendeeFlagged withFlaggedAt(@Nonnull String flaggedAt) {
        this.flaggedAt = Utils.checkNotNull(flaggedAt, "flaggedAt");
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
        AttendeeFlagged other = (AttendeeFlagged) o;
        return Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.attendeeId, other.attendeeId)
                && Utils.enhancedDeepEquals(this.screeningId, other.screeningId)
                && Utils.enhancedDeepEquals(this.highestBand, other.highestBand)
                && Utils.enhancedDeepEquals(this.matchState, other.matchState)
                && Utils.enhancedDeepEquals(this.flaggedAt, other.flaggedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(eventId, attendeeId, screeningId, highestBand, matchState, flaggedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AttendeeFlagged.class,
                "eventId",
                eventId,
                "attendeeId",
                attendeeId,
                "screeningId",
                screeningId,
                "highestBand",
                highestBand,
                "matchState",
                matchState,
                "flaggedAt",
                flaggedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String eventId;

        private String attendeeId;

        private String screeningId;

        private HighestBand highestBand;

        private AttendeeFlaggedMatchState matchState;

        private String flaggedAt;

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
         * The flagged attendee.
         */
        public Builder attendeeId(@Nonnull String attendeeId) {
            this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
            return this;
        }

        /**
         * The screening that flagged them.
         */
        public Builder screeningId(@Nonnull String screeningId) {
            this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
            return this;
        }

        public Builder highestBand(@Nonnull HighestBand highestBand) {
            this.highestBand = Utils.checkNotNull(highestBand, "highestBand");
            return this;
        }

        /**
         * How sure the screening is that the accounts it read are the attendee's. A finding on a possible
         * match is at most moderate, on a probable match at most high.
         */
        public Builder matchState(@Nonnull AttendeeFlaggedMatchState matchState) {
            this.matchState = Utils.checkNotNull(matchState, "matchState");
            return this;
        }

        /**
         * When the screening recorded the finding; equal to the event's created_at.
         */
        public Builder flaggedAt(@Nonnull String flaggedAt) {
            this.flaggedAt = Utils.checkNotNull(flaggedAt, "flaggedAt");
            return this;
        }

        public AttendeeFlagged build() {
            return new AttendeeFlagged(eventId, attendeeId, screeningId, highestBand, matchState, flaggedAt);
        }
    }
}
