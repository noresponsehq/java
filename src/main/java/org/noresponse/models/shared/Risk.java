package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * Risk
 *
 * <p>The one risk decision for this event. score is 0 (safe) to 100 (risky), set by the reasons that
 * fired under a hand-set, versioned heuristic policy. Each reason carries its direction and impact
 * band; the policy's weights are not published.
 *
 * <p>Every answer with a recommendation carries it, and the top-level recommendation equals
 * risk.recommendation. It names no source or provider.
 */
public class Risk {

    @JsonProperty("score")
    private long score;

    @JsonProperty("band")
    private Band band;

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    @JsonProperty("recommendation")
    private Recommendation recommendation;

    /**
     * true when the workspace's block threshold is set and score reached it. A provider hook can deny the
     * account creation on it; the provider decides and enforces. It does not change recommendation.
     *
     * <p>It is false while blocking is off, the default, and a body from before the flag existed omits it:
     * read absent as false.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("block")
    private Boolean block;

    /**
     * The block threshold this answer used: block is true exactly when score is at least this value.
     * Absent while blocking is off. NoResponse sets it per workspace on request, at or above the review
     * threshold.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("block_threshold")
    private Long blockThreshold;

    /**
     * The score at which this answer's flow starts to recommend step_up. Absent from an answer stored
     * before it was recorded.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("step_up_threshold")
    private Long stepUpThreshold;

    /**
     * The score at which this answer's flow starts to recommend review. An answer nothing answered
     * recommends review below it, with confidence unavailable. Absent from an answer stored before it was
     * recorded.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("review_threshold")
    private Long reviewThreshold;

    /**
     * How much of the planned evidence answered; it says nothing about risk.
     */
    @JsonProperty("confidence")
    private RiskConfidence confidence;

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    @JsonProperty("policy_version")
    private String policyVersion;

    /**
     * Labels the v0 policy as hand-authored and not statistically validated.
     */
    @JsonProperty("policy_basis")
    private String policyBasis;

    /**
     * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
     * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
     * reason carries only its direction and impact band.
     */
    @JsonProperty("reasons")
    private List<RiskReason> reasons;

    /**
     * A few typed facts per identifier. Every fact is optional: absent means the evidence did not answer
     * and makes no claim, while false or 0 is a definite answer. Lanes that do not exist yet leave their
     * members absent.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("summary")
    private RiskSummary summary;

    /**
     * Counts of planned source-identifier pairs by evidence outcome, deduplicated across signals and
     * incomplete entries. These counts do not measure identity agreement or prove that processing is still
     * running. A lane that was not searched is not an answered source.
     */
    @JsonProperty("coverage")
    private RiskCoverage coverage;

    /**
     * Detailed view only (response_mode=detailed, which needs the detailed scope); a standard answer
     * leaves it out. Merged observations without provider, source, origin, raw breach record, value or
     * score.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("details")
    private RiskDetails details;

    @JsonCreator
    public Risk(
            @JsonProperty("score") long score,
            @JsonProperty("band") @Nonnull Band band,
            @JsonProperty("recommendation") @Nonnull Recommendation recommendation,
            @JsonProperty("block") @Nullable Boolean block,
            @JsonProperty("block_threshold") @Nullable Long blockThreshold,
            @JsonProperty("step_up_threshold") @Nullable Long stepUpThreshold,
            @JsonProperty("review_threshold") @Nullable Long reviewThreshold,
            @JsonProperty("confidence") @Nonnull RiskConfidence confidence,
            @JsonProperty("policy_version") @Nonnull String policyVersion,
            @JsonProperty("reasons") @Nonnull List<RiskReason> reasons,
            @JsonProperty("summary") @Nullable RiskSummary summary,
            @JsonProperty("coverage") @Nonnull RiskCoverage coverage,
            @JsonProperty("details") @Nullable RiskDetails details) {
        this.score = score;
        this.band = Optional.ofNullable(band).orElseThrow(() -> new IllegalArgumentException("band cannot be null"));
        this.recommendation = Optional.ofNullable(recommendation)
                .orElseThrow(() -> new IllegalArgumentException("recommendation cannot be null"));
        this.block = block;
        this.blockThreshold = blockThreshold;
        this.stepUpThreshold = stepUpThreshold;
        this.reviewThreshold = reviewThreshold;
        this.confidence = Optional.ofNullable(confidence)
                .orElseThrow(() -> new IllegalArgumentException("confidence cannot be null"));
        this.policyVersion = Optional.ofNullable(policyVersion)
                .orElseThrow(() -> new IllegalArgumentException("policyVersion cannot be null"));
        this.policyBasis = Builder._SINGLETON_VALUE_PolicyBasis.value();
        this.reasons =
                Optional.ofNullable(reasons).orElseThrow(() -> new IllegalArgumentException("reasons cannot be null"));
        this.summary = summary;
        this.coverage = Optional.ofNullable(coverage)
                .orElseThrow(() -> new IllegalArgumentException("coverage cannot be null"));
        this.details = details;
    }

    public Risk(
            long score,
            @Nonnull Band band,
            @Nonnull Recommendation recommendation,
            @Nonnull RiskConfidence confidence,
            @Nonnull String policyVersion,
            @Nonnull List<RiskReason> reasons,
            @Nonnull RiskCoverage coverage) {
        this(
                score,
                band,
                recommendation,
                null,
                null,
                null,
                null,
                confidence,
                policyVersion,
                reasons,
                null,
                coverage,
                null);
    }

    public long score() {
        return this.score;
    }

    public Band band() {
        return this.band;
    }

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public Recommendation recommendation() {
        return this.recommendation;
    }

    /**
     * true when the workspace's block threshold is set and score reached it. A provider hook can deny the
     * account creation on it; the provider decides and enforces. It does not change recommendation.
     *
     * <p>It is false while blocking is off, the default, and a body from before the flag existed omits it:
     * read absent as false.
     */
    public Optional<Boolean> block() {
        return Optional.ofNullable(this.block);
    }

