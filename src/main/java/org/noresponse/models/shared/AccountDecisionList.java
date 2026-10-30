package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AccountDecisionList
 *
 * <p>Page through recorded account decisions.
 */
public class AccountDecisionList {
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

    @JsonProperty("items")
    private List<AccountDecisionSnapshot> items;

    /**
     * Opaque, expiring cursor bound to the workspace, environment, account and history kind. Absent at the
     * end.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public AccountDecisionList(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("items") @Nonnull List<AccountDecisionSnapshot> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
    }

    public AccountDecisionList(@Nonnull String accountId, @Nonnull List<AccountDecisionSnapshot> items) {
        this(accountId, items, null);
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

    public List<AccountDecisionSnapshot> items() {
        return this.items;
    }

    /**
     * Opaque, expiring cursor bound to the workspace, environment, account and history kind. Absent at the
     * end.
     */
    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
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
    public AccountDecisionList withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public AccountDecisionList withItems(@Nonnull List<AccountDecisionSnapshot> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Opaque, expiring cursor bound to the workspace, environment, account and history kind. Absent at the
     * end.
     */
    public AccountDecisionList withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
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
        AccountDecisionList other = (AccountDecisionList) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, items, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDecisionList.class, "accountId", accountId, "items", items, "nextCursor", nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private List<AccountDecisionSnapshot> items;

        private String nextCursor;

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

        public Builder items(@Nonnull List<AccountDecisionSnapshot> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Opaque, expiring cursor bound to the workspace, environment, account and history kind. Absent at the
         * end.
         */
        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public AccountDecisionList build() {
            return new AccountDecisionList(accountId, items, nextCursor);
        }
    }
}
