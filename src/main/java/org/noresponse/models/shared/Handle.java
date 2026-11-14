package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * Handle
 *
 * <p>An account the person is known to hold.
 */
public class Handle {
    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    @JsonProperty("platform")
    private HandlePlatform platform;

    /**
     * The account's handle, without a leading @.
     */
    @JsonProperty("handle")
    private String handle;

    @JsonCreator
    public Handle(
            @JsonProperty("platform") @Nonnull HandlePlatform platform,
            @JsonProperty("handle") @Nonnull String handle) {
        this.platform = Optional.ofNullable(platform)
                .orElseThrow(() -> new IllegalArgumentException("platform cannot be null"));
        this.handle =
                Optional.ofNullable(handle).orElseThrow(() -> new IllegalArgumentException("handle cannot be null"));
    }

    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    public HandlePlatform platform() {
        return this.platform;
    }

    /**
     * The account's handle, without a leading @.
     */
    public String handle() {
        return this.handle;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    public Handle withPlatform(@Nonnull HandlePlatform platform) {
        this.platform = Utils.checkNotNull(platform, "platform");
        return this;
    }

    /**
     * The account's handle, without a leading @.
     */
    public Handle withHandle(@Nonnull String handle) {
        this.handle = Utils.checkNotNull(handle, "handle");
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
        Handle other = (Handle) o;
        return Utils.enhancedDeepEquals(this.platform, other.platform)
                && Utils.enhancedDeepEquals(this.handle, other.handle);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(platform, handle);
    }

    @Override
    public String toString() {
        return Utils.toString(Handle.class, "platform", platform, "handle", handle);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private HandlePlatform platform;

        private String handle;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
         * exercising the product end to end with no network.
         */
        public Builder platform(@Nonnull HandlePlatform platform) {
            this.platform = Utils.checkNotNull(platform, "platform");
            return this;
        }

        /**
         * The account's handle, without a leading @.
         */
        public Builder handle(@Nonnull String handle) {
            this.handle = Utils.checkNotNull(handle, "handle");
            return this;
        }

        public Handle build() {
            return new Handle(platform, handle);
        }
    }
}