    /**
     * The block threshold this answer used: block is true exactly when score is at least this value.
     * Absent while blocking is off. NoResponse sets it per workspace on request, at or above the review
     * threshold.
     */
    public Optional<Long> blockThreshold() {
        return Optional.ofNullable(this.blockThreshold);
    }

    /**
     * The score at which this answer's flow starts to recommend step_up. Absent from an answer stored
     * before it was recorded.
     */
    public Optional<Long> stepUpThreshold() {
        return Optional.ofNullable(this.stepUpThreshold);
    }

    /**
     * The score at which this answer's flow starts to recommend review. An answer nothing answered
     * recommends review below it, with confidence unavailable. Absent from an answer stored before it was
     * recorded.
     */
    public Optional<Long> reviewThreshold() {
        return Optional.ofNullable(this.reviewThreshold);
    }

    /**
     * How much of the planned evidence answered; it says nothing about risk.
     */
    public RiskConfidence confidence() {
        return this.confidence;
    }

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    public String policyVersion() {
        return this.policyVersion;
    }

    /**
     * Labels the v0 policy as hand-authored and not statistically validated.
     */
    public String policyBasis() {
        return this.policyBasis;
    }

    /**
     * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
     * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
     * reason carries only its direction and impact band.
     */
    public List<RiskReason> reasons() {
        return this.reasons;
    }

    /**
     * A few typed facts per identifier. Every fact is optional: absent means the evidence did not answer
     * and makes no claim, while false or 0 is a definite answer. Lanes that do not exist yet leave their
     * members absent.
     */
    public Optional<RiskSummary> summary() {
        return Optional.ofNullable(this.summary);
    }

    /**
     * Counts of planned source-identifier pairs by evidence outcome, deduplicated across signals and
     * incomplete entries. These counts do not measure identity agreement or prove that processing is still
     * running. A lane that was not searched is not an answered source.
     */
    public RiskCoverage coverage() {
        return this.coverage;
    }

