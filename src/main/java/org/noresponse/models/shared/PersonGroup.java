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
 * PersonGroup
 *
 * <p>One group the person belongs to and their role in it.
 */
public class PersonGroup {

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("group_name")
    private String groupName;

    /**
     * The group's optional event start; null for an ongoing group.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("group_starts_at")
    private JsonNullable<String> groupStartsAt;

    /**
     * The person's role in the group: guest, crew, vendor or press, or null when it was not given.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("role")
    private JsonNullable<PersonRole> role;

    /**
     * When the person was added to the group.
     */
    @JsonProperty("added_at")
    private String addedAt;

    @JsonCreator
    public PersonGroup(
            @JsonProperty("group_id") @Nonnull String groupId,
            @JsonProperty("group_name") @Nonnull String groupName,
            @JsonProperty("group_starts_at") @Nullable String groupStartsAt,
            @JsonProperty("role") @Nullable PersonRole role,
            @JsonProperty("added_at") @Nonnull String addedAt) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.groupName = Optional.ofNullable(groupName)
                .orElseThrow(() -> new IllegalArgumentException("groupName cannot be null"));
        this.groupStartsAt = JsonNullable.of(groupStartsAt);
        this.role = JsonNullable.of(role);
        this.addedAt =
                Optional.ofNullable(addedAt).orElseThrow(() -> new IllegalArgumentException("addedAt cannot be null"));
    }

    public PersonGroup(@Nonnull String groupId, @Nonnull String groupName, @Nonnull String addedAt) {
        this(groupId, groupName, null, null, addedAt);
    }

    public String groupId() {
        return this.groupId;
    }

    public String groupName() {
        return this.groupName;
    }

    /**
     * The group's optional event start; null for an ongoing group.
     */
    public JsonNullable<String> groupStartsAt() {
        return this.groupStartsAt;
    }

    /**
     * The person's role in the group: guest, crew, vendor or press, or null when it was not given.
     */
    public JsonNullable<PersonRole> role() {
        return this.role;
    }

    /**
     * When the person was added to the group.
     */
    public String addedAt() {
        return this.addedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PersonGroup withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public PersonGroup withGroupName(@Nonnull String groupName) {
        this.groupName = Utils.checkNotNull(groupName, "groupName");
        return this;
    }

    /**
     * The group's optional event start; null for an ongoing group.
     */
    public PersonGroup withGroupStartsAt(@Nullable String groupStartsAt) {
        this.groupStartsAt = JsonNullable.of(groupStartsAt);
        return this;
    }

    /**
     * The person's role in the group: guest, crew, vendor or press, or null when it was not given.
     */
    public PersonGroup withRole(@Nullable PersonRole role) {
        this.role = JsonNullable.of(role);
        return this;
    }

    /**
     * When the person was added to the group.
     */
    public PersonGroup withAddedAt(@Nonnull String addedAt) {
        this.addedAt = Utils.checkNotNull(addedAt, "addedAt");
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
        PersonGroup other = (PersonGroup) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.groupName, other.groupName)
                && Utils.enhancedDeepEquals(this.groupStartsAt, other.groupStartsAt)
                && Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.addedAt, other.addedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, groupName, groupStartsAt, role, addedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonGroup.class,
                "groupId",
                groupId,
                "groupName",
                groupName,
                "groupStartsAt",
                groupStartsAt,
                "role",
                role,
                "addedAt",
                addedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private String groupName;

        private String groupStartsAt;

        private PersonRole role;

        private String addedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        public Builder groupName(@Nonnull String groupName) {
            this.groupName = Utils.checkNotNull(groupName, "groupName");
            return this;
        }

        /**
         * The group's optional event start; null for an ongoing group.
         */
        public Builder groupStartsAt(@Nullable String groupStartsAt) {
            this.groupStartsAt = groupStartsAt;
            return this;
        }

        /**
         * The person's role in the group: guest, crew, vendor or press, or null when it was not given.
         */
        public Builder role(@Nullable PersonRole role) {
            this.role = role;
            return this;
        }

        /**
         * When the person was added to the group.
         */
        public Builder addedAt(@Nonnull String addedAt) {
            this.addedAt = Utils.checkNotNull(addedAt, "addedAt");
            return this;
        }

        public PersonGroup build() {
            return new PersonGroup(groupId, groupName, groupStartsAt, role, addedAt);
        }
    }
}
