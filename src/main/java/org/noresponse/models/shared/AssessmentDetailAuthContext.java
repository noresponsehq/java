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
 * AssessmentDetailAuthContext
 *
 * <p>The identity fields of the auth context the assessment was made with, echoed for the tenant's own
 * correlation: the request's auth_context, or the one a hosted provider hook (POST
 * /hooks/{integration_id}) built, with transport provider_hook and its hook_point. Present exactly
 * when the request carried an auth_context, and the same on every revision of the assessment. Values
 * are as the request or the hook sent them; tokens, timestamps, roles and scopes are not echoed, and
 * the echo does not change the score.
 */
public class AssessmentDetailAuthContext {
    /**
     * The provider slug the context named.
     */
    @JsonProperty("provider")
    private String provider;

    /**
     * The issuer the context named.
     */
    @JsonProperty("issuer")
    private String issuer;

    /**
     * The subject the context named; absent when it named none (a pre_registration hook).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("subject")
    private String subject;

    /**
     * The session identifier the context named, when it named one.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("session_id")
    private String sessionId;

    /**
     * The organization identifier the context named, when it named one.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("organization_id")
    private String organizationId;

    /**
     * The verified flag the context carried. A provider_hook context is always false.
     */
    @JsonProperty("verified")
    private boolean verified;

    /**
     * Where the context was derived: backend (also when the request named no transport), browser, or
     * provider_hook for a hosted provider hook.
     */
    @JsonProperty("transport")
    private Transport transport;

    /**
     * The provider event a provider_hook context was sent for; absent for any other transport.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("hook_point")
    private HookPoint hookPoint;

    @JsonCreator
    public AssessmentDetailAuthContext(
            @JsonProperty("provider") @Nonnull String provider,
            @JsonProperty("issuer") @Nonnull String issuer,
            @JsonProperty("subject") @Nullable String subject,
            @JsonProperty("session_id") @Nullable String sessionId,
            @JsonProperty("organization_id") @Nullable String organizationId,
            @JsonProperty("verified") boolean verified,
            @JsonProperty("transport") @Nonnull Transport transport,
            @JsonProperty("hook_point") @Nullable HookPoint hookPoint) {
        this.provider = Optional.ofNullable(provider)
                .orElseThrow(() -> new IllegalArgumentException("provider cannot be null"));
        this.issuer =
                Optional.ofNullable(issuer).orElseThrow(() -> new IllegalArgumentException("issuer cannot be null"));
        this.subject = subject;
        this.sessionId = sessionId;
        this.organizationId = organizationId;
        this.verified = verified;
        this.transport = Optional.ofNullable(transport)
                .orElseThrow(() -> new IllegalArgumentException("transport cannot be null"));
        this.hookPoint = hookPoint;
    }

    public AssessmentDetailAuthContext(
            @Nonnull String provider, @Nonnull String issuer, boolean verified, @Nonnull Transport transport) {
        this(provider, issuer, null, null, null, verified, transport, null);
    }

    /**
     * The provider slug the context named.
     */
    public String provider() {
        return this.provider;
    }

    /**
     * The issuer the context named.
     */
    public String issuer() {
        return this.issuer;
    }

    /**
     * The subject the context named; absent when it named none (a pre_registration hook).
     */
    public Optional<String> subject() {
        return Optional.ofNullable(this.subject);
    }

    /**
     * The session identifier the context named, when it named one.
     */
    public Optional<String> sessionId() {
        return Optional.ofNullable(this.sessionId);
    }

    /**
     * The organization identifier the context named, when it named one.
     */
    public Optional<String> organizationId() {
        return Optional.ofNullable(this.organizationId);
    }

    /**
     * The verified flag the context carried. A provider_hook context is always false.
     */
    public boolean verified() {
        return this.verified;
    }

    /**
     * Where the context was derived: backend (also when the request named no transport), browser, or
     * provider_hook for a hosted provider hook.
     */
    public Transport transport() {
        return this.transport;
    }

