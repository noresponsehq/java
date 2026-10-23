package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class DeleteAssessmentRequest {
    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=assessment_id")
    private String assessmentId;

    @JsonCreator
    public DeleteAssessmentRequest(@Nonnull String assessmentId) {
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public DeleteAssessmentRequest withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
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
        DeleteAssessmentRequest other = (DeleteAssessmentRequest) o;
        return Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(assessmentId);
    }

    @Override
    public String toString() {
        return Utils.toString(DeleteAssessmentRequest.class, "assessmentId", assessmentId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

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

        public DeleteAssessmentRequest build() {
            return new DeleteAssessmentRequest(assessmentId);
        }
    }
}
