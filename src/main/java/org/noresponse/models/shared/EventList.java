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
 * EventList
 *
 * <p>Events, newest first.
 */
public class EventList {

    @JsonProperty("items")
    private List<Event> items;

    /**
     * Pass as before to read the next, older page.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_before")
    private String nextBefore;

    @JsonCreator
    public EventList(
            @JsonProperty("items") @Nonnull List<Event> items,
            @JsonProperty("next_before") @Nullable String nextBefore) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextBefore = nextBefore;
    }

    public EventList(@Nonnull List<Event> items) {
        this(items, null);
    }

    public List<Event> items() {
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

    public EventList withItems(@Nonnull List<Event> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Pass as before to read the next, older page.
     */
    public EventList withNextBefore(@Nullable String nextBefore) {
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
        EventList other = (EventList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextBefore, other.nextBefore);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextBefore);
    }

    @Override
    public String toString() {
        return Utils.toString(EventList.class, "items", items, "nextBefore", nextBefore);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Event> items;

        private String nextBefore;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Event> items) {
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

        public EventList build() {
            return new EventList(items, nextBefore);
        }
    }
}
