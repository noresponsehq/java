package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * KeyRotationRequest
 *
 * <p>POST /v1/keys/rotate body. The workspace and the key being rotated are the API key that signs the
 * request, so neither is a body member. An empty object takes the default overlap.
 */
public class KeyRotationRequest {
    /**
     * How long the presented key keeps working beside the new one, from 60 seconds to 7 days. Default
     * 86400 (24 hours). At the end of the overlap the presented key stops authenticating.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("overlap_seconds")
    private Long overlapSeconds;

    @JsonCreator
    public KeyRotationRequest(@JsonProperty("overlap_seconds") @Nullable Long overlapSeconds) {
        this.overlapSeconds = overlapSeconds;
    }

    public KeyRotationRequest() {
        this(null);
    }

    /**
     * How long the presented key keeps working beside the new one, from 60 seconds to 7 days. Default
     * 86400 (24 hours). At the end of the overlap the presented key stops authenticating.
     */
    public Optional<Long> overlapSeconds() {
        return Optional.ofNullable(this.overlapSeconds);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How long the presented key keeps working beside the new one, from 60 seconds to 7 days. Default
     * 86400 (24 hours). At the end of the overlap the presented key stops authenticating.
     */
    public KeyRotationRequest withOverlapSeconds(@Nullable Long overlapSeconds) {
        this.overlapSeconds = overlapSeconds;
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
        KeyRotationRequest other = (KeyRotationRequest) o;
        return Utils.enhancedDeepEquals(this.overlapSeconds, other.overlapSeconds);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(overlapSeconds);
    }

    @Override
    public String toString() {
        return Utils.toString(KeyRotationRequest.class, "overlapSeconds", overlapSeconds);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long overlapSeconds;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How long the presented key keeps working beside the new one, from 60 seconds to 7 days. Default
         * 86400 (24 hours). At the end of the overlap the presented key stops authenticating.
         */
        public Builder overlapSeconds(@Nullable Long overlapSeconds) {
            this.overlapSeconds = overlapSeconds;
            return this;
        }

        public KeyRotationRequest build() {
            return new KeyRotationRequest(overlapSeconds);
        }
    }
}
