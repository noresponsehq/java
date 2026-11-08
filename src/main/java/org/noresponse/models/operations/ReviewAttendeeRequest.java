package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.AttendeeReviewRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ReviewAttendeeRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=environment")
    private KeyEnvironment environment;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=event_id")
    private String eventId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=attendee_id")
    private String attendeeId;

    @NoResponseMetadata("request:mediaType=application/json")
    private AttendeeReviewRequest body;

    @JsonCreator
    public ReviewAttendeeRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull String attendeeId,
            @Nonnull AttendeeReviewRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.attendeeId = Optional.ofNullable(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("attendeeId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public KeyEnvironment environment() {
        return this.environment;
    }

    public String eventId() {
        return this.eventId;
    }

    public String attendeeId() {
        return this.attendeeId;
    }

    public AttendeeReviewRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ReviewAttendeeRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public ReviewAttendeeRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ReviewAttendeeRequest withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    public ReviewAttendeeRequest withAttendeeId(@Nonnull String attendeeId) {
        this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
        return this;
    }

    public ReviewAttendeeRequest withBody(@Nonnull AttendeeReviewRequest body) {
        this.body = Utils.checkNotNull(body, "body");
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
        ReviewAttendeeRequest other = (ReviewAttendeeRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.attendeeId, other.attendeeId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, eventId, attendeeId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewAttendeeRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "eventId",
                eventId,
                "attendeeId",
                attendeeId,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private String eventId;

        private String attendeeId;

        private AttendeeReviewRequest body;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
         * test environment's id answer 404.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        /**
         * The environment: live or test. Each has its own settings. Any other value answers 400.
         *
         * <p>An API key reads and changes only its own environment's; the other answers 404.
         */
        public Builder environment(@Nonnull KeyEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        public Builder attendeeId(@Nonnull String attendeeId) {
            this.attendeeId = Utils.checkNotNull(attendeeId, "attendeeId");
            return this;
        }

        public Builder body(@Nonnull AttendeeReviewRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ReviewAttendeeRequest build() {
            return new ReviewAttendeeRequest(workspaceId, environment, eventId, attendeeId, body);
        }
    }
}
