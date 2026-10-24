package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookVerificationResponse
 *
 * <p>The body an endpoint returns, with status 200 and Content-Type application/json within 5 seconds, to
 * an endpoint.verification event. A 2xx without the echoed challenge does not verify the endpoint.
 */
public class WebhookVerificationResponse {
    /**
     * Endpoint verification nonce: 32 random bytes, unpadded base64url (43 characters). Fresh per
     * verification.
     */
    @JsonProperty("challenge")
    private String challenge;

    @JsonCreator
    public WebhookVerificationResponse(@JsonProperty("challenge") @Nonnull String challenge) {
        this.challenge = Optional.ofNullable(challenge)
                .orElseThrow(() -> new IllegalArgumentException("challenge cannot be null"));
    }

    /**
     * Endpoint verification nonce: 32 random bytes, unpadded base64url (43 characters). Fresh per
     * verification.
     */
    public String challenge() {
        return this.challenge;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Endpoint verification nonce: 32 random bytes, unpadded base64url (43 characters). Fresh per
     * verification.
     */
    public WebhookVerificationResponse withChallenge(@Nonnull String challenge) {
        this.challenge = Utils.checkNotNull(challenge, "challenge");
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
        WebhookVerificationResponse other = (WebhookVerificationResponse) o;
        return Utils.enhancedDeepEquals(this.challenge, other.challenge);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(challenge);
    }

    @Override
    public String toString() {
        return Utils.toString(WebhookVerificationResponse.class, "challenge", challenge);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String challenge;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Endpoint verification nonce: 32 random bytes, unpadded base64url (43 characters). Fresh per
         * verification.
         */
        public Builder challenge(@Nonnull String challenge) {
            this.challenge = Utils.checkNotNull(challenge, "challenge");
            return this;
        }

        public WebhookVerificationResponse build() {
            return new WebhookVerificationResponse(challenge);
        }
    }
}
