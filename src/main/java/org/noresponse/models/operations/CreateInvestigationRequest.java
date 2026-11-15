package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.InvestigationRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CreateInvestigationRequest {
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
    private InvestigationRequest body;

    @JsonCreator
    public CreateInvestigationRequest(@Nonnull String idempotencyKey, @Nonnull InvestigationRequest body) {
        this.idempotencyKey = Optional.ofNullable(idempotencyKey)
                .orElseThrow(() -> new IllegalArgumentException("idempotencyKey cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in
     * its current state while it is retained. A different request with the same key answers 409.
     *
     * <p>Use a new key for a deliberate new operation. Keys are scoped to the environment and operation;
     * person searches also scope them to the group and person.
     */
    public String idempotencyKey() {
        return this.idempotencyKey;
    }

    public InvestigationRequest body() {
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
    public CreateInvestigationRequest withIdempotencyKey(@Nonnull String idempotencyKey) {
        this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
        return this;
    }

    public CreateInvestigationRequest withBody(@Nonnull InvestigationRequest body) {
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
        CreateInvestigationRequest other = (CreateInvestigationRequest) o;
        return Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(CreateInvestigationRequest.class, "idempotencyKey", idempotencyKey, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String idempotencyKey;

        private InvestigationRequest body;

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
        public Builder idempotencyKey(@Nonnull String idempotencyKey) {
            this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
            return this;
        }

        public Builder body(@Nonnull InvestigationRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CreateInvestigationRequest build() {
            return new CreateInvestigationRequest(idempotencyKey, body);
        }
    }
}
