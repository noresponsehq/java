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
 * AttendeeIdentity
 *
 * <p>What you know about an attendee. Stored encrypted under the workspace's key.
 */
public class AttendeeIdentity {
    /**
     * Your own id for the attendee, unique within the event. A later row with the same external id updates
     * this attendee.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_id")
    private String externalId;

    /**
     * The attendee's full name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The attendee's email.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    /**
     * The attendee's phone in E.164 form.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private String phone;

    /**
     * Where the attendee works.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("company")
    private String company;

    /**
     * Where the attendee lives.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("city")
    private String city;

    /**
     * Accounts the attendee is known to hold, at most 20.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("handles")
    private List<Handle> handles;

    /**
     * The attendee's role at the event: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: an attendee without one has no role, not guest. A later row naming the same
     * attendee replaces it, and a change of role alone keeps the attendee's screening results.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("role")
    private AttendeeRole role;

    @JsonCreator
    public AttendeeIdentity(
            @JsonProperty("external_id") @Nullable String externalId,
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("company") @Nullable String company,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("handles") @Nullable List<Handle> handles,
            @JsonProperty("role") @Nullable AttendeeRole role) {
        this.externalId = externalId;
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.city = city;
        this.handles = handles;
        this.role = role;
    }

    public AttendeeIdentity(@Nonnull String name) {
        this(null, name, null, null, null, null, null, null);
    }

    /**
     * Your own id for the attendee, unique within the event. A later row with the same external id updates
     * this attendee.
     */
    public Optional<String> externalId() {
        return Optional.ofNullable(this.externalId);
    }

    /**
     * The attendee's full name.
     */
    public String name() {
        return this.name;
    }

    /**
     * The attendee's email.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * The attendee's phone in E.164 form.
     */
    public Optional<String> phone() {
        return Optional.ofNullable(this.phone);
    }

    /**
     * Where the attendee works.
     */
    public Optional<String> company() {
        return Optional.ofNullable(this.company);
    }

    /**
     * Where the attendee lives.
     */
    public Optional<String> city() {
        return Optional.ofNullable(this.city);
    }

    /**
     * Accounts the attendee is known to hold, at most 20.
     */
    public Optional<List<Handle>> handles() {
        return Optional.ofNullable(this.handles);
    }

    /**
     * The attendee's role at the event: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: an attendee without one has no role, not guest. A later row naming the same
     * attendee replaces it, and a change of role alone keeps the attendee's screening results.
     */
    public Optional<AttendeeRole> role() {
        return Optional.ofNullable(this.role);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your own id for the attendee, unique within the event. A later row with the same external id updates
     * this attendee.
     */
    public AttendeeIdentity withExternalId(@Nullable String externalId) {
        this.externalId = externalId;
        return this;
    }

    /**
     * The attendee's full name.
     */
    public AttendeeIdentity withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * The attendee's email.
     */
    public AttendeeIdentity withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The attendee's phone in E.164 form.
     */
    public AttendeeIdentity withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Where the attendee works.
     */
    public AttendeeIdentity withCompany(@Nullable String company) {
        this.company = company;
        return this;
    }

    /**
     * Where the attendee lives.
     */
    public AttendeeIdentity withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    /**
     * Accounts the attendee is known to hold, at most 20.
     */
    public AttendeeIdentity withHandles(@Nullable List<Handle> handles) {
        this.handles = handles;
        return this;
    }

    /**
     * The attendee's role at the event: guest (a ticket holder), crew, vendor or press. Leave it out when
     * you do not know it: an attendee without one has no role, not guest. A later row naming the same
     * attendee replaces it, and a change of role alone keeps the attendee's screening results.
     */
    public AttendeeIdentity withRole(@Nullable AttendeeRole role) {
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
        AttendeeIdentity other = (AttendeeIdentity) o;
        return Utils.enhancedDeepEquals(this.externalId, other.externalId)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.company, other.company)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.handles, other.handles)
                && Utils.enhancedDeepEquals(this.role, other.role);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(externalId, name, email, phone, company, city, handles, role);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AttendeeIdentity.class,
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

        private AttendeeRole role;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your own id for the attendee, unique within the event. A later row with the same external id updates
         * this attendee.
         */
        public Builder externalId(@Nullable String externalId) {
            this.externalId = externalId;
            return this;
        }

        /**
         * The attendee's full name.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * The attendee's email.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * The attendee's phone in E.164 form.
         */
        public Builder phone(@Nullable String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Where the attendee works.
         */
        public Builder company(@Nullable String company) {
            this.company = company;
            return this;
        }

        /**
         * Where the attendee lives.
         */
        public Builder city(@Nullable String city) {
            this.city = city;
            return this;
        }

        /**
         * Accounts the attendee is known to hold, at most 20.
         */
        public Builder handles(@Nullable List<Handle> handles) {
            this.handles = handles;
            return this;
        }

        /**
         * The attendee's role at the event: guest (a ticket holder), crew, vendor or press. Leave it out when
         * you do not know it: an attendee without one has no role, not guest. A later row naming the same
         * attendee replaces it, and a change of role alone keeps the attendee's screening results.
         */
        public Builder role(@Nullable AttendeeRole role) {
            this.role = role;
            return this;
        }

        public AttendeeIdentity build() {
            return new AttendeeIdentity(externalId, name, email, phone, company, city, handles, role);
        }
    }
}
