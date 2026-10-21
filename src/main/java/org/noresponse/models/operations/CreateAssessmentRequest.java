package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CreateAssessmentRequest {
    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * tenant principal and bound to the canonical body for 24 hours.
     */
    @NoResponseMetadata("header:style=simple,explode=false,name=Idempotency-Key")
    private String idempotencyKey;

    @NoResponseMetadata("request:mediaType=application/json")
    private AssessmentRequest body;

    @JsonCreator
    public CreateAssessmentRequest(@Nonnull String idempotencyKey, @Nonnull AssessmentRequest body) {
        this.idempotencyKey = Optional.ofNullable(idempotencyKey)
                .orElseThrow(() -> new IllegalArgumentException("idempotencyKey cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * tenant principal and bound to the canonical body for 24 hours.
     */
    public String idempotencyKey() {
        return this.idempotencyKey;
    }

    public AssessmentRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * tenant principal and bound to the canonical body for 24 hours.
     */
    public CreateAssessmentRequest withIdempotencyKey(@Nonnull String idempotencyKey) {
        this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
        return this;
    }

    public CreateAssessmentRequest withBody(@Nonnull AssessmentRequest body) {
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
        CreateAssessmentRequest other = (CreateAssessmentRequest) o;
        return Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(CreateAssessmentRequest.class, "idempotencyKey", idempotencyKey, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String idempotencyKey;

        private AssessmentRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
         * tenant principal and bound to the canonical body for 24 hours.
         */
        public Builder idempotencyKey(@Nonnull String idempotencyKey) {
            this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
            return this;
        }

        public Builder body(@Nonnull AssessmentRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CreateAssessmentRequest build() {
            return new CreateAssessmentRequest(idempotencyKey, body);
        }
    }
}
