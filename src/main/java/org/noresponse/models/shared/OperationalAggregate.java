package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * OperationalAggregate
 *
 * <p>One repeatable-read snapshot of retained request diagnostics or webhook attempts. Capture can have
 * gaps and these counts are not a billing ledger. Unattributed keys include older requests and member
 * requests.
 *
 * <p>Queue state is current retained work, not a delivery success denominator.
 */
public class OperationalAggregate {

    @JsonProperty("schema_version")
    private String schemaVersion;

    @JsonProperty("source")
    private Source source;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("as_of")
    private String asOf;

    @JsonProperty("retained_from")
    private String retainedFrom;

    @JsonProperty("retention_days")
    private long retentionDays;

    @JsonProperty("bucket")
    private RequestAggregateRequestBucket bucket;

    @JsonProperty("capture")
    private String capture;

    @JsonProperty("population")
    private String population;

    @JsonProperty("totals")
    private OperationalAggregateTotals totals;

    /**
     * Nonempty UTC buckets in ascending order. Counts include all retained matching attempts, without the
     * log page limit.
     */
    @JsonProperty("buckets")
    private List<OperationalAggregateBucket> buckets;

    /**
     * Current retained delivery queue, independent of the requested attempt window and status filter.
     * Endpoint filter applies. Does not prove customer enforcement execution.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("queue")
    private OperationalAggregateQueue queue;

    @JsonCreator
    public OperationalAggregate(
            @JsonProperty("source") @Nonnull Source source,
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("as_of") @Nonnull String asOf,
            @JsonProperty("retained_from") @Nonnull String retainedFrom,
            @JsonProperty("retention_days") long retentionDays,
            @JsonProperty("bucket") @Nonnull RequestAggregateRequestBucket bucket,
            @JsonProperty("totals") @Nonnull OperationalAggregateTotals totals,
            @JsonProperty("buckets") @Nonnull List<OperationalAggregateBucket> buckets,
            @JsonProperty("queue") @Nullable OperationalAggregateQueue queue) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.source =
                Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException("source cannot be null"));
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.asOf = Optional.ofNullable(asOf).orElseThrow(() -> new IllegalArgumentException("asOf cannot be null"));
        this.retainedFrom = Optional.ofNullable(retainedFrom)
                .orElseThrow(() -> new IllegalArgumentException("retainedFrom cannot be null"));
        this.retentionDays = retentionDays;
        this.bucket =
                Optional.ofNullable(bucket).orElseThrow(() -> new IllegalArgumentException("bucket cannot be null"));
        this.capture = Builder._SINGLETON_VALUE_Capture.value();
        this.population = Builder._SINGLETON_VALUE_Population.value();
        this.totals =
                Optional.ofNullable(totals).orElseThrow(() -> new IllegalArgumentException("totals cannot be null"));
        this.buckets =
                Optional.ofNullable(buckets).orElseThrow(() -> new IllegalArgumentException("buckets cannot be null"));
        this.queue = queue;
    }

    public OperationalAggregate(
            @Nonnull Source source,
            @Nonnull String from,
            @Nonnull String to,
            @Nonnull String asOf,
            @Nonnull String retainedFrom,
            long retentionDays,
            @Nonnull RequestAggregateRequestBucket bucket,
            @Nonnull OperationalAggregateTotals totals,
            @Nonnull List<OperationalAggregateBucket> buckets) {
        this(source, from, to, asOf, retainedFrom, retentionDays, bucket, totals, buckets, null);
    }

    public String schemaVersion() {
        return this.schemaVersion;
    }

    public Source source() {
        return this.source;
    }

    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    public String asOf() {
        return this.asOf;
    }

    public String retainedFrom() {
        return this.retainedFrom;
    }

    public long retentionDays() {
        return this.retentionDays;
    }

    public RequestAggregateRequestBucket bucket() {
        return this.bucket;
    }

    public String capture() {
        return this.capture;
    }

    public String population() {
        return this.population;
    }

    public OperationalAggregateTotals totals() {
        return this.totals;
    }

    /**
     * Nonempty UTC buckets in ascending order. Counts include all retained matching attempts, without the
     * log page limit.
     */
    public List<OperationalAggregateBucket> buckets() {
        return this.buckets;
    }

    /**
     * Current retained delivery queue, independent of the requested attempt window and status filter.
     * Endpoint filter applies. Does not prove customer enforcement execution.
     */
    public Optional<OperationalAggregateQueue> queue() {
        return Optional.ofNullable(this.queue);
    }

    public static Builder builder() {
        return new Builder();
    }

    public OperationalAggregate withSource(@Nonnull Source source) {
        this.source = Utils.checkNotNull(source, "source");
        return this;
    }

    public OperationalAggregate withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public OperationalAggregate withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public OperationalAggregate withAsOf(@Nonnull String asOf) {
        this.asOf = Utils.checkNotNull(asOf, "asOf");
        return this;
    }

