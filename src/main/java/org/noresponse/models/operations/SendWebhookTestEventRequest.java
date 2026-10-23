package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.WebhookTestRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class SendWebhookTestEventRequest {
    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=endpoint_id")
    private String endpointId;

    @NoResponseMetadata("request:mediaType=application/json")
    private WebhookTestRequest body;

    @JsonCreator
    public SendWebhookTestEventRequest(@Nonnull String endpointId, @Nonnull WebhookTestRequest body) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     */
    public String endpointId() {
        return this.endpointId;
    }

    public WebhookTestRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
     */
    public SendWebhookTestEventRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    public SendWebhookTestEventRequest withBody(@Nonnull WebhookTestRequest body) {
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
        SendWebhookTestEventRequest other = (SendWebhookTestEventRequest) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(SendWebhookTestEventRequest.class, "endpointId", endpointId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private WebhookTestRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The endpoint's whe_ id. An id that is unknown, malformed or another workspace's answers 404.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        public Builder body(@Nonnull WebhookTestRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public SendWebhookTestEventRequest build() {
            return new SendWebhookTestEventRequest(endpointId, body);
        }
    }
}
