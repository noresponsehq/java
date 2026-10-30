package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class LinkedAccount {
    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    /**
     * One entry per shared kind, in the order email, phone, username. Each entry uses the strictest basis
     * that links the two accounts: exact, then canonical, then mailbox.
     */
    @JsonProperty("shared_identifiers")
    private List<SharedIdentifier> sharedIdentifiers;

    @JsonCreator
    public LinkedAccount(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("shared_identifiers") @Nonnull List<SharedIdentifier> sharedIdentifiers) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.sharedIdentifiers = Optional.ofNullable(sharedIdentifiers)
                .orElseThrow(() -> new IllegalArgumentException("sharedIdentifiers cannot be null"));
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    public String accountId() {
        return this.accountId;
    }

    /**
     * One entry per shared kind, in the order email, phone, username. Each entry uses the strictest basis
     * that links the two accounts: exact, then canonical, then mailbox.
     */
    public List<SharedIdentifier> sharedIdentifiers() {
        return this.sharedIdentifiers;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    public LinkedAccount withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * One entry per shared kind, in the order email, phone, username. Each entry uses the strictest basis
     * that links the two accounts: exact, then canonical, then mailbox.
     */
    public LinkedAccount withSharedIdentifiers(@Nonnull List<SharedIdentifier> sharedIdentifiers) {
        this.sharedIdentifiers = Utils.checkNotNull(sharedIdentifiers, "sharedIdentifiers");
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
        LinkedAccount other = (LinkedAccount) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.sharedIdentifiers, other.sharedIdentifiers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, sharedIdentifiers);
    }

    @Override
    public String toString() {
        return Utils.toString(LinkedAccount.class, "accountId", accountId, "sharedIdentifiers", sharedIdentifiers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private List<SharedIdentifier> sharedIdentifiers;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
         * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
         * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
         * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
         * internal id is a common pseudonymous scheme.
         *
         * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
         * /assessments and its /linked_accounts. An assessment requested by a provider hook at
         * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
         * its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        /**
         * One entry per shared kind, in the order email, phone, username. Each entry uses the strictest basis
         * that links the two accounts: exact, then canonical, then mailbox.
         */
        public Builder sharedIdentifiers(@Nonnull List<SharedIdentifier> sharedIdentifiers) {
            this.sharedIdentifiers = Utils.checkNotNull(sharedIdentifiers, "sharedIdentifiers");
            return this;
        }

        public LinkedAccount build() {
            return new LinkedAccount(accountId, sharedIdentifiers);
        }
    }
}
