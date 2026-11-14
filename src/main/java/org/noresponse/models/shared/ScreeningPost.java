package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * ScreeningPost
 *
 * <p>One post a collector read, with its permalink and time.
 */
public class ScreeningPost {

    @JsonProperty("id")
    private String id;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("text")
    private String text;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reply_to")
    private String replyTo;

    /**
     * The permalink of the post this one reposts. text then holds that post's author's words, not the
     * person's.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("repost_of")
    private String repostOf;

    /**
     * The permalink of the post this one quotes. text holds the person's own words alone.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("quote_of")
    private String quoteOf;

    @JsonProperty("profile_id")
    private String profileId;

    @JsonProperty("permalink")
    private String permalink;

    @JsonProperty("posted_at")
    private String postedAt;

    /**
     * SHA-256 of the screenshot taken of a flagged item.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("screenshot_sha256")
    private String screenshotSha256;

    /**
     * When the screenshot was taken.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("captured_at")
    private String capturedAt;

    @JsonCreator
    public ScreeningPost(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("text") @Nonnull String text,
            @JsonProperty("reply_to") @Nullable String replyTo,
            @JsonProperty("repost_of") @Nullable String repostOf,
            @JsonProperty("quote_of") @Nullable String quoteOf,
            @JsonProperty("profile_id") @Nonnull String profileId,
            @JsonProperty("permalink") @Nonnull String permalink,
            @JsonProperty("posted_at") @Nonnull String postedAt,
            @JsonProperty("screenshot_sha256") @Nullable String screenshotSha256,
            @JsonProperty("captured_at") @Nullable String capturedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.kind = Builder._SINGLETON_VALUE_Kind.value();
        this.text = Optional.ofNullable(text).orElseThrow(() -> new IllegalArgumentException("text cannot be null"));
        this.replyTo = replyTo;
        this.repostOf = repostOf;
        this.quoteOf = quoteOf;
        this.profileId = Optional.ofNullable(profileId)
                .orElseThrow(() -> new IllegalArgumentException("profileId cannot be null"));
        this.permalink = Optional.ofNullable(permalink)
                .orElseThrow(() -> new IllegalArgumentException("permalink cannot be null"));
        this.postedAt = Optional.ofNullable(postedAt)
                .orElseThrow(() -> new IllegalArgumentException("postedAt cannot be null"));
        this.screenshotSha256 = screenshotSha256;
        this.capturedAt = capturedAt;
    }

    public ScreeningPost(
            @Nonnull String id,
            @Nonnull String text,
            @Nonnull String profileId,
            @Nonnull String permalink,
            @Nonnull String postedAt) {
        this(id, text, null, null, null, profileId, permalink, postedAt, null, null);
    }

    public String id() {
        return this.id;
    }

    public String kind() {
        return this.kind;
    }

    public String text() {
        return this.text;
    }

    public Optional<String> replyTo() {
        return Optional.ofNullable(this.replyTo);
    }

    /**
     * The permalink of the post this one reposts. text then holds that post's author's words, not the
     * person's.
     */
    public Optional<String> repostOf() {
        return Optional.ofNullable(this.repostOf);
    }

    /**
     * The permalink of the post this one quotes. text holds the person's own words alone.
     */
    public Optional<String> quoteOf() {
        return Optional.ofNullable(this.quoteOf);
    }

    public String profileId() {
        return this.profileId;
    }

    public String permalink() {
        return this.permalink;
    }

    public String postedAt() {
        return this.postedAt;
    }

    /**
     * SHA-256 of the screenshot taken of a flagged item.
     */
    public Optional<String> screenshotSha256() {
        return Optional.ofNullable(this.screenshotSha256);
    }

