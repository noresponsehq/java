package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AccountBinding
 *
 * <p>Binding appends an assessment revision and preserves earlier versions. Continuations use the same
 * assessment. Erasing either the provisional or canonical name erases the canonical account, including
 * the bound assessment.
 */
public class AccountBinding {
    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

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
    @JsonProperty("provisional_account_id")
    private String provisionalAccountId;

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
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    @JsonProperty("assessment_version")
    private long assessmentVersion;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("actor")
    private AccountBindingActor actor;

    @JsonCreator
    public AccountBinding(
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("provisional_account_id") @Nonnull String provisionalAccountId,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("assessment_version") long assessmentVersion,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("actor") @Nonnull AccountBindingActor actor) {
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.provisionalAccountId = Optional.ofNullable(provisionalAccountId)
                .orElseThrow(() -> new IllegalArgumentException("provisionalAccountId cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.assessmentVersion = assessmentVersion;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.actor = Optional.ofNullable(actor).orElseThrow(() -> new IllegalArgumentException("actor cannot be null"));
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public String assessmentId() {
        return this.assessmentId;
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
    public String provisionalAccountId() {
        return this.provisionalAccountId;
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
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    public long assessmentVersion() {
        return this.assessmentVersion;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public AccountBindingActor actor() {
        return this.actor;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public AccountBinding withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
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
    public AccountBinding withProvisionalAccountId(@Nonnull String provisionalAccountId) {
        this.provisionalAccountId = Utils.checkNotNull(provisionalAccountId, "provisionalAccountId");
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
    public AccountBinding withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    public AccountBinding withAssessmentVersion(long assessmentVersion) {
        this.assessmentVersion = assessmentVersion;
        return this;
    }

    public AccountBinding withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public AccountBinding withActor(@Nonnull AccountBindingActor actor) {
        this.actor = Utils.checkNotNull(actor, "actor");
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
        AccountBinding other = (AccountBinding) o;
        return Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.provisionalAccountId, other.provisionalAccountId)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.assessmentVersion, other.assessmentVersion)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.actor, other.actor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(assessmentId, provisionalAccountId, accountId, assessmentVersion, createdAt, actor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountBinding.class,
                "assessmentId",
                assessmentId,
                "provisionalAccountId",
                provisionalAccountId,
                "accountId",
                accountId,
                "assessmentVersion",
                assessmentVersion,
                "createdAt",
                createdAt,
                "actor",
                actor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

        private String provisionalAccountId;

        private String accountId;

        private long assessmentVersion;

        private String createdAt;

        private AccountBindingActor actor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
         * within the workspace; another workspace's assessment id answers 404.
         */
        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
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
        public Builder provisionalAccountId(@Nonnull String provisionalAccountId) {
            this.provisionalAccountId = Utils.checkNotNull(provisionalAccountId, "provisionalAccountId");
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

        /**
         * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
         * assessment is served (an async continuation settling a source, a change of recommendation,
         * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
         * not revised, and the expiry webhook carries the last served version.
         */
        public Builder assessmentVersion(long assessmentVersion) {
            this.assessmentVersion = assessmentVersion;
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder actor(@Nonnull AccountBindingActor actor) {
            this.actor = Utils.checkNotNull(actor, "actor");
            return this;
        }

        public AccountBinding build() {
            return new AccountBinding(
                    assessmentId, provisionalAccountId, accountId, assessmentVersion, createdAt, actor);
        }
    }
}
