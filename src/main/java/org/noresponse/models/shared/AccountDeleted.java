package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AccountDeleted
 *
 * <p>Terminal for the account's assessments created at or before deleted_at in the receiving endpoint's
 * environment: delete what you hold from them and ignore later-arriving updates of them. deleted_at is
 * no earlier than the creation of any assessment it erased. An assessment created after it belongs to
 * the account assessed again: keep it, even when this event arrives after it.
 */
public class AccountDeleted {
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
     * How many assessments the erasure deleted.
     */
    @JsonProperty("assessments_deleted")
    private long assessmentsDeleted;

    /**
     * When the erasure took effect, equal to deleted_at in the DELETE answer.
     */
    @JsonProperty("deleted_at")
    private String deletedAt;

    @JsonCreator
    public AccountDeleted(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("assessments_deleted") long assessmentsDeleted,
            @JsonProperty("deleted_at") @Nonnull String deletedAt) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.assessmentsDeleted = assessmentsDeleted;
        this.deletedAt = Optional.ofNullable(deletedAt)
                .orElseThrow(() -> new IllegalArgumentException("deletedAt cannot be null"));
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
     * How many assessments the erasure deleted.
     */
    public long assessmentsDeleted() {
        return this.assessmentsDeleted;
    }

    /**
     * When the erasure took effect, equal to deleted_at in the DELETE answer.
     */
    public String deletedAt() {
        return this.deletedAt;
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
    public AccountDeleted withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * How many assessments the erasure deleted.
     */
    public AccountDeleted withAssessmentsDeleted(long assessmentsDeleted) {
        this.assessmentsDeleted = assessmentsDeleted;
        return this;
    }

    /**
     * When the erasure took effect, equal to deleted_at in the DELETE answer.
     */
    public AccountDeleted withDeletedAt(@Nonnull String deletedAt) {
        this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
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
        AccountDeleted other = (AccountDeleted) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.assessmentsDeleted, other.assessmentsDeleted)
                && Utils.enhancedDeepEquals(this.deletedAt, other.deletedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, assessmentsDeleted, deletedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDeleted.class,
                "accountId",
                accountId,
                "assessmentsDeleted",
                assessmentsDeleted,
                "deletedAt",
                deletedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private long assessmentsDeleted;

        private String deletedAt;

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
         * How many assessments the erasure deleted.
         */
        public Builder assessmentsDeleted(long assessmentsDeleted) {
            this.assessmentsDeleted = assessmentsDeleted;
            return this;
        }

        /**
         * When the erasure took effect, equal to deleted_at in the DELETE answer.
         */
        public Builder deletedAt(@Nonnull String deletedAt) {
            this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
            return this;
        }

        public AccountDeleted build() {
            return new AccountDeleted(accountId, assessmentsDeleted, deletedAt);
        }
    }
}
