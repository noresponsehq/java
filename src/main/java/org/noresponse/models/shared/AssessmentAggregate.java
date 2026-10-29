package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentAggregate
 *
 * <p>Body of the 200 answer to POST /v1/assessments/aggregate: one entry per bucket of [from,to), empty
 * buckets included. Counts are of first answers and stay when an assessment or account is deleted or
 * erased; search returns only assessments still retained.
 */
public class AssessmentAggregate {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    /**
     * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
     * are whole UTC hours in any time zone.
     */
    @JsonProperty("bucket")
    private Bucket bucket;

    @JsonProperty("group_by")
    private List<AggregateDimension> groupBy;

    @JsonProperty("metrics")
    private List<AssessmentAggregateMetric> metrics;

    /**
     * The time zone whose local midnight started each day and week bucket, as requested; UTC when the
     * request named none.
     */
    @JsonProperty("time_zone")
    private String timeZone;

    /**
     * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
     * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
     * created_at; see assessment expires_at.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("retention_days")
    private Long retentionDays;

    /**
     * true when more groups had counts than limit allowed.
     */
    @JsonProperty("groups_truncated")
    private boolean groupsTruncated;

    /**
     * Captured in the same database snapshot as the counts. Capture start times describe when counting
     * began, not the earliest creation time counted: an older assessment may first answer later. Older
     * outcomes are not inferred to be absent.
     *
     * <p>Erased detail cannot be returned by drill-down even while its anonymous count remains.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("coverage")
    private AssessmentAggregateCoverage coverage;

    @JsonProperty("buckets")
    private List<AggregateBucket> buckets;

    @JsonCreator
    public AssessmentAggregate(
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("bucket") @Nonnull Bucket bucket,
            @JsonProperty("group_by") @Nonnull List<AggregateDimension> groupBy,
            @JsonProperty("metrics") @Nonnull List<AssessmentAggregateMetric> metrics,
            @JsonProperty("time_zone") @Nonnull String timeZone,
            @JsonProperty("retention_days") @Nullable Long retentionDays,
            @JsonProperty("groups_truncated") boolean groupsTruncated,
            @JsonProperty("coverage") @Nullable AssessmentAggregateCoverage coverage,
            @JsonProperty("buckets") @Nonnull List<AggregateBucket> buckets) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.bucket =
                Optional.ofNullable(bucket).orElseThrow(() -> new IllegalArgumentException("bucket cannot be null"));
        this.groupBy =
                Optional.ofNullable(groupBy).orElseThrow(() -> new IllegalArgumentException("groupBy cannot be null"));
        this.metrics =
                Optional.ofNullable(metrics).orElseThrow(() -> new IllegalArgumentException("metrics cannot be null"));
        this.timeZone = Optional.ofNullable(timeZone)
                .orElseThrow(() -> new IllegalArgumentException("timeZone cannot be null"));
        this.retentionDays = Optional.ofNullable(retentionDays).orElse(Builder._SINGLETON_VALUE_RetentionDays.value());
        this.groupsTruncated = groupsTruncated;
        this.coverage = coverage;
        this.buckets =
                Optional.ofNullable(buckets).orElseThrow(() -> new IllegalArgumentException("buckets cannot be null"));
    }

    public AssessmentAggregate(
            @Nonnull String from,
            @Nonnull String to,
            @Nonnull Bucket bucket,
            @Nonnull List<AggregateDimension> groupBy,
            @Nonnull List<AssessmentAggregateMetric> metrics,
            @Nonnull String timeZone,
            boolean groupsTruncated,
            @Nonnull List<AggregateBucket> buckets) {
        this(from, to, bucket, groupBy, metrics, timeZone, null, groupsTruncated, null, buckets);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    /**
     * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
     * are whole UTC hours in any time zone.
     */
    public Bucket bucket() {
        return this.bucket;
    }

    public List<AggregateDimension> groupBy() {
        return this.groupBy;
    }

    public List<AssessmentAggregateMetric> metrics() {
        return this.metrics;
    }

    /**
     * The time zone whose local midnight started each day and week bucket, as requested; UTC when the
     * request named none.
     */
    public String timeZone() {
        return this.timeZone;
    }

    /**
     * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
     * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
     * created_at; see assessment expires_at.
     */
    public Optional<Long> retentionDays() {
        return Optional.ofNullable(this.retentionDays);
    }

    /**
     * true when more groups had counts than limit allowed.
     */
    public boolean groupsTruncated() {
        return this.groupsTruncated;
    }

    /**
     * Captured in the same database snapshot as the counts. Capture start times describe when counting
     * began, not the earliest creation time counted: an older assessment may first answer later. Older
     * outcomes are not inferred to be absent.
     *
     * <p>Erased detail cannot be returned by drill-down even while its anonymous count remains.
     */
    public Optional<AssessmentAggregateCoverage> coverage() {
        return Optional.ofNullable(this.coverage);
    }

