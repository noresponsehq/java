package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookEndpoint
 *
 * <p>One of the workspace's registered webhook endpoints. Signing secrets are not part of it: a secret is
 * shown once, when it is issued (POST /v1/webhooks and POST /v1/webhooks/{endpoint_id}/rotate).
 */
public class WebhookEndpoint {
    /**
     * Opaque id of the endpoint registration.
     */
    @JsonProperty("endpoint_id")
    private String endpointId;

    /**
     * The destination URL, as registered.
     */
    @JsonProperty("url")
    private String url;

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    @JsonProperty("state")
    private WebhookEndpointState state;

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    @JsonProperty("response_mode")
    private ResponseMode responseMode;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * When the endpoint last echoed a challenge. Absent until it first does.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("verified_at")
    private String verifiedAt;

    /**
     * The keys a receiver may see in NR-Webhook-Key-Id, oldest first. The last one signs every new
     * delivery; an older one keeps verifying until its expires_at.
     */
    @JsonProperty("signing_keys")
    private List<SigningKey> signingKeys;

    @JsonCreator
    public WebhookEndpoint(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("url") @Nonnull String url,
            @JsonProperty("state") @Nonnull WebhookEndpointState state,
            @JsonProperty("response_mode") @Nonnull ResponseMode responseMode,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("verified_at") @Nullable String verifiedAt,
            @JsonProperty("signing_keys") @Nonnull List<SigningKey> signingKeys) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.url = Optional.ofNullable(url).orElseThrow(() -> new IllegalArgumentException("url cannot be null"));
        this.state = Optional.ofNullable(state).orElseThrow(() -> new IllegalArgumentException("state cannot be null"));
        this.responseMode = Optional.ofNullable(responseMode)
                .orElseThrow(() -> new IllegalArgumentException("responseMode cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.verifiedAt = verifiedAt;
        this.signingKeys = Optional.ofNullable(signingKeys)
                .orElseThrow(() -> new IllegalArgumentException("signingKeys cannot be null"));
    }

    public WebhookEndpoint(
            @Nonnull String endpointId,
            @Nonnull String url,
            @Nonnull WebhookEndpointState state,
            @Nonnull ResponseMode responseMode,
            @Nonnull String createdAt,
            @Nonnull List<SigningKey> signingKeys) {
        this(endpointId, url, state, responseMode, createdAt, null, signingKeys);
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * The destination URL, as registered.
     */
    public String url() {
        return this.url;
    }

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    public WebhookEndpointState state() {
        return this.state;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public ResponseMode responseMode() {
        return this.responseMode;
    }

    public String createdAt() {
        return this.createdAt;
    }

    /**
     * When the endpoint last echoed a challenge. Absent until it first does.
     */
    public Optional<String> verifiedAt() {
        return Optional.ofNullable(this.verifiedAt);
    }

    /**
     * The keys a receiver may see in NR-Webhook-Key-Id, oldest first. The last one signs every new
     * delivery; an older one keeps verifying until its expires_at.
     */
    public List<SigningKey> signingKeys() {
        return this.signingKeys;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public WebhookEndpoint withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * The destination URL, as registered.
     */
    public WebhookEndpoint withUrl(@Nonnull String url) {
        this.url = Utils.checkNotNull(url, "url");
        return this;
    }

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    public WebhookEndpoint withState(@Nonnull WebhookEndpointState state) {
        this.state = Utils.checkNotNull(state, "state");
        return this;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public WebhookEndpoint withResponseMode(@Nonnull ResponseMode responseMode) {
        this.responseMode = Utils.checkNotNull(responseMode, "responseMode");
        return this;
    }

    public WebhookEndpoint withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * When the endpoint last echoed a challenge. Absent until it first does.
     */
    public WebhookEndpoint withVerifiedAt(@Nullable String verifiedAt) {
        this.verifiedAt = verifiedAt;
        return this;
    }

    /**
     * The keys a receiver may see in NR-Webhook-Key-Id, oldest first. The last one signs every new
     * delivery; an older one keeps verifying until its expires_at.
     */
    public WebhookEndpoint withSigningKeys(@Nonnull List<SigningKey> signingKeys) {
        this.signingKeys = Utils.checkNotNull(signingKeys, "signingKeys");
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
        WebhookEndpoint other = (WebhookEndpoint) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.url, other.url)
                && Utils.enhancedDeepEquals(this.state, other.state)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.verifiedAt, other.verifiedAt)
                && Utils.enhancedDeepEquals(this.signingKeys, other.signingKeys);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, url, state, responseMode, createdAt, verifiedAt, signingKeys);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookEndpoint.class,
                "endpointId",
                endpointId,
                "url",
                url,
                "state",
                state,
                "responseMode",
                responseMode,
                "createdAt",
                createdAt,
                "verifiedAt",
                verifiedAt,
                "signingKeys",
                signingKeys);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private String url;

        private WebhookEndpointState state;

        private ResponseMode responseMode;

        private String createdAt;

        private String verifiedAt;

        private List<SigningKey> signingKeys;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque id of the endpoint registration.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        /**
         * The destination URL, as registered.
         */
        public Builder url(@Nonnull String url) {
            this.url = Utils.checkNotNull(url, "url");
            return this;
        }

        /**
         * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
         * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
         * hours of continuous delivery failure, and verified again by the same challenge.
         */
        public Builder state(@Nonnull WebhookEndpointState state) {
            this.state = Utils.checkNotNull(state, "state");
            return this;
        }

        /**
         * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
         * the key has detailed scope. There is no raw mode.
         */
        public Builder responseMode(@Nonnull ResponseMode responseMode) {
            this.responseMode = Utils.checkNotNull(responseMode, "responseMode");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * When the endpoint last echoed a challenge. Absent until it first does.
         */
        public Builder verifiedAt(@Nullable String verifiedAt) {
            this.verifiedAt = verifiedAt;
            return this;
        }

        /**
         * The keys a receiver may see in NR-Webhook-Key-Id, oldest first. The last one signs every new
         * delivery; an older one keeps verifying until its expires_at.
         */
        public Builder signingKeys(@Nonnull List<SigningKey> signingKeys) {
            this.signingKeys = Utils.checkNotNull(signingKeys, "signingKeys");
            return this;
        }

        public WebhookEndpoint build() {
            return new WebhookEndpoint(endpointId, url, state, responseMode, createdAt, verifiedAt, signingKeys);
        }
    }
}
