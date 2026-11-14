package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * OtherGroup
 *
 * <p>A person of another group in the same environment who shares an email or phone with this one, as
 * that group recorded them.
 */
public class OtherGroup {

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("group_starts_at")
    private String groupStartsAt;

    /**
     * The person on that group's list.
     */
    @JsonProperty("person_id")
    private String personId;

    /**
     * Which identifiers match, in this order. external_id appears only beside email or phone: the same
     * external id alone is not a match, since ticket numbers may be reused across groups.
     */
    @JsonProperty("matched_on")
    private List<MatchedOn> matchedOn;

    /**
     * How sure the latest screening is that it found the person's accounts. unscreened until a screening
     * reaches the person, and after one that had no profile to check (no_profile_to_check).
     */
    @JsonProperty("match_state")
    private MatchState matchState;

    /**
     * true when that person's latest screening had no profile to check (see the person's
     * no_profile_to_check). Left out otherwise.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("no_profile_to_check")
    private Boolean noProfileToCheck;

    /**
     * That person's most severe band in that group, or null.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("highest_band")
    private JsonNullable<HighestBand> highestBand;

    /**
     * The latest reviewer decision in that group, or null before any.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("decision")
    private JsonNullable<PersonDecision> decision;

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
    public OtherGroup(
            @JsonProperty("group_id") @Nonnull String groupId,
            @JsonProperty("group_name") @Nonnull String groupName,
            @JsonProperty("group_starts_at") @Nonnull String groupStartsAt,
            @JsonProperty("person_id") @Nonnull String personId,
            @JsonProperty("matched_on") @Nonnull List<MatchedOn> matchedOn,
            @JsonProperty("match_state") @Nonnull MatchState matchState,
            @JsonProperty("no_profile_to_check") @Nullable Boolean noProfileToCheck,
            @JsonProperty("highest_band") @Nullable HighestBand highestBand,
            @JsonProperty("decision") @Nullable PersonDecision decision,
            @JsonProperty("decided_by") @Nullable String decidedBy,
            @JsonProperty("decided_at") @Nullable String decidedAt) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.groupName = Optional.ofNullable(groupName)
                .orElseThrow(() -> new IllegalArgumentException("groupName cannot be null"));
        this.groupStartsAt = Optional.ofNullable(groupStartsAt)
                .orElseThrow(() -> new IllegalArgumentException("groupStartsAt cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.matchedOn = Optional.ofNullable(matchedOn)
                .orElseThrow(() -> new IllegalArgumentException("matchedOn cannot be null"));
        this.matchState = Optional.ofNullable(matchState)
                .orElseThrow(() -> new IllegalArgumentException("matchState cannot be null"));
        this.noProfileToCheck = noProfileToCheck;
        this.highestBand = JsonNullable.of(highestBand);
        this.decision = JsonNullable.of(decision);
        this.decidedBy = decidedBy;
        this.decidedAt = decidedAt;
    }

    public OtherGroup(
            @Nonnull String groupId,
            @Nonnull String groupName,
            @Nonnull String groupStartsAt,
            @Nonnull String personId,
            @Nonnull List<MatchedOn> matchedOn,
            @Nonnull MatchState matchState) {
        this(groupId, groupName, groupStartsAt, personId, matchedOn, matchState, null, null, null, null, null);
    }

    public String groupId() {
        return this.groupId;
    }

    public String groupName() {
        return this.groupName;
    }

    public String groupStartsAt() {
        return this.groupStartsAt;
    }

    /**
     * The person on that group's list.
     */
    public String personId() {
        return this.personId;
    }

    /**
     * Which identifiers match, in this order. external_id appears only beside email or phone: the same
     * external id alone is not a match, since ticket numbers may be reused across groups.
     */
    public List<MatchedOn> matchedOn() {
        return this.matchedOn;
    }

    /**
     * How sure the latest screening is that it found the person's accounts. unscreened until a screening
     * reaches the person, and after one that had no profile to check (no_profile_to_check).
     */
    public MatchState matchState() {
        return this.matchState;
    }

    /**
     * true when that person's latest screening had no profile to check (see the person's
     * no_profile_to_check). Left out otherwise.
     */
    public Optional<Boolean> noProfileToCheck() {
        return Optional.ofNullable(this.noProfileToCheck);
    }

    /**
     * That person's most severe band in that group, or null.
     */
    public JsonNullable<HighestBand> highestBand() {
        return this.highestBand;
    }

