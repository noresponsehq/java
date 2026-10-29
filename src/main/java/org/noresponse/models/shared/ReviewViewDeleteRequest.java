package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

public class ReviewViewDeleteRequest {

    @JsonProperty("expected_version")
    private long expectedVersion;

    @JsonCreator
    public ReviewViewDeleteRequest(@JsonProperty("expected_version") long expectedVersion) {
        this.expectedVersion = expectedVersion;
    }

    public long expectedVersion() {
        return this.expectedVersion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewViewDeleteRequest withExpectedVersion(long expectedVersion) {
        this.expectedVersion = expectedVersion;
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
        ReviewViewDeleteRequest other = (ReviewViewDeleteRequest) o;
        return Utils.enhancedDeepEquals(this.expectedVersion, other.expectedVersion);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(expectedVersion);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewViewDeleteRequest.class, "expectedVersion", expectedVersion);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long expectedVersion;

        private Builder() {
            // force use of static builder() method
        }

        public Builder expectedVersion(long expectedVersion) {
            this.expectedVersion = expectedVersion;
            return this;
        }

        public ReviewViewDeleteRequest build() {
            return new ReviewViewDeleteRequest(expectedVersion);
        }
    }
}
