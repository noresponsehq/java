package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AggregateGroup
 *
 * <p>One group's count in one bucket. It holds each grouped dimension the counted assessments had a value
 * for: a pending first answer has no decision, recommendation or band; an answer that fired no reason,
 * listed no incomplete lane, matched no list entry or whose IP has no known country or ASN leaves that
 * dimension out.
 */
public class AggregateGroup {
    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decision")
    private Decision decision;

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("flow")
    private Flow flow;

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("recommendation")
    private Recommendation recommendation;

    /**
     * low 0-29, medium 30-59, high 60-84, critical 85-100.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("band")
    private Band band;

    /**
     * The action of the list entry that decided the account.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("list_match")
    private ListMatch listMatch;

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reason")
    private ReasonCode reason;

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

    @JsonProperty("count")
    private long count;

    @JsonCreator
    public AggregateGroup(
            @JsonProperty("decision") @Nullable Decision decision,
            @JsonProperty("flow") @Nullable Flow flow,
            @JsonProperty("recommendation") @Nullable Recommendation recommendation,
            @JsonProperty("band") @Nullable Band band,
            @JsonProperty("list_match") @Nullable ListMatch listMatch,
            @JsonProperty("reason") @Nullable ReasonCode reason,
            @JsonProperty("impact") @Nullable Impact impact,
            @JsonProperty("lane") @Nullable Lane lane,
            @JsonProperty("country") @Nullable String country,
            @JsonProperty("asn") @Nullable Long asn,
            @JsonProperty("count") long count) {
        this.decision = decision;
        this.flow = flow;
        this.recommendation = recommendation;
        this.band = band;
        this.listMatch = listMatch;
        this.reason = reason;
        this.impact = impact;
        this.lane = lane;
        this.country = country;
        this.asn = asn;
        this.count = count;
    }

    public AggregateGroup(long count) {
        this(null, null, null, null, null, null, null, null, null, null, count);
    }

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    public Optional<Decision> decision() {
        return Optional.ofNullable(this.decision);
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
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public Optional<Recommendation> recommendation() {
        return Optional.ofNullable(this.recommendation);
    }

    /**
     * low 0-29, medium 30-59, high 60-84, critical 85-100.
     */
    public Optional<Band> band() {
        return Optional.ofNullable(this.band);
    }

    /**
     * The action of the list entry that decided the account.
     */
    public Optional<ListMatch> listMatch() {
        return Optional.ofNullable(this.listMatch);
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public Optional<ReasonCode> reason() {
        return Optional.ofNullable(this.reason);
    }

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

    public long count() {
        return this.count;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    public AggregateGroup withDecision(@Nullable Decision decision) {
        this.decision = decision;
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public AggregateGroup withFlow(@Nullable Flow flow) {
        this.flow = flow;
        return this;
    }

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public AggregateGroup withRecommendation(@Nullable Recommendation recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    /**
     * low 0-29, medium 30-59, high 60-84, critical 85-100.
     */
    public AggregateGroup withBand(@Nullable Band band) {
        this.band = band;
        return this;
    }

    /**
     * The action of the list entry that decided the account.
     */
    public AggregateGroup withListMatch(@Nullable ListMatch listMatch) {
        this.listMatch = listMatch;
        return this;
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public AggregateGroup withReason(@Nullable ReasonCode reason) {
        this.reason = reason;
        return this;
    }

    public AggregateGroup withImpact(@Nullable Impact impact) {
        this.impact = impact;
        return this;
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public AggregateGroup withLane(@Nullable Lane lane) {
        this.lane = lane;
        return this;
    }

    public AggregateGroup withCountry(@Nullable String country) {
        this.country = country;
        return this;
    }

    public AggregateGroup withAsn(@Nullable Long asn) {
        this.asn = asn;
        return this;
    }

    public AggregateGroup withCount(long count) {
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
        AggregateGroup other = (AggregateGroup) o;
        return Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.recommendation, other.recommendation)
                && Utils.enhancedDeepEquals(this.band, other.band)
                && Utils.enhancedDeepEquals(this.listMatch, other.listMatch)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.impact, other.impact)
                && Utils.enhancedDeepEquals(this.lane, other.lane)
                && Utils.enhancedDeepEquals(this.country, other.country)
                && Utils.enhancedDeepEquals(this.asn, other.asn)
                && Utils.enhancedDeepEquals(this.count, other.count);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                decision, flow, recommendation, band, listMatch, reason, impact, lane, country, asn, count);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AggregateGroup.class,
                "decision",
                decision,
                "flow",
                flow,
                "recommendation",
                recommendation,
                "band",
                band,
                "listMatch",
                listMatch,
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
                "count",
                count);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Decision decision;

        private Flow flow;

        private Recommendation recommendation;

        private Band band;

        private ListMatch listMatch;

        private ReasonCode reason;

        private Impact impact;

        private Lane lane;

        private String country;

        private Long asn;

        private long count;

        private Builder() {
            // force use of static builder() method
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
         * The event an assessment is made for: account_creation (a user creates an account), login (a user
         * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
         * reported with the decision for this flow.
         */
        public Builder flow(@Nullable Flow flow) {
            this.flow = flow;
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
         * low 0-29, medium 30-59, high 60-84, critical 85-100.
         */
        public Builder band(@Nullable Band band) {
            this.band = band;
            return this;
        }

        /**
         * The action of the list entry that decided the account.
         */
        public Builder listMatch(@Nullable ListMatch listMatch) {
            this.listMatch = listMatch;
            return this;
        }

        /**
         * A stable reason code. New codes may appear; ignore any you do not know.
         */
        public Builder reason(@Nullable ReasonCode reason) {
            this.reason = reason;
            return this;
        }

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

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public AggregateGroup build() {
            return new AggregateGroup(
                    decision, flow, recommendation, band, listMatch, reason, impact, lane, country, asn, count);
        }
    }
}
