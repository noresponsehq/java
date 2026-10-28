package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AccountOutcomeRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RecordAccountOutcomeRequest {
    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=account_id")
    private String accountId;

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    @NoResponseMetadata("header:style=simple,explode=false,name=Idempotency-Key")
    private String idempotencyKey;

    @NoResponseMetadata("request:mediaType=application/json")
    private AccountOutcomeRequest body;

    @JsonCreator
    public RecordAccountOutcomeRequest(
            @Nonnull String accountId, @Nonnull String idempotencyKey, @Nonnull AccountOutcomeRequest body) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.idempotencyKey = Optional.ofNullable(idempotencyKey)
                .orElseThrow(() -> new IllegalArgumentException("idempotencyKey cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public String accountId() {
        return this.accountId;
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    public String idempotencyKey() {
        return this.idempotencyKey;
    }

    public AccountOutcomeRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public RecordAccountOutcomeRequest withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the
     * workspace principal and bound to the canonical body for 24 hours.
     */
    public RecordAccountOutcomeRequest withIdempotencyKey(@Nonnull String idempotencyKey) {
        this.idempotencyKey = Utils.checkNotNull(idempotencyKey, "idempotencyKey");
        return this;
    }

    public RecordAccountOutcomeRequest withBody(@Nonnull AccountOutcomeRequest body) {
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
        RecordAccountOutcomeRequest other = (RecordAccountOutcomeRequest) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordAccountOutcomeRequest.class,
                "accountId",
                accountId,
                "idempotencyKey",
                idempotencyKey,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private String idempotencyKey;

        private AccountOutcomeRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
         * ':' if the client requires it). A value that does not match the account_id shape answers 400
         * invalid_request.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
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

        public Builder body(@Nonnull AccountOutcomeRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public RecordAccountOutcomeRequest build() {
            return new RecordAccountOutcomeRequest(accountId, idempotencyKey, body);
        }
    }
}
