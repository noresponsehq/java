package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookEndpointCreated
 *
 * <p>The 201 answer of POST /v1/webhooks: the new endpoint, unverified, and its first signing secret,
 * shown once. Store the secret where the receiver reads it, then send endpoint.verification with POST
 * /v1/webhooks/{endpoint_id}/test.
 */
public class WebhookEndpointCreated {
    /**
     * One of the tenant's registered webhook endpoints. Signing secrets are not part of it: a secret is
     * shown once, when it is issued (POST /v1/webhooks and POST /v1/webhooks/{endpoint_id}/rotate).
     */
    @JsonProperty("endpoint")
    private WebhookEndpoint endpoint;

    /**
     * A newly issued signing key with its secret, shown only in this answer. A receiver verifies
     * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
     */
    @JsonProperty("signing_secret")
    private SigningSecret signingSecret;

    @JsonCreator
    public WebhookEndpointCreated(
            @JsonProperty("endpoint") @Nonnull WebhookEndpoint endpoint,
            @JsonProperty("signing_secret") @Nonnull SigningSecret signingSecret) {
        this.endpoint = Optional.ofNullable(endpoint)
                .orElseThrow(() -> new IllegalArgumentException("endpoint cannot be null"));
        this.signingSecret = Optional.ofNullable(signingSecret)
                .orElseThrow(() -> new IllegalArgumentException("signingSecret cannot be null"));
    }

    /**
     * One of the tenant's registered webhook endpoints. Signing secrets are not part of it: a secret is
     * shown once, when it is issued (POST /v1/webhooks and POST /v1/webhooks/{endpoint_id}/rotate).
     */
    public WebhookEndpoint endpoint() {
        return this.endpoint;
    }

    /**
     * A newly issued signing key with its secret, shown only in this answer. A receiver verifies
     * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
     */
    public SigningSecret signingSecret() {
        return this.signingSecret;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One of the tenant's registered webhook endpoints. Signing secrets are not part of it: a secret is
     * shown once, when it is issued (POST /v1/webhooks and POST /v1/webhooks/{endpoint_id}/rotate).
     */
    public WebhookEndpointCreated withEndpoint(@Nonnull WebhookEndpoint endpoint) {
        this.endpoint = Utils.checkNotNull(endpoint, "endpoint");
        return this;
    }

    /**
     * A newly issued signing key with its secret, shown only in this answer. A receiver verifies
     * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
     */
    public WebhookEndpointCreated withSigningSecret(@Nonnull SigningSecret signingSecret) {
        this.signingSecret = Utils.checkNotNull(signingSecret, "signingSecret");
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
        WebhookEndpointCreated other = (WebhookEndpointCreated) o;
        return Utils.enhancedDeepEquals(this.endpoint, other.endpoint)
                && Utils.enhancedDeepEquals(this.signingSecret, other.signingSecret);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpoint, signingSecret);
    }

    @Override
    public String toString() {
        return Utils.toString(WebhookEndpointCreated.class, "endpoint", endpoint, "signingSecret", signingSecret);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private WebhookEndpoint endpoint;

        private SigningSecret signingSecret;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * One of the tenant's registered webhook endpoints. Signing secrets are not part of it: a secret is
         * shown once, when it is issued (POST /v1/webhooks and POST /v1/webhooks/{endpoint_id}/rotate).
         */
        public Builder endpoint(@Nonnull WebhookEndpoint endpoint) {
            this.endpoint = Utils.checkNotNull(endpoint, "endpoint");
            return this;
        }

        /**
         * A newly issued signing key with its secret, shown only in this answer. A receiver verifies
         * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
         */
        public Builder signingSecret(@Nonnull SigningSecret signingSecret) {
            this.signingSecret = Utils.checkNotNull(signingSecret, "signingSecret");
            return this;
        }

        public WebhookEndpointCreated build() {
            return new WebhookEndpointCreated(endpoint, signingSecret);
        }
    }
}
