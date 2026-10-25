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
 * Enforcement
 *
 * <p>The answer of POST /v1/accounts/{account_id}/enforcements: one enforcement of one of the calling
 * workspace's accounts, kept once per (account, risk_version, flow, action). The first report answers
 * 201 with the new record. A repeat answers 200 with the record as first stored, whatever the repeat's
 * state_before, status, provider or error_code.
 *
 * <p>The one exception is a stored failed record followed by an applied report: the record becomes
 * applied, keeps its id and created_at, takes the applied report's state_before and provider, drops
 * error_code and answers 201, so a successful retry after a failure is recorded. Only an applied
 * record at the account's current risk_version takes the account out of GET
 * /v1/accounts?unenforced=true.
 */
public class Enforcement {
    /**
     * Opaque, server-assigned id of one recorded enforcement: 'enf_' and 26 Crockford base32 characters.
     * Unique within the workspace.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The account_id of the request path.
     */
    @JsonProperty("account_id")
    private String accountId;

    /**
     * The account's risk_version the workspace acted on. It can be older than the account's current
     * risk_version: such a record is kept and does not count for unenforced.
     */
    @JsonProperty("risk_version")
    private long riskVersion;

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    @JsonProperty("flow")
    private Flow flow;

    /**
     * What the workspace did in its identity provider: revoke_sessions (signed the account out
     * everywhere), restrict (limited what the account can do), unrestrict (lifted an earlier restriction),
     * fail_onboarding (stopped an account that was still onboarding), reject_activation (refused to
     * activate a provisional account), activate (let a held onboarding or provisional account in) or none
     * (decided to take no action).
     */
    @JsonProperty("action")
    private EnforcementAction action;

    /**
     * The account's state in the workspace's identity provider just before the action: onboarding,
     * provisional (created, not yet activated), active, restricted or rejected.
     */
    @JsonProperty("state_before")
    private EnforcementState stateBefore;

    /**
     * applied when the identity provider accepted the action, failed when it refused it or could not be
     * reached. Only applied counts as enforced.
     */
    @JsonProperty("status")
    private EnforcementStatus status;

    /**
     * The identity provider the action ran in; custom for the workspace's own user store.
     */
    @JsonProperty("provider")
    private EnforcementProvider provider;

