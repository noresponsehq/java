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
import org.openapitools.jackson.nullable.JsonNullable;

public class PolicyPreviewRequestFlowsAccountCreation {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("step_up")
    private Long stepUp;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("review")
    private Long review;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("block")
    private JsonNullable<Long> block;

    @JsonCreator
    public PolicyPreviewRequestFlowsAccountCreation(
            @JsonProperty("step_up") @Nullable Long stepUp,
            @JsonProperty("review") @Nullable Long review,
            @JsonProperty("block") @Nullable JsonNullable<Long> block) {
        this.stepUp = stepUp;
        this.review = review;
        this.block = Optional.ofNullable(block).orElse(JsonNullable.undefined());
    }

    public PolicyPreviewRequestFlowsAccountCreation() {
        this(null, null, null);
    }

    public Optional<Long> stepUp() {
        return Optional.ofNullable(this.stepUp);
    }

    public Optional<Long> review() {
        return Optional.ofNullable(this.review);
    }

    public JsonNullable<Long> block() {
        return this.block;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyPreviewRequestFlowsAccountCreation withStepUp(@Nullable Long stepUp) {
        this.stepUp = stepUp;
        return this;
    }

    public PolicyPreviewRequestFlowsAccountCreation withReview(@Nullable Long review) {
        this.review = review;
        return this;
    }

    public PolicyPreviewRequestFlowsAccountCreation withBlock(@Nullable Long block) {
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
        PolicyPreviewRequestFlowsAccountCreation other = (PolicyPreviewRequestFlowsAccountCreation) o;
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
        return Utils.toString(
                PolicyPreviewRequestFlowsAccountCreation.class, "stepUp", stepUp, "review", review, "block", block);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long stepUp;

        private Long review;

        private JsonNullable<Long> block;

        private Builder() {
            // force use of static builder() method
        }

        public Builder stepUp(@Nullable Long stepUp) {
            this.stepUp = stepUp;
            return this;
        }

        public Builder review(@Nullable Long review) {
            this.review = review;
            return this;
        }

        public Builder block(@Nullable Long block) {
            this.block = JsonNullable.of(block);
            return this;
        }

        public PolicyPreviewRequestFlowsAccountCreation build() {
            return new PolicyPreviewRequestFlowsAccountCreation(stepUp, review, block);
        }
    }
}
