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
 * AuthContext
 *
 * <p>Normalised claims from a session your backend has already verified with WorkOS, Clerk, Auth0 or any
 * OIDC provider. Pass the output of the SDK's auth adapters, not a raw token.
 */
public class AuthContext {
    /**
     * Lowercase provider slug. WorkOS, Clerk and Auth0 have first-class adapters; every other provider
     * uses a customer-specific OIDC slug. Provider brand is metadata; it does not change risk by itself.
     */
    @JsonProperty("provider")
    private String provider;

    /**
     * The verified token issuer, normally the provider's HTTPS issuer URL.
     */
    @JsonProperty("issuer")
    private String issuer;

    /**
     * The verified audience/resource identifier used by the customer's API. Use audiences when the
     * provider emits multiple values.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("audience")
    private String audience;

    /**
     * All verified audience/resource identifiers from an OAuth or OIDC token. The first value may also be
     * copied to audience for simple integrations.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("audiences")
    private List<String> audiences;

    /**
     * The verified stable subject identifier (`sub`), opaque to NoResponse. Required, except from a
     * provider hook at pre_registration, where no user exists yet.
     */
    @JsonProperty("subject")
    private String subject;

    /**
     * The verified session identifier (`sid` or provider equivalent).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("session_id")
    private String sessionId;

    /**
     * The active organization or workspace identifier, when the provider issued one.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("organization_id")
    private String organizationId;

    /**
     * The provider workspace identifier, when distinct from organization_id.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("tenant_id")
    private String tenantId;

    /**
     * The verified token identifier (`jti`), used for replay and rotation correlation.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("token_id")
    private String tokenId;

    /**
     * Verified token issue time (`iat`).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("issued_at")
    private String issuedAt;

    /**
     * Verified token expiry (`exp`).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * Creation time of the provider session from the verified backend session record (for example Clerk
     * Session.createdAt or WorkOS Session.created_at). This is separate from token issuance and auth_time.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("session_created_at")
    private String sessionCreatedAt;

    /**
     * Verified end-user authentication time (`auth_time`), when present.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("auth_time")
    private String authTime;

    /**
     * Account creation time from the customer's verified provider record, when available.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_created_at")
    private String accountCreatedAt;

    /**
     * Whether the customer's provider asserts that the email identifier was verified.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email_verified")
    private Boolean emailVerified;

    /**
     * Whether the customer's provider asserts that the phone identifier was verified.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone_verified")
    private Boolean phoneVerified;

    /**
     * Provider or OIDC assurance level, when the customer has mapped one.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assurance_level")
    private AssuranceLevel assuranceLevel;

    /**
     * Whether the verified session is acting on behalf of another subject.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("impersonated")
    private Boolean impersonated;

    /**
     * Where the context was derived: backend (also when the request named no transport), browser, or
     * provider_hook for a hosted provider hook.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("transport")
    private Transport transport;

    /**
     * The provider event a provider_hook context was sent for; absent for any other transport.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("hook_point")
    private HookPoint hookPoint;

    /**
     * Whether the customer completed signature, issuer, audience and expiry validation before calling
     * NoResponse. False contexts are accepted only as weak hints.
     */
    @JsonProperty("verified")
    private boolean verified;

