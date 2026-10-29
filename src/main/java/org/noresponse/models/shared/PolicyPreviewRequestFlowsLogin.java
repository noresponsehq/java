package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

public class PolicyPreviewRequestFlowsLogin {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("step_up")
    private Long stepUp;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("review")
    private Long review;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("block")
    private JsonNullable<Long> block;

    /**
     * Send true; false is refused.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("inherits_account_creation")
    private Boolean inheritsAccountCreation;

    @JsonCreator
    public PolicyPreviewRequestFlowsLogin(
            @JsonProperty("step_up") @Nullable Long stepUp,
            @JsonProperty("review") @Nullable Long review,
            @JsonProperty("block") @Nullable JsonNullable<Long> block,
            @JsonProperty("inherits_account_creation") @Nullable Boolean inheritsAccountCreation) {
        this.stepUp = stepUp;
        this.review = review;
        this.block = Optional.ofNullable(block).orElse(JsonNullable.undefined());
        this.inheritsAccountCreation = inheritsAccountCreation;
    }

    public PolicyPreviewRequestFlowsLogin() {
        this(null, null, null, null);
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

    /**
     * Send true; false is refused.
     */
    public Optional<Boolean> inheritsAccountCreation() {
        return Optional.ofNullable(this.inheritsAccountCreation);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyPreviewRequestFlowsLogin withStepUp(@Nullable Long stepUp) {
        this.stepUp = stepUp;
        return this;
    }

    public PolicyPreviewRequestFlowsLogin withReview(@Nullable Long review) {
        this.review = review;
        return this;
    }

    public PolicyPreviewRequestFlowsLogin withBlock(@Nullable Long block) {
        this.block = JsonNullable.of(block);
        return this;
    }

    /**
     * Send true; false is refused.
     */
    public PolicyPreviewRequestFlowsLogin withInheritsAccountCreation(@Nullable Boolean inheritsAccountCreation) {
        this.inheritsAccountCreation = inheritsAccountCreation;
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
        PolicyPreviewRequestFlowsLogin other = (PolicyPreviewRequestFlowsLogin) o;
        return Utils.enhancedDeepEquals(this.stepUp, other.stepUp)
                && Utils.enhancedDeepEquals(this.review, other.review)
                && Utils.enhancedDeepEquals(this.block, other.block)
                && Utils.enhancedDeepEquals(this.inheritsAccountCreation, other.inheritsAccountCreation);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(stepUp, review, block, inheritsAccountCreation);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyPreviewRequestFlowsLogin.class,
                "stepUp",
                stepUp,
                "review",
                review,
                "block",
                block,
                "inheritsAccountCreation",
                inheritsAccountCreation);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long stepUp;

        private Long review;

        private JsonNullable<Long> block;

        private Boolean inheritsAccountCreation;

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

        /**
         * Send true; false is refused.
         */
        public Builder inheritsAccountCreation(@Nullable Boolean inheritsAccountCreation) {
            this.inheritsAccountCreation = inheritsAccountCreation;
            return this;
        }

        public PolicyPreviewRequestFlowsLogin build() {
            return new PolicyPreviewRequestFlowsLogin(stepUp, review, block, inheritsAccountCreation);
        }
    }
}
