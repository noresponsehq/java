package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AssessmentOutcome
 *
 * <p>The 202 answer of POST /v1/assessments/{assessment_id}/outcomes. A replay with the same
 * Idempotency-Key and body answers the same record.
 */
public class AssessmentOutcome {
    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the tenant.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the tenant; another tenant's assessment id answers 404.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * tenant for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    @JsonProperty("outcome")
    private Outcome outcome;

    /**
     * When NoResponse first recorded the outcome.
     */
    @JsonProperty("received_at")
    private String receivedAt;

    @JsonCreator
    public AssessmentOutcome(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("outcome") @Nonnull Outcome outcome,
            @JsonProperty("received_at") @Nonnull String receivedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.receivedAt = Optional.ofNullable(receivedAt)
                .orElseThrow(() -> new IllegalArgumentException("receivedAt cannot be null"));
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the tenant.
     */
    public String id() {
        return this.id;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the tenant; another tenant's assessment id answers 404.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * tenant for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public Outcome outcome() {
        return this.outcome;
    }

    /**
     * When NoResponse first recorded the outcome.
     */
    public String receivedAt() {
        return this.receivedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the tenant.
     */
    public AssessmentOutcome withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the tenant; another tenant's assessment id answers 404.
     */
    public AssessmentOutcome withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * tenant for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public AssessmentOutcome withOutcome(@Nonnull Outcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    /**
     * When NoResponse first recorded the outcome.
     */
    public AssessmentOutcome withReceivedAt(@Nonnull String receivedAt) {
        this.receivedAt = Utils.checkNotNull(receivedAt, "receivedAt");
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
        AssessmentOutcome other = (AssessmentOutcome) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.receivedAt, other.receivedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, assessmentId, outcome, receivedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentOutcome.class,
                "id",
                id,
                "assessmentId",
                assessmentId,
                "outcome",
                outcome,
                "receivedAt",
                receivedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String assessmentId;

        private Outcome outcome;

        private String receivedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
         * Unique within the tenant.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
         * within the tenant; another tenant's assessment id answers 404.
         */
        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
            return this;
        }

        /**
         * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
         * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
         * tenant for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
         * recorded for an assessment wins.
         */
        public Builder outcome(@Nonnull Outcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        /**
         * When NoResponse first recorded the outcome.
         */
        public Builder receivedAt(@Nonnull String receivedAt) {
            this.receivedAt = Utils.checkNotNull(receivedAt, "receivedAt");
            return this;
        }

        public AssessmentOutcome build() {
            return new AssessmentOutcome(id, assessmentId, outcome, receivedAt);
        }
    }
}