    /**
     * The provider event a provider_hook context was sent for; absent for any other transport.
     */
    public Optional<HookPoint> hookPoint() {
        return Optional.ofNullable(this.hookPoint);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The provider slug the context named.
     */
    public AssessmentDetailAuthContext withProvider(@Nonnull String provider) {
        this.provider = Utils.checkNotNull(provider, "provider");
        return this;
    }

    /**
     * The issuer the context named.
     */
    public AssessmentDetailAuthContext withIssuer(@Nonnull String issuer) {
        this.issuer = Utils.checkNotNull(issuer, "issuer");
        return this;
    }

    /**
     * The subject the context named; absent when it named none (a pre_registration hook).
     */
    public AssessmentDetailAuthContext withSubject(@Nullable String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * The session identifier the context named, when it named one.
     */
    public AssessmentDetailAuthContext withSessionId(@Nullable String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * The organization identifier the context named, when it named one.
     */
    public AssessmentDetailAuthContext withOrganizationId(@Nullable String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    /**
     * The verified flag the context carried. A provider_hook context is always false.
     */
    public AssessmentDetailAuthContext withVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    /**
     * Where the context was derived: backend (also when the request named no transport), browser, or
     * provider_hook for a hosted provider hook.
     */
    public AssessmentDetailAuthContext withTransport(@Nonnull Transport transport) {
        this.transport = Utils.checkNotNull(transport, "transport");
        return this;
    }

    /**
     * The provider event a provider_hook context was sent for; absent for any other transport.
     */
    public AssessmentDetailAuthContext withHookPoint(@Nullable HookPoint hookPoint) {
        this.hookPoint = hookPoint;
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
        AssessmentDetailAuthContext other = (AssessmentDetailAuthContext) o;
        return Utils.enhancedDeepEquals(this.provider, other.provider)
                && Utils.enhancedDeepEquals(this.issuer, other.issuer)
                && Utils.enhancedDeepEquals(this.subject, other.subject)
                && Utils.enhancedDeepEquals(this.sessionId, other.sessionId)
                && Utils.enhancedDeepEquals(this.organizationId, other.organizationId)
                && Utils.enhancedDeepEquals(this.verified, other.verified)
                && Utils.enhancedDeepEquals(this.transport, other.transport)
                && Utils.enhancedDeepEquals(this.hookPoint, other.hookPoint);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                provider, issuer, subject, sessionId, organizationId, verified, transport, hookPoint);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentDetailAuthContext.class,
                "provider",
                provider,
                "issuer",
                issuer,
                "subject",
                subject,
                "sessionId",
                sessionId,
                "organizationId",
                organizationId,
                "verified",
                verified,
                "transport",
                transport,
                "hookPoint",
                hookPoint);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String provider;

        private String issuer;

        private String subject;

        private String sessionId;

        private String organizationId;

        private boolean verified;

        private Transport transport;

        private HookPoint hookPoint;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The provider slug the context named.
         */
        public Builder provider(@Nonnull String provider) {
            this.provider = Utils.checkNotNull(provider, "provider");
            return this;
        }

        /**
         * The issuer the context named.
         */
        public Builder issuer(@Nonnull String issuer) {
            this.issuer = Utils.checkNotNull(issuer, "issuer");
            return this;
        }

        /**
         * The subject the context named; absent when it named none (a pre_registration hook).
         */
        public Builder subject(@Nullable String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * The session identifier the context named, when it named one.
         */
        public Builder sessionId(@Nullable String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        /**
         * The organization identifier the context named, when it named one.
         */
        public Builder organizationId(@Nullable String organizationId) {
            this.organizationId = organizationId;
            return this;
        }

        /**
         * The verified flag the context carried. A provider_hook context is always false.
         */
        public Builder verified(boolean verified) {
            this.verified = verified;
            return this;
        }

        /**
         * Where the context was derived: backend (also when the request named no transport), browser, or
         * provider_hook for a hosted provider hook.
         */
        public Builder transport(@Nonnull Transport transport) {
            this.transport = Utils.checkNotNull(transport, "transport");
            return this;
        }

        /**
         * The provider event a provider_hook context was sent for; absent for any other transport.
         */
        public Builder hookPoint(@Nullable HookPoint hookPoint) {
            this.hookPoint = hookPoint;
            return this;
        }

        public AssessmentDetailAuthContext build() {
            return new AssessmentDetailAuthContext(
                    provider, issuer, subject, sessionId, organizationId, verified, transport, hookPoint);
        }
    }
}
