package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.SearchRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class StartWorkspacePersonSearchRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

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
    private SearchRequest body;

    @JsonCreator
    public StartWorkspacePersonSearchRequest(
            @Nonnull String personId, @Nullable String idempotencyKey, @Nonnull SearchRequest body) {
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.idempotencyKey = idempotencyKey;
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public StartWorkspacePersonSearchRequest(@Nonnull String personId, @Nonnull SearchRequest body) {
        this(personId, null, body);
    }

    public String personId() {
        return this.personId;
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

    public SearchRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public StartWorkspacePersonSearchRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    /**
     * Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in
     * its current state while it is retained. A different request with the same key answers 409.
     *
     * <p>Use a new key for a deliberate new operation. Keys are scoped to the environment and operation;
     * person searches also scope them to the group and person.
     */
    public StartWorkspacePersonSearchRequest withIdempotencyKey(@Nullable String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
        return this;
    }

    public StartWorkspacePersonSearchRequest withBody(@Nonnull SearchRequest body) {
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
        StartWorkspacePersonSearchRequest other = (StartWorkspacePersonSearchRequest) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId, idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                StartWorkspacePersonSearchRequest.class,
                "personId",
                personId,
                "idempotencyKey",
                idempotencyKey,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private String idempotencyKey;

        private SearchRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
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

        public Builder body(@Nonnull SearchRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public StartWorkspacePersonSearchRequest build() {
            return new StartWorkspacePersonSearchRequest(personId, idempotencyKey, body);
        }
    }
}
