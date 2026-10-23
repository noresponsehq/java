package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AccountDecisionsByFlow
 *
 * <p>The decision for each flow: the account's score under that flow's thresholds. A flow the workspace
 * has not set uses its account_creation thresholds, so the flows differ only where it set them.
 */
public class AccountDecisionsByFlow {
    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    @JsonProperty("account_creation")
    private AccountDecision accountCreation;

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    @JsonProperty("login")
    private AccountDecision login;

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    @JsonProperty("purchase")
    private AccountDecision purchase;

    @JsonCreator
    public AccountDecisionsByFlow(
            @JsonProperty("account_creation") @Nonnull AccountDecision accountCreation,
            @JsonProperty("login") @Nonnull AccountDecision login,
            @JsonProperty("purchase") @Nonnull AccountDecision purchase) {
        this.accountCreation = Optional.ofNullable(accountCreation)
                .orElseThrow(() -> new IllegalArgumentException("accountCreation cannot be null"));
        this.login = Optional.ofNullable(login).orElseThrow(() -> new IllegalArgumentException("login cannot be null"));
        this.purchase = Optional.ofNullable(purchase)
                .orElseThrow(() -> new IllegalArgumentException("purchase cannot be null"));
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    public AccountDecision accountCreation() {
        return this.accountCreation;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    public AccountDecision login() {
        return this.login;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    public AccountDecision purchase() {
        return this.purchase;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    public AccountDecisionsByFlow withAccountCreation(@Nonnull AccountDecision accountCreation) {
        this.accountCreation = Utils.checkNotNull(accountCreation, "accountCreation");
        return this;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    public AccountDecisionsByFlow withLogin(@Nonnull AccountDecision login) {
        this.login = Utils.checkNotNull(login, "login");
        return this;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. Your application applies it.
     */
    public AccountDecisionsByFlow withPurchase(@Nonnull AccountDecision purchase) {
        this.purchase = Utils.checkNotNull(purchase, "purchase");
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
        AccountDecisionsByFlow other = (AccountDecisionsByFlow) o;
        return Utils.enhancedDeepEquals(this.accountCreation, other.accountCreation)
                && Utils.enhancedDeepEquals(this.login, other.login)
                && Utils.enhancedDeepEquals(this.purchase, other.purchase);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountCreation, login, purchase);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDecisionsByFlow.class, "accountCreation", accountCreation, "login", login, "purchase", purchase);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AccountDecision accountCreation;

        private AccountDecision login;

        private AccountDecision purchase;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * block when the score reached your block threshold (risk.block of the assessment), otherwise the
         * assessment's recommendation. Your application applies it.
         */
        public Builder accountCreation(@Nonnull AccountDecision accountCreation) {
            this.accountCreation = Utils.checkNotNull(accountCreation, "accountCreation");
            return this;
        }

        /**
         * block when the score reached your block threshold (risk.block of the assessment), otherwise the
         * assessment's recommendation. Your application applies it.
         */
        public Builder login(@Nonnull AccountDecision login) {
            this.login = Utils.checkNotNull(login, "login");
            return this;
        }

        /**
         * block when the score reached your block threshold (risk.block of the assessment), otherwise the
         * assessment's recommendation. Your application applies it.
         */
        public Builder purchase(@Nonnull AccountDecision purchase) {
            this.purchase = Utils.checkNotNull(purchase, "purchase");
            return this;
        }

        public AccountDecisionsByFlow build() {
            return new AccountDecisionsByFlow(accountCreation, login, purchase);
        }
    }
}
