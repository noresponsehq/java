package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class UsageCount {

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    @JsonProperty("day")
    private String day;

    /**
     * assessments: assessments admitted. webhook_deliveries: events sent to endpoints, one per event per
     * endpoint. key_usage_events: key-usage events accepted.
     */
    @JsonProperty("metric")
    private Metric metric;

    /**
     * assessments only.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("flow")
    private UsageCountFlow flow;

    /**
     * assessments only.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("depth")
    private UsageCountDepth depth;

    @JsonProperty("count")
    private long count;

    @JsonCreator
    public UsageCount(
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("day") @Nonnull String day,
            @JsonProperty("metric") @Nonnull Metric metric,
            @JsonProperty("flow") @Nullable UsageCountFlow flow,
            @JsonProperty("depth") @Nullable UsageCountDepth depth,
            @JsonProperty("count") long count) {
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.day = Optional.ofNullable(day).orElseThrow(() -> new IllegalArgumentException("day cannot be null"));
        this.metric =
                Optional.ofNullable(metric).orElseThrow(() -> new IllegalArgumentException("metric cannot be null"));
        this.flow = flow;
        this.depth = depth;
        this.count = count;
    }

    public UsageCount(
            @Nonnull AuditSettingsEnvironment environment, @Nonnull String day, @Nonnull Metric metric, long count) {
        this(environment, day, metric, null, null, count);
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    public String day() {
        return this.day;
    }

    /**
     * assessments: assessments admitted. webhook_deliveries: events sent to endpoints, one per event per
     * endpoint. key_usage_events: key-usage events accepted.
     */
    public Metric metric() {
        return this.metric;
    }

    /**
     * assessments only.
     */
    public Optional<UsageCountFlow> flow() {
        return Optional.ofNullable(this.flow);
    }

    /**
     * assessments only.
     */
    public Optional<UsageCountDepth> depth() {
        return Optional.ofNullable(this.depth);
    }

    public long count() {
        return this.count;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UsageCount withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public UsageCount withDay(@Nonnull String day) {
        this.day = Utils.checkNotNull(day, "day");
        return this;
    }

    /**
     * assessments: assessments admitted. webhook_deliveries: events sent to endpoints, one per event per
     * endpoint. key_usage_events: key-usage events accepted.
     */
    public UsageCount withMetric(@Nonnull Metric metric) {
        this.metric = Utils.checkNotNull(metric, "metric");
        return this;
    }

    /**
     * assessments only.
     */
    public UsageCount withFlow(@Nullable UsageCountFlow flow) {
        this.flow = flow;
        return this;
    }

    /**
     * assessments only.
     */
    public UsageCount withDepth(@Nullable UsageCountDepth depth) {
        this.depth = depth;
        return this;
    }

    public UsageCount withCount(long count) {
        this.count = count;
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
        UsageCount other = (UsageCount) o;
        return Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.day, other.day)
                && Utils.enhancedDeepEquals(this.metric, other.metric)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.count, other.count);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(environment, day, metric, flow, depth, count);
    }

    @Override
    public String toString() {
        return Utils.toString(
                UsageCount.class,
                "environment",
                environment,
                "day",
                day,
                "metric",
                metric,
                "flow",
                flow,
                "depth",
                depth,
                "count",
                count);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AuditSettingsEnvironment environment;

        private String day;

        private Metric metric;

        private UsageCountFlow flow;

        private UsageCountDepth depth;

        private long count;

        private Builder() {
            // force use of static builder() method
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder day(@Nonnull String day) {
            this.day = Utils.checkNotNull(day, "day");
            return this;
        }

        /**
         * assessments: assessments admitted. webhook_deliveries: events sent to endpoints, one per event per
         * endpoint. key_usage_events: key-usage events accepted.
         */
        public Builder metric(@Nonnull Metric metric) {
            this.metric = Utils.checkNotNull(metric, "metric");
            return this;
        }

        /**
         * assessments only.
         */
        public Builder flow(@Nullable UsageCountFlow flow) {
            this.flow = flow;
            return this;
        }

        /**
         * assessments only.
         */
        public Builder depth(@Nullable UsageCountDepth depth) {
            this.depth = depth;
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public UsageCount build() {
            return new UsageCount(environment, day, metric, flow, depth, count);
        }
    }
}
