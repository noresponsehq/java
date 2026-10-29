package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AuditSettingsEnvironment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetAuditRetentionRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=environment")
    private AuditSettingsEnvironment environment;

    @JsonCreator
    public GetAuditRetentionRequest(@Nonnull String workspaceId, @Nonnull AuditSettingsEnvironment environment) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public GetAuditRetentionRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public GetAuditRetentionRequest withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
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
        GetAuditRetentionRequest other = (GetAuditRetentionRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment);
    }

    @Override
    public String toString() {
        return Utils.toString(GetAuditRetentionRequest.class, "workspaceId", workspaceId, "environment", environment);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private AuditSettingsEnvironment environment;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
         * test environment's id answer 404.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public GetAuditRetentionRequest build() {
            return new GetAuditRetentionRequest(workspaceId, environment);
        }
    }
}