    /**
     * Normalized multi-factor evidence from a verified provider session.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("mfa")
    private AuthContextMfa mfa;

    /**
     * Bounded role slugs from the verified provider; roles do not change risk by themselves.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("roles")
    private List<String> roles;

    /**
     * Bounded scopes from the verified access token; scopes do not change risk by themselves.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("scopes")
    private List<String> scopes;

    @JsonCreator
    public AuthContext(
            @JsonProperty("provider") @Nonnull String provider,
            @JsonProperty("issuer") @Nonnull String issuer,
            @JsonProperty("audience") @Nullable String audience,
            @JsonProperty("audiences") @Nullable List<String> audiences,
            @JsonProperty("subject") @Nonnull String subject,
            @JsonProperty("session_id") @Nullable String sessionId,
            @JsonProperty("organization_id") @Nullable String organizationId,
            @JsonProperty("tenant_id") @Nullable String tenantId,
            @JsonProperty("token_id") @Nullable String tokenId,
            @JsonProperty("issued_at") @Nullable String issuedAt,
            @JsonProperty("expires_at") @Nullable String expiresAt,
            @JsonProperty("session_created_at") @Nullable String sessionCreatedAt,
            @JsonProperty("auth_time") @Nullable String authTime,
            @JsonProperty("account_created_at") @Nullable String accountCreatedAt,
            @JsonProperty("email_verified") @Nullable Boolean emailVerified,
            @JsonProperty("phone_verified") @Nullable Boolean phoneVerified,
            @JsonProperty("assurance_level") @Nullable AssuranceLevel assuranceLevel,
            @JsonProperty("impersonated") @Nullable Boolean impersonated,
            @JsonProperty("transport") @Nullable Transport transport,
            @JsonProperty("hook_point") @Nullable HookPoint hookPoint,
            @JsonProperty("verified") boolean verified,
            @JsonProperty("mfa") @Nullable AuthContextMfa mfa,
            @JsonProperty("roles") @Nullable List<String> roles,
            @JsonProperty("scopes") @Nullable List<String> scopes) {
        this.provider = Optional.ofNullable(provider)
                .orElseThrow(() -> new IllegalArgumentException("provider cannot be null"));
        this.issuer =
                Optional.ofNullable(issuer).orElseThrow(() -> new IllegalArgumentException("issuer cannot be null"));
        this.audience = audience;
        this.audiences = audiences;
        this.subject =
                Optional.ofNullable(subject).orElseThrow(() -> new IllegalArgumentException("subject cannot be null"));
        this.sessionId = sessionId;
        this.organizationId = organizationId;
        this.tenantId = tenantId;
        this.tokenId = tokenId;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.sessionCreatedAt = sessionCreatedAt;
        this.authTime = authTime;
        this.accountCreatedAt = accountCreatedAt;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.assuranceLevel = assuranceLevel;
        this.impersonated = impersonated;
        this.transport = transport;
        this.hookPoint = hookPoint;
        this.verified = verified;
        this.mfa = mfa;
        this.roles = roles;
        this.scopes = scopes;
    }

    public AuthContext(@Nonnull String provider, @Nonnull String issuer, @Nonnull String subject, boolean verified) {
        this(
                provider,
                issuer,
                null,
                null,
                subject,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                verified,
                null,
                null,
                null);
    }

    /**
     * Lowercase provider slug. WorkOS, Clerk and Auth0 have first-class adapters; every other provider
     * uses a customer-specific OIDC slug. Provider brand is metadata; it does not change risk by itself.
     */
    public String provider() {
        return this.provider;
    }

    /**
     * The verified token issuer, normally the provider's HTTPS issuer URL.
     */
    public String issuer() {
        return this.issuer;
    }

    /**
     * The verified audience/resource identifier used by the customer's API. Use audiences when the
     * provider emits multiple values.
     */
    public Optional<String> audience() {
        return Optional.ofNullable(this.audience);
    }

    /**
     * All verified audience/resource identifiers from an OAuth or OIDC token. The first value may also be
     * copied to audience for simple integrations.
     */
    public Optional<List<String>> audiences() {
        return Optional.ofNullable(this.audiences);
    }

    /**
     * The verified stable subject identifier (`sub`), opaque to NoResponse. Required, except from a
     * provider hook at pre_registration, where no user exists yet.
     */
    public String subject() {
        return this.subject;
    }

    /**
     * The verified session identifier (`sid` or provider equivalent).
     */
    public Optional<String> sessionId() {
        return Optional.ofNullable(this.sessionId);
    }

    /**
     * The active organization or workspace identifier, when the provider issued one.
     */
    public Optional<String> organizationId() {
        return Optional.ofNullable(this.organizationId);
    }

    /**
     * The provider workspace identifier, when distinct from organization_id.
     */
    public Optional<String> tenantId() {
        return Optional.ofNullable(this.tenantId);
    }

    /**
     * The verified token identifier (`jti`), used for replay and rotation correlation.
     */
    public Optional<String> tokenId() {
        return Optional.ofNullable(this.tokenId);
    }

    /**
     * Verified token issue time (`iat`).
     */
    public Optional<String> issuedAt() {
        return Optional.ofNullable(this.issuedAt);
    }

