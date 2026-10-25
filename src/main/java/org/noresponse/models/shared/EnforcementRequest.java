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
 * EnforcementRequest
 *
 * <p>POST /v1/accounts/{account_id}/enforcements body: what the workspace did to the account in its
 * identity provider after reading the account's risk at risk_version. The account is named by the path
 * and the workspace by the API key, so neither is a body member. An enforcement is the workspace's own
 * action, not an outcome: what the workspace later learned about an assessment goes to POST
 * /v1/assessments/{assessment_id}/outcomes.
 *
 * <p>The record's key is (account, risk_version, flow, action), so a retry of the same report is safe and
 * needs no Idempotency-Key.
 */
public class EnforcementRequest {
    /**
     * The account's risk_version the workspace acted on, as read from GET /v1/accounts/{account_id}, GET
     * /v1/accounts, an assessment answer or account.risk_changed. A version older than the account's
     * current one is accepted and stored; a version newer than the current one answers 409
     * risk_version_ahead.
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

    @JsonCreator
    public EnforcementRequest(
            @JsonProperty("risk_version") long riskVersion,
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("action") @Nonnull EnforcementAction action,
            @JsonProperty("state_before") @Nonnull EnforcementState stateBefore,
            @JsonProperty("status") @Nonnull EnforcementStatus status,
            @JsonProperty("provider") @Nonnull EnforcementProvider provider,
            @JsonProperty("error_code") @Nullable String errorCode) {
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
    }

    public EnforcementRequest(
            long riskVersion,
            @Nonnull Flow flow,
            @Nonnull EnforcementAction action,
            @Nonnull EnforcementState stateBefore,
            @Nonnull EnforcementStatus status,
            @Nonnull EnforcementProvider provider) {
        this(riskVersion, flow, action, stateBefore, status, provider, null);
    }

    /**
     * The account's risk_version the workspace acted on, as read from GET /v1/accounts/{account_id}, GET
     * /v1/accounts, an assessment answer or account.risk_changed. A version older than the account's
     * current one is accepted and stored; a version newer than the current one answers 409
     * risk_version_ahead.
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

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account's risk_version the workspace acted on, as read from GET /v1/accounts/{account_id}, GET
     * /v1/accounts, an assessment answer or account.risk_changed. A version older than the account's
     * current one is accepted and stored; a version newer than the current one answers 409
     * risk_version_ahead.
     */
    public EnforcementRequest withRiskVersion(long riskVersion) {
        this.riskVersion = riskVersion;
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public EnforcementRequest withFlow(@Nonnull Flow flow) {
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
    public EnforcementRequest withAction(@Nonnull EnforcementAction action) {
        this.action = Utils.checkNotNull(action, "action");
        return this;
    }

    /**
     * The account's state in the workspace's identity provider just before the action: onboarding,
     * provisional (created, not yet activated), active, restricted or rejected.
     */
    public EnforcementRequest withStateBefore(@Nonnull EnforcementState stateBefore) {
        this.stateBefore = Utils.checkNotNull(stateBefore, "stateBefore");
        return this;
    }

    /**
     * applied when the identity provider accepted the action, failed when it refused it or could not be
     * reached. Only applied counts as enforced.
     */
    public EnforcementRequest withStatus(@Nonnull EnforcementStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The identity provider the action ran in; custom for the workspace's own user store.
     */
    public EnforcementRequest withProvider(@Nonnull EnforcementProvider provider) {
        this.provider = Utils.checkNotNull(provider, "provider");
        return this;
    }

    /**
     * Why a failed action failed, as a short code of the workspace's choosing (lower-case letters, digits,
     * '_', '.' and '-'), such as the identity provider's error code. Allowed only with status failed: with
     * status applied it answers 400 invalid_request. Keep personal data out of it.
     */
    public EnforcementRequest withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
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
        EnforcementRequest other = (EnforcementRequest) o;
        return Utils.enhancedDeepEquals(this.riskVersion, other.riskVersion)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.stateBefore, other.stateBefore)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.provider, other.provider)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(riskVersion, flow, action, stateBefore, status, provider, errorCode);
    }

    @Override
    public String toString() {
        return Utils.toString(
                EnforcementRequest.class,
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
                errorCode);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long riskVersion;

        private Flow flow;

        private EnforcementAction action;

        private EnforcementState stateBefore;

        private EnforcementStatus status;

        private EnforcementProvider provider;

        private String errorCode;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account's risk_version the workspace acted on, as read from GET /v1/accounts/{account_id}, GET
         * /v1/accounts, an assessment answer or account.risk_changed. A version older than the account's
         * current one is accepted and stored; a version newer than the current one answers 409
         * risk_version_ahead.
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

        public EnforcementRequest build() {
            return new EnforcementRequest(riskVersion, flow, action, stateBefore, status, provider, errorCode);
        }
    }
}