    /**
     * Why a failed action failed, as a short code of the workspace's choosing (lower-case letters, digits,
     * '_', '.' and '-'), such as the identity provider's error code. Allowed only with status failed: with
     * status applied it answers 400 invalid_request. Keep personal data out of it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * When NoResponse first recorded this enforcement, in UTC. A failed record that becomes applied keeps
     * it.
     */
    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public Enforcement(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("risk_version") long riskVersion,
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("action") @Nonnull EnforcementAction action,
            @JsonProperty("state_before") @Nonnull EnforcementState stateBefore,
            @JsonProperty("status") @Nonnull EnforcementStatus status,
            @JsonProperty("provider") @Nonnull EnforcementProvider provider,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("created_at") @Nonnull String createdAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.riskVersion = riskVersion;
        this.flow = Optional.ofNullable(flow).orElseThrow(() -> new IllegalArgumentException("flow cannot be null"));
        this.action =
                Optional.ofNullable(action).orElseThrow(() -> new IllegalArgumentException("action cannot be null"));
        this.stateBefore = Optional.ofNullable(stateBefore)
                .orElseThrow(() -> new IllegalArgumentException("stateBefore cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.provider = Optional.ofNullable(provider)
                .orElseThrow(() -> new IllegalArgumentException("provider cannot be null"));
        this.errorCode = errorCode;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
    }

    public Enforcement(
            @Nonnull String id,
            @Nonnull String accountId,
            long riskVersion,
            @Nonnull Flow flow,
            @Nonnull EnforcementAction action,
            @Nonnull EnforcementState stateBefore,
            @Nonnull EnforcementStatus status,
            @Nonnull EnforcementProvider provider,
            @Nonnull String createdAt) {
        this(id, accountId, riskVersion, flow, action, stateBefore, status, provider, null, createdAt);
    }

    /**
     * Opaque, server-assigned id of one recorded enforcement: 'enf_' and 26 Crockford base32 characters.
     * Unique within the workspace.
     */
    public String id() {
        return this.id;
    }

    /**
     * The account_id of the request path.
     */
    public String accountId() {
        return this.accountId;
    }

    /**
     * The account's risk_version the workspace acted on. It can be older than the account's current
     * risk_version: such a record is kept and does not count for unenforced.
     */
    public long riskVersion() {
        return this.riskVersion;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public Flow flow() {
        return this.flow;
    }

    /**
     * What the workspace did in its identity provider: revoke_sessions (signed the account out
     * everywhere), restrict (limited what the account can do), unrestrict (lifted an earlier restriction),
     * fail_onboarding (stopped an account that was still onboarding), reject_activation (refused to
     * activate a provisional account), activate (let a held onboarding or provisional account in) or none
     * (decided to take no action).
     */
    public EnforcementAction action() {
        return this.action;
    }

    /**
     * The account's state in the workspace's identity provider just before the action: onboarding,
     * provisional (created, not yet activated), active, restricted or rejected.
     */
    public EnforcementState stateBefore() {
        return this.stateBefore;
    }

    /**
     * applied when the identity provider accepted the action, failed when it refused it or could not be
     * reached. Only applied counts as enforced.
     */
    public EnforcementStatus status() {
        return this.status;
    }

    /**
     * The identity provider the action ran in; custom for the workspace's own user store.
     */
    public EnforcementProvider provider() {
        return this.provider;
    }

    /**
     * Why a failed action failed, as a short code of the workspace's choosing (lower-case letters, digits,
     * '_', '.' and '-'), such as the identity provider's error code. Allowed only with status failed: with
     * status applied it answers 400 invalid_request. Keep personal data out of it.
     */
    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    /**
     * When NoResponse first recorded this enforcement, in UTC. A failed record that becomes applied keeps
     * it.
     */
    public String createdAt() {
        return this.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned id of one recorded enforcement: 'enf_' and 26 Crockford base32 characters.
     * Unique within the workspace.
     */
    public Enforcement withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * The account_id of the request path.
     */
    public Enforcement withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * The account's risk_version the workspace acted on. It can be older than the account's current
     * risk_version: such a record is kept and does not count for unenforced.
     */
    public Enforcement withRiskVersion(long riskVersion) {
        this.riskVersion = riskVersion;
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public Enforcement withFlow(@Nonnull Flow flow) {
        this.flow = Utils.checkNotNull(flow, "flow");
        return this;
    }

    /**
     * What the workspace did in its identity provider: revoke_sessions (signed the account out
     * everywhere), restrict (limited what the account can do), unrestrict (lifted an earlier restriction),
     * fail_onboarding (stopped an account that was still onboarding), reject_activation (refused to
     * activate a provisional account), activate (let a held onboarding or provisional account in) or none
     * (decided to take no action).
     */
    public Enforcement withAction(@Nonnull EnforcementAction action) {
        this.action = Utils.checkNotNull(action, "action");
        return this;
    }

    /**
     * The account's state in the workspace's identity provider just before the action: onboarding,
     * provisional (created, not yet activated), active, restricted or rejected.
     */
    public Enforcement withStateBefore(@Nonnull EnforcementState stateBefore) {
        this.stateBefore = Utils.checkNotNull(stateBefore, "stateBefore");
        return this;
    }

    /**
     * applied when the identity provider accepted the action, failed when it refused it or could not be
     * reached. Only applied counts as enforced.
     */
    public Enforcement withStatus(@Nonnull EnforcementStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The identity provider the action ran in; custom for the workspace's own user store.
     */
    public Enforcement withProvider(@Nonnull EnforcementProvider provider) {
        this.provider = Utils.checkNotNull(provider, "provider");
        return this;
    }

    /**
     * Why a failed action failed, as a short code of the workspace's choosing (lower-case letters, digits,
     * '_', '.' and '-'), such as the identity provider's error code. Allowed only with status failed: with
     * status applied it answers 400 invalid_request. Keep personal data out of it.
     */
    public Enforcement withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * When NoResponse first recorded this enforcement, in UTC. A failed record that becomes applied keeps
     * it.
     */
    public Enforcement withCreatedAt(@Nonnull String createdAt) {
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
        Enforcement other = (Enforcement) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.riskVersion, other.riskVersion)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.stateBefore, other.stateBefore)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.provider, other.provider)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id, accountId, riskVersion, flow, action, stateBefore, status, provider, errorCode, createdAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Enforcement.class,
                "id",
                id,
                "accountId",
                accountId,
                "riskVersion",
                riskVersion,
                "flow",
                flow,
                "action",
                action,
                "stateBefore",
                stateBefore,
                "status",
                status,
                "provider",
                provider,
                "errorCode",
                errorCode,
                "createdAt",
                createdAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String accountId;

        private long riskVersion;

        private Flow flow;

        private EnforcementAction action;

        private EnforcementState stateBefore;

        private EnforcementStatus status;

        private EnforcementProvider provider;

        private String errorCode;

        private String createdAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned id of one recorded enforcement: 'enf_' and 26 Crockford base32 characters.
         * Unique within the workspace.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * The account_id of the request path.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        /**
         * The account's risk_version the workspace acted on. It can be older than the account's current
         * risk_version: such a record is kept and does not count for unenforced.
         */
        public Builder riskVersion(long riskVersion) {
            this.riskVersion = riskVersion;
            return this;
        }

        /**
         * The event an assessment is made for: account_creation (a user creates an account), login (a user
         * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
         * reported with the decision for this flow.
         */
        public Builder flow(@Nonnull Flow flow) {
            this.flow = Utils.checkNotNull(flow, "flow");
            return this;
        }

        /**
         * What the workspace did in its identity provider: revoke_sessions (signed the account out
         * everywhere), restrict (limited what the account can do), unrestrict (lifted an earlier restriction),
         * fail_onboarding (stopped an account that was still onboarding), reject_activation (refused to
         * activate a provisional account), activate (let a held onboarding or provisional account in) or none
         * (decided to take no action).
         */
        public Builder action(@Nonnull EnforcementAction action) {
            this.action = Utils.checkNotNull(action, "action");
            return this;
        }

        /**
         * The account's state in the workspace's identity provider just before the action: onboarding,
         * provisional (created, not yet activated), active, restricted or rejected.
         */
        public Builder stateBefore(@Nonnull EnforcementState stateBefore) {
            this.stateBefore = Utils.checkNotNull(stateBefore, "stateBefore");
            return this;
        }

        /**
         * applied when the identity provider accepted the action, failed when it refused it or could not be
         * reached. Only applied counts as enforced.
         */
        public Builder status(@Nonnull EnforcementStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        /**
         * The identity provider the action ran in; custom for the workspace's own user store.
         */
        public Builder provider(@Nonnull EnforcementProvider provider) {
            this.provider = Utils.checkNotNull(provider, "provider");
            return this;
        }

        /**
         * Why a failed action failed, as a short code of the workspace's choosing (lower-case letters, digits,
         * '_', '.' and '-'), such as the identity provider's error code. Allowed only with status failed: with
         * status applied it answers 400 invalid_request. Keep personal data out of it.
         */
        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        /**
         * When NoResponse first recorded this enforcement, in UTC. A failed record that becomes applied keeps
         * it.
         */
        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Enforcement build() {
            return new Enforcement(
                    id, accountId, riskVersion, flow, action, stateBefore, status, provider, errorCode, createdAt);
        }
    }
}
