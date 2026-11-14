package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningCompleted
 *
 * <p>A screening of a group finished. It counts only the people this screening set out to reach, not who
 * they are: read the roster with GET
 * /v1/workspaces/{workspace_id}/groups/{environment}/{group_id}/people, filtered by band, to act on
 * them. people_screened + partial_coverage = people_total, people_flagged is the sum of by_band,
 * no_footprint counts screened people with no flag whose accounts were not found, and
 * no_profile_to_check those the screening had nothing to look up for on any requested platform.
 *
 * <p>A cancelled screening sends no event.
 */
public class ScreeningCompleted {
    /**
     * The screening that finished.
     */
    @JsonProperty("screening_id")
    private String screeningId;

    /**
     * The group it screened.
     */
    @JsonProperty("group_id")
    private String groupId;

    /**
     * completed reached every person; partial left some not fully covered (partial_coverage); failed
     * covered nobody.
     */
    @JsonProperty("status")
    private ScreeningCompletedStatus status;

    /**
     * Why a partial or failed screening stopped before reaching everyone, as the screening resource says:
     * expired, worker_attempts_exhausted, attempts_exhausted or screening_unavailable.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * Every person the screening set out to reach: those no screening had reached and those on watch or
     * escalated.
     */
    @JsonProperty("people_total")
    private long peopleTotal;

    /**
     * People the screening covered, flagged or not.
     */
    @JsonProperty("people_screened")
    private long peopleScreened;

    /**
     * Screened people with a finding: the sum of by_band.
     */
    @JsonProperty("people_flagged")
    private long peopleFlagged;

    /**
     * Screened people by the band of their most severe finding.
     */
    @JsonProperty("by_band")
    private ScreeningCompletedByBand byBand;

    /**
     * Screened people with no finding whose accounts were not found (match_state none_found). A clean
     * result for someone with no visible accounts is not the same as a clean result for someone whose
     * accounts were read.
     */
    @JsonProperty("no_footprint")
    private long noFootprint;

    /**
     * Screened people with no profile to check: the list gave nothing to look up on any requested
     * platform, such as no handle, so nobody looked for a profile (no_profile_to_check on the person). Not
     * in no_footprint, and not partial: nothing failed. Their lookups are on their reports.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("no_profile_to_check")
    private Long noProfileToCheck;

    /**
     * People the screening did not fully cover. Read each one's coverage on its person.
     */
    @JsonProperty("partial_coverage")
    private long partialCoverage;

    /**
     * When the screening finished; equal to the event's created_at.
     */
    @JsonProperty("finished_at")
    private String finishedAt;

    @JsonCreator
    public ScreeningCompleted(
            @JsonProperty("screening_id") @Nonnull String screeningId,
            @JsonProperty("group_id") @Nonnull String groupId,
            @JsonProperty("status") @Nonnull ScreeningCompletedStatus status,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("people_total") long peopleTotal,
            @JsonProperty("people_screened") long peopleScreened,
            @JsonProperty("people_flagged") long peopleFlagged,
            @JsonProperty("by_band") @Nonnull ScreeningCompletedByBand byBand,
            @JsonProperty("no_footprint") long noFootprint,
            @JsonProperty("no_profile_to_check") @Nullable Long noProfileToCheck,
            @JsonProperty("partial_coverage") long partialCoverage,
            @JsonProperty("finished_at") @Nonnull String finishedAt) {
        this.screeningId = Optional.ofNullable(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("screeningId cannot be null"));
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.errorCode = errorCode;
        this.peopleTotal = peopleTotal;
        this.peopleScreened = peopleScreened;
        this.peopleFlagged = peopleFlagged;
        this.byBand =
                Optional.ofNullable(byBand).orElseThrow(() -> new IllegalArgumentException("byBand cannot be null"));
        this.noFootprint = noFootprint;
        this.noProfileToCheck = noProfileToCheck;
        this.partialCoverage = partialCoverage;
        this.finishedAt = Optional.ofNullable(finishedAt)
                .orElseThrow(() -> new IllegalArgumentException("finishedAt cannot be null"));
    }

    public ScreeningCompleted(
            @Nonnull String screeningId,
            @Nonnull String groupId,
            @Nonnull ScreeningCompletedStatus status,
            long peopleTotal,
            long peopleScreened,
            long peopleFlagged,
            @Nonnull ScreeningCompletedByBand byBand,
            long noFootprint,
            long partialCoverage,
            @Nonnull String finishedAt) {
        this(
                screeningId,
                groupId,
                status,
                null,
                peopleTotal,
                peopleScreened,
                peopleFlagged,
                byBand,
                noFootprint,
                null,
                partialCoverage,
                finishedAt);
    }

