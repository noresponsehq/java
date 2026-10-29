package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * SettingsRequest
 *
 * <p>Changes only named settings, atomically. Every successful write advances policy_version and retains
 * an immutable settings snapshot. expected_policy_version optionally guards against stale edits;
 * restore always requires it.
 */
public class SettingsRequest {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("retention_days")
    private Long retentionDays;

    /**
     * Replaces the refused sources; [] refuses none. A change applies to new assessments within a minute.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("refused_sources")
    private List<SourceId> refusedSources;

    /**
     * Use account_creation or its deprecated alias signup, not both.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("flows")
    private SettingsRequestFlows flows;

    /**
     * Compare-and-set guard. A stale version returns 409 policy_version_conflict before any change; every
     * settings write including retention advances the version.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expected_policy_version")
    private Long expectedPolicyVersion;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("change_reason")
    private String changeReason;

    @JsonCreator
    public SettingsRequest(
            @JsonProperty("retention_days") @Nullable Long retentionDays,
            @JsonProperty("refused_sources") @Nullable List<SourceId> refusedSources,
            @JsonProperty("flows") @Nullable SettingsRequestFlows flows,
            @JsonProperty("expected_policy_version") @Nullable Long expectedPolicyVersion,
            @JsonProperty("change_reason") @Nullable String changeReason) {
        this.retentionDays = retentionDays;
        this.refusedSources = refusedSources;
        this.flows = flows;
        this.expectedPolicyVersion = expectedPolicyVersion;
        this.changeReason = changeReason;
    }

    public SettingsRequest() {
        this(null, null, null, null, null);
    }

    public Optional<Long> retentionDays() {
        return Optional.ofNullable(this.retentionDays);
    }

    /**
     * Replaces the refused sources; [] refuses none. A change applies to new assessments within a minute.
     */
    public Optional<List<SourceId>> refusedSources() {
        return Optional.ofNullable(this.refusedSources);
    }

    /**
     * Use account_creation or its deprecated alias signup, not both.
     */
    public Optional<SettingsRequestFlows> flows() {
        return Optional.ofNullable(this.flows);
    }

    /**
     * Compare-and-set guard. A stale version returns 409 policy_version_conflict before any change; every
     * settings write including retention advances the version.
     */
    public Optional<Long> expectedPolicyVersion() {
        return Optional.ofNullable(this.expectedPolicyVersion);
    }

    public Optional<String> changeReason() {
        return Optional.ofNullable(this.changeReason);
    }

    public static Builder builder() {
        return new Builder();
    }

    public SettingsRequest withRetentionDays(@Nullable Long retentionDays) {
        this.retentionDays = retentionDays;
        return this;
    }

    /**
     * Replaces the refused sources; [] refuses none. A change applies to new assessments within a minute.
     */
    public SettingsRequest withRefusedSources(@Nullable List<SourceId> refusedSources) {
        this.refusedSources = refusedSources;
        return this;
    }

    /**
     * Use account_creation or its deprecated alias signup, not both.
     */
    public SettingsRequest withFlows(@Nullable SettingsRequestFlows flows) {
        this.flows = flows;
        return this;
    }

    /**
     * Compare-and-set guard. A stale version returns 409 policy_version_conflict before any change; every
     * settings write including retention advances the version.
     */
    public SettingsRequest withExpectedPolicyVersion(@Nullable Long expectedPolicyVersion) {
        this.expectedPolicyVersion = expectedPolicyVersion;
        return this;
    }

    public SettingsRequest withChangeReason(@Nullable String changeReason) {
        this.changeReason = changeReason;
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
        SettingsRequest other = (SettingsRequest) o;
        return Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays)
                && Utils.enhancedDeepEquals(this.refusedSources, other.refusedSources)
                && Utils.enhancedDeepEquals(this.flows, other.flows)
                && Utils.enhancedDeepEquals(this.expectedPolicyVersion, other.expectedPolicyVersion)
                && Utils.enhancedDeepEquals(this.changeReason, other.changeReason);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(retentionDays, refusedSources, flows, expectedPolicyVersion, changeReason);
    }

    @Override
    public String toString() {
        return Utils.toString(
                SettingsRequest.class,
                "retentionDays",
                retentionDays,
                "refusedSources",
                refusedSources,
                "flows",
                flows,
                "expectedPolicyVersion",
                expectedPolicyVersion,
                "changeReason",
                changeReason);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long retentionDays;

        private List<SourceId> refusedSources;

        private SettingsRequestFlows flows;

        private Long expectedPolicyVersion;

        private String changeReason;

        private Builder() {
            // force use of static builder() method
        }

        public Builder retentionDays(@Nullable Long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        /**
         * Replaces the refused sources; [] refuses none. A change applies to new assessments within a minute.
         */
        public Builder refusedSources(@Nullable List<SourceId> refusedSources) {
            this.refusedSources = refusedSources;
            return this;
        }

        /**
         * Use account_creation or its deprecated alias signup, not both.
         */
        public Builder flows(@Nullable SettingsRequestFlows flows) {
            this.flows = flows;
            return this;
        }

        /**
         * Compare-and-set guard. A stale version returns 409 policy_version_conflict before any change; every
         * settings write including retention advances the version.
         */
        public Builder expectedPolicyVersion(@Nullable Long expectedPolicyVersion) {
            this.expectedPolicyVersion = expectedPolicyVersion;
            return this;
        }

        public Builder changeReason(@Nullable String changeReason) {
            this.changeReason = changeReason;
            return this;
        }

        public SettingsRequest build() {
            return new SettingsRequest(retentionDays, refusedSources, flows, expectedPolicyVersion, changeReason);
        }
    }
}
