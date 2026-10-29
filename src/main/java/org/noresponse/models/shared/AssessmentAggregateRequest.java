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
 * hour, or a day or a week from Monday that starts at midnight in time_zone, UTC by default), cover at
 * most 366 days (31 for hour buckets, 92 for day and week buckets in a time_zone other than UTC) and
 * from is inside the retention period.
 *
 * <p>The filters are POST /v1/assessments/search's and describe the first answer, so a cell drills down
 * to search with the same filters, revision first and the cell's bucket as from and to. Counts hold no
 * identifier and stay when an assessment or account is deleted or erased; they expire with retention
 * and go with the workspace. Reasons are counted by code and impact band, not by weight.
 *
 * <p>No Idempotency-Key. Select retained_first_answers for exact facet filters and pairs over retained
 * detail. This mode supports count, score_histogram and score_outcomes, and refuses late_flips.
 *
 * <p>It has a 31-day and 100000-record work budget.
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
     * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
     * are whole UTC hours in any time zone.
     */
    @JsonProperty("bucket")
    private Bucket bucket;

    /**
     * The IANA time zone whose local midnight starts each day and week bucket, such as Europe/London or
     * Asia/Kolkata; daylight saving changes make those days 23 or 25 hours long. A name the zone database
     * does not know is refused (400 invalid_request naming time_zone). Day and week buckets in a zone
     * other than UTC cover at most 92 days (400 invalid_request naming to); a year of them reads too many
     * hourly counts to answer in time.
     *
     * <p>Hour buckets stay whole UTC hours. Bucket starts, from and to stay instants. With the default
     * population, a zone whose local midnight falls inside a UTC hour (a half-hour offset such as
     * Asia/Kolkata) is refused for day and week buckets, as those counts are kept by the hour;
     * retained_first_answers counts it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("time_zone")
    private String timeZone;

    /**
     * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
     * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
     * more than the bucket's count. With population retained_first_answers, both dimensions can be facets;
     * counts cover retained first answers only.
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
     * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
     * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
     * exists and evidence is incomplete; assessment_url identifies continuing work when present.
     *
     * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
     * sources answered.
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

    /**
     * first_answers uses anonymous counters that can include erased detail. retained_first_answers
     * computes exact intersections over retained first answers within 31 days. Requests exceeding the
     * 100000-record work budget are refused.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("population")
    private Population population;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("band")
    private Band band;

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reason")
    private ReasonCode reason;

    /**
     * The selected reason's served impact band. Requires reason.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("impact")
    private Impact impact;

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("lane")
    private Lane lane;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("country")
    private String country;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("asn")
    private Long asn;

    /**
     * Inclusive lower score bound. Pending answers without a score do not match.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("score_min")
    private Long scoreMin;

    /**
     * Inclusive upper score bound, at least score_min when both are supplied.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("score_max")
    private Long scoreMax;

    /**
     * The list action captured at the first answer. Requires revision first. Older answers without
     * captured list state do not match, including none.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("list_match")
    private ListMatch listMatch;

    @JsonCreator
    public AssessmentAggregateRequest(
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("bucket") @Nonnull Bucket bucket,
            @JsonProperty("time_zone") @Nullable String timeZone,
            @JsonProperty("group_by") @Nullable List<AggregateDimension> groupBy,
            @JsonProperty("metrics") @Nullable List<AssessmentAggregateRequestMetric> metrics,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("flow") @Nullable Flow flow,
            @JsonProperty("status") @Nullable Status status,
            @JsonProperty("recommendation") @Nullable Recommendation recommendation,
            @JsonProperty("decision") @Nullable Decision decision,
            @JsonProperty("revision") @Nullable AnswerRevision revision,
            @JsonProperty("population") @Nullable Population population,
            @JsonProperty("band") @Nullable Band band,
            @JsonProperty("reason") @Nullable ReasonCode reason,
            @JsonProperty("impact") @Nullable Impact impact,
            @JsonProperty("lane") @Nullable Lane lane,
            @JsonProperty("country") @Nullable String country,
            @JsonProperty("asn") @Nullable Long asn,
            @JsonProperty("score_min") @Nullable Long scoreMin,
            @JsonProperty("score_max") @Nullable Long scoreMax,
            @JsonProperty("list_match") @Nullable ListMatch listMatch) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.bucket =
                Optional.ofNullable(bucket).orElseThrow(() -> new IllegalArgumentException("bucket cannot be null"));
        this.timeZone = Optional.ofNullable(timeZone).orElse(Builder._SINGLETON_VALUE_TimeZone.value());
        this.groupBy = groupBy;
        this.metrics = metrics;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.flow = flow;
        this.status = status;
        this.recommendation = recommendation;
        this.decision = decision;
        this.revision = revision;
        this.population = population;
        this.band = band;
        this.reason = reason;
        this.impact = impact;
        this.lane = lane;
        this.country = country;
        this.asn = asn;
        this.scoreMin = scoreMin;
        this.scoreMax = scoreMax;
        this.listMatch = listMatch;
    }

    public AssessmentAggregateRequest(@Nonnull String from, @Nonnull String to, @Nonnull Bucket bucket) {
        this(
                from,
                to,
                bucket,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
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
     * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
     * are whole UTC hours in any time zone.
     */
    public Bucket bucket() {
        return this.bucket;
    }

    /**
     * The IANA time zone whose local midnight starts each day and week bucket, such as Europe/London or
     * Asia/Kolkata; daylight saving changes make those days 23 or 25 hours long. A name the zone database
     * does not know is refused (400 invalid_request naming time_zone). Day and week buckets in a zone
     * other than UTC cover at most 92 days (400 invalid_request naming to); a year of them reads too many
     * hourly counts to answer in time.
     *
     * <p>Hour buckets stay whole UTC hours. Bucket starts, from and to stay instants. With the default
     * population, a zone whose local midnight falls inside a UTC hour (a half-hour offset such as
     * Asia/Kolkata) is refused for day and week buckets, as those counts are kept by the hour;
     * retained_first_answers counts it.
     */
    public Optional<String> timeZone() {
        return Optional.ofNullable(this.timeZone);
    }

    /**
     * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
     * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
     * more than the bucket's count. With population retained_first_answers, both dimensions can be facets;
     * counts cover retained first answers only.
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
     * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
     * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
     * exists and evidence is incomplete; assessment_url identifies continuing work when present.
     *
     * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
     * sources answered.
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

    /**
     * first_answers uses anonymous counters that can include erased detail. retained_first_answers
     * computes exact intersections over retained first answers within 31 days. Requests exceeding the
     * 100000-record work budget are refused.
     */
    public Optional<Population> population() {
        return Optional.ofNullable(this.population);
    }

    public Optional<Band> band() {
        return Optional.ofNullable(this.band);
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public Optional<ReasonCode> reason() {
        return Optional.ofNullable(this.reason);
    }

    /**
     * The selected reason's served impact band. Requires reason.
     */
    public Optional<Impact> impact() {
        return Optional.ofNullable(this.impact);
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public Optional<Lane> lane() {
        return Optional.ofNullable(this.lane);
    }

    public Optional<String> country() {
        return Optional.ofNullable(this.country);
    }

    public Optional<Long> asn() {
        return Optional.ofNullable(this.asn);
    }

    /**
     * Inclusive lower score bound. Pending answers without a score do not match.
     */
    public Optional<Long> scoreMin() {
        return Optional.ofNullable(this.scoreMin);
    }

    /**
     * Inclusive upper score bound, at least score_min when both are supplied.
     */
    public Optional<Long> scoreMax() {
        return Optional.ofNullable(this.scoreMax);
    }

    /**
     * The list action captured at the first answer. Requires revision first. Older answers without
     * captured list state do not match, including none.
     */
    public Optional<ListMatch> listMatch() {
        return Optional.ofNullable(this.listMatch);
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
     * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
     * are whole UTC hours in any time zone.
     */
    public AssessmentAggregateRequest withBucket(@Nonnull Bucket bucket) {
        this.bucket = Utils.checkNotNull(bucket, "bucket");
        return this;
    }

    /**
     * The IANA time zone whose local midnight starts each day and week bucket, such as Europe/London or
     * Asia/Kolkata; daylight saving changes make those days 23 or 25 hours long. A name the zone database
     * does not know is refused (400 invalid_request naming time_zone). Day and week buckets in a zone
     * other than UTC cover at most 92 days (400 invalid_request naming to); a year of them reads too many
     * hourly counts to answer in time.
     *
     * <p>Hour buckets stay whole UTC hours. Bucket starts, from and to stay instants. With the default
     * population, a zone whose local midnight falls inside a UTC hour (a half-hour offset such as
     * Asia/Kolkata) is refused for day and week buckets, as those counts are kept by the hour;
     * retained_first_answers counts it.
     */
    public AssessmentAggregateRequest withTimeZone(@Nullable String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
     * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
     * more than the bucket's count. With population retained_first_answers, both dimensions can be facets;
     * counts cover retained first answers only.
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
     * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
     * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
     * exists and evidence is incomplete; assessment_url identifies continuing work when present.
     *
     * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
     * sources answered.
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

    /**
     * first_answers uses anonymous counters that can include erased detail. retained_first_answers
     * computes exact intersections over retained first answers within 31 days. Requests exceeding the
     * 100000-record work budget are refused.
     */
    public AssessmentAggregateRequest withPopulation(@Nullable Population population) {
        this.population = population;
        return this;
    }

    public AssessmentAggregateRequest withBand(@Nullable Band band) {
        this.band = band;
        return this;
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public AssessmentAggregateRequest withReason(@Nullable ReasonCode reason) {
        this.reason = reason;
        return this;
    }

    /**
     * The selected reason's served impact band. Requires reason.
     */
    public AssessmentAggregateRequest withImpact(@Nullable Impact impact) {
        this.impact = impact;
        return this;
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public AssessmentAggregateRequest withLane(@Nullable Lane lane) {
        this.lane = lane;
        return this;
    }

    public AssessmentAggregateRequest withCountry(@Nullable String country) {
        this.country = country;
        return this;
    }

    public AssessmentAggregateRequest withAsn(@Nullable Long asn) {
        this.asn = asn;
        return this;
    }

    /**
     * Inclusive lower score bound. Pending answers without a score do not match.
     */
    public AssessmentAggregateRequest withScoreMin(@Nullable Long scoreMin) {
        this.scoreMin = scoreMin;
        return this;
    }

    /**
     * Inclusive upper score bound, at least score_min when both are supplied.
     */
    public AssessmentAggregateRequest withScoreMax(@Nullable Long scoreMax) {
        this.scoreMax = scoreMax;
        return this;
    }

    /**
     * The list action captured at the first answer. Requires revision first. Older answers without
     * captured list state do not match, including none.
     */
    public AssessmentAggregateRequest withListMatch(@Nullable ListMatch listMatch) {
        this.listMatch = listMatch;
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
                && Utils.enhancedDeepEquals(this.timeZone, other.timeZone)
                && Utils.enhancedDeepEquals(this.groupBy, other.groupBy)
                && Utils.enhancedDeepEquals(this.metrics, other.metrics)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.recommendation, other.recommendation)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.revision, other.revision)
                && Utils.enhancedDeepEquals(this.population, other.population)
                && Utils.enhancedDeepEquals(this.band, other.band)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.impact, other.impact)
                && Utils.enhancedDeepEquals(this.lane, other.lane)
                && Utils.enhancedDeepEquals(this.country, other.country)
                && Utils.enhancedDeepEquals(this.asn, other.asn)
                && Utils.enhancedDeepEquals(this.scoreMin, other.scoreMin)
                && Utils.enhancedDeepEquals(this.scoreMax, other.scoreMax)
                && Utils.enhancedDeepEquals(this.listMatch, other.listMatch);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                from,
                to,
                bucket,
                timeZone,
                groupBy,
                metrics,
                limit,
                flow,
                status,
                recommendation,
                decision,
                revision,
                population,
                band,
                reason,
                impact,
                lane,
                country,
                asn,
                scoreMin,
                scoreMax,
                listMatch);
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
                "timeZone",
                timeZone,
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
                revision,
                "population",
                population,
                "band",
                band,
                "reason",
                reason,
                "impact",
                impact,
                "lane",
                lane,
                "country",
                country,
                "asn",
                asn,
                "scoreMin",
                scoreMin,
                "scoreMax",
                scoreMax,
                "listMatch",
                listMatch);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String from;

        private String to;

        private Bucket bucket;

        private String timeZone;

        private List<AggregateDimension> groupBy;

        private List<AssessmentAggregateRequestMetric> metrics;

        private Long limit;

        private Flow flow;

        private Status status;

        private Recommendation recommendation;

        private Decision decision;

        private AnswerRevision revision;

        private Population population;

        private Band band;

        private ReasonCode reason;

        private Impact impact;

        private Lane lane;

        private String country;

        private Long asn;

        private Long scoreMin;

        private Long scoreMax;

        private ListMatch listMatch;

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
         * The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets
         * are whole UTC hours in any time zone.
         */
        public Builder bucket(@Nonnull Bucket bucket) {
            this.bucket = Utils.checkNotNull(bucket, "bucket");
            return this;
        }

        /**
         * The IANA time zone whose local midnight starts each day and week bucket, such as Europe/London or
         * Asia/Kolkata; daylight saving changes make those days 23 or 25 hours long. A name the zone database
         * does not know is refused (400 invalid_request naming time_zone). Day and week buckets in a zone
         * other than UTC cover at most 92 days (400 invalid_request naming to); a year of them reads too many
         * hourly counts to answer in time.
         *
         * <p>Hour buckets stay whole UTC hours. Bucket starts, from and to stay instants. With the default
         * population, a zone whose local midnight falls inside a UTC hour (a half-hour offset such as
         * Asia/Kolkata) is refused for day and week buckets, as those counts are kept by the hour;
         * retained_first_answers counts it.
         */
        public Builder timeZone(@Nullable String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        /**
         * At most two dimensions to count by, of which at most one is reason, lane, country or asn. An
         * assessment counts once in each of its reasons and incomplete lanes, so those groups can add up to
         * more than the bucket's count. With population retained_first_answers, both dimensions can be facets;
         * counts cover retained first answers only.
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
         * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
         * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
         * exists and evidence is incomplete; assessment_url identifies continuing work when present.
         *
         * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
         * sources answered.
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

        /**
         * first_answers uses anonymous counters that can include erased detail. retained_first_answers
         * computes exact intersections over retained first answers within 31 days. Requests exceeding the
         * 100000-record work budget are refused.
         */
        public Builder population(@Nullable Population population) {
            this.population = population;
            return this;
        }

        public Builder band(@Nullable Band band) {
            this.band = band;
            return this;
        }

        /**
         * A stable reason code. New codes may appear; ignore any you do not know.
         */
        public Builder reason(@Nullable ReasonCode reason) {
            this.reason = reason;
            return this;
        }

        /**
         * The selected reason's served impact band. Requires reason.
         */
        public Builder impact(@Nullable Impact impact) {
            this.impact = impact;
            return this;
        }

        /**
         * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
         * email domain's registration) and ip.
         */
        public Builder lane(@Nullable Lane lane) {
            this.lane = lane;
            return this;
        }

        public Builder country(@Nullable String country) {
            this.country = country;
            return this;
        }

        public Builder asn(@Nullable Long asn) {
            this.asn = asn;
            return this;
        }

        /**
         * Inclusive lower score bound. Pending answers without a score do not match.
         */
        public Builder scoreMin(@Nullable Long scoreMin) {
            this.scoreMin = scoreMin;
            return this;
        }

        /**
         * Inclusive upper score bound, at least score_min when both are supplied.
         */
        public Builder scoreMax(@Nullable Long scoreMax) {
            this.scoreMax = scoreMax;
            return this;
        }

        /**
         * The list action captured at the first answer. Requires revision first. Older answers without
         * captured list state do not match, including none.
         */
        public Builder listMatch(@Nullable ListMatch listMatch) {
            this.listMatch = listMatch;
            return this;
        }

        public AssessmentAggregateRequest build() {
            return new AssessmentAggregateRequest(
                    from,
                    to,
                    bucket,
                    timeZone,
                    groupBy,
                    metrics,
                    limit,
                    flow,
                    status,
                    recommendation,
                    decision,
                    revision,
                    population,
                    band,
                    reason,
                    impact,
                    lane,
                    country,
                    asn,
                    scoreMin,
                    scoreMax,
                    listMatch);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_TimeZone =
                new LazySingletonValue<>("time_zone", "\"UTC\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
