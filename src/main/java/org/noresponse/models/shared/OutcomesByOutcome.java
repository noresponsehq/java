package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * OutcomesByOutcome
 *
 * <p>Current customer-reported labels, not independently established ground truth. Missing keys mean
 * zero. Unknown is an explicit report; absent or expired feedback is unreported.
 */
public class OutcomesByOutcome {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("confirmed_abuse")
    private Long confirmedAbuse;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_takeover")
    private Long accountTakeover;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("chargeback")
    private Long chargeback;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("false_positive")
    private Long falsePositive;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("legitimate")
    private Long legitimate;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("unknown")
    private Long unknown;

    @JsonCreator
    public OutcomesByOutcome(
            @JsonProperty("confirmed_abuse") @Nullable Long confirmedAbuse,
            @JsonProperty("account_takeover") @Nullable Long accountTakeover,
            @JsonProperty("chargeback") @Nullable Long chargeback,
            @JsonProperty("false_positive") @Nullable Long falsePositive,
            @JsonProperty("legitimate") @Nullable Long legitimate,
            @JsonProperty("unknown") @Nullable Long unknown) {
        this.confirmedAbuse = confirmedAbuse;
        this.accountTakeover = accountTakeover;
        this.chargeback = chargeback;
        this.falsePositive = falsePositive;
        this.legitimate = legitimate;
        this.unknown = unknown;
    }

    public OutcomesByOutcome() {
        this(null, null, null, null, null, null);
    }

    public Optional<Long> confirmedAbuse() {
        return Optional.ofNullable(this.confirmedAbuse);
    }

    public Optional<Long> accountTakeover() {
        return Optional.ofNullable(this.accountTakeover);
    }

    public Optional<Long> chargeback() {
        return Optional.ofNullable(this.chargeback);
    }

    public Optional<Long> falsePositive() {
        return Optional.ofNullable(this.falsePositive);
    }

    public Optional<Long> legitimate() {
        return Optional.ofNullable(this.legitimate);
    }

    public Optional<Long> unknown() {
        return Optional.ofNullable(this.unknown);
    }

    public static Builder builder() {
        return new Builder();
    }

    public OutcomesByOutcome withConfirmedAbuse(@Nullable Long confirmedAbuse) {
        this.confirmedAbuse = confirmedAbuse;
        return this;
    }

    public OutcomesByOutcome withAccountTakeover(@Nullable Long accountTakeover) {
        this.accountTakeover = accountTakeover;
        return this;
    }

    public OutcomesByOutcome withChargeback(@Nullable Long chargeback) {
        this.chargeback = chargeback;
        return this;
    }

    public OutcomesByOutcome withFalsePositive(@Nullable Long falsePositive) {
        this.falsePositive = falsePositive;
        return this;
    }

    public OutcomesByOutcome withLegitimate(@Nullable Long legitimate) {
        this.legitimate = legitimate;
        return this;
    }

    public OutcomesByOutcome withUnknown(@Nullable Long unknown) {
        this.unknown = unknown;
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
        OutcomesByOutcome other = (OutcomesByOutcome) o;
        return Utils.enhancedDeepEquals(this.confirmedAbuse, other.confirmedAbuse)
                && Utils.enhancedDeepEquals(this.accountTakeover, other.accountTakeover)
                && Utils.enhancedDeepEquals(this.chargeback, other.chargeback)
                && Utils.enhancedDeepEquals(this.falsePositive, other.falsePositive)
                && Utils.enhancedDeepEquals(this.legitimate, other.legitimate)
                && Utils.enhancedDeepEquals(this.unknown, other.unknown);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(confirmedAbuse, accountTakeover, chargeback, falsePositive, legitimate, unknown);
    }

    @Override
    public String toString() {
        return Utils.toString(
                OutcomesByOutcome.class,
                "confirmedAbuse",
                confirmedAbuse,
                "accountTakeover",
                accountTakeover,
                "chargeback",
                chargeback,
                "falsePositive",
                falsePositive,
                "legitimate",
                legitimate,
                "unknown",
                unknown);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long confirmedAbuse;

        private Long accountTakeover;

        private Long chargeback;

        private Long falsePositive;

        private Long legitimate;

        private Long unknown;

        private Builder() {
            // force use of static builder() method
        }

        public Builder confirmedAbuse(@Nullable Long confirmedAbuse) {
            this.confirmedAbuse = confirmedAbuse;
            return this;
        }

        public Builder accountTakeover(@Nullable Long accountTakeover) {
            this.accountTakeover = accountTakeover;
            return this;
        }

        public Builder chargeback(@Nullable Long chargeback) {
            this.chargeback = chargeback;
            return this;
        }

        public Builder falsePositive(@Nullable Long falsePositive) {
            this.falsePositive = falsePositive;
            return this;
        }

        public Builder legitimate(@Nullable Long legitimate) {
            this.legitimate = legitimate;
            return this;
        }

        public Builder unknown(@Nullable Long unknown) {
            this.unknown = unknown;
            return this;
        }

        public OutcomesByOutcome build() {
            return new OutcomesByOutcome(
                    confirmedAbuse, accountTakeover, chargeback, falsePositive, legitimate, unknown);
        }
    }
}
