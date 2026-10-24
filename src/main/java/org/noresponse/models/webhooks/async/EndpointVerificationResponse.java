package org.noresponse.models.webhooks.async;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Override;
import java.lang.String;
import java.net.http.HttpResponse;
import java.util.Optional;
import org.noresponse.models.shared.WebhookVerificationResponse;
import org.noresponse.utils.AsyncResponse;
import org.noresponse.utils.Blob;
import org.noresponse.utils.Utils;

public class EndpointVerificationResponse implements AsyncResponse {
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
     * The challenge, echoed. Required for verification.
     */
    private WebhookVerificationResponse webhookVerificationResponse;

    @JsonCreator
    public EndpointVerificationResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<Blob> rawResponse,
            @Nullable WebhookVerificationResponse webhookVerificationResponse) {
        this.contentType = Optional.ofNullable(contentType)
                .orElseThrow(() -> new IllegalArgumentException("contentType cannot be null"));
        this.statusCode = statusCode;
        this.rawResponse = Optional.ofNullable(rawResponse)
                .orElseThrow(() -> new IllegalArgumentException("rawResponse cannot be null"));
        this.webhookVerificationResponse = webhookVerificationResponse;
    }

    public EndpointVerificationResponse(
            @Nonnull String contentType, int statusCode, @Nonnull HttpResponse<Blob> rawResponse) {
        this(contentType, statusCode, rawResponse, null);
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
     * The challenge, echoed. Required for verification.
     */
    public Optional<WebhookVerificationResponse> webhookVerificationResponse() {
        return Optional.ofNullable(this.webhookVerificationResponse);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * HTTP response content type for this operation
     */
    public EndpointVerificationResponse withContentType(@Nonnull String contentType) {
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public EndpointVerificationResponse withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public EndpointVerificationResponse withRawResponse(@Nonnull HttpResponse<Blob> rawResponse) {
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        return this;
    }

    /**
     * The challenge, echoed. Required for verification.
     */
    public EndpointVerificationResponse withWebhookVerificationResponse(
            @Nullable WebhookVerificationResponse webhookVerificationResponse) {
        this.webhookVerificationResponse = webhookVerificationResponse;
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
        EndpointVerificationResponse other = (EndpointVerificationResponse) o;
        return Utils.enhancedDeepEquals(this.contentType, other.contentType)
                && Utils.enhancedDeepEquals(this.statusCode, other.statusCode)
                && Utils.enhancedDeepEquals(this.rawResponse, other.rawResponse)
                && Utils.enhancedDeepEquals(this.webhookVerificationResponse, other.webhookVerificationResponse);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(contentType, statusCode, rawResponse, webhookVerificationResponse);
    }

    @Override
    public String toString() {
        return Utils.toString(
                EndpointVerificationResponse.class,
                "contentType",
                contentType,
                "statusCode",
                statusCode,
                "rawResponse",
                rawResponse,
                "webhookVerificationResponse",
                webhookVerificationResponse);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String contentType;

        private int statusCode;

        private HttpResponse<Blob> rawResponse;

        private WebhookVerificationResponse webhookVerificationResponse;

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
         * The challenge, echoed. Required for verification.
         */
        public Builder webhookVerificationResponse(@Nullable WebhookVerificationResponse webhookVerificationResponse) {
            this.webhookVerificationResponse = webhookVerificationResponse;
            return this;
        }

        public EndpointVerificationResponse build() {
            return new EndpointVerificationResponse(contentType, statusCode, rawResponse, webhookVerificationResponse);
        }
    }
}
