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
 * OtherEvents
 *
 * <p>Where else in the environment the attendee appears, read live from those events: deleting one
 * removes it here.
 */
public class OtherEvents {
    /**
     * How many attendees of other events match, of which items lists the 20 most recent.
     */
    @JsonProperty("total")
    private long total;

    /**
     * The 20 attendees of the most recent other events, newest event first.
     */
    @JsonProperty("items")
    private List<OtherEvent> items;

    @JsonCreator
    public OtherEvents(@JsonProperty("total") long total, @JsonProperty("items") @Nonnull List<OtherEvent> items) {
        this.total = total;
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    /**
     * How many attendees of other events match, of which items lists the 20 most recent.
     */
    public long total() {
        return this.total;
    }

    /**
     * The 20 attendees of the most recent other events, newest event first.
     */
    public List<OtherEvent> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How many attendees of other events match, of which items lists the 20 most recent.
     */
    public OtherEvents withTotal(long total) {
        this.total = total;
        return this;
    }

    /**
     * The 20 attendees of the most recent other events, newest event first.
     */
    public OtherEvents withItems(@Nonnull List<OtherEvent> items) {
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
        OtherEvents other = (OtherEvents) o;
        return Utils.enhancedDeepEquals(this.total, other.total) && Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(total, items);
    }

    @Override
    public String toString() {
        return Utils.toString(OtherEvents.class, "total", total, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long total;

        private List<OtherEvent> items;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How many attendees of other events match, of which items lists the 20 most recent.
         */
        public Builder total(long total) {
            this.total = total;
            return this;
        }

        /**
         * The 20 attendees of the most recent other events, newest event first.
         */
        public Builder items(@Nonnull List<OtherEvent> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public OtherEvents build() {
            return new OtherEvents(total, items);
        }
    }
}
