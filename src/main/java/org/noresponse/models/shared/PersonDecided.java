package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PersonDecided
 *
 * <p>A reviewer recorded a decision on a person. The decision's note stays in the API. Sent once per
 * recorded review; a person's latest review is its decision, so order by decided_at.
 */
public class PersonDecided {
    /**
     * The person's group.
     */
    @JsonProperty("group_id")
    private String groupId;

    /**
     * The person decided on.
     */
    @JsonProperty("person_id")
    private String personId;

    /**
     * The recorded review.
     */
    @JsonProperty("review_id")
    private String reviewId;

    @JsonProperty("decision")
    private PersonDecision decision;

    /**
     * Who decided: the member's external user id, or the API key's label, as the person's decided_by.
     */
    @JsonProperty("decided_by")
    private String decidedBy;

    /**
     * When the decision was recorded; equal to the event's created_at.
     */
    @JsonProperty("decided_at")
    private String decidedAt;

    @JsonCreator
    public PersonDecided(
            @JsonProperty("group_id") @Nonnull String groupId,
            @JsonProperty("person_id") @Nonnull String personId,
            @JsonProperty("review_id") @Nonnull String reviewId,
            @JsonProperty("decision") @Nonnull PersonDecision decision,
            @JsonProperty("decided_by") @Nonnull String decidedBy,
            @JsonProperty("decided_at") @Nonnull String decidedAt) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
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
     * The person's group.
     */
    public String groupId() {
        return this.groupId;
    }

    /**
     * The person decided on.
     */
    public String personId() {
        return this.personId;
    }

    /**
     * The recorded review.
     */
    public String reviewId() {
        return this.reviewId;
    }

    public PersonDecision decision() {
        return this.decision;
    }

    /**
     * Who decided: the member's external user id, or the API key's label, as the person's decided_by.
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
     * The person's group.
     */
    public PersonDecided withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    /**
     * The person decided on.
     */
    public PersonDecided withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    /**
     * The recorded review.
     */
    public PersonDecided withReviewId(@Nonnull String reviewId) {
        this.reviewId = Utils.checkNotNull(reviewId, "reviewId");
        return this;
    }

    public PersonDecided withDecision(@Nonnull PersonDecision decision) {
        this.decision = Utils.checkNotNull(decision, "decision");
        return this;
    }

    /**
     * Who decided: the member's external user id, or the API key's label, as the person's decided_by.
     */
    public PersonDecided withDecidedBy(@Nonnull String decidedBy) {
        this.decidedBy = Utils.checkNotNull(decidedBy, "decidedBy");
        return this;
    }

    /**
     * When the decision was recorded; equal to the event's created_at.
     */
    public PersonDecided withDecidedAt(@Nonnull String decidedAt) {
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
        PersonDecided other = (PersonDecided) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.reviewId, other.reviewId)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.decidedBy, other.decidedBy)
                && Utils.enhancedDeepEquals(this.decidedAt, other.decidedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, personId, reviewId, decision, decidedBy, decidedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonDecided.class,
                "groupId",
                groupId,
                "personId",
                personId,
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

        private String groupId;

        private String personId;

        private String reviewId;

        private PersonDecision decision;

        private String decidedBy;

        private String decidedAt;

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
         * The person decided on.
         */
        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        /**
         * The recorded review.
         */
        public Builder reviewId(@Nonnull String reviewId) {
            this.reviewId = Utils.checkNotNull(reviewId, "reviewId");
            return this;
        }

        public Builder decision(@Nonnull PersonDecision decision) {
            this.decision = Utils.checkNotNull(decision, "decision");
            return this;
        }

        /**
         * Who decided: the member's external user id, or the API key's label, as the person's decided_by.
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

        public PersonDecided build() {
            return new PersonDecided(groupId, personId, reviewId, decision, decidedBy, decidedAt);
        }
    }
}
