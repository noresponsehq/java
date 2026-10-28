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
 * AssessmentAggregateRequest
 *
 * <p>Count this environment's assessments created in [from,to) by bucket, from counts kept as each
 * assessment is first answered, not by scanning assessments. Each assessment counts once, by its first
 * answer (the answer served when it was first answered); later revisions and late results do not count
 * it again, and late_flips counts its later account decision changes. from and to start a bucket (an
 * hour, a UTC day, or a week from Monday 00:00 UTC), cover at most 366 days (31 for hour buckets) and
 * from is inside the retention period.
 *
 * <p>The filters are POST /v1/assessments/search's and describe the first answer, so a cell drills down
 * to search with the same filters, revision first and the cell's bucket as from and to. Counts hold no
 * identifier and stay when an assessment or account is deleted or erased; they expire with retention
 * and go with the workspace. Reasons are counted by code and impact band, not by weight.
 *
 * <p>No Idempotency-Key.
 */
public class AssessmentAggregateRequest {
    /**
     * The first bucket's start.
     */
    @JsonProperty("from")
    private String from;

    /**
     * The end, excluded: the start of the bucket after the last.
     */
    @JsonProperty("to")
    private String to;

    /**
     * The period each count covers, in UTC; a week starts on Monday.
     */
    @JsonProperty("bucket")
    private Bucket bucket;

    /**
     * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
     * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
     * more than the bucket's count.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("group_by")
    private List<AggregateDimension> groupBy;

    /**
     * count: each bucket's count and groups. score_histogram: first answers by flow and 5-point score
     * bucket. score_outcomes: the histogram's first answers that have a reported outcome, by flow, 5-point
     * score bucket and the latest outcome reported for the assessment (POST
     * /v1/assessments/{assessment_id}/outcomes); the rest of a bin has none reported.
     *
     * <p>late_flips: changes of an assessment's account decision for its flow after its first answer, by
     * from, to and delay.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("metrics")
    private List<AssessmentAggregateRequestMetric> metrics;

    /**
     * The most groups answered: the largest over the whole range, then by value. groups_truncated says
     * when there were more.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("limit")
    private Long limit;

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("flow")
    private Flow flow;

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("status")
    private Status status;

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("recommendation")
    private Recommendation recommendation;

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decision")
    private Decision decision;

    /**
     * Which answer of each assessment the filters describe: latest, the latest stored revision, or first,
     * the first answer, as served when the assessment was first answered (POST /v1/assessments/aggregate
     * counts first answers). A cell of an aggregate drills down to POST /v1/assessments/search with the
     * same filters, revision first and the cell's bucket as from and to.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("revision")
    private AnswerRevision revision;

    @JsonCreator
    public AssessmentAggregateRequest(
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("bucket") @Nonnull Bucket bucket,
            @JsonProperty("group_by") @Nullable List<AggregateDimension> groupBy,
            @JsonProperty("metrics") @Nullable List<AssessmentAggregateRequestMetric> metrics,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("flow") @Nullable Flow flow,
            @JsonProperty("status") @Nullable Status status,
            @JsonProperty("recommendation") @Nullable Recommendation recommendation,
            @JsonProperty("decision") @Nullable Decision decision,
            @JsonProperty("revision") @Nullable AnswerRevision revision) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.bucket =
                Optional.ofNullable(bucket).orElseThrow(() -> new IllegalArgumentException("bucket cannot be null"));
        this.groupBy = groupBy;
        this.metrics = metrics;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.flow = flow;
        this.status = status;
        this.recommendation = recommendation;
        this.decision = decision;
        this.revision = revision;
    }

    public AssessmentAggregateRequest(@Nonnull String from, @Nonnull String to, @Nonnull Bucket bucket) {
        this(from, to, bucket, null, null, null, null, null, null, null, null);
    }

    /**
     * The first bucket's start.
     */
    public String from() {
        return this.from;
    }

