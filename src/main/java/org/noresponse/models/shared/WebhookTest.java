package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookTest
 *
 * <p>The 202 answer of POST /v1/webhooks/{endpoint_id}/test: the event that was sent or queued.
 */
public class WebhookTest {
    /**
     * The event id the receiver sees as NR-Webhook-Id and as the body's id.
     */
    @JsonProperty("delivery_id")
    private String deliveryId;

    /**
     * Opaque id of the endpoint registration.
     */
    @JsonProperty("endpoint_id")
    private String endpointId;

    /**
     * endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
     * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
     * true, delivered and retried like any other; the endpoint must be verified.
     */
    @JsonProperty("event")
    private WebhookTestRequestEvent event;

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    @JsonProperty("state")
    private WebhookEndpointState state;

    /**
     * endpoint.verification only: true when the endpoint echoed the challenge within 5 seconds. A false
     * answer leaves the endpoint's state as it was.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("verified")
    private Boolean verified;

    @JsonCreator
    public WebhookTest(
            @JsonProperty("delivery_id") @Nonnull String deliveryId,
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("event") @Nonnull WebhookTestRequestEvent event,
            @JsonProperty("state") @Nonnull WebhookEndpointState state,
            @JsonProperty("verified") @Nullable Boolean verified) {
        this.deliveryId = Optional.ofNullable(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("deliveryId cannot be null"));
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.event = Optional.ofNullable(event).orElseThrow(() -> new IllegalArgumentException("event cannot be null"));
        this.state = Optional.ofNullable(state).orElseThrow(() -> new IllegalArgumentException("state cannot be null"));
        this.verified = verified;
    }

    public WebhookTest(
            @Nonnull String deliveryId,
            @Nonnull String endpointId,
            @Nonnull WebhookTestRequestEvent event,
            @Nonnull WebhookEndpointState state) {
        this(deliveryId, endpointId, event, state, null);
    }

    /**
     * The event id the receiver sees as NR-Webhook-Id and as the body's id.
     */
    public String deliveryId() {
        return this.deliveryId;
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
     * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
     * true, delivered and retried like any other; the endpoint must be verified.
     */
    public WebhookTestRequestEvent event() {
        return this.event;
    }

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    public WebhookEndpointState state() {
        return this.state;
    }

    /**
     * endpoint.verification only: true when the endpoint echoed the challenge within 5 seconds. A false
     * answer leaves the endpoint's state as it was.
     */
    public Optional<Boolean> verified() {
        return Optional.ofNullable(this.verified);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The event id the receiver sees as NR-Webhook-Id and as the body's id.
     */
    public WebhookTest withDeliveryId(@Nonnull String deliveryId) {
        this.deliveryId = Utils.checkNotNull(deliveryId, "deliveryId");
        return this;
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public WebhookTest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
     * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
     * true, delivered and retried like any other; the endpoint must be verified.
     */
    public WebhookTest withEvent(@Nonnull WebhookTestRequestEvent event) {
        this.event = Utils.checkNotNull(event, "event");
        return this;
    }

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    public WebhookTest withState(@Nonnull WebhookEndpointState state) {
        this.state = Utils.checkNotNull(state, "state");
        return this;
    }

    /**
     * endpoint.verification only: true when the endpoint echoed the challenge within 5 seconds. A false
     * answer leaves the endpoint's state as it was.
     */
    public WebhookTest withVerified(@Nullable Boolean verified) {
        this.verified = verified;
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
        WebhookTest other = (WebhookTest) o;
        return Utils.enhancedDeepEquals(this.deliveryId, other.deliveryId)
                && Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.event, other.event)
                && Utils.enhancedDeepEquals(this.state, other.state)
                && Utils.enhancedDeepEquals(this.verified, other.verified);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(deliveryId, endpointId, event, state, verified);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookTest.class,
                "deliveryId",
                deliveryId,
                "endpointId",
                endpointId,
                "event",
                event,
                "state",
                state,
                "verified",
                verified);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String deliveryId;

        private String endpointId;

        private WebhookTestRequestEvent event;

        private WebhookEndpointState state;

        private Boolean verified;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The event id the receiver sees as NR-Webhook-Id and as the body's id.
         */
        public Builder deliveryId(@Nonnull String deliveryId) {
            this.deliveryId = Utils.checkNotNull(deliveryId, "deliveryId");
            return this;
        }

        /**
         * Opaque id of the endpoint registration.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
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

        /**
         * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
         * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
         * hours of continuous delivery failure, and verified again by the same challenge.
         */
        public Builder state(@Nonnull WebhookEndpointState state) {
            this.state = Utils.checkNotNull(state, "state");
            return this;
        }

        /**
         * endpoint.verification only: true when the endpoint echoed the challenge within 5 seconds. A false
         * answer leaves the endpoint's state as it was.
         */
        public Builder verified(@Nullable Boolean verified) {
            this.verified = verified;
            return this;
        }

        public WebhookTest build() {
            return new WebhookTest(deliveryId, endpointId, event, state, verified);
        }
    }
}
