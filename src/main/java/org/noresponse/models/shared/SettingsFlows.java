package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class SettingsFlows {
    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    @JsonProperty("account_creation")
    private FlowSettings accountCreation;

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    @JsonProperty("login")
    private FlowSettings login;

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    @JsonProperty("purchase")
    private FlowSettings purchase;

    @JsonCreator
    public SettingsFlows(
            @JsonProperty("account_creation") @Nonnull FlowSettings accountCreation,
            @JsonProperty("login") @Nonnull FlowSettings login,
            @JsonProperty("purchase") @Nonnull FlowSettings purchase) {
        this.accountCreation = Optional.ofNullable(accountCreation)
                .orElseThrow(() -> new IllegalArgumentException("accountCreation cannot be null"));
        this.login = Optional.ofNullable(login).orElseThrow(() -> new IllegalArgumentException("login cannot be null"));
        this.purchase = Optional.ofNullable(purchase)
                .orElseThrow(() -> new IllegalArgumentException("purchase cannot be null"));
    }

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    public FlowSettings accountCreation() {
        return this.accountCreation;
    }

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    public FlowSettings login() {
        return this.login;
    }

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    public FlowSettings purchase() {
        return this.purchase;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    public SettingsFlows withAccountCreation(@Nonnull FlowSettings accountCreation) {
        this.accountCreation = Utils.checkNotNull(accountCreation, "accountCreation");
        return this;
    }

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    public SettingsFlows withLogin(@Nonnull FlowSettings login) {
        this.login = Utils.checkNotNull(login, "login");
        return this;
    }

    /**
     * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
     * review; from block, when present, the decision is block. admission says when the flow's decision is
     * final.
     */
    public SettingsFlows withPurchase(@Nonnull FlowSettings purchase) {
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
        SettingsFlows other = (SettingsFlows) o;
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
                SettingsFlows.class, "accountCreation", accountCreation, "login", login, "purchase", purchase);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private FlowSettings accountCreation;

        private FlowSettings login;

        private FlowSettings purchase;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
         * review; from block, when present, the decision is block. admission says when the flow's decision is
         * final.
         */
        public Builder accountCreation(@Nonnull FlowSettings accountCreation) {
            this.accountCreation = Utils.checkNotNull(accountCreation, "accountCreation");
            return this;
        }

        /**
         * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
         * review; from block, when present, the decision is block. admission says when the flow's decision is
         * final.
         */
        public Builder login(@Nonnull FlowSettings login) {
            this.login = Utils.checkNotNull(login, "login");
            return this;
        }

        /**
         * One flow's policy in force. A score below step_up is allow, below review step_up, and from review
         * review; from block, when present, the decision is block. admission says when the flow's decision is
         * final.
         */
        public Builder purchase(@Nonnull FlowSettings purchase) {
            this.purchase = Utils.checkNotNull(purchase, "purchase");
            return this;
        }

        public SettingsFlows build() {
            return new SettingsFlows(accountCreation, login, purchase);
        }
    }
}
