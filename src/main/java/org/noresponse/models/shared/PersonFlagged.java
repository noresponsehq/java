package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PersonFlagged
 *
 * <p>A screening recorded a finding on a person. It says which person and how severe, not what they
 * posted, who they are or why: GET the person report for the findings. Sent once per screening that
 * flags the person, so a re-screen of someone on watch announces them again.
 */
public class PersonFlagged {
    /**
     * The person's group.
     */
    @JsonProperty("group_id")
    private String groupId;

    /**
     * The flagged person.
     */
    @JsonProperty("person_id")
    private String personId;

    /**
     * The screening that flagged them.
     */
    @JsonProperty("screening_id")
    private String screeningId;

    @JsonProperty("highest_band")
    private HighestBand highestBand;

    /**
     * How sure the screening is that the accounts it read are the person's. A finding on a possible match
     * is at most moderate, on a probable match at most high.
     */
    @JsonProperty("match_state")
    private PersonFlaggedMatchState matchState;

    /**
     * When the screening recorded the finding; equal to the event's created_at.
     */
    @JsonProperty("flagged_at")
    private String flaggedAt;

    @JsonCreator
    public PersonFlagged(
            @JsonProperty("group_id") @Nonnull String groupId,
            @JsonProperty("person_id") @Nonnull String personId,
            @JsonProperty("screening_id") @Nonnull String screeningId,
            @JsonProperty("highest_band") @Nonnull HighestBand highestBand,
            @JsonProperty("match_state") @Nonnull PersonFlaggedMatchState matchState,
            @JsonProperty("flagged_at") @Nonnull String flaggedAt) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
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
     * The person's group.
     */
    public String groupId() {
        return this.groupId;
    }

    /**
     * The flagged person.
     */
    public String personId() {
        return this.personId;
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
     * How sure the screening is that the accounts it read are the person's. A finding on a possible match
     * is at most moderate, on a probable match at most high.
     */
    public PersonFlaggedMatchState matchState() {
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
     * The person's group.
     */
    public PersonFlagged withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    /**
     * The flagged person.
     */
    public PersonFlagged withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    /**
     * The screening that flagged them.
     */
    public PersonFlagged withScreeningId(@Nonnull String screeningId) {
        this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
        return this;
    }

    public PersonFlagged withHighestBand(@Nonnull HighestBand highestBand) {
        this.highestBand = Utils.checkNotNull(highestBand, "highestBand");
        return this;
    }

    /**
     * How sure the screening is that the accounts it read are the person's. A finding on a possible match
     * is at most moderate, on a probable match at most high.
     */
    public PersonFlagged withMatchState(@Nonnull PersonFlaggedMatchState matchState) {
        this.matchState = Utils.checkNotNull(matchState, "matchState");
        return this;
    }

    /**
     * When the screening recorded the finding; equal to the event's created_at.
     */
    public PersonFlagged withFlaggedAt(@Nonnull String flaggedAt) {
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
        PersonFlagged other = (PersonFlagged) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.screeningId, other.screeningId)
                && Utils.enhancedDeepEquals(this.highestBand, other.highestBand)
                && Utils.enhancedDeepEquals(this.matchState, other.matchState)
                && Utils.enhancedDeepEquals(this.flaggedAt, other.flaggedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, personId, screeningId, highestBand, matchState, flaggedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonFlagged.class,
                "groupId",
                groupId,
                "personId",
                personId,
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

        private String groupId;

        private String personId;

        private String screeningId;

        private HighestBand highestBand;

        private PersonFlaggedMatchState matchState;

        private String flaggedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The person's group.
         */
        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        /**
         * The flagged person.
         */
        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
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
         * How sure the screening is that the accounts it read are the person's. A finding on a possible match
         * is at most moderate, on a probable match at most high.
         */
        public Builder matchState(@Nonnull PersonFlaggedMatchState matchState) {
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

        public PersonFlagged build() {
            return new PersonFlagged(groupId, personId, screeningId, highestBand, matchState, flaggedAt);
        }
    }
}
