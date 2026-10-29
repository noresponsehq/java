package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AccountDirectorySearchRequest
 *
 * <p>Bounded distinct-account search for a single authenticated workspace/environment. Email matches any
 * retained admitted assessment of an account, using assessment search's canonical mailbox rules. No
 * assessment history download is required.
 */
public class AccountDirectorySearchRequest {
    /**
     * Select accounts with a retained open or in-progress review, or a current review/block account
     * decision with no retained effective account outcome. The server applies this filter before counting
     * and pagination. Requires reviews.read as well as history.read for console members; API keys
     * additionally require management.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("needs_action")
    private Boolean needsAction;

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
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_id")
    private String accountId;

    /**
     * Assessments of every account that one of its retained admitted assessments sent this mailbox as
     * identifiers.email, matched as lists match an email entry: case-insensitive, a +tag removed and, at
     * gmail.com and googlemail.com, the dots of the local part ignored. Only assessments stored since
     * email list matching began carry the mailbox. An address that is not a valid mailbox is refused with
     * 400.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("limit")
    private Long limit;

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("cursor")
    private String cursor;

    @JsonCreator
    public AccountDirectorySearchRequest(
            @JsonProperty("needs_action") @Nullable Boolean needsAction,
            @JsonProperty("account_id") @Nullable String accountId,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("cursor") @Nullable String cursor) {
        this.needsAction = Optional.ofNullable(needsAction).orElse(Builder._SINGLETON_VALUE_NeedsAction.value());
        this.accountId = accountId;
        this.email = email;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
    }

    public AccountDirectorySearchRequest() {
        this(null, null, null, null, null);
    }

    /**
     * Select accounts with a retained open or in-progress review, or a current review/block account
     * decision with no retained effective account outcome. The server applies this filter before counting
     * and pagination. Requires reviews.read as well as history.read for console members; API keys
     * additionally require management.
     */
    public Optional<Boolean> needsAction() {
        return Optional.ofNullable(this.needsAction);
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
    public Optional<String> accountId() {
        return Optional.ofNullable(this.accountId);
    }

    /**
     * Assessments of every account that one of its retained admitted assessments sent this mailbox as
     * identifiers.email, matched as lists match an email entry: case-insensitive, a +tag removed and, at
     * gmail.com and googlemail.com, the dots of the local part ignored. Only assessments stored since
     * email list matching began carry the mailbox. An address that is not a valid mailbox is refused with
     * 400.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Select accounts with a retained open or in-progress review, or a current review/block account
     * decision with no retained effective account outcome. The server applies this filter before counting
     * and pagination. Requires reviews.read as well as history.read for console members; API keys
     * additionally require management.
     */
    public AccountDirectorySearchRequest withNeedsAction(@Nullable Boolean needsAction) {
        this.needsAction = needsAction;
        return this;
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
    public AccountDirectorySearchRequest withAccountId(@Nullable String accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * Assessments of every account that one of its retained admitted assessments sent this mailbox as
     * identifiers.email, matched as lists match an email entry: case-insensitive, a +tag removed and, at
     * gmail.com and googlemail.com, the dots of the local part ignored. Only assessments stored since
     * email list matching began carry the mailbox. An address that is not a valid mailbox is refused with
     * 400.
     */
    public AccountDirectorySearchRequest withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    public AccountDirectorySearchRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public AccountDirectorySearchRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
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
        AccountDirectorySearchRequest other = (AccountDirectorySearchRequest) o;
        return Utils.enhancedDeepEquals(this.needsAction, other.needsAction)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(needsAction, accountId, email, limit, cursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDirectorySearchRequest.class,
                "needsAction",
                needsAction,
                "accountId",
                accountId,
                "email",
                email,
                "limit",
                limit,
                "cursor",
                cursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Boolean needsAction;

        private String accountId;

        private String email;

        private Long limit;

        private String cursor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Select accounts with a retained open or in-progress review, or a current review/block account
         * decision with no retained effective account outcome. The server applies this filter before counting
         * and pagination. Requires reviews.read as well as history.read for console members; API keys
         * additionally require management.
         */
        public Builder needsAction(@Nullable Boolean needsAction) {
            this.needsAction = needsAction;
            return this;
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
        public Builder accountId(@Nullable String accountId) {
            this.accountId = accountId;
            return this;
        }

        /**
         * Assessments of every account that one of its retained admitted assessments sent this mailbox as
         * identifiers.email, matched as lists match an email entry: case-insensitive, a +tag removed and, at
         * gmail.com and googlemail.com, the dots of the local part ignored. Only assessments stored since
         * email list matching began carry the mailbox. An address that is not a valid mailbox is refused with
         * 400.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
         * unchanged.
         */
        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        public AccountDirectorySearchRequest build() {
            return new AccountDirectorySearchRequest(needsAction, accountId, email, limit, cursor);
        }

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_NeedsAction =
                new LazySingletonValue<>("needs_action", "false", new TypeReference<Boolean>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "25", new TypeReference<Long>() {});
    }
}
