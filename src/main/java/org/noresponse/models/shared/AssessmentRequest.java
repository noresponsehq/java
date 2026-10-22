package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AssessmentRequest
 *
 * <p>POST /v1/assessments body. At least one of email, username or phone is required; name and ip only
 * refine an assessment. No field carries a password, hash, token or breach value, and unknown members
 * are refused.
 *
 * <p>The email local part is kept as received and only the domain is lower-cased; the username is
 * compared case-insensitively; the phone is strict E.164 and is assessed asynchronously. The whole
 * body is capped at 32768 bytes. client_context holds bounded hints from the signup client;
 * auth_context holds normalised claims from a session your backend has verified.
 *
 * <p>Raw tokens are refused.
 */
public class AssessmentRequest {
    /**
     * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
     * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
     * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
     * JWT; a hex digest such as a SHA-256 is allowed.
     *
     * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
     * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    /**
     * At least one of email, username or phone. name and ip refine the match and travel beside a lookup
     * identifier; a body with only name or ip is refused with 400.
     */
    @JsonProperty("identifiers")
    private AssessmentRequestIdentifiers identifiers;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("policy_context")
    private AssessmentRequestPolicyContext policyContext;

    /**
     * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
     * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("request_id")
    private String requestId;

    /**
     * Optional tighter budget, 50 to 500 ms, measured from the moment the request reaches NoResponse.
     * Absent means the 500 ms ceiling; a value above it is refused with 400 rather than clamped.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("deadline_ms")
    private Long deadlineMs;

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("response_mode")
    private ResponseMode responseMode;

    /**
     * Optional context observed by your signup client. The server parses these hints into coarse features,
     * leaves raw user_agent and opaque ids out of every answer, and weighs a mismatch with the other
     * evidence.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("client_context")
    private AssessmentRequestClientContext clientContext;

    /**
     * Normalised claims from a session your backend has already verified with WorkOS, Clerk, Auth0 or any
     * OIDC provider. Pass the output of the SDK's auth adapters, not a raw token.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("auth_context")
    private AuthContext authContext;

    /**
     * Optional. When the signup gate issues an API key to the new account, send SHA-256 of that key, so
     * later usage of the key, or a leaked copy, ties back to this assessment. Each SDK has a helper that
     * computes it.
     *
     * <p>Send only the hash: customer keys are high-entropy, so the hash reveals nothing about them. The
     * assessment stores it, and no response, webhook or log carries it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("issued_credential")
    private AssessmentRequestIssuedCredential issuedCredential;

    @JsonCreator
    public AssessmentRequest(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("identifiers") @Nonnull AssessmentRequestIdentifiers identifiers,
            @JsonProperty("policy_context") @Nullable AssessmentRequestPolicyContext policyContext,
            @JsonProperty("request_id") @Nullable String requestId,
            @JsonProperty("deadline_ms") @Nullable Long deadlineMs,
            @JsonProperty("response_mode") @Nullable ResponseMode responseMode,
            @JsonProperty("client_context") @Nullable AssessmentRequestClientContext clientContext,
            @JsonProperty("auth_context") @Nullable AuthContext authContext,
            @JsonProperty("issued_credential") @Nullable AssessmentRequestIssuedCredential issuedCredential) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.identifiers = Optional.ofNullable(identifiers)
                .orElseThrow(() -> new IllegalArgumentException("identifiers cannot be null"));
        this.policyContext = policyContext;
        this.requestId = requestId;
        this.deadlineMs = deadlineMs;
        this.responseMode = responseMode;
        this.clientContext = clientContext;
        this.authContext = authContext;
        this.issuedCredential = issuedCredential;
    }

    public AssessmentRequest(@Nonnull String accountId, @Nonnull AssessmentRequestIdentifiers identifiers) {
        this(accountId, identifiers, null, null, null, null, null, null, null);
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
     * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
     * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
     * JWT; a hex digest such as a SHA-256 is allowed.
     *
     * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
     * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public String accountId() {
        return this.accountId;
    }

    /**
     * At least one of email, username or phone. name and ip refine the match and travel beside a lookup
     * identifier; a body with only name or ip is refused with 400.
     */
    public AssessmentRequestIdentifiers identifiers() {
        return this.identifiers;
    }

