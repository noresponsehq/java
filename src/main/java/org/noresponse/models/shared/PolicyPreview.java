package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * PolicyPreview
 *
 * <p>Exact threshold transitions among eligible retained first answers, newest first within the limit.
 * Partial older revisions without a frozen hold snapshot are reported as unsupported. Preview leaves
 * account state, outcomes and source evidence unchanged.
 */
public class PolicyPreview {

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("policy_version")
    private long policyVersion;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("retention_from")
    private String retentionFrom;

    @JsonProperty("coverage")
    private PolicyPreviewCoverage coverage;

    @JsonProperty("changed")
    private long changed;

    @JsonProperty("unchanged")
    private long unchanged;

    @JsonProperty("transitions")
    private List<PolicyPreviewTransition> transitions;

    @JsonCreator
    public PolicyPreview(
            @JsonProperty("policy_version") long policyVersion,
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("retention_from") @Nonnull String retentionFrom,
            @JsonProperty("coverage") @Nonnull PolicyPreviewCoverage coverage,
            @JsonProperty("changed") long changed,
            @JsonProperty("unchanged") long unchanged,
            @JsonProperty("transitions") @Nonnull List<PolicyPreviewTransition> transitions) {
        this.scope = Builder._SINGLETON_VALUE_Scope.value();
        this.policyVersion = policyVersion;
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.retentionFrom = Optional.ofNullable(retentionFrom)
                .orElseThrow(() -> new IllegalArgumentException("retentionFrom cannot be null"));
        this.coverage = Optional.ofNullable(coverage)
                .orElseThrow(() -> new IllegalArgumentException("coverage cannot be null"));
        this.changed = changed;
        this.unchanged = unchanged;
        this.transitions = Optional.ofNullable(transitions)
                .orElseThrow(() -> new IllegalArgumentException("transitions cannot be null"));
    }

    public String scope() {
        return this.scope;
    }

    public long policyVersion() {
        return this.policyVersion;
    }

    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    public String retentionFrom() {
        return this.retentionFrom;
    }

    public PolicyPreviewCoverage coverage() {
        return this.coverage;
    }

    public long changed() {
        return this.changed;
    }

    public long unchanged() {
        return this.unchanged;
    }

    public List<PolicyPreviewTransition> transitions() {
        return this.transitions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyPreview withPolicyVersion(long policyVersion) {
        this.policyVersion = policyVersion;
        return this;
    }

    public PolicyPreview withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public PolicyPreview withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public PolicyPreview withRetentionFrom(@Nonnull String retentionFrom) {
        this.retentionFrom = Utils.checkNotNull(retentionFrom, "retentionFrom");
        return this;
    }

    public PolicyPreview withCoverage(@Nonnull PolicyPreviewCoverage coverage) {
        this.coverage = Utils.checkNotNull(coverage, "coverage");
        return this;
    }

    public PolicyPreview withChanged(long changed) {
        this.changed = changed;
        return this;
    }

    public PolicyPreview withUnchanged(long unchanged) {
        this.unchanged = unchanged;
        return this;
    }

    public PolicyPreview withTransitions(@Nonnull List<PolicyPreviewTransition> transitions) {
        this.transitions = Utils.checkNotNull(transitions, "transitions");
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
        PolicyPreview other = (PolicyPreview) o;
        return Utils.enhancedDeepEquals(this.scope, other.scope)
                && Utils.enhancedDeepEquals(this.policyVersion, other.policyVersion)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.retentionFrom, other.retentionFrom)
                && Utils.enhancedDeepEquals(this.coverage, other.coverage)
                && Utils.enhancedDeepEquals(this.changed, other.changed)
                && Utils.enhancedDeepEquals(this.unchanged, other.unchanged)
                && Utils.enhancedDeepEquals(this.transitions, other.transitions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                scope, policyVersion, from, to, retentionFrom, coverage, changed, unchanged, transitions);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyPreview.class,
                "scope",
                scope,
                "policyVersion",
                policyVersion,
                "from",
                from,
                "to",
                to,
                "retentionFrom",
                retentionFrom,
                "coverage",
                coverage,
                "changed",
                changed,
                "unchanged",
                unchanged,
                "transitions",
                transitions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long policyVersion;

        private String from;

        private String to;

        private String retentionFrom;

        private PolicyPreviewCoverage coverage;

        private long changed;

        private long unchanged;

        private List<PolicyPreviewTransition> transitions;

        private Builder() {
            // force use of static builder() method
        }

        public Builder policyVersion(long policyVersion) {
            this.policyVersion = policyVersion;
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

        public Builder retentionFrom(@Nonnull String retentionFrom) {
            this.retentionFrom = Utils.checkNotNull(retentionFrom, "retentionFrom");
            return this;
        }

        public Builder coverage(@Nonnull PolicyPreviewCoverage coverage) {
            this.coverage = Utils.checkNotNull(coverage, "coverage");
            return this;
        }

        public Builder changed(long changed) {
            this.changed = changed;
            return this;
        }

        public Builder unchanged(long unchanged) {
            this.unchanged = unchanged;
            return this;
        }

        public Builder transitions(@Nonnull List<PolicyPreviewTransition> transitions) {
            this.transitions = Utils.checkNotNull(transitions, "transitions");
            return this;
        }

        public PolicyPreview build() {
            return new PolicyPreview(policyVersion, from, to, retentionFrom, coverage, changed, unchanged, transitions);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Scope = new LazySingletonValue<>(
                "scope", "\"retained_first_answer_thresholds\"", new TypeReference<String>() {});
    }
}
