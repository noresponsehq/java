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
 * Screening
 *
 * <p>A screening and its progress.
 */
public class Screening {

    @JsonProperty("id")
    private String id;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    /**
     * partial finished with some people or platforms not covered; people_failed and people_partial count
     * them.
     */
    @JsonProperty("status")
    private ScreeningStatus status;

    @JsonProperty("depth")
    private ScreeningDepth depth;

    @JsonProperty("platforms")
    private List<HandlePlatform> platforms;

    @JsonProperty("requested_by")
    private String requestedBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("started_at")
    private String startedAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("finished_at")
    private String finishedAt;

    /**
     * A screening still running then fails with error_code expired.
     */
    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * People selected when the screening started.
     */
    @JsonProperty("people_total")
    private long peopleTotal;

    /**
     * People screened so far, failed ones included.
     */
    @JsonProperty("people_done")
    private long peopleDone;

    @JsonProperty("people_flagged")
    private long peopleFlagged;

    @JsonProperty("people_failed")
    private long peopleFailed;

    /**
     * People screened with some requested platform not covered; their coverage names which.
     */
    @JsonProperty("people_partial")
    private long peoplePartial;

    /**
     * What the screening was estimated to cost when it started, in minor units of USD (cents): its people
     * at its depth and platforms. Until the screening finishes, what it has not yet spent counts against
     * the environment's screening_daily_cap.
     */
    @JsonProperty("estimated_spend")
    private long estimatedSpend;

    /**
     * What the people it has screened recorded, in minor units of USD. Each counts toward the UTC day it
     * was recorded on; a failed person records nothing.
     */
    @JsonProperty("spent")
    private long spent;

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    @JsonProperty("spend")
    private ScreeningSpend spend;

    /**
     * Why a failed or cancelled screening stopped.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    @JsonCreator
    public Screening(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("group_id") @Nonnull String groupId,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("status") @Nonnull ScreeningStatus status,
            @JsonProperty("depth") @Nonnull ScreeningDepth depth,
            @JsonProperty("platforms") @Nonnull List<HandlePlatform> platforms,
            @JsonProperty("requested_by") @Nonnull String requestedBy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("started_at") @Nullable String startedAt,
            @JsonProperty("finished_at") @Nullable String finishedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("people_total") long peopleTotal,
            @JsonProperty("people_done") long peopleDone,
            @JsonProperty("people_flagged") long peopleFlagged,
            @JsonProperty("people_failed") long peopleFailed,
            @JsonProperty("people_partial") long peoplePartial,
            @JsonProperty("estimated_spend") long estimatedSpend,
            @JsonProperty("spent") long spent,
            @JsonProperty("spend") @Nonnull ScreeningSpend spend,
            @JsonProperty("error_code") @Nullable String errorCode) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
        this.platforms = Optional.ofNullable(platforms)
                .orElseThrow(() -> new IllegalArgumentException("platforms cannot be null"));
        this.requestedBy = Optional.ofNullable(requestedBy)
                .orElseThrow(() -> new IllegalArgumentException("requestedBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.peopleTotal = peopleTotal;
        this.peopleDone = peopleDone;
        this.peopleFlagged = peopleFlagged;
        this.peopleFailed = peopleFailed;
        this.peoplePartial = peoplePartial;
        this.estimatedSpend = estimatedSpend;
        this.spent = spent;
        this.spend = Optional.ofNullable(spend).orElseThrow(() -> new IllegalArgumentException("spend cannot be null"));
        this.errorCode = errorCode;
    }

    public Screening(
            @Nonnull String id,
            @Nonnull String groupId,
            @Nonnull AuditSettingsEnvironment environment,
            @Nonnull ScreeningStatus status,
            @Nonnull ScreeningDepth depth,
            @Nonnull List<HandlePlatform> platforms,
            @Nonnull String requestedBy,
            @Nonnull String createdAt,
            @Nonnull String expiresAt,
            long peopleTotal,
            long peopleDone,
            long peopleFlagged,
            long peopleFailed,
            long peoplePartial,
            long estimatedSpend,
            long spent,
            @Nonnull ScreeningSpend spend) {
        this(
                id,
                groupId,
                environment,
                status,
                depth,
                platforms,
                requestedBy,
                createdAt,
                null,
                null,
                expiresAt,
                peopleTotal,
                peopleDone,
                peopleFlagged,
                peopleFailed,
                peoplePartial,
                estimatedSpend,
                spent,
                spend,
                null);
    }

    public String id() {
        return this.id;
    }

    public String groupId() {
        return this.groupId;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    /**
     * partial finished with some people or platforms not covered; people_failed and people_partial count
     * them.
     */
    public ScreeningStatus status() {
        return this.status;
    }

