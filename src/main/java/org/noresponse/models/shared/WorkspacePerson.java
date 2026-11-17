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

public class WorkspacePerson {
    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_id")
    private String externalId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private String name;

    /**
     * The person's email.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    /**
     * The person's phone in E.164 form.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private String phone;

    /**
     * Where the person works.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("company")
    private String company;

    /**
     * Where the person lives.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("city")
    private String city;

    /**
     * A username the person uses, with no platform. A search looks it up on every platform that finds
     * accounts by username.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("username")
    private String username;

    /**
     * Accounts the person is known to hold, at most 20.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("handles")
    private List<Handle> handles;

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * The time the workspace retention policy removes this person, their evidence and investigation
     * history. Shortening workspace retention can move this time earlier.
     */
    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonCreator
    public WorkspacePerson(
            @JsonProperty("external_id") @Nullable String externalId,
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("company") @Nullable String company,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("username") @Nullable String username,
            @JsonProperty("handles") @Nullable List<Handle> handles,
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt) {
        this.externalId = externalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.city = city;
        this.username = username;
        this.handles = handles;
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
    }

    public WorkspacePerson(
            @Nonnull String id, @Nonnull String createdAt, @Nonnull String updatedAt, @Nonnull String expiresAt) {
        this(null, null, null, null, null, null, null, null, id, createdAt, updatedAt, expiresAt);
    }

    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    public Optional<String> externalId() {
        return Optional.ofNullable(this.externalId);
    }

    public Optional<String> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * The person's email.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * The person's phone in E.164 form.
     */
    public Optional<String> phone() {
        return Optional.ofNullable(this.phone);
    }

    /**
     * Where the person works.
     */
    public Optional<String> company() {
        return Optional.ofNullable(this.company);
    }

    /**
     * Where the person lives.
     */
    public Optional<String> city() {
        return Optional.ofNullable(this.city);
    }

    /**
     * A username the person uses, with no platform. A search looks it up on every platform that finds
     * accounts by username.
     */
    public Optional<String> username() {
        return Optional.ofNullable(this.username);
    }

    /**
     * Accounts the person is known to hold, at most 20.
     */
    public Optional<List<Handle>> handles() {
        return Optional.ofNullable(this.handles);
    }

    public String id() {
        return this.id;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    /**
     * The time the workspace retention policy removes this person, their evidence and investigation
     * history. Shortening workspace retention can move this time earlier.
     */
    public String expiresAt() {
        return this.expiresAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    public WorkspacePerson withExternalId(@Nullable String externalId) {
        this.externalId = externalId;
        return this;
    }

    public WorkspacePerson withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The person's email.
     */
    public WorkspacePerson withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The person's phone in E.164 form.
     */
    public WorkspacePerson withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Where the person works.
     */
    public WorkspacePerson withCompany(@Nullable String company) {
        this.company = company;
        return this;
    }

    /**
     * Where the person lives.
     */
    public WorkspacePerson withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    /**
     * A username the person uses, with no platform. A search looks it up on every platform that finds
     * accounts by username.
     */
    public WorkspacePerson withUsername(@Nullable String username) {
        this.username = username;
        return this;
    }

    /**
     * Accounts the person is known to hold, at most 20.
     */
    public WorkspacePerson withHandles(@Nullable List<Handle> handles) {
        this.handles = handles;
        return this;
    }

    public WorkspacePerson withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public WorkspacePerson withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public WorkspacePerson withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
        return this;
    }

    /**
     * The time the workspace retention policy removes this person, their evidence and investigation
     * history. Shortening workspace retention can move this time earlier.
     */
    public WorkspacePerson withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
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
        WorkspacePerson other = (WorkspacePerson) o;
        return Utils.enhancedDeepEquals(this.externalId, other.externalId)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.company, other.company)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.username, other.username)
                && Utils.enhancedDeepEquals(this.handles, other.handles)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                externalId, name, email, phone, company, city, username, handles, id, createdAt, updatedAt, expiresAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WorkspacePerson.class,
                "externalId",
                externalId,
                "name",
                name,
                "email",
                email,
                "phone",
                phone,
                "company",
                company,
                "city",
                city,
                "username",
                username,
                "handles",
                handles,
                "id",
                id,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt,
                "expiresAt",
                expiresAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String externalId;

        private String name;

        private String email;

        private String phone;

        private String company;

        private String city;

        private String username;

        private List<Handle> handles;

        private String id;

        private String createdAt;

        private String updatedAt;

        private String expiresAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your reference for this person within the workspace environment. Only an explicit reference or
         * person_id can reuse an existing person.
         */
        public Builder externalId(@Nullable String externalId) {
            this.externalId = externalId;
            return this;
        }

        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        /**
         * The person's email.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * The person's phone in E.164 form.
         */
        public Builder phone(@Nullable String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Where the person works.
         */
        public Builder company(@Nullable String company) {
            this.company = company;
            return this;
        }

        /**
         * Where the person lives.
         */
        public Builder city(@Nullable String city) {
            this.city = city;
            return this;
        }

        /**
         * A username the person uses, with no platform. A search looks it up on every platform that finds
         * accounts by username.
         */
        public Builder username(@Nullable String username) {
            this.username = username;
            return this;
        }

        /**
         * Accounts the person is known to hold, at most 20.
         */
        public Builder handles(@Nullable List<Handle> handles) {
            this.handles = handles;
            return this;
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
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

        /**
         * The time the workspace retention policy removes this person, their evidence and investigation
         * history. Shortening workspace retention can move this time earlier.
         */
        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        public WorkspacePerson build() {
            return new WorkspacePerson(
                    externalId,
                    name,
                    email,
                    phone,
                    company,
                    city,
                    username,
                    handles,
                    id,
                    createdAt,
                    updatedAt,
                    expiresAt);
        }
    }
}
