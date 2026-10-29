package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ReviewResolutionList
 *
 * <p>Read immutable review resolution history.
 */
public class ReviewResolutionList {

    @JsonProperty("items")
    private List<Resolution> items;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_before_version")
    private Long nextBeforeVersion;

    @JsonCreator
    public ReviewResolutionList(
            @JsonProperty("items") @Nonnull List<Resolution> items,
            @JsonProperty("next_before_version") @Nullable Long nextBeforeVersion) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextBeforeVersion = nextBeforeVersion;
    }

    public ReviewResolutionList(@Nonnull List<Resolution> items) {
        this(items, null);
    }

    public List<Resolution> items() {
        return this.items;
    }

    public Optional<Long> nextBeforeVersion() {
        return Optional.ofNullable(this.nextBeforeVersion);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewResolutionList withItems(@Nonnull List<Resolution> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    public ReviewResolutionList withNextBeforeVersion(@Nullable Long nextBeforeVersion) {
        this.nextBeforeVersion = nextBeforeVersion;
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
        ReviewResolutionList other = (ReviewResolutionList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextBeforeVersion, other.nextBeforeVersion);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextBeforeVersion);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewResolutionList.class, "items", items, "nextBeforeVersion", nextBeforeVersion);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Resolution> items;

        private Long nextBeforeVersion;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Resolution> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public Builder nextBeforeVersion(@Nullable Long nextBeforeVersion) {
            this.nextBeforeVersion = nextBeforeVersion;
            return this;
        }

        public ReviewResolutionList build() {
            return new ReviewResolutionList(items, nextBeforeVersion);
        }
    }
}
