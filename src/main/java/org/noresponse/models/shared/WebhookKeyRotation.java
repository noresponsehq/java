package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookKeyRotation
 *
 * <p>The 201 answer of POST /v1/webhooks/{endpoint_id}/rotate: the new signing secret, shown once, which
 * signs every delivery from now on, and when the key it replaced stops verifying. Until then a
 * receiver accepts both.
 */
public class WebhookKeyRotation {
    /**
     * Opaque id of the endpoint registration.
     */
    @JsonProperty("endpoint_id")
    private String endpointId;

    /**
     * A newly issued signing key with its secret, shown only in this answer. A receiver verifies
     * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
     */
    @JsonProperty("signing_secret")
    private SigningSecret signingSecret;

    /**
     * The key that signed until this rotation.
     */
    @JsonProperty("previous_key_id")
    private String previousKeyId;

    /**
     * When the previous key stops verifying: 7 days after the rotation.
     */
    @JsonProperty("previous_key_expires_at")
    private String previousKeyExpiresAt;

    @JsonCreator
    public WebhookKeyRotation(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("signing_secret") @Nonnull SigningSecret signingSecret,
            @JsonProperty("previous_key_id") @Nonnull String previousKeyId,
            @JsonProperty("previous_key_expires_at") @Nonnull String previousKeyExpiresAt) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.signingSecret = Optional.ofNullable(signingSecret)
                .orElseThrow(() -> new IllegalArgumentException("signingSecret cannot be null"));
        this.previousKeyId = Optional.ofNullable(previousKeyId)
                .orElseThrow(() -> new IllegalArgumentException("previousKeyId cannot be null"));
        this.previousKeyExpiresAt = Optional.ofNullable(previousKeyExpiresAt)
                .orElseThrow(() -> new IllegalArgumentException("previousKeyExpiresAt cannot be null"));
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * A newly issued signing key with its secret, shown only in this answer. A receiver verifies
     * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
     */
    public SigningSecret signingSecret() {
        return this.signingSecret;
    }

    /**
     * The key that signed until this rotation.
     */
    public String previousKeyId() {
        return this.previousKeyId;
    }

    /**
     * When the previous key stops verifying: 7 days after the rotation.
     */
    public String previousKeyExpiresAt() {
        return this.previousKeyExpiresAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public WebhookKeyRotation withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * A newly issued signing key with its secret, shown only in this answer. A receiver verifies
     * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
     */
    public WebhookKeyRotation withSigningSecret(@Nonnull SigningSecret signingSecret) {
        this.signingSecret = Utils.checkNotNull(signingSecret, "signingSecret");
        return this;
    }

    /**
     * The key that signed until this rotation.
     */
    public WebhookKeyRotation withPreviousKeyId(@Nonnull String previousKeyId) {
        this.previousKeyId = Utils.checkNotNull(previousKeyId, "previousKeyId");
        return this;
    }

    /**
     * When the previous key stops verifying: 7 days after the rotation.
     */
    public WebhookKeyRotation withPreviousKeyExpiresAt(@Nonnull String previousKeyExpiresAt) {
        this.previousKeyExpiresAt = Utils.checkNotNull(previousKeyExpiresAt, "previousKeyExpiresAt");
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
        WebhookKeyRotation other = (WebhookKeyRotation) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.signingSecret, other.signingSecret)
                && Utils.enhancedDeepEquals(this.previousKeyId, other.previousKeyId)
                && Utils.enhancedDeepEquals(this.previousKeyExpiresAt, other.previousKeyExpiresAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, signingSecret, previousKeyId, previousKeyExpiresAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookKeyRotation.class,
                "endpointId",
                endpointId,
                "signingSecret",
                signingSecret,
                "previousKeyId",
                previousKeyId,
                "previousKeyExpiresAt",
                previousKeyExpiresAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private SigningSecret signingSecret;

        private String previousKeyId;

        private String previousKeyExpiresAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque id of the endpoint registration.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
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

        /**
         * The key that signed until this rotation.
         */
        public Builder previousKeyId(@Nonnull String previousKeyId) {
            this.previousKeyId = Utils.checkNotNull(previousKeyId, "previousKeyId");
            return this;
        }

        /**
         * When the previous key stops verifying: 7 days after the rotation.
         */
        public Builder previousKeyExpiresAt(@Nonnull String previousKeyExpiresAt) {
            this.previousKeyExpiresAt = Utils.checkNotNull(previousKeyExpiresAt, "previousKeyExpiresAt");
            return this;
        }

        public WebhookKeyRotation build() {
            return new WebhookKeyRotation(endpointId, signingSecret, previousKeyId, previousKeyExpiresAt);
        }
    }
}
