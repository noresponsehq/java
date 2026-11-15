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

public class PersonBatchItem {
    /**
     * The row's position in the request.
     */
    @JsonProperty("index")
    private long index;

    @JsonProperty("id")
    private String id;

    /**
     * unchanged: the row matched a person with the same details.
     */
    @JsonProperty("outcome")
    private PersonBatchItemOutcome outcome;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("suggested_matches")
    private List<PersonMatch> suggestedMatches;

    @JsonCreator
    public PersonBatchItem(
            @JsonProperty("index") long index,
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("outcome") @Nonnull PersonBatchItemOutcome outcome,
            @JsonProperty("suggested_matches") @Nullable List<PersonMatch> suggestedMatches) {
        this.index = index;
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.suggestedMatches = suggestedMatches;
    }

    public PersonBatchItem(long index, @Nonnull String id, @Nonnull PersonBatchItemOutcome outcome) {
        this(index, id, outcome, null);
    }

    /**
     * The row's position in the request.
     */
    public long index() {
        return this.index;
    }

    public String id() {
        return this.id;
    }

    /**
     * unchanged: the row matched a person with the same details.
     */
    public PersonBatchItemOutcome outcome() {
        return this.outcome;
    }

    public Optional<List<PersonMatch>> suggestedMatches() {
        return Optional.ofNullable(this.suggestedMatches);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The row's position in the request.
     */
    public PersonBatchItem withIndex(long index) {
        this.index = index;
        return this;
    }

    public PersonBatchItem withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * unchanged: the row matched a person with the same details.
     */
    public PersonBatchItem withOutcome(@Nonnull PersonBatchItemOutcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    public PersonBatchItem withSuggestedMatches(@Nullable List<PersonMatch> suggestedMatches) {
        this.suggestedMatches = suggestedMatches;
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
        PersonBatchItem other = (PersonBatchItem) o;
        return Utils.enhancedDeepEquals(this.index, other.index)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.suggestedMatches, other.suggestedMatches);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(index, id, outcome, suggestedMatches);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonBatchItem.class,
                "index",
                index,
                "id",
                id,
                "outcome",
                outcome,
                "suggestedMatches",
                suggestedMatches);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long index;

        private String id;

        private PersonBatchItemOutcome outcome;

        private List<PersonMatch> suggestedMatches;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The row's position in the request.
         */
        public Builder index(long index) {
            this.index = index;
            return this;
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * unchanged: the row matched a person with the same details.
         */
        public Builder outcome(@Nonnull PersonBatchItemOutcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        public Builder suggestedMatches(@Nullable List<PersonMatch> suggestedMatches) {
            this.suggestedMatches = suggestedMatches;
            return this;
        }

        public PersonBatchItem build() {
            return new PersonBatchItem(index, id, outcome, suggestedMatches);
        }
    }
}
