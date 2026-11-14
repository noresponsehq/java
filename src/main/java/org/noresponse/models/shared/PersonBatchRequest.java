package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PersonBatchRequest
 *
 * <p>Adds or updates 1 to 500 people. A row naming an existing person of the group (the same external_id,
 * else the same email, else the same phone) updates it.
 */
public class PersonBatchRequest {

    @JsonProperty("people")
    private List<PersonIdentity> people;

    @JsonCreator
    public PersonBatchRequest(@JsonProperty("people") @Nonnull List<PersonIdentity> people) {
        this.people =
                Optional.ofNullable(people).orElseThrow(() -> new IllegalArgumentException("people cannot be null"));
    }

    public List<PersonIdentity> people() {
        return this.people;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PersonBatchRequest withPeople(@Nonnull List<PersonIdentity> people) {
        this.people = Utils.checkNotNull(people, "people");
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
        PersonBatchRequest other = (PersonBatchRequest) o;
        return Utils.enhancedDeepEquals(this.people, other.people);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(people);
    }

    @Override
    public String toString() {
        return Utils.toString(PersonBatchRequest.class, "people", people);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<PersonIdentity> people;

        private Builder() {
            // force use of static builder() method
        }

        public Builder people(@Nonnull List<PersonIdentity> people) {
            this.people = Utils.checkNotNull(people, "people");
            return this;
        }

        public PersonBatchRequest build() {
            return new PersonBatchRequest(people);
        }
    }
}
