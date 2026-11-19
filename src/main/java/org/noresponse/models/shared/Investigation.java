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
import org.openapitools.jackson.nullable.JsonNullable;

public class Investigation {
    /**
     * The denominator of a screening: accounts found, how many could be read, items read and the period
     * they span, so a clean result is distinct from a person nobody could see.
     */
    @JsonProperty("coverage")
    private ScreeningCoverage coverage;

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    @JsonProperty("spend")
    private ScreeningSpend spend;

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

    @JsonProperty("id")
    private String id;

    @JsonProperty("person_id")
    private String personId;

    @JsonInclude(Include.ALWAYS)
    @JsonProperty("group_id")
    private JsonNullable<String> groupId;

    @JsonProperty("status")
    private InvestigationStatus status;

    @JsonProperty("depth")
    private ScreeningDepth depth;

    @JsonProperty("platforms")
    private List<String> platforms;

    @JsonInclude(Include.ALWAYS)
    @JsonProperty("match_state")
    private JsonNullable<MatchState> matchState;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("started_at")
    private String startedAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("finished_at")
    private String finishedAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("deadline_at")
    private String deadlineAt;

    @JsonCreator
    public Investigation(
            @JsonProperty("coverage") @Nonnull ScreeningCoverage coverage,
            @JsonProperty("spend") @Nonnull ScreeningSpend spend,
            @JsonProperty("decision") @Nullable PersonDecision decision,
            @JsonProperty("decision_note") @Nullable String decisionNote,
            @JsonProperty("decided_by") @Nullable String decidedBy,
            @JsonProperty("decided_at") @Nullable String decidedAt,
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("person_id") @Nonnull String personId,
            @JsonProperty("group_id") @Nullable String groupId,
            @JsonProperty("status") @Nonnull InvestigationStatus status,
            @JsonProperty("depth") @Nonnull ScreeningDepth depth,
            @JsonProperty("platforms") @Nonnull List<String> platforms,
            @JsonProperty("match_state") @Nullable MatchState matchState,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("started_at") @Nullable String startedAt,
            @JsonProperty("finished_at") @Nullable String finishedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("deadline_at") @Nonnull String deadlineAt) {
        this.coverage = Optional.ofNullable(coverage)
                .orElseThrow(() -> new IllegalArgumentException("coverage cannot be null"));
        this.spend = Optional.ofNullable(spend).orElseThrow(() -> new IllegalArgumentException("spend cannot be null"));
        this.decision = JsonNullable.of(decision);
        this.decisionNote = decisionNote;
        this.decidedBy = decidedBy;
        this.decidedAt = decidedAt;
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.groupId = JsonNullable.of(groupId);
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
        this.platforms = Optional.ofNullable(platforms)
                .orElseThrow(() -> new IllegalArgumentException("platforms cannot be null"));
        this.matchState = JsonNullable.of(matchState);
        this.errorCode = errorCode;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.deadlineAt = Optional.ofNullable(deadlineAt)
                .orElseThrow(() -> new IllegalArgumentException("deadlineAt cannot be null"));
    }

    public Investigation(
            @Nonnull ScreeningCoverage coverage,
            @Nonnull ScreeningSpend spend,
            @Nonnull String id,
            @Nonnull String personId,
            @Nonnull InvestigationStatus status,
            @Nonnull ScreeningDepth depth,
            @Nonnull List<String> platforms,
            @Nonnull String createdAt,
            @Nonnull String expiresAt,
            @Nonnull String deadlineAt) {
        this(
                coverage,
                spend,
                null,
                null,
                null,
                null,
                id,
                personId,
                null,
                status,
                depth,
                platforms,
                null,
                null,
                createdAt,
                null,
                null,
                expiresAt,
                deadlineAt);
    }

