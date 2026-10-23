package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * KeyUsageBatch
 *
 * <p>The 202 answer of POST /v1/key_usage/batches. A replay with the same Idempotency-Key and body
 * answers the same record and counts nothing again.
 */
public class KeyUsageBatch {
    /**
     * Opaque, server-assigned id of the batch: 'kub_' and a 26-character Crockford base32 ULID.
     */
    @JsonProperty("id")
    private String id;

    /**
     * When NoResponse first recorded the batch.
     */
    @JsonProperty("received_at")
    private String receivedAt;

    /**
     * Events counted.
     */
    @JsonProperty("accepted")
    private long accepted;

    /**
     * Events that added nothing: a private, reserved or unreadable ip, or an occurred_at more than 24
     * hours old or more than 5 minutes ahead.
     */
    @JsonProperty("ignored")
    private long ignored;

    @JsonCreator
    public KeyUsageBatch(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("received_at") @Nonnull String receivedAt,
            @JsonProperty("accepted") long accepted,
            @JsonProperty("ignored") long ignored) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.receivedAt = Optional.ofNullable(receivedAt)
                .orElseThrow(() -> new IllegalArgumentException("receivedAt cannot be null"));
        this.accepted = accepted;
        this.ignored = ignored;
    }

    /**
     * Opaque, server-assigned id of the batch: 'kub_' and a 26-character Crockford base32 ULID.
     */
    public String id() {
        return this.id;
    }

    /**
     * When NoResponse first recorded the batch.
     */
    public String receivedAt() {
        return this.receivedAt;
    }

    /**
     * Events counted.
     */
    public long accepted() {
        return this.accepted;
    }

    /**
     * Events that added nothing: a private, reserved or unreadable ip, or an occurred_at more than 24
     * hours old or more than 5 minutes ahead.
     */
    public long ignored() {
        return this.ignored;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned id of the batch: 'kub_' and a 26-character Crockford base32 ULID.
     */
    public KeyUsageBatch withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * When NoResponse first recorded the batch.
     */
    public KeyUsageBatch withReceivedAt(@Nonnull String receivedAt) {
        this.receivedAt = Utils.checkNotNull(receivedAt, "receivedAt");
        return this;
    }

    /**
     * Events counted.
     */
    public KeyUsageBatch withAccepted(long accepted) {
        this.accepted = accepted;
        return this;
    }

    /**
     * Events that added nothing: a private, reserved or unreadable ip, or an occurred_at more than 24
     * hours old or more than 5 minutes ahead.
     */
    public KeyUsageBatch withIgnored(long ignored) {
        this.ignored = ignored;
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
        KeyUsageBatch other = (KeyUsageBatch) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.receivedAt, other.receivedAt)
                && Utils.enhancedDeepEquals(this.accepted, other.accepted)
                && Utils.enhancedDeepEquals(this.ignored, other.ignored);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, receivedAt, accepted, ignored);
    }

    @Override
    public String toString() {
        return Utils.toString(
                KeyUsageBatch.class, "id", id, "receivedAt", receivedAt, "accepted", accepted, "ignored", ignored);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String receivedAt;

        private long accepted;

        private long ignored;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned id of the batch: 'kub_' and a 26-character Crockford base32 ULID.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * When NoResponse first recorded the batch.
         */
        public Builder receivedAt(@Nonnull String receivedAt) {
            this.receivedAt = Utils.checkNotNull(receivedAt, "receivedAt");
            return this;
        }

        /**
         * Events counted.
         */
        public Builder accepted(long accepted) {
            this.accepted = accepted;
            return this;
        }

        /**
         * Events that added nothing: a private, reserved or unreadable ip, or an occurred_at more than 24
         * hours old or more than 5 minutes ahead.
         */
        public Builder ignored(long ignored) {
            this.ignored = ignored;
            return this;
        }

        public KeyUsageBatch build() {
            return new KeyUsageBatch(id, receivedAt, accepted, ignored);
        }
    }
}
