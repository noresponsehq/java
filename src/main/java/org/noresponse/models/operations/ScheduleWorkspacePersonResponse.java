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
import org.noresponse.models.shared.PersonSchedule;
import org.noresponse.utils.Response;
import org.noresponse.utils.Utils;

public class ScheduleWorkspacePersonResponse implements Response {
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
     * The person's schedule as set.
     */
    private PersonSchedule personSchedule;

    private Map<String, List<String>> headers;

    @JsonCreator
    public ScheduleWorkspacePersonResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<InputStream> rawResponse,
            @Nullable PersonSchedule personSchedule,
            @Nonnull Map<String, List<String>> headers) {
        headers = Utils.emptyMapIfNull(headers);
        this.contentType = Optional.ofNullable(contentType)
                .orElseThrow(() -> new IllegalArgumentException("contentType cannot be null"));
        this.statusCode = statusCode;
        this.rawResponse = Optional.ofNullable(rawResponse)
                .orElseThrow(() -> new IllegalArgumentException("rawResponse cannot be null"));
        this.personSchedule = personSchedule;
        this.headers =
                Optional.ofNullable(headers).orElseThrow(() -> new IllegalArgumentException("headers cannot be null"));
    }

    public ScheduleWorkspacePersonResponse(
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
     * The person's schedule as set.
     */
    public Optional<PersonSchedule> personSchedule() {
        return Optional.ofNullable(this.personSchedule);
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
    public ScheduleWorkspacePersonResponse withContentType(@Nonnull String contentType) {
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public ScheduleWorkspacePersonResponse withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public ScheduleWorkspacePersonResponse withRawResponse(@Nonnull HttpResponse<InputStream> rawResponse) {
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        return this;
    }

    /**
     * The person's schedule as set.
     */
    public ScheduleWorkspacePersonResponse withPersonSchedule(@Nullable PersonSchedule personSchedule) {
        this.personSchedule = personSchedule;
        return this;
    }

    public ScheduleWorkspacePersonResponse withHeaders(@Nonnull Map<String, List<String>> headers) {
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
        ScheduleWorkspacePersonResponse other = (ScheduleWorkspacePersonResponse) o;
        return Utils.enhancedDeepEquals(this.contentType, other.contentType)
                && Utils.enhancedDeepEquals(this.statusCode, other.statusCode)
                && Utils.enhancedDeepEquals(this.rawResponse, other.rawResponse)
                && Utils.enhancedDeepEquals(this.personSchedule, other.personSchedule)
                && Utils.enhancedDeepEquals(this.headers, other.headers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(contentType, statusCode, rawResponse, personSchedule, headers);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScheduleWorkspacePersonResponse.class,
                "contentType",
                contentType,
                "statusCode",
                statusCode,
                "rawResponse",
                rawResponse,
                "personSchedule",
                personSchedule,
                "headers",
                headers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String contentType;

        private int statusCode;

        private HttpResponse<InputStream> rawResponse;

        private PersonSchedule personSchedule;

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
         * The person's schedule as set.
         */
        public Builder personSchedule(@Nullable PersonSchedule personSchedule) {
            this.personSchedule = personSchedule;
            return this;
        }

        public Builder headers(@Nonnull Map<String, List<String>> headers) {
            this.headers = Utils.checkNotNull(headers, "headers");
            return this;
        }

        public ScheduleWorkspacePersonResponse build() {
            return new ScheduleWorkspacePersonResponse(contentType, statusCode, rawResponse, personSchedule, headers);
        }
    }
}