    /**
     * Detailed view only (response_mode=detailed, which needs the detailed scope); a standard answer
     * leaves it out. Merged observations without provider, source, origin, raw breach record, value or
     * score.
     */
    public Optional<RiskDetails> details() {
        return Optional.ofNullable(this.details);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Risk withScore(long score) {
        this.score = score;
        return this;
    }

    public Risk withBand(@Nonnull Band band) {
        this.band = Utils.checkNotNull(band, "band");
        return this;
    }

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public Risk withRecommendation(@Nonnull Recommendation recommendation) {
        this.recommendation = Utils.checkNotNull(recommendation, "recommendation");
        return this;
    }

    /**
     * true when the workspace's block threshold is set and score reached it. A provider hook can deny the
     * account creation on it; the provider decides and enforces. It does not change recommendation.
     *
     * <p>It is false while blocking is off, the default, and a body from before the flag existed omits it:
     * read absent as false.
     */
    public Risk withBlock(@Nullable Boolean block) {
        this.block = block;
        return this;
    }

    /**
     * The block threshold this answer used: block is true exactly when score is at least this value.
     * Absent while blocking is off. NoResponse sets it per workspace on request, at or above the review
     * threshold.
     */
    public Risk withBlockThreshold(@Nullable Long blockThreshold) {
        this.blockThreshold = blockThreshold;
        return this;
    }

    /**
     * The score at which this answer's flow starts to recommend step_up. Absent from an answer stored
     * before it was recorded.
     */
    public Risk withStepUpThreshold(@Nullable Long stepUpThreshold) {
        this.stepUpThreshold = stepUpThreshold;
        return this;
    }

    /**
     * The score at which this answer's flow starts to recommend review. An answer nothing answered
     * recommends review below it, with confidence unavailable. Absent from an answer stored before it was
     * recorded.
     */
    public Risk withReviewThreshold(@Nullable Long reviewThreshold) {
        this.reviewThreshold = reviewThreshold;
        return this;
    }

    /**
     * How much of the planned evidence answered; it says nothing about risk.
     */
    public Risk withConfidence(@Nonnull RiskConfidence confidence) {
        this.confidence = Utils.checkNotNull(confidence, "confidence");
        return this;
    }

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    public Risk withPolicyVersion(@Nonnull String policyVersion) {
        this.policyVersion = Utils.checkNotNull(policyVersion, "policyVersion");
        return this;
    }

    /**
     * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
     * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
     * reason carries only its direction and impact band.
     */
    public Risk withReasons(@Nonnull List<RiskReason> reasons) {
        this.reasons = Utils.checkNotNull(reasons, "reasons");
        return this;
    }

    /**
     * A few typed facts per identifier. Every fact is optional: absent means the evidence did not answer
     * and makes no claim, while false or 0 is a definite answer. Lanes that do not exist yet leave their
     * members absent.
     */
    public Risk withSummary(@Nullable RiskSummary summary) {
        this.summary = summary;
        return this;
    }

    /**
     * Counts of planned source-identifier pairs by evidence outcome, deduplicated across signals and
     * incomplete entries. These counts do not measure identity agreement or prove that processing is still
     * running. A lane that was not searched is not an answered source.
     */
    public Risk withCoverage(@Nonnull RiskCoverage coverage) {
        this.coverage = Utils.checkNotNull(coverage, "coverage");
        return this;
    }

    /**
     * Detailed view only (response_mode=detailed, which needs the detailed scope); a standard answer
     * leaves it out. Merged observations without provider, source, origin, raw breach record, value or
     * score.
     */
    public Risk withDetails(@Nullable RiskDetails details) {
        this.details = details;
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
        Risk other = (Risk) o;
        return Utils.enhancedDeepEquals(this.score, other.score)
                && Utils.enhancedDeepEquals(this.band, other.band)
                && Utils.enhancedDeepEquals(this.recommendation, other.recommendation)
                && Utils.enhancedDeepEquals(this.block, other.block)
                && Utils.enhancedDeepEquals(this.blockThreshold, other.blockThreshold)
                && Utils.enhancedDeepEquals(this.stepUpThreshold, other.stepUpThreshold)
                && Utils.enhancedDeepEquals(this.reviewThreshold, other.reviewThreshold)
                && Utils.enhancedDeepEquals(this.confidence, other.confidence)
                && Utils.enhancedDeepEquals(this.policyVersion, other.policyVersion)
                && Utils.enhancedDeepEquals(this.policyBasis, other.policyBasis)
                && Utils.enhancedDeepEquals(this.reasons, other.reasons)
                && Utils.enhancedDeepEquals(this.summary, other.summary)
                && Utils.enhancedDeepEquals(this.coverage, other.coverage)
                && Utils.enhancedDeepEquals(this.details, other.details);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                score,
                band,
                recommendation,
                block,
                blockThreshold,
                stepUpThreshold,
                reviewThreshold,
                confidence,
                policyVersion,
                policyBasis,
                reasons,
                summary,
                coverage,
                details);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Risk.class,
                "score",
                score,
                "band",
                band,
                "recommendation",
                recommendation,
                "block",
                block,
                "blockThreshold",
                blockThreshold,
                "stepUpThreshold",
                stepUpThreshold,
                "reviewThreshold",
                reviewThreshold,
                "confidence",
                confidence,
                "policyVersion",
                policyVersion,
                "policyBasis",
                policyBasis,
                "reasons",
                reasons,
                "summary",
                summary,
                "coverage",
                coverage,
                "details",
                details);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long score;

