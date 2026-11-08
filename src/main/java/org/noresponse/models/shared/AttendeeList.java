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
 * AttendeeList
 *
 * <p>Attendees in upload order.
 */
public class AttendeeList {

    @JsonProperty("items")
    private List<Attendee> items;

    /**
     * Pass as after to read the next page.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_after")
    private String nextAfter;

    @JsonCreator
    public AttendeeList(
            @JsonProperty("items") @Nonnull List<Attendee> items,
            @JsonProperty("next_after") @Nullable String nextAfter) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextAfter = nextAfter;
    }

    public AttendeeList(@Nonnull List<Attendee> items) {
        this(items, null);
    }

    public List<Attendee> items() {
        return this.items;
    }

    /**
     * Pass as after to read the next page.
     */
    public Optional<String> nextAfter() {
        return Optional.ofNullable(this.nextAfter);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AttendeeList withItems(@Nonnull List<Attendee> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Pass as after to read the next page.
     */
    public AttendeeList withNextAfter(@Nullable String nextAfter) {
        this.nextAfter = nextAfter;
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
        AttendeeList other = (AttendeeList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextAfter, other.nextAfter);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextAfter);
    }

    @Override
    public String toString() {
        return Utils.toString(AttendeeList.class, "items", items, "nextAfter", nextAfter);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Attendee> items;

        private String nextAfter;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Attendee> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Pass as after to read the next page.
         */
        public Builder nextAfter(@Nullable String nextAfter) {
            this.nextAfter = nextAfter;
            return this;
        }

        public AttendeeList build() {
            return new AttendeeList(items, nextAfter);
        }
    }
}
