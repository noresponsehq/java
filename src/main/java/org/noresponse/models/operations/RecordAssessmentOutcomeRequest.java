package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AssessmentOutcomeRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RecordAssessmentOutcomeRequest {
    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=assessment_id")
    private String assessmentId;

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    @NoResponseMetadata("header:style=simple,explode=false,name=Idempotency-Key")
    private String idempotencyKey;

    @NoResponseMetadata("request:mediaType=application/json")
    private AssessmentOutcomeRequest body;

    @JsonCreator
    public RecordAssessmentOutcomeRequest(
            @Nonnull String assessmentId, @Nonnull String idempotencyKey, @Nonnull AssessmentOutcomeRequest body) {
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.idempotencyKey = Optional.ofNullable(idempotencyKey)
                .orElseThrow(() -> new IllegalArgumentException("idempotencyKey cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    public String idempotencyKey() {
        return this.idempotencyKey;
    }

    public AssessmentOutcomeRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers
     * 404, not 400.
     */
    public RecordAssessmentOutcomeRequest withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    public RecordAssessmentOutcomeRequest withIdempotencyKey(@Nonnull String idempotencyKey) {
        this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
        return this;
    }

    public RecordAssessmentOutcomeRequest withBody(@Nonnull AssessmentOutcomeRequest body) {
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
        RecordAssessmentOutcomeRequest other = (RecordAssessmentOutcomeRequest) o;
        return Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(assessmentId, idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordAssessmentOutcomeRequest.class,
                "assessmentId",
                assessmentId,
                "idempotencyKey",
                idempotencyKey,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

        private String idempotencyKey;

        private AssessmentOutcomeRequest body;

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
         * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
         * workspace principal and bound to the canonical body for 24 hours.
         */
        public Builder idempotencyKey(@Nonnull String idempotencyKey) {
            this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
            return this;
        }

        public Builder body(@Nonnull AssessmentOutcomeRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public RecordAssessmentOutcomeRequest build() {
            return new RecordAssessmentOutcomeRequest(assessmentId, idempotencyKey, body);
        }
    }
}
