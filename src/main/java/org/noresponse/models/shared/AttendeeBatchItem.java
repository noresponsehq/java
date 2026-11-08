package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AttendeeBatchItem {
    /**
     * The row's position in the request.
     */
    @JsonProperty("index")
    private long index;

    @JsonProperty("id")
    private String id;

    /**
     * unchanged: the row matched an attendee with the same details.
     */
    @JsonProperty("outcome")
    private AttendeeBatchItemOutcome outcome;

    @JsonCreator
    public AttendeeBatchItem(
            @JsonProperty("index") long index,
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("outcome") @Nonnull AttendeeBatchItemOutcome outcome) {
        this.index = index;
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
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
     * unchanged: the row matched an attendee with the same details.
     */
    public AttendeeBatchItemOutcome outcome() {
        return this.outcome;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The row's position in the request.
     */
    public AttendeeBatchItem withIndex(long index) {
        this.index = index;
        return this;
    }

    public AttendeeBatchItem withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * unchanged: the row matched an attendee with the same details.
     */
    public AttendeeBatchItem withOutcome(@Nonnull AttendeeBatchItemOutcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
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
        AttendeeBatchItem other = (AttendeeBatchItem) o;
        return Utils.enhancedDeepEquals(this.index, other.index)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(index, id, outcome);
    }

    @Override
    public String toString() {
        return Utils.toString(AttendeeBatchItem.class, "index", index, "id", id, "outcome", outcome);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long index;

        private String id;

        private AttendeeBatchItemOutcome outcome;

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
         * unchanged: the row matched an attendee with the same details.
         */
        public Builder outcome(@Nonnull AttendeeBatchItemOutcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        public AttendeeBatchItem build() {
            return new AttendeeBatchItem(index, id, outcome);
        }
    }
}
