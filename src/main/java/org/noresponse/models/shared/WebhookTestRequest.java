package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookTestRequest
 *
 * <p>POST /v1/webhooks/{endpoint_id}/test body: which event to send.
 */
public class WebhookTestRequest {
    /**
     * endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
     * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
     * true, delivered and retried like any other; the endpoint must be verified.
     */
    @JsonProperty("event")
    private WebhookTestRequestEvent event;

    @JsonCreator
    public WebhookTestRequest(@JsonProperty("event") @Nonnull WebhookTestRequestEvent event) {
        this.event = Optional.ofNullable(event).orElseThrow(() -> new IllegalArgumentException("event cannot be null"));
    }

    /**
     * endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
     * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
     * true, delivered and retried like any other; the endpoint must be verified.
     */
    public WebhookTestRequestEvent event() {
        return this.event;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
     * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
     * true, delivered and retried like any other; the endpoint must be verified.
     */
    public WebhookTestRequest withEvent(@Nonnull WebhookTestRequestEvent event) {
        this.event = Utils.checkNotNull(event, "event");
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
        WebhookTestRequest other = (WebhookTestRequest) o;
        return Utils.enhancedDeepEquals(this.event, other.event);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(event);
    }

    @Override
    public String toString() {
        return Utils.toString(WebhookTestRequest.class, "event", event);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private WebhookTestRequestEvent event;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
         * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
         * true, delivered and retried like any other; the endpoint must be verified.
         */
        public Builder event(@Nonnull WebhookTestRequestEvent event) {
            this.event = Utils.checkNotNull(event, "event");
            return this;
        }

        public WebhookTestRequest build() {
            return new WebhookTestRequest(event);
        }
    }
}
