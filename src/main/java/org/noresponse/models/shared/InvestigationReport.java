package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * InvestigationReport
 *
 * <p>Read an investigation and its evidence.
 */
public class InvestigationReport {
    /**
     * The accounts this result matched, with how each was matched.
     */
    @JsonProperty("profiles")
    private List<ScreeningProfile> profiles;

    /**
     * All posts retained for this result.
     */
    @JsonProperty("posts")
    private List<ScreeningPost> posts;

    /**
     * All images and videos retained for this result.
     */
    @JsonProperty("media")
    private List<ScreeningMedia> media;

    /**
     * What the signup check's lookups found for a person: account presence for the email, phone and
     * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
     * search. Nothing here sets a match state.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("lookup")
    private ScreeningLookup lookup;

    /**
     * True when evidence.read is explicitly denied. Submitted details, coverage and job status remain
     * available. Collected records, profiles, posts, media and discovered values are withheld.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("evidence_restricted")
    private Boolean evidenceRestricted;

    @JsonProperty("person")
    private WorkspacePerson person;

    @JsonProperty("investigation")
    private Investigation investigation;

    @JsonCreator
    public InvestigationReport(
            @JsonProperty("profiles") @Nonnull List<ScreeningProfile> profiles,
            @JsonProperty("posts") @Nonnull List<ScreeningPost> posts,
            @JsonProperty("media") @Nonnull List<ScreeningMedia> media,
            @JsonProperty("lookup") @Nullable ScreeningLookup lookup,
            @JsonProperty("evidence_restricted") @Nullable Boolean evidenceRestricted,
            @JsonProperty("person") @Nonnull WorkspacePerson person,
            @JsonProperty("investigation") @Nonnull Investigation investigation) {
        this.profiles = Optional.ofNullable(profiles)
                .orElseThrow(() -> new IllegalArgumentException("profiles cannot be null"));
        this.posts = Optional.ofNullable(posts).orElseThrow(() -> new IllegalArgumentException("posts cannot be null"));
        this.media = Optional.ofNullable(media).orElseThrow(() -> new IllegalArgumentException("media cannot be null"));
        this.lookup = lookup;
        this.evidenceRestricted = evidenceRestricted;
        this.person =
                Optional.ofNullable(person).orElseThrow(() -> new IllegalArgumentException("person cannot be null"));
        this.investigation = Optional.ofNullable(investigation)
                .orElseThrow(() -> new IllegalArgumentException("investigation cannot be null"));
    }

    public InvestigationReport(
            @Nonnull List<ScreeningProfile> profiles,
            @Nonnull List<ScreeningPost> posts,
            @Nonnull List<ScreeningMedia> media,
            @Nonnull WorkspacePerson person,
            @Nonnull Investigation investigation) {
        this(profiles, posts, media, null, null, person, investigation);
    }

    /**
     * The accounts this result matched, with how each was matched.
     */
    public List<ScreeningProfile> profiles() {
        return this.profiles;
    }

    /**
     * All posts retained for this result.
     */
    public List<ScreeningPost> posts() {
        return this.posts;
    }

    /**
     * All images and videos retained for this result.
     */
    public List<ScreeningMedia> media() {
        return this.media;
    }

    /**
     * What the signup check's lookups found for a person: account presence for the email, phone and
     * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
     * search. Nothing here sets a match state.
     */
    public Optional<ScreeningLookup> lookup() {
        return Optional.ofNullable(this.lookup);
    }

    /**
     * True when evidence.read is explicitly denied. Submitted details, coverage and job status remain
     * available. Collected records, profiles, posts, media and discovered values are withheld.
     */
    public Optional<Boolean> evidenceRestricted() {
        return Optional.ofNullable(this.evidenceRestricted);
    }

    public WorkspacePerson person() {
        return this.person;
    }

