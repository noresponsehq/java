package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * SigningKey
 *
 * <p>One signing key, by id. The secret is not included.
 */
public class SigningKey {
    /**
     * The signing key id, sent as NR-Webhook-Key-Id on every delivery it signs.
     */
    @JsonProperty("key_id")
    private String keyId;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Present on a key a rotation replaced: it stops verifying at this instant, 7 days after the rotation.
     * Absent on the key that signs.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonCreator
    public SigningKey(
            @JsonProperty("key_id") @Nonnull String keyId,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("expires_at") @Nullable String expiresAt) {
        this.keyId = Optional.ofNullable(keyId).orElseThrow(() -> new IllegalArgumentException("keyId cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.expiresAt = expiresAt;
    }

    public SigningKey(@Nonnull String keyId, @Nonnull String createdAt) {
        this(keyId, createdAt, null);
    }

    /**
     * The signing key id, sent as NR-Webhook-Key-Id on every delivery it signs.
     */
    public String keyId() {
        return this.keyId;
    }

    public String createdAt() {
        return this.createdAt;
    }

    /**
     * Present on a key a rotation replaced: it stops verifying at this instant, 7 days after the rotation.
     * Absent on the key that signs.
     */
    public Optional<String> expiresAt() {
        return Optional.ofNullable(this.expiresAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The signing key id, sent as NR-Webhook-Key-Id on every delivery it signs.
     */
    public SigningKey withKeyId(@Nonnull String keyId) {
        this.keyId = Utils.checkNotNull(keyId, "keyId");
        return this;
    }

    public SigningKey withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * Present on a key a rotation replaced: it stops verifying at this instant, 7 days after the rotation.
     * Absent on the key that signs.
     */
    public SigningKey withExpiresAt(@Nullable String expiresAt) {
        this.expiresAt = expiresAt;
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
        SigningKey other = (SigningKey) o;
        return Utils.enhancedDeepEquals(this.keyId, other.keyId)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(keyId, createdAt, expiresAt);
    }

    @Override
    public String toString() {
        return Utils.toString(SigningKey.class, "keyId", keyId, "createdAt", createdAt, "expiresAt", expiresAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String keyId;

        private String createdAt;

        private String expiresAt;

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

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * Present on a key a rotation replaced: it stops verifying at this instant, 7 days after the rotation.
         * Absent on the key that signs.
         */
        public Builder expiresAt(@Nullable String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public SigningKey build() {
            return new SigningKey(keyId, createdAt, expiresAt);
        }
    }
}
