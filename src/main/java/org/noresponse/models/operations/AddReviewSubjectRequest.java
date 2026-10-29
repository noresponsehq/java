package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewSubjectRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class AddReviewSubjectRequest {
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

    @NoResponseMetadata("request:mediaType=application/json")
    private ReviewSubjectRequest body;

    @JsonCreator
    public AddReviewSubjectRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nonnull ReviewSubjectRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.reviewId = Optional.ofNullable(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("reviewId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
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

    public ReviewSubjectRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public AddReviewSubjectRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public AddReviewSubjectRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public AddReviewSubjectRequest withReviewId(@Nonnull String reviewId) {
        this.reviewId = Utils.checkNotNull(reviewId, "reviewId");
        return this;
    }

    public AddReviewSubjectRequest withBody(@Nonnull ReviewSubjectRequest body) {
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
        AddReviewSubjectRequest other = (AddReviewSubjectRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.reviewId, other.reviewId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, reviewId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AddReviewSubjectRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "reviewId",
                reviewId,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private String reviewId;

        private ReviewSubjectRequest body;

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

        public Builder body(@Nonnull ReviewSubjectRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public AddReviewSubjectRequest build() {
            return new AddReviewSubjectRequest(workspaceId, environment, reviewId, body);
        }
    }
}
