package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AttendeeReport
 *
 * <p>Everything known about one attendee, and where else in the environment they appear.
 */
public class AttendeeReport {
    /**
     * An attendee and where their screening and review stand.
     */
    @JsonProperty("attendee")
    private Attendee attendee;

    /**
     * The accounts the latest screening matched, with how each was matched.
     */
    @JsonProperty("profiles")
    private List<ScreeningProfile> profiles;

    /**
     * What the latest screening flagged, most severe first, each citing its evidence.
     */
    @JsonProperty("findings")
    private List<ScreeningFinding> findings;

    /**
     * The posts the findings cite.
     */
    @JsonProperty("posts")
    private List<ScreeningPost> posts;

    /**
     * The images and videos the findings cite.
     */
    @JsonProperty("media")
    private List<ScreeningMedia> media;

    /**
     * Every recorded decision, oldest first.
     */
    @JsonProperty("reviews")
    private List<AttendeeReview> reviews;

    /**
     * What the signup check's lookups found for an attendee: account presence for the email, phone and
     * usernames (the attendee's handles), the phone lookup, the email domain's registration and the breach
     * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
     * state.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("lookup")
    private ScreeningLookup lookup;

    /**
     * Where else in the environment the attendee appears, read live from those events: deleting one
     * removes it here.
     */
    @JsonProperty("other_events")
    private OtherEvents otherEvents;

    @JsonCreator
    public AttendeeReport(
            @JsonProperty("attendee") @Nonnull Attendee attendee,
            @JsonProperty("profiles") @Nonnull List<ScreeningProfile> profiles,
            @JsonProperty("findings") @Nonnull List<ScreeningFinding> findings,
            @JsonProperty("posts") @Nonnull List<ScreeningPost> posts,
            @JsonProperty("media") @Nonnull List<ScreeningMedia> media,
            @JsonProperty("reviews") @Nonnull List<AttendeeReview> reviews,
            @JsonProperty("lookup") @Nullable ScreeningLookup lookup,
            @JsonProperty("other_events") @Nonnull OtherEvents otherEvents) {
        this.attendee = Optional.ofNullable(attendee)
                .orElseThrow(() -> new IllegalArgumentException("attendee cannot be null"));
        this.profiles = Optional.ofNullable(profiles)
                .orElseThrow(() -> new IllegalArgumentException("profiles cannot be null"));
        this.findings = Optional.ofNullable(findings)
                .orElseThrow(() -> new IllegalArgumentException("findings cannot be null"));
        this.posts = Optional.ofNullable(posts).orElseThrow(() -> new IllegalArgumentException("posts cannot be null"));
        this.media = Optional.ofNullable(media).orElseThrow(() -> new IllegalArgumentException("media cannot be null"));
        this.reviews =
                Optional.ofNullable(reviews).orElseThrow(() -> new IllegalArgumentException("reviews cannot be null"));
        this.lookup = lookup;
        this.otherEvents = Optional.ofNullable(otherEvents)
                .orElseThrow(() -> new IllegalArgumentException("otherEvents cannot be null"));
    }

    public AttendeeReport(
            @Nonnull Attendee attendee,
            @Nonnull List<ScreeningProfile> profiles,
            @Nonnull List<ScreeningFinding> findings,
            @Nonnull List<ScreeningPost> posts,
            @Nonnull List<ScreeningMedia> media,
            @Nonnull List<AttendeeReview> reviews,
            @Nonnull OtherEvents otherEvents) {
        this(attendee, profiles, findings, posts, media, reviews, null, otherEvents);
    }

    /**
     * An attendee and where their screening and review stand.
     */
    public Attendee attendee() {
        return this.attendee;
    }

    /**
     * The accounts the latest screening matched, with how each was matched.
     */
    public List<ScreeningProfile> profiles() {
        return this.profiles;
    }

    /**
     * What the latest screening flagged, most severe first, each citing its evidence.
     */
    public List<ScreeningFinding> findings() {
        return this.findings;
    }

    /**
     * The posts the findings cite.
     */
    public List<ScreeningPost> posts() {
        return this.posts;
    }

    /**
     * The images and videos the findings cite.
     */
    public List<ScreeningMedia> media() {
        return this.media;
    }

    /**
     * Every recorded decision, oldest first.
     */
    public List<AttendeeReview> reviews() {
        return this.reviews;
    }

    /**
     * What the signup check's lookups found for an attendee: account presence for the email, phone and
     * usernames (the attendee's handles), the phone lookup, the email domain's registration and the breach
     * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
     * state.
     */
    public Optional<ScreeningLookup> lookup() {
        return Optional.ofNullable(this.lookup);
    }

