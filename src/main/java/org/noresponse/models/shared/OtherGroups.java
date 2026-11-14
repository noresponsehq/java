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
 * OtherGroups
 *
 * <p>Where else in the environment the person appears, read live from those groups: deleting one removes
 * it here.
 */
public class OtherGroups {
    /**
     * How many people of other groups match, of which items lists the 20 most recent.
     */
    @JsonProperty("total")
    private long total;

    /**
     * The 20 people of the most recent other groups, newest group first.
     */
    @JsonProperty("items")
    private List<OtherGroup> items;

    @JsonCreator
    public OtherGroups(@JsonProperty("total") long total, @JsonProperty("items") @Nonnull List<OtherGroup> items) {
        this.total = total;
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    /**
     * How many people of other groups match, of which items lists the 20 most recent.
     */
    public long total() {
        return this.total;
    }

    /**
     * The 20 people of the most recent other groups, newest group first.
     */
    public List<OtherGroup> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How many people of other groups match, of which items lists the 20 most recent.
     */
    public OtherGroups withTotal(long total) {
        this.total = total;
        return this;
    }

    /**
     * The 20 people of the most recent other groups, newest group first.
     */
    public OtherGroups withItems(@Nonnull List<OtherGroup> items) {
        this.items = Utils.checkNotNull(items, "items");
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
        OtherGroups other = (OtherGroups) o;
        return Utils.enhancedDeepEquals(this.total, other.total) && Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(total, items);
    }

    @Override
    public String toString() {
        return Utils.toString(OtherGroups.class, "total", total, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long total;

        private List<OtherGroup> items;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How many people of other groups match, of which items lists the 20 most recent.
         */
        public Builder total(long total) {
            this.total = total;
            return this;
        }

        /**
         * The 20 people of the most recent other groups, newest group first.
         */
        public Builder items(@Nonnull List<OtherGroup> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public OtherGroups build() {
            return new OtherGroups(total, items);
        }
    }
}
