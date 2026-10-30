package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * OnlineAccount
 *
 * <p>One online-account search of the signup's email or username, detailed view only. name and domain
 * name the platform searched, not a provider. outcome is the search's signal outcome: found,
 * not_found, or a failure (blocked, unavailable, inconclusive) with its reason; a failure is not
 * reported as not_found.
 *
 * <p>The members after reason are what the platform returned beside an account it found, each absent when
 * it did not say.
 */
public class OnlineAccount {
    /**
     * The platform's name, such as Adobe or GitHub.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The platform's domain, lower case: the host its logo is fetched for.
     */
    @JsonProperty("domain")
    private String domain;

    @JsonProperty("identifier")
    private Identifier identifier;

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    @JsonProperty("outcome")
    private SignalOutcome outcome;

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reason")
    private FailureReason reason;

    /**
     * How the account signs in, as the platform reports it, sorted: a password, a Google, Facebook, Apple
     * or Microsoft account, the organisation's single sign-on, or another method.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("sign_in")
    private List<SignIn> signIn;

    /**
     * Whether the platform says the account's email is verified.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email_verified")
    private Boolean emailVerified;

    /**
     * Whether the platform says the account is blocked or suspended.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("blocked")
    private Boolean blocked;

    /**
     * personal, or business: an account a company or school manages.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_type")
    private AccountType accountType;

    /**
     * Whether the account has its own profile picture rather than the platform's default. The picture is
     * not returned.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("has_avatar")
    private Boolean hasAvatar;

    /**
     * The first letters of the account holder's first name, as the platform shows them (Life360 shows two
     * and hides the rest). It is evidence only: it does not change the score or the consistency results.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("first_name_prefix")
    private String firstNamePrefix;

    @JsonCreator
    public OnlineAccount(
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("domain") @Nonnull String domain,
            @JsonProperty("identifier") @Nonnull Identifier identifier,
            @JsonProperty("outcome") @Nonnull SignalOutcome outcome,
            @JsonProperty("reason") @Nullable FailureReason reason,
            @JsonProperty("sign_in") @Nullable List<SignIn> signIn,
            @JsonProperty("email_verified") @Nullable Boolean emailVerified,
            @JsonProperty("blocked") @Nullable Boolean blocked,
            @JsonProperty("account_type") @Nullable AccountType accountType,
            @JsonProperty("has_avatar") @Nullable Boolean hasAvatar,
            @JsonProperty("first_name_prefix") @Nullable String firstNamePrefix) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.domain =
                Optional.ofNullable(domain).orElseThrow(() -> new IllegalArgumentException("domain cannot be null"));
        this.identifier = Optional.ofNullable(identifier)
                .orElseThrow(() -> new IllegalArgumentException("identifier cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.reason = reason;
        this.signIn = signIn;
        this.emailVerified = emailVerified;
        this.blocked = blocked;
        this.accountType = accountType;
        this.hasAvatar = hasAvatar;
        this.firstNamePrefix = firstNamePrefix;
    }

    public OnlineAccount(
            @Nonnull String name,
            @Nonnull String domain,
            @Nonnull Identifier identifier,
            @Nonnull SignalOutcome outcome) {
        this(name, domain, identifier, outcome, null, null, null, null, null, null, null);
    }

    /**
     * The platform's name, such as Adobe or GitHub.
     */
    public String name() {
        return this.name;
    }

    /**
     * The platform's domain, lower case: the host its logo is fetched for.
     */
    public String domain() {
        return this.domain;
    }

