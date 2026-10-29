package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

public class Cost {

    @JsonProperty("allow")
    private double allow;

    @JsonProperty("step_up")
    private double stepUp;

    @JsonProperty("review")
    private double review;

    @JsonProperty("block")
    private double block;

    @JsonCreator
    public Cost(
            @JsonProperty("allow") double allow,
            @JsonProperty("step_up") double stepUp,
            @JsonProperty("review") double review,
            @JsonProperty("block") double block) {
        this.allow = allow;
        this.stepUp = stepUp;
        this.review = review;
        this.block = block;
    }

    public double allow() {
        return this.allow;
    }

    public double stepUp() {
        return this.stepUp;
    }

    public double review() {
        return this.review;
    }

    public double block() {
        return this.block;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Cost withAllow(double allow) {
        this.allow = allow;
        return this;
    }

    public Cost withStepUp(double stepUp) {
        this.stepUp = stepUp;
        return this;
    }

    public Cost withReview(double review) {
        this.review = review;
        return this;
    }

    public Cost withBlock(double block) {
        this.block = block;
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
        Cost other = (Cost) o;
        return Utils.enhancedDeepEquals(this.allow, other.allow)
                && Utils.enhancedDeepEquals(this.stepUp, other.stepUp)
                && Utils.enhancedDeepEquals(this.review, other.review)
                && Utils.enhancedDeepEquals(this.block, other.block);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(allow, stepUp, review, block);
    }

    @Override
    public String toString() {
        return Utils.toString(Cost.class, "allow", allow, "stepUp", stepUp, "review", review, "block", block);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private double allow;

        private double stepUp;

        private double review;

        private double block;

        private Builder() {
            // force use of static builder() method
        }

        public Builder allow(double allow) {
            this.allow = allow;
            return this;
        }

        public Builder stepUp(double stepUp) {
            this.stepUp = stepUp;
            return this;
        }

        public Builder review(double review) {
            this.review = review;
            return this;
        }

        public Builder block(double block) {
            this.block = block;
            return this;
        }

        public Cost build() {
            return new Cost(allow, stepUp, review, block);
        }
    }
}
