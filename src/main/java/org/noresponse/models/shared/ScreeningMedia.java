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
 * ScreeningMedia
 *
 * <p>One image or video a collector read, with the post that carried it.
 */
public class ScreeningMedia {

    @JsonProperty("id")
    private String id;

    @JsonProperty("kind")
    private String kind;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("media_type")
    private MediaType mediaType;

    /**
     * Where the platform serves the image or video.
     */
    @JsonProperty("media_url")
    private String mediaUrl;

    /**
     * The caption or alt text.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("text")
    private String text;

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
    public ScreeningMedia(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("post_id") @Nullable String postId,
            @JsonProperty("media_type") @Nonnull MediaType mediaType,
            @JsonProperty("media_url") @Nonnull String mediaUrl,
            @JsonProperty("text") @Nullable String text,
            @JsonProperty("profile_id") @Nonnull String profileId,
            @JsonProperty("permalink") @Nonnull String permalink,
            @JsonProperty("posted_at") @Nonnull String postedAt,
            @JsonProperty("screenshot_sha256") @Nullable String screenshotSha256,
            @JsonProperty("captured_at") @Nullable String capturedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.kind = Builder._SINGLETON_VALUE_Kind.value();
        this.postId = postId;
        this.mediaType = Optional.ofNullable(mediaType)
                .orElseThrow(() -> new IllegalArgumentException("mediaType cannot be null"));
        this.mediaUrl = Optional.ofNullable(mediaUrl)
                .orElseThrow(() -> new IllegalArgumentException("mediaUrl cannot be null"));
        this.text = text;
        this.profileId = Optional.ofNullable(profileId)
                .orElseThrow(() -> new IllegalArgumentException("profileId cannot be null"));
        this.permalink = Optional.ofNullable(permalink)
                .orElseThrow(() -> new IllegalArgumentException("permalink cannot be null"));
        this.postedAt = Optional.ofNullable(postedAt)
                .orElseThrow(() -> new IllegalArgumentException("postedAt cannot be null"));
        this.screenshotSha256 = screenshotSha256;
        this.capturedAt = capturedAt;
    }

    public ScreeningMedia(
            @Nonnull String id,
            @Nonnull MediaType mediaType,
            @Nonnull String mediaUrl,
            @Nonnull String profileId,
            @Nonnull String permalink,
            @Nonnull String postedAt) {
        this(id, null, mediaType, mediaUrl, null, profileId, permalink, postedAt, null, null);
    }

    public String id() {
        return this.id;
    }

    public String kind() {
        return this.kind;
    }

    public Optional<String> postId() {
        return Optional.ofNullable(this.postId);
    }

    public MediaType mediaType() {
        return this.mediaType;
    }

    /**
     * Where the platform serves the image or video.
     */
    public String mediaUrl() {
        return this.mediaUrl;
    }

    /**
     * The caption or alt text.
     */
    public Optional<String> text() {
        return Optional.ofNullable(this.text);
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

    public ScreeningMedia withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public ScreeningMedia withPostId(@Nullable String postId) {
        this.postId = postId;
        return this;
    }

    public ScreeningMedia withMediaType(@Nonnull MediaType mediaType) {
        this.mediaType = Utils.checkNotNull(mediaType, "mediaType");
        return this;
    }

    /**
     * Where the platform serves the image or video.
     */
    public ScreeningMedia withMediaUrl(@Nonnull String mediaUrl) {
        this.mediaUrl = Utils.checkNotNull(mediaUrl, "mediaUrl");
        return this;
    }

    /**
     * The caption or alt text.
     */
    public ScreeningMedia withText(@Nullable String text) {
        this.text = text;
        return this;
    }

    public ScreeningMedia withProfileId(@Nonnull String profileId) {
        this.profileId = Utils.checkNotNull(profileId, "profileId");
        return this;
    }

    public ScreeningMedia withPermalink(@Nonnull String permalink) {
        this.permalink = Utils.checkNotNull(permalink, "permalink");
        return this;
    }

    public ScreeningMedia withPostedAt(@Nonnull String postedAt) {
        this.postedAt = Utils.checkNotNull(postedAt, "postedAt");
        return this;
    }

    /**
     * SHA-256 of the screenshot taken of a flagged item.
     */
    public ScreeningMedia withScreenshotSha256(@Nullable String screenshotSha256) {
        this.screenshotSha256 = screenshotSha256;
        return this;
    }

    /**
     * When the screenshot was taken.
     */
    public ScreeningMedia withCapturedAt(@Nullable String capturedAt) {
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
        ScreeningMedia other = (ScreeningMedia) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.postId, other.postId)
                && Utils.enhancedDeepEquals(this.mediaType, other.mediaType)
                && Utils.enhancedDeepEquals(this.mediaUrl, other.mediaUrl)
                && Utils.enhancedDeepEquals(this.text, other.text)
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
                postId,
                mediaType,
                mediaUrl,
                text,
                profileId,
                permalink,
                postedAt,
                screenshotSha256,
                capturedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningMedia.class,
                "id",
                id,
                "kind",
                kind,
                "postId",
                postId,
                "mediaType",
                mediaType,
                "mediaUrl",
                mediaUrl,
                "text",
                text,
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

        private String postId;

        private MediaType mediaType;

        private String mediaUrl;

        private String text;

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

        public Builder postId(@Nullable String postId) {
            this.postId = postId;
            return this;
        }

        public Builder mediaType(@Nonnull MediaType mediaType) {
            this.mediaType = Utils.checkNotNull(mediaType, "mediaType");
            return this;
        }

        /**
         * Where the platform serves the image or video.
         */
        public Builder mediaUrl(@Nonnull String mediaUrl) {
            this.mediaUrl = Utils.checkNotNull(mediaUrl, "mediaUrl");
            return this;
        }

        /**
         * The caption or alt text.
         */
        public Builder text(@Nullable String text) {
            this.text = text;
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

        public ScreeningMedia build() {
            return new ScreeningMedia(
                    id,
                    postId,
                    mediaType,
                    mediaUrl,
                    text,
                    profileId,
                    permalink,
                    postedAt,
                    screenshotSha256,
                    capturedAt);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Kind =
                new LazySingletonValue<>("kind", "\"media\"", new TypeReference<String>() {});
    }
}
