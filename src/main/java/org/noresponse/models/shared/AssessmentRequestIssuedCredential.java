package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AssessmentRequestIssuedCredential
 *
 * <p>Optional. When the signup gate issues an API key to the new account, send SHA-256 of that key, so
 * later usage of the key, or a leaked copy, ties back to this assessment. Each SDK has a helper that
 * computes it.
 *
 * <p>Send only the hash: customer keys are high-entropy, so the hash reveals nothing about them. The
 * assessment stores it, and no response, webhook or log carries it.
 */
public class AssessmentRequestIssuedCredential {
    /**
     * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters.
     */
    @JsonProperty("sha256")
    private String sha256;

    @JsonCreator
    public AssessmentRequestIssuedCredential(@JsonProperty("sha256") @Nonnull String sha256) {
        this.sha256 =
                Optional.ofNullable(sha256).orElseThrow(() -> new IllegalArgumentException("sha256 cannot be null"));
    }

    /**
     * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters.
     */
    public String sha256() {
        return this.sha256;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters.
     */
    public AssessmentRequestIssuedCredential withSha256(@Nonnull String sha256) {
        this.sha256 = Utils.checkNotNull(sha256, "sha256");
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
        AssessmentRequestIssuedCredential other = (AssessmentRequestIssuedCredential) o;
        return Utils.enhancedDeepEquals(this.sha256, other.sha256);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(sha256);
    }

    @Override
    public String toString() {
        return Utils.toString(AssessmentRequestIssuedCredential.class, "sha256", sha256);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String sha256;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters.
         */
        public Builder sha256(@Nonnull String sha256) {
            this.sha256 = Utils.checkNotNull(sha256, "sha256");
            return this;
        }

        public AssessmentRequestIssuedCredential build() {
            return new AssessmentRequestIssuedCredential(sha256);
        }
    }
}
