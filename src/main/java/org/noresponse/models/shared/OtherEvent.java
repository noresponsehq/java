package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * OtherEvent
 *
 * <p>An attendee of another event in the same environment who shares an email or phone with this one, as
 * that event recorded them.
 */
public class OtherEvent {

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("event_name")
    private String eventName;

    @JsonProperty("event_starts_at")
    private String eventStartsAt;

    /**
     * The attendee on that event's list.
     */
    @JsonProperty("attendee_id")
    private String attendeeId;

    /**
     * Which identifiers match, in this order. external_id appears only beside email or phone: the same
     * external id alone is not a match, since ticket numbers may be reused across events.
     */
    @JsonProperty("matched_on")
    private List<MatchedOn> matchedOn;

    /**
     * How sure the latest screening is that it found the attendee's accounts. unscreened until a screening
     * reaches the attendee.
     */
    @JsonProperty("match_state")
    private MatchState matchState;

    /**
     * That attendee's most severe band at that event, or null.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("highest_band")
    private JsonNullable<HighestBand> highestBand;

    /**
     * The latest reviewer decision at that event, or null before any.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("decision")
    private JsonNullable<AttendeeDecision> decision;

    /**
     * Who made that decision.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decided_by")
    private String decidedBy;

    /**
     * When that decision was made.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decided_at")
    private String decidedAt;

    @JsonCreator
    public OtherEvent(
            @JsonProperty("event_id") @Nonnull String eventId,
            @JsonProperty("event_name") @Nonnull String eventName,
            @JsonProperty("event_starts_at") @Nonnull String eventStartsAt,
            @JsonProperty("attendee_id") @Nonnull String attendeeId,
            @JsonProperty("matched_on") @Nonnull List<MatchedOn> matchedOn,
            @JsonProperty("match_state") @Nonnull MatchState matchState,
            @JsonProperty("highest_band") @Nullable HighestBand highestBand,
            @JsonProperty("decision") @Nullable AttendeeDecision decision,
            @JsonProperty("decided_by") @Nullable String decidedBy,
            @JsonProperty("decided_at") @Nullable String decidedAt) {
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.eventName = Optional.ofNullable(eventName)
                .orElseThrow(() -> new IllegalArgumentException("eventName cannot be null"));
        this.eventStartsAt = Optional.ofNullable(eventStartsAt)
                .orElseThrow(() -> new IllegalArgumentException("eventStartsAt cannot be null"));
        this.attendeeId = Optional.ofNullable(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("attendeeId cannot be null"));
        this.matchedOn = Optional.ofNullable(matchedOn)
                .orElseThrow(() -> new IllegalArgumentException("matchedOn cannot be null"));
        this.matchState = Optional.ofNullable(matchState)
                .orElseThrow(() -> new IllegalArgumentException("matchState cannot be null"));
        this.highestBand = JsonNullable.of(highestBand);
        this.decision = JsonNullable.of(decision);
        this.decidedBy = decidedBy;
        this.decidedAt = decidedAt;
    }

    public OtherEvent(
            @Nonnull String eventId,
            @Nonnull String eventName,
            @Nonnull String eventStartsAt,
            @Nonnull String attendeeId,
            @Nonnull List<MatchedOn> matchedOn,
            @Nonnull MatchState matchState) {
        this(eventId, eventName, eventStartsAt, attendeeId, matchedOn, matchState, null, null, null, null);
    }

    public String eventId() {
        return this.eventId;
    }

    public String eventName() {
        return this.eventName;
    }

    public String eventStartsAt() {
        return this.eventStartsAt;
    }

    /**
     * The attendee on that event's list.
     */
    public String attendeeId() {
        return this.attendeeId;
    }

    /**
     * Which identifiers match, in this order. external_id appears only beside email or phone: the same
     * external id alone is not a match, since ticket numbers may be reused across events.
     */
    public List<MatchedOn> matchedOn() {
        return this.matchedOn;
    }

    /**
     * How sure the latest screening is that it found the attendee's accounts. unscreened until a screening
     * reaches the attendee.
     */
    public MatchState matchState() {
        return this.matchState;
    }

    /**
     * That attendee's most severe band at that event, or null.
     */
    public JsonNullable<HighestBand> highestBand() {
        return this.highestBand;
    }

    /**
     * The latest reviewer decision at that event, or null before any.
     */
    public JsonNullable<AttendeeDecision> decision() {
        return this.decision;
    }

    /**
     * Who made that decision.
     */
    public Optional<String> decidedBy() {
        return Optional.ofNullable(this.decidedBy);
    }

