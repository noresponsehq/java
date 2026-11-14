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
 * PersonList
 *
 * <p>People in upload order.
 */
public class PersonList {

    @JsonProperty("items")
    private List<Person> items;

    /**
     * Pass as after to read the next page.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_after")
    private String nextAfter;

    @JsonCreator
    public PersonList(
            @JsonProperty("items") @Nonnull List<Person> items,
            @JsonProperty("next_after") @Nullable String nextAfter) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextAfter = nextAfter;
    }

    public PersonList(@Nonnull List<Person> items) {
        this(items, null);
    }

    public List<Person> items() {
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

    public PersonList withItems(@Nonnull List<Person> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Pass as after to read the next page.
     */
    public PersonList withNextAfter(@Nullable String nextAfter) {
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
        PersonList other = (PersonList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextAfter, other.nextAfter);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextAfter);
    }

    @Override
    public String toString() {
        return Utils.toString(PersonList.class, "items", items, "nextAfter", nextAfter);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Person> items;

        private String nextAfter;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Person> items) {
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

        public PersonList build() {
            return new PersonList(items, nextAfter);
        }
    }
}
