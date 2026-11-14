package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningProfile
 *
 * <p>An account the screening matched to the person, how it was matched, and how much of it was read.
 */
public class ScreeningProfile {

    @JsonProperty("id")
    private String id;

    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    @JsonProperty("platform")
    private HandlePlatform platform;

    @JsonProperty("handle")
    private String handle;

    @JsonProperty("url")
    private String url;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("display_name")
    private String displayName;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("bio")
    private String bio;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("followers")
    private Long followers;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_created_at")
    private String accountCreatedAt;

    /**
     * public profiles were read; a private or locked one exists but could not be read.
     */
    @JsonProperty("access")
    private Access access;

    /**
     * How a profile was matched to the person: the state its evidence earns, and the evidence.
     */
    @JsonProperty("match")
    private ScreeningMatch match;

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    @JsonProperty("collector")
    private String collector;

    @JsonProperty("items_read")
    private long itemsRead;

    /**
     * The oldest item read.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("period_from")
    private String periodFrom;

    /**
     * The newest item read.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("period_to")
    private String periodTo;

    @JsonProperty("collected_at")
    private String collectedAt;

    @JsonCreator
    public ScreeningProfile(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("platform") @Nonnull HandlePlatform platform,
            @JsonProperty("handle") @Nonnull String handle,
            @JsonProperty("url") @Nonnull String url,
            @JsonProperty("display_name") @Nullable String displayName,
            @JsonProperty("bio") @Nullable String bio,
            @JsonProperty("avatar_url") @Nullable String avatarUrl,
            @JsonProperty("followers") @Nullable Long followers,
            @JsonProperty("account_created_at") @Nullable String accountCreatedAt,
            @JsonProperty("access") @Nonnull Access access,
            @JsonProperty("match") @Nonnull ScreeningMatch match,
            @JsonProperty("collector") @Nonnull String collector,
            @JsonProperty("items_read") long itemsRead,
            @JsonProperty("period_from") @Nullable String periodFrom,
            @JsonProperty("period_to") @Nullable String periodTo,
            @JsonProperty("collected_at") @Nonnull String collectedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.platform = Optional.ofNullable(platform)
                .orElseThrow(() -> new IllegalArgumentException("platform cannot be null"));
        this.handle =
                Optional.ofNullable(handle).orElseThrow(() -> new IllegalArgumentException("handle cannot be null"));
        this.url = Optional.ofNullable(url).orElseThrow(() -> new IllegalArgumentException("url cannot be null"));
        this.displayName = displayName;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
        this.accountCreatedAt = accountCreatedAt;
        this.access =
                Optional.ofNullable(access).orElseThrow(() -> new IllegalArgumentException("access cannot be null"));
        this.match = Optional.ofNullable(match).orElseThrow(() -> new IllegalArgumentException("match cannot be null"));
        this.collector = Optional.ofNullable(collector)
                .orElseThrow(() -> new IllegalArgumentException("collector cannot be null"));
        this.itemsRead = itemsRead;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.collectedAt = Optional.ofNullable(collectedAt)
                .orElseThrow(() -> new IllegalArgumentException("collectedAt cannot be null"));
    }

    public ScreeningProfile(
            @Nonnull String id,
            @Nonnull HandlePlatform platform,
            @Nonnull String handle,
            @Nonnull String url,
            @Nonnull Access access,
            @Nonnull ScreeningMatch match,
            @Nonnull String collector,
            long itemsRead,
            @Nonnull String collectedAt) {
        this(
                id,
                platform,
                handle,
                url,
                null,
                null,
                null,
                null,
                null,
                access,
                match,
                collector,
                itemsRead,
                null,
                null,
                collectedAt);
    }

    public String id() {
        return this.id;
    }

    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    public HandlePlatform platform() {
        return this.platform;
    }

    public String handle() {
        return this.handle;
    }

    public String url() {
        return this.url;
    }

    public Optional<String> displayName() {
        return Optional.ofNullable(this.displayName);
    }

    public Optional<String> bio() {
        return Optional.ofNullable(this.bio);
    }

    public Optional<String> avatarUrl() {
        return Optional.ofNullable(this.avatarUrl);
    }

    public Optional<Long> followers() {
        return Optional.ofNullable(this.followers);
    }

    public Optional<String> accountCreatedAt() {
        return Optional.ofNullable(this.accountCreatedAt);
    }

    /**
     * public profiles were read; a private or locked one exists but could not be read.
     */
    public Access access() {
        return this.access;
    }

