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
 * Person
 *
 * <p>A person and where their screening and review stand.
 */
public class Person {

    @JsonProperty("id")
    private String id;

    @JsonProperty("group_id")
    private String groupId;

    /**
     * Your own id for the person, unique within the group. A later row with the same external id updates
     * this person.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_id")
    private String externalId;

    /**
     * The person's full name.
     */
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
     * Accounts the person is known to hold, at most 20.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("handles")
    private List<Handle> handles;

    /**
     * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: a person without one has no role, not guest. A later row naming the same person
     * replaces it, and a change of role alone keeps the person's screening results.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("role")
    private PersonRole role;

    /**
     * How sure the latest screening is that it found the person's accounts. unscreened until a screening
     * reaches the person, and after one that had no profile to check (no_profile_to_check).
     */
    @JsonProperty("match_state")
    private MatchState matchState;

    /**
     * true when the latest screening reached the person but the list gave nothing to look up on any
     * requested platform, such as no handle for a platform that reads supplied handles only: nobody looked
     * for a profile. match_state stays unscreened, every platform is in coverage.not_searched, and the
     * person is neither none_found nor failed and does not make the screening partial. Its lookups are on
     * its report.
     *
     * <p>Left out otherwise.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("no_profile_to_check")
    private Boolean noProfileToCheck;

    /**
     * The most severe finding's band, or null when nothing was flagged.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("highest_band")
    private JsonNullable<HighestBand> highestBand;

    /**
     * The latest reviewer decision, or null before any.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("decision")
    private JsonNullable<PersonDecision> decision;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decision_note")
    private String decisionNote;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decided_by")
    private String decidedBy;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decided_at")
    private String decidedAt;

    /**
     * The denominator of a screening: accounts found, how many could be read, items read and the period
     * they span, so a clean result is distinct from a person nobody could see.
     */
    @JsonProperty("coverage")
    private ScreeningCoverage coverage;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("last_screening_id")
    private String lastScreeningId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("screened_at")
    private String screenedAt;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    @JsonProperty("spend")
    private ScreeningSpend spend;

