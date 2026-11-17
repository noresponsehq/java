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
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * WorkspacePersonReport
 *
 * <p>Read a workspace person.
 */
public class WorkspacePersonReport {
    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_id")
    private String externalId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private String name;

    /**
     * The person's email.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    /**
     * The person's phone in E.164 form.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private String phone;

    /**
     * Where the person works.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("company")
    private String company;

    /**
     * Where the person lives.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("city")
    private String city;

    /**
     * A username the person uses, with no platform. A search looks it up on every platform that finds
     * accounts by username.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("username")
    private String username;

    /**
     * Accounts the person is known to hold, at most 20.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("handles")
    private List<Handle> handles;

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * The time the workspace retention policy removes this person, their evidence and investigation
     * history. Shortening workspace retention can move this time earlier.
     */
    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * The accounts this result matched, with how each was matched.
     */
    @JsonProperty("profiles")
    private List<ScreeningProfile> profiles;

    /**
     * What this result flagged, most severe first, each citing its evidence.
     */
    @JsonProperty("findings")
    private List<ScreeningFinding> findings;

    /**
     * All posts retained for this result, including posts that no finding cites.
     */
    @JsonProperty("posts")
    private List<ScreeningPost> posts;

    /**
     * All images and videos retained for this result, including media that no finding cites.
     */
    @JsonProperty("media")
    private List<ScreeningMedia> media;

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
     * Every search on this person's values, newest first, whether a person started it or enrichment did:
     * each names the value, the record and field it came from, and the search that found that record.
     * Absent when nothing was searched.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("searches")
    private List<Search> searches;

    /**
     * The searches enrichment would start on this person, likeliest record first, under the environment's
     * enrichment setting for a person's own investigation. In off mode the list is empty. In suggest mode
     * enrichment starts none of them by itself; in automatic mode it starts them as its limits allow.
     *
     * <p>Empty when evidence.read is denied.
     */
    @JsonProperty("suggested_searches")
    private List<SuggestedSearch> suggestedSearches;

    /**
     * True when evidence.read is explicitly denied. Submitted details, coverage, job status and decisions
     * remain available. Collected records, profiles, findings, posts, media, discovered values and
     * free-text decision notes are withheld.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("evidence_restricted")
    private Boolean evidenceRestricted;

    /**
     * The most recent retained completed or partial investigation, or a migrated result whose original
     * per-person status is unknown. The evidence below belongs to this investigation. Read investigation
     * history for earlier results and pending jobs.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("evidence_investigation")
    private JsonNullable<Investigation> evidenceInvestigation;

    /**
     * An investigator's profile of this person, written from the evidence of a settled investigation. Null
     * until an investigation completes or partially completes, and null when evidence.read is denied.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("written_profile")
    private JsonNullable<WrittenProfile> writtenProfile;

    @JsonCreator
    public WorkspacePersonReport(
            @JsonProperty("external_id") @Nullable String externalId,
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("company") @Nullable String company,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("username") @Nullable String username,
            @JsonProperty("handles") @Nullable List<Handle> handles,
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("profiles") @Nonnull List<ScreeningProfile> profiles,
            @JsonProperty("findings") @Nonnull List<ScreeningFinding> findings,
            @JsonProperty("posts") @Nonnull List<ScreeningPost> posts,
            @JsonProperty("media") @Nonnull List<ScreeningMedia> media,
            @JsonProperty("lookup") @Nullable ScreeningLookup lookup,
            @JsonProperty("searches") @Nullable List<Search> searches,
            @JsonProperty("suggested_searches") @Nonnull List<SuggestedSearch> suggestedSearches,
            @JsonProperty("evidence_restricted") @Nullable Boolean evidenceRestricted,
            @JsonProperty("evidence_investigation") @Nullable Investigation evidenceInvestigation,
            @JsonProperty("written_profile") @Nullable WrittenProfile writtenProfile) {
        this.externalId = externalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.city = city;
        this.username = username;
        this.handles = handles;
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.profiles = Optional.ofNullable(profiles)
                .orElseThrow(() -> new IllegalArgumentException("profiles cannot be null"));
        this.findings = Optional.ofNullable(findings)
                .orElseThrow(() -> new IllegalArgumentException("findings cannot be null"));
        this.posts = Optional.ofNullable(posts).orElseThrow(() -> new IllegalArgumentException("posts cannot be null"));
        this.media = Optional.ofNullable(media).orElseThrow(() -> new IllegalArgumentException("media cannot be null"));
        this.lookup = lookup;
        this.searches = searches;
        this.suggestedSearches = Optional.ofNullable(suggestedSearches)
                .orElseThrow(() -> new IllegalArgumentException("suggestedSearches cannot be null"));
        this.evidenceRestricted = evidenceRestricted;
        this.evidenceInvestigation = JsonNullable.of(evidenceInvestigation);
        this.writtenProfile = JsonNullable.of(writtenProfile);
    }

    public WorkspacePersonReport(
            @Nonnull String id,
            @Nonnull String createdAt,
            @Nonnull String updatedAt,
            @Nonnull String expiresAt,
            @Nonnull List<ScreeningProfile> profiles,
            @Nonnull List<ScreeningFinding> findings,
            @Nonnull List<ScreeningPost> posts,
            @Nonnull List<ScreeningMedia> media,
            @Nonnull List<SuggestedSearch> suggestedSearches) {
        this(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                id,
                createdAt,
                updatedAt,
                expiresAt,
                profiles,
                findings,
                posts,
                media,
                null,
                null,
                suggestedSearches,
                null,
                null,
                null);
    }

    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    public Optional<String> externalId() {
        return Optional.ofNullable(this.externalId);
    }

    public Optional<String> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * The person's email.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * The person's phone in E.164 form.
     */
    public Optional<String> phone() {
        return Optional.ofNullable(this.phone);
    }

