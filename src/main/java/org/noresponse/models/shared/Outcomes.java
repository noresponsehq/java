package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * Outcomes
 *
 * <p>Accounts whose current completed assessment was created in the selected window and remains retained
 * at as_of. The effective account feedback head is observed at as_of, including explicit unknown
 * reports. Customer-reported labels and unlabelled accounts have separate counts.
 *
 * <p>Superseded labels are excluded.
 */
public class Outcomes {

    @JsonProperty("population")
    private String population;

    @JsonProperty("denominator")
    private long denominator;

    @JsonProperty("reported")
    private long reported;

    @JsonProperty("unreported")
    private long unreported;

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    @JsonProperty("label_coverage")
    private Coverage labelCoverage;

    /**
     * Current customer-reported labels, not independently established ground truth. Missing keys mean
     * zero. Unknown is an explicit report; absent or expired feedback is unreported.
     */
    @JsonProperty("by_outcome")
    private OutcomesByOutcome byOutcome;

    @JsonCreator
    public Outcomes(
            @JsonProperty("denominator") long denominator,
            @JsonProperty("reported") long reported,
            @JsonProperty("unreported") long unreported,
            @JsonProperty("label_coverage") @Nonnull Coverage labelCoverage,
            @JsonProperty("by_outcome") @Nonnull OutcomesByOutcome byOutcome) {
        this.population = Builder._SINGLETON_VALUE_Population.value();
        this.denominator = denominator;
        this.reported = reported;
        this.unreported = unreported;
        this.labelCoverage = Optional.ofNullable(labelCoverage)
                .orElseThrow(() -> new IllegalArgumentException("labelCoverage cannot be null"));
        this.byOutcome = Optional.ofNullable(byOutcome)
                .orElseThrow(() -> new IllegalArgumentException("byOutcome cannot be null"));
    }

    public String population() {
        return this.population;
    }

    public long denominator() {
        return this.denominator;
    }

    public long reported() {
        return this.reported;
    }

    public long unreported() {
        return this.unreported;
    }

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    public Coverage labelCoverage() {
        return this.labelCoverage;
    }

    /**
     * Current customer-reported labels, not independently established ground truth. Missing keys mean
     * zero. Unknown is an explicit report; absent or expired feedback is unreported.
     */
    public OutcomesByOutcome byOutcome() {
        return this.byOutcome;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Outcomes withDenominator(long denominator) {
        this.denominator = denominator;
        return this;
    }

    public Outcomes withReported(long reported) {
        this.reported = reported;
        return this;
    }

    public Outcomes withUnreported(long unreported) {
        this.unreported = unreported;
        return this;
    }

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    public Outcomes withLabelCoverage(@Nonnull Coverage labelCoverage) {
        this.labelCoverage = Utils.checkNotNull(labelCoverage, "labelCoverage");
        return this;
    }

    /**
     * Current customer-reported labels, not independently established ground truth. Missing keys mean
     * zero. Unknown is an explicit report; absent or expired feedback is unreported.
     */
    public Outcomes withByOutcome(@Nonnull OutcomesByOutcome byOutcome) {
        this.byOutcome = Utils.checkNotNull(byOutcome, "byOutcome");
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
        Outcomes other = (Outcomes) o;
        return Utils.enhancedDeepEquals(this.population, other.population)
                && Utils.enhancedDeepEquals(this.denominator, other.denominator)
                && Utils.enhancedDeepEquals(this.reported, other.reported)
                && Utils.enhancedDeepEquals(this.unreported, other.unreported)
                && Utils.enhancedDeepEquals(this.labelCoverage, other.labelCoverage)
                && Utils.enhancedDeepEquals(this.byOutcome, other.byOutcome);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(population, denominator, reported, unreported, labelCoverage, byOutcome);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Outcomes.class,
                "population",
                population,
                "denominator",
                denominator,
                "reported",
                reported,
                "unreported",
                unreported,
                "labelCoverage",
                labelCoverage,
                "byOutcome",
                byOutcome);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long denominator;

        private long reported;

        private long unreported;

        private Coverage labelCoverage;

        private OutcomesByOutcome byOutcome;

        private Builder() {
            // force use of static builder() method
        }

        public Builder denominator(long denominator) {
            this.denominator = denominator;
            return this;
        }

        public Builder reported(long reported) {
            this.reported = reported;
            return this;
        }

        public Builder unreported(long unreported) {
            this.unreported = unreported;
            return this;
        }

        /**
         * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
         */
        public Builder labelCoverage(@Nonnull Coverage labelCoverage) {
            this.labelCoverage = Utils.checkNotNull(labelCoverage, "labelCoverage");
            return this;
        }

        /**
         * Current customer-reported labels, not independently established ground truth. Missing keys mean
         * zero. Unknown is an explicit report; absent or expired feedback is unreported.
         */
        public Builder byOutcome(@Nonnull OutcomesByOutcome byOutcome) {
            this.byOutcome = Utils.checkNotNull(byOutcome, "byOutcome");
            return this;
        }

        public Outcomes build() {
            return new Outcomes(denominator, reported, unreported, labelCoverage, byOutcome);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Population = new LazySingletonValue<>(
                "population",
                "\"retained_current_accounts_by_current_assessment_created_at\"",
                new TypeReference<String>() {});
    }
}
