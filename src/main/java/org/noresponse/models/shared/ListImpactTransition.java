package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ListImpactTransition {

    @JsonProperty("from")
    private ListImpactTransitionFrom from;

    @JsonProperty("to")
    private ListImpactTransitionFrom to;

    @JsonProperty("count")
    private long count;

    @JsonCreator
    public ListImpactTransition(
            @JsonProperty("from") @Nonnull ListImpactTransitionFrom from,
            @JsonProperty("to") @Nonnull ListImpactTransitionFrom to,
            @JsonProperty("count") long count) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.count = count;
    }

    public ListImpactTransitionFrom from() {
        return this.from;
    }

    public ListImpactTransitionFrom to() {
        return this.to;
    }

    public long count() {
        return this.count;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListImpactTransition withFrom(@Nonnull ListImpactTransitionFrom from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public ListImpactTransition withTo(@Nonnull ListImpactTransitionFrom to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public ListImpactTransition withCount(long count) {
        this.count = count;
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
        ListImpactTransition other = (ListImpactTransition) o;
        return Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.count, other.count);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(from, to, count);
    }

    @Override
    public String toString() {
        return Utils.toString(ListImpactTransition.class, "from", from, "to", to, "count", count);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ListImpactTransitionFrom from;

        private ListImpactTransitionFrom to;

        private long count;

        private Builder() {
            // force use of static builder() method
        }

        public Builder from(@Nonnull ListImpactTransitionFrom from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        public Builder to(@Nonnull ListImpactTransitionFrom to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public ListImpactTransition build() {
            return new ListImpactTransition(from, to, count);
        }
    }
}
