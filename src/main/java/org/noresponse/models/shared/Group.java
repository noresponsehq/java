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

/**
 * Group
 *
 * <p>A workspace group for a building, organization or event. Dates are optional and do not control
 * person retention.
 */
public class Group {

    @JsonProperty("id")
    private String id;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    /**
     * The group's name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * What the group's people share: an event, an organisation, a place or another attribute.
     */
    @JsonProperty("type")
    private GroupType type;

    /**
     * The attribute its people share. Empty when none was given.
     */
    @JsonProperty("attribute")
    private String attribute;

    /**
     * Optional event start; null for an ongoing group.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("starts_at")
    private JsonNullable<String> startsAt;

    /**
     * Optional event end; requires starts_at and must be at or after it. Null when no end is set.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("ends_at")
    private JsonNullable<String> endsAt;

    /**
     * Where the group is held.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("venue")
    private String venue;

    /**
     * The organisation hosting the group.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("host")
    private String host;

    /**
     * The people the group protects.
     */
    @JsonProperty("principals")
    private List<Principal> principals;

    /**
     * Sensitive topics.
     */
    @JsonProperty("topics")
    private List<String> topics;

    /**
     * How many people the group holds.
     */
    @JsonProperty("people")
    private long people;

    /**
     * Goes up by one with every change.
     */
    @JsonProperty("version")
    private long version;

