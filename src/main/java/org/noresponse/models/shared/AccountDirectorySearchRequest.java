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
 * identifier-read permission for identifier, identifier_expression or email filtering.
 * identifier_expression explicitly chooses scope assessment (one whole matching submission) or account
 * (terms may match different retained submissions of one account).
 *
 * <p>At most 8 raw terms and 3 group levels. Mutually exclusive with identifier and email. Expression
 * search uses the same stable opaque account order as typed identifier search.
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
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
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

    /**
     * Every account that one of its retained admitted assessments sent an email of this domain or its
     * subdomains, matched as lists match an email_domain entry and with the same identifier-read
     * permission as email. A value that is not a registrable domain is refused with 400. Mutually
     * exclusive with email, identifier and identifier_expression.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email_domain")
    private String emailDomain;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("limit")
    private Long limit;

    /**
     * Opaque, expiring cursor bound to workspace, environment, operation and all filters, including
     * expression scope, ordered structure and original term values. Keep grouping, operand order, matching
     * modes and values unchanged; an omitted match is equivalent to exact. The page limit may change.
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

    /**
     * A bounded AND/OR expression over submitted identifiers. Required scope assessment means the entire
     * expression must match one retained assessment. Scope account permits each term to match a different
     * retained assessment of the same account and is accepted only by account search.
     *
     * <p>Exactly one of term, and or or is allowed at every node. Groups contain 2 to 8 operands, with at
     * most 3 nested group levels and 8 raw term occurrences over the entire expression. A root term is
     * allowed.
     *
     * <p>Duplicate terms with the same kind, match rule, namespace and normalized comparison are idempotent,
     * but count toward the raw limit. Match rules default to exact and preserve the existing identifier
     * contract. Only retained submitted identifiers can satisfy a term.
     *
     * <p>Unknown fields, unsupported scopes and malformed or oversized expressions return 400
     * invalid_request.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("identifier_expression")
    private IdentifierExpression identifierExpression;

    @JsonCreator
    public AccountDirectorySearchRequest(
            @JsonProperty("needs_action") @Nullable Boolean needsAction,
            @JsonProperty("account_id") @Nullable String accountId,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("email_domain") @Nullable String emailDomain,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("cursor") @Nullable String cursor,
            @JsonProperty("identifier") @Nullable IdentifierSearch identifier,
            @JsonProperty("identifier_expression") @Nullable IdentifierExpression identifierExpression) {
        this.needsAction = Optional.ofNullable(needsAction).orElse(Builder._SINGLETON_VALUE_NeedsAction.value());
        this.accountId = accountId;
        this.email = email;
        this.emailDomain = emailDomain;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
        this.identifier = identifier;
        this.identifierExpression = identifierExpression;
    }

    public AccountDirectorySearchRequest() {
        this(null, null, null, null, null, null, null, null);
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
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
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

    /**
     * Every account that one of its retained admitted assessments sent an email of this domain or its
     * subdomains, matched as lists match an email_domain entry and with the same identifier-read
     * permission as email. A value that is not a registrable domain is refused with 400. Mutually
     * exclusive with email, identifier and identifier_expression.
     */
    public Optional<String> emailDomain() {
        return Optional.ofNullable(this.emailDomain);
    }

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Opaque, expiring cursor bound to workspace, environment, operation and all filters, including
     * expression scope, ordered structure and original term values. Keep grouping, operand order, matching
     * modes and values unchanged; an omitted match is equivalent to exact. The page limit may change.
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

    /**
     * A bounded AND/OR expression over submitted identifiers. Required scope assessment means the entire
     * expression must match one retained assessment. Scope account permits each term to match a different
     * retained assessment of the same account and is accepted only by account search.
     *
     * <p>Exactly one of term, and or or is allowed at every node. Groups contain 2 to 8 operands, with at
     * most 3 nested group levels and 8 raw term occurrences over the entire expression. A root term is
     * allowed.
     *
     * <p>Duplicate terms with the same kind, match rule, namespace and normalized comparison are idempotent,
     * but count toward the raw limit. Match rules default to exact and preserve the existing identifier
     * contract. Only retained submitted identifiers can satisfy a term.
     *
     * <p>Unknown fields, unsupported scopes and malformed or oversized expressions return 400
     * invalid_request.
     */
    public Optional<IdentifierExpression> identifierExpression() {
        return Optional.ofNullable(this.identifierExpression);
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
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
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

    /**
     * Every account that one of its retained admitted assessments sent an email of this domain or its
     * subdomains, matched as lists match an email_domain entry and with the same identifier-read
     * permission as email. A value that is not a registrable domain is refused with 400. Mutually
     * exclusive with email, identifier and identifier_expression.
     */
    public AccountDirectorySearchRequest withEmailDomain(@Nullable String emailDomain) {
        this.emailDomain = emailDomain;
        return this;
    }

    public AccountDirectorySearchRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Opaque, expiring cursor bound to workspace, environment, operation and all filters, including
     * expression scope, ordered structure and original term values. Keep grouping, operand order, matching
     * modes and values unchanged; an omitted match is equivalent to exact. The page limit may change.
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

    /**
     * A bounded AND/OR expression over submitted identifiers. Required scope assessment means the entire
     * expression must match one retained assessment. Scope account permits each term to match a different
     * retained assessment of the same account and is accepted only by account search.
     *
     * <p>Exactly one of term, and or or is allowed at every node. Groups contain 2 to 8 operands, with at
     * most 3 nested group levels and 8 raw term occurrences over the entire expression. A root term is
     * allowed.
     *
     * <p>Duplicate terms with the same kind, match rule, namespace and normalized comparison are idempotent,
     * but count toward the raw limit. Match rules default to exact and preserve the existing identifier
     * contract. Only retained submitted identifiers can satisfy a term.
     *
     * <p>Unknown fields, unsupported scopes and malformed or oversized expressions return 400
     * invalid_request.
     */
    public AccountDirectorySearchRequest withIdentifierExpression(@Nullable IdentifierExpression identifierExpression) {
        this.identifierExpression = identifierExpression;
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
                && Utils.enhancedDeepEquals(this.emailDomain, other.emailDomain)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier)
                && Utils.enhancedDeepEquals(this.identifierExpression, other.identifierExpression);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                needsAction, accountId, email, emailDomain, limit, cursor, identifier, identifierExpression);
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
                "emailDomain",
                emailDomain,
                "limit",
                limit,
                "cursor",
                cursor,
                "identifier",
                identifier,
                "identifierExpression",
                identifierExpression);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Boolean needsAction;

        private String accountId;

        private String email;

        private String emailDomain;

        private Long limit;

        private String cursor;

        private IdentifierSearch identifier;

        private IdentifierExpression identifierExpression;

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
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
         * /assessments and its /linked_accounts. An assessment requested by a provider hook at
         * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
         * its assessment_id.
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

        /**
         * Every account that one of its retained admitted assessments sent an email of this domain or its
         * subdomains, matched as lists match an email_domain entry and with the same identifier-read
         * permission as email. A value that is not a registrable domain is refused with 400. Mutually
         * exclusive with email, identifier and identifier_expression.
         */
        public Builder emailDomain(@Nullable String emailDomain) {
            this.emailDomain = emailDomain;
            return this;
        }

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Opaque, expiring cursor bound to workspace, environment, operation and all filters, including
         * expression scope, ordered structure and original term values. Keep grouping, operand order, matching
         * modes and values unchanged; an omitted match is equivalent to exact. The page limit may change.
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

        /**
         * A bounded AND/OR expression over submitted identifiers. Required scope assessment means the entire
         * expression must match one retained assessment. Scope account permits each term to match a different
         * retained assessment of the same account and is accepted only by account search.
         *
         * <p>Exactly one of term, and or or is allowed at every node. Groups contain 2 to 8 operands, with at
         * most 3 nested group levels and 8 raw term occurrences over the entire expression. A root term is
         * allowed.
         *
         * <p>Duplicate terms with the same kind, match rule, namespace and normalized comparison are idempotent,
         * but count toward the raw limit. Match rules default to exact and preserve the existing identifier
         * contract. Only retained submitted identifiers can satisfy a term.
         *
         * <p>Unknown fields, unsupported scopes and malformed or oversized expressions return 400
         * invalid_request.
         */
        public Builder identifierExpression(@Nullable IdentifierExpression identifierExpression) {
            this.identifierExpression = identifierExpression;
            return this;
        }

        public AccountDirectorySearchRequest build() {
            return new AccountDirectorySearchRequest(
                    needsAction, accountId, email, emailDomain, limit, cursor, identifier, identifierExpression);
        }

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_NeedsAction =
                new LazySingletonValue<>("needs_action", "false", new TypeReference<Boolean>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "25", new TypeReference<Long>() {});
    }
}
