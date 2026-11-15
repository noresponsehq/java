package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.GroupRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CreateGroupRequest {
    /**
     * Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in
     * its current state while it is retained. A different request with the same key answers 409.
     *
     * <p>Use a new key for a deliberate new operation. Keys are scoped to the environment and operation;
     * person searches also scope them to the group and person.
     */
    @NoResponseMetadata("header:style=simple,explode=false,name=Idempotency-Key")
    private String idempotencyKey;

    @NoResponseMetadata("request:mediaType=application/json")
    private GroupRequest body;

    @JsonCreator
    public CreateGroupRequest(@Nullable String idempotencyKey, @Nonnull GroupRequest body) {
        this.idempotencyKey = idempotencyKey;
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public CreateGroupRequest(@Nonnull GroupRequest body) {
        this(null, body);
    }

    /**
     * Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in
     * its current state while it is retained. A different request with the same key answers 409.
     *
     * <p>Use a new key for a deliberate new operation. Keys are scoped to the environment and operation;
     * person searches also scope them to the group and person.
     */
    public Optional<String> idempotencyKey() {
        return Optional.ofNullable(this.idempotencyKey);
    }

    public GroupRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in
     * its current state while it is retained. A different request with the same key answers 409.
     *
     * <p>Use a new key for a deliberate new operation. Keys are scoped to the environment and operation;
     * person searches also scope them to the group and person.
     */
    public CreateGroupRequest withIdempotencyKey(@Nullable String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
        return this;
    }

    public CreateGroupRequest withBody(@Nonnull GroupRequest body) {
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
        CreateGroupRequest other = (CreateGroupRequest) o;
        return Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(CreateGroupRequest.class, "idempotencyKey", idempotencyKey, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String idempotencyKey;

        private GroupRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in
         * its current state while it is retained. A different request with the same key answers 409.
         *
         * <p>Use a new key for a deliberate new operation. Keys are scoped to the environment and operation;
         * person searches also scope them to the group and person.
         */
        public Builder idempotencyKey(@Nullable String idempotencyKey) {
            this.idempotencyKey = idempotencyKey;
            return this;
        }

        public Builder body(@Nonnull GroupRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CreateGroupRequest build() {
            return new CreateGroupRequest(idempotencyKey, body);
        }
    }
}
