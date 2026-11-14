package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
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

    /**
     * A place a post names, as the platform shows it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("place")
    private PostPlace place;

    /**
     * The handles of the accounts the post tags, on its own platform.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("tagged")
    private List<String> tagged;

    /**
     * How many likes the platform shows for the post.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("likes")
    private Long likes;

    /**
     * How many comments the platform shows for the post.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("comments")
    private Long comments;

    /**
     * How many views or plays the platform shows for the post.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("views")
    private Long views;

    /**
     * How many times the platform shows the post was shared or reposted.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("shares")
    private Long shares;

    /**
     * The language the platform says the post is in, as the platform codes it, such as en or pt-BR.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("language")
    private String language;

    /**
     * true when the platform marks the post as edited after it went up. Absent when it does not mark it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("edited")
    private Boolean edited;

    /**
     * true when the platform marks the post as a paid partnership or an ad. Absent when it does not mark
     * it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("sponsored")
    private Boolean sponsored;

    /**
     * true when the platform labels the post as made or changed with AI. Absent when it does not label it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("ai_generated")
    private Boolean aiGenerated;

    /**
     * The app or device the platform says the post was made with.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("posted_with")
    private String postedWith;

    /**
     * Everything else the platform sent about the post, under the platform's own names and as it typed
     * them: counts, ids of linked posts and accounts, links, media details, labels and notices. The fields
     * above are not repeated here. Nothing that describes the account the screening read with, such as
     * whether it liked or saved the post, is ever here.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("stated")
    private Map<String, Object> stated;

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
            @JsonProperty("place") @Nullable PostPlace place,
            @JsonProperty("tagged") @Nullable List<String> tagged,
            @JsonProperty("likes") @Nullable Long likes,
            @JsonProperty("comments") @Nullable Long comments,
            @JsonProperty("views") @Nullable Long views,
            @JsonProperty("shares") @Nullable Long shares,
            @JsonProperty("language") @Nullable String language,
            @JsonProperty("edited") @Nullable Boolean edited,
            @JsonProperty("sponsored") @Nullable Boolean sponsored,
            @JsonProperty("ai_generated") @Nullable Boolean aiGenerated,
            @JsonProperty("posted_with") @Nullable String postedWith,
            @JsonProperty("stated") @Nullable Map<String, Object> stated,
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
        this.place = place;
        this.tagged = tagged;
        this.likes = likes;
        this.comments = comments;
        this.views = views;
        this.shares = shares;
        this.language = language;
        this.edited = edited;
        this.sponsored = sponsored;
        this.aiGenerated = aiGenerated;
        this.postedWith = postedWith;
        this.stated = stated;
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
        this(
                id,
                text,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                profileId,
                permalink,
                postedAt,
                null,
                null);
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

    /**
     * A place a post names, as the platform shows it.
     */
    public Optional<PostPlace> place() {
        return Optional.ofNullable(this.place);
    }

    /**
     * The handles of the accounts the post tags, on its own platform.
     */
    public Optional<List<String>> tagged() {
        return Optional.ofNullable(this.tagged);
    }

    /**
     * How many likes the platform shows for the post.
     */
    public Optional<Long> likes() {
        return Optional.ofNullable(this.likes);
    }

    /**
     * How many comments the platform shows for the post.
     */
    public Optional<Long> comments() {
        return Optional.ofNullable(this.comments);
    }

    /**
     * How many views or plays the platform shows for the post.
     */
    public Optional<Long> views() {
        return Optional.ofNullable(this.views);
    }

    /**
     * How many times the platform shows the post was shared or reposted.
     */
    public Optional<Long> shares() {
        return Optional.ofNullable(this.shares);
    }

    /**
     * The language the platform says the post is in, as the platform codes it, such as en or pt-BR.
     */
    public Optional<String> language() {
        return Optional.ofNullable(this.language);
    }

    /**
     * true when the platform marks the post as edited after it went up. Absent when it does not mark it.
     */
    public Optional<Boolean> edited() {
        return Optional.ofNullable(this.edited);
    }

    /**
     * true when the platform marks the post as a paid partnership or an ad. Absent when it does not mark
     * it.
     */
    public Optional<Boolean> sponsored() {
        return Optional.ofNullable(this.sponsored);
    }

    /**
     * true when the platform labels the post as made or changed with AI. Absent when it does not label it.
     */
    public Optional<Boolean> aiGenerated() {
        return Optional.ofNullable(this.aiGenerated);
    }

    /**
     * The app or device the platform says the post was made with.
     */
    public Optional<String> postedWith() {
        return Optional.ofNullable(this.postedWith);
    }

    /**
     * Everything else the platform sent about the post, under the platform's own names and as it typed
     * them: counts, ids of linked posts and accounts, links, media details, labels and notices. The fields
     * above are not repeated here. Nothing that describes the account the screening read with, such as
     * whether it liked or saved the post, is ever here.
     */
    public Optional<Map<String, Object>> stated() {
        return Optional.ofNullable(this.stated);
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

    /**
     * A place a post names, as the platform shows it.
     */
    public ScreeningPost withPlace(@Nullable PostPlace place) {
        this.place = place;
        return this;
    }

    /**
     * The handles of the accounts the post tags, on its own platform.
     */
    public ScreeningPost withTagged(@Nullable List<String> tagged) {
        this.tagged = tagged;
        return this;
    }

    /**
     * How many likes the platform shows for the post.
     */
    public ScreeningPost withLikes(@Nullable Long likes) {
        this.likes = likes;
        return this;
    }

    /**
     * How many comments the platform shows for the post.
     */
    public ScreeningPost withComments(@Nullable Long comments) {
        this.comments = comments;
        return this;
    }

    /**
     * How many views or plays the platform shows for the post.
     */
    public ScreeningPost withViews(@Nullable Long views) {
        this.views = views;
        return this;
    }

    /**
     * How many times the platform shows the post was shared or reposted.
     */
    public ScreeningPost withShares(@Nullable Long shares) {
        this.shares = shares;
        return this;
    }

    /**
     * The language the platform says the post is in, as the platform codes it, such as en or pt-BR.
     */
    public ScreeningPost withLanguage(@Nullable String language) {
        this.language = language;
        return this;
    }

    /**
     * true when the platform marks the post as edited after it went up. Absent when it does not mark it.
     */
    public ScreeningPost withEdited(@Nullable Boolean edited) {
        this.edited = edited;
        return this;
    }

    /**
     * true when the platform marks the post as a paid partnership or an ad. Absent when it does not mark
     * it.
     */
    public ScreeningPost withSponsored(@Nullable Boolean sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    /**
     * true when the platform labels the post as made or changed with AI. Absent when it does not label it.
     */
    public ScreeningPost withAiGenerated(@Nullable Boolean aiGenerated) {
        this.aiGenerated = aiGenerated;
        return this;
    }

    /**
     * The app or device the platform says the post was made with.
     */
    public ScreeningPost withPostedWith(@Nullable String postedWith) {
        this.postedWith = postedWith;
        return this;
    }

    /**
     * Everything else the platform sent about the post, under the platform's own names and as it typed
     * them: counts, ids of linked posts and accounts, links, media details, labels and notices. The fields
     * above are not repeated here. Nothing that describes the account the screening read with, such as
     * whether it liked or saved the post, is ever here.
     */
    public ScreeningPost withStated(@Nullable Map<String, Object> stated) {
        this.stated = stated;
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
                && Utils.enhancedDeepEquals(this.place, other.place)
                && Utils.enhancedDeepEquals(this.tagged, other.tagged)
                && Utils.enhancedDeepEquals(this.likes, other.likes)
                && Utils.enhancedDeepEquals(this.comments, other.comments)
                && Utils.enhancedDeepEquals(this.views, other.views)
                && Utils.enhancedDeepEquals(this.shares, other.shares)
                && Utils.enhancedDeepEquals(this.language, other.language)
                && Utils.enhancedDeepEquals(this.edited, other.edited)
                && Utils.enhancedDeepEquals(this.sponsored, other.sponsored)
                && Utils.enhancedDeepEquals(this.aiGenerated, other.aiGenerated)
                && Utils.enhancedDeepEquals(this.postedWith, other.postedWith)
                && Utils.enhancedDeepEquals(this.stated, other.stated)
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
                place,
                tagged,
                likes,
                comments,
                views,
                shares,
                language,
                edited,
                sponsored,
                aiGenerated,
                postedWith,
                stated,
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
                "place",
                place,
                "tagged",
                tagged,
                "likes",
                likes,
                "comments",
                comments,
                "views",
                views,
                "shares",
                shares,
                "language",
                language,
                "edited",
                edited,
                "sponsored",
                sponsored,
                "aiGenerated",
                aiGenerated,
                "postedWith",
                postedWith,
                "stated",
                stated,
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

        private PostPlace place;

        private List<String> tagged;

        private Long likes;

        private Long comments;

        private Long views;

        private Long shares;

        private String language;

        private Boolean edited;

        private Boolean sponsored;

        private Boolean aiGenerated;

        private String postedWith;

        private Map<String, Object> stated;

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

        /**
         * A place a post names, as the platform shows it.
         */
        public Builder place(@Nullable PostPlace place) {
            this.place = place;
            return this;
        }

        /**
         * The handles of the accounts the post tags, on its own platform.
         */
        public Builder tagged(@Nullable List<String> tagged) {
            this.tagged = tagged;
            return this;
        }

        /**
         * How many likes the platform shows for the post.
         */
        public Builder likes(@Nullable Long likes) {
            this.likes = likes;
            return this;
        }

        /**
         * How many comments the platform shows for the post.
         */
        public Builder comments(@Nullable Long comments) {
            this.comments = comments;
            return this;
        }

        /**
         * How many views or plays the platform shows for the post.
         */
        public Builder views(@Nullable Long views) {
            this.views = views;
            return this;
        }

        /**
         * How many times the platform shows the post was shared or reposted.
         */
        public Builder shares(@Nullable Long shares) {
            this.shares = shares;
            return this;
        }

        /**
         * The language the platform says the post is in, as the platform codes it, such as en or pt-BR.
         */
        public Builder language(@Nullable String language) {
            this.language = language;
            return this;
        }

        /**
         * true when the platform marks the post as edited after it went up. Absent when it does not mark it.
         */
        public Builder edited(@Nullable Boolean edited) {
            this.edited = edited;
            return this;
        }

        /**
         * true when the platform marks the post as a paid partnership or an ad. Absent when it does not mark
         * it.
         */
        public Builder sponsored(@Nullable Boolean sponsored) {
            this.sponsored = sponsored;
            return this;
        }

        /**
         * true when the platform labels the post as made or changed with AI. Absent when it does not label it.
         */
        public Builder aiGenerated(@Nullable Boolean aiGenerated) {
            this.aiGenerated = aiGenerated;
            return this;
        }

        /**
         * The app or device the platform says the post was made with.
         */
        public Builder postedWith(@Nullable String postedWith) {
            this.postedWith = postedWith;
            return this;
        }

        /**
         * Everything else the platform sent about the post, under the platform's own names and as it typed
         * them: counts, ids of linked posts and accounts, links, media details, labels and notices. The fields
         * above are not repeated here. Nothing that describes the account the screening read with, such as
         * whether it liked or saved the post, is ever here.
         */
        public Builder stated(@Nullable Map<String, Object> stated) {
            this.stated = stated;
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
                    id,
                    text,
                    replyTo,
                    repostOf,
                    quoteOf,
                    place,
                    tagged,
                    likes,
                    comments,
                    views,
                    shares,
                    language,
                    edited,
                    sponsored,
                    aiGenerated,
                    postedWith,
                    stated,
                    profileId,
                    permalink,
                    postedAt,
                    screenshotSha256,
                    capturedAt);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Kind =
                new LazySingletonValue<>("kind", "\"post\"", new TypeReference<String>() {});
    }
}
