package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class PersonMatch {

    @JsonProperty("person_id")
    private String personId;

    @JsonProperty("matched_on")
    private List<PersonMatchMatchedOn> matchedOn;

    @JsonCreator
    public PersonMatch(
            @JsonProperty("person_id") @Nonnull String personId,
            @JsonProperty("matched_on") @Nonnull List<PersonMatchMatchedOn> matchedOn) {
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.matchedOn = Optional.ofNullable(matchedOn)
                .orElseThrow(() -> new IllegalArgumentException("matchedOn cannot be null"));
    }

    public String personId() {
        return this.personId;
    }

    public List<PersonMatchMatchedOn> matchedOn() {
        return this.matchedOn;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PersonMatch withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public PersonMatch withMatchedOn(@Nonnull List<PersonMatchMatchedOn> matchedOn) {
        this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
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
        PersonMatch other = (PersonMatch) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.matchedOn, other.matchedOn);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId, matchedOn);
    }

    @Override
    public String toString() {
        return Utils.toString(PersonMatch.class, "personId", personId, "matchedOn", matchedOn);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private List<PersonMatchMatchedOn> matchedOn;

        private Builder() {
            // force use of static builder() method
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder matchedOn(@Nonnull List<PersonMatchMatchedOn> matchedOn) {
            this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
            return this;
        }

        public PersonMatch build() {
            return new PersonMatch(personId, matchedOn);
        }
    }
}
