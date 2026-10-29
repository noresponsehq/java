package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PolicyRestoreRequest
 *
 * <p>Publish a captured configuration as a new version. Version checks and publication are atomic. Longer
 * retention does not revive expired records, and restored policy does not undo customer enforcement.
 */
public class PolicyRestoreRequest {

    @JsonProperty("version")
    private long version;

    @JsonProperty("expected_policy_version")
    private long expectedPolicyVersion;

    @JsonProperty("change_reason")
    private String changeReason;

    @JsonCreator
    public PolicyRestoreRequest(
            @JsonProperty("version") long version,
            @JsonProperty("expected_policy_version") long expectedPolicyVersion,
            @JsonProperty("change_reason") @Nonnull String changeReason) {
        this.version = version;
        this.expectedPolicyVersion = expectedPolicyVersion;
        this.changeReason = Optional.ofNullable(changeReason)
                .orElseThrow(() -> new IllegalArgumentException("changeReason cannot be null"));
    }

    public long version() {
        return this.version;
    }

    public long expectedPolicyVersion() {
        return this.expectedPolicyVersion;
    }

    public String changeReason() {
        return this.changeReason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyRestoreRequest withVersion(long version) {
        this.version = version;
        return this;
    }

    public PolicyRestoreRequest withExpectedPolicyVersion(long expectedPolicyVersion) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        return this;
    }

    public PolicyRestoreRequest withChangeReason(@Nonnull String changeReason) {
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
        PolicyRestoreRequest other = (PolicyRestoreRequest) o;
        return Utils.enhancedDeepEquals(this.version, other.version)
                && Utils.enhancedDeepEquals(this.expectedPolicyVersion, other.expectedPolicyVersion)
                && Utils.enhancedDeepEquals(this.changeReason, other.changeReason);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(version, expectedPolicyVersion, changeReason);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyRestoreRequest.class,
                "version",
                version,
                "expectedPolicyVersion",
                expectedPolicyVersion,
                "changeReason",
                changeReason);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long version;

        private long expectedPolicyVersion;

        private String changeReason;

        private Builder() {
            // force use of static builder() method
        }

        public Builder version(long version) {
            this.version = version;
            return this;
        }

        public Builder expectedPolicyVersion(long expectedPolicyVersion) {
            this.expectedPolicyVersion = expectedPolicyVersion;
            return this;
        }

        public Builder changeReason(@Nonnull String changeReason) {
            this.changeReason = Utils.checkNotNull(changeReason, "changeReason");
            return this;
        }

        public PolicyRestoreRequest build() {
            return new PolicyRestoreRequest(version, expectedPolicyVersion, changeReason);
        }
    }
}
