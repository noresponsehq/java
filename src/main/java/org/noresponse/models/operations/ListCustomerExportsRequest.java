package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.CustomerExportRequestKind;
import org.noresponse.models.shared.Environment;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListCustomerExportsRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=kind")
    private CustomerExportRequestKind kind;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=environment")
    private Environment environment;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=cursor")
    private String cursor;

    @JsonCreator
    public ListCustomerExportsRequest(
            @Nonnull String workspaceId,
            @Nonnull CustomerExportRequestKind kind,
            @Nonnull Environment environment,
            @Nullable Long limit,
            @Nullable String cursor) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
    }

    public ListCustomerExportsRequest(
            @Nonnull String workspaceId, @Nonnull CustomerExportRequestKind kind, @Nonnull Environment environment) {
        this(workspaceId, kind, environment, null, null);
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public CustomerExportRequestKind kind() {
        return this.kind;
    }

    public Environment environment() {
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
    public ListCustomerExportsRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public ListCustomerExportsRequest withKind(@Nonnull CustomerExportRequestKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public ListCustomerExportsRequest withEnvironment(@Nonnull Environment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ListCustomerExportsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    public ListCustomerExportsRequest withCursor(@Nullable String cursor) {
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
        ListCustomerExportsRequest other = (ListCustomerExportsRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, kind, environment, limit, cursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListCustomerExportsRequest.class,
                "workspaceId",
                workspaceId,
                "kind",
                kind,
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

        private CustomerExportRequestKind kind;

        private Environment environment;

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

        public Builder kind(@Nonnull CustomerExportRequestKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder environment(@Nonnull Environment environment) {
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

        public ListCustomerExportsRequest build() {
            return new ListCustomerExportsRequest(workspaceId, kind, environment, limit, cursor);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "25", new TypeReference<Long>() {});
    }
}