    public ScreeningDepth depth() {
        return this.depth;
    }

    public List<HandlePlatform> platforms() {
        return this.platforms;
    }

    public String requestedBy() {
        return this.requestedBy;
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

    /**
     * A screening still running then fails with error_code expired.
     */
    public String expiresAt() {
        return this.expiresAt;
    }

    /**
     * People selected when the screening started.
     */
    public long peopleTotal() {
        return this.peopleTotal;
    }

    /**
     * People screened so far, failed ones included.
     */
    public long peopleDone() {
        return this.peopleDone;
    }

    public long peopleFlagged() {
        return this.peopleFlagged;
    }

    public long peopleFailed() {
        return this.peopleFailed;
    }

    /**
     * People screened with some requested platform not covered; their coverage names which.
     */
    public long peoplePartial() {
        return this.peoplePartial;
    }

    /**
     * What the screening was estimated to cost when it started, in minor units of USD (cents): its people
     * at its depth and platforms. Until the screening finishes, what it has not yet spent counts against
     * the environment's screening_daily_cap.
     */
    public long estimatedSpend() {
        return this.estimatedSpend;
    }

    /**
     * What the people it has screened recorded, in minor units of USD. Each counts toward the UTC day it
     * was recorded on; a failed person records nothing.
     */
    public long spent() {
        return this.spent;
    }

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    public ScreeningSpend spend() {
        return this.spend;
    }

    /**
     * Why a failed or cancelled screening stopped.
     */
    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Screening withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public Screening withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public Screening withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * partial finished with some people or platforms not covered; people_failed and people_partial count
     * them.
     */
    public Screening withStatus(@Nonnull ScreeningStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public Screening withDepth(@Nonnull ScreeningDepth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    public Screening withPlatforms(@Nonnull List<HandlePlatform> platforms) {
        this.platforms = Utils.checkNotNull(platforms, "platforms");
        return this;
    }

    public Screening withRequestedBy(@Nonnull String requestedBy) {
        this.requestedBy = Utils.checkNotNull(requestedBy, "requestedBy");
        return this;
    }

    public Screening withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Screening withStartedAt(@Nullable String startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public Screening withFinishedAt(@Nullable String finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    /**
     * A screening still running then fails with error_code expired.
     */
    public Screening withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    /**
     * People selected when the screening started.
     */
    public Screening withPeopleTotal(long peopleTotal) {
        this.peopleTotal = peopleTotal;
        return this;
    }

    /**
     * People screened so far, failed ones included.
     */
    public Screening withPeopleDone(long peopleDone) {
        this.peopleDone = peopleDone;
        return this;
    }

    public Screening withPeopleFlagged(long peopleFlagged) {
        this.peopleFlagged = peopleFlagged;
        return this;
    }

    public Screening withPeopleFailed(long peopleFailed) {
        this.peopleFailed = peopleFailed;
        return this;
    }

    /**
     * People screened with some requested platform not covered; their coverage names which.
     */
    public Screening withPeoplePartial(long peoplePartial) {
        this.peoplePartial = peoplePartial;
        return this;
    }

    /**
     * What the screening was estimated to cost when it started, in minor units of USD (cents): its people
     * at its depth and platforms. Until the screening finishes, what it has not yet spent counts against
     * the environment's screening_daily_cap.
     */
    public Screening withEstimatedSpend(long estimatedSpend) {
        this.estimatedSpend = estimatedSpend;
        return this;
    }

    /**
     * What the people it has screened recorded, in minor units of USD. Each counts toward the UTC day it
     * was recorded on; a failed person records nothing.
     */
    public Screening withSpent(long spent) {
        this.spent = spent;
        return this;
    }

    /**
     * What screening cost, in US cents, in total and per implementation.
     */
    public Screening withSpend(@Nonnull ScreeningSpend spend) {
        this.spend = Utils.checkNotNull(spend, "spend");
        return this;
    }

    /**
     * Why a failed or cancelled screening stopped.
     */
    public Screening withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
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
        Screening other = (Screening) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.platforms, other.platforms)
                && Utils.enhancedDeepEquals(this.requestedBy, other.requestedBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.startedAt, other.startedAt)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.peopleTotal, other.peopleTotal)
                && Utils.enhancedDeepEquals(this.peopleDone, other.peopleDone)
                && Utils.enhancedDeepEquals(this.peopleFlagged, other.peopleFlagged)
                && Utils.enhancedDeepEquals(this.peopleFailed, other.peopleFailed)
                && Utils.enhancedDeepEquals(this.peoplePartial, other.peoplePartial)
                && Utils.enhancedDeepEquals(this.estimatedSpend, other.estimatedSpend)
                && Utils.enhancedDeepEquals(this.spent, other.spent)
                && Utils.enhancedDeepEquals(this.spend, other.spend)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                groupId,
                environment,
                status,
                depth,
                platforms,
                requestedBy,
                createdAt,
                startedAt,
                finishedAt,
                expiresAt,
                peopleTotal,
                peopleDone,
                peopleFlagged,
                peopleFailed,
                peoplePartial,
                estimatedSpend,
                spent,
                spend,
                errorCode);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Screening.class,
                "id",
                id,
                "groupId",
                groupId,
                "environment",
                environment,
                "status",
                status,
                "depth",
                depth,
                "platforms",
                platforms,
                "requestedBy",
                requestedBy,
                "createdAt",
                createdAt,
                "startedAt",
                startedAt,
                "finishedAt",
                finishedAt,
                "expiresAt",
                expiresAt,
                "peopleTotal",
                peopleTotal,
                "peopleDone",
                peopleDone,
                "peopleFlagged",
                peopleFlagged,
                "peopleFailed",
                peopleFailed,
                "peoplePartial",
                peoplePartial,
                "estimatedSpend",
                estimatedSpend,
                "spent",
                spent,
                "spend",
                spend,
                "errorCode",
                errorCode);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String groupId;

        private AuditSettingsEnvironment environment;

        private ScreeningStatus status;

        private ScreeningDepth depth;

        private List<HandlePlatform> platforms;

        private String requestedBy;

        private String createdAt;

        private String startedAt;

        private String finishedAt;

        private String expiresAt;

        private long peopleTotal;

        private long peopleDone;

        private long peopleFlagged;

        private long peopleFailed;

        private long peoplePartial;

        private long estimatedSpend;

        private long spent;

        private ScreeningSpend spend;

        private String errorCode;

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

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * partial finished with some people or platforms not covered; people_failed and people_partial count
         * them.
         */
        public Builder status(@Nonnull ScreeningStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder depth(@Nonnull ScreeningDepth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        public Builder platforms(@Nonnull List<HandlePlatform> platforms) {
            this.platforms = Utils.checkNotNull(platforms, "platforms");
            return this;
        }

        public Builder requestedBy(@Nonnull String requestedBy) {
            this.requestedBy = Utils.checkNotNull(requestedBy, "requestedBy");
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

        /**
         * A screening still running then fails with error_code expired.
         */
        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        /**
         * People selected when the screening started.
         */
        public Builder peopleTotal(long peopleTotal) {
            this.peopleTotal = peopleTotal;
            return this;
        }

        /**
         * People screened so far, failed ones included.
         */
        public Builder peopleDone(long peopleDone) {
            this.peopleDone = peopleDone;
            return this;
        }

        public Builder peopleFlagged(long peopleFlagged) {
            this.peopleFlagged = peopleFlagged;
            return this;
        }

        public Builder peopleFailed(long peopleFailed) {
            this.peopleFailed = peopleFailed;
            return this;
        }

        /**
         * People screened with some requested platform not covered; their coverage names which.
         */
        public Builder peoplePartial(long peoplePartial) {
            this.peoplePartial = peoplePartial;
            return this;
        }

        /**
         * What the screening was estimated to cost when it started, in minor units of USD (cents): its people
         * at its depth and platforms. Until the screening finishes, what it has not yet spent counts against
         * the environment's screening_daily_cap.
         */
        public Builder estimatedSpend(long estimatedSpend) {
            this.estimatedSpend = estimatedSpend;
            return this;
        }

        /**
         * What the people it has screened recorded, in minor units of USD. Each counts toward the UTC day it
         * was recorded on; a failed person records nothing.
         */
        public Builder spent(long spent) {
            this.spent = spent;
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
         * Why a failed or cancelled screening stopped.
         */
        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Screening build() {
            return new Screening(
                    id,
                    groupId,
                    environment,
                    status,
                    depth,
                    platforms,
                    requestedBy,
                    createdAt,
                    startedAt,
                    finishedAt,
                    expiresAt,
                    peopleTotal,
                    peopleDone,
                    peopleFlagged,
                    peopleFailed,
                    peoplePartial,
                    estimatedSpend,
                    spent,
                    spend,
                    errorCode);
        }
    }
}
