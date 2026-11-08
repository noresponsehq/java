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
 * Event
 *
 * <p>An event and its screening context.
 */
public class Event {

    @JsonProperty("id")
    private String id;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    /**
     * The event's name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * When the event starts.
     */
    @JsonProperty("starts_at")
    private String startsAt;

    /**
     * When the event ends, at or after its start; the start when omitted.
     */
    @JsonProperty("ends_at")
    private String endsAt;

    /**
     * Where the event is held.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("venue")
    private String venue;

    /**
     * The organisation hosting the event.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("host")
    private String host;

    /**
     * The people the event protects.
     */
    @JsonProperty("principals")
    private List<Principal> principals;

    /**
     * Sensitive topics.
     */
    @JsonProperty("topics")
    private List<String> topics;

    /**
     * How many attendees the event holds.
     */
    @JsonProperty("attendees")
    private long attendees;

    /**
     * Goes up by one with every change.
     */
    @JsonProperty("version")
    private long version;

    /**
     * When raw collected items and unmatched candidate profiles are purged: 30 days after the event ends.
     */
    @JsonProperty("purge_after")
    private String purgeAfter;

    /**
     * The member or key label that created the event.
     */
    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonCreator
    public Event(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("starts_at") @Nonnull String startsAt,
            @JsonProperty("ends_at") @Nonnull String endsAt,
            @JsonProperty("venue") @Nullable String venue,
            @JsonProperty("host") @Nullable String host,
            @JsonProperty("principals") @Nonnull List<Principal> principals,
            @JsonProperty("topics") @Nonnull List<String> topics,
            @JsonProperty("attendees") long attendees,
            @JsonProperty("version") long version,
            @JsonProperty("purge_after") @Nonnull String purgeAfter,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.startsAt = Optional.ofNullable(startsAt)
                .orElseThrow(() -> new IllegalArgumentException("startsAt cannot be null"));
        this.endsAt =
                Optional.ofNullable(endsAt).orElseThrow(() -> new IllegalArgumentException("endsAt cannot be null"));
        this.venue = venue;
        this.host = host;
        this.principals = Optional.ofNullable(principals)
                .orElseThrow(() -> new IllegalArgumentException("principals cannot be null"));
        this.topics =
                Optional.ofNullable(topics).orElseThrow(() -> new IllegalArgumentException("topics cannot be null"));
        this.attendees = attendees;
        this.version = version;
        this.purgeAfter = Optional.ofNullable(purgeAfter)
                .orElseThrow(() -> new IllegalArgumentException("purgeAfter cannot be null"));
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
    }

    public Event(
            @Nonnull String id,
            @Nonnull AuditSettingsEnvironment environment,
            @Nonnull String name,
            @Nonnull String startsAt,
            @Nonnull String endsAt,
            @Nonnull List<Principal> principals,
            @Nonnull List<String> topics,
            long attendees,
            long version,
            @Nonnull String purgeAfter,
            @Nonnull String createdBy,
            @Nonnull String createdAt,
            @Nonnull String updatedAt) {
        this(
                id,
                environment,
                name,
                startsAt,
                endsAt,
                null,
                null,
                principals,
                topics,
                attendees,
                version,
                purgeAfter,
                createdBy,
                createdAt,
                updatedAt);
    }

