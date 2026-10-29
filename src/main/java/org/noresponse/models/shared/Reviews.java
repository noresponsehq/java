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
 * Reviews
 *
 * <p>Reviews created in [from,to) whose review and current account assessment remain retained. Status and
 * assignment are current at as_of, not a transition history. label_coverage numerator is resolved
 * reviews labeled benign or abuse; denominator is resolved reviews.
 *
 * <p>Inconclusive is not a known outcome. Review conclusions do not change account feedback or
 * enforcement.
 */
public class Reviews {

    @JsonProperty("population")
    private String population;

    @JsonProperty("denominator")
    private long denominator;

    @JsonProperty("open")
    private long open;

    @JsonProperty("in_progress")
    private long inProgress;

    @JsonProperty("resolved")
    private long resolved;

    @JsonProperty("assigned")
    private long assigned;

    @JsonProperty("unassigned")
    private long unassigned;

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    @JsonProperty("label_coverage")
    private Coverage labelCoverage;

    /**
     * Current resolution of each resolved retained review; prior resolutions and reopened reviews do not
     * count. Missing keys mean zero. These investigation conclusions are independent of account feedback.
     */
    @JsonProperty("conclusions")
    private ReviewsConclusions conclusions;

    @JsonCreator
    public Reviews(
            @JsonProperty("denominator") long denominator,
            @JsonProperty("open") long open,
            @JsonProperty("in_progress") long inProgress,
            @JsonProperty("resolved") long resolved,
            @JsonProperty("assigned") long assigned,
            @JsonProperty("unassigned") long unassigned,
            @JsonProperty("label_coverage") @Nonnull Coverage labelCoverage,
            @JsonProperty("conclusions") @Nonnull ReviewsConclusions conclusions) {
        this.population = Builder._SINGLETON_VALUE_Population.value();
        this.denominator = denominator;
        this.open = open;
        this.inProgress = inProgress;
        this.resolved = resolved;
        this.assigned = assigned;
        this.unassigned = unassigned;
        this.labelCoverage = Optional.ofNullable(labelCoverage)
                .orElseThrow(() -> new IllegalArgumentException("labelCoverage cannot be null"));
        this.conclusions = Optional.ofNullable(conclusions)
                .orElseThrow(() -> new IllegalArgumentException("conclusions cannot be null"));
    }

    public String population() {
        return this.population;
    }

    public long denominator() {
        return this.denominator;
    }

    public long open() {
        return this.open;
    }

    public long inProgress() {
        return this.inProgress;
    }

    public long resolved() {
        return this.resolved;
    }

    public long assigned() {
        return this.assigned;
    }

    public long unassigned() {
        return this.unassigned;
    }

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    public Coverage labelCoverage() {
        return this.labelCoverage;
    }

    /**
     * Current resolution of each resolved retained review; prior resolutions and reopened reviews do not
     * count. Missing keys mean zero. These investigation conclusions are independent of account feedback.
     */
    public ReviewsConclusions conclusions() {
        return this.conclusions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Reviews withDenominator(long denominator) {
        this.denominator = denominator;
        return this;
    }

    public Reviews withOpen(long open) {
        this.open = open;
        return this;
    }

    public Reviews withInProgress(long inProgress) {
        this.inProgress = inProgress;
        return this;
    }

    public Reviews withResolved(long resolved) {
        this.resolved = resolved;
        return this;
    }

    public Reviews withAssigned(long assigned) {
        this.assigned = assigned;
        return this;
    }

    public Reviews withUnassigned(long unassigned) {
        this.unassigned = unassigned;
        return this;
    }

    /**
     * Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
     */
    public Reviews withLabelCoverage(@Nonnull Coverage labelCoverage) {
        this.labelCoverage = Utils.checkNotNull(labelCoverage, "labelCoverage");
        return this;
    }

    /**
     * Current resolution of each resolved retained review; prior resolutions and reopened reviews do not
     * count. Missing keys mean zero. These investigation conclusions are independent of account feedback.
     */
    public Reviews withConclusions(@Nonnull ReviewsConclusions conclusions) {
        this.conclusions = Utils.checkNotNull(conclusions, "conclusions");
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
        Reviews other = (Reviews) o;
        return Utils.enhancedDeepEquals(this.population, other.population)
                && Utils.enhancedDeepEquals(this.denominator, other.denominator)
                && Utils.enhancedDeepEquals(this.open, other.open)
                && Utils.enhancedDeepEquals(this.inProgress, other.inProgress)
                && Utils.enhancedDeepEquals(this.resolved, other.resolved)
                && Utils.enhancedDeepEquals(this.assigned, other.assigned)
                && Utils.enhancedDeepEquals(this.unassigned, other.unassigned)
                && Utils.enhancedDeepEquals(this.labelCoverage, other.labelCoverage)
                && Utils.enhancedDeepEquals(this.conclusions, other.conclusions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                population, denominator, open, inProgress, resolved, assigned, unassigned, labelCoverage, conclusions);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Reviews.class,
                "population",
                population,
                "denominator",
                denominator,
                "open",
                open,
                "inProgress",
                inProgress,
                "resolved",
                resolved,
                "assigned",
                assigned,
                "unassigned",
                unassigned,
                "labelCoverage",
                labelCoverage,
                "conclusions",
                conclusions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long denominator;

        private long open;

        private long inProgress;

        private long resolved;

        private long assigned;

        private long unassigned;

        private Coverage labelCoverage;

        private ReviewsConclusions conclusions;

        private Builder() {
            // force use of static builder() method
        }

        public Builder denominator(long denominator) {
            this.denominator = denominator;
            return this;
        }

        public Builder open(long open) {
            this.open = open;
            return this;
        }

        public Builder inProgress(long inProgress) {
            this.inProgress = inProgress;
            return this;
        }

        public Builder resolved(long resolved) {
            this.resolved = resolved;
            return this;
        }

        public Builder assigned(long assigned) {
            this.assigned = assigned;
            return this;
        }

        public Builder unassigned(long unassigned) {
            this.unassigned = unassigned;
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
         * Current resolution of each resolved retained review; prior resolutions and reopened reviews do not
         * count. Missing keys mean zero. These investigation conclusions are independent of account feedback.
         */
        public Builder conclusions(@Nonnull ReviewsConclusions conclusions) {
            this.conclusions = Utils.checkNotNull(conclusions, "conclusions");
            return this;
        }

        public Reviews build() {
            return new Reviews(
                    denominator, open, inProgress, resolved, assigned, unassigned, labelCoverage, conclusions);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Population = new LazySingletonValue<>(
                "population", "\"retained_reviews_by_created_at\"", new TypeReference<String>() {});
    }
}
