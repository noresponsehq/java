package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class PolicyEvaluationRequest {

    @JsonProperty("expected_policy_version")
    private long expectedPolicyVersion;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("candidates")
    private List<Thresholds> candidates;

    @JsonProperty("costs")
    private PolicyEvaluationRequestCosts costs;

    @JsonProperty("limits")
    private PolicyEvaluationRequestLimits limits;

    @JsonProperty("label_collection")
    private String labelCollection;

    @JsonProperty("split_seed")
    private String splitSeed;

    @JsonCreator
    public PolicyEvaluationRequest(
            @JsonProperty("expected_policy_version") long expectedPolicyVersion,
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("candidates") @Nonnull List<Thresholds> candidates,
            @JsonProperty("costs") @Nonnull PolicyEvaluationRequestCosts costs,
            @JsonProperty("limits") @Nonnull PolicyEvaluationRequestLimits limits,
            @JsonProperty("label_collection") @Nonnull String labelCollection,
            @JsonProperty("split_seed") @Nonnull String splitSeed) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.candidates = Optional.ofNullable(candidates)
                .orElseThrow(() -> new IllegalArgumentException("candidates cannot be null"));
        this.costs = Optional.ofNullable(costs).orElseThrow(() -> new IllegalArgumentException("costs cannot be null"));
        this.limits =
                Optional.ofNullable(limits).orElseThrow(() -> new IllegalArgumentException("limits cannot be null"));
        this.labelCollection = Optional.ofNullable(labelCollection)
                .orElseThrow(() -> new IllegalArgumentException("labelCollection cannot be null"));
        this.splitSeed = Optional.ofNullable(splitSeed)
                .orElseThrow(() -> new IllegalArgumentException("splitSeed cannot be null"));
    }

    public long expectedPolicyVersion() {
        return this.expectedPolicyVersion;
    }

    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    public List<Thresholds> candidates() {
        return this.candidates;
    }

    public PolicyEvaluationRequestCosts costs() {
        return this.costs;
    }

    public PolicyEvaluationRequestLimits limits() {
        return this.limits;
    }

    public String labelCollection() {
        return this.labelCollection;
    }

    public String splitSeed() {
        return this.splitSeed;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyEvaluationRequest withExpectedPolicyVersion(long expectedPolicyVersion) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        return this;
    }

    public PolicyEvaluationRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public PolicyEvaluationRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public PolicyEvaluationRequest withCandidates(@Nonnull List<Thresholds> candidates) {
        this.candidates = Utils.checkNotNull(candidates, "candidates");
        return this;
    }

    public PolicyEvaluationRequest withCosts(@Nonnull PolicyEvaluationRequestCosts costs) {
        this.costs = Utils.checkNotNull(costs, "costs");
        return this;
    }

    public PolicyEvaluationRequest withLimits(@Nonnull PolicyEvaluationRequestLimits limits) {
        this.limits = Utils.checkNotNull(limits, "limits");
        return this;
    }

    public PolicyEvaluationRequest withLabelCollection(@Nonnull String labelCollection) {
        this.labelCollection = Utils.checkNotNull(labelCollection, "labelCollection");
        return this;
    }

    public PolicyEvaluationRequest withSplitSeed(@Nonnull String splitSeed) {
        this.splitSeed = Utils.checkNotNull(splitSeed, "splitSeed");
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
        PolicyEvaluationRequest other = (PolicyEvaluationRequest) o;
        return Utils.enhancedDeepEquals(this.expectedPolicyVersion, other.expectedPolicyVersion)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.candidates, other.candidates)
                && Utils.enhancedDeepEquals(this.costs, other.costs)
                && Utils.enhancedDeepEquals(this.limits, other.limits)
                && Utils.enhancedDeepEquals(this.labelCollection, other.labelCollection)
                && Utils.enhancedDeepEquals(this.splitSeed, other.splitSeed);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                expectedPolicyVersion, from, to, candidates, costs, limits, labelCollection, splitSeed);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyEvaluationRequest.class,
                "expectedPolicyVersion",
                expectedPolicyVersion,
                "from",
                from,
                "to",
                to,
                "candidates",
                candidates,
                "costs",
                costs,
                "limits",
                limits,
                "labelCollection",
                labelCollection,
                "splitSeed",
                splitSeed);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long expectedPolicyVersion;

        private String from;

        private String to;

        private List<Thresholds> candidates;

        private PolicyEvaluationRequestCosts costs;

        private PolicyEvaluationRequestLimits limits;

        private String labelCollection;

        private String splitSeed;

        private Builder() {
            // force use of static builder() method
        }

        public Builder expectedPolicyVersion(long expectedPolicyVersion) {
            this.expectedPolicyVersion = expectedPolicyVersion;
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

        public Builder candidates(@Nonnull List<Thresholds> candidates) {
            this.candidates = Utils.checkNotNull(candidates, "candidates");
            return this;
        }

        public Builder costs(@Nonnull PolicyEvaluationRequestCosts costs) {
            this.costs = Utils.checkNotNull(costs, "costs");
            return this;
        }

        public Builder limits(@Nonnull PolicyEvaluationRequestLimits limits) {
            this.limits = Utils.checkNotNull(limits, "limits");
            return this;
        }

        public Builder labelCollection(@Nonnull String labelCollection) {
            this.labelCollection = Utils.checkNotNull(labelCollection, "labelCollection");
            return this;
        }

        public Builder splitSeed(@Nonnull String splitSeed) {
            this.splitSeed = Utils.checkNotNull(splitSeed, "splitSeed");
            return this;
        }

        public PolicyEvaluationRequest build() {
            return new PolicyEvaluationRequest(
                    expectedPolicyVersion, from, to, candidates, costs, limits, labelCollection, splitSeed);
        }
    }
}
