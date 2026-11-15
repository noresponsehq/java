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
 * WorkspacePersonResult
 *
 * <p>Create or reuse a workspace person.
 */
public class WorkspacePersonResult {

    @JsonProperty("person")
    private WorkspacePerson person;

    @JsonProperty("suggested_matches")
    private List<PersonMatch> suggestedMatches;

    @JsonProperty("created")
    private boolean created;

    @JsonCreator
    public WorkspacePersonResult(
            @JsonProperty("person") @Nonnull WorkspacePerson person,
            @JsonProperty("suggested_matches") @Nonnull List<PersonMatch> suggestedMatches,
            @JsonProperty("created") boolean created) {
        this.person =
                Optional.ofNullable(person).orElseThrow(() -> new IllegalArgumentException("person cannot be null"));
        this.suggestedMatches = Optional.ofNullable(suggestedMatches)
                .orElseThrow(() -> new IllegalArgumentException("suggestedMatches cannot be null"));
        this.created = created;
    }

    public WorkspacePerson person() {
        return this.person;
    }

    public List<PersonMatch> suggestedMatches() {
        return this.suggestedMatches;
    }

    public boolean created() {
        return this.created;
    }

    public static Builder builder() {
        return new Builder();
    }

    public WorkspacePersonResult withPerson(@Nonnull WorkspacePerson person) {
        this.person = Utils.checkNotNull(person, "person");
        return this;
    }

    public WorkspacePersonResult withSuggestedMatches(@Nonnull List<PersonMatch> suggestedMatches) {
        this.suggestedMatches = Utils.checkNotNull(suggestedMatches, "suggestedMatches");
        return this;
    }

    public WorkspacePersonResult withCreated(boolean created) {
        this.created = created;
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
        WorkspacePersonResult other = (WorkspacePersonResult) o;
        return Utils.enhancedDeepEquals(this.person, other.person)
                && Utils.enhancedDeepEquals(this.suggestedMatches, other.suggestedMatches)
                && Utils.enhancedDeepEquals(this.created, other.created);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(person, suggestedMatches, created);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WorkspacePersonResult.class,
                "person",
                person,
                "suggestedMatches",
                suggestedMatches,
                "created",
                created);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private WorkspacePerson person;

        private List<PersonMatch> suggestedMatches;

        private boolean created;

        private Builder() {
            // force use of static builder() method
        }

        public Builder person(@Nonnull WorkspacePerson person) {
            this.person = Utils.checkNotNull(person, "person");
            return this;
        }

        public Builder suggestedMatches(@Nonnull List<PersonMatch> suggestedMatches) {
            this.suggestedMatches = Utils.checkNotNull(suggestedMatches, "suggestedMatches");
            return this;
        }

        public Builder created(boolean created) {
            this.created = created;
            return this;
        }

        public WorkspacePersonResult build() {
            return new WorkspacePersonResult(person, suggestedMatches, created);
        }
    }
}