        private Band band;

        private Recommendation recommendation;

        private Boolean block;

        private Long blockThreshold;

        private Long stepUpThreshold;

        private Long reviewThreshold;

        private RiskConfidence confidence;

        private String policyVersion;

        private List<RiskReason> reasons;

        private RiskSummary summary;

        private RiskCoverage coverage;

        private RiskDetails details;

        private Builder() {
            // force use of static builder() method
        }

        public Builder score(long score) {
            this.score = score;
            return this;
        }

        public Builder band(@Nonnull Band band) {
            this.band = Utils.checkNotNull(band, "band");
            return this;
        }

        /**
         * The recommended action: allow, step_up or review. Your application applies it.
         */
        public Builder recommendation(@Nonnull Recommendation recommendation) {
            this.recommendation = Utils.checkNotNull(recommendation, "recommendation");
            return this;
        }

        /**
         * true when the workspace's block threshold is set and score reached it. A provider hook can deny the
         * account creation on it; the provider decides and enforces. It does not change recommendation.
         *
         * <p>It is false while blocking is off, the default, and a body from before the flag existed omits it:
         * read absent as false.
         */
        public Builder block(@Nullable Boolean block) {
            this.block = block;
            return this;
        }

        /**
         * The block threshold this answer used: block is true exactly when score is at least this value.
         * Absent while blocking is off. NoResponse sets it per workspace on request, at or above the review
         * threshold.
         */
        public Builder blockThreshold(@Nullable Long blockThreshold) {
            this.blockThreshold = blockThreshold;
            return this;
        }

        /**
         * The score at which this answer's flow starts to recommend step_up. Absent from an answer stored
         * before it was recorded.
         */
        public Builder stepUpThreshold(@Nullable Long stepUpThreshold) {
            this.stepUpThreshold = stepUpThreshold;
            return this;
        }

        /**
         * The score at which this answer's flow starts to recommend review. An answer nothing answered
         * recommends review below it, with confidence unavailable. Absent from an answer stored before it was
         * recorded.
         */
        public Builder reviewThreshold(@Nullable Long reviewThreshold) {
            this.reviewThreshold = reviewThreshold;
            return this;
        }

        /**
         * How much of the planned evidence answered; it says nothing about risk.
         */
        public Builder confidence(@Nonnull RiskConfidence confidence) {
            this.confidence = Utils.checkNotNull(confidence, "confidence");
            return this;
        }

        /**
         * Version of the hand-authored heuristic policy that produced the recommendation, for example
         * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
         * 'risk-2026-10.7+t3'.
         */
        public Builder policyVersion(@Nonnull String policyVersion) {
            this.policyVersion = Utils.checkNotNull(policyVersion, "policyVersion");
            return this;
        }

        /**
         * Every reason that fired: risk-raising reasons first, then neutral, then risk-lowering, each
         * strongest impact band first and then by code. The policy's numeric weights are kept private, so a
         * reason carries only its direction and impact band.
         */
        public Builder reasons(@Nonnull List<RiskReason> reasons) {
            this.reasons = Utils.checkNotNull(reasons, "reasons");
            return this;
        }

        /**
         * A few typed facts per identifier. Every fact is optional: absent means the evidence did not answer
         * and makes no claim, while false or 0 is a definite answer. Lanes that do not exist yet leave their
         * members absent.
         */
        public Builder summary(@Nullable RiskSummary summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Counts of planned source-identifier pairs by evidence outcome, deduplicated across signals and
         * incomplete entries. These counts do not measure identity agreement or prove that processing is still
         * running. A lane that was not searched is not an answered source.
         */
        public Builder coverage(@Nonnull RiskCoverage coverage) {
            this.coverage = Utils.checkNotNull(coverage, "coverage");
            return this;
        }

        /**
         * Detailed view only (response_mode=detailed, which needs the detailed scope); a standard answer
         * leaves it out. Merged observations without provider, source, origin, raw breach record, value or
         * score.
         */
        public Builder details(@Nullable RiskDetails details) {
            this.details = details;
            return this;
        }

        public Risk build() {
            return new Risk(
                    score,
                    band,
                    recommendation,
                    block,
                    blockThreshold,
                    stepUpThreshold,
                    reviewThreshold,
                    confidence,
                    policyVersion,
                    reasons,
                    summary,
                    coverage,
                    details);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_PolicyBasis =
                new LazySingletonValue<>("policy_basis", "\"heuristic_unvalidated\"", new TypeReference<String>() {});
    }
}