    /**
     * The member or key label that created the group.
     */
    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonCreator
    public Group(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("type") @Nonnull GroupType type,
            @JsonProperty("attribute") @Nonnull String attribute,
            @JsonProperty("starts_at") @Nullable String startsAt,
            @JsonProperty("ends_at") @Nullable String endsAt,
            @JsonProperty("venue") @Nullable String venue,
            @JsonProperty("host") @Nullable String host,
            @JsonProperty("principals") @Nonnull List<Principal> principals,
            @JsonProperty("topics") @Nonnull List<String> topics,
            @JsonProperty("people") long people,
            @JsonProperty("version") long version,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.type = Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("type cannot be null"));
        this.attribute = Optional.ofNullable(attribute)
                .orElseThrow(() -> new IllegalArgumentException("attribute cannot be null"));
        this.startsAt = JsonNullable.of(startsAt);
        this.endsAt = JsonNullable.of(endsAt);
        this.venue = venue;
        this.host = host;
        this.principals = Optional.ofNullable(principals)
                .orElseThrow(() -> new IllegalArgumentException("principals cannot be null"));
        this.topics =
                Optional.ofNullable(topics).orElseThrow(() -> new IllegalArgumentException("topics cannot be null"));
        this.people = people;
        this.version = version;
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
    }

    public Group(
            @Nonnull String id,
            @Nonnull AuditSettingsEnvironment environment,
            @Nonnull String name,
            @Nonnull GroupType type,
            @Nonnull String attribute,
            @Nonnull List<Principal> principals,
            @Nonnull List<String> topics,
            long people,
            long version,
            @Nonnull String createdBy,
            @Nonnull String createdAt,
            @Nonnull String updatedAt) {
        this(
                id,
                environment,
                name,
                type,
                attribute,
                null,
                null,
                null,
                null,
                principals,
                topics,
                people,
                version,
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
     * The group's name.
     */
    public String name() {
        return this.name;
    }

    /**
     * What the group's people share: an event, an organisation, a place or another attribute.
     */
    public GroupType type() {
        return this.type;
    }

    /**
     * The attribute its people share. Empty when none was given.
     */
    public String attribute() {
        return this.attribute;
    }

    /**
     * Optional event start; null for an ongoing group.
     */
    public JsonNullable<String> startsAt() {
        return this.startsAt;
    }

    /**
     * Optional event end; requires starts_at and must be at or after it. Null when no end is set.
     */
    public JsonNullable<String> endsAt() {
        return this.endsAt;
    }

    /**
     * Where the group is held.
     */
    public Optional<String> venue() {
        return Optional.ofNullable(this.venue);
    }

    /**
     * The organisation hosting the group.
     */
    public Optional<String> host() {
        return Optional.ofNullable(this.host);
    }

    /**
     * The people the group protects.
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
     * How many people the group holds.
     */
    public long people() {
        return this.people;
    }

    /**
     * Goes up by one with every change.
     */
    public long version() {
        return this.version;
    }

    /**
     * The member or key label that created the group.
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

    public Group withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public Group withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * The group's name.
     */
    public Group withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * What the group's people share: an event, an organisation, a place or another attribute.
     */
    public Group withType(@Nonnull GroupType type) {
        this.type = Utils.checkNotNull(type, "type");
        return this;
    }

    /**
     * The attribute its people share. Empty when none was given.
     */
    public Group withAttribute(@Nonnull String attribute) {
        this.attribute = Utils.checkNotNull(attribute, "attribute");
        return this;
    }

    /**
     * Optional event start; null for an ongoing group.
     */
    public Group withStartsAt(@Nullable String startsAt) {
        this.startsAt = JsonNullable.of(startsAt);
        return this;
    }

    /**
     * Optional event end; requires starts_at and must be at or after it. Null when no end is set.
     */
    public Group withEndsAt(@Nullable String endsAt) {
        this.endsAt = JsonNullable.of(endsAt);
        return this;
    }

    /**
     * Where the group is held.
     */
    public Group withVenue(@Nullable String venue) {
        this.venue = venue;
        return this;
    }

    /**
     * The organisation hosting the group.
     */
    public Group withHost(@Nullable String host) {
        this.host = host;
        return this;
    }

    /**
     * The people the group protects.
     */
    public Group withPrincipals(@Nonnull List<Principal> principals) {
        this.principals = Utils.checkNotNull(principals, "principals");
        return this;
    }

    /**
     * Sensitive topics.
     */
    public Group withTopics(@Nonnull List<String> topics) {
        this.topics = Utils.checkNotNull(topics, "topics");
        return this;
    }

    /**
     * How many people the group holds.
     */
    public Group withPeople(long people) {
        this.people = people;
        return this;
    }

    /**
     * Goes up by one with every change.
     */
    public Group withVersion(long version) {
        this.version = version;
        return this;
    }

    /**
     * The member or key label that created the group.
     */
    public Group withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public Group withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Group withUpdatedAt(@Nonnull String updatedAt) {
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
        Group other = (Group) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.type, other.type)
                && Utils.enhancedDeepEquals(this.attribute, other.attribute)
                && Utils.enhancedDeepEquals(this.startsAt, other.startsAt)
                && Utils.enhancedDeepEquals(this.endsAt, other.endsAt)
                && Utils.enhancedDeepEquals(this.venue, other.venue)
                && Utils.enhancedDeepEquals(this.host, other.host)
                && Utils.enhancedDeepEquals(this.principals, other.principals)
                && Utils.enhancedDeepEquals(this.topics, other.topics)
                && Utils.enhancedDeepEquals(this.people, other.people)
                && Utils.enhancedDeepEquals(this.version, other.version)
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
                type,
                attribute,
                startsAt,
                endsAt,
                venue,
                host,
                principals,
                topics,
                people,
                version,
                createdBy,
                createdAt,
                updatedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Group.class,
                "id",
                id,
                "environment",
                environment,
                "name",
                name,
                "type",
                type,
                "attribute",
                attribute,
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
                "people",
                people,
                "version",
                version,
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

        private GroupType type;

        private String attribute;

        private String startsAt;

        private String endsAt;

        private String venue;

        private String host;

        private List<Principal> principals;

        private List<String> topics;

        private long people;

        private long version;

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
         * The group's name.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * What the group's people share: an event, an organisation, a place or another attribute.
         */
        public Builder type(@Nonnull GroupType type) {
            this.type = Utils.checkNotNull(type, "type");
            return this;
        }

        /**
         * The attribute its people share. Empty when none was given.
         */
        public Builder attribute(@Nonnull String attribute) {
            this.attribute = Utils.checkNotNull(attribute, "attribute");
            return this;
        }

        /**
         * Optional event start; null for an ongoing group.
         */
        public Builder startsAt(@Nullable String startsAt) {
            this.startsAt = startsAt;
            return this;
        }

        /**
         * Optional event end; requires starts_at and must be at or after it. Null when no end is set.
         */
        public Builder endsAt(@Nullable String endsAt) {
            this.endsAt = endsAt;
            return this;
        }

        /**
         * Where the group is held.
         */
        public Builder venue(@Nullable String venue) {
            this.venue = venue;
            return this;
        }

        /**
         * The organisation hosting the group.
         */
        public Builder host(@Nullable String host) {
            this.host = host;
            return this;
        }

        /**
         * The people the group protects.
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
         * How many people the group holds.
         */
        public Builder people(long people) {
            this.people = people;
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
         * The member or key label that created the group.
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

        public Group build() {
            return new Group(
                    id,
                    environment,
                    name,
                    type,
                    attribute,
                    startsAt,
                    endsAt,
                    venue,
                    host,
                    principals,
                    topics,
                    people,
                    version,
                    createdBy,
                    createdAt,
                    updatedAt);
        }
    }
}
