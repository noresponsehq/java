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
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentSearchRequest
 *
 * <p>Search retained assessments created in [from,to), over at most 366 days. Filters use each assessment
 * decision, rather than the current account decision. Results can change between pages.
 *
 * <p>Keep the assessment version to retrieve the exact decision later. This read does not require an
 * idempotency key.
 */
public class AssessmentSearchRequest {

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_id")
    private String accountId;

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

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("limit")
    private Long limit;

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("cursor")
    private String cursor;

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("response_mode")
    private ResponseMode responseMode;

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
    public AssessmentSearchRequest(
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("account_id") @Nullable String accountId,
            @JsonProperty("flow") @Nullable Flow flow,
            @JsonProperty("status") @Nullable Status status,
            @JsonProperty("recommendation") @Nullable Recommendation recommendation,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("cursor") @Nullable String cursor,
            @JsonProperty("response_mode") @Nullable ResponseMode responseMode,
            @JsonProperty("decision") @Nullable Decision decision,
            @JsonProperty("revision") @Nullable AnswerRevision revision,
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
        this.accountId = accountId;
        this.flow = flow;
        this.status = status;
        this.recommendation = recommendation;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
        this.responseMode = responseMode;
        this.decision = decision;
        this.revision = revision;
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

    public AssessmentSearchRequest(@Nonnull String from, @Nonnull String to) {
        this(
                from,
                to,
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

    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public Optional<String> accountId() {
        return Optional.ofNullable(this.accountId);
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

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public Optional<ResponseMode> responseMode() {
        return Optional.ofNullable(this.responseMode);
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

    public AssessmentSearchRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public AssessmentSearchRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public AssessmentSearchRequest withAccountId(@Nullable String accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public AssessmentSearchRequest withFlow(@Nullable Flow flow) {
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
    public AssessmentSearchRequest withStatus(@Nullable Status status) {
        this.status = status;
        return this;
    }

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public AssessmentSearchRequest withRecommendation(@Nullable Recommendation recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public AssessmentSearchRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public AssessmentSearchRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
        return this;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public AssessmentSearchRequest withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
        return this;
    }

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    public AssessmentSearchRequest withDecision(@Nullable Decision decision) {
        this.decision = decision;
        return this;
    }

    /**
     * Which answer of each assessment the filters describe: latest, the latest stored revision, or first,
     * the first answer, as served when the assessment was first answered (POST /v1/assessments/aggregate
     * counts first answers). A cell of an aggregate drills down to POST /v1/assessments/search with the
     * same filters, revision first and the cell's bucket as from and to.
     */
    public AssessmentSearchRequest withRevision(@Nullable AnswerRevision revision) {
        this.revision = revision;
        return this;
    }

    public AssessmentSearchRequest withBand(@Nullable Band band) {
        this.band = band;
        return this;
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public AssessmentSearchRequest withReason(@Nullable ReasonCode reason) {
        this.reason = reason;
        return this;
    }

    /**
     * The selected reason's served impact band. Requires reason.
     */
    public AssessmentSearchRequest withImpact(@Nullable Impact impact) {
        this.impact = impact;
        return this;
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public AssessmentSearchRequest withLane(@Nullable Lane lane) {
        this.lane = lane;
        return this;
    }

    public AssessmentSearchRequest withCountry(@Nullable String country) {
        this.country = country;
        return this;
    }

    public AssessmentSearchRequest withAsn(@Nullable Long asn) {
        this.asn = asn;
        return this;
    }

    /**
     * Inclusive lower score bound. Pending answers without a score do not match.
     */
    public AssessmentSearchRequest withScoreMin(@Nullable Long scoreMin) {
        this.scoreMin = scoreMin;
        return this;
    }

    /**
     * Inclusive upper score bound, at least score_min when both are supplied.
     */
    public AssessmentSearchRequest withScoreMax(@Nullable Long scoreMax) {
        this.scoreMax = scoreMax;
        return this;
    }

    /**
     * The list action captured at the first answer. Requires revision first. Older answers without
     * captured list state do not match, including none.
     */
    public AssessmentSearchRequest withListMatch(@Nullable ListMatch listMatch) {
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
        AssessmentSearchRequest other = (AssessmentSearchRequest) o;
        return Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.recommendation, other.recommendation)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.revision, other.revision)
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
                accountId,
                flow,
                status,
                recommendation,
                limit,
                cursor,
                responseMode,
                decision,
                revision,
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
                AssessmentSearchRequest.class,
                "from",
                from,
                "to",
                to,
                "accountId",
                accountId,
                "flow",
                flow,
                "status",
                status,
                "recommendation",
                recommendation,
                "limit",
                limit,
                "cursor",
                cursor,
                "responseMode",
                responseMode,
                "decision",
                decision,
                "revision",
                revision,
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

        private String accountId;

        private Flow flow;

        private Status status;

        private Recommendation recommendation;

        private Long limit;

        private String cursor;

        private ResponseMode responseMode;

        private Decision decision;

        private AnswerRevision revision;

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

        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        /**
         * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
         * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
         * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
         * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
         * internal id is a common pseudonymous scheme.
         *
         * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
         * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
         * yet, carries the server-issued account_id pre: followed by its assessment_id.
         */
        public Builder accountId(@Nullable String accountId) {
            this.accountId = accountId;
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

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
         * unchanged.
         */
        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        /**
         * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
         * the key has detailed scope. There is no raw mode.
         */
        public Builder responseMode(@Nullable ResponseMode responseMode) {
            this.responseMode = responseMode;
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

        public AssessmentSearchRequest build() {
            return new AssessmentSearchRequest(
                    from,
                    to,
                    accountId,
                    flow,
                    status,
                    recommendation,
                    limit,
                    cursor,
                    responseMode,
                    decision,
                    revision,
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

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
