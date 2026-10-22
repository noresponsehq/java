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
 * Integration
 *
 * <p>A hosted provider integration. Point the provider at url: a WorkOS user registration or
 * authentication Action, or the NoResponse Auth0 Action, which sends secret as a bearer token. The
 * answer allows or denies the event: deny only when the assessment's risk.block is true, and allow on
 * every failure, so a fault on the NoResponse side lets the user through.
 *
 * <p>secret is present only in the answers of POST /v1/integrations and POST
 * /v1/integrations/{integration_id}/rotate for an Auth0 integration, and is not returned again.
 */
public class Integration {
    /**
     * Opaque, server-assigned integration id: int_ and a 26-character Crockford base32 ULID. Another
     * tenant's integration id answers 404.
     */
    @JsonProperty("integration_id")
    private String integrationId;

    /**
     * The authentication provider that calls the endpoint.
     */
    @JsonProperty("platform")
    private IntegrationPlatform platform;

    /**
     * Each hook point at most once.
     */
    @JsonProperty("hook_points")
    private List<IntegrationHookPoint> hookPoints;

    /**
     * The hosted endpoint, https://api.noresponse.org/hooks/{integration_id} in production.
     */
    @JsonProperty("url")
    private String url;

    /**
     * Auth0 only, and only when it is created or rotated: the shared secret the Auth0 Action sends as
     * Authorization: Bearer &lt;secret&gt;. nri_ and 43 unpadded base64url characters (32 random bytes).
     * Store it as an Auth0 Action secret.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("secret")
    private String secret;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * When the secret was last rotated; absent until the first rotation.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("rotated_at")
    private String rotatedAt;

    /**
     * After a rotation, when the replaced secret stops being accepted; absent when no replaced secret is
     * still accepted.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("previous_secret_expires_at")
    private String previousSecretExpiresAt;

    @JsonCreator
    public Integration(
            @JsonProperty("integration_id") @Nonnull String integrationId,
            @JsonProperty("platform") @Nonnull IntegrationPlatform platform,
            @JsonProperty("hook_points") @Nonnull List<IntegrationHookPoint> hookPoints,
            @JsonProperty("url") @Nonnull String url,
            @JsonProperty("secret") @Nullable String secret,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("rotated_at") @Nullable String rotatedAt,
            @JsonProperty("previous_secret_expires_at") @Nullable String previousSecretExpiresAt) {
        this.integrationId = Optional.ofNullable(integrationId)
                .orElseThrow(() -> new IllegalArgumentException("integrationId cannot be null"));
        this.platform = Optional.ofNullable(platform)
                .orElseThrow(() -> new IllegalArgumentException("platform cannot be null"));
        this.hookPoints = Optional.ofNullable(hookPoints)
                .orElseThrow(() -> new IllegalArgumentException("hookPoints cannot be null"));
        this.url = Optional.ofNullable(url).orElseThrow(() -> new IllegalArgumentException("url cannot be null"));
        this.secret = secret;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.rotatedAt = rotatedAt;
        this.previousSecretExpiresAt = previousSecretExpiresAt;
    }

    public Integration(
            @Nonnull String integrationId,
            @Nonnull IntegrationPlatform platform,
            @Nonnull List<IntegrationHookPoint> hookPoints,
            @Nonnull String url,
            @Nonnull String createdAt) {
        this(integrationId, platform, hookPoints, url, null, createdAt, null, null);
    }

    /**
     * Opaque, server-assigned integration id: int_ and a 26-character Crockford base32 ULID. Another
     * tenant's integration id answers 404.
     */
    public String integrationId() {
        return this.integrationId;
    }

    /**
     * The authentication provider that calls the endpoint.
     */
    public IntegrationPlatform platform() {
        return this.platform;
    }

    /**
     * Each hook point at most once.
     */
    public List<IntegrationHookPoint> hookPoints() {
        return this.hookPoints;
    }

    /**
     * The hosted endpoint, https://api.noresponse.org/hooks/{integration_id} in production.
     */
    public String url() {
        return this.url;
    }

    /**
     * Auth0 only, and only when it is created or rotated: the shared secret the Auth0 Action sends as
     * Authorization: Bearer &lt;secret&gt;. nri_ and 43 unpadded base64url characters (32 random bytes).
     * Store it as an Auth0 Action secret.
     */
    public Optional<String> secret() {
        return Optional.ofNullable(this.secret);
    }

    public String createdAt() {
        return this.createdAt;
    }

    /**
     * When the secret was last rotated; absent until the first rotation.
     */
    public Optional<String> rotatedAt() {
        return Optional.ofNullable(this.rotatedAt);
    }