    /**
     * Verified token expiry (`exp`).
     */
    public Optional<String> expiresAt() {
        return Optional.ofNullable(this.expiresAt);
    }

    /**
     * Creation time of the provider session from the verified backend session record (for example Clerk
     * Session.createdAt or WorkOS Session.created_at). This is separate from token issuance and auth_time.
     */
    public Optional<String> sessionCreatedAt() {
        return Optional.ofNullable(this.sessionCreatedAt);
    }

    /**
     * Verified end-user authentication time (`auth_time`), when present.
     */
    public Optional<String> authTime() {
        return Optional.ofNullable(this.authTime);
    }

    /**
     * Account creation time from the customer's verified provider record, when available.
     */
    public Optional<String> accountCreatedAt() {
        return Optional.ofNullable(this.accountCreatedAt);
    }

    /**
     * Whether the customer's provider asserts that the email identifier was verified.
     */
    public Optional<Boolean> emailVerified() {
        return Optional.ofNullable(this.emailVerified);
    }

    /**
     * Whether the customer's provider asserts that the phone identifier was verified.
     */
    public Optional<Boolean> phoneVerified() {
        return Optional.ofNullable(this.phoneVerified);
    }

    /**
     * Provider or OIDC assurance level, when the customer has mapped one.
     */
    public Optional<AssuranceLevel> assuranceLevel() {
        return Optional.ofNullable(this.assuranceLevel);
    }

    /**
     * Whether the verified session is acting on behalf of another subject.
     */
    public Optional<Boolean> impersonated() {
        return Optional.ofNullable(this.impersonated);
    }

    /**
     * Where the context was derived: backend (also when the request named no transport), browser, or
     * provider_hook for a hosted provider hook.
     */
    public Optional<Transport> transport() {
        return Optional.ofNullable(this.transport);
    }

    /**
     * The provider event a provider_hook context was sent for; absent for any other transport.
     */
    public Optional<HookPoint> hookPoint() {
        return Optional.ofNullable(this.hookPoint);
    }

    /**
     * Whether the customer completed signature, issuer, audience and expiry validation before calling
     * NoResponse. False contexts are accepted only as weak hints.
     */
    public boolean verified() {
        return this.verified;
    }

    /**
     * Normalized multi-factor evidence from a verified provider session.
     */
    public Optional<AuthContextMfa> mfa() {
        return Optional.ofNullable(this.mfa);
    }

    /**
     * Bounded role slugs from the verified provider; roles do not change risk by themselves.
     */
    public Optional<List<String>> roles() {
        return Optional.ofNullable(this.roles);
    }

