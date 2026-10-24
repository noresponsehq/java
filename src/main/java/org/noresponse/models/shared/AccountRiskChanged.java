package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AccountRiskChanged
 *
 * <p>The account's decision for at least one flow changed. account is the account as GET
 * /v1/accounts/{account_id} answers it for the revision that made the change: its new score, decision,
 * reasons and decisions_by_flow, and the assessment the risk comes from. previous_decisions_by_flow
 * are the decisions it replaced, so account.decisions_by_flow differs from them for at least one flow.
 *
 * <p>On the account's first decision, previous_decisions_by_flow is allow for every flow. Events of one
 * account are not ordered: account.risk_version orders them, so keep the account with the highest
 * risk_version and ignore an event whose account is older than the one held.
 */
public class AccountRiskChanged {
    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
     * when a key issued to the account was used from many IP addresses or countries (POST
     * /v1/key_usage/batches). Its assessments are the account's history (GET
     * /v1/accounts/{account_id}/assessments).
     */
    @JsonProperty("account")
    private Account account;

    /**
     * The decision for each flow: the account's score under that flow's thresholds. A flow the workspace
     * has not set uses its account_creation thresholds, so the flows differ only where it set them.
     */
    @JsonProperty("previous_decisions_by_flow")
    private AccountDecisionsByFlow previousDecisionsByFlow;

    @JsonCreator
    public AccountRiskChanged(
            @JsonProperty("account") @Nonnull Account account,
            @JsonProperty("previous_decisions_by_flow") @Nonnull AccountDecisionsByFlow previousDecisionsByFlow) {
        this.account =
                Optional.ofNullable(account).orElseThrow(() -> new IllegalArgumentException("account cannot be null"));
        this.previousDecisionsByFlow = Optional.ofNullable(previousDecisionsByFlow)
                .orElseThrow(() -> new IllegalArgumentException("previousDecisionsByFlow cannot be null"));
    }

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
     * when a key issued to the account was used from many IP addresses or countries (POST
     * /v1/key_usage/batches). Its assessments are the account's history (GET
     * /v1/accounts/{account_id}/assessments).
     */
    public Account account() {
        return this.account;
    }

    /**
     * The decision for each flow: the account's score under that flow's thresholds. A flow the workspace
     * has not set uses its account_creation thresholds, so the flows differ only where it set them.
     */
    public AccountDecisionsByFlow previousDecisionsByFlow() {
        return this.previousDecisionsByFlow;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
     * when a key issued to the account was used from many IP addresses or countries (POST
     * /v1/key_usage/batches). Its assessments are the account's history (GET
     * /v1/accounts/{account_id}/assessments).
     */
    public AccountRiskChanged withAccount(@Nonnull Account account) {
        this.account = Utils.checkNotNull(account, "account");
        return this;
    }

    /**
     * The decision for each flow: the account's score under that flow's thresholds. A flow the workspace
     * has not set uses its account_creation thresholds, so the flows differ only where it set them.
     */
    public AccountRiskChanged withPreviousDecisionsByFlow(@Nonnull AccountDecisionsByFlow previousDecisionsByFlow) {
        this.previousDecisionsByFlow = Utils.checkNotNull(previousDecisionsByFlow, "previousDecisionsByFlow");
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
        AccountRiskChanged other = (AccountRiskChanged) o;
        return Utils.enhancedDeepEquals(this.account, other.account)
                && Utils.enhancedDeepEquals(this.previousDecisionsByFlow, other.previousDecisionsByFlow);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(account, previousDecisionsByFlow);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountRiskChanged.class, "account", account, "previousDecisionsByFlow", previousDecisionsByFlow);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Account account;

        private AccountDecisionsByFlow previousDecisionsByFlow;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
         * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
         * complete or partial), by created_at and then assessment_id, scored exactly as GET
         * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
         * when a key issued to the account was used from many IP addresses or countries (POST
         * /v1/key_usage/batches). Its assessments are the account's history (GET
         * /v1/accounts/{account_id}/assessments).
         */
        public Builder account(@Nonnull Account account) {
            this.account = Utils.checkNotNull(account, "account");
            return this;
        }

        /**
         * The decision for each flow: the account's score under that flow's thresholds. A flow the workspace
         * has not set uses its account_creation thresholds, so the flows differ only where it set them.
         */
        public Builder previousDecisionsByFlow(@Nonnull AccountDecisionsByFlow previousDecisionsByFlow) {
            this.previousDecisionsByFlow = Utils.checkNotNull(previousDecisionsByFlow, "previousDecisionsByFlow");
            return this;
        }

        public AccountRiskChanged build() {
            return new AccountRiskChanged(account, previousDecisionsByFlow);
        }
    }
}
