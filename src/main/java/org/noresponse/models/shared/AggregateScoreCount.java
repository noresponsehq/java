package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AggregateScoreCount
 *
 * <p>First answers of one flow whose risk.score is in [score_min, score_max]: 0-4, 5-9 up to 95-100. A
 * pending answer has no score.
 */
public class AggregateScoreCount {
    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    @JsonProperty("flow")
    private Flow flow;

    /**
     * A multiple of 5.
     */
    @JsonProperty("score_min")
    private long scoreMin;

    @JsonProperty("score_max")
    private long scoreMax;

    @JsonProperty("count")
    private long count;

    @JsonCreator
    public AggregateScoreCount(
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("score_min") long scoreMin,
            @JsonProperty("score_max") long scoreMax,
            @JsonProperty("count") long count) {
        this.flow = Optional.ofNullable(flow).orElseThrow(() -> new IllegalArgumentException("flow cannot be null"));
        this.scoreMin = scoreMin;
        this.scoreMax = scoreMax;
        this.count = count;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public Flow flow() {
        return this.flow;
    }

    /**
     * A multiple of 5.
     */
    public long scoreMin() {
        return this.scoreMin;
    }

    public long scoreMax() {
        return this.scoreMax;
    }

    public long count() {
        return this.count;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public AggregateScoreCount withFlow(@Nonnull Flow flow) {
        this.flow = Utils.checkNotNull(flow, "flow");
        return this;
    }

    /**
     * A multiple of 5.
     */
    public AggregateScoreCount withScoreMin(long scoreMin) {
        this.scoreMin = scoreMin;
        return this;
    }

    public AggregateScoreCount withScoreMax(long scoreMax) {
        this.scoreMax = scoreMax;
        return this;
    }

    public AggregateScoreCount withCount(long count) {
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
        AggregateScoreCount other = (AggregateScoreCount) o;
        return Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.scoreMin, other.scoreMin)
                && Utils.enhancedDeepEquals(this.scoreMax, other.scoreMax)
                && Utils.enhancedDeepEquals(this.count, other.count);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(flow, scoreMin, scoreMax, count);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AggregateScoreCount.class, "flow", flow, "scoreMin", scoreMin, "scoreMax", scoreMax, "count", count);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Flow flow;

        private long scoreMin;

        private long scoreMax;

        private long count;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The event an assessment is made for: account_creation (a user creates an account), login (a user
         * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
         * reported with the decision for this flow.
         */
        public Builder flow(@Nonnull Flow flow) {
            this.flow = Utils.checkNotNull(flow, "flow");
            return this;
        }

        /**
         * A multiple of 5.
         */
        public Builder scoreMin(long scoreMin) {
            this.scoreMin = scoreMin;
            return this;
        }

        public Builder scoreMax(long scoreMax) {
            this.scoreMax = scoreMax;
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public AggregateScoreCount build() {
            return new AggregateScoreCount(flow, scoreMin, scoreMax, count);
        }
    }
}
