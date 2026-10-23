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
 * AssessmentOutcomeRequest
 *
 * <p>POST /v1/assessments/{assessment_id}/outcomes body: what the provider later learned about the
 * account it assessed. The assessment is named by the path and the workspace by the API key, so
 * neither is a body member. Idempotency-Key is required and binds the body as it does on POST
 * /v1/assessments.
 */
public class AssessmentOutcomeRequest {
    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    @JsonProperty("outcome")
    private Outcome outcome;

    /**
     * When the provider observed the outcome. Defaults to when NoResponse received it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("observed_at")
    private String observedAt;

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_ref")
    private String externalRef;

    /**
     * Free text for your own records, at most 500 characters. Stored only; it is left out of scoring and
     * answers. Keep personal data out of it.
     *
     * <p>Control characters other than tab and line breaks are refused.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("notes")
    private String notes;

    @JsonCreator
    public AssessmentOutcomeRequest(
            @JsonProperty("outcome") @Nonnull Outcome outcome,
            @JsonProperty("observed_at") @Nullable String observedAt,
            @JsonProperty("external_ref") @Nullable String externalRef,
            @JsonProperty("notes") @Nullable String notes) {
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.observedAt = observedAt;
        this.externalRef = externalRef;
        this.notes = notes;
    }

    public AssessmentOutcomeRequest(@Nonnull Outcome outcome) {
        this(outcome, null, null, null);
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

    /**
     * When the provider observed the outcome. Defaults to when NoResponse received it.
     */
    public Optional<String> observedAt() {
        return Optional.ofNullable(this.observedAt);
    }

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    public Optional<String> externalRef() {
        return Optional.ofNullable(this.externalRef);
    }

    /**
     * Free text for your own records, at most 500 characters. Stored only; it is left out of scoring and
     * answers. Keep personal data out of it.
     *
     * <p>Control characters other than tab and line breaks are refused.
     */
    public Optional<String> notes() {
        return Optional.ofNullable(this.notes);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public AssessmentOutcomeRequest withOutcome(@Nonnull Outcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    /**
     * When the provider observed the outcome. Defaults to when NoResponse received it.
     */
    public AssessmentOutcomeRequest withObservedAt(@Nullable String observedAt) {
        this.observedAt = observedAt;
        return this;
    }

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    public AssessmentOutcomeRequest withExternalRef(@Nullable String externalRef) {
        this.externalRef = externalRef;
        return this;
    }

    /**
     * Free text for your own records, at most 500 characters. Stored only; it is left out of scoring and
     * answers. Keep personal data out of it.
     *
     * <p>Control characters other than tab and line breaks are refused.
     */
    public AssessmentOutcomeRequest withNotes(@Nullable String notes) {
        this.notes = notes;
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
        AssessmentOutcomeRequest other = (AssessmentOutcomeRequest) o;
        return Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.observedAt, other.observedAt)
                && Utils.enhancedDeepEquals(this.externalRef, other.externalRef)
                && Utils.enhancedDeepEquals(this.notes, other.notes);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(outcome, observedAt, externalRef, notes);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentOutcomeRequest.class,
                "outcome",
                outcome,
                "observedAt",
                observedAt,
                "externalRef",
                externalRef,
                "notes",
                notes);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Outcome outcome;

        private String observedAt;

        private String externalRef;

        private String notes;

        private Builder() {
            // force use of static builder() method
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

        /**
         * When the provider observed the outcome. Defaults to when NoResponse received it.
         */
        public Builder observedAt(@Nullable String observedAt) {
            this.observedAt = observedAt;
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

        /**
         * Free text for your own records, at most 500 characters. Stored only; it is left out of scoring and
         * answers. Keep personal data out of it.
         *
         * <p>Control characters other than tab and line breaks are refused.
         */
        public Builder notes(@Nullable String notes) {
            this.notes = notes;
            return this;
        }

        public AssessmentOutcomeRequest build() {
            return new AssessmentOutcomeRequest(outcome, observedAt, externalRef, notes);
        }
    }
}
