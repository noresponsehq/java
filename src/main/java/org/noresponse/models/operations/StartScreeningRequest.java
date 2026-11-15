package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ScreeningRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class StartScreeningRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    @NoResponseMetadata("header:style=simple,explode=false,name=Idempotency-Key")
    private String idempotencyKey;

    @NoResponseMetadata("request:mediaType=application/json")
    private ScreeningRequest body;

    @JsonCreator
    public StartScreeningRequest(
            @Nonnull String groupId, @Nonnull String idempotencyKey, @Nonnull ScreeningRequest body) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.idempotencyKey = Optional.ofNullable(idempotencyKey)
                .orElseThrow(() -> new IllegalArgumentException("idempotencyKey cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    public String idempotencyKey() {
        return this.idempotencyKey;
    }

    public ScreeningRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public StartScreeningRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    public StartScreeningRequest withIdempotencyKey(@Nonnull String idempotencyKey) {
        this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
        return this;
    }

    public StartScreeningRequest withBody(@Nonnull ScreeningRequest body) {
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
        StartScreeningRequest other = (StartScreeningRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                StartScreeningRequest.class, "groupId", groupId, "idempotencyKey", idempotencyKey, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private String idempotencyKey;

        private ScreeningRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        /**
         * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
         * workspace principal and bound to the canonical body for 24 hours.
         */
        public Builder idempotencyKey(@Nonnull String idempotencyKey) {
            this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
            return this;
        }

        public Builder body(@Nonnull ScreeningRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public StartScreeningRequest build() {
            return new StartScreeningRequest(groupId, idempotencyKey, body);
        }
    }
}