    public Optional<AssessmentRequestPolicyContext> policyContext() {
        return Optional.ofNullable(this.policyContext);
    }

    /**
     * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
     * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
     */
    public Optional<String> requestId() {
        return Optional.ofNullable(this.requestId);
    }

    /**
     * Optional tighter budget, 50 to 500 ms, measured from the moment the request reaches NoResponse.
     * Absent means the 500 ms ceiling; a value above it is refused with 400 rather than clamped.
     */
    public Optional<Long> deadlineMs() {
        return Optional.ofNullable(this.deadlineMs);
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public Optional<ResponseMode> responseMode() {
        return Optional.ofNullable(this.responseMode);
    }

    /**
     * Optional context observed by your signup client. The server parses these hints into coarse features,
     * leaves raw user_agent and opaque ids out of every answer, and weighs a mismatch with the other
     * evidence.
     */
    public Optional<AssessmentRequestClientContext> clientContext() {
        return Optional.ofNullable(this.clientContext);
    }

    /**
     * Normalised claims from a session your backend has already verified with WorkOS, Clerk, Auth0 or any
     * OIDC provider. Pass the output of the SDK's auth adapters, not a raw token.
     */
    public Optional<AuthContext> authContext() {
        return Optional.ofNullable(this.authContext);
    }

    /**
     * Optional. When the signup gate issues an API key to the new account, send SHA-256 of that key, so
     * later usage of the key, or a leaked copy, ties back to this assessment. Each SDK has a helper that
     * computes it.
     *
     * <p>Send only the hash: customer keys are high-entropy, so the hash reveals nothing about them. The
     * assessment stores it, and no response, webhook or log carries it.
     */
    public Optional<AssessmentRequestIssuedCredential> issuedCredential() {
        return Optional.ofNullable(this.issuedCredential);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
     * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
     * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
     * JWT; a hex digest such as a SHA-256 is allowed.
     *
     * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
     * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public AssessmentRequest withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * At least one of email, username or phone. name and ip refine the match and travel beside a lookup
     * identifier; a body with only name or ip is refused with 400.
     */
    public AssessmentRequest withIdentifiers(@Nonnull AssessmentRequestIdentifiers identifiers) {
        this.identifiers = Utils.checkNotNull(identifiers, "identifiers");
        return this;
    }

    public AssessmentRequest withPolicyContext(@Nullable AssessmentRequestPolicyContext policyContext) {
        this.policyContext = policyContext;
        return this;
    }

    /**
     * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
     * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
     */
    public AssessmentRequest withRequestId(@Nullable String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Optional tighter budget, 50 to 500 ms, measured from the moment the request reaches NoResponse.
     * Absent means the 500 ms ceiling; a value above it is refused with 400 rather than clamped.
     */
    public AssessmentRequest withDeadlineMs(@Nullable Long deadlineMs) {
        this.deadlineMs = deadlineMs;
        return this;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public AssessmentRequest withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
        return this;
    }

    /**
     * Optional context observed by your signup client. The server parses these hints into coarse features,
     * leaves raw user_agent and opaque ids out of every answer, and weighs a mismatch with the other
     * evidence.
     */
    public AssessmentRequest withClientContext(@Nullable AssessmentRequestClientContext clientContext) {
        this.clientContext = clientContext;
        return this;
    }

    /**
     * Normalised claims from a session your backend has already verified with WorkOS, Clerk, Auth0 or any
     * OIDC provider. Pass the output of the SDK's auth adapters, not a raw token.
     */
    public AssessmentRequest withAuthContext(@Nullable AuthContext authContext) {
        this.authContext = authContext;
        return this;
    }

    /**
     * Optional. When the signup gate issues an API key to the new account, send SHA-256 of that key, so
     * later usage of the key, or a leaked copy, ties back to this assessment. Each SDK has a helper that
     * computes it.
     *
     * <p>Send only the hash: customer keys are high-entropy, so the hash reveals nothing about them. The
     * assessment stores it, and no response, webhook or log carries it.
     */
    public AssessmentRequest withIssuedCredential(@Nullable AssessmentRequestIssuedCredential issuedCredential) {
        this.issuedCredential = issuedCredential;
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
        AssessmentRequest other = (AssessmentRequest) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.identifiers, other.identifiers)
                && Utils.enhancedDeepEquals(this.policyContext, other.policyContext)
                && Utils.enhancedDeepEquals(this.requestId, other.requestId)
                && Utils.enhancedDeepEquals(this.deadlineMs, other.deadlineMs)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode)
                && Utils.enhancedDeepEquals(this.clientContext, other.clientContext)
                && Utils.enhancedDeepEquals(this.authContext, other.authContext)
                && Utils.enhancedDeepEquals(this.issuedCredential, other.issuedCredential);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                accountId,
                identifiers,
                policyContext,
                requestId,
                deadlineMs,
                responseMode,
                clientContext,
                authContext,
                issuedCredential);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentRequest.class,
                "accountId",
                accountId,
                "identifiers",
                identifiers,
                "policyContext",
                policyContext,
                "requestId",
                requestId,
                "deadlineMs",
                deadlineMs,
                "responseMode",
                responseMode,
                "clientContext",
                clientContext,
                "authContext",
                authContext,
                "issuedCredential",
                issuedCredential);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private AssessmentRequestIdentifiers identifiers;