    /**
     * The end, excluded: the start of the bucket after the last.
     */
    public String to() {
        return this.to;
    }

    /**
     * The period each count covers, in UTC; a week starts on Monday.
     */
    public Bucket bucket() {
        return this.bucket;
    }

    /**
     * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
     * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
     * more than the bucket's count.
     */
    public Optional<List<AggregateDimension>> groupBy() {
        return Optional.ofNullable(this.groupBy);
    }

    /**
     * count: each bucket's count and groups. score_histogram: first answers by flow and 5-point score
     * bucket. score_outcomes: the histogram's first answers that have a reported outcome, by flow, 5-point
     * score bucket and the latest outcome reported for the assessment (POST
     * /v1/assessments/{assessment_id}/outcomes); the rest of a bin has none reported.
     *
     * <p>late_flips: changes of an assessment's account decision for its flow after its first answer, by
     * from, to and delay.
     */
    public Optional<List<AssessmentAggregateRequestMetric>> metrics() {
        return Optional.ofNullable(this.metrics);
    }

    /**
     * The most groups answered: the largest over the whole range, then by value. groups_truncated says
     * when there were more.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public Optional<Flow> flow() {
        return Optional.ofNullable(this.flow);
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public Optional<Status> status() {
        return Optional.ofNullable(this.status);
    }

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public Optional<Recommendation> recommendation() {
        return Optional.ofNullable(this.recommendation);
    }

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    public Optional<Decision> decision() {
        return Optional.ofNullable(this.decision);
    }

    /**
     * Which answer of each assessment the filters describe: latest, the latest stored revision, or first,
     * the first answer, as served when the assessment was first answered (POST /v1/assessments/aggregate
     * counts first answers). A cell of an aggregate drills down to POST /v1/assessments/search with the
     * same filters, revision first and the cell's bucket as from and to.
     */
    public Optional<AnswerRevision> revision() {
        return Optional.ofNullable(this.revision);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The first bucket's start.
     */
    public AssessmentAggregateRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    /**
     * The end, excluded: the start of the bucket after the last.
     */
    public AssessmentAggregateRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    /**
     * The period each count covers, in UTC; a week starts on Monday.
     */
    public AssessmentAggregateRequest withBucket(@Nonnull Bucket bucket) {
        this.bucket = Utils.checkNotNull(bucket, "bucket");
        return this;
    }

    /**
     * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
     * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
     * more than the bucket's count.
     */
    public AssessmentAggregateRequest withGroupBy(@Nullable List<AggregateDimension> groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    /**
     * count: each bucket's count and groups. score_histogram: first answers by flow and 5-point score
     * bucket. score_outcomes: the histogram's first answers that have a reported outcome, by flow, 5-point
     * score bucket and the latest outcome reported for the assessment (POST
     * /v1/assessments/{assessment_id}/outcomes); the rest of a bin has none reported.
     *
     * <p>late_flips: changes of an assessment's account decision for its flow after its first answer, by
     * from, to and delay.
     */
    public AssessmentAggregateRequest withMetrics(@Nullable List<AssessmentAggregateRequestMetric> metrics) {
        this.metrics = metrics;
        return this;
    }

    /**
     * The most groups answered: the largest over the whole range, then by value. groups_truncated says
     * when there were more.
     */
    public AssessmentAggregateRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public AssessmentAggregateRequest withFlow(@Nullable Flow flow) {
        this.flow = flow;
        return this;
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public AssessmentAggregateRequest withStatus(@Nullable Status status) {
        this.status = status;
        return this;
    }

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public AssessmentAggregateRequest withRecommendation(@Nullable Recommendation recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    public AssessmentAggregateRequest withDecision(@Nullable Decision decision) {
        this.decision = decision;
        return this;
    }

    /**
     * Which answer of each assessment the filters describe: latest, the latest stored revision, or first,
     * the first answer, as served when the assessment was first answered (POST /v1/assessments/aggregate
     * counts first answers). A cell of an aggregate drills down to POST /v1/assessments/search with the
     * same filters, revision first and the cell's bucket as from and to.
     */
    public AssessmentAggregateRequest withRevision(@Nullable AnswerRevision revision) {
        this.revision = revision;
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
        AssessmentAggregateRequest other = (AssessmentAggregateRequest) o;
        return Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.bucket, other.bucket)
                && Utils.enhancedDeepEquals(this.groupBy, other.groupBy)
                && Utils.enhancedDeepEquals(this.metrics, other.metrics)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.recommendation, other.recommendation)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.revision, other.revision);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                from, to, bucket, groupBy, metrics, limit, flow, status, recommendation, decision, revision);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentAggregateRequest.class,
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
                "limit",
                limit,
                "flow",
                flow,
                "status",
                status,
                "recommendation",
                recommendation,
                "decision",
                decision,
                "revision",
                revision);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String from;