    /**
     * Where else in the environment the attendee appears, read live from those events: deleting one
     * removes it here.
     */
    public OtherEvents otherEvents() {
        return this.otherEvents;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * An attendee and where their screening and review stand.
     */
    public AttendeeReport withAttendee(@Nonnull Attendee attendee) {
        this.attendee = Utils.checkNotNull(attendee, "attendee");
        return this;
    }

    /**
     * The accounts the latest screening matched, with how each was matched.
     */
    public AttendeeReport withProfiles(@Nonnull List<ScreeningProfile> profiles) {
        this.profiles = Utils.checkNotNull(profiles, "profiles");
        return this;
    }

    /**
     * What the latest screening flagged, most severe first, each citing its evidence.
     */
    public AttendeeReport withFindings(@Nonnull List<ScreeningFinding> findings) {
        this.findings = Utils.checkNotNull(findings, "findings");
        return this;
    }

    /**
     * The posts the findings cite.
     */
    public AttendeeReport withPosts(@Nonnull List<ScreeningPost> posts) {
        this.posts = Utils.checkNotNull(posts, "posts");
        return this;
    }

    /**
     * The images and videos the findings cite.
     */
    public AttendeeReport withMedia(@Nonnull List<ScreeningMedia> media) {
        this.media = Utils.checkNotNull(media, "media");
        return this;
    }

    /**
     * Every recorded decision, oldest first.
     */
    public AttendeeReport withReviews(@Nonnull List<AttendeeReview> reviews) {
        this.reviews = Utils.checkNotNull(reviews, "reviews");
        return this;
    }

    /**
     * What the signup check's lookups found for an attendee: account presence for the email, phone and
     * usernames (the attendee's handles), the phone lookup, the email domain's registration and the breach
     * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
     * state.
     */
    public AttendeeReport withLookup(@Nullable ScreeningLookup lookup) {
        this.lookup = lookup;
        return this;
    }

    /**
     * Where else in the environment the attendee appears, read live from those events: deleting one
     * removes it here.
     */
    public AttendeeReport withOtherEvents(@Nonnull OtherEvents otherEvents) {
        this.otherEvents = Utils.checkNotNull(otherEvents, "otherEvents");
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
        AttendeeReport other = (AttendeeReport) o;
        return Utils.enhancedDeepEquals(this.attendee, other.attendee)
                && Utils.enhancedDeepEquals(this.profiles, other.profiles)
                && Utils.enhancedDeepEquals(this.findings, other.findings)
                && Utils.enhancedDeepEquals(this.posts, other.posts)
                && Utils.enhancedDeepEquals(this.media, other.media)
                && Utils.enhancedDeepEquals(this.reviews, other.reviews)
                && Utils.enhancedDeepEquals(this.lookup, other.lookup)
                && Utils.enhancedDeepEquals(this.otherEvents, other.otherEvents);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(attendee, profiles, findings, posts, media, reviews, lookup, otherEvents);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AttendeeReport.class,
                "attendee",
                attendee,
                "profiles",
                profiles,
                "findings",
                findings,
                "posts",
                posts,
                "media",
                media,
                "reviews",
                reviews,
                "lookup",
                lookup,
                "otherEvents",
                otherEvents);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Attendee attendee;

        private List<ScreeningProfile> profiles;

        private List<ScreeningFinding> findings;

        private List<ScreeningPost> posts;

        private List<ScreeningMedia> media;

        private List<AttendeeReview> reviews;

        private ScreeningLookup lookup;

        private OtherEvents otherEvents;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * An attendee and where their screening and review stand.
         */
        public Builder attendee(@Nonnull Attendee attendee) {
            this.attendee = Utils.checkNotNull(attendee, "attendee");
            return this;
        }

        /**
         * The accounts the latest screening matched, with how each was matched.
         */
        public Builder profiles(@Nonnull List<ScreeningProfile> profiles) {
            this.profiles = Utils.checkNotNull(profiles, "profiles");
            return this;
        }

        /**
         * What the latest screening flagged, most severe first, each citing its evidence.
         */
        public Builder findings(@Nonnull List<ScreeningFinding> findings) {
            this.findings = Utils.checkNotNull(findings, "findings");
            return this;
        }

        /**
         * The posts the findings cite.
         */
        public Builder posts(@Nonnull List<ScreeningPost> posts) {
            this.posts = Utils.checkNotNull(posts, "posts");
            return this;
        }

        /**
         * The images and videos the findings cite.
         */
        public Builder media(@Nonnull List<ScreeningMedia> media) {
            this.media = Utils.checkNotNull(media, "media");
            return this;
        }

        /**
         * Every recorded decision, oldest first.
         */
        public Builder reviews(@Nonnull List<AttendeeReview> reviews) {
            this.reviews = Utils.checkNotNull(reviews, "reviews");
            return this;
        }

        /**
         * What the signup check's lookups found for an attendee: account presence for the email, phone and
         * usernames (the attendee's handles), the phone lookup, the email domain's registration and the breach
         * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
         * state.
         */
        public Builder lookup(@Nullable ScreeningLookup lookup) {
            this.lookup = lookup;
            return this;
        }

        /**
         * Where else in the environment the attendee appears, read live from those events: deleting one
         * removes it here.
         */
        public Builder otherEvents(@Nonnull OtherEvents otherEvents) {
            this.otherEvents = Utils.checkNotNull(otherEvents, "otherEvents");
            return this;
        }

        public AttendeeReport build() {
            return new AttendeeReport(attendee, profiles, findings, posts, media, reviews, lookup, otherEvents);
        }
    }
}