    /**
     * How a profile was matched to the person: the state its evidence earns, and the evidence.
     */
    public ScreeningMatch match() {
        return this.match;
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public String collector() {
        return this.collector;
    }

    public long itemsRead() {
        return this.itemsRead;
    }

    /**
     * The oldest item read.
     */
    public Optional<String> periodFrom() {
        return Optional.ofNullable(this.periodFrom);
    }

    /**
     * The newest item read.
     */
    public Optional<String> periodTo() {
        return Optional.ofNullable(this.periodTo);
    }

    public String collectedAt() {
        return this.collectedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningProfile withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
     * exercising the product end to end with no network.
     */
    public ScreeningProfile withPlatform(@Nonnull HandlePlatform platform) {
        this.platform = Utils.checkNotNull(platform, "platform");
        return this;
    }

    public ScreeningProfile withHandle(@Nonnull String handle) {
        this.handle = Utils.checkNotNull(handle, "handle");
        return this;
    }

    public ScreeningProfile withUrl(@Nonnull String url) {
        this.url = Utils.checkNotNull(url, "url");
        return this;
    }

    public ScreeningProfile withDisplayName(@Nullable String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ScreeningProfile withBio(@Nullable String bio) {
        this.bio = bio;
        return this;
    }

    public ScreeningProfile withAvatarUrl(@Nullable String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public ScreeningProfile withFollowers(@Nullable Long followers) {
        this.followers = followers;
        return this;
    }

    public ScreeningProfile withAccountCreatedAt(@Nullable String accountCreatedAt) {
        this.accountCreatedAt = accountCreatedAt;
        return this;
    }

    /**
     * public profiles were read; a private or locked one exists but could not be read.
     */
    public ScreeningProfile withAccess(@Nonnull Access access) {
        this.access = Utils.checkNotNull(access, "access");
        return this;
    }

    /**
     * How a profile was matched to the person: the state its evidence earns, and the evidence.
     */
    public ScreeningProfile withMatch(@Nonnull ScreeningMatch match) {
        this.match = Utils.checkNotNull(match, "match");
        return this;
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public ScreeningProfile withCollector(@Nonnull String collector) {
        this.collector = Utils.checkNotNull(collector, "collector");
        return this;
    }

    public ScreeningProfile withItemsRead(long itemsRead) {
        this.itemsRead = itemsRead;
        return this;
    }

    /**
     * The oldest item read.
     */
    public ScreeningProfile withPeriodFrom(@Nullable String periodFrom) {
        this.periodFrom = periodFrom;
        return this;
    }

    /**
     * The newest item read.
     */
    public ScreeningProfile withPeriodTo(@Nullable String periodTo) {
        this.periodTo = periodTo;
        return this;
    }

    public ScreeningProfile withCollectedAt(@Nonnull String collectedAt) {
        this.collectedAt = Utils.checkNotNull(collectedAt, "collectedAt");
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
        ScreeningProfile other = (ScreeningProfile) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.platform, other.platform)
                && Utils.enhancedDeepEquals(this.handle, other.handle)
                && Utils.enhancedDeepEquals(this.url, other.url)
                && Utils.enhancedDeepEquals(this.displayName, other.displayName)
                && Utils.enhancedDeepEquals(this.bio, other.bio)
                && Utils.enhancedDeepEquals(this.avatarUrl, other.avatarUrl)
                && Utils.enhancedDeepEquals(this.followers, other.followers)
                && Utils.enhancedDeepEquals(this.accountCreatedAt, other.accountCreatedAt)
                && Utils.enhancedDeepEquals(this.access, other.access)
                && Utils.enhancedDeepEquals(this.match, other.match)
                && Utils.enhancedDeepEquals(this.collector, other.collector)
                && Utils.enhancedDeepEquals(this.itemsRead, other.itemsRead)
                && Utils.enhancedDeepEquals(this.periodFrom, other.periodFrom)
                && Utils.enhancedDeepEquals(this.periodTo, other.periodTo)
                && Utils.enhancedDeepEquals(this.collectedAt, other.collectedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                platform,
                handle,
                url,
                displayName,
                bio,
                avatarUrl,
                followers,
                accountCreatedAt,
                access,
                match,
                collector,
                itemsRead,
                periodFrom,
                periodTo,
                collectedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningProfile.class,
                "id",
                id,
                "platform",
                platform,
                "handle",
                handle,
                "url",
                url,
                "displayName",
                displayName,
                "bio",
                bio,
                "avatarUrl",
                avatarUrl,
                "followers",
                followers,
                "accountCreatedAt",
                accountCreatedAt,
                "access",
                access,
                "match",
                match,
                "collector",
                collector,
                "itemsRead",
                itemsRead,
                "periodFrom",
                periodFrom,
                "periodTo",
                periodTo,
                "collectedAt",
                collectedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private HandlePlatform platform;

        private String handle;

        private String url;

        private String displayName;

        private String bio;

        private String avatarUrl;

        private Long followers;

        private String accountCreatedAt;

        private Access access;

        private ScreeningMatch match;

        private String collector;

        private long itemsRead;

        private String periodFrom;

        private String periodTo;

        private String collectedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * A platform a screening can cover. synthetic is the deterministic connector's own platform, for
         * exercising the product end to end with no network.
         */
        public Builder platform(@Nonnull HandlePlatform platform) {
            this.platform = Utils.checkNotNull(platform, "platform");
            return this;
        }

        public Builder handle(@Nonnull String handle) {
            this.handle = Utils.checkNotNull(handle, "handle");
            return this;
        }

        public Builder url(@Nonnull String url) {
            this.url = Utils.checkNotNull(url, "url");
            return this;
        }

        public Builder displayName(@Nullable String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder bio(@Nullable String bio) {
            this.bio = bio;
            return this;
        }

        public Builder avatarUrl(@Nullable String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder followers(@Nullable Long followers) {
            this.followers = followers;
            return this;
        }

        public Builder accountCreatedAt(@Nullable String accountCreatedAt) {
            this.accountCreatedAt = accountCreatedAt;
            return this;
        }

        /**
         * public profiles were read; a private or locked one exists but could not be read.
         */
        public Builder access(@Nonnull Access access) {
            this.access = Utils.checkNotNull(access, "access");
            return this;
        }

        /**
         * How a profile was matched to the person: the state its evidence earns, and the evidence.
         */
        public Builder match(@Nonnull ScreeningMatch match) {
            this.match = Utils.checkNotNull(match, "match");
            return this;
        }

        /**
         * The resolver, collector or analyser that produced a record, by its catalogue name.
         */
        public Builder collector(@Nonnull String collector) {
            this.collector = Utils.checkNotNull(collector, "collector");
            return this;
        }

        public Builder itemsRead(long itemsRead) {
            this.itemsRead = itemsRead;
            return this;
        }

        /**
         * The oldest item read.
         */
        public Builder periodFrom(@Nullable String periodFrom) {
            this.periodFrom = periodFrom;
            return this;
        }

        /**
         * The newest item read.
         */
        public Builder periodTo(@Nullable String periodTo) {
            this.periodTo = periodTo;
            return this;
        }

        public Builder collectedAt(@Nonnull String collectedAt) {
            this.collectedAt = Utils.checkNotNull(collectedAt, "collectedAt");
            return this;
        }

        public ScreeningProfile build() {
            return new ScreeningProfile(
                    id,
                    platform,
                    handle,
                    url,
                    displayName,
                    bio,
                    avatarUrl,
                    followers,
                    accountCreatedAt,
                    access,
                    match,
                    collector,
                    itemsRead,
                    periodFrom,
                    periodTo,
                    collectedAt);
        }
    }
}
