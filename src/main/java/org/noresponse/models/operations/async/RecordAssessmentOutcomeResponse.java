package org.noresponse.models.operations.async;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Override;
import java.lang.String;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.noresponse.models.shared.AssessmentOutcome;
import org.noresponse.utils.AsyncResponse;
import org.noresponse.utils.Blob;
import org.noresponse.utils.Utils;

public class RecordAssessmentOutcomeResponse implements AsyncResponse {
    /**
     * HTTP response content type for this operation
     */
    private String contentType;

    /**
     * HTTP response status code for this operation
     */
    private int statusCode;

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    private HttpResponse<Blob> rawResponse;

    /**
     * The outcome was recorded. A replay of the same Idempotency-Key and body answers the record the first
     * request created, with Idempotent-Replayed: true. Nothing is polled afterwards.
     */
    private AssessmentOutcome assessmentOutcome;

    private Map<String, List<String>> headers;

    @JsonCreator
    public RecordAssessmentOutcomeResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<Blob> rawResponse,
            @Nullable AssessmentOutcome assessmentOutcome,
            @Nonnull Map<String, List<String>> headers) {
        headers = Utils.emptyMapIfNull(headers);
        this.contentType = Optional.ofNullable(contentType)
                .orElseThrow(() -> new IllegalArgumentException("contentType cannot be null"));
        this.statusCode = statusCode;
        this.rawResponse = Optional.ofNullable(rawResponse)
                .orElseThrow(() -> new IllegalArgumentException("rawResponse cannot be null"));
        this.assessmentOutcome = assessmentOutcome;
        this.headers =
                Optional.ofNullable(headers).orElseThrow(() -> new IllegalArgumentException("headers cannot be null"));
    }

    public RecordAssessmentOutcomeResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<Blob> rawResponse,
            @Nonnull Map<String, List<String>> headers) {
        this(contentType, statusCode, rawResponse, null, headers);
    }

    /**
     * HTTP response content type for this operation
     */
    public String contentType() {
        return this.contentType;
    }

    /**
     * HTTP response status code for this operation
     */
    public int statusCode() {
        return this.statusCode;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public HttpResponse<Blob> rawResponse() {
        return this.rawResponse;
    }

    /**
     * The outcome was recorded. A replay of the same Idempotency-Key and body answers the record the first
     * request created, with Idempotent-Replayed: true. Nothing is polled afterwards.
     */
    public Optional<AssessmentOutcome> assessmentOutcome() {
        return Optional.ofNullable(this.assessmentOutcome);
    }

    public Map<String, List<String>> headers() {
        return this.headers;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * HTTP response content type for this operation
     */
    public RecordAssessmentOutcomeResponse withContentType(@Nonnull String contentType) {
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public RecordAssessmentOutcomeResponse withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public RecordAssessmentOutcomeResponse withRawResponse(@Nonnull HttpResponse<Blob> rawResponse) {
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        return this;
    }

    /**
     * The outcome was recorded. A replay of the same Idempotency-Key and body answers the record the first
     * request created, with Idempotent-Replayed: true. Nothing is polled afterwards.
     */
    public RecordAssessmentOutcomeResponse withAssessmentOutcome(@Nullable AssessmentOutcome assessmentOutcome) {
        this.assessmentOutcome = assessmentOutcome;
        return this;
    }

    public RecordAssessmentOutcomeResponse withHeaders(@Nonnull Map<String, List<String>> headers) {
        this.headers = Utils.checkNotNull(headers, "headers");
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
        RecordAssessmentOutcomeResponse other = (RecordAssessmentOutcomeResponse) o;
        return Utils.enhancedDeepEquals(this.contentType, other.contentType)
                && Utils.enhancedDeepEquals(this.statusCode, other.statusCode)
                && Utils.enhancedDeepEquals(this.rawResponse, other.rawResponse)
                && Utils.enhancedDeepEquals(this.assessmentOutcome, other.assessmentOutcome)
                && Utils.enhancedDeepEquals(this.headers, other.headers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(contentType, statusCode, rawResponse, assessmentOutcome, headers);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordAssessmentOutcomeResponse.class,
                "contentType",
                contentType,
                "statusCode",
                statusCode,
                "rawResponse",
                rawResponse,
                "assessmentOutcome",
                assessmentOutcome,
                "headers",
                headers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String contentType;

        private int statusCode;

        private HttpResponse<Blob> rawResponse;

        private AssessmentOutcome assessmentOutcome;

        private Map<String, List<String>> headers;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * HTTP response content type for this operation
         */
        public Builder contentType(@Nonnull String contentType) {
            this.contentType = Utils.checkNotNull(contentType, "contentType");
            return this;
        }

        /**
         * HTTP response status code for this operation
         */
        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Raw HTTP response; suitable for custom response parsing
         */
        public Builder rawResponse(@Nonnull HttpResponse<Blob> rawResponse) {
            this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
            return this;
        }

        /**
         * The outcome was recorded. A replay of the same Idempotency-Key and body answers the record the first
         * request created, with Idempotent-Replayed: true. Nothing is polled afterwards.
         */
        public Builder assessmentOutcome(@Nullable AssessmentOutcome assessmentOutcome) {
            this.assessmentOutcome = assessmentOutcome;
            return this;
        }

        public Builder headers(@Nonnull Map<String, List<String>> headers) {
            this.headers = Utils.checkNotNull(headers, "headers");
            return this;
        }

        public RecordAssessmentOutcomeResponse build() {
            return new RecordAssessmentOutcomeResponse(
                    contentType, statusCode, rawResponse, assessmentOutcome, headers);
        }
    }
}