    public OperationalAggregate withRetainedFrom(@Nonnull String retainedFrom) {
        this.retainedFrom = Utils.checkNotNull(retainedFrom, "retainedFrom");
        return this;
    }

    public OperationalAggregate withRetentionDays(long retentionDays) {
        this.retentionDays = retentionDays;
        return this;
    }

    public OperationalAggregate withBucket(@Nonnull RequestAggregateRequestBucket bucket) {
        this.bucket = Utils.checkNotNull(bucket, "bucket");
        return this;
    }

    public OperationalAggregate withTotals(@Nonnull OperationalAggregateTotals totals) {
        this.totals = Utils.checkNotNull(totals, "totals");
        return this;
    }

    /**
     * Nonempty UTC buckets in ascending order. Counts include all retained matching attempts, without the
     * log page limit.
     */
    public OperationalAggregate withBuckets(@Nonnull List<OperationalAggregateBucket> buckets) {
        this.buckets = Utils.checkNotNull(buckets, "buckets");
        return this;
    }

    /**
     * Current retained delivery queue, independent of the requested attempt window and status filter.
     * Endpoint filter applies. Does not prove customer enforcement execution.
     */
    public OperationalAggregate withQueue(@Nullable OperationalAggregateQueue queue) {
        this.queue = queue;
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
        OperationalAggregate other = (OperationalAggregate) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.source, other.source)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.asOf, other.asOf)
                && Utils.enhancedDeepEquals(this.retainedFrom, other.retainedFrom)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays)
                && Utils.enhancedDeepEquals(this.bucket, other.bucket)
                && Utils.enhancedDeepEquals(this.capture, other.capture)
                && Utils.enhancedDeepEquals(this.population, other.population)
                && Utils.enhancedDeepEquals(this.totals, other.totals)
                && Utils.enhancedDeepEquals(this.buckets, other.buckets)
                && Utils.enhancedDeepEquals(this.queue, other.queue);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                schemaVersion,
                source,
                from,
                to,
                asOf,
                retainedFrom,
                retentionDays,
                bucket,
                capture,
                population,
                totals,
                buckets,
                queue);
    }

    @Override
    public String toString() {
        return Utils.toString(
                OperationalAggregate.class,
                "schemaVersion",
                schemaVersion,
                "source",
                source,
                "from",
                from,
                "to",
                to,
                "asOf",
                asOf,
                "retainedFrom",
                retainedFrom,
                "retentionDays",
                retentionDays,
                "bucket",
                bucket,
                "capture",
                capture,
                "population",
                population,
                "totals",
                totals,
                "buckets",
                buckets,
                "queue",
                queue);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Source source;

        private String from;

        private String to;

        private String asOf;

        private String retainedFrom;

        private long retentionDays;

        private RequestAggregateRequestBucket bucket;

        private OperationalAggregateTotals totals;

        private List<OperationalAggregateBucket> buckets;

        private OperationalAggregateQueue queue;

        private Builder() {
            // force use of static builder() method
        }

        public Builder source(@Nonnull Source source) {
            this.source = Utils.checkNotNull(source, "source");
            return this;
        }

        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        public Builder asOf(@Nonnull String asOf) {
            this.asOf = Utils.checkNotNull(asOf, "asOf");
            return this;
        }

        public Builder retainedFrom(@Nonnull String retainedFrom) {
            this.retainedFrom = Utils.checkNotNull(retainedFrom, "retainedFrom");
            return this;
        }

        public Builder retentionDays(long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        public Builder bucket(@Nonnull RequestAggregateRequestBucket bucket) {
            this.bucket = Utils.checkNotNull(bucket, "bucket");
            return this;
        }

        public Builder totals(@Nonnull OperationalAggregateTotals totals) {
            this.totals = Utils.checkNotNull(totals, "totals");
            return this;
        }

        /**
         * Nonempty UTC buckets in ascending order. Counts include all retained matching attempts, without the
         * log page limit.
         */
        public Builder buckets(@Nonnull List<OperationalAggregateBucket> buckets) {
            this.buckets = Utils.checkNotNull(buckets, "buckets");
            return this;
        }

        /**
         * Current retained delivery queue, independent of the requested attempt window and status filter.
         * Endpoint filter applies. Does not prove customer enforcement execution.
         */
        public Builder queue(@Nullable OperationalAggregateQueue queue) {
            this.queue = queue;
            return this;
        }

        public OperationalAggregate build() {
            return new OperationalAggregate(
                    source, from, to, asOf, retainedFrom, retentionDays, bucket, totals, buckets, queue);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Capture =
                new LazySingletonValue<>("capture", "\"best_effort\"", new TypeReference<String>() {});

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Population =
                new LazySingletonValue<>("population", "\"retained_attempts\"", new TypeReference<String>() {});
    }
}
