package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

public class Thresholds {

    @JsonProperty("step_up")
    private long stepUp;

    @JsonProperty("review")
    private long review;

    @JsonInclude(Include.ALWAYS)
    @JsonProperty("block")
    private JsonNullable<Long> block;

    @JsonCreator
    public Thresholds(
            @JsonProperty("step_up") long stepUp,
            @JsonProperty("review") long review,
            @JsonProperty("block") @Nullable Long block) {
        this.stepUp = stepUp;
        this.review = review;
        this.block = JsonNullable.of(block);
    }

    public Thresholds(long stepUp, long review) {
        this(stepUp, review, null);
    }

    public long stepUp() {
        return this.stepUp;
    }

    public long review() {
        return this.review;
    }

    public JsonNullable<Long> block() {
        return this.block;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Thresholds withStepUp(long stepUp) {
        this.stepUp = stepUp;
        return this;
    }

    public Thresholds withReview(long review) {
        this.review = review;
        return this;
    }

    public Thresholds withBlock(@Nullable Long block) {
        this.block = JsonNullable.of(block);
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
        Thresholds other = (Thresholds) o;
        return Utils.enhancedDeepEquals(this.stepUp, other.stepUp)
                && Utils.enhancedDeepEquals(this.review, other.review)
                && Utils.enhancedDeepEquals(this.block, other.block);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(stepUp, review, block);
    }

    @Override
    public String toString() {
        return Utils.toString(Thresholds.class, "stepUp", stepUp, "review", review, "block", block);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long stepUp;

        private long review;

        private Long block;

        private Builder() {
            // force use of static builder() method
        }

        public Builder stepUp(long stepUp) {
            this.stepUp = stepUp;
            return this;
        }

        public Builder review(long review) {
            this.review = review;
            return this;
        }

        public Builder block(@Nullable Long block) {
            this.block = block;
            return this;
        }

        public Thresholds build() {
            return new Thresholds(stepUp, review, block);
        }
    }
}
