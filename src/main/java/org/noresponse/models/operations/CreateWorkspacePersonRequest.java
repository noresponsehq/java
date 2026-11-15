package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.WorkspacePersonRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CreateWorkspacePersonRequest {
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
    private WorkspacePersonRequest body;

    @JsonCreator
    public CreateWorkspacePersonRequest(@Nullable String idempotencyKey, @Nonnull WorkspacePersonRequest body) {
        this.idempotencyKey = idempotencyKey;
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public CreateWorkspacePersonRequest(@Nonnull WorkspacePersonRequest body) {
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

    public WorkspacePersonRequest body() {
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
    public CreateWorkspacePersonRequest withIdempotencyKey(@Nullable String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
        return this;
    }

    public CreateWorkspacePersonRequest withBody(@Nonnull WorkspacePersonRequest body) {
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
        CreateWorkspacePersonRequest other = (CreateWorkspacePersonRequest) o;
        return Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(CreateWorkspacePersonRequest.class, "idempotencyKey", idempotencyKey, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String idempotencyKey;

        private WorkspacePersonRequest body;

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

        public Builder body(@Nonnull WorkspacePersonRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CreateWorkspacePersonRequest build() {
            return new CreateWorkspacePersonRequest(idempotencyKey, body);
        }
    }
}
