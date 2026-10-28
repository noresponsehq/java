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

public class GetAssessmentVersionRequest {
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

    /**
     * The stored assessment_version, in canonical decimal form. An unavailable or malformed version
     * answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=version")
    private long version;

    @JsonCreator
    public GetAssessmentVersionRequest(
            @Nonnull String assessmentId, @Nullable ResponseMode responseMode, long version) {
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.responseMode = responseMode;
        this.version = version;
    }

    public GetAssessmentVersionRequest(@Nonnull String assessmentId, long version) {
        this(assessmentId, null, version);
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

    /**
     * The stored assessment_version, in canonical decimal form. An unavailable or malformed version
     * answers 404.
     */
    public long version() {
        return this.version;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public GetAssessmentVersionRequest withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * Optional standard or separately authorised detailed customer view; raw is rejected.
     */
    public GetAssessmentVersionRequest withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
        return this;
    }

    /**
     * The stored assessment_version, in canonical decimal form. An unavailable or malformed version
     * answers 404.
     */
    public GetAssessmentVersionRequest withVersion(long version) {
        this.version = version;
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
        GetAssessmentVersionRequest other = (GetAssessmentVersionRequest) o;
        return Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode)
                && Utils.enhancedDeepEquals(this.version, other.version);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(assessmentId, responseMode, version);
    }

    @Override
    public String toString() {
        return Utils.toString(
                GetAssessmentVersionRequest.class,
                "assessmentId",
                assessmentId,
                "responseMode",
                responseMode,
                "version",
                version);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

        private ResponseMode responseMode;

        private long version;

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

        /**
         * The stored assessment_version, in canonical decimal form. An unavailable or malformed version
         * answers 404.
         */
        public Builder version(long version) {
            this.version = version;
            return this;
        }

        public GetAssessmentVersionRequest build() {
            return new GetAssessmentVersionRequest(assessmentId, responseMode, version);
        }
    }
}
