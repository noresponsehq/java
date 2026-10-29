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
 * ReviewViewList
 *
 * <p>List your private and shared review filters.
 */
public class ReviewViewList {

    @JsonProperty("items")
    private List<ReviewView> items;

    @JsonCreator
    public ReviewViewList(@JsonProperty("items") @Nonnull List<ReviewView> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<ReviewView> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewViewList withItems(@Nonnull List<ReviewView> items) {
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
        ReviewViewList other = (ReviewViewList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewViewList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<ReviewView> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<ReviewView> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public ReviewViewList build() {
            return new ReviewViewList(items);
        }
    }
}
