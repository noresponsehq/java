package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetAssessmentRequest {
    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=assessment_id")
    private String assessmentId;

    /**
     * Optional standard or separately authorised detailed customer view; raw is rejected.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=response_mode")
    private ResponseMode responseMode;

    @JsonCreator
    public GetAssessmentRequest(@Nonnull String assessmentId, @Nullable ResponseMode responseMode) {
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.responseMode = responseMode;
    }

    public GetAssessmentRequest(@Nonnull String assessmentId) {
        this(assessmentId, null);
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * Optional standard or separately authorised detailed customer view; raw is rejected.
     */
    public Optional<ResponseMode> responseMode() {
        return Optional.ofNullable(this.responseMode);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public GetAssessmentRequest withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * Optional standard or separately authorised detailed customer view; raw is rejected.
     */
    public GetAssessmentRequest withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
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
        GetAssessmentRequest other = (GetAssessmentRequest) o;
        return Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(assessmentId, responseMode);
    }

    @Override
    public String toString() {
        return Utils.toString(GetAssessmentRequest.class, "assessmentId", assessmentId, "responseMode", responseMode);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

        private ResponseMode responseMode;

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

        /**
         * Optional standard or separately authorised detailed customer view; raw is rejected.
         */
        public Builder responseMode(@Nullable ResponseMode responseMode) {
            this.responseMode = responseMode;
            return this;
        }

        public GetAssessmentRequest build() {
            return new GetAssessmentRequest(assessmentId, responseMode);
        }
    }
}