    /**
     * The denominator of a screening: accounts found, how many could be read, items read and the period
     * they span, so a clean result is distinct from a person nobody could see.
     */
    public ScreeningCoverage coverage() {
        return this.coverage;
    }

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    public ScreeningSpend spend() {
        return this.spend;
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

    public String id() {
        return this.id;
    }

    public String personId() {
        return this.personId;
    }

    public JsonNullable<String> groupId() {
        return this.groupId;
    }

    public InvestigationStatus status() {
        return this.status;
    }

    public ScreeningDepth depth() {
        return this.depth;
    }

    public List<String> platforms() {
        return this.platforms;
    }

    public JsonNullable<MatchState> matchState() {
        return this.matchState;
    }

    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public String createdAt() {
        return this.createdAt;
    }

    public Optional<String> startedAt() {
        return Optional.ofNullable(this.startedAt);
    }

    public Optional<String> finishedAt() {
        return Optional.ofNullable(this.finishedAt);
    }

    public String expiresAt() {
        return this.expiresAt;
    }

    public String deadlineAt() {
        return this.deadlineAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The denominator of a screening: accounts found, how many could be read, items read and the period
     * they span, so a clean result is distinct from a person nobody could see.
     */
    public Investigation withCoverage(@Nonnull ScreeningCoverage coverage) {
        this.coverage = Utils.checkNotNull(coverage, "coverage");
        return this;
    }

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    public Investigation withSpend(@Nonnull ScreeningSpend spend) {
        this.spend = Utils.checkNotNull(spend, "spend");
        return this;
    }

    /**
     * The latest reviewer decision, or null before any.
     */
    public Investigation withDecision(@Nullable PersonDecision decision) {
        this.decision = JsonNullable.of(decision);
        return this;
    }

    public Investigation withDecisionNote(@Nullable String decisionNote) {
        this.decisionNote = decisionNote;
        return this;
    }

    public Investigation withDecidedBy(@Nullable String decidedBy) {
        this.decidedBy = decidedBy;
        return this;
    }

    public Investigation withDecidedAt(@Nullable String decidedAt) {
        this.decidedAt = decidedAt;
        return this;
    }

    public Investigation withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public Investigation withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public Investigation withGroupId(@Nullable String groupId) {
        this.groupId = JsonNullable.of(groupId);
        return this;
    }

    public Investigation withStatus(@Nonnull InvestigationStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public Investigation withDepth(@Nonnull ScreeningDepth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    public Investigation withPlatforms(@Nonnull List<String> platforms) {
        this.platforms = Utils.checkNotNull(platforms, "platforms");
        return this;
    }

    public Investigation withMatchState(@Nullable MatchState matchState) {
        this.matchState = JsonNullable.of(matchState);
        return this;
    }

    public Investigation withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Investigation withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Investigation withStartedAt(@Nullable String startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public Investigation withFinishedAt(@Nullable String finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    public Investigation withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    public Investigation withDeadlineAt(@Nonnull String deadlineAt) {
        this.deadlineAt = Utils.checkNotNull(deadlineAt, "deadlineAt");
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
        Investigation other = (Investigation) o;
        return Utils.enhancedDeepEquals(this.coverage, other.coverage)
                && Utils.enhancedDeepEquals(this.spend, other.spend)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.decisionNote, other.decisionNote)
                && Utils.enhancedDeepEquals(this.decidedBy, other.decidedBy)
                && Utils.enhancedDeepEquals(this.decidedAt, other.decidedAt)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.platforms, other.platforms)
                && Utils.enhancedDeepEquals(this.matchState, other.matchState)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.startedAt, other.startedAt)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.deadlineAt, other.deadlineAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                coverage,
                spend,
                decision,
                decisionNote,
                decidedBy,
                decidedAt,
                id,
                personId,
                groupId,
                status,
                depth,
                platforms,
                matchState,
                errorCode,
                createdAt,
                startedAt,
                finishedAt,
                expiresAt,
                deadlineAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Investigation.class,
                "coverage",
                coverage,
                "spend",
                spend,
                "decision",
                decision,
                "decisionNote",
                decisionNote,
                "decidedBy",
                decidedBy,
                "decidedAt",
                decidedAt,
                "id",
                id,
                "personId",
                personId,
                "groupId",
                groupId,
                "status",
                status,
                "depth",
                depth,
                "platforms",
                platforms,
                "matchState",
                matchState,
                "errorCode",
                errorCode,
                "createdAt",
                createdAt,
                "startedAt",
                startedAt,
                "finishedAt",
                finishedAt,
                "expiresAt",
                expiresAt,
                "deadlineAt",
                deadlineAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ScreeningCoverage coverage;

        private ScreeningSpend spend;

        private PersonDecision decision;

        private String decisionNote;

        private String decidedBy;

        private String decidedAt;

        private String id;

        private String personId;

        private String groupId;

        private InvestigationStatus status;

        private ScreeningDepth depth;

        private List<String> platforms;

        private MatchState matchState;

        private String errorCode;

        private String createdAt;

        private String startedAt;

        private String finishedAt;

        private String expiresAt;

        private String deadlineAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The denominator of a screening: accounts found, how many could be read, items read and the period
         * they span, so a clean result is distinct from a person nobody could see.
         */
        public Builder coverage(@Nonnull ScreeningCoverage coverage) {
            this.coverage = Utils.checkNotNull(coverage, "coverage");
            return this;
        }

        /**
         * What screening cost, in US cents, in total and per implementation.
         */
        public Builder spend(@Nonnull ScreeningSpend spend) {
            this.spend = Utils.checkNotNull(spend, "spend");
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

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder groupId(@Nullable String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder status(@Nonnull InvestigationStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder depth(@Nonnull ScreeningDepth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        public Builder platforms(@Nonnull List<String> platforms) {
            this.platforms = Utils.checkNotNull(platforms, "platforms");
            return this;
        }

        public Builder matchState(@Nullable MatchState matchState) {
            this.matchState = matchState;
            return this;
        }

        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder startedAt(@Nullable String startedAt) {
            this.startedAt = startedAt;
            return this;
        }

        public Builder finishedAt(@Nullable String finishedAt) {
            this.finishedAt = finishedAt;
            return this;
        }

        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        public Builder deadlineAt(@Nonnull String deadlineAt) {
            this.deadlineAt = Utils.checkNotNull(deadlineAt, "deadlineAt");
            return this;
        }

        public Investigation build() {
            return new Investigation(
                    coverage,
                    spend,
                    decision,
                    decisionNote,
                    decidedBy,
                    decidedAt,
                    id,
                    personId,
                    groupId,
                    status,
                    depth,
                    platforms,
                    matchState,
                    errorCode,
                    createdAt,
                    startedAt,
                    finishedAt,
                    expiresAt,
                    deadlineAt);
        }
    }
}