    public Investigation investigation() {
        return this.investigation;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The accounts this result matched, with how each was matched.
     */
    public InvestigationReport withProfiles(@Nonnull List<ScreeningProfile> profiles) {
        this.profiles = Utils.checkNotNull(profiles, "profiles");
        return this;
    }

    /**
     * All posts retained for this result.
     */
    public InvestigationReport withPosts(@Nonnull List<ScreeningPost> posts) {
        this.posts = Utils.checkNotNull(posts, "posts");
        return this;
    }

    /**
     * All images and videos retained for this result.
     */
    public InvestigationReport withMedia(@Nonnull List<ScreeningMedia> media) {
        this.media = Utils.checkNotNull(media, "media");
        return this;
    }

    /**
     * What the signup check's lookups found for a person: account presence for the email, phone and
     * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
     * search. Nothing here sets a match state.
     */
    public InvestigationReport withLookup(@Nullable ScreeningLookup lookup) {
        this.lookup = lookup;
        return this;
    }

    /**
     * True when evidence.read is explicitly denied. Submitted details, coverage and job status remain
     * available. Collected records, profiles, posts, media and discovered values are withheld.
     */
    public InvestigationReport withEvidenceRestricted(@Nullable Boolean evidenceRestricted) {
        this.evidenceRestricted = evidenceRestricted;
        return this;
    }

    public InvestigationReport withPerson(@Nonnull WorkspacePerson person) {
        this.person = Utils.checkNotNull(person, "person");
        return this;
    }

    public InvestigationReport withInvestigation(@Nonnull Investigation investigation) {
        this.investigation = Utils.checkNotNull(investigation, "investigation");
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
        InvestigationReport other = (InvestigationReport) o;
        return Utils.enhancedDeepEquals(this.profiles, other.profiles)
                && Utils.enhancedDeepEquals(this.posts, other.posts)
                && Utils.enhancedDeepEquals(this.media, other.media)
                && Utils.enhancedDeepEquals(this.lookup, other.lookup)
                && Utils.enhancedDeepEquals(this.evidenceRestricted, other.evidenceRestricted)
                && Utils.enhancedDeepEquals(this.person, other.person)
                && Utils.enhancedDeepEquals(this.investigation, other.investigation);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(profiles, posts, media, lookup, evidenceRestricted, person, investigation);
    }

    @Override
    public String toString() {
        return Utils.toString(
                InvestigationReport.class,
                "profiles",
                profiles,
                "posts",
                posts,
                "media",
                media,
                "lookup",
                lookup,
                "evidenceRestricted",
                evidenceRestricted,
                "person",
                person,
                "investigation",
                investigation);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<ScreeningProfile> profiles;

        private List<ScreeningPost> posts;

        private List<ScreeningMedia> media;

        private ScreeningLookup lookup;

        private Boolean evidenceRestricted;

        private WorkspacePerson person;

        private Investigation investigation;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The accounts this result matched, with how each was matched.
         */
        public Builder profiles(@Nonnull List<ScreeningProfile> profiles) {
            this.profiles = Utils.checkNotNull(profiles, "profiles");
            return this;
        }

        /**
         * All posts retained for this result.
         */
        public Builder posts(@Nonnull List<ScreeningPost> posts) {
            this.posts = Utils.checkNotNull(posts, "posts");
            return this;
        }

        /**
         * All images and videos retained for this result.
         */
        public Builder media(@Nonnull List<ScreeningMedia> media) {
            this.media = Utils.checkNotNull(media, "media");
            return this;
        }

        /**
         * What the signup check's lookups found for a person: account presence for the email, phone and
         * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
         * search. Nothing here sets a match state.
         */
        public Builder lookup(@Nullable ScreeningLookup lookup) {
            this.lookup = lookup;
            return this;
        }

        /**
         * True when evidence.read is explicitly denied. Submitted details, coverage and job status remain
         * available. Collected records, profiles, posts, media and discovered values are withheld.
         */
        public Builder evidenceRestricted(@Nullable Boolean evidenceRestricted) {
            this.evidenceRestricted = evidenceRestricted;
            return this;
        }

        public Builder person(@Nonnull WorkspacePerson person) {
            this.person = Utils.checkNotNull(person, "person");
            return this;
        }

        public Builder investigation(@Nonnull Investigation investigation) {
            this.investigation = Utils.checkNotNull(investigation, "investigation");
            return this;
        }

        public InvestigationReport build() {
            return new InvestigationReport(profiles, posts, media, lookup, evidenceRestricted, person, investigation);
        }
    }
}
