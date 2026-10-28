package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * CredentialRequest
 *
 * <p>Associate the SHA-256 of a high-entropy key issued by your service. Send the hash instead of the
 * key. Repeating a hash returns its original association, including its retirement state.
 *
 * <p>A hash associated with another account returns a conflict.
 */
public class CredentialRequest {

    @JsonProperty("sha256")
    private String sha256;

    @JsonCreator
    public CredentialRequest(@JsonProperty("sha256") @Nonnull String sha256) {
        this.sha256 =
                Optional.ofNullable(sha256).orElseThrow(() -> new IllegalArgumentException("sha256 cannot be null"));
    }

    public String sha256() {
        return this.sha256;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CredentialRequest withSha256(@Nonnull String sha256) {
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
        CredentialRequest other = (CredentialRequest) o;
        return Utils.enhancedDeepEquals(this.sha256, other.sha256);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(sha256);
    }

    @Override
    public String toString() {
        return Utils.toString(CredentialRequest.class, "sha256", sha256);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String sha256;

        private Builder() {
            // force use of static builder() method
        }

        public Builder sha256(@Nonnull String sha256) {
            this.sha256 = Utils.checkNotNull(sha256, "sha256");
            return this;
        }

        public CredentialRequest build() {
            return new CredentialRequest(sha256);
        }
    }
}
