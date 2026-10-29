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
 * ListEntryList
 *
 * <p>Newest first. Without a query, returns every active entry for compatibility. With limit or cursor,
 * returns up to 100 entries and an authenticated workspace/environment-bound cursor.
 *
 * <p>Expired entries are omitted.
 */
public class ListEntryList {

    @JsonProperty("items")
    private List<ListEntry> items;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public ListEntryList(
            @JsonProperty("items") @Nonnull List<ListEntry> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
    }

    public ListEntryList(@Nonnull List<ListEntry> items) {
        this(items, null);
    }

    public List<ListEntry> items() {
        return this.items;
    }

    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListEntryList withItems(@Nonnull List<ListEntry> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    public ListEntryList withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
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
        ListEntryList other = (ListEntryList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(ListEntryList.class, "items", items, "nextCursor", nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<ListEntry> items;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<ListEntry> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public ListEntryList build() {
            return new ListEntryList(items, nextCursor);
        }
    }
}
