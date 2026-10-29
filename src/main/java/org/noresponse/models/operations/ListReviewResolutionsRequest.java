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

public class ListReviewResolutionsRequest {
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

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=review_id")
    private String reviewId;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=before_version")
    private Long beforeVersion;

    @JsonCreator
    public ListReviewResolutionsRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nullable Long limit,
            @Nullable Long beforeVersion) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.reviewId = Optional.ofNullable(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("reviewId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.beforeVersion = beforeVersion;
    }

    public ListReviewResolutionsRequest(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String reviewId) {
        this(workspaceId, environment, reviewId, null, null);
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

    public String reviewId() {
        return this.reviewId;
    }

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    public Optional<Long> beforeVersion() {
        return Optional.ofNullable(this.beforeVersion);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ListReviewResolutionsRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public ListReviewResolutionsRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ListReviewResolutionsRequest withReviewId(@Nonnull String reviewId) {
        this.reviewId = Utils.checkNotNull(reviewId, "reviewId");
        return this;
    }

    public ListReviewResolutionsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    public ListReviewResolutionsRequest withBeforeVersion(@Nullable Long beforeVersion) {
        this.beforeVersion = beforeVersion;
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
        ListReviewResolutionsRequest other = (ListReviewResolutionsRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.reviewId, other.reviewId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.beforeVersion, other.beforeVersion);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, reviewId, limit, beforeVersion);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListReviewResolutionsRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "reviewId",
                reviewId,
                "limit",
                limit,
                "beforeVersion",
                beforeVersion);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private String reviewId;

        private Long limit;

        private Long beforeVersion;

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

        public Builder reviewId(@Nonnull String reviewId) {
            this.reviewId = Utils.checkNotNull(reviewId, "reviewId");
            return this;
        }

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        public Builder beforeVersion(@Nullable Long beforeVersion) {
            this.beforeVersion = beforeVersion;
            return this;
        }

        public ListReviewResolutionsRequest build() {
            return new ListReviewResolutionsRequest(workspaceId, environment, reviewId, limit, beforeVersion);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "50", new TypeReference<Long>() {});
    }
}
