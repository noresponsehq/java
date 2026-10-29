package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ReviewComment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("review_version")
    private long reviewVersion;

    @JsonProperty("body")
    private String body;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public ReviewComment(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("review_version") long reviewVersion,
            @JsonProperty("body") @Nonnull String body,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("created_at") @Nonnull String createdAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.reviewVersion = reviewVersion;
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
    }

    public String id() {
        return this.id;
    }

    public long reviewVersion() {
        return this.reviewVersion;
    }

    public String body() {
        return this.body;
    }

    public String createdBy() {
        return this.createdBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewComment withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public ReviewComment withReviewVersion(long reviewVersion) {
        this.reviewVersion = reviewVersion;
        return this;
    }

    public ReviewComment withBody(@Nonnull String body) {
        this.body = Utils.checkNotNull(body, "body");
        return this;
    }

    public ReviewComment withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public ReviewComment withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
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
        ReviewComment other = (ReviewComment) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.reviewVersion, other.reviewVersion)
                && Utils.enhancedDeepEquals(this.body, other.body)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, reviewVersion, body, createdBy, createdAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewComment.class,
                "id",
                id,
                "reviewVersion",
                reviewVersion,
                "body",
                body,
                "createdBy",
                createdBy,
                "createdAt",
                createdAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private long reviewVersion;

        private String body;

        private String createdBy;

        private String createdAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder reviewVersion(long reviewVersion) {
            this.reviewVersion = reviewVersion;
            return this;
        }

        public Builder body(@Nonnull String body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public Builder createdBy(@Nonnull String createdBy) {
            this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public ReviewComment build() {
            return new ReviewComment(id, reviewVersion, body, createdBy, createdAt);
        }
    }
}
