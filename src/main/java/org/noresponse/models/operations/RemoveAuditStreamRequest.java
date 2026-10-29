package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.Environment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RemoveAuditStreamRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=environment")
    private Environment environment;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=endpoint_id")
    private String endpointId;

    @JsonCreator
    public RemoveAuditStreamRequest(
            @Nonnull String workspaceId, @Nonnull Environment environment, @Nonnull String endpointId) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public Environment environment() {
        return this.environment;
    }

    public String endpointId() {
        return this.endpointId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public RemoveAuditStreamRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public RemoveAuditStreamRequest withEnvironment(@Nonnull Environment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public RemoveAuditStreamRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
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
        RemoveAuditStreamRequest other = (RemoveAuditStreamRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.endpointId, other.endpointId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, endpointId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RemoveAuditStreamRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "endpointId",
                endpointId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private Environment environment;

        private String endpointId;

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

        public Builder environment(@Nonnull Environment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        public RemoveAuditStreamRequest build() {
            return new RemoveAuditStreamRequest(workspaceId, environment, endpointId);
        }
    }
}
