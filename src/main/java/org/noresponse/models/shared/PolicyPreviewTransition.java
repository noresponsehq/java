package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class PolicyPreviewTransition {

    @JsonProperty("from")
    private From from;

    @JsonProperty("to")
    private From to;

    @JsonProperty("count")
    private long count;

    @JsonCreator
    public PolicyPreviewTransition(
            @JsonProperty("from") @Nonnull From from,
            @JsonProperty("to") @Nonnull From to,
            @JsonProperty("count") long count) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.count = count;
    }

    public From from() {
        return this.from;
    }

    public From to() {
        return this.to;
    }

    public long count() {
        return this.count;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyPreviewTransition withFrom(@Nonnull From from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public PolicyPreviewTransition withTo(@Nonnull From to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public PolicyPreviewTransition withCount(long count) {
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
        PolicyPreviewTransition other = (PolicyPreviewTransition) o;
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
        return Utils.toString(PolicyPreviewTransition.class, "from", from, "to", to, "count", count);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private From from;

        private From to;

        private long count;

        private Builder() {
            // force use of static builder() method
        }

        public Builder from(@Nonnull From from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        public Builder to(@Nonnull From to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public PolicyPreviewTransition build() {
            return new PolicyPreviewTransition(from, to, count);
        }
    }
}