    /**
     * Bounded scopes from the verified access token; scopes do not change risk by themselves.
     */
    public Optional<List<String>> scopes() {
        return Optional.ofNullable(this.scopes);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Lowercase provider slug. WorkOS, Clerk and Auth0 have first-class adapters; every other provider
     * uses a customer-specific OIDC slug. Provider brand is metadata; it does not change risk by itself.
     */
    public AuthContext withProvider(@Nonnull String provider) {
        this.provider = Utils.checkNotNull(provider, "provider");
        return this;
    }

    /**
     * The verified token issuer, normally the provider's HTTPS issuer URL.
     */
    public AuthContext withIssuer(@Nonnull String issuer) {
        this.issuer = Utils.checkNotNull(issuer, "issuer");
        return this;
    }

    /**
     * The verified audience/resource identifier used by the customer's API. Use audiences when the
     * provider emits multiple values.
     */
    public AuthContext withAudience(@Nullable String audience) {
        this.audience = audience;
        return this;
    }

    /**
     * All verified audience/resource identifiers from an OAuth or OIDC token. The first value may also be
     * copied to audience for simple integrations.
     */
    public AuthContext withAudiences(@Nullable List<String> audiences) {
        this.audiences = audiences;
        return this;
    }

    /**
     * The verified stable subject identifier (`sub`), opaque to NoResponse. Required, except from a
     * provider hook at pre_registration, where no user exists yet.
     */
    public AuthContext withSubject(@Nonnull String subject) {
        this.subject = Utils.checkNotNull(subject, "subject");
        return this;
    }

    /**
     * The verified session identifier (`sid` or provider equivalent).
     */
    public AuthContext withSessionId(@Nullable String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * The active organization or workspace identifier, when the provider issued one.
     */
    public AuthContext withOrganizationId(@Nullable String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    /**
     * The provider workspace identifier, when distinct from organization_id.
     */
    public AuthContext withTenantId(@Nullable String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * The verified token identifier (`jti`), used for replay and rotation correlation.
     */
    public AuthContext withTokenId(@Nullable String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    /**
     * Verified token issue time (`iat`).
     */
    public AuthContext withIssuedAt(@Nullable String issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    /**
     * Verified token expiry (`exp`).
     */
    public AuthContext withExpiresAt(@Nullable String expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    /**
     * Creation time of the provider session from the verified backend session record (for example Clerk
     * Session.createdAt or WorkOS Session.created_at). This is separate from token issuance and auth_time.
     */
    public AuthContext withSessionCreatedAt(@Nullable String sessionCreatedAt) {
        this.sessionCreatedAt = sessionCreatedAt;
        return this;
    }

    /**
     * Verified end-user authentication time (`auth_time`), when present.
     */
    public AuthContext withAuthTime(@Nullable String authTime) {
        this.authTime = authTime;
        return this;
    }

    /**
     * Account creation time from the customer's verified provider record, when available.
     */
    public AuthContext withAccountCreatedAt(@Nullable String accountCreatedAt) {
        this.accountCreatedAt = accountCreatedAt;
        return this;
    }

    /**
     * Whether the customer's provider asserts that the email identifier was verified.
     */
    public AuthContext withEmailVerified(@Nullable Boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }

    /**
     * Whether the customer's provider asserts that the phone identifier was verified.
     */
    public AuthContext withPhoneVerified(@Nullable Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
        return this;
    }

    /**
     * Provider or OIDC assurance level, when the customer has mapped one.
     */
    public AuthContext withAssuranceLevel(@Nullable AssuranceLevel assuranceLevel) {
        this.assuranceLevel = assuranceLevel;
        return this;
    }

    /**
     * Whether the verified session is acting on behalf of another subject.
     */
    public AuthContext withImpersonated(@Nullable Boolean impersonated) {
        this.impersonated = impersonated;
        return this;
    }

    /**
     * Where the context was derived: backend (also when the request named no transport), browser, or
     * provider_hook for a hosted provider hook.
     */
    public AuthContext withTransport(@Nullable Transport transport) {
        this.transport = transport;
        return this;
    }

    /**
     * The provider event a provider_hook context was sent for; absent for any other transport.
     */
    public AuthContext withHookPoint(@Nullable HookPoint hookPoint) {
        this.hookPoint = hookPoint;
        return this;
    }

    /**
     * Whether the customer completed signature, issuer, audience and expiry validation before calling
     * NoResponse. False contexts are accepted only as weak hints.
     */
    public AuthContext withVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    /**
     * Normalized multi-factor evidence from a verified provider session.
     */
    public AuthContext withMfa(@Nullable AuthContextMfa mfa) {
        this.mfa = mfa;
        return this;
    }

    /**
     * Bounded role slugs from the verified provider; roles do not change risk by themselves.
     */
    public AuthContext withRoles(@Nullable List<String> roles) {
        this.roles = roles;
        return this;
    }

    /**
     * Bounded scopes from the verified access token; scopes do not change risk by themselves.
     */
    public AuthContext withScopes(@Nullable List<String> scopes) {
        this.scopes = scopes;
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
        AuthContext other = (AuthContext) o;
        return Utils.enhancedDeepEquals(this.provider, other.provider)
                && Utils.enhancedDeepEquals(this.issuer, other.issuer)
                && Utils.enhancedDeepEquals(this.audience, other.audience)
                && Utils.enhancedDeepEquals(this.audiences, other.audiences)
                && Utils.enhancedDeepEquals(this.subject, other.subject)
                && Utils.enhancedDeepEquals(this.sessionId, other.sessionId)
                && Utils.enhancedDeepEquals(this.organizationId, other.organizationId)
                && Utils.enhancedDeepEquals(this.tenantId, other.tenantId)
                && Utils.enhancedDeepEquals(this.tokenId, other.tokenId)
                && Utils.enhancedDeepEquals(this.issuedAt, other.issuedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.sessionCreatedAt, other.sessionCreatedAt)
                && Utils.enhancedDeepEquals(this.authTime, other.authTime)
                && Utils.enhancedDeepEquals(this.accountCreatedAt, other.accountCreatedAt)
                && Utils.enhancedDeepEquals(this.emailVerified, other.emailVerified)
                && Utils.enhancedDeepEquals(this.phoneVerified, other.phoneVerified)
                && Utils.enhancedDeepEquals(this.assuranceLevel, other.assuranceLevel)
                && Utils.enhancedDeepEquals(this.impersonated, other.impersonated)
                && Utils.enhancedDeepEquals(this.transport, other.transport)
                && Utils.enhancedDeepEquals(this.hookPoint, other.hookPoint)
                && Utils.enhancedDeepEquals(this.verified, other.verified)
                && Utils.enhancedDeepEquals(this.mfa, other.mfa)
                && Utils.enhancedDeepEquals(this.roles, other.roles)
                && Utils.enhancedDeepEquals(this.scopes, other.scopes);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                provider,
                issuer,
                audience,
                audiences,
                subject,
                sessionId,
                organizationId,
                tenantId,
                tokenId,
                issuedAt,
                expiresAt,
                sessionCreatedAt,
                authTime,
                accountCreatedAt,
                emailVerified,
                phoneVerified,
                assuranceLevel,
                impersonated,
                transport,
                hookPoint,
                verified,
                mfa,
                roles,
                scopes);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AuthContext.class,
                "provider",
                provider,
                "issuer",
                issuer,
                "audience",
                audience,
                "audiences",
                audiences,
                "subject",
                subject,
                "sessionId",
                sessionId,
                "organizationId",
                organizationId,
                "tenantId",
                tenantId,
                "tokenId",
                tokenId,
                "issuedAt",
                issuedAt,
                "expiresAt",
                expiresAt,
                "sessionCreatedAt",
                sessionCreatedAt,
                "authTime",
                authTime,
                "accountCreatedAt",
                accountCreatedAt,
                "emailVerified",
                emailVerified,
                "phoneVerified",
                phoneVerified,
                "assuranceLevel",
                assuranceLevel,
                "impersonated",
                impersonated,
                "transport",
                transport,
                "hookPoint",
                hookPoint,
                "verified",
                verified,
                "mfa",
                mfa,
                "roles",
                roles,
                "scopes",
                scopes);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String provider;

        private String issuer;

        private String audience;

        private List<String> audiences;

        private String subject;

        private String sessionId;

        private String organizationId;

        private String tenantId;

        private String tokenId;

        private String issuedAt;

        private String expiresAt;

        private String sessionCreatedAt;

        private String authTime;

        private String accountCreatedAt;

        private Boolean emailVerified;

        private Boolean phoneVerified;

        private AssuranceLevel assuranceLevel;

        private Boolean impersonated;

        private Transport transport;

        private HookPoint hookPoint;

        private boolean verified;

        private AuthContextMfa mfa;

        private List<String> roles;

        private List<String> scopes;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Lowercase provider slug. WorkOS, Clerk and Auth0 have first-class adapters; every other provider
         * uses a customer-specific OIDC slug. Provider brand is metadata; it does not change risk by itself.
         */
        public Builder provider(@Nonnull String provider) {
            this.provider = Utils.checkNotNull(provider, "provider");
            return this;
        }

        /**
         * The verified token issuer, normally the provider's HTTPS issuer URL.
         */
        public Builder issuer(@Nonnull String issuer) {
            this.issuer = Utils.checkNotNull(issuer, "issuer");
            return this;
        }

        /**
         * The verified audience/resource identifier used by the customer's API. Use audiences when the
         * provider emits multiple values.
         */
        public Builder audience(@Nullable String audience) {
            this.audience = audience;
            return this;
        }

        /**
         * All verified audience/resource identifiers from an OAuth or OIDC token. The first value may also be
         * copied to audience for simple integrations.
         */
        public Builder audiences(@Nullable List<String> audiences) {
            this.audiences = audiences;
            return this;
        }

        /**
         * The verified stable subject identifier (`sub`), opaque to NoResponse. Required, except from a
         * provider hook at pre_registration, where no user exists yet.
         */
        public Builder subject(@Nonnull String subject) {
            this.subject = Utils.checkNotNull(subject, "subject");
            return this;
        }

        /**
         * The verified session identifier (`sid` or provider equivalent).
         */
        public Builder sessionId(@Nullable String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        /**
         * The active organization or workspace identifier, when the provider issued one.
         */
        public Builder organizationId(@Nullable String organizationId) {
            this.organizationId = organizationId;
            return this;
        }

        /**
         * The provider workspace identifier, when distinct from organization_id.
         */
        public Builder tenantId(@Nullable String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /**
         * The verified token identifier (`jti`), used for replay and rotation correlation.
         */
        public Builder tokenId(@Nullable String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        /**
         * Verified token issue time (`iat`).
         */
        public Builder issuedAt(@Nullable String issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        /**
         * Verified token expiry (`exp`).
         */
        public Builder expiresAt(@Nullable String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        /**
         * Creation time of the provider session from the verified backend session record (for example Clerk
         * Session.createdAt or WorkOS Session.created_at). This is separate from token issuance and auth_time.
         */
        public Builder sessionCreatedAt(@Nullable String sessionCreatedAt) {
            this.sessionCreatedAt = sessionCreatedAt;
            return this;
        }

        /**
         * Verified end-user authentication time (`auth_time`), when present.
         */
        public Builder authTime(@Nullable String authTime) {
            this.authTime = authTime;
            return this;
        }

        /**
         * Account creation time from the customer's verified provider record, when available.
         */
        public Builder accountCreatedAt(@Nullable String accountCreatedAt) {
            this.accountCreatedAt = accountCreatedAt;
            return this;
        }

        /**
         * Whether the customer's provider asserts that the email identifier was verified.
         */
        public Builder emailVerified(@Nullable Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        /**
         * Whether the customer's provider asserts that the phone identifier was verified.
         */
        public Builder phoneVerified(@Nullable Boolean phoneVerified) {
            this.phoneVerified = phoneVerified;
            return this;
        }

        /**
         * Provider or OIDC assurance level, when the customer has mapped one.
         */
        public Builder assuranceLevel(@Nullable AssuranceLevel assuranceLevel) {
            this.assuranceLevel = assuranceLevel;
            return this;
        }

        /**
         * Whether the verified session is acting on behalf of another subject.
         */
        public Builder impersonated(@Nullable Boolean impersonated) {
            this.impersonated = impersonated;
            return this;
        }

        /**
         * Where the context was derived: backend (also when the request named no transport), browser, or
         * provider_hook for a hosted provider hook.
         */
        public Builder transport(@Nullable Transport transport) {
            this.transport = transport;
            return this;
        }

        /**
         * The provider event a provider_hook context was sent for; absent for any other transport.
         */
        public Builder hookPoint(@Nullable HookPoint hookPoint) {
            this.hookPoint = hookPoint;
            return this;
        }

        /**
         * Whether the customer completed signature, issuer, audience and expiry validation before calling
         * NoResponse. False contexts are accepted only as weak hints.
         */
        public Builder verified(boolean verified) {
            this.verified = verified;
            return this;
        }

        /**
         * Normalized multi-factor evidence from a verified provider session.
         */
        public Builder mfa(@Nullable AuthContextMfa mfa) {
            this.mfa = mfa;
            return this;
        }

        /**
         * Bounded role slugs from the verified provider; roles do not change risk by themselves.
         */
        public Builder roles(@Nullable List<String> roles) {
            this.roles = roles;
            return this;
        }

        /**
         * Bounded scopes from the verified access token; scopes do not change risk by themselves.
         */
        public Builder scopes(@Nullable List<String> scopes) {
            this.scopes = scopes;
            return this;
        }

        public AuthContext build() {
            return new AuthContext(
                    provider,
                    issuer,
                    audience,
                    audiences,
                    subject,
                    sessionId,
                    organizationId,
                    tenantId,
                    tokenId,
                    issuedAt,
                    expiresAt,
                    sessionCreatedAt,
                    authTime,
                    accountCreatedAt,
                    emailVerified,
                    phoneVerified,
                    assuranceLevel,
                    impersonated,
                    transport,
                    hookPoint,
                    verified,
                    mfa,
                    roles,
                    scopes);
        }
    }
}
