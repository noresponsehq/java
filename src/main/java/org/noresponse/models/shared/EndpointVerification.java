package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class EndpointVerification {
    /**
     * Opaque id of the endpoint registration being verified.
     */
    @JsonProperty("endpoint_id")
    private String endpointId;

    /**
     * Endpoint verification nonce: 32 random bytes, unpadded base64url (43 characters). Fresh per
     * verification.
     */
    @JsonProperty("challenge")
    private String challenge;

    @JsonCreator
    public EndpointVerification(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("challenge") @Nonnull String challenge) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.challenge = Optional.ofNullable(challenge)
                .orElseThrow(() -> new IllegalArgumentException("challenge cannot be null"));
    }

    /**
     * Opaque id of the endpoint registration being verified.
     */
    public String endpointId() {
        return this.endpointId;
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
     * Opaque id of the endpoint registration being verified.
     */
    public EndpointVerification withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * Endpoint verification nonce: 32 random bytes, unpadded base64url (43 characters). Fresh per
     * verification.
     */
    public EndpointVerification withChallenge(@Nonnull String challenge) {
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
        EndpointVerification other = (EndpointVerification) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.challenge, other.challenge);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, challenge);
    }

    @Override
    public String toString() {
        return Utils.toString(EndpointVerification.class, "endpointId", endpointId, "challenge", challenge);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private String challenge;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque id of the endpoint registration being verified.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        /**
         * Endpoint verification nonce: 32 random bytes, unpadded base64url (43 characters). Fresh per
         * verification.
         */
        public Builder challenge(@Nonnull String challenge) {
            this.challenge = Utils.checkNotNull(challenge, "challenge");
            return this;
        }

        public EndpointVerification build() {
            return new EndpointVerification(endpointId, challenge);
        }
    }
}