    /**
     * After a rotation, when the replaced secret stops being accepted; absent when no replaced secret is
     * still accepted.
     */
    public Optional<String> previousSecretExpiresAt() {
        return Optional.ofNullable(this.previousSecretExpiresAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned integration id: int_ and a 26-character Crockford base32 ULID. Another
     * tenant's integration id answers 404.
     */
    public Integration withIntegrationId(@Nonnull String integrationId) {
        this.integrationId = Utils.checkNotNull(integrationId, "integrationId");
        return this;
    }

    /**
     * The authentication provider that calls the endpoint.
     */
    public Integration withPlatform(@Nonnull IntegrationPlatform platform) {
        this.platform = Utils.checkNotNull(platform, "platform");
        return this;
    }

    /**
     * Each hook point at most once.
     */
    public Integration withHookPoints(@Nonnull List<IntegrationHookPoint> hookPoints) {
        this.hookPoints = Utils.checkNotNull(hookPoints, "hookPoints");
        return this;
    }

    /**
     * The hosted endpoint, https://api.noresponse.org/hooks/{integration_id} in production.
     */
    public Integration withUrl(@Nonnull String url) {
        this.url = Utils.checkNotNull(url, "url");
        return this;
    }

    /**
     * Auth0 only, and only when it is created or rotated: the shared secret the Auth0 Action sends as
     * Authorization: Bearer &lt;secret&gt;. nri_ and 43 unpadded base64url characters (32 random bytes).
     * Store it as an Auth0 Action secret.
     */
    public Integration withSecret(@Nullable String secret) {
        this.secret = secret;
        return this;
    }

    public Integration withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * When the secret was last rotated; absent until the first rotation.
     */
    public Integration withRotatedAt(@Nullable String rotatedAt) {
        this.rotatedAt = rotatedAt;
        return this;
    }

    /**
     * After a rotation, when the replaced secret stops being accepted; absent when no replaced secret is
     * still accepted.
     */
    public Integration withPreviousSecretExpiresAt(@Nullable String previousSecretExpiresAt) {
        this.previousSecretExpiresAt = previousSecretExpiresAt;
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
        Integration other = (Integration) o;
        return Utils.enhancedDeepEquals(this.integrationId, other.integrationId)
                && Utils.enhancedDeepEquals(this.platform, other.platform)
                && Utils.enhancedDeepEquals(this.hookPoints, other.hookPoints)
                && Utils.enhancedDeepEquals(this.url, other.url)
                && Utils.enhancedDeepEquals(this.secret, other.secret)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.rotatedAt, other.rotatedAt)
                && Utils.enhancedDeepEquals(this.previousSecretExpiresAt, other.previousSecretExpiresAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                integrationId, platform, hookPoints, url, secret, createdAt, rotatedAt, previousSecretExpiresAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Integration.class,
                "integrationId",
                integrationId,
                "platform",
                platform,
                "hookPoints",
                hookPoints,
                "url",
                url,
                "secret",
                secret,
                "createdAt",
                createdAt,
                "rotatedAt",
                rotatedAt,
                "previousSecretExpiresAt",
                previousSecretExpiresAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String integrationId;

        private IntegrationPlatform platform;

        private List<IntegrationHookPoint> hookPoints;

        private String url;

        private String secret;

        private String createdAt;

        private String rotatedAt;

        private String previousSecretExpiresAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned integration id: int_ and a 26-character Crockford base32 ULID. Another
         * tenant's integration id answers 404.
         */
        public Builder integrationId(@Nonnull String integrationId) {
            this.integrationId = Utils.checkNotNull(integrationId, "integrationId");
            return this;
        }

        /**
         * The authentication provider that calls the endpoint.
         */
        public Builder platform(@Nonnull IntegrationPlatform platform) {
            this.platform = Utils.checkNotNull(platform, "platform");
            return this;
        }

        /**
         * Each hook point at most once.
         */
        public Builder hookPoints(@Nonnull List<IntegrationHookPoint> hookPoints) {
            this.hookPoints = Utils.checkNotNull(hookPoints, "hookPoints");
            return this;
        }

        /**
         * The hosted endpoint, https://api.noresponse.org/hooks/{integration_id} in production.
         */
        public Builder url(@Nonnull String url) {
            this.url = Utils.checkNotNull(url, "url");
            return this;
        }

        /**
         * Auth0 only, and only when it is created or rotated: the shared secret the Auth0 Action sends as
         * Authorization: Bearer &lt;secret&gt;. nri_ and 43 unpadded base64url characters (32 random bytes).
         * Store it as an Auth0 Action secret.
         */
        public Builder secret(@Nullable String secret) {
            this.secret = secret;
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * When the secret was last rotated; absent until the first rotation.
         */
        public Builder rotatedAt(@Nullable String rotatedAt) {
            this.rotatedAt = rotatedAt;
            return this;
        }

        /**
         * After a rotation, when the replaced secret stops being accepted; absent when no replaced secret is
         * still accepted.
         */
        public Builder previousSecretExpiresAt(@Nullable String previousSecretExpiresAt) {
            this.previousSecretExpiresAt = previousSecretExpiresAt;
            return this;
        }

        public Integration build() {
            return new Integration(
                    integrationId, platform, hookPoints, url, secret, createdAt, rotatedAt, previousSecretExpiresAt);
        }
    }
}