    /**
     * When that decision was made.
     */
    public Optional<String> decidedAt() {
        return Optional.ofNullable(this.decidedAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    public OtherEvent withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    public OtherEvent withEventName(@Nonnull String eventName) {
        this.eventName = Utils.checkNotNull(eventName, "eventName");
        return this;
    }

    public OtherEvent withEventStartsAt(@Nonnull String eventStartsAt) {
        this.eventStartsAt = Utils.checkNotNull(eventStartsAt, "eventStartsAt");
        return this;
    }

    /**
     * The attendee on that event's list.
     */
    public OtherEvent withAttendeeId(@Nonnull String attendeeId) {
        this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
        return this;
    }

    /**
     * Which identifiers match, in this order. external_id appears only beside email or phone: the same
     * external id alone is not a match, since ticket numbers may be reused across events.
     */
    public OtherEvent withMatchedOn(@Nonnull List<MatchedOn> matchedOn) {
        this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
        return this;
    }

    /**
     * How sure the latest screening is that it found the attendee's accounts. unscreened until a screening
     * reaches the attendee.
     */
    public OtherEvent withMatchState(@Nonnull MatchState matchState) {
        this.matchState = Utils.checkNotNull(matchState, "matchState");
        return this;
    }

    /**
     * That attendee's most severe band at that event, or null.
     */
    public OtherEvent withHighestBand(@Nullable HighestBand highestBand) {
        this.highestBand = JsonNullable.of(highestBand);
        return this;
    }

    /**
     * The latest reviewer decision at that event, or null before any.
     */
    public OtherEvent withDecision(@Nullable AttendeeDecision decision) {
        this.decision = JsonNullable.of(decision);
        return this;
    }

    /**
     * Who made that decision.
     */
    public OtherEvent withDecidedBy(@Nullable String decidedBy) {
        this.decidedBy = decidedBy;
        return this;
    }

    /**
     * When that decision was made.
     */
    public OtherEvent withDecidedAt(@Nullable String decidedAt) {
        this.decidedAt = decidedAt;
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
        OtherEvent other = (OtherEvent) o;
        return Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.eventName, other.eventName)
                && Utils.enhancedDeepEquals(this.eventStartsAt, other.eventStartsAt)
                && Utils.enhancedDeepEquals(this.attendeeId, other.attendeeId)
                && Utils.enhancedDeepEquals(this.matchedOn, other.matchedOn)
                && Utils.enhancedDeepEquals(this.matchState, other.matchState)
                && Utils.enhancedDeepEquals(this.highestBand, other.highestBand)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.decidedBy, other.decidedBy)
                && Utils.enhancedDeepEquals(this.decidedAt, other.decidedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                eventId,
                eventName,
                eventStartsAt,
                attendeeId,
                matchedOn,
                matchState,
                highestBand,
                decision,
                decidedBy,
                decidedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                OtherEvent.class,
                "eventId",
                eventId,
                "eventName",
                eventName,
                "eventStartsAt",
                eventStartsAt,
                "attendeeId",
                attendeeId,
                "matchedOn",
                matchedOn,
                "matchState",
                matchState,
                "highestBand",
                highestBand,
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

        private String eventName;

        private String eventStartsAt;

        private String attendeeId;

        private List<MatchedOn> matchedOn;

        private MatchState matchState;

        private HighestBand highestBand;

        private AttendeeDecision decision;

        private String decidedBy;

        private String decidedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        public Builder eventName(@Nonnull String eventName) {
            this.eventName = Utils.checkNotNull(eventName, "eventName");
            return this;
        }

        public Builder eventStartsAt(@Nonnull String eventStartsAt) {
            this.eventStartsAt = Utils.checkNotNull(eventStartsAt, "eventStartsAt");
            return this;
        }

        /**
         * The attendee on that event's list.
         */
        public Builder attendeeId(@Nonnull String attendeeId) {
            this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
            return this;
        }

        /**
         * Which identifiers match, in this order. external_id appears only beside email or phone: the same
         * external id alone is not a match, since ticket numbers may be reused across events.
         */
        public Builder matchedOn(@Nonnull List<MatchedOn> matchedOn) {
            this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
            return this;
        }

        /**
         * How sure the latest screening is that it found the attendee's accounts. unscreened until a screening
         * reaches the attendee.
         */
        public Builder matchState(@Nonnull MatchState matchState) {
            this.matchState = Utils.checkNotNull(matchState, "matchState");
            return this;
        }

        /**
         * That attendee's most severe band at that event, or null.
         */
        public Builder highestBand(@Nullable HighestBand highestBand) {
            this.highestBand = highestBand;
            return this;
        }

        /**
         * The latest reviewer decision at that event, or null before any.
         */
        public Builder decision(@Nullable AttendeeDecision decision) {
            this.decision = decision;
            return this;
        }

        /**
         * Who made that decision.
         */
        public Builder decidedBy(@Nullable String decidedBy) {
            this.decidedBy = decidedBy;
            return this;
        }

        /**
         * When that decision was made.
         */
        public Builder decidedAt(@Nullable String decidedAt) {
            this.decidedAt = decidedAt;
            return this;
        }

        public OtherEvent build() {
            return new OtherEvent(
                    eventId,
                    eventName,
                    eventStartsAt,
                    attendeeId,
                    matchedOn,
                    matchState,
                    highestBand,
                    decision,
                    decidedBy,
                    decidedAt);
        }
    }
}
