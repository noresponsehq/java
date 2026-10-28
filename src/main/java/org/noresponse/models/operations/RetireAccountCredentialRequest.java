package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RetireAccountCredentialRequest {
    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=account_id")
    private String accountId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=credential_id")
    private String credentialId;

    @JsonCreator
    public RetireAccountCredentialRequest(@Nonnull String accountId, @Nonnull String credentialId) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.credentialId = Optional.ofNullable(credentialId)
                .orElseThrow(() -> new IllegalArgumentException("credentialId cannot be null"));
    }

    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public String accountId() {
        return this.accountId;
    }

    public String credentialId() {
        return this.credentialId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public RetireAccountCredentialRequest withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public RetireAccountCredentialRequest withCredentialId(@Nonnull String credentialId) {
        this.credentialId = Utils.checkNotNull(credentialId, "credentialId");
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
        RetireAccountCredentialRequest other = (RetireAccountCredentialRequest) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.credentialId, other.credentialId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, credentialId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RetireAccountCredentialRequest.class, "accountId", accountId, "credentialId", credentialId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private String credentialId;

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

        public Builder credentialId(@Nonnull String credentialId) {
            this.credentialId = Utils.checkNotNull(credentialId, "credentialId");
            return this;
        }

        public RetireAccountCredentialRequest build() {
            return new RetireAccountCredentialRequest(accountId, credentialId);
        }
    }
}
