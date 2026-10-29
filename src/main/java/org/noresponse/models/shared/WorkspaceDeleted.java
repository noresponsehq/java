package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WorkspaceDeleted
 *
 * <p>Terminal for the receiving endpoint's environment: its workspace was deleted. Every key of the
 * environment already answers 401, its assessments and accounts are gone, and no further event
 * follows. The workspace id is not reused, so a later event naming it cannot arrive.
 */
public class WorkspaceDeleted {
    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    /**
     * When the deletion took effect, equal to deleted_at in the DELETE answer.
     */
    @JsonProperty("deleted_at")
    private String deletedAt;

    @JsonCreator
    public WorkspaceDeleted(
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("deleted_at") @Nonnull String deletedAt) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.deletedAt = Optional.ofNullable(deletedAt)
                .orElseThrow(() -> new IllegalArgumentException("deletedAt cannot be null"));
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    /**
     * When the deletion took effect, equal to deleted_at in the DELETE answer.
     */
    public String deletedAt() {
        return this.deletedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public WorkspaceDeleted withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public WorkspaceDeleted withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * When the deletion took effect, equal to deleted_at in the DELETE answer.
     */
    public WorkspaceDeleted withDeletedAt(@Nonnull String deletedAt) {
        this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
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
        WorkspaceDeleted other = (WorkspaceDeleted) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.deletedAt, other.deletedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, deletedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WorkspaceDeleted.class, "workspaceId", workspaceId, "environment", environment, "deletedAt", deletedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private AuditSettingsEnvironment environment;

        private String deletedAt;

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

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * When the deletion took effect, equal to deleted_at in the DELETE answer.
         */
        public Builder deletedAt(@Nonnull String deletedAt) {
            this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
            return this;
        }

        public WorkspaceDeleted build() {
            return new WorkspaceDeleted(workspaceId, environment, deletedAt);
        }
    }
}
