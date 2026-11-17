package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class WorkspacePersonRequest {
    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_id")
    private String externalId;

    /**
     * The person's full name.
     */
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

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("handles")
    private List<Handle> handles;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("person_id")
    private String personId;

    @JsonCreator
    public WorkspacePersonRequest(
            @JsonProperty("external_id") @Nullable String externalId,
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("company") @Nullable String company,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("username") @Nullable String username,
            @JsonProperty("handles") @Nullable List<Handle> handles,
            @JsonProperty("person_id") @Nullable String personId) {
        this.externalId = externalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.city = city;
        this.username = username;
        this.handles = handles;
        this.personId = personId;
    }

    public WorkspacePersonRequest() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    public Optional<String> externalId() {
        return Optional.ofNullable(this.externalId);
    }

    /**
     * The person's full name.
     */
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

    public Optional<List<Handle>> handles() {
        return Optional.ofNullable(this.handles);
    }

    public Optional<String> personId() {
        return Optional.ofNullable(this.personId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    public WorkspacePersonRequest withExternalId(@Nullable String externalId) {
        this.externalId = externalId;
        return this;
    }

    /**
     * The person's full name.
     */
    public WorkspacePersonRequest withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The person's email.
     */
    public WorkspacePersonRequest withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The person's phone in E.164 form.
     */
    public WorkspacePersonRequest withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Where the person works.
     */
    public WorkspacePersonRequest withCompany(@Nullable String company) {
        this.company = company;
        return this;
    }

    /**
     * Where the person lives.
     */
    public WorkspacePersonRequest withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    /**
     * A username the person uses, with no platform. A search looks it up on every platform that finds
     * accounts by username.
     */
    public WorkspacePersonRequest withUsername(@Nullable String username) {
        this.username = username;
        return this;
    }

    public WorkspacePersonRequest withHandles(@Nullable List<Handle> handles) {
        this.handles = handles;
        return this;
    }

    public WorkspacePersonRequest withPersonId(@Nullable String personId) {
        this.personId = personId;
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
        WorkspacePersonRequest other = (WorkspacePersonRequest) o;
        return Utils.enhancedDeepEquals(this.externalId, other.externalId)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.company, other.company)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.username, other.username)
                && Utils.enhancedDeepEquals(this.handles, other.handles)
                && Utils.enhancedDeepEquals(this.personId, other.personId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(externalId, name, email, phone, company, city, username, handles, personId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WorkspacePersonRequest.class,
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
                "personId",
                personId);
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

        private String personId;

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

        /**
         * The person's full name.
         */
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

        public Builder handles(@Nullable List<Handle> handles) {
            this.handles = handles;
            return this;
        }

        public Builder personId(@Nullable String personId) {
            this.personId = personId;
            return this;
        }

        public WorkspacePersonRequest build() {
            return new WorkspacePersonRequest(
                    externalId, name, email, phone, company, city, username, handles, personId);
        }
    }
}
