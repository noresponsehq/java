package org.noresponse.models.operations;

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
import org.noresponse.models.shared.AssessmentAggregate;
import org.noresponse.utils.Response;
import org.noresponse.utils.Utils;

public class AggregateAssessmentsResponse implements Response {
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
    private HttpResponse<InputStream> rawResponse;

    /**
     * Counts of first answers per bucket.
     */
    private AssessmentAggregate assessmentAggregate;

    private Map<String, List<String>> headers;

    @JsonCreator
    public AggregateAssessmentsResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<InputStream> rawResponse,
            @Nullable AssessmentAggregate assessmentAggregate,
            @Nonnull Map<String, List<String>> headers) {
        headers = Utils.emptyMapIfNull(headers);
        this.contentType = Optional.ofNullable(contentType)
                .orElseThrow(() -> new IllegalArgumentException("contentType cannot be null"));
        this.statusCode = statusCode;
        this.rawResponse = Optional.ofNullable(rawResponse)
                .orElseThrow(() -> new IllegalArgumentException("rawResponse cannot be null"));
        this.assessmentAggregate = assessmentAggregate;
        this.headers =
                Optional.ofNullable(headers).orElseThrow(() -> new IllegalArgumentException("headers cannot be null"));
    }

    public AggregateAssessmentsResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<InputStream> rawResponse,
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
    public HttpResponse<InputStream> rawResponse() {
        return this.rawResponse;
    }

    /**
     * Counts of first answers per bucket.
     */
    public Optional<AssessmentAggregate> assessmentAggregate() {
        return Optional.ofNullable(this.assessmentAggregate);
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
    public AggregateAssessmentsResponse withContentType(@Nonnull String contentType) {
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public AggregateAssessmentsResponse withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public AggregateAssessmentsResponse withRawResponse(@Nonnull HttpResponse<InputStream> rawResponse) {
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        return this;
    }

    /**
     * Counts of first answers per bucket.
     */
    public AggregateAssessmentsResponse withAssessmentAggregate(@Nullable AssessmentAggregate assessmentAggregate) {
        this.assessmentAggregate = assessmentAggregate;
        return this;
    }

    public AggregateAssessmentsResponse withHeaders(@Nonnull Map<String, List<String>> headers) {
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
        AggregateAssessmentsResponse other = (AggregateAssessmentsResponse) o;
        return Utils.enhancedDeepEquals(this.contentType, other.contentType)
                && Utils.enhancedDeepEquals(this.statusCode, other.statusCode)
                && Utils.enhancedDeepEquals(this.rawResponse, other.rawResponse)
                && Utils.enhancedDeepEquals(this.assessmentAggregate, other.assessmentAggregate)
                && Utils.enhancedDeepEquals(this.headers, other.headers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(contentType, statusCode, rawResponse, assessmentAggregate, headers);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AggregateAssessmentsResponse.class,
                "contentType",
                contentType,
                "statusCode",
                statusCode,
                "rawResponse",
                rawResponse,
                "assessmentAggregate",
                assessmentAggregate,
                "headers",
                headers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String contentType;

        private int statusCode;

        private HttpResponse<InputStream> rawResponse;

        private AssessmentAggregate assessmentAggregate;

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
        public Builder rawResponse(@Nonnull HttpResponse<InputStream> rawResponse) {
            this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
            return this;
        }

        /**
         * Counts of first answers per bucket.
         */
        public Builder assessmentAggregate(@Nullable AssessmentAggregate assessmentAggregate) {
            this.assessmentAggregate = assessmentAggregate;
            return this;
        }

        public Builder headers(@Nonnull Map<String, List<String>> headers) {
            this.headers = Utils.checkNotNull(headers, "headers");
            return this;
        }

        public AggregateAssessmentsResponse build() {
            return new AggregateAssessmentsResponse(contentType, statusCode, rawResponse, assessmentAggregate, headers);
        }
    }
}
