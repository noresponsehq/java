package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookEndpointDisabled
 *
 * <p>One of the environment's endpoints stopped receiving events: every delivery attempt to it failed
 * from failing_since until disabled_at, 72 hours or more. Its queued deliveries are dropped. Fix the
 * receiver and verify it again with POST /v1/webhooks/{endpoint_id}/test (type endpoint.verification)
 * to resume delivery.
 */
public class WebhookEndpointDisabled {
    /**
     * The endpoint that was disabled.
     */
    @JsonProperty("endpoint_id")
    private String endpointId;

    /**
     * The first failed attempt of the unbroken failure streak.
     */
    @JsonProperty("failing_since")
    private String failingSince;

    /**
     * When the endpoint was disabled; equal to the event's created_at.
     */
    @JsonProperty("disabled_at")
    private String disabledAt;

    @JsonCreator
    public WebhookEndpointDisabled(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("failing_since") @Nonnull String failingSince,
            @JsonProperty("disabled_at") @Nonnull String disabledAt) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.failingSince = Optional.ofNullable(failingSince)
                .orElseThrow(() -> new IllegalArgumentException("failingSince cannot be null"));
        this.disabledAt = Optional.ofNullable(disabledAt)
                .orElseThrow(() -> new IllegalArgumentException("disabledAt cannot be null"));
    }

    /**
     * The endpoint that was disabled.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * The first failed attempt of the unbroken failure streak.
     */
    public String failingSince() {
        return this.failingSince;
    }

    /**
     * When the endpoint was disabled; equal to the event's created_at.
     */
    public String disabledAt() {
        return this.disabledAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The endpoint that was disabled.
     */
    public WebhookEndpointDisabled withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * The first failed attempt of the unbroken failure streak.
     */
    public WebhookEndpointDisabled withFailingSince(@Nonnull String failingSince) {
        this.failingSince = Utils.checkNotNull(failingSince, "failingSince");
        return this;
    }

    /**
     * When the endpoint was disabled; equal to the event's created_at.
     */
    public WebhookEndpointDisabled withDisabledAt(@Nonnull String disabledAt) {
        this.disabledAt = Utils.checkNotNull(disabledAt, "disabledAt");
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
        WebhookEndpointDisabled other = (WebhookEndpointDisabled) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.failingSince, other.failingSince)
                && Utils.enhancedDeepEquals(this.disabledAt, other.disabledAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, failingSince, disabledAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookEndpointDisabled.class,
                "endpointId",
                endpointId,
                "failingSince",
                failingSince,
                "disabledAt",
                disabledAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private String failingSince;

        private String disabledAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The endpoint that was disabled.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        /**
         * The first failed attempt of the unbroken failure streak.
         */
        public Builder failingSince(@Nonnull String failingSince) {
            this.failingSince = Utils.checkNotNull(failingSince, "failingSince");
            return this;
        }

        /**
         * When the endpoint was disabled; equal to the event's created_at.
         */
        public Builder disabledAt(@Nonnull String disabledAt) {
            this.disabledAt = Utils.checkNotNull(disabledAt, "disabledAt");
            return this;
        }

        public WebhookEndpointDisabled build() {
            return new WebhookEndpointDisabled(endpointId, failingSince, disabledAt);
        }
    }
}
