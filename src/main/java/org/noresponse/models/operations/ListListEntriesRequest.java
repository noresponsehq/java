package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListListEntriesRequest {
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

    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=cursor")
    private String cursor;

    @JsonCreator
    public ListListEntriesRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String cursor) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.limit = limit;
        this.cursor = cursor;
    }

    public ListListEntriesRequest(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        this(workspaceId, environment, null, null);
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

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ListListEntriesRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public ListListEntriesRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ListListEntriesRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    public ListListEntriesRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
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
        ListListEntriesRequest other = (ListListEntriesRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, limit, cursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListListEntriesRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "limit",
                limit,
                "cursor",
                cursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private Long limit;

        private String cursor;

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

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        public ListListEntriesRequest build() {
            return new ListListEntriesRequest(workspaceId, environment, limit, cursor);
        }
    }
}