    /**
     * Where the person works.
     */
    public Optional<String> company() {
        return Optional.ofNullable(this.company);
    }

    /**
     * Where the person lives.
     */
    public Optional<String> city() {
        return Optional.ofNullable(this.city);
    }

    /**
     * A username the person uses, with no platform. A search looks it up on every platform that finds
     * accounts by username.
     */
    public Optional<String> username() {
        return Optional.ofNullable(this.username);
    }

    /**
     * Accounts the person is known to hold, at most 20.
     */
    public Optional<List<Handle>> handles() {
        return Optional.ofNullable(this.handles);
    }

    public String id() {
        return this.id;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    /**
     * The time the workspace retention policy removes this person, their evidence and investigation
     * history. Shortening workspace retention can move this time earlier.
     */
    public String expiresAt() {
        return this.expiresAt;
    }

    /**
     * The accounts this result matched, with how each was matched.
     */
    public List<ScreeningProfile> profiles() {
        return this.profiles;
    }

    /**
     * What this result flagged, most severe first, each citing its evidence.
     */
    public List<ScreeningFinding> findings() {
        return this.findings;
    }

    /**
     * All posts retained for this result, including posts that no finding cites.
     */
    public List<ScreeningPost> posts() {
        return this.posts;
    }

    /**
     * All images and videos retained for this result, including media that no finding cites.
     */
    public List<ScreeningMedia> media() {
        return this.media;
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
     * Every search on this person's values, newest first, whether a person started it or enrichment did:
     * each names the value, the record and field it came from, and the search that found that record.
     * Absent when nothing was searched.
     */
    public Optional<List<Search>> searches() {
        return Optional.ofNullable(this.searches);
    }

    /**
     * The searches enrichment would start on this person, likeliest record first, under the environment's
     * enrichment setting for a person's own investigation. In off mode the list is empty. In suggest mode
     * enrichment starts none of them by itself; in automatic mode it starts them as its limits allow.
     *
     * <p>Empty when evidence.read is denied.
     */
    public List<SuggestedSearch> suggestedSearches() {
        return this.suggestedSearches;
    }

    /**
     * True when evidence.read is explicitly denied. Submitted details, coverage, job status and decisions
     * remain available. Collected records, profiles, findings, posts, media, discovered values and
     * free-text decision notes are withheld.
     */
    public Optional<Boolean> evidenceRestricted() {
        return Optional.ofNullable(this.evidenceRestricted);
    }

    /**
     * The most recent retained completed or partial investigation, or a migrated result whose original
     * per-person status is unknown. The evidence below belongs to this investigation. Read investigation
     * history for earlier results and pending jobs.
     */
    public JsonNullable<Investigation> evidenceInvestigation() {
        return this.evidenceInvestigation;
    }

    /**
     * An investigator's profile of this person, written from the evidence of a settled investigation. Null
     * until an investigation completes or partially completes, and null when evidence.read is denied.
     */
    public JsonNullable<WrittenProfile> writtenProfile() {
        return this.writtenProfile;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    public WorkspacePersonReport withExternalId(@Nullable String externalId) {
        this.externalId = externalId;
        return this;
    }

    public WorkspacePersonReport withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The person's email.
     */
    public WorkspacePersonReport withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The person's phone in E.164 form.
     */
    public WorkspacePersonReport withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Where the person works.
     */
    public WorkspacePersonReport withCompany(@Nullable String company) {
        this.company = company;
        return this;
    }

    /**
     * Where the person lives.
     */
    public WorkspacePersonReport withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    /**
     * A username the person uses, with no platform. A search looks it up on every platform that finds
     * accounts by username.
     */
    public WorkspacePersonReport withUsername(@Nullable String username) {
        this.username = username;
        return this;
    }

    /**
     * Accounts the person is known to hold, at most 20.
     */
    public WorkspacePersonReport withHandles(@Nullable List<Handle> handles) {
        this.handles = handles;
        return this;
    }

    public WorkspacePersonReport withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public WorkspacePersonReport withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public WorkspacePersonReport withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
        return this;
    }

    /**
     * The time the workspace retention policy removes this person, their evidence and investigation
     * history. Shortening workspace retention can move this time earlier.
     */
    public WorkspacePersonReport withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    /**
     * The accounts this result matched, with how each was matched.
     */
    public WorkspacePersonReport withProfiles(@Nonnull List<ScreeningProfile> profiles) {
        this.profiles = Utils.checkNotNull(profiles, "profiles");
        return this;
    }

    /**
     * What this result flagged, most severe first, each citing its evidence.
     */
    public WorkspacePersonReport withFindings(@Nonnull List<ScreeningFinding> findings) {
        this.findings = Utils.checkNotNull(findings, "findings");
        return this;
    }

    /**
     * All posts retained for this result, including posts that no finding cites.
     */
    public WorkspacePersonReport withPosts(@Nonnull List<ScreeningPost> posts) {
        this.posts = Utils.checkNotNull(posts, "posts");
        return this;
    }

    /**
     * All images and videos retained for this result, including media that no finding cites.
     */
    public WorkspacePersonReport withMedia(@Nonnull List<ScreeningMedia> media) {
        this.media = Utils.checkNotNull(media, "media");
        return this;
    }

    /**
     * What the signup check's lookups found for a person: account presence for the email, phone and
     * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
     * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
     * state.
     */
    public WorkspacePersonReport withLookup(@Nullable ScreeningLookup lookup) {
        this.lookup = lookup;
        return this;
    }

    /**
     * Every search on this person's values, newest first, whether a person started it or enrichment did:
     * each names the value, the record and field it came from, and the search that found that record.
     * Absent when nothing was searched.
     */
    public WorkspacePersonReport withSearches(@Nullable List<Search> searches) {
        this.searches = searches;
        return this;
    }

    /**
     * The searches enrichment would start on this person, likeliest record first, under the environment's
     * enrichment setting for a person's own investigation. In off mode the list is empty. In suggest mode
     * enrichment starts none of them by itself; in automatic mode it starts them as its limits allow.
     *
     * <p>Empty when evidence.read is denied.
     */
    public WorkspacePersonReport withSuggestedSearches(@Nonnull List<SuggestedSearch> suggestedSearches) {
        this.suggestedSearches = Utils.checkNotNull(suggestedSearches, "suggestedSearches");
        return this;
    }

    /**
     * True when evidence.read is explicitly denied. Submitted details, coverage, job status and decisions
     * remain available. Collected records, profiles, findings, posts, media, discovered values and
     * free-text decision notes are withheld.
     */
    public WorkspacePersonReport withEvidenceRestricted(@Nullable Boolean evidenceRestricted) {
        this.evidenceRestricted = evidenceRestricted;
        return this;
    }

    /**
     * The most recent retained completed or partial investigation, or a migrated result whose original
     * per-person status is unknown. The evidence below belongs to this investigation. Read investigation
     * history for earlier results and pending jobs.
     */
    public WorkspacePersonReport withEvidenceInvestigation(@Nullable Investigation evidenceInvestigation) {
        this.evidenceInvestigation = JsonNullable.of(evidenceInvestigation);
        return this;
    }

    /**
     * An investigator's profile of this person, written from the evidence of a settled investigation. Null
     * until an investigation completes or partially completes, and null when evidence.read is denied.
     */
    public WorkspacePersonReport withWrittenProfile(@Nullable WrittenProfile writtenProfile) {
        this.writtenProfile = JsonNullable.of(writtenProfile);
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
        WorkspacePersonReport other = (WorkspacePersonReport) o;
        return Utils.enhancedDeepEquals(this.externalId, other.externalId)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.company, other.company)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.username, other.username)
                && Utils.enhancedDeepEquals(this.handles, other.handles)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.profiles, other.profiles)
                && Utils.enhancedDeepEquals(this.findings, other.findings)
                && Utils.enhancedDeepEquals(this.posts, other.posts)
                && Utils.enhancedDeepEquals(this.media, other.media)
                && Utils.enhancedDeepEquals(this.lookup, other.lookup)
                && Utils.enhancedDeepEquals(this.searches, other.searches)
                && Utils.enhancedDeepEquals(this.suggestedSearches, other.suggestedSearches)
                && Utils.enhancedDeepEquals(this.evidenceRestricted, other.evidenceRestricted)
                && Utils.enhancedDeepEquals(this.evidenceInvestigation, other.evidenceInvestigation)
                && Utils.enhancedDeepEquals(this.writtenProfile, other.writtenProfile);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                externalId,
                name,
                email,
                phone,
                company,
                city,
                username,
                handles,
                id,
                createdAt,
                updatedAt,
                expiresAt,
                profiles,
                findings,
                posts,
                media,
                lookup,
                searches,
                suggestedSearches,
                evidenceRestricted,
                evidenceInvestigation,
                writtenProfile);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WorkspacePersonReport.class,
                "externalId",
                externalId,
                "name",
                name,
                "email",
                email,
                "phone",
                phone,
                "company",
                company,
                "city",
                city,
                "username",
                username,
                "handles",
                handles,
                "id",
                id,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt,
                "expiresAt",
                expiresAt,
                "profiles",
                profiles,
                "findings",
                findings,
                "posts",
                posts,
                "media",
                media,
                "lookup",
                lookup,
                "searches",
                searches,
                "suggestedSearches",
                suggestedSearches,
                "evidenceRestricted",
                evidenceRestricted,
                "evidenceInvestigation",
                evidenceInvestigation,
                "writtenProfile",
                writtenProfile);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String externalId;

        private String name;

        private String email;

        private String phone;

        private String company;

        private String city;

        private String username;

        private List<Handle> handles;

        private String id;

        private String createdAt;

        private String updatedAt;

        private String expiresAt;

        private List<ScreeningProfile> profiles;

        private List<ScreeningFinding> findings;

        private List<ScreeningPost> posts;

        private List<ScreeningMedia> media;

        private ScreeningLookup lookup;

        private List<Search> searches;

        private List<SuggestedSearch> suggestedSearches;

        private Boolean evidenceRestricted;

        private Investigation evidenceInvestigation;

        private WrittenProfile writtenProfile;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your reference for this person within the workspace environment. Only an explicit reference or
         * person_id can reuse an existing person.
         */
        public Builder externalId(@Nullable String externalId) {
            this.externalId = externalId;
            return this;
        }

        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        /**
         * The person's email.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * The person's phone in E.164 form.
         */
        public Builder phone(@Nullable String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Where the person works.
         */
        public Builder company(@Nullable String company) {
            this.company = company;
            return this;
        }

        /**
         * Where the person lives.
         */
        public Builder city(@Nullable String city) {
            this.city = city;
            return this;
        }

        /**
         * A username the person uses, with no platform. A search looks it up on every platform that finds
         * accounts by username.
         */
        public Builder username(@Nullable String username) {
            this.username = username;
            return this;
        }

        /**
         * Accounts the person is known to hold, at most 20.
         */
        public Builder handles(@Nullable List<Handle> handles) {
            this.handles = handles;
            return this;
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder updatedAt(@Nonnull String updatedAt) {
            this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
            return this;
        }

        /**
         * The time the workspace retention policy removes this person, their evidence and investigation
         * history. Shortening workspace retention can move this time earlier.
         */
        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        /**
         * The accounts this result matched, with how each was matched.
         */
        public Builder profiles(@Nonnull List<ScreeningProfile> profiles) {
            this.profiles = Utils.checkNotNull(profiles, "profiles");
            return this;
        }

        /**
         * What this result flagged, most severe first, each citing its evidence.
         */
        public Builder findings(@Nonnull List<ScreeningFinding> findings) {
            this.findings = Utils.checkNotNull(findings, "findings");
            return this;
        }

        /**
         * All posts retained for this result, including posts that no finding cites.
         */
        public Builder posts(@Nonnull List<ScreeningPost> posts) {
            this.posts = Utils.checkNotNull(posts, "posts");
            return this;
        }

        /**
         * All images and videos retained for this result, including media that no finding cites.
         */
        public Builder media(@Nonnull List<ScreeningMedia> media) {
            this.media = Utils.checkNotNull(media, "media");
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
         * Every search on this person's values, newest first, whether a person started it or enrichment did:
         * each names the value, the record and field it came from, and the search that found that record.
         * Absent when nothing was searched.
         */
        public Builder searches(@Nullable List<Search> searches) {
            this.searches = searches;
            return this;
        }

        /**
         * The searches enrichment would start on this person, likeliest record first, under the environment's
         * enrichment setting for a person's own investigation. In off mode the list is empty. In suggest mode
         * enrichment starts none of them by itself; in automatic mode it starts them as its limits allow.
         *
         * <p>Empty when evidence.read is denied.
         */
        public Builder suggestedSearches(@Nonnull List<SuggestedSearch> suggestedSearches) {
            this.suggestedSearches = Utils.checkNotNull(suggestedSearches, "suggestedSearches");
            return this;
        }

        /**
         * True when evidence.read is explicitly denied. Submitted details, coverage, job status and decisions
         * remain available. Collected records, profiles, findings, posts, media, discovered values and
         * free-text decision notes are withheld.
         */
        public Builder evidenceRestricted(@Nullable Boolean evidenceRestricted) {
            this.evidenceRestricted = evidenceRestricted;
            return this;
        }

        /**
         * The most recent retained completed or partial investigation, or a migrated result whose original
         * per-person status is unknown. The evidence below belongs to this investigation. Read investigation
         * history for earlier results and pending jobs.
         */
        public Builder evidenceInvestigation(@Nullable Investigation evidenceInvestigation) {
            this.evidenceInvestigation = evidenceInvestigation;
            return this;
        }

        /**
         * An investigator's profile of this person, written from the evidence of a settled investigation. Null
         * until an investigation completes or partially completes, and null when evidence.read is denied.
         */
        public Builder writtenProfile(@Nullable WrittenProfile writtenProfile) {
            this.writtenProfile = writtenProfile;
            return this;
        }

        public WorkspacePersonReport build() {
            return new WorkspacePersonReport(
                    externalId,
                    name,
                    email,
                    phone,
                    company,
                    city,
                    username,
                    handles,
                    id,
                    createdAt,
                    updatedAt,
                    expiresAt,
                    profiles,
                    findings,
                    posts,
                    media,
                    lookup,
                    searches,
                    suggestedSearches,
                    evidenceRestricted,
                    evidenceInvestigation,
                    writtenProfile);
        }
    }
}
