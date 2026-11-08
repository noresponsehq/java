package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ListAttendeesBand;
import org.noresponse.models.shared.ListAttendeesDecision;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListAttendeesRequest {
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

    /**
     * At most this many attendees.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * Read attendees after this one.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=after")
    private String after;

    /**
     * Only attendees whose highest finding has this band; none for attendees with no finding.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=band")
    private ListAttendeesBand band;

    /**
     * Only attendees with this decision; undecided for none yet.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=decision")
    private ListAttendeesDecision decision;

    @JsonCreator
    public ListAttendeesRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nullable Long limit,
            @Nullable String after,
            @Nullable ListAttendeesBand band,
            @Nullable ListAttendeesDecision decision) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.after = after;
        this.band = band;
        this.decision = decision;
    }

    public ListAttendeesRequest(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String eventId) {
        this(workspaceId, environment, eventId, null, null, null, null);
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

    /**
     * At most this many attendees.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Read attendees after this one.
     */
    public Optional<String> after() {
        return Optional.ofNullable(this.after);
    }

    /**
     * Only attendees whose highest finding has this band; none for attendees with no finding.
     */
    public Optional<ListAttendeesBand> band() {
        return Optional.ofNullable(this.band);
    }

    /**
     * Only attendees with this decision; undecided for none yet.
     */
    public Optional<ListAttendeesDecision> decision() {
        return Optional.ofNullable(this.decision);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ListAttendeesRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public ListAttendeesRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ListAttendeesRequest withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    /**
     * At most this many attendees.
     */
    public ListAttendeesRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Read attendees after this one.
     */
    public ListAttendeesRequest withAfter(@Nullable String after) {
        this.after = after;
        return this;
    }

    /**
     * Only attendees whose highest finding has this band; none for attendees with no finding.
     */
    public ListAttendeesRequest withBand(@Nullable ListAttendeesBand band) {
        this.band = band;
        return this;
    }

    /**
     * Only attendees with this decision; undecided for none yet.
     */
    public ListAttendeesRequest withDecision(@Nullable ListAttendeesDecision decision) {
        this.decision = decision;
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
        ListAttendeesRequest other = (ListAttendeesRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.after, other.after)
                && Utils.enhancedDeepEquals(this.band, other.band)
                && Utils.enhancedDeepEquals(this.decision, other.decision);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, eventId, limit, after, band, decision);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListAttendeesRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "eventId",
                eventId,
                "limit",
                limit,
                "after",
                after,
                "band",
                band,
                "decision",
                decision);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private String eventId;

        private Long limit;

        private String after;

        private ListAttendeesBand band;

        private ListAttendeesDecision decision;

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

        /**
         * At most this many attendees.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Read attendees after this one.
         */
        public Builder after(@Nullable String after) {
            this.after = after;
            return this;
        }

        /**
         * Only attendees whose highest finding has this band; none for attendees with no finding.
         */
        public Builder band(@Nullable ListAttendeesBand band) {
            this.band = band;
            return this;
        }

        /**
         * Only attendees with this decision; undecided for none yet.
         */
        public Builder decision(@Nullable ListAttendeesDecision decision) {
            this.decision = decision;
            return this;
        }

        public ListAttendeesRequest build() {
            return new ListAttendeesRequest(workspaceId, environment, eventId, limit, after, band, decision);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "100", new TypeReference<Long>() {});
    }
}
