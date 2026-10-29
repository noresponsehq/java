package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentAggregateCoverage
 *
 * <p>Captured in the same database snapshot as the counts. Capture start times describe when counting
 * began, not the earliest creation time counted: an older assessment may first answer later. Older
 * outcomes are not inferred to be absent.
 *
 * <p>Erased detail cannot be returned by drill-down even while its anonymous count remains.
 */
public class AssessmentAggregateCoverage {

    @JsonProperty("as_of")
    private String asOf;

    @JsonProperty("first_answers_started_at")
    private String firstAnswersStartedAt;

    @JsonProperty("score_outcomes_started_at")
    private String scoreOutcomesStartedAt;

    @JsonProperty("retained_from")
    private String retainedFrom;

    /**
     * first_answers uses anonymous counters that can include erased detail. retained_first_answers
     * computes exact intersections over retained first answers within 31 days. Requests exceeding the
     * 100000-record work budget are refused.
     */
    @JsonProperty("population")
    private Population population;

    @JsonProperty("erased_records_included")
    private boolean erasedRecordsIncluded;

    @JsonProperty("overlapping_groups")
    private boolean overlappingGroups;

    @JsonProperty("late_flip_unit")
    private String lateFlipUnit;

    @JsonProperty("historical_backfill")
    private boolean historicalBackfill;

    @JsonCreator
    public AssessmentAggregateCoverage(
            @JsonProperty("as_of") @Nonnull String asOf,
            @JsonProperty("first_answers_started_at") @Nonnull String firstAnswersStartedAt,
            @JsonProperty("score_outcomes_started_at") @Nonnull String scoreOutcomesStartedAt,
            @JsonProperty("retained_from") @Nonnull String retainedFrom,
            @JsonProperty("population") @Nonnull Population population,
            @JsonProperty("erased_records_included") boolean erasedRecordsIncluded,
            @JsonProperty("overlapping_groups") boolean overlappingGroups) {
        this.asOf = Optional.ofNullable(asOf).orElseThrow(() -> new IllegalArgumentException("asOf cannot be null"));
        this.firstAnswersStartedAt = Optional.ofNullable(firstAnswersStartedAt)
                .orElseThrow(() -> new IllegalArgumentException("firstAnswersStartedAt cannot be null"));
        this.scoreOutcomesStartedAt = Optional.ofNullable(scoreOutcomesStartedAt)
                .orElseThrow(() -> new IllegalArgumentException("scoreOutcomesStartedAt cannot be null"));
        this.retainedFrom = Optional.ofNullable(retainedFrom)
                .orElseThrow(() -> new IllegalArgumentException("retainedFrom cannot be null"));
        this.population = Optional.ofNullable(population)
                .orElseThrow(() -> new IllegalArgumentException("population cannot be null"));
        this.erasedRecordsIncluded = erasedRecordsIncluded;
        this.overlappingGroups = overlappingGroups;
        this.lateFlipUnit = Builder._SINGLETON_VALUE_LateFlipUnit.value();
        this.historicalBackfill = Builder._SINGLETON_VALUE_HistoricalBackfill.value();
    }

    public String asOf() {
        return this.asOf;
    }

    public String firstAnswersStartedAt() {
        return this.firstAnswersStartedAt;
    }

    public String scoreOutcomesStartedAt() {
        return this.scoreOutcomesStartedAt;
    }

    public String retainedFrom() {
        return this.retainedFrom;
    }

    /**
     * first_answers uses anonymous counters that can include erased detail. retained_first_answers
     * computes exact intersections over retained first answers within 31 days. Requests exceeding the
     * 100000-record work budget are refused.
     */
    public Population population() {
        return this.population;
    }

    public boolean erasedRecordsIncluded() {
        return this.erasedRecordsIncluded;
    }

    public boolean overlappingGroups() {
        return this.overlappingGroups;
    }

    public String lateFlipUnit() {
        return this.lateFlipUnit;
    }