        private String to;

        private Bucket bucket;

        private List<AggregateDimension> groupBy;

        private List<AssessmentAggregateRequestMetric> metrics;

        private Long limit;

        private Flow flow;

        private Status status;

        private Recommendation recommendation;

        private Decision decision;

        private AnswerRevision revision;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The first bucket's start.
         */
        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        /**
         * The end, excluded: the start of the bucket after the last.
         */
        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        /**
         * The period each count covers, in UTC; a week starts on Monday.
         */
        public Builder bucket(@Nonnull Bucket bucket) {
            this.bucket = Utils.checkNotNull(bucket, "bucket");
            return this;
        }

        /**
         * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
         * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
         * more than the bucket's count.
         */
        public Builder groupBy(@Nullable List<AggregateDimension> groupBy) {
            this.groupBy = groupBy;
            return this;
        }

        /**
         * count: each bucket's count and groups. score_histogram: first answers by flow and 5-point score
         * bucket. score_outcomes: the histogram's first answers that have a reported outcome, by flow, 5-point
         * score bucket and the latest outcome reported for the assessment (POST
         * /v1/assessments/{assessment_id}/outcomes); the rest of a bin has none reported.
         *
         * <p>late_flips: changes of an assessment's account decision for its flow after its first answer, by
         * from, to and delay.
         */
        public Builder metrics(@Nullable List<AssessmentAggregateRequestMetric> metrics) {
            this.metrics = metrics;
            return this;
        }

        /**
         * The most groups answered: the largest over the whole range, then by value. groups_truncated says
         * when there were more.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * The event an assessment is made for: account_creation (a user creates an account), login (a user
         * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
         * reported with the decision for this flow.
         */
        public Builder flow(@Nullable Flow flow) {
            this.flow = flow;
            return this;
        }

        /**
         * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
         * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
         * one lane is still running or did not settle (see incomplete_sources).
         *
         * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
         */
        public Builder status(@Nullable Status status) {
            this.status = status;
            return this;
        }

        /**
         * The recommended action: allow, step_up or review. Your application applies it.
         */
        public Builder recommendation(@Nullable Recommendation recommendation) {
            this.recommendation = recommendation;
            return this;
        }

        /**
         * An assessment's own decision: block when its stored risk.block is true, otherwise its
         * recommendation. It is not the account's decision or held-admission state.
         */
        public Builder decision(@Nullable Decision decision) {
            this.decision = decision;
            return this;
        }

        /**
         * Which answer of each assessment the filters describe: latest, the latest stored revision, or first,
         * the first answer, as served when the assessment was first answered (POST /v1/assessments/aggregate
         * counts first answers). A cell of an aggregate drills down to POST /v1/assessments/search with the
         * same filters, revision first and the cell's bucket as from and to.
         */
        public Builder revision(@Nullable AnswerRevision revision) {
            this.revision = revision;
            return this;
        }

        public AssessmentAggregateRequest build() {
            return new AssessmentAggregateRequest(
                    from, to, bucket, groupBy, metrics, limit, flow, status, recommendation, decision, revision);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
