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
 * GroupList
 *
 * <p>Groups, newest first.
 */
public class GroupList {

    @JsonProperty("items")
    private List<Group> items;

    /**
     * Pass as before to read the next, older page.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_before")
    private String nextBefore;

    @JsonCreator
    public GroupList(
            @JsonProperty("items") @Nonnull List<Group> items,
            @JsonProperty("next_before") @Nullable String nextBefore) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextBefore = nextBefore;
    }

    public GroupList(@Nonnull List<Group> items) {
        this(items, null);
    }

    public List<Group> items() {
        return this.items;
    }

    /**
     * Pass as before to read the next, older page.
     */
    public Optional<String> nextBefore() {
        return Optional.ofNullable(this.nextBefore);
    }

    public static Builder builder() {
        return new Builder();
    }

    public GroupList withItems(@Nonnull List<Group> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Pass as before to read the next, older page.
     */
    public GroupList withNextBefore(@Nullable String nextBefore) {
        this.nextBefore = nextBefore;
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
        GroupList other = (GroupList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextBefore, other.nextBefore);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextBefore);
    }

    @Override
    public String toString() {
        return Utils.toString(GroupList.class, "items", items, "nextBefore", nextBefore);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Group> items;

        private String nextBefore;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Group> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Pass as before to read the next, older page.
         */
        public Builder nextBefore(@Nullable String nextBefore) {
            this.nextBefore = nextBefore;
            return this;
        }

        public GroupList build() {
            return new GroupList(items, nextBefore);
        }
    }
}