    public List<AggregateBucket> buckets() {
        return this.buckets;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AssessmentAggregate withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public AssessmentAggregate withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    /**
     * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
     * are whole UTC hours in any time zone.
     */
    public AssessmentAggregate withBucket(@Nonnull Bucket bucket) {
        this.bucket = Utils.checkNotNull(bucket, "bucket");
        return this;
    }

    public AssessmentAggregate withGroupBy(@Nonnull List<AggregateDimension> groupBy) {
        this.groupBy = Utils.checkNotNull(groupBy, "groupBy");
        return this;
    }

    public AssessmentAggregate withMetrics(@Nonnull List<AssessmentAggregateMetric> metrics) {
        this.metrics = Utils.checkNotNull(metrics, "metrics");
        return this;
    }

    /**
     * The time zone whose local midnight started each day and week bucket, as requested; UTC when the
     * request named none.
     */
    public AssessmentAggregate withTimeZone(@Nonnull String timeZone) {
        this.timeZone = Utils.checkNotNull(timeZone, "timeZone");
        return this;
    }

    /**
     * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
     * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
     * created_at; see assessment expires_at.
     */
    public AssessmentAggregate withRetentionDays(@Nullable Long retentionDays) {
        this.retentionDays = retentionDays;
        return this;
    }

    /**
     * true when more groups had counts than limit allowed.
     */
    public AssessmentAggregate withGroupsTruncated(boolean groupsTruncated) {
        this.groupsTruncated = groupsTruncated;
        return this;
    }

    /**
     * Captured in the same database snapshot as the counts. Capture start times describe when counting
     * began, not the earliest creation time counted: an older assessment may first answer later. Older
     * outcomes are not inferred to be absent.
     *
     * <p>Erased detail cannot be returned by drill-down even while its anonymous count remains.
     */
    public AssessmentAggregate withCoverage(@Nullable AssessmentAggregateCoverage coverage) {
        this.coverage = coverage;
        return this;
    }

    public AssessmentAggregate withBuckets(@Nonnull List<AggregateBucket> buckets) {
        this.buckets = Utils.checkNotNull(buckets, "buckets");
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
        AssessmentAggregate other = (AssessmentAggregate) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.bucket, other.bucket)
                && Utils.enhancedDeepEquals(this.groupBy, other.groupBy)
                && Utils.enhancedDeepEquals(this.metrics, other.metrics)
                && Utils.enhancedDeepEquals(this.timeZone, other.timeZone)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays)
                && Utils.enhancedDeepEquals(this.groupsTruncated, other.groupsTruncated)
                && Utils.enhancedDeepEquals(this.coverage, other.coverage)
                && Utils.enhancedDeepEquals(this.buckets, other.buckets);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                schemaVersion,
                from,
                to,
                bucket,
                groupBy,
                metrics,
                timeZone,
                retentionDays,
                groupsTruncated,
                coverage,
                buckets);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentAggregate.class,
                "schemaVersion",
                schemaVersion,
                "from",
                from,
                "to",
                to,
                "bucket",
                bucket,
                "groupBy",
                groupBy,
                "metrics",
                metrics,
                "timeZone",
                timeZone,
                "retentionDays",
                retentionDays,
                "groupsTruncated",
                groupsTruncated,
                "coverage",
                coverage,
                "buckets",
                buckets);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String from;

        private String to;

        private Bucket bucket;

        private List<AggregateDimension> groupBy;

        private List<AssessmentAggregateMetric> metrics;

        private String timeZone;

        private Long retentionDays;

        private boolean groupsTruncated;

        private AssessmentAggregateCoverage coverage;

        private List<AggregateBucket> buckets;

        private Builder() {
            // force use of static builder() method
        }

        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        /**
         * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
         * are whole UTC hours in any time zone.
         */
        public Builder bucket(@Nonnull Bucket bucket) {
            this.bucket = Utils.checkNotNull(bucket, "bucket");
            return this;
        }

        public Builder groupBy(@Nonnull List<AggregateDimension> groupBy) {
            this.groupBy = Utils.checkNotNull(groupBy, "groupBy");
            return this;
        }

        public Builder metrics(@Nonnull List<AssessmentAggregateMetric> metrics) {
            this.metrics = Utils.checkNotNull(metrics, "metrics");
            return this;
        }

        /**
         * The time zone whose local midnight started each day and week bucket, as requested; UTC when the
         * request named none.
         */
        public Builder timeZone(@Nonnull String timeZone) {
            this.timeZone = Utils.checkNotNull(timeZone, "timeZone");
            return this;
        }

        /**
         * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
         * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
         * created_at; see assessment expires_at.
         */
        public Builder retentionDays(@Nullable Long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        /**
         * true when more groups had counts than limit allowed.
         */
        public Builder groupsTruncated(boolean groupsTruncated) {
            this.groupsTruncated = groupsTruncated;
            return this;
        }

        /**
         * Captured in the same database snapshot as the counts. Capture start times describe when counting
         * began, not the earliest creation time counted: an older assessment may first answer later. Older
         * outcomes are not inferred to be absent.
         *
         * <p>Erased detail cannot be returned by drill-down even while its anonymous count remains.
         */
        public Builder coverage(@Nullable AssessmentAggregateCoverage coverage) {
            this.coverage = coverage;
            return this;
        }

        public Builder buckets(@Nonnull List<AggregateBucket> buckets) {
            this.buckets = Utils.checkNotNull(buckets, "buckets");
            return this;
        }

        public AssessmentAggregate build() {
            return new AssessmentAggregate(
                    from, to, bucket, groupBy, metrics, timeZone, retentionDays, groupsTruncated, coverage, buckets);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_RetentionDays =
                new LazySingletonValue<>("retention_days", "365", new TypeReference<Long>() {});
    }
}