    /**
     * When the screenshot was taken.
     */
    public Optional<String> capturedAt() {
        return Optional.ofNullable(this.capturedAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningPost withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public ScreeningPost withText(@Nonnull String text) {
        this.text = Utils.checkNotNull(text, "text");
        return this;
    }

    public ScreeningPost withReplyTo(@Nullable String replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    /**
     * The permalink of the post this one reposts. text then holds that post's author's words, not the
     * person's.
     */
    public ScreeningPost withRepostOf(@Nullable String repostOf) {
        this.repostOf = repostOf;
        return this;
    }

    /**
     * The permalink of the post this one quotes. text holds the person's own words alone.
     */
    public ScreeningPost withQuoteOf(@Nullable String quoteOf) {
        this.quoteOf = quoteOf;
        return this;
    }

    public ScreeningPost withProfileId(@Nonnull String profileId) {
        this.profileId = Utils.checkNotNull(profileId, "profileId");
        return this;
    }

    public ScreeningPost withPermalink(@Nonnull String permalink) {
        this.permalink = Utils.checkNotNull(permalink, "permalink");
        return this;
    }

    public ScreeningPost withPostedAt(@Nonnull String postedAt) {
        this.postedAt = Utils.checkNotNull(postedAt, "postedAt");
        return this;
    }

    /**
     * SHA-256 of the screenshot taken of a flagged item.
     */
    public ScreeningPost withScreenshotSha256(@Nullable String screenshotSha256) {
        this.screenshotSha256 = screenshotSha256;
        return this;
    }

    /**
     * When the screenshot was taken.
     */
    public ScreeningPost withCapturedAt(@Nullable String capturedAt) {
        this.capturedAt = capturedAt;
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
        ScreeningPost other = (ScreeningPost) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.text, other.text)
                && Utils.enhancedDeepEquals(this.replyTo, other.replyTo)
                && Utils.enhancedDeepEquals(this.repostOf, other.repostOf)
                && Utils.enhancedDeepEquals(this.quoteOf, other.quoteOf)
                && Utils.enhancedDeepEquals(this.profileId, other.profileId)
                && Utils.enhancedDeepEquals(this.permalink, other.permalink)
                && Utils.enhancedDeepEquals(this.postedAt, other.postedAt)
                && Utils.enhancedDeepEquals(this.screenshotSha256, other.screenshotSha256)
                && Utils.enhancedDeepEquals(this.capturedAt, other.capturedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                kind,
                text,
                replyTo,
                repostOf,
                quoteOf,
                profileId,
                permalink,
                postedAt,
                screenshotSha256,
                capturedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningPost.class,
                "id",
                id,
                "kind",
                kind,
                "text",
                text,
                "replyTo",
                replyTo,
                "repostOf",
                repostOf,
                "quoteOf",
                quoteOf,
                "profileId",
                profileId,
                "permalink",
                permalink,
                "postedAt",
                postedAt,
                "screenshotSha256",
                screenshotSha256,
                "capturedAt",
                capturedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String text;

        private String replyTo;

        private String repostOf;

        private String quoteOf;

        private String profileId;

        private String permalink;

        private String postedAt;

        private String screenshotSha256;

        private String capturedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder text(@Nonnull String text) {
            this.text = Utils.checkNotNull(text, "text");
            return this;
        }

        public Builder replyTo(@Nullable String replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        /**
         * The permalink of the post this one reposts. text then holds that post's author's words, not the
         * person's.
         */
        public Builder repostOf(@Nullable String repostOf) {
            this.repostOf = repostOf;
            return this;
        }

        /**
         * The permalink of the post this one quotes. text holds the person's own words alone.
         */
        public Builder quoteOf(@Nullable String quoteOf) {
            this.quoteOf = quoteOf;
            return this;
        }

        public Builder profileId(@Nonnull String profileId) {
            this.profileId = Utils.checkNotNull(profileId, "profileId");
            return this;
        }

        public Builder permalink(@Nonnull String permalink) {
            this.permalink = Utils.checkNotNull(permalink, "permalink");
            return this;
        }

        public Builder postedAt(@Nonnull String postedAt) {
            this.postedAt = Utils.checkNotNull(postedAt, "postedAt");
            return this;
        }

        /**
         * SHA-256 of the screenshot taken of a flagged item.
         */
        public Builder screenshotSha256(@Nullable String screenshotSha256) {
            this.screenshotSha256 = screenshotSha256;
            return this;
        }

        /**
         * When the screenshot was taken.
         */
        public Builder capturedAt(@Nullable String capturedAt) {
            this.capturedAt = capturedAt;
            return this;
        }

        public ScreeningPost build() {
            return new ScreeningPost(
                    id, text, replyTo, repostOf, quoteOf, profileId, permalink, postedAt, screenshotSha256, capturedAt);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Kind =
                new LazySingletonValue<>("kind", "\"post\"", new TypeReference<String>() {});
    }
}
