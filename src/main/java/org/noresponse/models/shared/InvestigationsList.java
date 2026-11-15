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
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * InvestigationsList
 *
 * <p>List investigation history.
 */
public class InvestigationsList {

    @JsonProperty("items")
    private List<Investigation> items;

    @JsonProperty("has_more")
    private boolean hasMore;

    @JsonInclude(Include.ALWAYS)
    @JsonProperty("next_cursor")
    private JsonNullable<String> nextCursor;

    @JsonCreator
    public InvestigationsList(
            @JsonProperty("items") @Nonnull List<Investigation> items,
            @JsonProperty("has_more") boolean hasMore,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.hasMore = hasMore;
        this.nextCursor = JsonNullable.of(nextCursor);
    }

    public InvestigationsList(@Nonnull List<Investigation> items, boolean hasMore) {
        this(items, hasMore, null);
    }

    public List<Investigation> items() {
        return this.items;
    }

    public boolean hasMore() {
        return this.hasMore;
    }

    public JsonNullable<String> nextCursor() {
        return this.nextCursor;
    }

    public static Builder builder() {
        return new Builder();
    }

    public InvestigationsList withItems(@Nonnull List<Investigation> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    public InvestigationsList withHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

    public InvestigationsList withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = JsonNullable.of(nextCursor);
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
        InvestigationsList other = (InvestigationsList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.hasMore, other.hasMore)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, hasMore, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(InvestigationsList.class, "items", items, "hasMore", hasMore, "nextCursor", nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Investigation> items;

        private boolean hasMore;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Investigation> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public Builder hasMore(boolean hasMore) {
            this.hasMore = hasMore;
            return this;
        }

        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public InvestigationsList build() {
            return new InvestigationsList(items, hasMore, nextCursor);
        }
    }
}
