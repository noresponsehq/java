package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ReviewsConclusions
 *
 * <p>Current resolution of each resolved retained review; prior resolutions and reopened reviews do not
 * count. Missing keys mean zero. These investigation conclusions are independent of account feedback.
 */
public class ReviewsConclusions {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("benign")
    private Long benign;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("abuse")
    private Long abuse;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("inconclusive")
    private Long inconclusive;

    @JsonCreator
    public ReviewsConclusions(
            @JsonProperty("benign") @Nullable Long benign,
            @JsonProperty("abuse") @Nullable Long abuse,
            @JsonProperty("inconclusive") @Nullable Long inconclusive) {
        this.benign = benign;
        this.abuse = abuse;
        this.inconclusive = inconclusive;
    }

    public ReviewsConclusions() {
        this(null, null, null);
    }

    public Optional<Long> benign() {
        return Optional.ofNullable(this.benign);
    }

    public Optional<Long> abuse() {
        return Optional.ofNullable(this.abuse);
    }

    public Optional<Long> inconclusive() {
        return Optional.ofNullable(this.inconclusive);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewsConclusions withBenign(@Nullable Long benign) {
        this.benign = benign;
        return this;
    }

    public ReviewsConclusions withAbuse(@Nullable Long abuse) {
        this.abuse = abuse;
        return this;
    }

    public ReviewsConclusions withInconclusive(@Nullable Long inconclusive) {
        this.inconclusive = inconclusive;
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
        ReviewsConclusions other = (ReviewsConclusions) o;
        return Utils.enhancedDeepEquals(this.benign, other.benign)
                && Utils.enhancedDeepEquals(this.abuse, other.abuse)
                && Utils.enhancedDeepEquals(this.inconclusive, other.inconclusive);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(benign, abuse, inconclusive);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewsConclusions.class, "benign", benign, "abuse", abuse, "inconclusive", inconclusive);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long benign;

        private Long abuse;

        private Long inconclusive;

        private Builder() {
            // force use of static builder() method
        }

        public Builder benign(@Nullable Long benign) {
            this.benign = benign;
            return this;
        }

        public Builder abuse(@Nullable Long abuse) {
            this.abuse = abuse;
            return this;
        }

        public Builder inconclusive(@Nullable Long inconclusive) {
            this.inconclusive = inconclusive;
            return this;
        }

        public ReviewsConclusions build() {
            return new ReviewsConclusions(benign, abuse, inconclusive);
        }
    }
}
