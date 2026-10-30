package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class IdentifierSubmission {
    /**
     * The original value submitted in assessment_id. Under canonical or mailbox matching the two accounts'
     * values can differ.
     */
    @JsonProperty("value")
    private String value;

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

    /**
     * When assessment_id was created.
     */
    @JsonProperty("submitted_at")
    private String submittedAt;

    @JsonCreator
    public IdentifierSubmission(
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("submitted_at") @Nonnull String submittedAt) {
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.submittedAt = Optional.ofNullable(submittedAt)
                .orElseThrow(() -> new IllegalArgumentException("submittedAt cannot be null"));
    }

    /**
     * The original value submitted in assessment_id. Under canonical or mailbox matching the two accounts'
     * values can differ.
     */
    public String value() {
        return this.value;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * When assessment_id was created.
     */
    public String submittedAt() {
        return this.submittedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The original value submitted in assessment_id. Under canonical or mailbox matching the two accounts'
     * values can differ.
     */
    public IdentifierSubmission withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public IdentifierSubmission withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * When assessment_id was created.
     */
    public IdentifierSubmission withSubmittedAt(@Nonnull String submittedAt) {
        this.submittedAt = Utils.checkNotNull(submittedAt, "submittedAt");
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
        IdentifierSubmission other = (IdentifierSubmission) o;
        return Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.submittedAt, other.submittedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value, assessmentId, submittedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                IdentifierSubmission.class, "value", value, "assessmentId", assessmentId, "submittedAt", submittedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String value;

        private String assessmentId;

        private String submittedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The original value submitted in assessment_id. Under canonical or mailbox matching the two accounts'
         * values can differ.
         */
        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        /**
         * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
         * within the workspace; another workspace's assessment id answers 404.
         */
        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
            return this;
        }

        /**
         * When assessment_id was created.
         */
        public Builder submittedAt(@Nonnull String submittedAt) {
            this.submittedAt = Utils.checkNotNull(submittedAt, "submittedAt");
            return this;
        }

        public IdentifierSubmission build() {
            return new IdentifierSubmission(value, assessmentId, submittedAt);
        }
    }
}
