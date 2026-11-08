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

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    /**
     * partial finished with some attendees not covered; attendees_failed counts them.
     */
    @JsonProperty("status")
    private ScreeningStatus status;

    @JsonProperty("depth")
    private ScreeningDepth depth;

    @JsonProperty("platforms")
    private List<ScreeningPlatform> platforms;

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
     * Attendees selected when the screening started.
     */
    @JsonProperty("attendees_total")
    private long attendeesTotal;

    /**
     * Attendees screened so far, failed ones included.
     */
    @JsonProperty("attendees_done")
    private long attendeesDone;

    @JsonProperty("attendees_flagged")
    private long attendeesFlagged;

    @JsonProperty("attendees_failed")
    private long attendeesFailed;

    /**
     * Why a failed or cancelled screening stopped.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    @JsonCreator
    public Screening(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("event_id") @Nonnull String eventId,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("status") @Nonnull ScreeningStatus status,
            @JsonProperty("depth") @Nonnull ScreeningDepth depth,
            @JsonProperty("platforms") @Nonnull List<ScreeningPlatform> platforms,
            @JsonProperty("requested_by") @Nonnull String requestedBy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("started_at") @Nullable String startedAt,
            @JsonProperty("finished_at") @Nullable String finishedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("attendees_total") long attendeesTotal,
            @JsonProperty("attendees_done") long attendeesDone,
            @JsonProperty("attendees_flagged") long attendeesFlagged,
            @JsonProperty("attendees_failed") long attendeesFailed,
            @JsonProperty("error_code") @Nullable String errorCode) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
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
        this.attendeesTotal = attendeesTotal;
        this.attendeesDone = attendeesDone;
        this.attendeesFlagged = attendeesFlagged;
        this.attendeesFailed = attendeesFailed;
        this.errorCode = errorCode;
    }

    public Screening(
            @Nonnull String id,
            @Nonnull String eventId,
            @Nonnull AuditSettingsEnvironment environment,
            @Nonnull ScreeningStatus status,
            @Nonnull ScreeningDepth depth,
            @Nonnull List<ScreeningPlatform> platforms,
            @Nonnull String requestedBy,
            @Nonnull String createdAt,
            @Nonnull String expiresAt,
            long attendeesTotal,
            long attendeesDone,
            long attendeesFlagged,
            long attendeesFailed) {
        this(
                id,
                eventId,
                environment,
                status,
                depth,
                platforms,
                requestedBy,
                createdAt,
                null,
                null,
                expiresAt,
                attendeesTotal,
                attendeesDone,
                attendeesFlagged,
                attendeesFailed,
                null);
    }

    public String id() {
        return this.id;
    }

    public String eventId() {
        return this.eventId;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    /**
     * partial finished with some attendees not covered; attendees_failed counts them.
     */
    public ScreeningStatus status() {
        return this.status;
    }

    public ScreeningDepth depth() {
        return this.depth;
    }

    public List<ScreeningPlatform> platforms() {
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
     * Attendees selected when the screening started.
     */
    public long attendeesTotal() {
        return this.attendeesTotal;
    }

    /**
     * Attendees screened so far, failed ones included.
     */
    public long attendeesDone() {
        return this.attendeesDone;
    }

    public long attendeesFlagged() {
        return this.attendeesFlagged;
    }

    public long attendeesFailed() {
        return this.attendeesFailed;
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

    public Screening withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    public Screening withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * partial finished with some attendees not covered; attendees_failed counts them.
     */
    public Screening withStatus(@Nonnull ScreeningStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public Screening withDepth(@Nonnull ScreeningDepth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    public Screening withPlatforms(@Nonnull List<ScreeningPlatform> platforms) {
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
     * Attendees selected when the screening started.
     */
    public Screening withAttendeesTotal(long attendeesTotal) {
        this.attendeesTotal = attendeesTotal;
        return this;
    }

    /**
     * Attendees screened so far, failed ones included.
     */
    public Screening withAttendeesDone(long attendeesDone) {
        this.attendeesDone = attendeesDone;
        return this;
    }

    public Screening withAttendeesFlagged(long attendeesFlagged) {
        this.attendeesFlagged = attendeesFlagged;
        return this;
    }

    public Screening withAttendeesFailed(long attendeesFailed) {
        this.attendeesFailed = attendeesFailed;
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
                && Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.platforms, other.platforms)
                && Utils.enhancedDeepEquals(this.requestedBy, other.requestedBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.startedAt, other.startedAt)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.attendeesTotal, other.attendeesTotal)
                && Utils.enhancedDeepEquals(this.attendeesDone, other.attendeesDone)
                && Utils.enhancedDeepEquals(this.attendeesFlagged, other.attendeesFlagged)
                && Utils.enhancedDeepEquals(this.attendeesFailed, other.attendeesFailed)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                eventId,
                environment,
                status,
                depth,
                platforms,
                requestedBy,
                createdAt,
                startedAt,
                finishedAt,
                expiresAt,
                attendeesTotal,
                attendeesDone,
                attendeesFlagged,
                attendeesFailed,
                errorCode);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Screening.class,
                "id",
                id,
                "eventId",
                eventId,
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
                "attendeesTotal",
                attendeesTotal,
                "attendeesDone",
                attendeesDone,
                "attendeesFlagged",
                attendeesFlagged,
                "attendeesFailed",
                attendeesFailed,
                "errorCode",
                errorCode);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String eventId;

        private AuditSettingsEnvironment environment;

        private ScreeningStatus status;

        private ScreeningDepth depth;

        private List<ScreeningPlatform> platforms;

        private String requestedBy;

        private String createdAt;

        private String startedAt;

        private String finishedAt;

        private String expiresAt;

        private long attendeesTotal;

        private long attendeesDone;

        private long attendeesFlagged;

        private long attendeesFailed;

        private String errorCode;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * partial finished with some attendees not covered; attendees_failed counts them.
         */
        public Builder status(@Nonnull ScreeningStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder depth(@Nonnull ScreeningDepth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        public Builder platforms(@Nonnull List<ScreeningPlatform> platforms) {
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
         * Attendees selected when the screening started.
         */
        public Builder attendeesTotal(long attendeesTotal) {
            this.attendeesTotal = attendeesTotal;
            return this;
        }

        /**
         * Attendees screened so far, failed ones included.
         */
        public Builder attendeesDone(long attendeesDone) {
            this.attendeesDone = attendeesDone;
            return this;
        }

        public Builder attendeesFlagged(long attendeesFlagged) {
            this.attendeesFlagged = attendeesFlagged;
            return this;
        }

        public Builder attendeesFailed(long attendeesFailed) {
            this.attendeesFailed = attendeesFailed;
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
                    eventId,
                    environment,
                    status,
                    depth,
                    platforms,
                    requestedBy,
                    createdAt,
                    startedAt,
                    finishedAt,
                    expiresAt,
                    attendeesTotal,
                    attendeesDone,
                    attendeesFlagged,
                    attendeesFailed,
                    errorCode);
        }
    }
}