    @JsonCreator
    public Person(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("group_id") @Nonnull String groupId,
            @JsonProperty("external_id") @Nullable String externalId,
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("company") @Nullable String company,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("handles") @Nullable List<Handle> handles,
            @JsonProperty("role") @Nullable PersonRole role,
            @JsonProperty("match_state") @Nonnull MatchState matchState,
            @JsonProperty("no_profile_to_check") @Nullable Boolean noProfileToCheck,
            @JsonProperty("highest_band") @Nullable HighestBand highestBand,
            @JsonProperty("decision") @Nullable PersonDecision decision,
            @JsonProperty("decision_note") @Nullable String decisionNote,
            @JsonProperty("decided_by") @Nullable String decidedBy,
            @JsonProperty("decided_at") @Nullable String decidedAt,
            @JsonProperty("coverage") @Nonnull ScreeningCoverage coverage,
            @JsonProperty("last_screening_id") @Nullable String lastScreeningId,
            @JsonProperty("screened_at") @Nullable String screenedAt,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt,
            @JsonProperty("spend") @Nonnull ScreeningSpend spend) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.externalId = externalId;
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.city = city;
        this.handles = handles;
        this.role = role;
        this.matchState = Optional.ofNullable(matchState)
                .orElseThrow(() -> new IllegalArgumentException("matchState cannot be null"));
        this.noProfileToCheck = noProfileToCheck;
        this.highestBand = JsonNullable.of(highestBand);
        this.decision = JsonNullable.of(decision);
        this.decisionNote = decisionNote;
        this.decidedBy = decidedBy;
        this.decidedAt = decidedAt;
        this.coverage = Optional.ofNullable(coverage)
                .orElseThrow(() -> new IllegalArgumentException("coverage cannot be null"));
        this.lastScreeningId = lastScreeningId;
        this.screenedAt = screenedAt;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
        this.spend = Optional.ofNullable(spend).orElseThrow(() -> new IllegalArgumentException("spend cannot be null"));
    }

    public Person(
            @Nonnull String id,
            @Nonnull String groupId,
            @Nonnull String name,
            @Nonnull MatchState matchState,
            @Nonnull ScreeningCoverage coverage,
            @Nonnull String createdAt,
            @Nonnull String updatedAt,
            @Nonnull ScreeningSpend spend) {
        this(
                id,
                groupId,
                null,
                name,
                null,
                null,
                null,
                null,
                null,
                null,
                matchState,
                null,
                null,
                null,
                null,
                null,
                null,
                coverage,
                null,
                null,
                createdAt,
                updatedAt,
                spend);
    }

    public String id() {
        return this.id;
    }

    public String groupId() {
        return this.groupId;
    }

    /**
     * Your own id for the person, unique within the group. A later row with the same external id updates
     * this person.
     */
    public Optional<String> externalId() {
        return Optional.ofNullable(this.externalId);
    }

    /**
     * The person's full name.
     */
    public String name() {
        return this.name;
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
     * Accounts the person is known to hold, at most 20.
     */
    public Optional<List<Handle>> handles() {
        return Optional.ofNullable(this.handles);
    }

    /**
     * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: a person without one has no role, not guest. A later row naming the same person
     * replaces it, and a change of role alone keeps the person's screening results.
     */
    public Optional<PersonRole> role() {
        return Optional.ofNullable(this.role);
    }

    /**
     * How sure the latest screening is that it found the person's accounts. unscreened until a screening
     * reaches the person, and after one that had no profile to check (no_profile_to_check).
     */
    public MatchState matchState() {
        return this.matchState;
    }

    /**
     * true when the latest screening reached the person but the list gave nothing to look up on any
     * requested platform, such as no handle for a platform that reads supplied handles only: nobody looked
     * for a profile. match_state stays unscreened, every platform is in coverage.not_searched, and the
     * person is neither none_found nor failed and does not make the screening partial. Its lookups are on
     * its report.
     *
     * <p>Left out otherwise.
     */
    public Optional<Boolean> noProfileToCheck() {
        return Optional.ofNullable(this.noProfileToCheck);
    }

    /**
     * The most severe finding's band, or null when nothing was flagged.
     */
    public JsonNullable<HighestBand> highestBand() {
        return this.highestBand;
    }

    /**
     * The latest reviewer decision, or null before any.
     */
    public JsonNullable<PersonDecision> decision() {
        return this.decision;
    }

    public Optional<String> decisionNote() {
        return Optional.ofNullable(this.decisionNote);
    }

    public Optional<String> decidedBy() {
        return Optional.ofNullable(this.decidedBy);
    }

    public Optional<String> decidedAt() {
        return Optional.ofNullable(this.decidedAt);
    }

    /**
     * The denominator of a screening: accounts found, how many could be read, items read and the period
     * they span, so a clean result is distinct from a person nobody could see.
     */
    public ScreeningCoverage coverage() {
        return this.coverage;
    }

    public Optional<String> lastScreeningId() {
        return Optional.ofNullable(this.lastScreeningId);
    }

    public Optional<String> screenedAt() {
        return Optional.ofNullable(this.screenedAt);
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    public ScreeningSpend spend() {
        return this.spend;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Person withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public Person withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    /**
     * Your own id for the person, unique within the group. A later row with the same external id updates
     * this person.
     */
    public Person withExternalId(@Nullable String externalId) {
        this.externalId = externalId;
        return this;
    }

    /**
     * The person's full name.
     */
    public Person withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * The person's email.
     */
    public Person withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The person's phone in E.164 form.
     */
    public Person withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Where the person works.
     */
    public Person withCompany(@Nullable String company) {
        this.company = company;
        return this;
    }

    /**
     * Where the person lives.
     */
    public Person withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    /**
     * Accounts the person is known to hold, at most 20.
     */
    public Person withHandles(@Nullable List<Handle> handles) {
        this.handles = handles;
        return this;
    }

    /**
     * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: a person without one has no role, not guest. A later row naming the same person
     * replaces it, and a change of role alone keeps the person's screening results.
     */
    public Person withRole(@Nullable PersonRole role) {
        this.role = role;
        return this;
    }

    /**
     * How sure the latest screening is that it found the person's accounts. unscreened until a screening
     * reaches the person, and after one that had no profile to check (no_profile_to_check).
     */
    public Person withMatchState(@Nonnull MatchState matchState) {
        this.matchState = Utils.checkNotNull(matchState, "matchState");
        return this;
    }

    /**
     * true when the latest screening reached the person but the list gave nothing to look up on any
     * requested platform, such as no handle for a platform that reads supplied handles only: nobody looked
     * for a profile. match_state stays unscreened, every platform is in coverage.not_searched, and the
     * person is neither none_found nor failed and does not make the screening partial. Its lookups are on
     * its report.
     *
     * <p>Left out otherwise.
     */
    public Person withNoProfileToCheck(@Nullable Boolean noProfileToCheck) {
        this.noProfileToCheck = noProfileToCheck;
        return this;
    }

    /**
     * The most severe finding's band, or null when nothing was flagged.
     */
    public Person withHighestBand(@Nullable HighestBand highestBand) {
        this.highestBand = JsonNullable.of(highestBand);
        return this;
    }

    /**
     * The latest reviewer decision, or null before any.
     */
    public Person withDecision(@Nullable PersonDecision decision) {
        this.decision = JsonNullable.of(decision);
        return this;
    }

    public Person withDecisionNote(@Nullable String decisionNote) {
        this.decisionNote = decisionNote;
        return this;
    }

    public Person withDecidedBy(@Nullable String decidedBy) {
        this.decidedBy = decidedBy;
        return this;
    }

    public Person withDecidedAt(@Nullable String decidedAt) {
        this.decidedAt = decidedAt;
        return this;
    }

    /**
     * The denominator of a screening: accounts found, how many could be read, items read and the period
     * they span, so a clean result is distinct from a person nobody could see.
     */
    public Person withCoverage(@Nonnull ScreeningCoverage coverage) {
        this.coverage = Utils.checkNotNull(coverage, "coverage");
        return this;
    }

    public Person withLastScreeningId(@Nullable String lastScreeningId) {
        this.lastScreeningId = lastScreeningId;
        return this;
    }

    public Person withScreenedAt(@Nullable String screenedAt) {
        this.screenedAt = screenedAt;
        return this;
    }

    public Person withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Person withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
        return this;
    }

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    public Person withSpend(@Nonnull ScreeningSpend spend) {
        this.spend = Utils.checkNotNull(spend, "spend");
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
        Person other = (Person) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.externalId, other.externalId)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.company, other.company)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.handles, other.handles)
                && Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.matchState, other.matchState)
                && Utils.enhancedDeepEquals(this.noProfileToCheck, other.noProfileToCheck)
                && Utils.enhancedDeepEquals(this.highestBand, other.highestBand)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.decisionNote, other.decisionNote)
                && Utils.enhancedDeepEquals(this.decidedBy, other.decidedBy)
                && Utils.enhancedDeepEquals(this.decidedAt, other.decidedAt)
                && Utils.enhancedDeepEquals(this.coverage, other.coverage)
                && Utils.enhancedDeepEquals(this.lastScreeningId, other.lastScreeningId)
                && Utils.enhancedDeepEquals(this.screenedAt, other.screenedAt)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.spend, other.spend);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                groupId,
                externalId,
                name,
                email,
                phone,
                company,
                city,
                handles,
                role,
                matchState,
                noProfileToCheck,
                highestBand,
                decision,
                decisionNote,
                decidedBy,
                decidedAt,
                coverage,
                lastScreeningId,
                screenedAt,
                createdAt,
                updatedAt,
                spend);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Person.class,
                "id",
                id,
                "groupId",
                groupId,
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
                "handles",
                handles,
                "role",
                role,
                "matchState",
                matchState,
                "noProfileToCheck",
                noProfileToCheck,
                "highestBand",
                highestBand,
                "decision",
                decision,
                "decisionNote",
                decisionNote,
                "decidedBy",
                decidedBy,
                "decidedAt",
                decidedAt,
                "coverage",
                coverage,
                "lastScreeningId",
                lastScreeningId,
                "screenedAt",
                screenedAt,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt,
                "spend",
                spend);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String groupId;

        private String externalId;

        private String name;

        private String email;

        private String phone;

        private String company;

        private String city;

        private List<Handle> handles;

        private PersonRole role;

        private MatchState matchState;

        private Boolean noProfileToCheck;

        private HighestBand highestBand;

        private PersonDecision decision;

        private String decisionNote;

        private String decidedBy;

        private String decidedAt;

        private ScreeningCoverage coverage;

        private String lastScreeningId;

        private String screenedAt;

        private String createdAt;

        private String updatedAt;

        private ScreeningSpend spend;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        /**
         * Your own id for the person, unique within the group. A later row with the same external id updates
         * this person.
         */
        public Builder externalId(@Nullable String externalId) {
            this.externalId = externalId;
            return this;
        }

        /**
         * The person's full name.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
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
         * Accounts the person is known to hold, at most 20.
         */
        public Builder handles(@Nullable List<Handle> handles) {
            this.handles = handles;
            return this;
        }

        /**
         * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
         * you do not know it: a person without one has no role, not guest. A later row naming the same person
         * replaces it, and a change of role alone keeps the person's screening results.
         */
        public Builder role(@Nullable PersonRole role) {
            this.role = role;
            return this;
        }

        /**
         * How sure the latest screening is that it found the person's accounts. unscreened until a screening
         * reaches the person, and after one that had no profile to check (no_profile_to_check).
         */
        public Builder matchState(@Nonnull MatchState matchState) {
            this.matchState = Utils.checkNotNull(matchState, "matchState");
            return this;
        }

        /**
         * true when the latest screening reached the person but the list gave nothing to look up on any
         * requested platform, such as no handle for a platform that reads supplied handles only: nobody looked
         * for a profile. match_state stays unscreened, every platform is in coverage.not_searched, and the
         * person is neither none_found nor failed and does not make the screening partial. Its lookups are on
         * its report.
         *
         * <p>Left out otherwise.
         */
        public Builder noProfileToCheck(@Nullable Boolean noProfileToCheck) {
            this.noProfileToCheck = noProfileToCheck;
            return this;
        }

        /**
         * The most severe finding's band, or null when nothing was flagged.
         */
        public Builder highestBand(@Nullable HighestBand highestBand) {
            this.highestBand = highestBand;
            return this;
        }

        /**
         * The latest reviewer decision, or null before any.
         */
        public Builder decision(@Nullable PersonDecision decision) {
            this.decision = decision;
            return this;
        }

        public Builder decisionNote(@Nullable String decisionNote) {
            this.decisionNote = decisionNote;
            return this;
        }

        public Builder decidedBy(@Nullable String decidedBy) {
            this.decidedBy = decidedBy;
            return this;
        }

        public Builder decidedAt(@Nullable String decidedAt) {
            this.decidedAt = decidedAt;
            return this;
        }

        /**
         * The denominator of a screening: accounts found, how many could be read, items read and the period
         * they span, so a clean result is distinct from a person nobody could see.
         */
        public Builder coverage(@Nonnull ScreeningCoverage coverage) {
            this.coverage = Utils.checkNotNull(coverage, "coverage");
            return this;
        }

        public Builder lastScreeningId(@Nullable String lastScreeningId) {
            this.lastScreeningId = lastScreeningId;
            return this;
        }

        public Builder screenedAt(@Nullable String screenedAt) {
            this.screenedAt = screenedAt;
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
         * What screening cost, in US cents, in total and per implementation.
         */
        public Builder spend(@Nonnull ScreeningSpend spend) {
            this.spend = Utils.checkNotNull(spend, "spend");
            return this;
        }

        public Person build() {
            return new Person(
                    id,
                    groupId,
                    externalId,
                    name,
                    email,
                    phone,
                    company,
                    city,
                    handles,
                    role,
                    matchState,
                    noProfileToCheck,
                    highestBand,
                    decision,
                    decisionNote,
                    decidedBy,
                    decidedAt,
                    coverage,
                    lastScreeningId,
                    screenedAt,
                    createdAt,
                    updatedAt,
                    spend);
        }
    }
}
