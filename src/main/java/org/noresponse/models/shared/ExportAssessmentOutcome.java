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
 * ExportAssessmentOutcome
 *
 * <p>Structured fields of a retained per-assessment outcome. Legacy notes remain private as promised by
 * the existing outcome contract.
 */
public class ExportAssessmentOutcome {
    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    @JsonProperty("outcome")
    private Outcome outcome;

    @JsonProperty("observed_at")
    private String observedAt;

    @JsonProperty("received_at")
    private String receivedAt;

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_ref")
    private String externalRef;

    @JsonCreator
    public ExportAssessmentOutcome(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("outcome") @Nonnull Outcome outcome,
            @JsonProperty("observed_at") @Nonnull String observedAt,
            @JsonProperty("received_at") @Nonnull String receivedAt,
            @JsonProperty("external_ref") @Nullable String externalRef) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.observedAt = Optional.ofNullable(observedAt)
                .orElseThrow(() -> new IllegalArgumentException("observedAt cannot be null"));
        this.receivedAt = Optional.ofNullable(receivedAt)
                .orElseThrow(() -> new IllegalArgumentException("receivedAt cannot be null"));
        this.externalRef = externalRef;
    }

    public ExportAssessmentOutcome(
            @Nonnull String id,
            @Nonnull String assessmentId,
            @Nonnull Outcome outcome,
            @Nonnull String observedAt,
            @Nonnull String receivedAt) {
        this(id, assessmentId, outcome, observedAt, receivedAt, null);
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public String id() {
        return this.id;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public Outcome outcome() {
        return this.outcome;
    }

    public String observedAt() {
        return this.observedAt;
    }

    public String receivedAt() {
        return this.receivedAt;
    }

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    public Optional<String> externalRef() {
        return Optional.ofNullable(this.externalRef);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public ExportAssessmentOutcome withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public ExportAssessmentOutcome withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public ExportAssessmentOutcome withOutcome(@Nonnull Outcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    public ExportAssessmentOutcome withObservedAt(@Nonnull String observedAt) {
        this.observedAt = Utils.checkNotNull(observedAt, "observedAt");
        return this;
    }

    public ExportAssessmentOutcome withReceivedAt(@Nonnull String receivedAt) {
        this.receivedAt = Utils.checkNotNull(receivedAt, "receivedAt");
        return this;
    }

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    public ExportAssessmentOutcome withExternalRef(@Nullable String externalRef) {
        this.externalRef = externalRef;
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
        ExportAssessmentOutcome other = (ExportAssessmentOutcome) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.observedAt, other.observedAt)
                && Utils.enhancedDeepEquals(this.receivedAt, other.receivedAt)
                && Utils.enhancedDeepEquals(this.externalRef, other.externalRef);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, assessmentId, outcome, observedAt, receivedAt, externalRef);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ExportAssessmentOutcome.class,
                "id",
                id,
                "assessmentId",
                assessmentId,
                "outcome",
                outcome,
                "observedAt",
                observedAt,
                "receivedAt",
                receivedAt,
                "externalRef",
                externalRef);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String assessmentId;

        private Outcome outcome;

        private String observedAt;

        private String receivedAt;

        private String externalRef;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
         * Unique within the workspace.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
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
         * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
         * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
         * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
         * recorded for an assessment wins.
         */
        public Builder outcome(@Nonnull Outcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        public Builder observedAt(@Nonnull String observedAt) {
            this.observedAt = Utils.checkNotNull(observedAt, "observedAt");
            return this;
        }

        public Builder receivedAt(@Nonnull String receivedAt) {
            this.receivedAt = Utils.checkNotNull(receivedAt, "receivedAt");
            return this;
        }

        /**
         * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
         * interpreted; keep direct identifiers out of it.
         */
        public Builder externalRef(@Nullable String externalRef) {
            this.externalRef = externalRef;
            return this;
        }

        public ExportAssessmentOutcome build() {
            return new ExportAssessmentOutcome(id, assessmentId, outcome, observedAt, receivedAt, externalRef);
        }
    }
}
