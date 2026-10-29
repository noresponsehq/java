package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class PolicyEvaluationPublishRequest {

    @JsonProperty("expected_policy_version")
    private long expectedPolicyVersion;

    @JsonProperty("manifest_sha256")
    private String manifestSha256;

    @JsonProperty("input_sha256")
    private String inputSha256;

    @JsonProperty("result_sha256")
    private String resultSha256;

    @JsonProperty("candidate")
    private Thresholds candidate;

    @JsonProperty("change_reason")
    private String changeReason;

    @JsonCreator
    public PolicyEvaluationPublishRequest(
            @JsonProperty("expected_policy_version") long expectedPolicyVersion,
            @JsonProperty("manifest_sha256") @Nonnull String manifestSha256,
            @JsonProperty("input_sha256") @Nonnull String inputSha256,
            @JsonProperty("result_sha256") @Nonnull String resultSha256,
            @JsonProperty("candidate") @Nonnull Thresholds candidate,
            @JsonProperty("change_reason") @Nonnull String changeReason) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        this.manifestSha256 = Optional.ofNullable(manifestSha256)
                .orElseThrow(() -> new IllegalArgumentException("manifestSha256 cannot be null"));
        this.inputSha256 = Optional.ofNullable(inputSha256)
                .orElseThrow(() -> new IllegalArgumentException("inputSha256 cannot be null"));
        this.resultSha256 = Optional.ofNullable(resultSha256)
                .orElseThrow(() -> new IllegalArgumentException("resultSha256 cannot be null"));
        this.candidate = Optional.ofNullable(candidate)
                .orElseThrow(() -> new IllegalArgumentException("candidate cannot be null"));
        this.changeReason = Optional.ofNullable(changeReason)
                .orElseThrow(() -> new IllegalArgumentException("changeReason cannot be null"));
    }

    public long expectedPolicyVersion() {
        return this.expectedPolicyVersion;
    }

    public String manifestSha256() {
        return this.manifestSha256;
    }

    public String inputSha256() {
        return this.inputSha256;
    }

    public String resultSha256() {
        return this.resultSha256;
    }

    public Thresholds candidate() {
        return this.candidate;
    }

    public String changeReason() {
        return this.changeReason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyEvaluationPublishRequest withExpectedPolicyVersion(long expectedPolicyVersion) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        return this;
    }

    public PolicyEvaluationPublishRequest withManifestSha256(@Nonnull String manifestSha256) {
        this.manifestSha256 = Utils.checkNotNull(manifestSha256, "manifestSha256");
        return this;
    }

    public PolicyEvaluationPublishRequest withInputSha256(@Nonnull String inputSha256) {
        this.inputSha256 = Utils.checkNotNull(inputSha256, "inputSha256");
        return this;
    }

    public PolicyEvaluationPublishRequest withResultSha256(@Nonnull String resultSha256) {
        this.resultSha256 = Utils.checkNotNull(resultSha256, "resultSha256");
        return this;
    }

    public PolicyEvaluationPublishRequest withCandidate(@Nonnull Thresholds candidate) {
        this.candidate = Utils.checkNotNull(candidate, "candidate");
        return this;
    }

    public PolicyEvaluationPublishRequest withChangeReason(@Nonnull String changeReason) {
        this.changeReason = Utils.checkNotNull(changeReason, "changeReason");
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
        PolicyEvaluationPublishRequest other = (PolicyEvaluationPublishRequest) o;
        return Utils.enhancedDeepEquals(this.expectedPolicyVersion, other.expectedPolicyVersion)
                && Utils.enhancedDeepEquals(this.manifestSha256, other.manifestSha256)
                && Utils.enhancedDeepEquals(this.inputSha256, other.inputSha256)
                && Utils.enhancedDeepEquals(this.resultSha256, other.resultSha256)
                && Utils.enhancedDeepEquals(this.candidate, other.candidate)
                && Utils.enhancedDeepEquals(this.changeReason, other.changeReason);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                expectedPolicyVersion, manifestSha256, inputSha256, resultSha256, candidate, changeReason);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyEvaluationPublishRequest.class,
                "expectedPolicyVersion",
                expectedPolicyVersion,
                "manifestSha256",
                manifestSha256,
                "inputSha256",
                inputSha256,
                "resultSha256",
                resultSha256,
                "candidate",
                candidate,
                "changeReason",
                changeReason);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long expectedPolicyVersion;

        private String manifestSha256;

        private String inputSha256;

        private String resultSha256;

        private Thresholds candidate;

        private String changeReason;

        private Builder() {
            // force use of static builder() method
        }

        public Builder expectedPolicyVersion(long expectedPolicyVersion) {
            this.expectedPolicyVersion = expectedPolicyVersion;
            return this;
        }

        public Builder manifestSha256(@Nonnull String manifestSha256) {
            this.manifestSha256 = Utils.checkNotNull(manifestSha256, "manifestSha256");
            return this;
        }

        public Builder inputSha256(@Nonnull String inputSha256) {
            this.inputSha256 = Utils.checkNotNull(inputSha256, "inputSha256");
            return this;
        }

        public Builder resultSha256(@Nonnull String resultSha256) {
            this.resultSha256 = Utils.checkNotNull(resultSha256, "resultSha256");
            return this;
        }

        public Builder candidate(@Nonnull Thresholds candidate) {
            this.candidate = Utils.checkNotNull(candidate, "candidate");
            return this;
        }

        public Builder changeReason(@Nonnull String changeReason) {
            this.changeReason = Utils.checkNotNull(changeReason, "changeReason");
            return this;
        }

        public PolicyEvaluationPublishRequest build() {
            return new PolicyEvaluationPublishRequest(
                    expectedPolicyVersion, manifestSha256, inputSha256, resultSha256, candidate, changeReason);
        }
    }
}
