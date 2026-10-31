package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.Priority;
import org.noresponse.models.shared.ReviewStatus;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListReviewsRequest {
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

    @NoResponseMetadata("queryParam:style=form,explode=true,name=status")
    private ReviewStatus status;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=priority")
    private Priority priority;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=assignee")
    private String assignee;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=unassigned")
    private Boolean unassigned;

    /**
     * Filter by immutable member ID. Mutually exclusive with assignee and unassigned=true.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=assignee_principal_id")
    private String assigneePrincipalId;

    @JsonCreator
    public ListReviewsRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String cursor,
            @Nullable ReviewStatus status,
            @Nullable Priority priority,
            @Nullable String assignee,
            @Nullable Boolean unassigned,
            @Nullable String assigneePrincipalId) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
        this.status = status;
        this.priority = priority;
        this.assignee = assignee;
        this.unassigned = unassigned;
        this.assigneePrincipalId = assigneePrincipalId;
    }

    public ListReviewsRequest(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        this(workspaceId, environment, null, null, null, null, null, null, null);
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

    public Optional<ReviewStatus> status() {
        return Optional.ofNullable(this.status);
    }

    public Optional<Priority> priority() {
        return Optional.ofNullable(this.priority);
    }

    public Optional<String> assignee() {
        return Optional.ofNullable(this.assignee);
    }

    public Optional<Boolean> unassigned() {
        return Optional.ofNullable(this.unassigned);
    }

    /**
     * Filter by immutable member ID. Mutually exclusive with assignee and unassigned=true.
     */
    public Optional<String> assigneePrincipalId() {
        return Optional.ofNullable(this.assigneePrincipalId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ListReviewsRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public ListReviewsRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ListReviewsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    public ListReviewsRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
        return this;
    }

    public ListReviewsRequest withStatus(@Nullable ReviewStatus status) {
        this.status = status;
        return this;
    }

    public ListReviewsRequest withPriority(@Nullable Priority priority) {
        this.priority = priority;
        return this;
    }

    public ListReviewsRequest withAssignee(@Nullable String assignee) {
        this.assignee = assignee;
        return this;
    }

    public ListReviewsRequest withUnassigned(@Nullable Boolean unassigned) {
        this.unassigned = unassigned;
        return this;
    }

    /**
     * Filter by immutable member ID. Mutually exclusive with assignee and unassigned=true.
     */
    public ListReviewsRequest withAssigneePrincipalId(@Nullable String assigneePrincipalId) {
        this.assigneePrincipalId = assigneePrincipalId;
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
        ListReviewsRequest other = (ListReviewsRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.priority, other.priority)
                && Utils.enhancedDeepEquals(this.assignee, other.assignee)
                && Utils.enhancedDeepEquals(this.unassigned, other.unassigned)
                && Utils.enhancedDeepEquals(this.assigneePrincipalId, other.assigneePrincipalId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                workspaceId, environment, limit, cursor, status, priority, assignee, unassigned, assigneePrincipalId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListReviewsRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "limit",
                limit,
                "cursor",
                cursor,
                "status",
                status,
                "priority",
                priority,
                "assignee",
                assignee,
                "unassigned",
                unassigned,
                "assigneePrincipalId",
                assigneePrincipalId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private Long limit;

        private String cursor;

        private ReviewStatus status;

        private Priority priority;

        private String assignee;

        private Boolean unassigned;

        private String assigneePrincipalId;

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

        public Builder status(@Nullable ReviewStatus status) {
            this.status = status;
            return this;
        }

        public Builder priority(@Nullable Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder assignee(@Nullable String assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder unassigned(@Nullable Boolean unassigned) {
            this.unassigned = unassigned;
            return this;
        }

        /**
         * Filter by immutable member ID. Mutually exclusive with assignee and unassigned=true.
         */
        public Builder assigneePrincipalId(@Nullable String assigneePrincipalId) {
            this.assigneePrincipalId = assigneePrincipalId;
            return this;
        }

        public ListReviewsRequest build() {
            return new ListReviewsRequest(
                    workspaceId,
                    environment,
                    limit,
                    cursor,
                    status,
                    priority,
                    assignee,
                    unassigned,
                    assigneePrincipalId);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "50", new TypeReference<Long>() {});
    }
}
