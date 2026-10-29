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
 * Enforcements
 *
 * <p>Retained customer enforcement report rows created in [from,to), one row per
 * account/risk_version/flow/action. A later applied report can replace failed at the original
 * created_at; retries are not additional attempts here. applied_coverage divides reported_applied by
 * report rows, including explicit action none; it is not independently verified execution, all
 * requested actions, or prevented fraud.
 *
 * <p>Unreported actions are unknown.
 */
public class Enforcements {

    @JsonProperty("population")
    private String population;

    @JsonProperty("denominator")
    private long denominator;

    @JsonProperty("accounts")
    private long accounts;

    @JsonProperty("reported_applied")
    private long reportedApplied;

    @JsonProperty("reported_failed")
    private long reportedFailed;

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    @JsonProperty("applied_coverage")
    private Coverage appliedCoverage;

    @JsonCreator
    public Enforcements(
            @JsonProperty("denominator") long denominator,
            @JsonProperty("accounts") long accounts,
            @JsonProperty("reported_applied") long reportedApplied,
            @JsonProperty("reported_failed") long reportedFailed,
            @JsonProperty("applied_coverage") @Nonnull Coverage appliedCoverage) {
        this.population = Builder._SINGLETON_VALUE_Population.value();
        this.denominator = denominator;
        this.accounts = accounts;
        this.reportedApplied = reportedApplied;
        this.reportedFailed = reportedFailed;
        this.appliedCoverage = Optional.ofNullable(appliedCoverage)
                .orElseThrow(() -> new IllegalArgumentException("appliedCoverage cannot be null"));
    }

    public String population() {
        return this.population;
    }

    public long denominator() {
        return this.denominator;
    }

    public long accounts() {
        return this.accounts;
    }

    public long reportedApplied() {
        return this.reportedApplied;
    }

    public long reportedFailed() {
        return this.reportedFailed;
    }

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    public Coverage appliedCoverage() {
        return this.appliedCoverage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Enforcements withDenominator(long denominator) {
        this.denominator = denominator;
        return this;
    }

    public Enforcements withAccounts(long accounts) {
        this.accounts = accounts;
        return this;
    }

    public Enforcements withReportedApplied(long reportedApplied) {
        this.reportedApplied = reportedApplied;
        return this;
    }

    public Enforcements withReportedFailed(long reportedFailed) {
        this.reportedFailed = reportedFailed;
        return this;
    }

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    public Enforcements withAppliedCoverage(@Nonnull Coverage appliedCoverage) {
        this.appliedCoverage = Utils.checkNotNull(appliedCoverage, "appliedCoverage");
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
        Enforcements other = (Enforcements) o;
        return Utils.enhancedDeepEquals(this.population, other.population)
                && Utils.enhancedDeepEquals(this.denominator, other.denominator)
                && Utils.enhancedDeepEquals(this.accounts, other.accounts)
                && Utils.enhancedDeepEquals(this.reportedApplied, other.reportedApplied)
                && Utils.enhancedDeepEquals(this.reportedFailed, other.reportedFailed)
                && Utils.enhancedDeepEquals(this.appliedCoverage, other.appliedCoverage);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(population, denominator, accounts, reportedApplied, reportedFailed, appliedCoverage);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Enforcements.class,
                "population",
                population,
                "denominator",
                denominator,
                "accounts",
                accounts,
                "reportedApplied",
                reportedApplied,
                "reportedFailed",
                reportedFailed,
                "appliedCoverage",
                appliedCoverage);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long denominator;

        private long accounts;

        private long reportedApplied;

        private long reportedFailed;

        private Coverage appliedCoverage;

        private Builder() {
            // force use of static builder() method
        }

        public Builder denominator(long denominator) {
            this.denominator = denominator;
            return this;
        }

        public Builder accounts(long accounts) {
            this.accounts = accounts;
            return this;
        }

        public Builder reportedApplied(long reportedApplied) {
            this.reportedApplied = reportedApplied;
            return this;
        }

        public Builder reportedFailed(long reportedFailed) {
            this.reportedFailed = reportedFailed;
            return this;
        }

        /**
         * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
         */
        public Builder appliedCoverage(@Nonnull Coverage appliedCoverage) {
            this.appliedCoverage = Utils.checkNotNull(appliedCoverage, "appliedCoverage");
            return this;
        }

        public Enforcements build() {
            return new Enforcements(denominator, accounts, reportedApplied, reportedFailed, appliedCoverage);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Population = new LazySingletonValue<>(
                "population", "\"retained_enforcement_reports_by_created_at\"", new TypeReference<String>() {});
    }
}
