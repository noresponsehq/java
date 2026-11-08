package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * EventUpdate
 *
 * <p>Changes the fields it names; at least one besides expected_version.
 */
public class EventUpdate {
    /**
     * The event's name.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private String name;

    /**
     * When the event starts.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("starts_at")
    private String startsAt;

    /**
     * When the event ends, at or after its start; the start when omitted.
     */
    @JsonInclude(Include.NON_ABSENT)
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
     * The people the event protects, at most 20.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("principals")
    private List<Principal> principals;

    /**
     * Sensitive topics the screening reads posts against, at most 50.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("topics")
    private List<String> topics;

    /**
     * When present, the change applies only if the event is still at this version (409 event_conflict
     * otherwise).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expected_version")
    private Long expectedVersion;

    @JsonCreator
    public EventUpdate(
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("starts_at") @Nullable String startsAt,
            @JsonProperty("ends_at") @Nullable String endsAt,
            @JsonProperty("venue") @Nullable String venue,
            @JsonProperty("host") @Nullable String host,
            @JsonProperty("principals") @Nullable List<Principal> principals,
            @JsonProperty("topics") @Nullable List<String> topics,
            @JsonProperty("expected_version") @Nullable Long expectedVersion) {
        this.name = name;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.venue = venue;
        this.host = host;
        this.principals = principals;
        this.topics = topics;
        this.expectedVersion = expectedVersion;
    }

    public EventUpdate() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * The event's name.
     */
    public Optional<String> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * When the event starts.
     */
    public Optional<String> startsAt() {
        return Optional.ofNullable(this.startsAt);
    }

    /**
     * When the event ends, at or after its start; the start when omitted.
     */
    public Optional<String> endsAt() {
        return Optional.ofNullable(this.endsAt);
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
     * The people the event protects, at most 20.
     */
    public Optional<List<Principal>> principals() {
        return Optional.ofNullable(this.principals);
    }

    /**
     * Sensitive topics the screening reads posts against, at most 50.
     */
    public Optional<List<String>> topics() {
        return Optional.ofNullable(this.topics);
    }

    /**
     * When present, the change applies only if the event is still at this version (409 event_conflict
     * otherwise).
     */
    public Optional<Long> expectedVersion() {
        return Optional.ofNullable(this.expectedVersion);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The event's name.
     */
    public EventUpdate withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * When the event starts.
     */
    public EventUpdate withStartsAt(@Nullable String startsAt) {
        this.startsAt = startsAt;
        return this;
    }

    /**
     * When the event ends, at or after its start; the start when omitted.
     */
    public EventUpdate withEndsAt(@Nullable String endsAt) {
        this.endsAt = endsAt;
        return this;
    }

    /**
     * Where the event is held.
     */
    public EventUpdate withVenue(@Nullable String venue) {
        this.venue = venue;
        return this;
    }

    /**
     * The organisation hosting the event.
     */
    public EventUpdate withHost(@Nullable String host) {
        this.host = host;
        return this;
    }

    /**
     * The people the event protects, at most 20.
     */
    public EventUpdate withPrincipals(@Nullable List<Principal> principals) {
        this.principals = principals;
        return this;
    }

    /**
     * Sensitive topics the screening reads posts against, at most 50.
     */
    public EventUpdate withTopics(@Nullable List<String> topics) {
        this.topics = topics;
        return this;
    }

    /**
     * When present, the change applies only if the event is still at this version (409 event_conflict
     * otherwise).
     */
    public EventUpdate withExpectedVersion(@Nullable Long expectedVersion) {
        this.expectedVersion = expectedVersion;
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
        EventUpdate other = (EventUpdate) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.startsAt, other.startsAt)
                && Utils.enhancedDeepEquals(this.endsAt, other.endsAt)
                && Utils.enhancedDeepEquals(this.venue, other.venue)
                && Utils.enhancedDeepEquals(this.host, other.host)
                && Utils.enhancedDeepEquals(this.principals, other.principals)
                && Utils.enhancedDeepEquals(this.topics, other.topics)
                && Utils.enhancedDeepEquals(this.expectedVersion, other.expectedVersion);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, startsAt, endsAt, venue, host, principals, topics, expectedVersion);
    }

    @Override
    public String toString() {
        return Utils.toString(
                EventUpdate.class,
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
                "expectedVersion",
                expectedVersion);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private String startsAt;

        private String endsAt;

        private String venue;

        private String host;

        private List<Principal> principals;

        private List<String> topics;

        private Long expectedVersion;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The event's name.
         */
        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        /**
         * When the event starts.
         */
        public Builder startsAt(@Nullable String startsAt) {
            this.startsAt = startsAt;
            return this;
        }

        /**
         * When the event ends, at or after its start; the start when omitted.
         */
        public Builder endsAt(@Nullable String endsAt) {
            this.endsAt = endsAt;
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
         * The people the event protects, at most 20.
         */
        public Builder principals(@Nullable List<Principal> principals) {
            this.principals = principals;
            return this;
        }

        /**
         * Sensitive topics the screening reads posts against, at most 50.
         */
        public Builder topics(@Nullable List<String> topics) {
            this.topics = topics;
            return this;
        }

        /**
         * When present, the change applies only if the event is still at this version (409 event_conflict
         * otherwise).
         */
        public Builder expectedVersion(@Nullable Long expectedVersion) {
            this.expectedVersion = expectedVersion;
            return this;
        }

        public EventUpdate build() {
            return new EventUpdate(name, startsAt, endsAt, venue, host, principals, topics, expectedVersion);
        }
    }
}
