package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * Settings
 *
 * <p>One environment's settings: how long its assessments are kept, and for each flow the risk thresholds
 * that decide it and the depth a request that names none is served at, and the sources it refuses.
 * Live and test each have their own. A flow without thresholds of its own uses account_creation's
 * (inherits_account_creation).
 *
 * <p>Every change is recorded in the audit log as settings.updated, with who made it.
 */
public class Settings {
    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    @JsonProperty("environment")
    private KeyEnvironment environment;

    /**
     * Days an assessment is kept after it is created, 1 to 365 (default 365). Shortening hides older
     * assessments at once.
     */
    @JsonProperty("retention_days")
    private long retentionDays;

    /**
     * How many times this environment's policy has changed; 0 while the defaults apply. A served
     * risk.policy_version ends in +t<policy_version> once it is above 0.
     */
    @JsonProperty("policy_version")
    private long policyVersion;

    /**
     * Who last changed the policy: the member's external user id for a change made through the console,
     * the prefix (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that made it, or
     * NoResponse. Absent while the defaults apply.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("updated_by")
    private String updatedBy;

    /**
     * When the policy last changed. Absent while the defaults apply.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("flows")
    private SettingsFlows flows;

    /**
     * The sources this environment refuses, sorted; empty, the default, refuses none. An assessment leaves
     * a refused source out: its coverage is missing (an identifier with no other source lists
     * coverage-unavailable as unavailable) rather than not_found, and a stored result of a refused source
     * is not reused.
     */
    @JsonProperty("refused_sources")
    private List<SourceId> refusedSources;

