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
 * ListEntryList
 *
 * <p>GET /v1/workspaces/{workspace_id}/lists/{environment}: every entry of the environment's list, newest
 * first. An environment holds at most 10,000 entries.
 */
public class ListEntryList {

    @JsonProperty("items")
    private List<ListEntry> items;

    @JsonCreator
    public ListEntryList(@JsonProperty("items") @Nonnull List<ListEntry> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<ListEntry> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListEntryList withItems(@Nonnull List<ListEntry> items) {
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
        ListEntryList other = (ListEntryList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(ListEntryList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<ListEntry> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<ListEntry> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public ListEntryList build() {
            return new ListEntryList(items);
        }
    }
}