    /**
     * The screening that finished.
     */
    public String screeningId() {
        return this.screeningId;
    }

    /**
     * The group it screened.
     */
    public String groupId() {
        return this.groupId;
    }

    /**
     * completed reached every person; partial left some not fully covered (partial_coverage); failed
     * covered nobody.
     */
    public ScreeningCompletedStatus status() {
        return this.status;
    }

    /**
     * Why a partial or failed screening stopped before reaching everyone, as the screening resource says:
     * expired, worker_attempts_exhausted, attempts_exhausted or screening_unavailable.
     */
    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    /**
     * Every person the screening set out to reach: those no screening had reached and those on watch or
     * escalated.
     */
    public long peopleTotal() {
        return this.peopleTotal;
    }

    /**
     * People the screening covered, flagged or not.
     */
    public long peopleScreened() {
        return this.peopleScreened;
    }

    /**
     * Screened people with a finding: the sum of by_band.
     */
    public long peopleFlagged() {
        return this.peopleFlagged;
    }

    /**
     * Screened people by the band of their most severe finding.
     */
    public ScreeningCompletedByBand byBand() {
        return this.byBand;
    }

    /**
     * Screened people with no finding whose accounts were not found (match_state none_found). A clean
     * result for someone with no visible accounts is not the same as a clean result for someone whose
     * accounts were read.
     */
    public long noFootprint() {
        return this.noFootprint;
    }

    /**
     * Screened people with no profile to check: the list gave nothing to look up on any requested
     * platform, such as no handle, so nobody looked for a profile (no_profile_to_check on the person). Not
     * in no_footprint, and not partial: nothing failed. Their lookups are on their reports.
     */
    public Optional<Long> noProfileToCheck() {
        return Optional.ofNullable(this.noProfileToCheck);
    }

    /**
     * People the screening did not fully cover. Read each one's coverage on its person.
     */
    public long partialCoverage() {
        return this.partialCoverage;
    }

