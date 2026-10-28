package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AggregateScoreOutcome
 *
 * <p>First answers of one flow whose risk.score is in [score_min, score_max] and whose assessment's
 * latest reported outcome (the latest received) is outcome. Counted when the first answer is counted
 * and moved when a later outcome wins; outcomes reported before the first answer was counted are
 * counted with it. An outcome reported before this count existed is counted only once another outcome
 * is reported for the assessment.
 */
public class AggregateScoreOutcome {
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

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    @JsonProperty("outcome")
    private Outcome outcome;

    @JsonProperty("count")
    private long count;

    @JsonCreator
    public AggregateScoreOutcome(
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("score_min") long scoreMin,
            @JsonProperty("score_max") long scoreMax,
            @JsonProperty("outcome") @Nonnull Outcome outcome,
            @JsonProperty("count") long count) {
        this.flow = Optional.ofNullable(flow).orElseThrow(() -> new IllegalArgumentException("flow cannot be null"));
        this.scoreMin = scoreMin;
        this.scoreMax = scoreMax;
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
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

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public Outcome outcome() {
        return this.outcome;
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
    public AggregateScoreOutcome withFlow(@Nonnull Flow flow) {
        this.flow = Utils.checkNotNull(flow, "flow");
        return this;
    }

    /**
     * A multiple of 5.
     */
    public AggregateScoreOutcome withScoreMin(long scoreMin) {
        this.scoreMin = scoreMin;
        return this;
    }

    public AggregateScoreOutcome withScoreMax(long scoreMax) {
        this.scoreMax = scoreMax;
        return this;
    }

    /**
     * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
     * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
     * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
     * recorded for an assessment wins.
     */
    public AggregateScoreOutcome withOutcome(@Nonnull Outcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    public AggregateScoreOutcome withCount(long count) {
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
        AggregateScoreOutcome other = (AggregateScoreOutcome) o;
        return Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.scoreMin, other.scoreMin)
                && Utils.enhancedDeepEquals(this.scoreMax, other.scoreMax)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.count, other.count);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(flow, scoreMin, scoreMax, outcome, count);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AggregateScoreOutcome.class,
                "flow",
                flow,
                "scoreMin",
                scoreMin,
                "scoreMax",
                scoreMax,
                "outcome",
                outcome,
                "count",
                count);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Flow flow;

        private long scoreMin;

        private long scoreMax;

        private Outcome outcome;

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

        /**
         * What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
         * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
         * workspace for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
         * recorded for an assessment wins.
         */
        public Builder outcome(@Nonnull Outcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public AggregateScoreOutcome build() {
            return new AggregateScoreOutcome(flow, scoreMin, scoreMax, outcome, count);
        }
    }
}