    /**
     * The latest reviewer decision in that group, or null before any.
     */
    public JsonNullable<PersonDecision> decision() {
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

    public OtherGroup withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public OtherGroup withGroupName(@Nonnull String groupName) {
        this.groupName = Utils.checkNotNull(groupName, "groupName");
        return this;
    }

    public OtherGroup withGroupStartsAt(@Nonnull String groupStartsAt) {
        this.groupStartsAt = Utils.checkNotNull(groupStartsAt, "groupStartsAt");
        return this;
    }

    /**
     * The person on that group's list.
     */
    public OtherGroup withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    /**
     * Which identifiers match, in this order. external_id appears only beside email or phone: the same
     * external id alone is not a match, since ticket numbers may be reused across groups.
     */
    public OtherGroup withMatchedOn(@Nonnull List<MatchedOn> matchedOn) {
        this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
        return this;
    }

    /**
     * How sure the latest screening is that it found the person's accounts. unscreened until a screening
     * reaches the person, and after one that had no profile to check (no_profile_to_check).
     */
    public OtherGroup withMatchState(@Nonnull MatchState matchState) {
        this.matchState = Utils.checkNotNull(matchState, "matchState");
        return this;
    }

    /**
     * true when that person's latest screening had no profile to check (see the person's
     * no_profile_to_check). Left out otherwise.
     */
    public OtherGroup withNoProfileToCheck(@Nullable Boolean noProfileToCheck) {
        this.noProfileToCheck = noProfileToCheck;
        return this;
    }

    /**
     * That person's most severe band in that group, or null.
     */
    public OtherGroup withHighestBand(@Nullable HighestBand highestBand) {
        this.highestBand = JsonNullable.of(highestBand);
        return this;
    }

    /**
     * The latest reviewer decision in that group, or null before any.
     */
    public OtherGroup withDecision(@Nullable PersonDecision decision) {
        this.decision = JsonNullable.of(decision);
        return this;
    }

    /**
     * Who made that decision.
     */
    public OtherGroup withDecidedBy(@Nullable String decidedBy) {
        this.decidedBy = decidedBy;
        return this;
    }

    /**
     * When that decision was made.
     */
    public OtherGroup withDecidedAt(@Nullable String decidedAt) {
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
        OtherGroup other = (OtherGroup) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.groupName, other.groupName)
                && Utils.enhancedDeepEquals(this.groupStartsAt, other.groupStartsAt)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.matchedOn, other.matchedOn)
                && Utils.enhancedDeepEquals(this.matchState, other.matchState)
                && Utils.enhancedDeepEquals(this.noProfileToCheck, other.noProfileToCheck)
                && Utils.enhancedDeepEquals(this.highestBand, other.highestBand)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.decidedBy, other.decidedBy)
                && Utils.enhancedDeepEquals(this.decidedAt, other.decidedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                groupId,
                groupName,
                groupStartsAt,
                personId,
                matchedOn,
                matchState,
                noProfileToCheck,
                highestBand,
                decision,
                decidedBy,
                decidedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                OtherGroup.class,
                "groupId",
                groupId,
                "groupName",
                groupName,
                "groupStartsAt",
                groupStartsAt,
                "personId",
                personId,
                "matchedOn",
                matchedOn,
                "matchState",
                matchState,
                "noProfileToCheck",
                noProfileToCheck,
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

        private String groupId;

        private String groupName;

        private String groupStartsAt;

        private String personId;

        private List<MatchedOn> matchedOn;

        private MatchState matchState;

        private Boolean noProfileToCheck;

        private HighestBand highestBand;

        private PersonDecision decision;

        private String decidedBy;

        private String decidedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        public Builder groupName(@Nonnull String groupName) {
            this.groupName = Utils.checkNotNull(groupName, "groupName");
            return this;
        }

        public Builder groupStartsAt(@Nonnull String groupStartsAt) {
            this.groupStartsAt = Utils.checkNotNull(groupStartsAt, "groupStartsAt");
            return this;
        }

        /**
         * The person on that group's list.
         */
        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        /**
         * Which identifiers match, in this order. external_id appears only beside email or phone: the same
         * external id alone is not a match, since ticket numbers may be reused across groups.
         */
        public Builder matchedOn(@Nonnull List<MatchedOn> matchedOn) {
            this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
            return this;
        }

        /**
         * How sure the latest screening is that it found the person's accounts. unscreened until a screening
         * reaches the person, and after one that had no profile to check (no_profile_to_check).
         */
        public Builder matchState(@Nonnull MatchState matchState) {
            this.matchState = Utils.checkNotNull(matchState, "matchState");
            return this;
        }

        /**
         * true when that person's latest screening had no profile to check (see the person's
         * no_profile_to_check). Left out otherwise.
         */
        public Builder noProfileToCheck(@Nullable Boolean noProfileToCheck) {
            this.noProfileToCheck = noProfileToCheck;
            return this;
        }

        /**
         * That person's most severe band in that group, or null.
         */
        public Builder highestBand(@Nullable HighestBand highestBand) {
            this.highestBand = highestBand;
            return this;
        }

        /**
         * The latest reviewer decision in that group, or null before any.
         */
        public Builder decision(@Nullable PersonDecision decision) {
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

        public OtherGroup build() {
            return new OtherGroup(
                    groupId,
                    groupName,
                    groupStartsAt,
                    personId,
                    matchedOn,
                    matchState,
                    noProfileToCheck,
                    highestBand,
                    decision,
                    decidedBy,
                    decidedAt);
        }
    }
}
