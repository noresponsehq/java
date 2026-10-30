package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * CredentialAssociation
 *
 * <p>An association between an account and an issued key. The response excludes the key and its hash.
 * Retirement removes this association from risk calculations; revoke the actual key through your own
 * service.
 */
public class CredentialAssociation {

    @JsonProperty("id")
    private String id;

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

    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("retired_at")
    private String retiredAt;

    @JsonProperty("actor")
    private CredentialAssociationActor actor;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("retired_by")
    private CredentialAssociationRetiredBy retiredBy;

    @JsonCreator
    public CredentialAssociation(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("retired_at") @Nullable String retiredAt,
            @JsonProperty("actor") @Nonnull CredentialAssociationActor actor,
            @JsonProperty("retired_by") @Nullable CredentialAssociationRetiredBy retiredBy) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.retiredAt = retiredAt;
        this.actor = Optional.ofNullable(actor).orElseThrow(() -> new IllegalArgumentException("actor cannot be null"));
        this.retiredBy = retiredBy;
    }

    public CredentialAssociation(
            @Nonnull String id,
            @Nonnull String accountId,
            @Nonnull String createdAt,
            @Nonnull CredentialAssociationActor actor) {
        this(id, accountId, createdAt, null, actor, null);
    }

    public String id() {
        return this.id;
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

    public String createdAt() {
        return this.createdAt;
    }

    public Optional<String> retiredAt() {
        return Optional.ofNullable(this.retiredAt);
    }

    public CredentialAssociationActor actor() {
        return this.actor;
    }

    public Optional<CredentialAssociationRetiredBy> retiredBy() {
        return Optional.ofNullable(this.retiredBy);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CredentialAssociation withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
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
    public CredentialAssociation withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public CredentialAssociation withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public CredentialAssociation withRetiredAt(@Nullable String retiredAt) {
        this.retiredAt = retiredAt;
        return this;
    }

    public CredentialAssociation withActor(@Nonnull CredentialAssociationActor actor) {
        this.actor = Utils.checkNotNull(actor, "actor");
        return this;
    }

    public CredentialAssociation withRetiredBy(@Nullable CredentialAssociationRetiredBy retiredBy) {
        this.retiredBy = retiredBy;
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
        CredentialAssociation other = (CredentialAssociation) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.retiredAt, other.retiredAt)
                && Utils.enhancedDeepEquals(this.actor, other.actor)
                && Utils.enhancedDeepEquals(this.retiredBy, other.retiredBy);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, accountId, createdAt, retiredAt, actor, retiredBy);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CredentialAssociation.class,
                "id",
                id,
                "accountId",
                accountId,
                "createdAt",
                createdAt,
                "retiredAt",
                retiredAt,
                "actor",
                actor,
                "retiredBy",
                retiredBy);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String accountId;

        private String createdAt;

        private String retiredAt;

        private CredentialAssociationActor actor;

        private CredentialAssociationRetiredBy retiredBy;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
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
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder retiredAt(@Nullable String retiredAt) {
            this.retiredAt = retiredAt;
            return this;
        }

        public Builder actor(@Nonnull CredentialAssociationActor actor) {
            this.actor = Utils.checkNotNull(actor, "actor");
            return this;
        }

        public Builder retiredBy(@Nullable CredentialAssociationRetiredBy retiredBy) {
            this.retiredBy = retiredBy;
            return this;
        }

        public CredentialAssociation build() {
            return new CredentialAssociation(id, accountId, createdAt, retiredAt, actor, retiredBy);
        }
    }
}
