package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.PersonReviewRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ReviewInvestigationRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=investigation_id")
    private String investigationId;

    @NoResponseMetadata("request:mediaType=application/json")
    private PersonReviewRequest body;

    @JsonCreator
    public ReviewInvestigationRequest(@Nonnull String investigationId, @Nonnull PersonReviewRequest body) {
        this.investigationId = Optional.ofNullable(investigationId)
                .orElseThrow(() -> new IllegalArgumentException("investigationId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String investigationId() {
        return this.investigationId;
    }

    public PersonReviewRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewInvestigationRequest withInvestigationId(@Nonnull String investigationId) {
        this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
        return this;
    }

    public ReviewInvestigationRequest withBody(@Nonnull PersonReviewRequest body) {
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
        ReviewInvestigationRequest other = (ReviewInvestigationRequest) o;
        return Utils.enhancedDeepEquals(this.investigationId, other.investigationId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(investigationId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewInvestigationRequest.class, "investigationId", investigationId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String investigationId;

        private PersonReviewRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder investigationId(@Nonnull String investigationId) {
            this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
            return this;
        }

        public Builder body(@Nonnull PersonReviewRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ReviewInvestigationRequest build() {
            return new ReviewInvestigationRequest(investigationId, body);
        }
    }
}
