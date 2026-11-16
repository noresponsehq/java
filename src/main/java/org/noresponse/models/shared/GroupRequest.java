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
 * GroupRequest
 *
 * <p>Creates an ongoing group or an event with optional dates.
 */
public class GroupRequest {
    /**
     * The group's name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Optional event start; null for an ongoing group.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("starts_at")
    private JsonNullable<String> startsAt;

    /**
     * Optional event end; requires starts_at and must be at or after it. Null when no end is set.
     */
    @JsonInclude(Include.NON_ABSENT)
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
     * The people the group protects, at most 20.
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

    @JsonCreator
    public GroupRequest(
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("starts_at") @Nullable JsonNullable<String> startsAt,
            @JsonProperty("ends_at") @Nullable JsonNullable<String> endsAt,
            @JsonProperty("venue") @Nullable String venue,
            @JsonProperty("host") @Nullable String host,
            @JsonProperty("principals") @Nullable List<Principal> principals,
            @JsonProperty("topics") @Nullable List<String> topics) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.startsAt = Optional.ofNullable(startsAt).orElse(JsonNullable.undefined());
        this.endsAt = Optional.ofNullable(endsAt).orElse(JsonNullable.undefined());
        this.venue = venue;
        this.host = host;
        this.principals = principals;
        this.topics = topics;
    }

    public GroupRequest(@Nonnull String name) {
        this(name, null, null, null, null, null, null);
    }

    /**
     * The group's name.
     */
    public String name() {
        return this.name;
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
     * The people the group protects, at most 20.
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

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The group's name.
     */
    public GroupRequest withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * Optional event start; null for an ongoing group.
     */
    public GroupRequest withStartsAt(@Nullable String startsAt) {
        this.startsAt = JsonNullable.of(startsAt);
        return this;
    }

    /**
     * Optional event end; requires starts_at and must be at or after it. Null when no end is set.
     */
    public GroupRequest withEndsAt(@Nullable String endsAt) {
        this.endsAt = JsonNullable.of(endsAt);
        return this;
    }

    /**
     * Where the group is held.
     */
    public GroupRequest withVenue(@Nullable String venue) {
        this.venue = venue;
        return this;
    }

    /**
     * The organisation hosting the group.
     */
    public GroupRequest withHost(@Nullable String host) {
        this.host = host;
        return this;
    }

    /**
     * The people the group protects, at most 20.
     */
    public GroupRequest withPrincipals(@Nullable List<Principal> principals) {
        this.principals = principals;
        return this;
    }

    /**
     * Sensitive topics the screening reads posts against, at most 50.
     */
    public GroupRequest withTopics(@Nullable List<String> topics) {
        this.topics = topics;
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
        GroupRequest other = (GroupRequest) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.startsAt, other.startsAt)
                && Utils.enhancedDeepEquals(this.endsAt, other.endsAt)
                && Utils.enhancedDeepEquals(this.venue, other.venue)
                && Utils.enhancedDeepEquals(this.host, other.host)
                && Utils.enhancedDeepEquals(this.principals, other.principals)
                && Utils.enhancedDeepEquals(this.topics, other.topics);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, startsAt, endsAt, venue, host, principals, topics);
    }

    @Override
    public String toString() {
        return Utils.toString(
                GroupRequest.class,
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
                topics);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private JsonNullable<String> startsAt;

        private JsonNullable<String> endsAt;

        private String venue;

        private String host;

        private List<Principal> principals;

        private List<String> topics;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The group's name.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * Optional event start; null for an ongoing group.
         */
        public Builder startsAt(@Nullable String startsAt) {
            this.startsAt = JsonNullable.of(startsAt);
            return this;
        }

        /**
         * Optional event end; requires starts_at and must be at or after it. Null when no end is set.
         */
        public Builder endsAt(@Nullable String endsAt) {
            this.endsAt = JsonNullable.of(endsAt);
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
         * The people the group protects, at most 20.
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

        public GroupRequest build() {
            return new GroupRequest(name, startsAt, endsAt, venue, host, principals, topics);
        }
    }
}