    public String id() {
        return this.id;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    /**
     * The event's name.
     */
    public String name() {
        return this.name;
    }

    /**
     * When the event starts.
     */
    public String startsAt() {
        return this.startsAt;
    }

    /**
     * When the event ends, at or after its start; the start when omitted.
     */
    public String endsAt() {
        return this.endsAt;
    }

    /**
     * Where the event is held.
     */
    public Optional<String> venue() {
        return Optional.ofNullable(this.venue);
    }

    /**
     * The organisation hosting the event.
     */
    public Optional<String> host() {
        return Optional.ofNullable(this.host);
    }

    /**
     * The people the event protects.
     */
    public List<Principal> principals() {
        return this.principals;
    }

    /**
     * Sensitive topics.
     */
    public List<String> topics() {
        return this.topics;
    }

    /**
     * How many attendees the event holds.
     */
    public long attendees() {
        return this.attendees;
    }

    /**
     * Goes up by one with every change.
     */
    public long version() {
        return this.version;
    }

    /**
     * When raw collected items and unmatched candidate profiles are purged: 30 days after the event ends.
     */
    public String purgeAfter() {
        return this.purgeAfter;
    }

    /**
     * The member or key label that created the event.
     */
    public String createdBy() {
        return this.createdBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Event withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public Event withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * The event's name.
     */
    public Event withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * When the event starts.
     */
    public Event withStartsAt(@Nonnull String startsAt) {
        this.startsAt = Utils.checkNotNull(startsAt, "startsAt");
        return this;
    }

    /**
     * When the event ends, at or after its start; the start when omitted.
     */
    public Event withEndsAt(@Nonnull String endsAt) {
        this.endsAt = Utils.checkNotNull(endsAt, "endsAt");
        return this;
    }

    /**
     * Where the event is held.
     */
    public Event withVenue(@Nullable String venue) {
        this.venue = venue;
        return this;
    }

    /**
     * The organisation hosting the event.
     */
    public Event withHost(@Nullable String host) {
        this.host = host;
        return this;
    }

    /**
     * The people the event protects.
     */
    public Event withPrincipals(@Nonnull List<Principal> principals) {
        this.principals = Utils.checkNotNull(principals, "principals");
        return this;
    }

    /**
     * Sensitive topics.
     */
    public Event withTopics(@Nonnull List<String> topics) {
        this.topics = Utils.checkNotNull(topics, "topics");
        return this;
    }

    /**
     * How many attendees the event holds.
     */
    public Event withAttendees(long attendees) {
        this.attendees = attendees;
        return this;
    }

    /**
     * Goes up by one with every change.
     */
    public Event withVersion(long version) {
        this.version = version;
        return this;
    }

    /**
     * When raw collected items and unmatched candidate profiles are purged: 30 days after the event ends.
     */
    public Event withPurgeAfter(@Nonnull String purgeAfter) {
        this.purgeAfter = Utils.checkNotNull(purgeAfter, "purgeAfter");
        return this;
    }

    /**
     * The member or key label that created the event.
     */
    public Event withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public Event withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Event withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
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
        Event other = (Event) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.startsAt, other.startsAt)
                && Utils.enhancedDeepEquals(this.endsAt, other.endsAt)
                && Utils.enhancedDeepEquals(this.venue, other.venue)
                && Utils.enhancedDeepEquals(this.host, other.host)
                && Utils.enhancedDeepEquals(this.principals, other.principals)
                && Utils.enhancedDeepEquals(this.topics, other.topics)
                && Utils.enhancedDeepEquals(this.attendees, other.attendees)
                && Utils.enhancedDeepEquals(this.version, other.version)
                && Utils.enhancedDeepEquals(this.purgeAfter, other.purgeAfter)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                environment,
                name,
                startsAt,
                endsAt,
                venue,
                host,
                principals,
                topics,
                attendees,
                version,
                purgeAfter,
                createdBy,
                createdAt,
                updatedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Event.class,
                "id",
                id,
                "environment",
                environment,
                "name",
                name,
                "startsAt",
                startsAt,
                "endsAt",
                endsAt,
                "venue",
                venue,
                "host",
                host,
                "principals",
                principals,
                "topics",
                topics,
                "attendees",
                attendees,
                "version",
                version,
                "purgeAfter",
                purgeAfter,
                "createdBy",
                createdBy,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private AuditSettingsEnvironment environment;

        private String name;

        private String startsAt;

        private String endsAt;

        private String venue;

        private String host;

        private List<Principal> principals;

        private List<String> topics;

        private long attendees;

        private long version;

        private String purgeAfter;

        private String createdBy;

        private String createdAt;

        private String updatedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * The event's name.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * When the event starts.
         */
        public Builder startsAt(@Nonnull String startsAt) {
            this.startsAt = Utils.checkNotNull(startsAt, "startsAt");
            return this;
        }

        /**
         * When the event ends, at or after its start; the start when omitted.
         */
        public Builder endsAt(@Nonnull String endsAt) {
            this.endsAt = Utils.checkNotNull(endsAt, "endsAt");
            return this;
        }

        /**
         * Where the event is held.
         */
        public Builder venue(@Nullable String venue) {
            this.venue = venue;
            return this;
        }

        /**
         * The organisation hosting the event.
         */
        public Builder host(@Nullable String host) {
            this.host = host;
            return this;
        }

        /**
         * The people the event protects.
         */
        public Builder principals(@Nonnull List<Principal> principals) {
            this.principals = Utils.checkNotNull(principals, "principals");
            return this;
        }

        /**
         * Sensitive topics.
         */
        public Builder topics(@Nonnull List<String> topics) {
            this.topics = Utils.checkNotNull(topics, "topics");
            return this;
        }

        /**
         * How many attendees the event holds.
         */
        public Builder attendees(long attendees) {
            this.attendees = attendees;
            return this;
        }

        /**
         * Goes up by one with every change.
         */
        public Builder version(long version) {
            this.version = version;
            return this;
        }

        /**
         * When raw collected items and unmatched candidate profiles are purged: 30 days after the event ends.
         */
        public Builder purgeAfter(@Nonnull String purgeAfter) {
            this.purgeAfter = Utils.checkNotNull(purgeAfter, "purgeAfter");
            return this;
        }

        /**
         * The member or key label that created the event.
         */
        public Builder createdBy(@Nonnull String createdBy) {
            this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
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

        public Event build() {
            return new Event(
                    id,
                    environment,
                    name,
                    startsAt,
                    endsAt,
                    venue,
                    host,
                    principals,
                    topics,
                    attendees,
                    version,
                    purgeAfter,
                    createdBy,
                    createdAt,
                    updatedAt);
        }
    }
}
