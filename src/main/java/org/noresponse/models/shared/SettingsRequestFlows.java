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

/**
 * SettingsRequestFlows
 *
 * <p>Use account_creation or its deprecated alias signup, not both.
 */
public class SettingsRequestFlows {
    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_creation")
    private FlowChange accountCreation;

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("login")
    private FlowChange login;

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("purchase")
    private FlowChange purchase;

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("signup")
    private FlowChange signup;

    @JsonCreator
    public SettingsRequestFlows(
            @JsonProperty("account_creation") @Nullable FlowChange accountCreation,
            @JsonProperty("login") @Nullable FlowChange login,
            @JsonProperty("purchase") @Nullable FlowChange purchase,
            @JsonProperty("signup") @Nullable FlowChange signup) {
        this.accountCreation = accountCreation;
        this.login = login;
        this.purchase = purchase;
        this.signup = signup;
    }

    public SettingsRequestFlows() {
        this(null, null, null, null);
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public Optional<FlowChange> accountCreation() {
        return Optional.ofNullable(this.accountCreation);
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public Optional<FlowChange> login() {
        return Optional.ofNullable(this.login);
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public Optional<FlowChange> purchase() {
        return Optional.ofNullable(this.purchase);
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public Optional<FlowChange> signup() {
        return Optional.ofNullable(this.signup);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public SettingsRequestFlows withAccountCreation(@Nullable FlowChange accountCreation) {
        this.accountCreation = accountCreation;
        return this;
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public SettingsRequestFlows withLogin(@Nullable FlowChange login) {
        this.login = login;
        return this;
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public SettingsRequestFlows withPurchase(@Nullable FlowChange purchase) {
        this.purchase = purchase;
        return this;
    }

    /**
     * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
     * start from the values in force, account_creation's while it inherits them.
     *
     * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
     * 100.
     */
    public SettingsRequestFlows withSignup(@Nullable FlowChange signup) {
        this.signup = signup;
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
        SettingsRequestFlows other = (SettingsRequestFlows) o;
        return Utils.enhancedDeepEquals(this.accountCreation, other.accountCreation)
                && Utils.enhancedDeepEquals(this.login, other.login)
                && Utils.enhancedDeepEquals(this.purchase, other.purchase)
                && Utils.enhancedDeepEquals(this.signup, other.signup);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountCreation, login, purchase, signup);
    }

    @Override
    public String toString() {
        return Utils.toString(
                SettingsRequestFlows.class,
                "accountCreation",
                accountCreation,
                "login",
                login,
                "purchase",
                purchase,
                "signup",
                signup);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private FlowChange accountCreation;

        private FlowChange login;

        private FlowChange purchase;

        private FlowChange signup;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
         * start from the values in force, account_creation's while it inherits them.
         *
         * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
         * 100.
         */
        public Builder accountCreation(@Nullable FlowChange accountCreation) {
            this.accountCreation = accountCreation;
            return this;
        }

        /**
         * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
         * start from the values in force, account_creation's while it inherits them.
         *
         * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
         * 100.
         */
        public Builder login(@Nullable FlowChange login) {
            this.login = login;
            return this;
        }

        /**
         * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
         * start from the values in force, account_creation's while it inherits them.
         *
         * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
         * 100.
         */
        public Builder purchase(@Nullable FlowChange purchase) {
            this.purchase = purchase;
            return this;
        }

        /**
         * A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
         * start from the values in force, account_creation's while it inherits them.
         *
         * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
         * 100.
         */
        public Builder signup(@Nullable FlowChange signup) {
            this.signup = signup;
            return this;
        }

        public SettingsRequestFlows build() {
            return new SettingsRequestFlows(accountCreation, login, purchase, signup);
        }
    }
}
