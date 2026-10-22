package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * SigningSecret
 *
 * <p>A newly issued signing key with its secret, shown only in this answer. A receiver verifies
 * NR-Webhook-Signature with the secret's UTF-8 bytes as the HMAC-SHA256 key.
 */
public class SigningSecret {
    /**
     * The signing key id, sent as NR-Webhook-Key-Id on every delivery it signs.
     */
    @JsonProperty("key_id")
    private String keyId;

    /**
     * The signing secret: 32 random bytes as 43 characters of unpadded base64url. It is not stored in
     * readable form or returned again.
     */
    @JsonProperty("secret")
    private String secret;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public SigningSecret(
            @JsonProperty("key_id") @Nonnull String keyId,
            @JsonProperty("secret") @Nonnull String secret,
            @JsonProperty("created_at") @Nonnull String createdAt) {
        this.keyId = Optional.ofNullable(keyId).orElseThrow(() -> new IllegalArgumentException("keyId cannot be null"));
        this.secret =
                Optional.ofNullable(secret).orElseThrow(() -> new IllegalArgumentException("secret cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
    }

    /**
     * The signing key id, sent as NR-Webhook-Key-Id on every delivery it signs.
     */
    public String keyId() {
        return this.keyId;
    }

    /**
     * The signing secret: 32 random bytes as 43 characters of unpadded base64url. It is not stored in
     * readable form or returned again.
     */
    public String secret() {
        return this.secret;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The signing key id, sent as NR-Webhook-Key-Id on every delivery it signs.
     */
    public SigningSecret withKeyId(@Nonnull String keyId) {
        this.keyId = Utils.checkNotNull(keyId, "keyId");
        return this;
    }

    /**
     * The signing secret: 32 random bytes as 43 characters of unpadded base64url. It is not stored in
     * readable form or returned again.
     */
    public SigningSecret withSecret(@Nonnull String secret) {
        this.secret = Utils.checkNotNull(secret, "secret");
        return this;
    }

    public SigningSecret withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
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
        SigningSecret other = (SigningSecret) o;
        return Utils.enhancedDeepEquals(this.keyId, other.keyId)
                && Utils.enhancedDeepEquals(this.secret, other.secret)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(keyId, secret, createdAt);
    }

    @Override
    public String toString() {
        return Utils.toString(SigningSecret.class, "keyId", keyId, "secret", secret, "createdAt", createdAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String keyId;

        private String secret;

        private String createdAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The signing key id, sent as NR-Webhook-Key-Id on every delivery it signs.
         */
        public Builder keyId(@Nonnull String keyId) {
            this.keyId = Utils.checkNotNull(keyId, "keyId");
            return this;
        }

        /**
         * The signing secret: 32 random bytes as 43 characters of unpadded base64url. It is not stored in
         * readable form or returned again.
         */
        public Builder secret(@Nonnull String secret) {
            this.secret = Utils.checkNotNull(secret, "secret");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public SigningSecret build() {
            return new SigningSecret(keyId, secret, createdAt);
        }
    }
}