    public boolean historicalBackfill() {
        return this.historicalBackfill;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AssessmentAggregateCoverage withAsOf(@Nonnull String asOf) {
        this.asOf = Utils.checkNotNull(asOf, "asOf");
        return this;
    }

    public AssessmentAggregateCoverage withFirstAnswersStartedAt(@Nonnull String firstAnswersStartedAt) {
        this.firstAnswersStartedAt = Utils.checkNotNull(firstAnswersStartedAt, "firstAnswersStartedAt");
        return this;
    }

    public AssessmentAggregateCoverage withScoreOutcomesStartedAt(@Nonnull String scoreOutcomesStartedAt) {
        this.scoreOutcomesStartedAt = Utils.checkNotNull(scoreOutcomesStartedAt, "scoreOutcomesStartedAt");
        return this;
    }

    public AssessmentAggregateCoverage withRetainedFrom(@Nonnull String retainedFrom) {
        this.retainedFrom = Utils.checkNotNull(retainedFrom, "retainedFrom");
        return this;
    }

    /**
     * first_answers uses anonymous counters that can include erased detail. retained_first_answers
     * computes exact intersections over retained first answers within 31 days. Requests exceeding the
     * 100000-record work budget are refused.
     */
    public AssessmentAggregateCoverage withPopulation(@Nonnull Population population) {
        this.population = Utils.checkNotNull(population, "population");
        return this;
    }

    public AssessmentAggregateCoverage withErasedRecordsIncluded(boolean erasedRecordsIncluded) {
        this.erasedRecordsIncluded = erasedRecordsIncluded;
        return this;
    }

    public AssessmentAggregateCoverage withOverlappingGroups(boolean overlappingGroups) {
        this.overlappingGroups = overlappingGroups;
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
        AssessmentAggregateCoverage other = (AssessmentAggregateCoverage) o;
        return Utils.enhancedDeepEquals(this.asOf, other.asOf)
                && Utils.enhancedDeepEquals(this.firstAnswersStartedAt, other.firstAnswersStartedAt)
                && Utils.enhancedDeepEquals(this.scoreOutcomesStartedAt, other.scoreOutcomesStartedAt)
                && Utils.enhancedDeepEquals(this.retainedFrom, other.retainedFrom)
                && Utils.enhancedDeepEquals(this.population, other.population)
                && Utils.enhancedDeepEquals(this.erasedRecordsIncluded, other.erasedRecordsIncluded)
                && Utils.enhancedDeepEquals(this.overlappingGroups, other.overlappingGroups)
                && Utils.enhancedDeepEquals(this.lateFlipUnit, other.lateFlipUnit)
                && Utils.enhancedDeepEquals(this.historicalBackfill, other.historicalBackfill);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                asOf,
                firstAnswersStartedAt,
                scoreOutcomesStartedAt,
                retainedFrom,
                population,
                erasedRecordsIncluded,
                overlappingGroups,
                lateFlipUnit,
                historicalBackfill);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentAggregateCoverage.class,
                "asOf",
                asOf,
                "firstAnswersStartedAt",
                firstAnswersStartedAt,
                "scoreOutcomesStartedAt",
                scoreOutcomesStartedAt,
                "retainedFrom",
                retainedFrom,
                "population",
                population,
                "erasedRecordsIncluded",
                erasedRecordsIncluded,
                "overlappingGroups",
                overlappingGroups,
                "lateFlipUnit",
                lateFlipUnit,
                "historicalBackfill",
                historicalBackfill);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String asOf;

        private String firstAnswersStartedAt;

        private String scoreOutcomesStartedAt;

        private String retainedFrom;

        private Population population;

        private boolean erasedRecordsIncluded;

        private boolean overlappingGroups;

        private Builder() {
            // force use of static builder() method
        }

        public Builder asOf(@Nonnull String asOf) {
            this.asOf = Utils.checkNotNull(asOf, "asOf");
            return this;
        }

        public Builder firstAnswersStartedAt(@Nonnull String firstAnswersStartedAt) {
            this.firstAnswersStartedAt = Utils.checkNotNull(firstAnswersStartedAt, "firstAnswersStartedAt");
            return this;
        }

        public Builder scoreOutcomesStartedAt(@Nonnull String scoreOutcomesStartedAt) {
            this.scoreOutcomesStartedAt = Utils.checkNotNull(scoreOutcomesStartedAt, "scoreOutcomesStartedAt");
            return this;
        }

        public Builder retainedFrom(@Nonnull String retainedFrom) {
            this.retainedFrom = Utils.checkNotNull(retainedFrom, "retainedFrom");
            return this;
        }

        /**
         * first_answers uses anonymous counters that can include erased detail. retained_first_answers
         * computes exact intersections over retained first answers within 31 days. Requests exceeding the
         * 100000-record work budget are refused.
         */
        public Builder population(@Nonnull Population population) {
            this.population = Utils.checkNotNull(population, "population");
            return this;
        }

        public Builder erasedRecordsIncluded(boolean erasedRecordsIncluded) {
            this.erasedRecordsIncluded = erasedRecordsIncluded;
            return this;
        }

        public Builder overlappingGroups(boolean overlappingGroups) {
            this.overlappingGroups = overlappingGroups;
            return this;
        }

        public AssessmentAggregateCoverage build() {
            return new AssessmentAggregateCoverage(
                    asOf,
                    firstAnswersStartedAt,
                    scoreOutcomesStartedAt,
                    retainedFrom,
                    population,
                    erasedRecordsIncluded,
                    overlappingGroups);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_LateFlipUnit =
                new LazySingletonValue<>("late_flip_unit", "\"decision_transitions\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_HistoricalBackfill =
                new LazySingletonValue<>("historical_backfill", "false", new TypeReference<Boolean>() {});
    }
}