    public Identifier identifier() {
        return this.identifier;
    }

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    public SignalOutcome outcome() {
        return this.outcome;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public Optional<FailureReason> reason() {
        return Optional.ofNullable(this.reason);
    }

    /**
     * How the account signs in, as the platform reports it, sorted: a password, a Google, Facebook, Apple
     * or Microsoft account, the organisation's single sign-on, or another method.
     */
    public Optional<List<SignIn>> signIn() {
        return Optional.ofNullable(this.signIn);
    }

    /**
     * Whether the platform says the account's email is verified.
     */
    public Optional<Boolean> emailVerified() {
        return Optional.ofNullable(this.emailVerified);
    }

    /**
     * Whether the platform says the account is blocked or suspended.
     */
    public Optional<Boolean> blocked() {
        return Optional.ofNullable(this.blocked);
    }

    /**
     * personal, or business: an account a company or school manages.
     */
    public Optional<AccountType> accountType() {
        return Optional.ofNullable(this.accountType);
    }

    /**
     * Whether the account has its own profile picture rather than the platform's default. The picture is
     * not returned.
     */
    public Optional<Boolean> hasAvatar() {
        return Optional.ofNullable(this.hasAvatar);
    }

    /**
     * The first letters of the account holder's first name, as the platform shows them (Life360 shows two
     * and hides the rest). It is evidence only: it does not change the score or the consistency results.
     */
    public Optional<String> firstNamePrefix() {
        return Optional.ofNullable(this.firstNamePrefix);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The platform's name, such as Adobe or GitHub.
     */
    public OnlineAccount withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * The platform's domain, lower case: the host its logo is fetched for.
     */
    public OnlineAccount withDomain(@Nonnull String domain) {
        this.domain = Utils.checkNotNull(domain, "domain");
        return this;
    }

    public OnlineAccount withIdentifier(@Nonnull Identifier identifier) {
        this.identifier = Utils.checkNotNull(identifier, "identifier");
        return this;
    }

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    public OnlineAccount withOutcome(@Nonnull SignalOutcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public OnlineAccount withReason(@Nullable FailureReason reason) {
        this.reason = reason;
        return this;
    }

    /**
     * How the account signs in, as the platform reports it, sorted: a password, a Google, Facebook, Apple
     * or Microsoft account, the organisation's single sign-on, or another method.
     */
    public OnlineAccount withSignIn(@Nullable List<SignIn> signIn) {
        this.signIn = signIn;
        return this;
    }

    /**
     * Whether the platform says the account's email is verified.
     */
    public OnlineAccount withEmailVerified(@Nullable Boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }

    /**
     * Whether the platform says the account is blocked or suspended.
     */
    public OnlineAccount withBlocked(@Nullable Boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    /**
     * personal, or business: an account a company or school manages.
     */
    public OnlineAccount withAccountType(@Nullable AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    /**
     * Whether the account has its own profile picture rather than the platform's default. The picture is
     * not returned.
     */
    public OnlineAccount withHasAvatar(@Nullable Boolean hasAvatar) {
        this.hasAvatar = hasAvatar;
        return this;
    }

    /**
     * The first letters of the account holder's first name, as the platform shows them (Life360 shows two
     * and hides the rest). It is evidence only: it does not change the score or the consistency results.
     */
    public OnlineAccount withFirstNamePrefix(@Nullable String firstNamePrefix) {
        this.firstNamePrefix = firstNamePrefix;
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
        OnlineAccount other = (OnlineAccount) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.domain, other.domain)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.signIn, other.signIn)
                && Utils.enhancedDeepEquals(this.emailVerified, other.emailVerified)
                && Utils.enhancedDeepEquals(this.blocked, other.blocked)
                && Utils.enhancedDeepEquals(this.accountType, other.accountType)
                && Utils.enhancedDeepEquals(this.hasAvatar, other.hasAvatar)
                && Utils.enhancedDeepEquals(this.firstNamePrefix, other.firstNamePrefix);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                name,
                domain,
                identifier,
                outcome,
                reason,
                signIn,
                emailVerified,
                blocked,
                accountType,
                hasAvatar,
                firstNamePrefix);
    }

    @Override
    public String toString() {
        return Utils.toString(
                OnlineAccount.class,
                "name",
                name,
                "domain",
                domain,
                "identifier",
                identifier,
                "outcome",
                outcome,
                "reason",
                reason,
                "signIn",
                signIn,
                "emailVerified",
                emailVerified,
                "blocked",
                blocked,
                "accountType",
                accountType,
                "hasAvatar",
                hasAvatar,
                "firstNamePrefix",
                firstNamePrefix);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private String domain;

        private Identifier identifier;

        private SignalOutcome outcome;

        private FailureReason reason;

        private List<SignIn> signIn;

        private Boolean emailVerified;

        private Boolean blocked;

        private AccountType accountType;

        private Boolean hasAvatar;

        private String firstNamePrefix;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The platform's name, such as Adobe or GitHub.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * The platform's domain, lower case: the host its logo is fetched for.
         */
        public Builder domain(@Nonnull String domain) {
            this.domain = Utils.checkNotNull(domain, "domain");
            return this;
        }

        public Builder identifier(@Nonnull Identifier identifier) {
            this.identifier = Utils.checkNotNull(identifier, "identifier");
            return this;
        }

        /**
         * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
         * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
         */
        public Builder outcome(@Nonnull SignalOutcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        /**
         * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
         * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
         * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
         */
        public Builder reason(@Nullable FailureReason reason) {
            this.reason = reason;
            return this;
        }

        /**
         * How the account signs in, as the platform reports it, sorted: a password, a Google, Facebook, Apple
         * or Microsoft account, the organisation's single sign-on, or another method.
         */
        public Builder signIn(@Nullable List<SignIn> signIn) {
            this.signIn = signIn;
            return this;
        }

        /**
         * Whether the platform says the account's email is verified.
         */
        public Builder emailVerified(@Nullable Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        /**
         * Whether the platform says the account is blocked or suspended.
         */
        public Builder blocked(@Nullable Boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        /**
         * personal, or business: an account a company or school manages.
         */
        public Builder accountType(@Nullable AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        /**
         * Whether the account has its own profile picture rather than the platform's default. The picture is
         * not returned.
         */
        public Builder hasAvatar(@Nullable Boolean hasAvatar) {
            this.hasAvatar = hasAvatar;
            return this;
        }

        /**
         * The first letters of the account holder's first name, as the platform shows them (Life360 shows two
         * and hides the rest). It is evidence only: it does not change the score or the consistency results.
         */
        public Builder firstNamePrefix(@Nullable String firstNamePrefix) {
            this.firstNamePrefix = firstNamePrefix;
            return this;
        }

        public OnlineAccount build() {
            return new OnlineAccount(
                    name,
                    domain,
                    identifier,
                    outcome,
                    reason,
                    signIn,
                    emailVerified,
                    blocked,
                    accountType,
                    hasAvatar,
                    firstNamePrefix);
        }
    }
}
