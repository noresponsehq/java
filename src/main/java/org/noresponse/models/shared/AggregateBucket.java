package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AggregateBucket {

    @JsonProperty("start")
    private String start;

    /**
     * Assessments first answered whose creation falls in the bucket. With metric count.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("count")
    private Long count;

    /**
     * With metric count and group_by: the bucket's counts of the groups answered, largest group first.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("groups")
    private List<AggregateGroup> groups;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("score_histogram")
    private List<AggregateScoreCount> scoreHistogram;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("late_flips")
    private List<AggregateLateFlip> lateFlips;

    @JsonCreator
    public AggregateBucket(
            @JsonProperty("start") @Nonnull String start,
            @JsonProperty("count") @Nullable Long count,
            @JsonProperty("groups") @Nullable List<AggregateGroup> groups,
            @JsonProperty("score_histogram") @Nullable List<AggregateScoreCount> scoreHistogram,
            @JsonProperty("late_flips") @Nullable List<AggregateLateFlip> lateFlips) {
        this.start = Optional.ofNullable(start).orElseThrow(() -> new IllegalArgumentException("start cannot be null"));
        this.count = count;
        this.groups = groups;
        this.scoreHistogram = scoreHistogram;
        this.lateFlips = lateFlips;
    }

    public AggregateBucket(@Nonnull String start) {
        this(start, null, null, null, null);
    }

    public String start() {
        return this.start;
    }

    /**
     * Assessments first answered whose creation falls in the bucket. With metric count.
     */
    public Optional<Long> count() {
        return Optional.ofNullable(this.count);
    }

    /**
     * With metric count and group_by: the bucket's counts of the groups answered, largest group first.
     */
    public Optional<List<AggregateGroup>> groups() {
        return Optional.ofNullable(this.groups);
    }

    public Optional<List<AggregateScoreCount>> scoreHistogram() {
        return Optional.ofNullable(this.scoreHistogram);
    }

    public Optional<List<AggregateLateFlip>> lateFlips() {
        return Optional.ofNullable(this.lateFlips);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AggregateBucket withStart(@Nonnull String start) {
        this.start = Utils.checkNotNull(start, "start");
        return this;
    }

    /**
     * Assessments first answered whose creation falls in the bucket. With metric count.
     */
    public AggregateBucket withCount(@Nullable Long count) {
        this.count = count;
        return this;
    }

    /**
     * With metric count and group_by: the bucket's counts of the groups answered, largest group first.
     */
    public AggregateBucket withGroups(@Nullable List<AggregateGroup> groups) {
        this.groups = groups;
        return this;
    }

    public AggregateBucket withScoreHistogram(@Nullable List<AggregateScoreCount> scoreHistogram) {
        this.scoreHistogram = scoreHistogram;
        return this;
    }

    public AggregateBucket withLateFlips(@Nullable List<AggregateLateFlip> lateFlips) {
        this.lateFlips = lateFlips;
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
        AggregateBucket other = (AggregateBucket) o;
        return Utils.enhancedDeepEquals(this.start, other.start)
                && Utils.enhancedDeepEquals(this.count, other.count)
                && Utils.enhancedDeepEquals(this.groups, other.groups)
                && Utils.enhancedDeepEquals(this.scoreHistogram, other.scoreHistogram)
                && Utils.enhancedDeepEquals(this.lateFlips, other.lateFlips);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(start, count, groups, scoreHistogram, lateFlips);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AggregateBucket.class,
                "start",
                start,
                "count",
                count,
                "groups",
                groups,
                "scoreHistogram",
                scoreHistogram,
                "lateFlips",
                lateFlips);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String start;

        private Long count;

        private List<AggregateGroup> groups;

        private List<AggregateScoreCount> scoreHistogram;

        private List<AggregateLateFlip> lateFlips;

        private Builder() {
            // force use of static builder() method
        }

        public Builder start(@Nonnull String start) {
            this.start = Utils.checkNotNull(start, "start");
            return this;
        }

        /**
         * Assessments first answered whose creation falls in the bucket. With metric count.
         */
        public Builder count(@Nullable Long count) {
            this.count = count;
            return this;
        }

        /**
         * With metric count and group_by: the bucket's counts of the groups answered, largest group first.
         */
        public Builder groups(@Nullable List<AggregateGroup> groups) {
            this.groups = groups;
            return this;
        }

        public Builder scoreHistogram(@Nullable List<AggregateScoreCount> scoreHistogram) {
            this.scoreHistogram = scoreHistogram;
            return this;
        }

        public Builder lateFlips(@Nullable List<AggregateLateFlip> lateFlips) {
            this.lateFlips = lateFlips;
            return this;
        }

        public AggregateBucket build() {
            return new AggregateBucket(start, count, groups, scoreHistogram, lateFlips);
        }
    }
}
