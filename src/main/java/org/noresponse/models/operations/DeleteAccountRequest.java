package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class DeleteAccountRequest {
    /**
     * The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=account_id")
    private String accountId;

    @JsonCreator
    public DeleteAccountRequest(@Nonnull String accountId) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
    }

    /**
     * The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public String accountId() {
        return this.accountId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public DeleteAccountRequest withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
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
        DeleteAccountRequest other = (DeleteAccountRequest) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId);
    }

    @Override
    public String toString() {
        return Utils.toString(DeleteAccountRequest.class, "accountId", accountId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and
         * ':' if the client requires it). A value that does not match the account_id shape answers 400
         * invalid_request.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public DeleteAccountRequest build() {
            return new DeleteAccountRequest(accountId);
        }
    }
}
