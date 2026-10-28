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
 * AccountOutcomeRequest
 *
 * <p>Record a factual report, not an enforcement action. A known outcome replaces the effective report.
 * unknown alone records uncertainty without changing it; unknown with supersedes explicitly retracts
 * it.
 *
 * <p>supersedes must name the current effective report or the request answers 409 outcome_conflict.
 * Reports remain append-only. Idempotency-Key is required.
 *
 * <p>observed_at defaults to receipt time and cannot be over five minutes ahead.
 */
public class AccountOutcomeRequest {
    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    @JsonProperty("outcome")
    private Outcome outcome;

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
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("supersedes")
    private String supersedes;

    @JsonCreator
    public AccountOutcomeRequest(
            @JsonProperty("outcome") @Nonnull Outcome outcome,
            @JsonProperty("observed_at") @Nullable String observedAt,
            @JsonProperty("external_ref") @Nullable String externalRef,
            @JsonProperty("supersedes") @Nullable String supersedes) {
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.observedAt = observedAt;
        this.externalRef = externalRef;
        this.supersedes = supersedes;
    }

    public AccountOutcomeRequest(@Nonnull Outcome outcome) {
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
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public Optional<String> supersedes() {
        return Optional.ofNullable(this.supersedes);
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
    public AccountOutcomeRequest withOutcome(@Nonnull Outcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    public AccountOutcomeRequest withObservedAt(@Nullable String observedAt) {
        this.observedAt = observedAt;
        return this;
    }

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    public AccountOutcomeRequest withExternalRef(@Nullable String externalRef) {
        this.externalRef = externalRef;
        return this;
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public AccountOutcomeRequest withSupersedes(@Nullable String supersedes) {
        this.supersedes = supersedes;
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
        AccountOutcomeRequest other = (AccountOutcomeRequest) o;
        return Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.observedAt, other.observedAt)
                && Utils.enhancedDeepEquals(this.externalRef, other.externalRef)
                && Utils.enhancedDeepEquals(this.supersedes, other.supersedes);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(outcome, observedAt, externalRef, supersedes);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountOutcomeRequest.class,
                "outcome",
                outcome,
                "observedAt",
                observedAt,
                "externalRef",
                externalRef,
                "supersedes",
                supersedes);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Outcome outcome;

        private String observedAt;

        private String externalRef;

        private String supersedes;

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
         * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
         * Unique within the workspace.
         */
        public Builder supersedes(@Nullable String supersedes) {
            this.supersedes = supersedes;
            return this;
        }

        public AccountOutcomeRequest build() {
            return new AccountOutcomeRequest(outcome, observedAt, externalRef, supersedes);
        }
    }
}
