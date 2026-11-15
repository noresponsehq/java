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

public class PersonIdentity {
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

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("handles")
    private List<Handle> handles;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("person_id")
    private String personId;

    /**
     * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: a person without one has no role, not guest. A later row naming the same person
     * replaces it, and a change of role alone keeps the person's screening results.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("role")
    private PersonRole role;

    @JsonCreator
    public PersonIdentity(
            @JsonProperty("external_id") @Nullable String externalId,
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("company") @Nullable String company,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("handles") @Nullable List<Handle> handles,
            @JsonProperty("person_id") @Nullable String personId,
            @JsonProperty("role") @Nullable PersonRole role) {
        this.externalId = externalId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.city = city;
        this.handles = handles;
        this.personId = personId;
        this.role = role;
    }

    public PersonIdentity() {
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

    public Optional<List<Handle>> handles() {
        return Optional.ofNullable(this.handles);
    }

    public Optional<String> personId() {
        return Optional.ofNullable(this.personId);
    }

    /**
     * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: a person without one has no role, not guest. A later row naming the same person
     * replaces it, and a change of role alone keeps the person's screening results.
     */
    public Optional<PersonRole> role() {
        return Optional.ofNullable(this.role);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your reference for this person within the workspace environment. Only an explicit reference or
     * person_id can reuse an existing person.
     */
    public PersonIdentity withExternalId(@Nullable String externalId) {
        this.externalId = externalId;
        return this;
    }

    /**
     * The person's full name.
     */
    public PersonIdentity withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The person's email.
     */
    public PersonIdentity withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The person's phone in E.164 form.
     */
    public PersonIdentity withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Where the person works.
     */
    public PersonIdentity withCompany(@Nullable String company) {
        this.company = company;
        return this;
    }

    /**
     * Where the person lives.
     */
    public PersonIdentity withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    public PersonIdentity withHandles(@Nullable List<Handle> handles) {
        this.handles = handles;
        return this;
    }

    public PersonIdentity withPersonId(@Nullable String personId) {
        this.personId = personId;
        return this;
    }

    /**
     * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: a person without one has no role, not guest. A later row naming the same person
     * replaces it, and a change of role alone keeps the person's screening results.
     */
    public PersonIdentity withRole(@Nullable PersonRole role) {
        this.role = role;
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
        PersonIdentity other = (PersonIdentity) o;
        return Utils.enhancedDeepEquals(this.externalId, other.externalId)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.company, other.company)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.handles, other.handles)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.role, other.role);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(externalId, name, email, phone, company, city, handles, personId, role);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonIdentity.class,
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
                "handles",
                handles,
                "personId",
                personId,
                "role",
                role);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String externalId;

        private String name;

        private String email;

        private String phone;

        private String company;

        private String city;

        private List<Handle> handles;

        private String personId;

        private PersonRole role;

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

        public Builder handles(@Nullable List<Handle> handles) {
            this.handles = handles;
            return this;
        }

        public Builder personId(@Nullable String personId) {
            this.personId = personId;
            return this;
        }

        /**
         * The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
         * you do not know it: a person without one has no role, not guest. A later row naming the same person
         * replaces it, and a change of role alone keeps the person's screening results.
         */
        public Builder role(@Nullable PersonRole role) {
            this.role = role;
            return this;
        }

        public PersonIdentity build() {
            return new PersonIdentity(externalId, name, email, phone, company, city, handles, personId, role);
        }
    }
}
