package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.CustomerExportRequestFilterOutcome;
import org.noresponse.models.shared.Environment;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListAuditEventsRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * Events per page.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * next_cursor from the previous page, unchanged; keep every filter unchanged.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=cursor")
    private String cursor;

    /**
     * Affected scope. API keys can request only their own environment. Omit to read the scopes the caller
     * is allowed.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=environment")
    private Environment environment;

    /**
     * Exact action, such as key.created or account.deleted.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=action")
    private String action;

    /**
     * Exact external user id, public API key id or console credential id.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=actor")
    private String actor;

    /**
     * Exact target kind, such as key, member, setting or workspace.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=target_type")
    private String targetType;

    /**
     * Public target identifier. Audit targets exclude subject identifiers.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=target_id")
    private String targetId;

    /**
     * Exact outcome.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=outcome")
    private CustomerExportRequestFilterOutcome outcome;

    /**
     * Inclusive creation timestamp in RFC3339 format.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=since")
    private String since;

    /**
     * Exclusive creation timestamp in RFC3339 format; must follow since when both are supplied.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=until")
    private String until;

    @JsonCreator
    public ListAuditEventsRequest(
            @Nonnull String workspaceId,
            @Nullable Long limit,
            @Nullable String cursor,
            @Nullable Environment environment,
            @Nullable String action,
            @Nullable String actor,
            @Nullable String targetType,
            @Nullable String targetId,
            @Nullable CustomerExportRequestFilterOutcome outcome,
            @Nullable String since,
            @Nullable String until) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
        this.environment = environment;
        this.action = action;
        this.actor = actor;
        this.targetType = targetType;
        this.targetId = targetId;
        this.outcome = outcome;
        this.since = since;
        this.until = until;
    }

    public ListAuditEventsRequest(@Nonnull String workspaceId) {
        this(workspaceId, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * Events per page.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * next_cursor from the previous page, unchanged; keep every filter unchanged.
     */
    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    /**
     * Affected scope. API keys can request only their own environment. Omit to read the scopes the caller
     * is allowed.
     */
    public Optional<Environment> environment() {
        return Optional.ofNullable(this.environment);
    }

    /**
     * Exact action, such as key.created or account.deleted.
     */
    public Optional<String> action() {
        return Optional.ofNullable(this.action);
    }

    /**
     * Exact external user id, public API key id or console credential id.
     */
    public Optional<String> actor() {
        return Optional.ofNullable(this.actor);
    }

    /**
     * Exact target kind, such as key, member, setting or workspace.
     */
    public Optional<String> targetType() {
        return Optional.ofNullable(this.targetType);
    }

    /**
     * Public target identifier. Audit targets exclude subject identifiers.
     */
    public Optional<String> targetId() {
        return Optional.ofNullable(this.targetId);
    }

    /**
     * Exact outcome.
     */
    public Optional<CustomerExportRequestFilterOutcome> outcome() {
        return Optional.ofNullable(this.outcome);
    }

    /**
     * Inclusive creation timestamp in RFC3339 format.
     */
    public Optional<String> since() {
        return Optional.ofNullable(this.since);
    }

    /**
     * Exclusive creation timestamp in RFC3339 format; must follow since when both are supplied.
     */
    public Optional<String> until() {
        return Optional.ofNullable(this.until);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ListAuditEventsRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * Events per page.
     */
    public ListAuditEventsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * next_cursor from the previous page, unchanged; keep every filter unchanged.
     */
    public ListAuditEventsRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
        return this;
    }

    /**
     * Affected scope. API keys can request only their own environment. Omit to read the scopes the caller
     * is allowed.
     */
    public ListAuditEventsRequest withEnvironment(@Nullable Environment environment) {
        this.environment = environment;
        return this;
    }

    /**
     * Exact action, such as key.created or account.deleted.
     */
    public ListAuditEventsRequest withAction(@Nullable String action) {
        this.action = action;
        return this;
    }

    /**
     * Exact external user id, public API key id or console credential id.
     */
    public ListAuditEventsRequest withActor(@Nullable String actor) {
        this.actor = actor;
        return this;
    }

    /**
     * Exact target kind, such as key, member, setting or workspace.
     */
    public ListAuditEventsRequest withTargetType(@Nullable String targetType) {
        this.targetType = targetType;
        return this;
    }

    /**
     * Public target identifier. Audit targets exclude subject identifiers.
     */
    public ListAuditEventsRequest withTargetId(@Nullable String targetId) {
        this.targetId = targetId;
        return this;
    }

    /**
     * Exact outcome.
     */
    public ListAuditEventsRequest withOutcome(@Nullable CustomerExportRequestFilterOutcome outcome) {
        this.outcome = outcome;
        return this;
    }

    /**
     * Inclusive creation timestamp in RFC3339 format.
     */
    public ListAuditEventsRequest withSince(@Nullable String since) {
        this.since = since;
        return this;
    }

    /**
     * Exclusive creation timestamp in RFC3339 format; must follow since when both are supplied.
     */
    public ListAuditEventsRequest withUntil(@Nullable String until) {
        this.until = until;
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
        ListAuditEventsRequest other = (ListAuditEventsRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.actor, other.actor)
                && Utils.enhancedDeepEquals(this.targetType, other.targetType)
                && Utils.enhancedDeepEquals(this.targetId, other.targetId)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.since, other.since)
                && Utils.enhancedDeepEquals(this.until, other.until);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                workspaceId, limit, cursor, environment, action, actor, targetType, targetId, outcome, since, until);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListAuditEventsRequest.class,
                "workspaceId",
                workspaceId,
                "limit",
                limit,
                "cursor",
                cursor,
                "environment",
                environment,
                "action",
                action,
                "actor",
                actor,
                "targetType",
                targetType,
                "targetId",
                targetId,
                "outcome",
                outcome,
                "since",
                since,
                "until",
                until);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private Long limit;

        private String cursor;

        private Environment environment;

        private String action;

        private String actor;

        private String targetType;

        private String targetId;

        private CustomerExportRequestFilterOutcome outcome;

        private String since;

        private String until;

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
         * Events per page.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * next_cursor from the previous page, unchanged; keep every filter unchanged.
         */
        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        /**
         * Affected scope. API keys can request only their own environment. Omit to read the scopes the caller
         * is allowed.
         */
        public Builder environment(@Nullable Environment environment) {
            this.environment = environment;
            return this;
        }

        /**
         * Exact action, such as key.created or account.deleted.
         */
        public Builder action(@Nullable String action) {
            this.action = action;
            return this;
        }

        /**
         * Exact external user id, public API key id or console credential id.
         */
        public Builder actor(@Nullable String actor) {
            this.actor = actor;
            return this;
        }

        /**
         * Exact target kind, such as key, member, setting or workspace.
         */
        public Builder targetType(@Nullable String targetType) {
            this.targetType = targetType;
            return this;
        }

        /**
         * Public target identifier. Audit targets exclude subject identifiers.
         */
        public Builder targetId(@Nullable String targetId) {
            this.targetId = targetId;
            return this;
        }

        /**
         * Exact outcome.
         */
        public Builder outcome(@Nullable CustomerExportRequestFilterOutcome outcome) {
            this.outcome = outcome;
            return this;
        }

        /**
         * Inclusive creation timestamp in RFC3339 format.
         */
        public Builder since(@Nullable String since) {
            this.since = since;
            return this;
        }

        /**
         * Exclusive creation timestamp in RFC3339 format; must follow since when both are supplied.
         */
        public Builder until(@Nullable String until) {
            this.until = until;
            return this;
        }

        public ListAuditEventsRequest build() {
            return new ListAuditEventsRequest(
                    workspaceId,
                    limit,
                    cursor,
                    environment,
                    action,
                    actor,
                    targetType,
                    targetId,
                    outcome,
                    since,
                    until);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
