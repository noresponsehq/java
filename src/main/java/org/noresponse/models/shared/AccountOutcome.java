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
 * AccountOutcome
 *
 * <p>Immutable acceptance receipt. risk_version is the account version after this report committed, not
 * the version when a retry reads it. effective_outcome_id names the effective head at that write.
 *
 * <p>The effective expiration may shorten under retention changes. A legitimate report or correction
 * removes only the account-feedback contribution, not unrelated risk.
 */
public class AccountOutcome {
    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    @JsonProperty("outcome")
    private Outcome outcome;

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("supersedes")
    private String supersedes;

    @JsonProperty("observed_at")
    private String observedAt;

    @JsonProperty("received_at")
    private String receivedAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_ref")
    private String externalRef;

    @JsonProperty("actor")
    private AccountOutcomeActor actor;

    @JsonProperty("risk_version_before")
    private long riskVersionBefore;

    @JsonProperty("risk_version")
    private long riskVersion;

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("effective_outcome_id")
    private String effectiveOutcomeId;

    @JsonCreator
    public AccountOutcome(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("outcome") @Nonnull Outcome outcome,
            @JsonProperty("supersedes") @Nullable String supersedes,
            @JsonProperty("observed_at") @Nonnull String observedAt,
            @JsonProperty("received_at") @Nonnull String receivedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("external_ref") @Nullable String externalRef,
            @JsonProperty("actor") @Nonnull AccountOutcomeActor actor,
            @JsonProperty("risk_version_before") long riskVersionBefore,
            @JsonProperty("risk_version") long riskVersion,
            @JsonProperty("effective_outcome_id") @Nullable String effectiveOutcomeId) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.supersedes = supersedes;
        this.observedAt = Optional.ofNullable(observedAt)
                .orElseThrow(() -> new IllegalArgumentException("observedAt cannot be null"));
        this.receivedAt = Optional.ofNullable(receivedAt)
                .orElseThrow(() -> new IllegalArgumentException("receivedAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.externalRef = externalRef;
        this.actor = Optional.ofNullable(actor).orElseThrow(() -> new IllegalArgumentException("actor cannot be null"));
        this.riskVersionBefore = riskVersionBefore;
        this.riskVersion = riskVersion;
        this.effectiveOutcomeId = effectiveOutcomeId;
    }

    public AccountOutcome(
            @Nonnull String id,
            @Nonnull String accountId,
            @Nonnull Outcome outcome,
            @Nonnull String observedAt,
            @Nonnull String receivedAt,
            @Nonnull String expiresAt,
            @Nonnull AccountOutcomeActor actor,
            long riskVersionBefore,
            long riskVersion) {
        this(
                id,
                accountId,
                outcome,
                null,
                observedAt,
                receivedAt,
                expiresAt,
                null,
                actor,
                riskVersionBefore,
                riskVersion,
                null);
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public String id() {
        return this.id;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public String accountId() {
        return this.accountId;
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
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public Optional<String> supersedes() {
        return Optional.ofNullable(this.supersedes);
    }

    public String observedAt() {
        return this.observedAt;
    }

    public String receivedAt() {
        return this.receivedAt;
    }

    public String expiresAt() {
        return this.expiresAt;
    }

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    public Optional<String> externalRef() {
        return Optional.ofNullable(this.externalRef);
    }

    public AccountOutcomeActor actor() {
        return this.actor;
    }

    public long riskVersionBefore() {
        return this.riskVersionBefore;
    }

    public long riskVersion() {
        return this.riskVersion;
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public Optional<String> effectiveOutcomeId() {
        return Optional.ofNullable(this.effectiveOutcomeId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public AccountOutcome withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public AccountOutcome withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public AccountOutcome withOutcome(@Nonnull Outcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public AccountOutcome withSupersedes(@Nullable String supersedes) {
        this.supersedes = supersedes;
        return this;
    }

    public AccountOutcome withObservedAt(@Nonnull String observedAt) {
        this.observedAt = Utils.checkNotNull(observedAt, "observedAt");
        return this;
    }

    public AccountOutcome withReceivedAt(@Nonnull String receivedAt) {
        this.receivedAt = Utils.checkNotNull(receivedAt, "receivedAt");
        return this;
    }

    public AccountOutcome withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    /**
     * Your own opaque reference for the outcome, such as a case or ticket id. Stored as given and not
     * interpreted; keep direct identifiers out of it.
     */
    public AccountOutcome withExternalRef(@Nullable String externalRef) {
        this.externalRef = externalRef;
        return this;
    }

    public AccountOutcome withActor(@Nonnull AccountOutcomeActor actor) {
        this.actor = Utils.checkNotNull(actor, "actor");
        return this;
    }

    public AccountOutcome withRiskVersionBefore(long riskVersionBefore) {
        this.riskVersionBefore = riskVersionBefore;
        return this;
    }

    public AccountOutcome withRiskVersion(long riskVersion) {
        this.riskVersion = riskVersion;
        return this;
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public AccountOutcome withEffectiveOutcomeId(@Nullable String effectiveOutcomeId) {
        this.effectiveOutcomeId = effectiveOutcomeId;
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
        AccountOutcome other = (AccountOutcome) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.supersedes, other.supersedes)
                && Utils.enhancedDeepEquals(this.observedAt, other.observedAt)
                && Utils.enhancedDeepEquals(this.receivedAt, other.receivedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.externalRef, other.externalRef)
                && Utils.enhancedDeepEquals(this.actor, other.actor)
                && Utils.enhancedDeepEquals(this.riskVersionBefore, other.riskVersionBefore)
                && Utils.enhancedDeepEquals(this.riskVersion, other.riskVersion)
                && Utils.enhancedDeepEquals(this.effectiveOutcomeId, other.effectiveOutcomeId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                accountId,
                outcome,
                supersedes,
                observedAt,
                receivedAt,
                expiresAt,
                externalRef,
                actor,
                riskVersionBefore,
                riskVersion,
                effectiveOutcomeId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountOutcome.class,
                "id",
                id,
                "accountId",
                accountId,
                "outcome",
                outcome,
                "supersedes",
                supersedes,
                "observedAt",
                observedAt,
                "receivedAt",
                receivedAt,
                "expiresAt",
                expiresAt,
                "externalRef",
                externalRef,
                "actor",
                actor,
                "riskVersionBefore",
                riskVersionBefore,
                "riskVersion",
                riskVersion,
                "effectiveOutcomeId",
                effectiveOutcomeId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String accountId;

        private Outcome outcome;

        private String supersedes;

        private String observedAt;

        private String receivedAt;

        private String expiresAt;

        private String externalRef;

        private AccountOutcomeActor actor;

        private long riskVersionBefore;

        private long riskVersion;

        private String effectiveOutcomeId;

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
         * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
         * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
         * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
         * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
         * internal id is a common pseudonymous scheme.
         *
         * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
         * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
         * yet, carries the server-issued account_id pre: followed by its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
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

        /**
         * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
         * Unique within the workspace.
         */
        public Builder supersedes(@Nullable String supersedes) {
            this.supersedes = supersedes;
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

        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
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

        public Builder actor(@Nonnull AccountOutcomeActor actor) {
            this.actor = Utils.checkNotNull(actor, "actor");
            return this;
        }

        public Builder riskVersionBefore(long riskVersionBefore) {
            this.riskVersionBefore = riskVersionBefore;
            return this;
        }

        public Builder riskVersion(long riskVersion) {
            this.riskVersion = riskVersion;
            return this;
        }

        /**
         * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
         * Unique within the workspace.
         */
        public Builder effectiveOutcomeId(@Nullable String effectiveOutcomeId) {
            this.effectiveOutcomeId = effectiveOutcomeId;
            return this;
        }

        public AccountOutcome build() {
            return new AccountOutcome(
                    id,
                    accountId,
                    outcome,
                    supersedes,
                    observedAt,
                    receivedAt,
                    expiresAt,
                    externalRef,
                    actor,
                    riskVersionBefore,
                    riskVersion,
                    effectiveOutcomeId);
        }
    }
}
