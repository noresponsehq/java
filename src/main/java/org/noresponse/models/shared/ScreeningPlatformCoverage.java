package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningPlatformCoverage
 *
 * <p>What a screening read on one platform.
 */
public class ScreeningPlatformCoverage {
    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    @JsonProperty("platform")
    private HandlePlatform platform;

    /**
     * covered read at least one profile; none_found looked and found no account; inaccessible found
     * accounts that are private or locked; failed could not finish looking; unavailable has no resolver or
     * collector enabled.
     */
    @JsonProperty("status")
    private ScreeningPlatformCoverageStatus status;

    @JsonProperty("accounts_found")
    private long accountsFound;

    @JsonProperty("items_read")
    private long itemsRead;

    @JsonCreator
    public ScreeningPlatformCoverage(
            @JsonProperty("platform") @Nonnull HandlePlatform platform,
            @JsonProperty("status") @Nonnull ScreeningPlatformCoverageStatus status,
            @JsonProperty("accounts_found") long accountsFound,
            @JsonProperty("items_read") long itemsRead) {
        this.platform = Optional.ofNullable(platform)
                .orElseThrow(() -> new IllegalArgumentException("platform cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.accountsFound = accountsFound;
        this.itemsRead = itemsRead;
    }

    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    public HandlePlatform platform() {
        return this.platform;
    }

    /**
     * covered read at least one profile; none_found looked and found no account; inaccessible found
     * accounts that are private or locked; failed could not finish looking; unavailable has no resolver or
     * collector enabled.
     */
    public ScreeningPlatformCoverageStatus status() {
        return this.status;
    }

    public long accountsFound() {
        return this.accountsFound;
    }

    public long itemsRead() {
        return this.itemsRead;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    public ScreeningPlatformCoverage withPlatform(@Nonnull HandlePlatform platform) {
        this.platform = Utils.checkNotNull(platform, "platform");
        return this;
    }

    /**
     * covered read at least one profile; none_found looked and found no account; inaccessible found
     * accounts that are private or locked; failed could not finish looking; unavailable has no resolver or
     * collector enabled.
     */
    public ScreeningPlatformCoverage withStatus(@Nonnull ScreeningPlatformCoverageStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public ScreeningPlatformCoverage withAccountsFound(long accountsFound) {
        this.accountsFound = accountsFound;
        return this;
    }

    public ScreeningPlatformCoverage withItemsRead(long itemsRead) {
        this.itemsRead = itemsRead;
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
        ScreeningPlatformCoverage other = (ScreeningPlatformCoverage) o;
        return Utils.enhancedDeepEquals(this.platform, other.platform)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.accountsFound, other.accountsFound)
                && Utils.enhancedDeepEquals(this.itemsRead, other.itemsRead);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(platform, status, accountsFound, itemsRead);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningPlatformCoverage.class,
                "platform",
                platform,
                "status",
                status,
                "accountsFound",
                accountsFound,
                "itemsRead",
                itemsRead);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private HandlePlatform platform;

        private ScreeningPlatformCoverageStatus status;

        private long accountsFound;

        private long itemsRead;

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
         * covered read at least one profile; none_found looked and found no account; inaccessible found
         * accounts that are private or locked; failed could not finish looking; unavailable has no resolver or
         * collector enabled.
         */
        public Builder status(@Nonnull ScreeningPlatformCoverageStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder accountsFound(long accountsFound) {
            this.accountsFound = accountsFound;
            return this;
        }

        public Builder itemsRead(long itemsRead) {
            this.itemsRead = itemsRead;
            return this;
        }

        public ScreeningPlatformCoverage build() {
            return new ScreeningPlatformCoverage(platform, status, accountsFound, itemsRead);
        }
    }
}
