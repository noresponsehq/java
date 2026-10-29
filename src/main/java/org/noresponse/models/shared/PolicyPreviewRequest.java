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
 * PolicyPreviewRequest
 *
 * <p>Side-effect-free threshold preview for at most 31 days. Retained first answers are evaluated at
 * their exact frozen scores using the production threshold and hold rules. Changes to admission,
 * source selection, depth or retention are not simulated.
 */
public class PolicyPreviewRequest {

    @JsonProperty("expected_policy_version")
    private long expectedPolicyVersion;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("limit")
    private Long limit;

    @JsonProperty("flows")
    private PolicyPreviewRequestFlows flows;

    @JsonCreator
    public PolicyPreviewRequest(
            @JsonProperty("expected_policy_version") long expectedPolicyVersion,
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("flows") @Nonnull PolicyPreviewRequestFlows flows) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.flows = Optional.ofNullable(flows).orElseThrow(() -> new IllegalArgumentException("flows cannot be null"));
    }

    public PolicyPreviewRequest(
            long expectedPolicyVersion,
            @Nonnull String from,
            @Nonnull String to,
            @Nonnull PolicyPreviewRequestFlows flows) {
        this(expectedPolicyVersion, from, to, null, flows);
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

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    public PolicyPreviewRequestFlows flows() {
        return this.flows;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyPreviewRequest withExpectedPolicyVersion(long expectedPolicyVersion) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        return this;
    }

    public PolicyPreviewRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public PolicyPreviewRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public PolicyPreviewRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    public PolicyPreviewRequest withFlows(@Nonnull PolicyPreviewRequestFlows flows) {
        this.flows = Utils.checkNotNull(flows, "flows");
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
        PolicyPreviewRequest other = (PolicyPreviewRequest) o;
        return Utils.enhancedDeepEquals(this.expectedPolicyVersion, other.expectedPolicyVersion)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.flows, other.flows);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(expectedPolicyVersion, from, to, limit, flows);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyPreviewRequest.class,
                "expectedPolicyVersion",
                expectedPolicyVersion,
                "from",
                from,
                "to",
                to,
                "limit",
                limit,
                "flows",
                flows);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long expectedPolicyVersion;

        private String from;

        private String to;

        private Long limit;

        private PolicyPreviewRequestFlows flows;

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

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        public Builder flows(@Nonnull PolicyPreviewRequestFlows flows) {
            this.flows = Utils.checkNotNull(flows, "flows");
            return this;
        }

        public PolicyPreviewRequest build() {
            return new PolicyPreviewRequest(expectedPolicyVersion, from, to, limit, flows);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "1000", new TypeReference<Long>() {});
    }
}
