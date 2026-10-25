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
 * <p>PATCH /v1/workspaces/{workspace_id}/settings/{environment} body. Changes only what it names, in one
 * transaction; a policy change is one new policy_version. Needs the admin or owner role, or an API key
 * with the management scope for its own environment.
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

    @JsonCreator
    public SettingsRequest(
            @JsonProperty("retention_days") @Nullable Long retentionDays,
            @JsonProperty("refused_sources") @Nullable List<SourceId> refusedSources,
            @JsonProperty("flows") @Nullable SettingsRequestFlows flows) {
        this.retentionDays = retentionDays;
        this.refusedSources = refusedSources;
        this.flows = flows;
    }

    public SettingsRequest() {
        this(null, null, null);
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
                && Utils.enhancedDeepEquals(this.flows, other.flows);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(retentionDays, refusedSources, flows);
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
                flows);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long retentionDays;

        private List<SourceId> refusedSources;

        private SettingsRequestFlows flows;

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

        public SettingsRequest build() {
            return new SettingsRequest(retentionDays, refusedSources, flows);
        }
    }
}
