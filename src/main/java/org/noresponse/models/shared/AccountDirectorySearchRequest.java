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
 * <p>Bounded distinct-account search for one authenticated workspace/environment. identifier matches any
 * retained admitted assessment of an account, including historical submitted values. email preserves
 * the legacy canonical-mailbox filter.
 *
 * <p>identifier and email cannot be combined. All other filters combine using AND. Typed identifier
 * search uses stable opaque account order; ordinary and legacy email searches retain newest-seen
 * ordering.
 *
 * <p>Pages are live, not a cross-page snapshot. Requires history or investigate authority and
 * identifier-read permission for identifier or email filtering.
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

    /**
     * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
     * canonical uses the supported identifier normalisation without an ownership claim: email preserves
     * local-part case, NFC-normalises it and lowercases the domain without removing dots or tags; phone
     * uses international E.164 without extensions or an inferred region; username uses NFC and Unicode
     * case folding within the customer namespace.
     *
     * <p>mailbox is email-only and explicitly selects existing list rules: case-insensitive, remove +tag, and
     * ignore local-part dots at gmail.com and googlemail.com; googlemail.com maps to gmail.com. Username
     * requires namespace customer. Email and phone forbid namespace.
     *
     * <p>Source-discovered identifiers and platform handles are not searched. Unsupported kinds, namespaces,
     * modes and invalid values return 400. Search equality does not establish verified ownership or merge
     * accounts.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("identifier")
    private IdentifierSearch identifier;

    @JsonCreator
    public AccountDirectorySearchRequest(
            @JsonProperty("needs_action") @Nullable Boolean needsAction,
            @JsonProperty("account_id") @Nullable String accountId,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("cursor") @Nullable String cursor,
            @JsonProperty("identifier") @Nullable IdentifierSearch identifier) {
        this.needsAction = Optional.ofNullable(needsAction).orElse(Builder._SINGLETON_VALUE_NeedsAction.value());
        this.accountId = accountId;
        this.email = email;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
        this.identifier = identifier;
    }

    public AccountDirectorySearchRequest() {
        this(null, null, null, null, null, null);
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

    /**
     * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
     * canonical uses the supported identifier normalisation without an ownership claim: email preserves
     * local-part case, NFC-normalises it and lowercases the domain without removing dots or tags; phone
     * uses international E.164 without extensions or an inferred region; username uses NFC and Unicode
     * case folding within the customer namespace.
     *
     * <p>mailbox is email-only and explicitly selects existing list rules: case-insensitive, remove +tag, and
     * ignore local-part dots at gmail.com and googlemail.com; googlemail.com maps to gmail.com. Username
     * requires namespace customer. Email and phone forbid namespace.
     *
     * <p>Source-discovered identifiers and platform handles are not searched. Unsupported kinds, namespaces,
     * modes and invalid values return 400. Search equality does not establish verified ownership or merge
     * accounts.
     */
    public Optional<IdentifierSearch> identifier() {
        return Optional.ofNullable(this.identifier);
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

    /**
     * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
     * canonical uses the supported identifier normalisation without an ownership claim: email preserves
     * local-part case, NFC-normalises it and lowercases the domain without removing dots or tags; phone
     * uses international E.164 without extensions or an inferred region; username uses NFC and Unicode
     * case folding within the customer namespace.
     *
     * <p>mailbox is email-only and explicitly selects existing list rules: case-insensitive, remove +tag, and
     * ignore local-part dots at gmail.com and googlemail.com; googlemail.com maps to gmail.com. Username
     * requires namespace customer. Email and phone forbid namespace.
     *
     * <p>Source-discovered identifiers and platform handles are not searched. Unsupported kinds, namespaces,
     * modes and invalid values return 400. Search equality does not establish verified ownership or merge
     * accounts.
     */
    public AccountDirectorySearchRequest withIdentifier(@Nullable IdentifierSearch identifier) {
        this.identifier = identifier;
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
                && Utils.enhancedDeepEquals(this.cursor, other.cursor)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(needsAction, accountId, email, limit, cursor, identifier);
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
                cursor,
                "identifier",
                identifier);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Boolean needsAction;

        private String accountId;

        private String email;

        private Long limit;

        private String cursor;

        private IdentifierSearch identifier;

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

        /**
         * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
         * canonical uses the supported identifier normalisation without an ownership claim: email preserves
         * local-part case, NFC-normalises it and lowercases the domain without removing dots or tags; phone
         * uses international E.164 without extensions or an inferred region; username uses NFC and Unicode
         * case folding within the customer namespace.
         *
         * <p>mailbox is email-only and explicitly selects existing list rules: case-insensitive, remove +tag, and
         * ignore local-part dots at gmail.com and googlemail.com; googlemail.com maps to gmail.com. Username
         * requires namespace customer. Email and phone forbid namespace.
         *
         * <p>Source-discovered identifiers and platform handles are not searched. Unsupported kinds, namespaces,
         * modes and invalid values return 400. Search equality does not establish verified ownership or merge
         * accounts.
         */
        public Builder identifier(@Nullable IdentifierSearch identifier) {
            this.identifier = identifier;
            return this;
        }

        public AccountDirectorySearchRequest build() {
            return new AccountDirectorySearchRequest(needsAction, accountId, email, limit, cursor, identifier);
        }

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_NeedsAction =
                new LazySingletonValue<>("needs_action", "false", new TypeReference<Boolean>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "25", new TypeReference<Long>() {});
    }
}
