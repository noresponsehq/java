package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AuditStreamRequest;
import org.noresponse.models.shared.Environment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ConfigureAuditStreamRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=environment")
    private Environment environment;

    @NoResponseMetadata("request:mediaType=application/json")
    private AuditStreamRequest body;

    @JsonCreator
    public ConfigureAuditStreamRequest(
            @Nonnull String workspaceId, @Nonnull Environment environment, @Nonnull AuditStreamRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
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

    public AuditStreamRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ConfigureAuditStreamRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public ConfigureAuditStreamRequest withEnvironment(@Nonnull Environment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ConfigureAuditStreamRequest withBody(@Nonnull AuditStreamRequest body) {
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
        ConfigureAuditStreamRequest other = (ConfigureAuditStreamRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ConfigureAuditStreamRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private Environment environment;

        private AuditStreamRequest body;

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

        public Builder body(@Nonnull AuditStreamRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ConfigureAuditStreamRequest build() {
            return new ConfigureAuditStreamRequest(workspaceId, environment, body);
        }
    }
}
