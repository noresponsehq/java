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
 * IntegrationRequest
 *
 * <p>POST /v1/integrations body. Registers a hosted endpoint for one authentication provider. The tenant
 * is the API key that signs the request, so the body has no tenant field and refuses one like any
 * other unknown member.
 *
 * <p>A WorkOS integration needs signing_secret, the Actions secret shown in the WorkOS dashboard, which
 * NoResponse uses to verify each Action request and to sign its answer. An Auth0 integration sends no
 * signing_secret: NoResponse generates the shared secret and returns it once.
 */
public class IntegrationRequest {
    /**
     * The authentication provider that calls the endpoint.
     */
    @JsonProperty("platform")
    private IntegrationPlatform platform;

    /**
     * The provider events the endpoint scores. Default both: pre_registration (WorkOS user registration
     * Action, Auth0 pre-user-registration trigger) and post_login (WorkOS authentication Action, Auth0
     * post-login trigger). An event that is not listed is answered allow without an assessment.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("hook_points")
    private List<IntegrationHookPoint> hookPoints;

    /**
     * The WorkOS Actions signing secret, as the WorkOS dashboard shows it: 16 to 256 printable ASCII
     * characters with no spaces. It is stored sealed and is not returned.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("signing_secret")
    private String signingSecret;

    @JsonCreator
    public IntegrationRequest(
            @JsonProperty("platform") @Nonnull IntegrationPlatform platform,
            @JsonProperty("hook_points") @Nullable List<IntegrationHookPoint> hookPoints,
            @JsonProperty("signing_secret") @Nullable String signingSecret) {
        this.platform = Optional.ofNullable(platform)
                .orElseThrow(() -> new IllegalArgumentException("platform cannot be null"));
        this.hookPoints = hookPoints;
        this.signingSecret = signingSecret;
    }

    public IntegrationRequest(@Nonnull IntegrationPlatform platform) {
        this(platform, null, null);
    }

    /**
     * The authentication provider that calls the endpoint.
     */
    public IntegrationPlatform platform() {
        return this.platform;
    }

    /**
     * The provider events the endpoint scores. Default both: pre_registration (WorkOS user registration
     * Action, Auth0 pre-user-registration trigger) and post_login (WorkOS authentication Action, Auth0
     * post-login trigger). An event that is not listed is answered allow without an assessment.
     */
    public Optional<List<IntegrationHookPoint>> hookPoints() {
        return Optional.ofNullable(this.hookPoints);
    }

    /**
     * The WorkOS Actions signing secret, as the WorkOS dashboard shows it: 16 to 256 printable ASCII
     * characters with no spaces. It is stored sealed and is not returned.
     */
    public Optional<String> signingSecret() {
        return Optional.ofNullable(this.signingSecret);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The authentication provider that calls the endpoint.
     */
    public IntegrationRequest withPlatform(@Nonnull IntegrationPlatform platform) {
        this.platform = Utils.checkNotNull(platform, "platform");
        return this;
    }

    /**
     * The provider events the endpoint scores. Default both: pre_registration (WorkOS user registration
     * Action, Auth0 pre-user-registration trigger) and post_login (WorkOS authentication Action, Auth0
     * post-login trigger). An event that is not listed is answered allow without an assessment.
     */
    public IntegrationRequest withHookPoints(@Nullable List<IntegrationHookPoint> hookPoints) {
        this.hookPoints = hookPoints;
        return this;
    }

    /**
     * The WorkOS Actions signing secret, as the WorkOS dashboard shows it: 16 to 256 printable ASCII
     * characters with no spaces. It is stored sealed and is not returned.
     */
    public IntegrationRequest withSigningSecret(@Nullable String signingSecret) {
        this.signingSecret = signingSecret;
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
        IntegrationRequest other = (IntegrationRequest) o;
        return Utils.enhancedDeepEquals(this.platform, other.platform)
                && Utils.enhancedDeepEquals(this.hookPoints, other.hookPoints)
                && Utils.enhancedDeepEquals(this.signingSecret, other.signingSecret);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(platform, hookPoints, signingSecret);
    }

    @Override
    public String toString() {
        return Utils.toString(
                IntegrationRequest.class,
                "platform",
                platform,
                "hookPoints",
                hookPoints,
                "signingSecret",
                signingSecret);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IntegrationPlatform platform;

        private List<IntegrationHookPoint> hookPoints;

        private String signingSecret;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The authentication provider that calls the endpoint.
         */
        public Builder platform(@Nonnull IntegrationPlatform platform) {
            this.platform = Utils.checkNotNull(platform, "platform");
            return this;
        }

        /**
         * The provider events the endpoint scores. Default both: pre_registration (WorkOS user registration
         * Action, Auth0 pre-user-registration trigger) and post_login (WorkOS authentication Action, Auth0
         * post-login trigger). An event that is not listed is answered allow without an assessment.
         */
        public Builder hookPoints(@Nullable List<IntegrationHookPoint> hookPoints) {
            this.hookPoints = hookPoints;
            return this;
        }

        /**
         * The WorkOS Actions signing secret, as the WorkOS dashboard shows it: 16 to 256 printable ASCII
         * characters with no spaces. It is stored sealed and is not returned.
         */
        public Builder signingSecret(@Nullable String signingSecret) {
            this.signingSecret = signingSecret;
            return this;
        }

        public IntegrationRequest build() {
            return new IntegrationRequest(platform, hookPoints, signingSecret);
        }
    }
}
