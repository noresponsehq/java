package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.WebhookKeyRotationRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RotateWebhookSigningKeyRequest {
    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=endpoint_id")
    private String endpointId;

    @NoResponseMetadata("request:mediaType=application/json")
    private WebhookKeyRotationRequest body;

    @JsonCreator
    public RotateWebhookSigningKeyRequest(@Nonnull String endpointId, @Nonnull WebhookKeyRotationRequest body) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public String endpointId() {
        return this.endpointId;
    }

    public WebhookKeyRotationRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public RotateWebhookSigningKeyRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    public RotateWebhookSigningKeyRequest withBody(@Nonnull WebhookKeyRotationRequest body) {
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
        RotateWebhookSigningKeyRequest other = (RotateWebhookSigningKeyRequest) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(RotateWebhookSigningKeyRequest.class, "endpointId", endpointId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private WebhookKeyRotationRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        public Builder body(@Nonnull WebhookKeyRotationRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public RotateWebhookSigningKeyRequest build() {
            return new RotateWebhookSigningKeyRequest(endpointId, body);
        }
    }
}
