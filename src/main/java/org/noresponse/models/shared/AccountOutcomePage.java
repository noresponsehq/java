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
 * AccountOutcomePage
 *
 * <p>List retained account reports, newest first. The page-level effective outcome id names the current
 * report. Each receipt preserves the effective report and risk version from its original write.
 *
 * <p>Page with the returned cursor.
 */
public class AccountOutcomePage {
    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("items")
    private List<AccountOutcome> items;

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("effective_outcome_id")
    private String effectiveOutcomeId;

    @JsonCreator
    public AccountOutcomePage(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("items") @Nonnull List<AccountOutcome> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor,
            @JsonProperty("effective_outcome_id") @Nullable String effectiveOutcomeId) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
        this.effectiveOutcomeId = effectiveOutcomeId;
    }

    public AccountOutcomePage(@Nonnull String accountId, @Nonnull List<AccountOutcome> items) {
        this(accountId, items, null, null);
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public String accountId() {
        return this.accountId;
    }

    public List<AccountOutcome> items() {
        return this.items;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public Optional<String> effectiveOutcomeId() {
        return Optional.ofNullable(this.effectiveOutcomeId);
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
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public AccountOutcomePage withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public AccountOutcomePage withItems(@Nonnull List<AccountOutcome> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public AccountOutcomePage withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
        return this;
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public AccountOutcomePage withEffectiveOutcomeId(@Nullable String effectiveOutcomeId) {
        this.effectiveOutcomeId = effectiveOutcomeId;
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
        AccountOutcomePage other = (AccountOutcomePage) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor)
                && Utils.enhancedDeepEquals(this.effectiveOutcomeId, other.effectiveOutcomeId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, items, nextCursor, effectiveOutcomeId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountOutcomePage.class,
                "accountId",
                accountId,
                "items",
                items,
                "nextCursor",
                nextCursor,
                "effectiveOutcomeId",
                effectiveOutcomeId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private List<AccountOutcome> items;

        private String nextCursor;

        private String effectiveOutcomeId;

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
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
         * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
         * yet, carries the server-issued account_id pre: followed by its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public Builder items(@Nonnull List<AccountOutcome> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
         * unchanged.
         */
        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        /**
         * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
         * Unique within the workspace.
         */
        public Builder effectiveOutcomeId(@Nullable String effectiveOutcomeId) {
            this.effectiveOutcomeId = effectiveOutcomeId;
            return this;
        }

        public AccountOutcomePage build() {
            return new AccountOutcomePage(accountId, items, nextCursor, effectiveOutcomeId);
        }
    }
}
