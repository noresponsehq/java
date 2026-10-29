package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class PolicyPreviewRequestFlows {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_creation")
    private PolicyPreviewRequestFlowsAccountCreation accountCreation;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("login")
    private PolicyPreviewRequestFlowsLogin login;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("purchase")
    private PolicyPreviewRequestFlowsPurchase purchase;

    @JsonCreator
    public PolicyPreviewRequestFlows(
            @JsonProperty("account_creation") @Nullable PolicyPreviewRequestFlowsAccountCreation accountCreation,
            @JsonProperty("login") @Nullable PolicyPreviewRequestFlowsLogin login,
            @JsonProperty("purchase") @Nullable PolicyPreviewRequestFlowsPurchase purchase) {
        this.accountCreation = accountCreation;
        this.login = login;
        this.purchase = purchase;
    }

    public PolicyPreviewRequestFlows() {
        this(null, null, null);
    }

    public Optional<PolicyPreviewRequestFlowsAccountCreation> accountCreation() {
        return Optional.ofNullable(this.accountCreation);
    }

    public Optional<PolicyPreviewRequestFlowsLogin> login() {
        return Optional.ofNullable(this.login);
    }

    public Optional<PolicyPreviewRequestFlowsPurchase> purchase() {
        return Optional.ofNullable(this.purchase);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyPreviewRequestFlows withAccountCreation(
            @Nullable PolicyPreviewRequestFlowsAccountCreation accountCreation) {
        this.accountCreation = accountCreation;
        return this;
    }

    public PolicyPreviewRequestFlows withLogin(@Nullable PolicyPreviewRequestFlowsLogin login) {
        this.login = login;
        return this;
    }

    public PolicyPreviewRequestFlows withPurchase(@Nullable PolicyPreviewRequestFlowsPurchase purchase) {
        this.purchase = purchase;
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
        PolicyPreviewRequestFlows other = (PolicyPreviewRequestFlows) o;
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
                PolicyPreviewRequestFlows.class,
                "accountCreation",
                accountCreation,
                "login",
                login,
                "purchase",
                purchase);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private PolicyPreviewRequestFlowsAccountCreation accountCreation;

        private PolicyPreviewRequestFlowsLogin login;

        private PolicyPreviewRequestFlowsPurchase purchase;

        private Builder() {
            // force use of static builder() method
        }

        public Builder accountCreation(@Nullable PolicyPreviewRequestFlowsAccountCreation accountCreation) {
            this.accountCreation = accountCreation;
            return this;
        }

        public Builder login(@Nullable PolicyPreviewRequestFlowsLogin login) {
            this.login = login;
            return this;
        }

        public Builder purchase(@Nullable PolicyPreviewRequestFlowsPurchase purchase) {
            this.purchase = purchase;
            return this;
        }

        public PolicyPreviewRequestFlows build() {
            return new PolicyPreviewRequestFlows(accountCreation, login, purchase);
        }
    }
}
