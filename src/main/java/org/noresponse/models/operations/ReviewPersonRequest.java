package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.PersonReviewRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ReviewPersonRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @NoResponseMetadata("request:mediaType=application/json")
    private PersonReviewRequest body;

    @JsonCreator
    public ReviewPersonRequest(@Nonnull String groupId, @Nonnull String personId, @Nonnull PersonReviewRequest body) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    public String personId() {
        return this.personId;
    }

    public PersonReviewRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewPersonRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public ReviewPersonRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public ReviewPersonRequest withBody(@Nonnull PersonReviewRequest body) {
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
        ReviewPersonRequest other = (ReviewPersonRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, personId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewPersonRequest.class, "groupId", groupId, "personId", personId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private String personId;

        private PersonReviewRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder body(@Nonnull PersonReviewRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ReviewPersonRequest build() {
            return new ReviewPersonRequest(groupId, personId, body);
        }
    }
}
