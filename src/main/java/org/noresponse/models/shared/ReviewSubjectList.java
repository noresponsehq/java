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
 * ReviewSubjectList
 *
 * <p>Retained tenant-local subject links and the current review version.
 */
public class ReviewSubjectList {

    @JsonProperty("review_version")
    private long reviewVersion;

    @JsonProperty("items")
    private List<ReviewSubject> items;

    @JsonCreator
    public ReviewSubjectList(
            @JsonProperty("review_version") long reviewVersion,
            @JsonProperty("items") @Nonnull List<ReviewSubject> items) {
        this.reviewVersion = reviewVersion;
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public long reviewVersion() {
        return this.reviewVersion;
    }

    public List<ReviewSubject> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewSubjectList withReviewVersion(long reviewVersion) {
        this.reviewVersion = reviewVersion;
        return this;
    }

    public ReviewSubjectList withItems(@Nonnull List<ReviewSubject> items) {
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
        ReviewSubjectList other = (ReviewSubjectList) o;
        return Utils.enhancedDeepEquals(this.reviewVersion, other.reviewVersion)
                && Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(reviewVersion, items);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewSubjectList.class, "reviewVersion", reviewVersion, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long reviewVersion;

        private List<ReviewSubject> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder reviewVersion(long reviewVersion) {
            this.reviewVersion = reviewVersion;
            return this;
        }

        public Builder items(@Nonnull List<ReviewSubject> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public ReviewSubjectList build() {
            return new ReviewSubjectList(reviewVersion, items);
        }
    }
}
