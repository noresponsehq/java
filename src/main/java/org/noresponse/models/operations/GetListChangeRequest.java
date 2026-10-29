package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetListChangeRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=environment")
    private KeyEnvironment environment;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=change_id")
    private String changeId;

    @JsonCreator
    public GetListChangeRequest(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String changeId) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.changeId = Optional.ofNullable(changeId)
                .orElseThrow(() -> new IllegalArgumentException("changeId cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public KeyEnvironment environment() {
        return this.environment;
    }

    public String changeId() {
        return this.changeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public GetListChangeRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public GetListChangeRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public GetListChangeRequest withChangeId(@Nonnull String changeId) {
        this.changeId = Utils.checkNotNull(changeId, "changeId");
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
        GetListChangeRequest other = (GetListChangeRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.changeId, other.changeId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, changeId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                GetListChangeRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "changeId",
                changeId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private String changeId;

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

        /**
         * The environment: live or test. Each has its own settings. Any other value answers 400.
         *
         * <p>An API key reads and changes only its own environment's; the other answers 404.
         */
        public Builder environment(@Nonnull KeyEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder changeId(@Nonnull String changeId) {
            this.changeId = Utils.checkNotNull(changeId, "changeId");
            return this;
        }

        public GetListChangeRequest build() {
            return new GetListChangeRequest(workspaceId, environment, changeId);
        }
    }
}