    @JsonCreator
    public Settings(
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("environment") @Nonnull KeyEnvironment environment,
            @JsonProperty("retention_days") long retentionDays,
            @JsonProperty("policy_version") long policyVersion,
            @JsonProperty("updated_by") @Nullable String updatedBy,
            @JsonProperty("updated_at") @Nullable String updatedAt,
            @JsonProperty("flows") @Nonnull SettingsFlows flows,
            @JsonProperty("refused_sources") @Nonnull List<SourceId> refusedSources) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.retentionDays = retentionDays;
        this.policyVersion = policyVersion;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.flows = Optional.ofNullable(flows).orElseThrow(() -> new IllegalArgumentException("flows cannot be null"));
        this.refusedSources = Optional.ofNullable(refusedSources)
                .orElseThrow(() -> new IllegalArgumentException("refusedSources cannot be null"));
    }

    public Settings(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            long retentionDays,
            long policyVersion,
            @Nonnull SettingsFlows flows,
            @Nonnull List<SourceId> refusedSources) {
        this(workspaceId, environment, retentionDays, policyVersion, null, null, flows, refusedSources);
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    public KeyEnvironment environment() {
        return this.environment;
    }

    /**
     * Days an assessment is kept after it is created, 1 to 365 (default 365). Shortening hides older
     * assessments at once.
     */
    public long retentionDays() {
        return this.retentionDays;
    }

    /**
     * How many times this environment's policy has changed; 0 while the defaults apply. A served
     * risk.policy_version ends in +t<policy_version> once it is above 0.
     */
    public long policyVersion() {
        return this.policyVersion;
    }

    /**
     * Who last changed the policy: the member's external user id for a change made through the console,
     * the prefix (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that made it, or
     * NoResponse. Absent while the defaults apply.
     */
    public Optional<String> updatedBy() {
        return Optional.ofNullable(this.updatedBy);
    }

    /**
     * When the policy last changed. Absent while the defaults apply.
     */
    public Optional<String> updatedAt() {
        return Optional.ofNullable(this.updatedAt);
    }

    public SettingsFlows flows() {
        return this.flows;
    }

    /**
     * The sources this environment refuses, sorted; empty, the default, refuses none. An assessment leaves
     * a refused source out: its coverage is missing (an identifier with no other source lists
     * coverage-unavailable as unavailable) rather than not_found, and a stored result of a refused source
     * is not reused.
     */
    public List<SourceId> refusedSources() {
        return this.refusedSources;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public Settings withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    public Settings withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * Days an assessment is kept after it is created, 1 to 365 (default 365). Shortening hides older
     * assessments at once.
     */
    public Settings withRetentionDays(long retentionDays) {
        this.retentionDays = retentionDays;
        return this;
    }

    /**
     * How many times this environment's policy has changed; 0 while the defaults apply. A served
     * risk.policy_version ends in +t<policy_version> once it is above 0.
     */
    public Settings withPolicyVersion(long policyVersion) {
        this.policyVersion = policyVersion;
        return this;
    }

    /**
     * Who last changed the policy: the member's external user id for a change made through the console,
     * the prefix (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that made it, or
     * NoResponse. Absent while the defaults apply.
     */
    public Settings withUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    /**
     * When the policy last changed. Absent while the defaults apply.
     */
    public Settings withUpdatedAt(@Nullable String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Settings withFlows(@Nonnull SettingsFlows flows) {
        this.flows = Utils.checkNotNull(flows, "flows");
        return this;
    }

    /**
     * The sources this environment refuses, sorted; empty, the default, refuses none. An assessment leaves
     * a refused source out: its coverage is missing (an identifier with no other source lists
     * coverage-unavailable as unavailable) rather than not_found, and a stored result of a refused source
     * is not reused.
     */
    public Settings withRefusedSources(@Nonnull List<SourceId> refusedSources) {
        this.refusedSources = Utils.checkNotNull(refusedSources, "refusedSources");
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
        Settings other = (Settings) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays)
                && Utils.enhancedDeepEquals(this.policyVersion, other.policyVersion)
                && Utils.enhancedDeepEquals(this.updatedBy, other.updatedBy)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.flows, other.flows)
                && Utils.enhancedDeepEquals(this.refusedSources, other.refusedSources);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                workspaceId, environment, retentionDays, policyVersion, updatedBy, updatedAt, flows, refusedSources);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Settings.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "retentionDays",
                retentionDays,
                "policyVersion",
                policyVersion,
                "updatedBy",
                updatedBy,
                "updatedAt",
                updatedAt,
                "flows",
                flows,
                "refusedSources",
                refusedSources);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private long retentionDays;

        private long policyVersion;

        private String updatedBy;

        private String updatedAt;

        private SettingsFlows flows;

        private List<SourceId> refusedSources;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The workspace id, which is also its live environment's workspace id; the test environment's is the
         * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        /**
         * The environment the key belongs to. A live key assesses with live sources; a test key answers from
         * the deterministic simulator.
         */
        public Builder environment(@Nonnull KeyEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * Days an assessment is kept after it is created, 1 to 365 (default 365). Shortening hides older
         * assessments at once.
         */
        public Builder retentionDays(long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        /**
         * How many times this environment's policy has changed; 0 while the defaults apply. A served
         * risk.policy_version ends in +t<policy_version> once it is above 0.
         */
        public Builder policyVersion(long policyVersion) {
            this.policyVersion = policyVersion;
            return this;
        }

        /**
         * Who last changed the policy: the member's external user id for a change made through the console,
         * the prefix (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that made it, or
         * NoResponse. Absent while the defaults apply.
         */
        public Builder updatedBy(@Nullable String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        /**
         * When the policy last changed. Absent while the defaults apply.
         */
        public Builder updatedAt(@Nullable String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder flows(@Nonnull SettingsFlows flows) {
            this.flows = Utils.checkNotNull(flows, "flows");
            return this;
        }

        /**
         * The sources this environment refuses, sorted; empty, the default, refuses none. An assessment leaves
         * a refused source out: its coverage is missing (an identifier with no other source lists
         * coverage-unavailable as unavailable) rather than not_found, and a stored result of a refused source
         * is not reused.
         */
        public Builder refusedSources(@Nonnull List<SourceId> refusedSources) {
            this.refusedSources = Utils.checkNotNull(refusedSources, "refusedSources");
            return this;
        }

        public Settings build() {
            return new Settings(
                    workspaceId,
                    environment,
                    retentionDays,
                    policyVersion,
                    updatedBy,
                    updatedAt,
                    flows,
                    refusedSources);
        }
    }
}