    /**
     * When the screening finished; equal to the event's created_at.
     */
    public String finishedAt() {
        return this.finishedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The screening that finished.
     */
    public ScreeningCompleted withScreeningId(@Nonnull String screeningId) {
        this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
        return this;
    }

    /**
     * The group it screened.
     */
    public ScreeningCompleted withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    /**
     * completed reached every person; partial left some not fully covered (partial_coverage); failed
     * covered nobody.
     */
    public ScreeningCompleted withStatus(@Nonnull ScreeningCompletedStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * Why a partial or failed screening stopped before reaching everyone, as the screening resource says:
     * expired, worker_attempts_exhausted, attempts_exhausted or screening_unavailable.
     */
    public ScreeningCompleted withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Every person the screening set out to reach: those no screening had reached and those on watch or
     * escalated.
     */
    public ScreeningCompleted withPeopleTotal(long peopleTotal) {
        this.peopleTotal = peopleTotal;
        return this;
    }

    /**
     * People the screening covered, flagged or not.
     */
    public ScreeningCompleted withPeopleScreened(long peopleScreened) {
        this.peopleScreened = peopleScreened;
        return this;
    }

    /**
     * Screened people with a finding: the sum of by_band.
     */
    public ScreeningCompleted withPeopleFlagged(long peopleFlagged) {
        this.peopleFlagged = peopleFlagged;
        return this;
    }

    /**
     * Screened people by the band of their most severe finding.
     */
    public ScreeningCompleted withByBand(@Nonnull ScreeningCompletedByBand byBand) {
        this.byBand = Utils.checkNotNull(byBand, "byBand");
        return this;
    }

    /**
     * Screened people with no finding whose accounts were not found (match_state none_found). A clean
     * result for someone with no visible accounts is not the same as a clean result for someone whose
     * accounts were read.
     */
    public ScreeningCompleted withNoFootprint(long noFootprint) {
        this.noFootprint = noFootprint;
        return this;
    }

    /**
     * Screened people with no profile to check: the list gave nothing to look up on any requested
     * platform, such as no handle, so nobody looked for a profile (no_profile_to_check on the person). Not
     * in no_footprint, and not partial: nothing failed. Their lookups are on their reports.
     */
    public ScreeningCompleted withNoProfileToCheck(@Nullable Long noProfileToCheck) {
        this.noProfileToCheck = noProfileToCheck;
        return this;
    }

    /**
     * People the screening did not fully cover. Read each one's coverage on its person.
     */
    public ScreeningCompleted withPartialCoverage(long partialCoverage) {
        this.partialCoverage = partialCoverage;
        return this;
    }

    /**
     * When the screening finished; equal to the event's created_at.
     */
    public ScreeningCompleted withFinishedAt(@Nonnull String finishedAt) {
        this.finishedAt = Utils.checkNotNull(finishedAt, "finishedAt");
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
        ScreeningCompleted other = (ScreeningCompleted) o;
        return Utils.enhancedDeepEquals(this.screeningId, other.screeningId)
                && Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.peopleTotal, other.peopleTotal)
                && Utils.enhancedDeepEquals(this.peopleScreened, other.peopleScreened)
                && Utils.enhancedDeepEquals(this.peopleFlagged, other.peopleFlagged)
                && Utils.enhancedDeepEquals(this.byBand, other.byBand)
                && Utils.enhancedDeepEquals(this.noFootprint, other.noFootprint)
                && Utils.enhancedDeepEquals(this.noProfileToCheck, other.noProfileToCheck)
                && Utils.enhancedDeepEquals(this.partialCoverage, other.partialCoverage)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                screeningId,
                groupId,
                status,
                errorCode,
                peopleTotal,
                peopleScreened,
                peopleFlagged,
                byBand,
                noFootprint,
                noProfileToCheck,
                partialCoverage,
                finishedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningCompleted.class,
                "screeningId",
                screeningId,
                "groupId",
                groupId,
                "status",
                status,
                "errorCode",
                errorCode,
                "peopleTotal",
                peopleTotal,
                "peopleScreened",
                peopleScreened,
                "peopleFlagged",
                peopleFlagged,
                "byBand",
                byBand,
                "noFootprint",
                noFootprint,
                "noProfileToCheck",
                noProfileToCheck,
                "partialCoverage",
                partialCoverage,
                "finishedAt",
                finishedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String screeningId;

        private String groupId;

        private ScreeningCompletedStatus status;

        private String errorCode;

        private long peopleTotal;

        private long peopleScreened;

        private long peopleFlagged;

        private ScreeningCompletedByBand byBand;

        private long noFootprint;

        private Long noProfileToCheck;

        private long partialCoverage;

        private String finishedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The screening that finished.
         */
        public Builder screeningId(@Nonnull String screeningId) {
            this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
            return this;
        }

        /**
         * The group it screened.
         */
        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        /**
         * completed reached every person; partial left some not fully covered (partial_coverage); failed
         * covered nobody.
         */
        public Builder status(@Nonnull ScreeningCompletedStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        /**
         * Why a partial or failed screening stopped before reaching everyone, as the screening resource says:
         * expired, worker_attempts_exhausted, attempts_exhausted or screening_unavailable.
         */
        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        /**
         * Every person the screening set out to reach: those no screening had reached and those on watch or
         * escalated.
         */
        public Builder peopleTotal(long peopleTotal) {
            this.peopleTotal = peopleTotal;
            return this;
        }

        /**
         * People the screening covered, flagged or not.
         */
        public Builder peopleScreened(long peopleScreened) {
            this.peopleScreened = peopleScreened;
            return this;
        }

        /**
         * Screened people with a finding: the sum of by_band.
         */
        public Builder peopleFlagged(long peopleFlagged) {
            this.peopleFlagged = peopleFlagged;
            return this;
        }

        /**
         * Screened people by the band of their most severe finding.
         */
        public Builder byBand(@Nonnull ScreeningCompletedByBand byBand) {
            this.byBand = Utils.checkNotNull(byBand, "byBand");
            return this;
        }

        /**
         * Screened people with no finding whose accounts were not found (match_state none_found). A clean
         * result for someone with no visible accounts is not the same as a clean result for someone whose
         * accounts were read.
         */
        public Builder noFootprint(long noFootprint) {
            this.noFootprint = noFootprint;
            return this;
        }

        /**
         * Screened people with no profile to check: the list gave nothing to look up on any requested
         * platform, such as no handle, so nobody looked for a profile (no_profile_to_check on the person). Not
         * in no_footprint, and not partial: nothing failed. Their lookups are on their reports.
         */
        public Builder noProfileToCheck(@Nullable Long noProfileToCheck) {
            this.noProfileToCheck = noProfileToCheck;
            return this;
        }

        /**
         * People the screening did not fully cover. Read each one's coverage on its person.
         */
        public Builder partialCoverage(long partialCoverage) {
            this.partialCoverage = partialCoverage;
            return this;
        }

        /**
         * When the screening finished; equal to the event's created_at.
         */
        public Builder finishedAt(@Nonnull String finishedAt) {
            this.finishedAt = Utils.checkNotNull(finishedAt, "finishedAt");
            return this;
        }

        public ScreeningCompleted build() {
            return new ScreeningCompleted(
                    screeningId,
                    groupId,
                    status,
                    errorCode,
                    peopleTotal,
                    peopleScreened,
                    peopleFlagged,
                    byBand,
                    noFootprint,
                    noProfileToCheck,
                    partialCoverage,
                    finishedAt);
        }
    }
}
