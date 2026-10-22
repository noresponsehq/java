package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * IntegrationRotationRequest
 *
 * <p>POST /v1/integrations/{integration_id}/rotate body. For an Auth0 integration send {}: NoResponse
 * generates a new shared secret and returns it once. For a WorkOS integration send the new
 * signing_secret after regenerating it in the WorkOS dashboard.
 *
 * <p>Either way the previous secret is still accepted for 24 hours, so the provider can be switched over
 * without a refused call.
 */
public class IntegrationRotationRequest {
    /**
     * The WorkOS Actions signing secret, as the WorkOS dashboard shows it: 16 to 256 printable ASCII
     * characters with no spaces. It is stored sealed and is not returned.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("signing_secret")
    private String signingSecret;

    @JsonCreator
    public IntegrationRotationRequest(@JsonProperty("signing_secret") @Nullable String signingSecret) {
        this.signingSecret = signingSecret;
    }

    public IntegrationRotationRequest() {
        this(null);
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
     * The WorkOS Actions signing secret, as the WorkOS dashboard shows it: 16 to 256 printable ASCII
     * characters with no spaces. It is stored sealed and is not returned.
     */
    public IntegrationRotationRequest withSigningSecret(@Nullable String signingSecret) {
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
        IntegrationRotationRequest other = (IntegrationRotationRequest) o;
        return Utils.enhancedDeepEquals(this.signingSecret, other.signingSecret);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(signingSecret);
    }

    @Override
    public String toString() {
        return Utils.toString(IntegrationRotationRequest.class, "signingSecret", signingSecret);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String signingSecret;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The WorkOS Actions signing secret, as the WorkOS dashboard shows it: 16 to 256 printable ASCII
         * characters with no spaces. It is stored sealed and is not returned.
         */
        public Builder signingSecret(@Nullable String signingSecret) {
            this.signingSecret = signingSecret;
            return this;
        }

        public IntegrationRotationRequest build() {
            return new IntegrationRotationRequest(signingSecret);
        }
    }
}
