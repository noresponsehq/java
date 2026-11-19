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
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * PersonSchedule
 *
 * <p>The person's rerun schedule. Each due run starts a full investigation of the person, as POST
 * /v1/investigations does, and its changes are stored as changes. The schedule lives in the database
 * and the worker runs it, so it survives restarts and releases; each due run starts exactly one
 * investigation.
 */
public class PersonSchedule {
    /**
     * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
     * was set; off runs no scheduled investigation.
     */
    @JsonProperty("period")
    private SchedulePeriod period;

    /**
     * When the next scheduled investigation starts, within a minute. null when the schedule is off.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("next_run_at")
    private JsonNullable<String> nextRunAt;

    /**
     * The investigation the schedule last started. null before its first run.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("last_investigation_id")
    private JsonNullable<String> lastInvestigationId;

    /**
     * Why the last due run started no investigation, as the problem code its start would have answered.
     * null when the last due run started one, or none has come due. A run that started none is not
     * retried: the schedule moves to its next period.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("last_error")
    private JsonNullable<LastError> lastError;

    @JsonInclude(Include.ALWAYS)
    @JsonProperty("updated_at")
    private JsonNullable<String> updatedAt;

    /**
     * Who last set the schedule.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("updated_by")
    private JsonNullable<String> updatedBy;

    @JsonCreator
    public PersonSchedule(
            @JsonProperty("period") @Nonnull SchedulePeriod period,
            @JsonProperty("next_run_at") @Nullable String nextRunAt,
            @JsonProperty("last_investigation_id") @Nullable String lastInvestigationId,
            @JsonProperty("last_error") @Nullable LastError lastError,
            @JsonProperty("updated_at") @Nullable String updatedAt,
            @JsonProperty("updated_by") @Nullable String updatedBy) {
        this.period =
                Optional.ofNullable(period).orElseThrow(() -> new IllegalArgumentException("period cannot be null"));
        this.nextRunAt = JsonNullable.of(nextRunAt);
        this.lastInvestigationId = JsonNullable.of(lastInvestigationId);
        this.lastError = JsonNullable.of(lastError);
        this.updatedAt = JsonNullable.of(updatedAt);
        this.updatedBy = JsonNullable.of(updatedBy);
    }

    public PersonSchedule(@Nonnull SchedulePeriod period) {
        this(period, null, null, null, null, null);
    }

    /**
     * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
     * was set; off runs no scheduled investigation.
     */
    public SchedulePeriod period() {
        return this.period;
    }

    /**
     * When the next scheduled investigation starts, within a minute. null when the schedule is off.
     */
    public JsonNullable<String> nextRunAt() {
        return this.nextRunAt;
    }

    /**
     * The investigation the schedule last started. null before its first run.
     */
    public JsonNullable<String> lastInvestigationId() {
        return this.lastInvestigationId;
    }

    /**
     * Why the last due run started no investigation, as the problem code its start would have answered.
     * null when the last due run started one, or none has come due. A run that started none is not
     * retried: the schedule moves to its next period.
     */
    public JsonNullable<LastError> lastError() {
        return this.lastError;
    }

    public JsonNullable<String> updatedAt() {
        return this.updatedAt;
    }

    /**
     * Who last set the schedule.
     */
    public JsonNullable<String> updatedBy() {
        return this.updatedBy;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
     * was set; off runs no scheduled investigation.
     */
    public PersonSchedule withPeriod(@Nonnull SchedulePeriod period) {
        this.period = Utils.checkNotNull(period, "period");
        return this;
    }

    /**
     * When the next scheduled investigation starts, within a minute. null when the schedule is off.
     */
    public PersonSchedule withNextRunAt(@Nullable String nextRunAt) {
        this.nextRunAt = JsonNullable.of(nextRunAt);
        return this;
    }

    /**
     * The investigation the schedule last started. null before its first run.
     */
    public PersonSchedule withLastInvestigationId(@Nullable String lastInvestigationId) {
        this.lastInvestigationId = JsonNullable.of(lastInvestigationId);
        return this;
    }

    /**
     * Why the last due run started no investigation, as the problem code its start would have answered.
     * null when the last due run started one, or none has come due. A run that started none is not
     * retried: the schedule moves to its next period.
     */
    public PersonSchedule withLastError(@Nullable LastError lastError) {
        this.lastError = JsonNullable.of(lastError);
        return this;
    }

    public PersonSchedule withUpdatedAt(@Nullable String updatedAt) {
        this.updatedAt = JsonNullable.of(updatedAt);
        return this;
    }

    /**
     * Who last set the schedule.
     */
    public PersonSchedule withUpdatedBy(@Nullable String updatedBy) {
        this.updatedBy = JsonNullable.of(updatedBy);
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
        PersonSchedule other = (PersonSchedule) o;
        return Utils.enhancedDeepEquals(this.period, other.period)
                && Utils.enhancedDeepEquals(this.nextRunAt, other.nextRunAt)
                && Utils.enhancedDeepEquals(this.lastInvestigationId, other.lastInvestigationId)
                && Utils.enhancedDeepEquals(this.lastError, other.lastError)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.updatedBy, other.updatedBy);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(period, nextRunAt, lastInvestigationId, lastError, updatedAt, updatedBy);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonSchedule.class,
                "period",
                period,
                "nextRunAt",
                nextRunAt,
                "lastInvestigationId",
                lastInvestigationId,
                "lastError",
                lastError,
                "updatedAt",
                updatedAt,
                "updatedBy",
                updatedBy);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private SchedulePeriod period;

        private String nextRunAt;

        private String lastInvestigationId;

        private LastError lastError;

        private String updatedAt;

        private String updatedBy;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
         * was set; off runs no scheduled investigation.
         */
        public Builder period(@Nonnull SchedulePeriod period) {
            this.period = Utils.checkNotNull(period, "period");
            return this;
        }

        /**
         * When the next scheduled investigation starts, within a minute. null when the schedule is off.
         */
        public Builder nextRunAt(@Nullable String nextRunAt) {
            this.nextRunAt = nextRunAt;
            return this;
        }

        /**
         * The investigation the schedule last started. null before its first run.
         */
        public Builder lastInvestigationId(@Nullable String lastInvestigationId) {
            this.lastInvestigationId = lastInvestigationId;
            return this;
        }

        /**
         * Why the last due run started no investigation, as the problem code its start would have answered.
         * null when the last due run started one, or none has come due. A run that started none is not
         * retried: the schedule moves to its next period.
         */
        public Builder lastError(@Nullable LastError lastError) {
            this.lastError = lastError;
            return this;
        }

        public Builder updatedAt(@Nullable String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * Who last set the schedule.
         */
        public Builder updatedBy(@Nullable String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public PersonSchedule build() {
            return new PersonSchedule(period, nextRunAt, lastInvestigationId, lastError, updatedAt, updatedBy);
        }
    }
}
