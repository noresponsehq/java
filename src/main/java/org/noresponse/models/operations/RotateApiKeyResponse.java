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
import org.noresponse.models.shared.KeyRotation;
import org.noresponse.utils.Response;
import org.noresponse.utils.Utils;

public class RotateApiKeyResponse implements Response {
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
     * The new key, shown once. Both keys authenticate until previous_key_expires_at; after it only the new
     * key does.
     */
    private KeyRotation keyRotation;

    private Map<String, List<String>> headers;

    @JsonCreator
    public RotateApiKeyResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<InputStream> rawResponse,
            @Nullable KeyRotation keyRotation,
            @Nonnull Map<String, List<String>> headers) {
        headers = Utils.emptyMapIfNull(headers);
        this.contentType = Optional.ofNullable(contentType)
                .orElseThrow(() -> new IllegalArgumentException("contentType cannot be null"));
        this.statusCode = statusCode;
        this.rawResponse = Optional.ofNullable(rawResponse)
                .orElseThrow(() -> new IllegalArgumentException("rawResponse cannot be null"));
        this.keyRotation = keyRotation;
        this.headers =
                Optional.ofNullable(headers).orElseThrow(() -> new IllegalArgumentException("headers cannot be null"));
    }

    public RotateApiKeyResponse(
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
     * The new key, shown once. Both keys authenticate until previous_key_expires_at; after it only the new
     * key does.
     */
    public Optional<KeyRotation> keyRotation() {
        return Optional.ofNullable(this.keyRotation);
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
    public RotateApiKeyResponse withContentType(@Nonnull String contentType) {
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public RotateApiKeyResponse withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public RotateApiKeyResponse withRawResponse(@Nonnull HttpResponse<InputStream> rawResponse) {
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        return this;
    }

    /**
     * The new key, shown once. Both keys authenticate until previous_key_expires_at; after it only the new
     * key does.
     */
    public RotateApiKeyResponse withKeyRotation(@Nullable KeyRotation keyRotation) {
        this.keyRotation = keyRotation;
        return this;
    }

    public RotateApiKeyResponse withHeaders(@Nonnull Map<String, List<String>> headers) {
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
        RotateApiKeyResponse other = (RotateApiKeyResponse) o;
        return Utils.enhancedDeepEquals(this.contentType, other.contentType)
                && Utils.enhancedDeepEquals(this.statusCode, other.statusCode)
                && Utils.enhancedDeepEquals(this.rawResponse, other.rawResponse)
                && Utils.enhancedDeepEquals(this.keyRotation, other.keyRotation)
                && Utils.enhancedDeepEquals(this.headers, other.headers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(contentType, statusCode, rawResponse, keyRotation, headers);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RotateApiKeyResponse.class,
                "contentType",
                contentType,
                "statusCode",
                statusCode,
                "rawResponse",
                rawResponse,
                "keyRotation",
                keyRotation,
                "headers",
                headers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String contentType;

        private int statusCode;

        private HttpResponse<InputStream> rawResponse;

        private KeyRotation keyRotation;

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
         * The new key, shown once. Both keys authenticate until previous_key_expires_at; after it only the new
         * key does.
         */
        public Builder keyRotation(@Nullable KeyRotation keyRotation) {
            this.keyRotation = keyRotation;
            return this;
        }

        public Builder headers(@Nonnull Map<String, List<String>> headers) {
            this.headers = Utils.checkNotNull(headers, "headers");
            return this;
        }

        public RotateApiKeyResponse build() {
            return new RotateApiKeyResponse(contentType, statusCode, rawResponse, keyRotation, headers);
        }
    }
}
