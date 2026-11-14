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
 * PersonReport
 *
 * <p>Everything known about one person, and where else in the environment they appear.
 */
public class PersonReport {
    /**
     * A person and where their screening and review stand.
     */
    @JsonProperty("person")
    private Person person;

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
    private List<PersonReview> reviews;

    /**
     * What the signup check's lookups found for a person: account presence for the email, phone and
     * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
     * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
     * state.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("lookup")
    private ScreeningLookup lookup;

    /**
     * Where else in the environment the person appears, read live from those groups: deleting one removes
     * it here.
     */
    @JsonProperty("other_groups")
    private OtherGroups otherGroups;

    @JsonCreator
    public PersonReport(
            @JsonProperty("person") @Nonnull Person person,
            @JsonProperty("profiles") @Nonnull List<ScreeningProfile> profiles,
            @JsonProperty("findings") @Nonnull List<ScreeningFinding> findings,
            @JsonProperty("posts") @Nonnull List<ScreeningPost> posts,
            @JsonProperty("media") @Nonnull List<ScreeningMedia> media,
            @JsonProperty("reviews") @Nonnull List<PersonReview> reviews,
            @JsonProperty("lookup") @Nullable ScreeningLookup lookup,
            @JsonProperty("other_groups") @Nonnull OtherGroups otherGroups) {
        this.person =
                Optional.ofNullable(person).orElseThrow(() -> new IllegalArgumentException("person cannot be null"));
        this.profiles = Optional.ofNullable(profiles)
                .orElseThrow(() -> new IllegalArgumentException("profiles cannot be null"));
        this.findings = Optional.ofNullable(findings)
                .orElseThrow(() -> new IllegalArgumentException("findings cannot be null"));
        this.posts = Optional.ofNullable(posts).orElseThrow(() -> new IllegalArgumentException("posts cannot be null"));
        this.media = Optional.ofNullable(media).orElseThrow(() -> new IllegalArgumentException("media cannot be null"));
        this.reviews =
                Optional.ofNullable(reviews).orElseThrow(() -> new IllegalArgumentException("reviews cannot be null"));
        this.lookup = lookup;
        this.otherGroups = Optional.ofNullable(otherGroups)
                .orElseThrow(() -> new IllegalArgumentException("otherGroups cannot be null"));
    }

    public PersonReport(
            @Nonnull Person person,
            @Nonnull List<ScreeningProfile> profiles,
            @Nonnull List<ScreeningFinding> findings,
            @Nonnull List<ScreeningPost> posts,
            @Nonnull List<ScreeningMedia> media,
            @Nonnull List<PersonReview> reviews,
            @Nonnull OtherGroups otherGroups) {
        this(person, profiles, findings, posts, media, reviews, null, otherGroups);
    }

    /**
     * A person and where their screening and review stand.
     */
    public Person person() {
        return this.person;
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
    public List<PersonReview> reviews() {
        return this.reviews;
    }

    /**
     * What the signup check's lookups found for a person: account presence for the email, phone and
     * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
     * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
     * state.
     */
    public Optional<ScreeningLookup> lookup() {
        return Optional.ofNullable(this.lookup);
    }

    /**
     * Where else in the environment the person appears, read live from those groups: deleting one removes
     * it here.
     */
    public OtherGroups otherGroups() {
        return this.otherGroups;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A person and where their screening and review stand.
     */
    public PersonReport withPerson(@Nonnull Person person) {
        this.person = Utils.checkNotNull(person, "person");
        return this;
    }

    /**
     * The accounts the latest screening matched, with how each was matched.
     */
    public PersonReport withProfiles(@Nonnull List<ScreeningProfile> profiles) {
        this.profiles = Utils.checkNotNull(profiles, "profiles");
        return this;
    }

    /**
     * What the latest screening flagged, most severe first, each citing its evidence.
     */
    public PersonReport withFindings(@Nonnull List<ScreeningFinding> findings) {
        this.findings = Utils.checkNotNull(findings, "findings");
        return this;
    }

    /**
     * The posts the findings cite.
     */
    public PersonReport withPosts(@Nonnull List<ScreeningPost> posts) {
        this.posts = Utils.checkNotNull(posts, "posts");
        return this;
    }

    /**
     * The images and videos the findings cite.
     */
    public PersonReport withMedia(@Nonnull List<ScreeningMedia> media) {
        this.media = Utils.checkNotNull(media, "media");
        return this;
    }

    /**
     * Every recorded decision, oldest first.
     */
    public PersonReport withReviews(@Nonnull List<PersonReview> reviews) {
        this.reviews = Utils.checkNotNull(reviews, "reviews");
        return this;
    }

    /**
     * What the signup check's lookups found for a person: account presence for the email, phone and
     * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
     * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
     * state.
     */
    public PersonReport withLookup(@Nullable ScreeningLookup lookup) {
        this.lookup = lookup;
        return this;
    }

    /**
     * Where else in the environment the person appears, read live from those groups: deleting one removes
     * it here.
     */
    public PersonReport withOtherGroups(@Nonnull OtherGroups otherGroups) {
        this.otherGroups = Utils.checkNotNull(otherGroups, "otherGroups");
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
        PersonReport other = (PersonReport) o;
        return Utils.enhancedDeepEquals(this.person, other.person)
                && Utils.enhancedDeepEquals(this.profiles, other.profiles)
                && Utils.enhancedDeepEquals(this.findings, other.findings)
                && Utils.enhancedDeepEquals(this.posts, other.posts)
                && Utils.enhancedDeepEquals(this.media, other.media)
                && Utils.enhancedDeepEquals(this.reviews, other.reviews)
                && Utils.enhancedDeepEquals(this.lookup, other.lookup)
                && Utils.enhancedDeepEquals(this.otherGroups, other.otherGroups);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(person, profiles, findings, posts, media, reviews, lookup, otherGroups);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonReport.class,
                "person",
                person,
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
                "otherGroups",
                otherGroups);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Person person;

        private List<ScreeningProfile> profiles;

        private List<ScreeningFinding> findings;

        private List<ScreeningPost> posts;

        private List<ScreeningMedia> media;

        private List<PersonReview> reviews;

        private ScreeningLookup lookup;

        private OtherGroups otherGroups;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * A person and where their screening and review stand.
         */
        public Builder person(@Nonnull Person person) {
            this.person = Utils.checkNotNull(person, "person");
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
        public Builder reviews(@Nonnull List<PersonReview> reviews) {
            this.reviews = Utils.checkNotNull(reviews, "reviews");
            return this;
        }

        /**
         * What the signup check's lookups found for a person: account presence for the email, phone and
         * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
         * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
         * state.
         */
        public Builder lookup(@Nullable ScreeningLookup lookup) {
            this.lookup = lookup;
            return this;
        }

        /**
         * Where else in the environment the person appears, read live from those groups: deleting one removes
         * it here.
         */
        public Builder otherGroups(@Nonnull OtherGroups otherGroups) {
            this.otherGroups = Utils.checkNotNull(otherGroups, "otherGroups");
            return this;
        }

        public PersonReport build() {
            return new PersonReport(person, profiles, findings, posts, media, reviews, lookup, otherGroups);
        }
    }
}
