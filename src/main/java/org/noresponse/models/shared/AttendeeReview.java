package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AttendeeReview
 *
 * <p>One recorded decision.
 */
public class AttendeeReview {

    @JsonProperty("id")
    private String id;

    @JsonProperty("decision")
    private AttendeeDecision decision;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("note")
    private String note;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public AttendeeReview(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("decision") @Nonnull AttendeeDecision decision,
            @JsonProperty("note") @Nullable String note,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("created_at") @Nonnull String createdAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.decision = Optional.ofNullable(decision)
                .orElseThrow(() -> new IllegalArgumentException("decision cannot be null"));
        this.note = note;
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
    }

    public AttendeeReview(
            @Nonnull String id,
            @Nonnull AttendeeDecision decision,
            @Nonnull String createdBy,
            @Nonnull String createdAt) {
        this(id, decision, null, createdBy, createdAt);
    }

    public String id() {
        return this.id;
    }

    public AttendeeDecision decision() {
        return this.decision;
    }

    public Optional<String> note() {
        return Optional.ofNullable(this.note);
    }

    public String createdBy() {
        return this.createdBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AttendeeReview withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public AttendeeReview withDecision(@Nonnull AttendeeDecision decision) {
        this.decision = Utils.checkNotNull(decision, "decision");
        return this;
    }

    public AttendeeReview withNote(@Nullable String note) {
        this.note = note;
        return this;
    }

    public AttendeeReview withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public AttendeeReview withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
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
        AttendeeReview other = (AttendeeReview) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.note, other.note)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, decision, note, createdBy, createdAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AttendeeReview.class,
                "id",
                id,
                "decision",
                decision,
                "note",
                note,
                "createdBy",
                createdBy,
                "createdAt",
                createdAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private AttendeeDecision decision;

        private String note;

        private String createdBy;

        private String createdAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder decision(@Nonnull AttendeeDecision decision) {
            this.decision = Utils.checkNotNull(decision, "decision");
            return this;
        }

        public Builder note(@Nullable String note) {
            this.note = note;
            return this;
        }

        public Builder createdBy(@Nonnull String createdBy) {
            this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public AttendeeReview build() {
            return new AttendeeReview(id, decision, note, createdBy, createdAt);
        }
    }
}
