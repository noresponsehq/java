package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * KeyRotation
 *
 * <p>The 201 answer of POST /v1/keys/rotate: the new key, shown once, and when the key that was presented
 * stops working. The new key has the scopes and the environment of the presented key.
 */
public class KeyRotation {
    /**
     * The new credential, nrk_&lt;mode&gt;_&lt;key id&gt;_&lt;secret&gt;, in the environment of the
     * presented key: nrk_live_ or nrk_test_. It is shown only in this answer; store it now.
     */
    @JsonProperty("key")
    private String key;

    /**
     * The public id of the new key.
     */
    @JsonProperty("key_id")
    private String keyId;

    /**
     * The scopes of the new key: those of the presented key.
     */
    @JsonProperty("scopes")
    private List<Scope> scopes;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * The id of the key that signed this request.
     */
    @JsonProperty("previous_key_id")
    private String previousKeyId;

    /**
     * When the previous key stops authenticating. Until then both keys work.
     */
    @JsonProperty("previous_key_expires_at")
    private String previousKeyExpiresAt;

    @JsonCreator
    public KeyRotation(
            @JsonProperty("key") @Nonnull String key,
            @JsonProperty("key_id") @Nonnull String keyId,
            @JsonProperty("scopes") @Nonnull List<Scope> scopes,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("previous_key_id") @Nonnull String previousKeyId,
            @JsonProperty("previous_key_expires_at") @Nonnull String previousKeyExpiresAt) {
        this.key = Optional.ofNullable(key).orElseThrow(() -> new IllegalArgumentException("key cannot be null"));
        this.keyId = Optional.ofNullable(keyId).orElseThrow(() -> new IllegalArgumentException("keyId cannot be null"));
        this.scopes =
                Optional.ofNullable(scopes).orElseThrow(() -> new IllegalArgumentException("scopes cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.previousKeyId = Optional.ofNullable(previousKeyId)
                .orElseThrow(() -> new IllegalArgumentException("previousKeyId cannot be null"));
        this.previousKeyExpiresAt = Optional.ofNullable(previousKeyExpiresAt)
                .orElseThrow(() -> new IllegalArgumentException("previousKeyExpiresAt cannot be null"));
    }

    /**
     * The new credential, nrk_&lt;mode&gt;_&lt;key id&gt;_&lt;secret&gt;, in the environment of the
     * presented key: nrk_live_ or nrk_test_. It is shown only in this answer; store it now.
     */
    public String key() {
        return this.key;
    }

    /**
     * The public id of the new key.
     */
    public String keyId() {
        return this.keyId;
    }

    /**
     * The scopes of the new key: those of the presented key.
     */
    public List<Scope> scopes() {
        return this.scopes;
    }

    public String createdAt() {
        return this.createdAt;
    }

    /**
     * The id of the key that signed this request.
     */
    public String previousKeyId() {
        return this.previousKeyId;
    }

    /**
     * When the previous key stops authenticating. Until then both keys work.
     */
    public String previousKeyExpiresAt() {
        return this.previousKeyExpiresAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The new credential, nrk_&lt;mode&gt;_&lt;key id&gt;_&lt;secret&gt;, in the environment of the
     * presented key: nrk_live_ or nrk_test_. It is shown only in this answer; store it now.
     */
    public KeyRotation withKey(@Nonnull String key) {
        this.key = Utils.checkNotNull(key, "key");
        return this;
    }

    /**
     * The public id of the new key.
     */
    public KeyRotation withKeyId(@Nonnull String keyId) {
        this.keyId = Utils.checkNotNull(keyId, "keyId");
        return this;
    }

    /**
     * The scopes of the new key: those of the presented key.
     */
    public KeyRotation withScopes(@Nonnull List<Scope> scopes) {
        this.scopes = Utils.checkNotNull(scopes, "scopes");
        return this;
    }

    public KeyRotation withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * The id of the key that signed this request.
     */
    public KeyRotation withPreviousKeyId(@Nonnull String previousKeyId) {
        this.previousKeyId = Utils.checkNotNull(previousKeyId, "previousKeyId");
        return this;
    }

    /**
     * When the previous key stops authenticating. Until then both keys work.
     */
    public KeyRotation withPreviousKeyExpiresAt(@Nonnull String previousKeyExpiresAt) {
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
        KeyRotation other = (KeyRotation) o;
        return Utils.enhancedDeepEquals(this.key, other.key)
                && Utils.enhancedDeepEquals(this.keyId, other.keyId)
                && Utils.enhancedDeepEquals(this.scopes, other.scopes)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.previousKeyId, other.previousKeyId)
                && Utils.enhancedDeepEquals(this.previousKeyExpiresAt, other.previousKeyExpiresAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(key, keyId, scopes, createdAt, previousKeyId, previousKeyExpiresAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                KeyRotation.class,
                "key",
                key,
                "keyId",
                keyId,
                "scopes",
                scopes,
                "createdAt",
                createdAt,
                "previousKeyId",
                previousKeyId,
                "previousKeyExpiresAt",
                previousKeyExpiresAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String key;

        private String keyId;

        private List<Scope> scopes;

        private String createdAt;

        private String previousKeyId;

        private String previousKeyExpiresAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The new credential, nrk_&lt;mode&gt;_&lt;key id&gt;_&lt;secret&gt;, in the environment of the
         * presented key: nrk_live_ or nrk_test_. It is shown only in this answer; store it now.
         */
        public Builder key(@Nonnull String key) {
            this.key = Utils.checkNotNull(key, "key");
            return this;
        }

        /**
         * The public id of the new key.
         */
        public Builder keyId(@Nonnull String keyId) {
            this.keyId = Utils.checkNotNull(keyId, "keyId");
            return this;
        }

        /**
         * The scopes of the new key: those of the presented key.
         */
        public Builder scopes(@Nonnull List<Scope> scopes) {
            this.scopes = Utils.checkNotNull(scopes, "scopes");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * The id of the key that signed this request.
         */
        public Builder previousKeyId(@Nonnull String previousKeyId) {
            this.previousKeyId = Utils.checkNotNull(previousKeyId, "previousKeyId");
            return this;
        }

        /**
         * When the previous key stops authenticating. Until then both keys work.
         */
        public Builder previousKeyExpiresAt(@Nonnull String previousKeyExpiresAt) {
            this.previousKeyExpiresAt = Utils.checkNotNull(previousKeyExpiresAt, "previousKeyExpiresAt");
            return this;
        }

        public KeyRotation build() {
            return new KeyRotation(key, keyId, scopes, createdAt, previousKeyId, previousKeyExpiresAt);
        }
    }
}
