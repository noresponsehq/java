package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningCompleted
 *
 * <p>A screening of an event finished. It counts only the attendees this screening set out to reach, not
 * who they are: read the roster with GET
 * /v1/workspaces/{workspace_id}/events/{environment}/{event_id}/attendees, filtered by band, to act on
 * them. attendees_screened + partial_coverage = attendees_total, attendees_flagged is the sum of
 * by_band, and no_footprint counts screened attendees with no flag whose accounts were not found.
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
     * The event it screened.
     */
    @JsonProperty("event_id")
    private String eventId;

    /**
     * completed reached every attendee; partial left some not fully covered (partial_coverage); failed
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
     * Every attendee the screening set out to reach: those no screening had reached and those on watch or
     * escalated.
     */
    @JsonProperty("attendees_total")
    private long attendeesTotal;

    /**
     * Attendees the screening covered, flagged or not.
     */
    @JsonProperty("attendees_screened")
    private long attendeesScreened;

    /**
     * Screened attendees with a finding: the sum of by_band.
     */
    @JsonProperty("attendees_flagged")
    private long attendeesFlagged;

    /**
     * Screened attendees by the band of their most severe finding.
     */
    @JsonProperty("by_band")
    private ScreeningCompletedByBand byBand;

    /**
     * Screened attendees with no finding whose accounts were not found (match_state none_found). A clean
     * result for someone with no visible accounts is not the same as a clean result for someone whose
     * accounts were read.
     */
    @JsonProperty("no_footprint")
    private long noFootprint;

    /**
     * Attendees the screening did not fully cover. Read each one's coverage on its attendee.
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
            @JsonProperty("event_id") @Nonnull String eventId,
            @JsonProperty("status") @Nonnull ScreeningCompletedStatus status,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("attendees_total") long attendeesTotal,
            @JsonProperty("attendees_screened") long attendeesScreened,
            @JsonProperty("attendees_flagged") long attendeesFlagged,
            @JsonProperty("by_band") @Nonnull ScreeningCompletedByBand byBand,
            @JsonProperty("no_footprint") long noFootprint,
            @JsonProperty("partial_coverage") long partialCoverage,
            @JsonProperty("finished_at") @Nonnull String finishedAt) {
        this.screeningId = Optional.ofNullable(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("screeningId cannot be null"));
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.errorCode = errorCode;
        this.attendeesTotal = attendeesTotal;
        this.attendeesScreened = attendeesScreened;
        this.attendeesFlagged = attendeesFlagged;
        this.byBand =
                Optional.ofNullable(byBand).orElseThrow(() -> new IllegalArgumentException("byBand cannot be null"));
        this.noFootprint = noFootprint;
        this.partialCoverage = partialCoverage;
        this.finishedAt = Optional.ofNullable(finishedAt)
                .orElseThrow(() -> new IllegalArgumentException("finishedAt cannot be null"));
    }

    public ScreeningCompleted(
            @Nonnull String screeningId,
            @Nonnull String eventId,
            @Nonnull ScreeningCompletedStatus status,
            long attendeesTotal,
            long attendeesScreened,
            long attendeesFlagged,
            @Nonnull ScreeningCompletedByBand byBand,
            long noFootprint,
            long partialCoverage,
            @Nonnull String finishedAt) {
        this(
                screeningId,
                eventId,
                status,
                null,
                attendeesTotal,
                attendeesScreened,
                attendeesFlagged,
                byBand,
                noFootprint,
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
     * The event it screened.
     */
    public String eventId() {
        return this.eventId;
    }

    /**
     * completed reached every attendee; partial left some not fully covered (partial_coverage); failed
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
     * Every attendee the screening set out to reach: those no screening had reached and those on watch or
     * escalated.
     */
    public long attendeesTotal() {
        return this.attendeesTotal;
    }

    /**
     * Attendees the screening covered, flagged or not.
     */
    public long attendeesScreened() {
        return this.attendeesScreened;
    }

    /**
     * Screened attendees with a finding: the sum of by_band.
     */
    public long attendeesFlagged() {
        return this.attendeesFlagged;
    }

    /**
     * Screened attendees by the band of their most severe finding.
     */
    public ScreeningCompletedByBand byBand() {
        return this.byBand;
    }

    /**
     * Screened attendees with no finding whose accounts were not found (match_state none_found). A clean
     * result for someone with no visible accounts is not the same as a clean result for someone whose
     * accounts were read.
     */
    public long noFootprint() {
        return this.noFootprint;
    }

    /**
     * Attendees the screening did not fully cover. Read each one's coverage on its attendee.
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
     * The event it screened.
     */
    public ScreeningCompleted withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    /**
     * completed reached every attendee; partial left some not fully covered (partial_coverage); failed
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
     * Every attendee the screening set out to reach: those no screening had reached and those on watch or
     * escalated.
     */
    public ScreeningCompleted withAttendeesTotal(long attendeesTotal) {
        this.attendeesTotal = attendeesTotal;
        return this;
    }

    /**
     * Attendees the screening covered, flagged or not.
     */
    public ScreeningCompleted withAttendeesScreened(long attendeesScreened) {
        this.attendeesScreened = attendeesScreened;
        return this;
    }

    /**
     * Screened attendees with a finding: the sum of by_band.
     */
    public ScreeningCompleted withAttendeesFlagged(long attendeesFlagged) {
        this.attendeesFlagged = attendeesFlagged;
        return this;
    }

    /**
     * Screened attendees by the band of their most severe finding.
     */
    public ScreeningCompleted withByBand(@Nonnull ScreeningCompletedByBand byBand) {
        this.byBand = Utils.checkNotNull(byBand, "byBand");
        return this;
    }

    /**
     * Screened attendees with no finding whose accounts were not found (match_state none_found). A clean
     * result for someone with no visible accounts is not the same as a clean result for someone whose
     * accounts were read.
     */
    public ScreeningCompleted withNoFootprint(long noFootprint) {
        this.noFootprint = noFootprint;
        return this;
    }

    /**
     * Attendees the screening did not fully cover. Read each one's coverage on its attendee.
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
                && Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.attendeesTotal, other.attendeesTotal)
                && Utils.enhancedDeepEquals(this.attendeesScreened, other.attendeesScreened)
                && Utils.enhancedDeepEquals(this.attendeesFlagged, other.attendeesFlagged)
                && Utils.enhancedDeepEquals(this.byBand, other.byBand)
                && Utils.enhancedDeepEquals(this.noFootprint, other.noFootprint)
                && Utils.enhancedDeepEquals(this.partialCoverage, other.partialCoverage)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                screeningId,
                eventId,
                status,
                errorCode,
                attendeesTotal,
                attendeesScreened,
                attendeesFlagged,
                byBand,
                noFootprint,
                partialCoverage,
                finishedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningCompleted.class,
                "screeningId",
                screeningId,
                "eventId",
                eventId,
                "status",
                status,
                "errorCode",
                errorCode,
                "attendeesTotal",
                attendeesTotal,
                "attendeesScreened",
                attendeesScreened,
                "attendeesFlagged",
                attendeesFlagged,
                "byBand",
                byBand,
                "noFootprint",
                noFootprint,
                "partialCoverage",
                partialCoverage,
                "finishedAt",
                finishedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String screeningId;

        private String eventId;

        private ScreeningCompletedStatus status;

        private String errorCode;

        private long attendeesTotal;

        private long attendeesScreened;

        private long attendeesFlagged;

        private ScreeningCompletedByBand byBand;

        private long noFootprint;

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
         * The event it screened.
         */
        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        /**
         * completed reached every attendee; partial left some not fully covered (partial_coverage); failed
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
         * Every attendee the screening set out to reach: those no screening had reached and those on watch or
         * escalated.
         */
        public Builder attendeesTotal(long attendeesTotal) {
            this.attendeesTotal = attendeesTotal;
            return this;
        }

        /**
         * Attendees the screening covered, flagged or not.
         */
        public Builder attendeesScreened(long attendeesScreened) {
            this.attendeesScreened = attendeesScreened;
            return this;
        }

        /**
         * Screened attendees with a finding: the sum of by_band.
         */
        public Builder attendeesFlagged(long attendeesFlagged) {
            this.attendeesFlagged = attendeesFlagged;
            return this;
        }

        /**
         * Screened attendees by the band of their most severe finding.
         */
        public Builder byBand(@Nonnull ScreeningCompletedByBand byBand) {
            this.byBand = Utils.checkNotNull(byBand, "byBand");
            return this;
        }

        /**
         * Screened attendees with no finding whose accounts were not found (match_state none_found). A clean
         * result for someone with no visible accounts is not the same as a clean result for someone whose
         * accounts were read.
         */
        public Builder noFootprint(long noFootprint) {
            this.noFootprint = noFootprint;
            return this;
        }

        /**
         * Attendees the screening did not fully cover. Read each one's coverage on its attendee.
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
                    eventId,
                    status,
                    errorCode,
                    attendeesTotal,
                    attendeesScreened,
                    attendeesFlagged,
                    byBand,
                    noFootprint,
                    partialCoverage,
                    finishedAt);
        }
    }
}
