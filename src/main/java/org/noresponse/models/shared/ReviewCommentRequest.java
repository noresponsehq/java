package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ReviewCommentRequest {

    @JsonProperty("expected_version")
    private long expectedVersion;

    @JsonProperty("body")
    private String body;

    @JsonCreator
    public ReviewCommentRequest(
            @JsonProperty("expected_version") long expectedVersion, @JsonProperty("body") @Nonnull String body) {
        this.expectedVersion = expectedVersion;
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public long expectedVersion() {
        return this.expectedVersion;
    }

    public String body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewCommentRequest withExpectedVersion(long expectedVersion) {
        this.expectedVersion = expectedVersion;
        return this;
    }

    public ReviewCommentRequest withBody(@Nonnull String body) {
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
        ReviewCommentRequest other = (ReviewCommentRequest) o;
        return Utils.enhancedDeepEquals(this.expectedVersion, other.expectedVersion)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(expectedVersion, body);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewCommentRequest.class, "expectedVersion", expectedVersion, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long expectedVersion;

        private String body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder expectedVersion(long expectedVersion) {
            this.expectedVersion = expectedVersion;
            return this;
        }

        public Builder body(@Nonnull String body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ReviewCommentRequest build() {
            return new ReviewCommentRequest(expectedVersion, body);
        }
    }
}
