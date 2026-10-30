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
import org.noresponse.models.shared.LinkedAccountPage;
import org.noresponse.utils.AsyncResponse;
import org.noresponse.utils.Blob;
import org.noresponse.utils.Utils;

public class ListLinkedAccountsResponse implements AsyncResponse {
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
     * Accounts that share a submitted identifier with the requested account
     */
    private LinkedAccountPage linkedAccountPage;

    private Map<String, List<String>> headers;

    @JsonCreator
    public ListLinkedAccountsResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<Blob> rawResponse,
            @Nullable LinkedAccountPage linkedAccountPage,
            @Nonnull Map<String, List<String>> headers) {
        headers = Utils.emptyMapIfNull(headers);
        this.contentType = Optional.ofNullable(contentType)
                .orElseThrow(() -> new IllegalArgumentException("contentType cannot be null"));
        this.statusCode = statusCode;
        this.rawResponse = Optional.ofNullable(rawResponse)
                .orElseThrow(() -> new IllegalArgumentException("rawResponse cannot be null"));
        this.linkedAccountPage = linkedAccountPage;
        this.headers =
                Optional.ofNullable(headers).orElseThrow(() -> new IllegalArgumentException("headers cannot be null"));
    }

    public ListLinkedAccountsResponse(
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
     * Accounts that share a submitted identifier with the requested account
     */
    public Optional<LinkedAccountPage> linkedAccountPage() {
        return Optional.ofNullable(this.linkedAccountPage);
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
    public ListLinkedAccountsResponse withContentType(@Nonnull String contentType) {
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public ListLinkedAccountsResponse withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public ListLinkedAccountsResponse withRawResponse(@Nonnull HttpResponse<Blob> rawResponse) {
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        return this;
    }

    /**
     * Accounts that share a submitted identifier with the requested account
     */
    public ListLinkedAccountsResponse withLinkedAccountPage(@Nullable LinkedAccountPage linkedAccountPage) {
        this.linkedAccountPage = linkedAccountPage;
        return this;
    }

    public ListLinkedAccountsResponse withHeaders(@Nonnull Map<String, List<String>> headers) {
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
        ListLinkedAccountsResponse other = (ListLinkedAccountsResponse) o;
        return Utils.enhancedDeepEquals(this.contentType, other.contentType)
                && Utils.enhancedDeepEquals(this.statusCode, other.statusCode)
                && Utils.enhancedDeepEquals(this.rawResponse, other.rawResponse)
                && Utils.enhancedDeepEquals(this.linkedAccountPage, other.linkedAccountPage)
                && Utils.enhancedDeepEquals(this.headers, other.headers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(contentType, statusCode, rawResponse, linkedAccountPage, headers);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListLinkedAccountsResponse.class,
                "contentType",
                contentType,
                "statusCode",
                statusCode,
                "rawResponse",
                rawResponse,
                "linkedAccountPage",
                linkedAccountPage,
                "headers",
                headers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String contentType;

        private int statusCode;

        private HttpResponse<Blob> rawResponse;

        private LinkedAccountPage linkedAccountPage;

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
         * Accounts that share a submitted identifier with the requested account
         */
        public Builder linkedAccountPage(@Nullable LinkedAccountPage linkedAccountPage) {
            this.linkedAccountPage = linkedAccountPage;
            return this;
        }

        public Builder headers(@Nonnull Map<String, List<String>> headers) {
            this.headers = Utils.checkNotNull(headers, "headers");
            return this;
        }

        public ListLinkedAccountsResponse build() {
            return new ListLinkedAccountsResponse(contentType, statusCode, rawResponse, linkedAccountPage, headers);
        }
    }
}
