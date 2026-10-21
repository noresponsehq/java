package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class RiskSummaryEmail {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("disposable")
    private Boolean disposable;

    /**
     * Accounts found for the email.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("accounts_found")
    private Long accountsFound;

    /**
     * Breach facts for one selector. found is the one required member: false is a definite 'in no breach
     * table'. Every other member is optional, and absent makes no claim.
     *
     * <p>No member carries a breach value, password or hash, and unknown members are refused. truncated means
     * the value matched more tables or records than the lookup keeps, so absence-based claims (a missing
     * name match, for example) are withheld; a truncated result is always found.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("breach")
    private BreachSummary breach;

    @JsonCreator
    public RiskSummaryEmail(
            @JsonProperty("disposable") @Nullable Boolean disposable,
            @JsonProperty("accounts_found") @Nullable Long accountsFound,
            @JsonProperty("breach") @Nullable BreachSummary breach) {
        this.disposable = disposable;
        this.accountsFound = accountsFound;
        this.breach = breach;
    }

    public RiskSummaryEmail() {
        this(null, null, null);
    }

    public Optional<Boolean> disposable() {
        return Optional.ofNullable(this.disposable);
    }

    /**
     * Accounts found for the email.
     */
    public Optional<Long> accountsFound() {
        return Optional.ofNullable(this.accountsFound);
    }

    /**
     * Breach facts for one selector. found is the one required member: false is a definite 'in no breach
     * table'. Every other member is optional, and absent makes no claim.
     *
     * <p>No member carries a breach value, password or hash, and unknown members are refused. truncated means
     * the value matched more tables or records than the lookup keeps, so absence-based claims (a missing
     * name match, for example) are withheld; a truncated result is always found.
     */
    public Optional<BreachSummary> breach() {
        return Optional.ofNullable(this.breach);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskSummaryEmail withDisposable(@Nullable Boolean disposable) {
        this.disposable = disposable;
        return this;
    }

    /**
     * Accounts found for the email.
     */
    public RiskSummaryEmail withAccountsFound(@Nullable Long accountsFound) {
        this.accountsFound = accountsFound;
        return this;
    }

    /**
     * Breach facts for one selector. found is the one required member: false is a definite 'in no breach
     * table'. Every other member is optional, and absent makes no claim.
     *
     * <p>No member carries a breach value, password or hash, and unknown members are refused. truncated means
     * the value matched more tables or records than the lookup keeps, so absence-based claims (a missing
     * name match, for example) are withheld; a truncated result is always found.
     */
    public RiskSummaryEmail withBreach(@Nullable BreachSummary breach) {
        this.breach = breach;
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
        RiskSummaryEmail other = (RiskSummaryEmail) o;
        return Utils.enhancedDeepEquals(this.disposable, other.disposable)
                && Utils.enhancedDeepEquals(this.accountsFound, other.accountsFound)
                && Utils.enhancedDeepEquals(this.breach, other.breach);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(disposable, accountsFound, breach);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RiskSummaryEmail.class, "disposable", disposable, "accountsFound", accountsFound, "breach", breach);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Boolean disposable;

        private Long accountsFound;

        private BreachSummary breach;

        private Builder() {
            // force use of static builder() method
        }

        public Builder disposable(@Nullable Boolean disposable) {
            this.disposable = disposable;
            return this;
        }

        /**
         * Accounts found for the email.
         */
        public Builder accountsFound(@Nullable Long accountsFound) {
            this.accountsFound = accountsFound;
            return this;
        }

        /**
         * Breach facts for one selector. found is the one required member: false is a definite 'in no breach
         * table'. Every other member is optional, and absent makes no claim.
         *
         * <p>No member carries a breach value, password or hash, and unknown members are refused. truncated means
         * the value matched more tables or records than the lookup keeps, so absence-based claims (a missing
         * name match, for example) are withheld; a truncated result is always found.
         */
        public Builder breach(@Nullable BreachSummary breach) {
            this.breach = breach;
            return this;
        }

        public RiskSummaryEmail build() {
            return new RiskSummaryEmail(disposable, accountsFound, breach);
        }
    }
}
