package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListPolicyHistoryRequest {
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

    @NoResponseMetadata("queryParam:style=form,explode=true,name=before_version")
    private Long beforeVersion;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    @JsonCreator
    public ListPolicyHistoryRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long beforeVersion,
            @Nullable Long limit) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.beforeVersion = beforeVersion;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
    }

    public ListPolicyHistoryRequest(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
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

    public Optional<Long> beforeVersion() {
        return Optional.ofNullable(this.beforeVersion);
    }

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ListPolicyHistoryRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public ListPolicyHistoryRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ListPolicyHistoryRequest withBeforeVersion(@Nullable Long beforeVersion) {
        this.beforeVersion = beforeVersion;
        return this;
    }

    public ListPolicyHistoryRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
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
        ListPolicyHistoryRequest other = (ListPolicyHistoryRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.beforeVersion, other.beforeVersion)
                && Utils.enhancedDeepEquals(this.limit, other.limit);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, beforeVersion, limit);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListPolicyHistoryRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "beforeVersion",
                beforeVersion,
                "limit",
                limit);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private Long beforeVersion;

        private Long limit;

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

        public Builder beforeVersion(@Nullable Long beforeVersion) {
            this.beforeVersion = beforeVersion;
            return this;
        }

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        public ListPolicyHistoryRequest build() {
            return new ListPolicyHistoryRequest(workspaceId, environment, beforeVersion, limit);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "50", new TypeReference<Long>() {});
    }
}