        private AssessmentRequestPolicyContext policyContext;

        private String requestId;

        private Long deadlineMs;

        private ResponseMode responseMode;

        private AssessmentRequestClientContext clientContext;

        private AuthContext authContext;

        private AssessmentRequestIssuedCredential issuedCredential;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
         * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
         * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
         * JWT; a hex digest such as a SHA-256 is allowed.
         *
         * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
         * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        /**
         * At least one of email, username or phone. name and ip refine the match and travel beside a lookup
         * identifier; a body with only name or ip is refused with 400.
         */
        public Builder identifiers(@Nonnull AssessmentRequestIdentifiers identifiers) {
            this.identifiers = Utils.checkNotNull(identifiers, "identifiers");
            return this;
        }

        public Builder policyContext(@Nullable AssessmentRequestPolicyContext policyContext) {
            this.policyContext = policyContext;
            return this;
        }

        /**
         * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
         * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
         */
        public Builder requestId(@Nullable String requestId) {
            this.requestId = requestId;
            return this;
        }

        /**
         * Optional tighter budget, 50 to 500 ms, measured from the moment the request reaches NoResponse.
         * Absent means the 500 ms ceiling; a value above it is refused with 400 rather than clamped.
         */
        public Builder deadlineMs(@Nullable Long deadlineMs) {
            this.deadlineMs = deadlineMs;
            return this;
        }

        /**
         * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
         * the key has detailed scope. There is no raw mode.
         */
        public Builder responseMode(@Nullable ResponseMode responseMode) {
            this.responseMode = responseMode;
            return this;
        }

        /**
         * Optional context observed by your signup client. The server parses these hints into coarse features,
         * leaves raw user_agent and opaque ids out of every answer, and weighs a mismatch with the other
         * evidence.
         */
        public Builder clientContext(@Nullable AssessmentRequestClientContext clientContext) {
            this.clientContext = clientContext;
            return this;
        }

        /**
         * Normalised claims from a session your backend has already verified with WorkOS, Clerk, Auth0 or any
         * OIDC provider. Pass the output of the SDK's auth adapters, not a raw token.
         */
        public Builder authContext(@Nullable AuthContext authContext) {
            this.authContext = authContext;
            return this;
        }

        /**
         * Optional. When the signup gate issues an API key to the new account, send SHA-256 of that key, so
         * later usage of the key, or a leaked copy, ties back to this assessment. Each SDK has a helper that
         * computes it.
         *
         * <p>Send only the hash: customer keys are high-entropy, so the hash reveals nothing about them. The
         * assessment stores it, and no response, webhook or log carries it.
         */
        public Builder issuedCredential(@Nullable AssessmentRequestIssuedCredential issuedCredential) {
            this.issuedCredential = issuedCredential;
            return this;
        }

        public AssessmentRequest build() {
            return new AssessmentRequest(
                    accountId,
                    identifiers,
                    policyContext,
                    requestId,
                    deadlineMs,
                    responseMode,
                    clientContext,
                    authContext,
                    issuedCredential);
        }
    }
}
