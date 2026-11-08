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
 * AttendeeReviewRequest
 *
 * <p>Records a decision on an attendee.
 */
public class AttendeeReviewRequest {

    @JsonProperty("decision")
    private AttendeeDecision decision;

    /**
     * Why, for the venue team and later reviewers.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("note")
    private String note;

    @JsonCreator
    public AttendeeReviewRequest(
            @JsonProperty("decision") @Nonnull AttendeeDecision decision, @JsonProperty("note") @Nullable String note) {
        this.decision = Optional.ofNullable(decision)
                .orElseThrow(() -> new IllegalArgumentException("decision cannot be null"));
        this.note = note;
    }

    public AttendeeReviewRequest(@Nonnull AttendeeDecision decision) {
        this(decision, null);
    }

    public AttendeeDecision decision() {
        return this.decision;
    }

    /**
     * Why, for the venue team and later reviewers.
     */
    public Optional<String> note() {
        return Optional.ofNullable(this.note);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AttendeeReviewRequest withDecision(@Nonnull AttendeeDecision decision) {
        this.decision = Utils.checkNotNull(decision, "decision");
        return this;
    }

    /**
     * Why, for the venue team and later reviewers.
     */
    public AttendeeReviewRequest withNote(@Nullable String note) {
        this.note = note;
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
        AttendeeReviewRequest other = (AttendeeReviewRequest) o;
        return Utils.enhancedDeepEquals(this.decision, other.decision) && Utils.enhancedDeepEquals(this.note, other.note);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(decision, note);
    }

    @Override
    public String toString() {
        return Utils.toString(AttendeeReviewRequest.class, "decision", decision, "note", note);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AttendeeDecision decision;

        private String note;

        private Builder() {
            // force use of static builder() method
        }

        public Builder decision(@Nonnull AttendeeDecision decision) {
            this.decision = Utils.checkNotNull(decision, "decision");
            return this;
        }

        /**
         * Why, for the venue team and later reviewers.
         */
        public Builder note(@Nullable String note) {
            this.note = note;
            return this;
        }

        public AttendeeReviewRequest build() {
            return new AttendeeReviewRequest(decision, note);
        }
    }
}
