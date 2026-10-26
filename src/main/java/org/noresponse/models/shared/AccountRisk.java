package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AccountRisk {
    /**
     * 0 (safe) to 100 (risky), as risk.score of the assessment.
     */
    @JsonProperty("score")
    private long score;

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    @JsonProperty("decision")
    private AccountDecision decision;

    /**
     * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
     * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
     * reason carries only its direction and impact band.
     */
    @JsonProperty("reasons")
    private List<AccountRiskReason> reasons;

    @JsonCreator
    public AccountRisk(
            @JsonProperty("score") long score,
            @JsonProperty("decision") @Nonnull AccountDecision decision,
            @JsonProperty("reasons") @Nonnull List<AccountRiskReason> reasons) {
        this.score = score;
        this.decision = Optional.ofNullable(decision)
                .orElseThrow(() -> new IllegalArgumentException("decision cannot be null"));
        this.reasons =
                Optional.ofNullable(reasons).orElseThrow(() -> new IllegalArgumentException("reasons cannot be null"));
    }

    /**
     * 0 (safe) to 100 (risky), as risk.score of the assessment.
     */
    public long score() {
        return this.score;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    public AccountDecision decision() {
        return this.decision;
    }

    /**
     * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
     * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
     * reason carries only its direction and impact band.
     */
    public List<AccountRiskReason> reasons() {
        return this.reasons;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 0 (safe) to 100 (risky), as risk.score of the assessment.
     */
    public AccountRisk withScore(long score) {
        this.score = score;
        return this;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    public AccountRisk withDecision(@Nonnull AccountDecision decision) {
        this.decision = Utils.checkNotNull(decision, "decision");
        return this;
    }

    /**
     * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
     * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
     * reason carries only its direction and impact band.
     */
    public AccountRisk withReasons(@Nonnull List<AccountRiskReason> reasons) {
        this.reasons = Utils.checkNotNull(reasons, "reasons");
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
        AccountRisk other = (AccountRisk) o;
        return Utils.enhancedDeepEquals(this.score, other.score)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.reasons, other.reasons);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(score, decision, reasons);
    }

    @Override
    public String toString() {
        return Utils.toString(AccountRisk.class, "score", score, "decision", decision, "reasons", reasons);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long score;

        private AccountDecision decision;

        private List<AccountRiskReason> reasons;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * 0 (safe) to 100 (risky), as risk.score of the assessment.
         */
        public Builder score(long score) {
            this.score = score;
            return this;
        }

        /**
         * block when the score reached your block threshold (risk.block of the assessment), otherwise the
         * assessment's recommendation. held only for a flow whose admission is held (settings
         * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
         * investigation has not completed (status partial): the final decision arrives as account.risk_changed
         * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
         * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
         * evidence so far gives, whichever is stricter. Your application applies it.
         */
        public Builder decision(@Nonnull AccountDecision decision) {
            this.decision = Utils.checkNotNull(decision, "decision");
            return this;
        }

        /**
         * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
         * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
         * reason carries only its direction and impact band.
         */
        public Builder reasons(@Nonnull List<AccountRiskReason> reasons) {
            this.reasons = Utils.checkNotNull(reasons, "reasons");
            return this;
        }

        public AccountRisk build() {
            return new AccountRisk(score, decision, reasons);
        }
    }
}
