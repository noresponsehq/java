package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class DeleteListEntryRequest {
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

    /**
     * The entry's id. An entry of another environment or workspace answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=list_entry_id")
    private String listEntryId;

    @JsonCreator
    public DeleteListEntryRequest(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String listEntryId) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.listEntryId = Optional.ofNullable(listEntryId)
                .orElseThrow(() -> new IllegalArgumentException("listEntryId cannot be null"));
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

    /**
     * The entry's id. An entry of another environment or workspace answers 404.
     */
    public String listEntryId() {
        return this.listEntryId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public DeleteListEntryRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public DeleteListEntryRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * The entry's id. An entry of another environment or workspace answers 404.
     */
    public DeleteListEntryRequest withListEntryId(@Nonnull String listEntryId) {
        this.listEntryId = Utils.checkNotNull(listEntryId, "listEntryId");
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
        DeleteListEntryRequest other = (DeleteListEntryRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.listEntryId, other.listEntryId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, listEntryId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                DeleteListEntryRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "listEntryId",
                listEntryId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private String listEntryId;

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

        /**
         * The entry's id. An entry of another environment or workspace answers 404.
         */
        public Builder listEntryId(@Nonnull String listEntryId) {
            this.listEntryId = Utils.checkNotNull(listEntryId, "listEntryId");
            return this;
        }

        public DeleteListEntryRequest build() {
            return new DeleteListEntryRequest(workspaceId, environment, listEntryId);
        }
    }
}
