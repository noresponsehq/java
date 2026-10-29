package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AuditStreamReplayRequest;
import org.noresponse.models.shared.Environment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ReplayAuditStreamRequest {
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

    @NoResponseMetadata("request:mediaType=application/json")
    private AuditStreamReplayRequest body;

    @JsonCreator
    public ReplayAuditStreamRequest(
            @Nonnull String workspaceId,
            @Nonnull Environment environment,
            @Nonnull String endpointId,
            @Nonnull AuditStreamReplayRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
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

    public AuditStreamReplayRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ReplayAuditStreamRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public ReplayAuditStreamRequest withEnvironment(@Nonnull Environment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ReplayAuditStreamRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    public ReplayAuditStreamRequest withBody(@Nonnull AuditStreamReplayRequest body) {
        this.body = Utils.checkNotNull(body, "body");
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
        ReplayAuditStreamRequest other = (ReplayAuditStreamRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, endpointId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReplayAuditStreamRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "endpointId",
                endpointId,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private Environment environment;

        private String endpointId;

        private AuditStreamReplayRequest body;

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

        public Builder body(@Nonnull AuditStreamReplayRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ReplayAuditStreamRequest build() {
            return new ReplayAuditStreamRequest(workspaceId, environment, endpointId, body);
        }
    }
}
