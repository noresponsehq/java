package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AccountBindingRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class BindProvisionalAccountRequest {
    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=assessment_id")
    private String assessmentId;

    @NoResponseMetadata("request:mediaType=application/json")
    private AccountBindingRequest body;

    @JsonCreator
    public BindProvisionalAccountRequest(@Nonnull String assessmentId, @Nonnull AccountBindingRequest body) {
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    public AccountBindingRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public BindProvisionalAccountRequest withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    public BindProvisionalAccountRequest withBody(@Nonnull AccountBindingRequest body) {
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
        BindProvisionalAccountRequest other = (BindProvisionalAccountRequest) o;
        return Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(assessmentId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(BindProvisionalAccountRequest.class, "assessmentId", assessmentId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

        private AccountBindingRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
         * 404, not 400.
         */
        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
            return this;
        }

        public Builder body(@Nonnull AccountBindingRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public BindProvisionalAccountRequest build() {
            return new BindProvisionalAccountRequest(assessmentId, body);
        }
    }
}
