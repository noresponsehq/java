package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

public class ReviewUpdateRequest {

    @JsonProperty("expected_version")
    private long expectedVersion;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("status")
    private ReviewUpdateRequestStatus status;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("priority")
    private Priority priority;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assignee")
    private JsonNullable<String> assignee;

    @JsonCreator
    public ReviewUpdateRequest(
            @JsonProperty("expected_version") long expectedVersion,
            @JsonProperty("status") @Nullable ReviewUpdateRequestStatus status,
            @JsonProperty("priority") @Nullable Priority priority,
            @JsonProperty("assignee") @Nullable JsonNullable<String> assignee) {
        this.expectedVersion = expectedVersion;
        this.status = status;
        this.priority = priority;
        this.assignee = Optional.ofNullable(assignee).orElse(JsonNullable.undefined());
    }

    public ReviewUpdateRequest(long expectedVersion) {
        this(expectedVersion, null, null, null);
    }

    public long expectedVersion() {
        return this.expectedVersion;
    }

    public Optional<ReviewUpdateRequestStatus> status() {
        return Optional.ofNullable(this.status);
    }

    public Optional<Priority> priority() {
        return Optional.ofNullable(this.priority);
    }

    public JsonNullable<String> assignee() {
        return this.assignee;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewUpdateRequest withExpectedVersion(long expectedVersion) {
        this.expectedVersion = expectedVersion;
        return this;
    }

    public ReviewUpdateRequest withStatus(@Nullable ReviewUpdateRequestStatus status) {
        this.status = status;
        return this;
    }

    public ReviewUpdateRequest withPriority(@Nullable Priority priority) {
        this.priority = priority;
        return this;
    }

    public ReviewUpdateRequest withAssignee(@Nullable String assignee) {
        this.assignee = JsonNullable.of(assignee);
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
        ReviewUpdateRequest other = (ReviewUpdateRequest) o;
        return Utils.enhancedDeepEquals(this.expectedVersion, other.expectedVersion)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.priority, other.priority)
                && Utils.enhancedDeepEquals(this.assignee, other.assignee);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(expectedVersion, status, priority, assignee);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewUpdateRequest.class,
                "expectedVersion",
                expectedVersion,
                "status",
                status,
                "priority",
                priority,
                "assignee",
                assignee);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long expectedVersion;

        private ReviewUpdateRequestStatus status;

        private Priority priority;

        private JsonNullable<String> assignee;

        private Builder() {
            // force use of static builder() method
        }

        public Builder expectedVersion(long expectedVersion) {
            this.expectedVersion = expectedVersion;
            return this;
        }

        public Builder status(@Nullable ReviewUpdateRequestStatus status) {
            this.status = status;
            return this;
        }

        public Builder priority(@Nullable Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder assignee(@Nullable String assignee) {
            this.assignee = JsonNullable.of(assignee);
            return this;
        }

        public ReviewUpdateRequest build() {
            return new ReviewUpdateRequest(expectedVersion, status, priority, assignee);
        }
    }
}
