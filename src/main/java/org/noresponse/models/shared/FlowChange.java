package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * FlowChange
 *
 * <p>A change to one flow. A field left out keeps the value in force. Thresholds set on login or purchase
 * start from the values in force, account_creation's while it inherits them.
 *
 * <p>After the change, 0 &lt; step_up &lt; review &lt;= 100 and block is off or review &lt;= block &lt;=
 * 100.
 */
public class FlowChange {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("step_up")
    private Long stepUp;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("review")
    private Long review;

    /**
     * The score from which the decision is block; null turns blocking off.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("block")
    private JsonNullable<Long> block;

    /**
     * assessments only.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("default_depth")
    private UsageCountDepth defaultDepth;

    /**
     * Login or purchase only: drop the flow's own thresholds so it uses account_creation's again. Not
     * combined with step_up, review or block. Send true; false is refused.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("inherits_account_creation")
    private Boolean inheritsAccountCreation;

    /**
     * Alias of inherits_account_creation, still accepted. Send true; false is refused.
     *
     * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("inherits_signup")
    @Deprecated
    private Boolean inheritsSignup;

    /**
     * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
     * later evidence may change the decision. held: while the account's current assessment of this flow
     * has not completed, the account's decision for the flow is held, and the final decision arrives as
     * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
     * flow has its own admission.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("admission")
    private Admission admission;

    /**
     * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
     * Kept while the flow is immediate.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("hold_seconds")
    private Long holdSeconds;

    /**
     * The decision a held assessment takes when hold_seconds pass before its investigation completes,
     * unless the evidence so far gives a stricter one (default review). It is announced as
     * account.risk_changed like any other change, so an expired hold is always announced. A hosted
     * provider hook, which cannot wait, answers a held account by it at once: deny for block, allow
     * otherwise.
     *
     * <p>Kept while the flow is immediate.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("on_expiry")
    private OnExpiry onExpiry;

    @JsonCreator
    public FlowChange(
            @JsonProperty("step_up") @Nullable Long stepUp,
            @JsonProperty("review") @Nullable Long review,
            @JsonProperty("block") @Nullable JsonNullable<Long> block,
            @JsonProperty("default_depth") @Nullable UsageCountDepth defaultDepth,
            @JsonProperty("inherits_account_creation") @Nullable Boolean inheritsAccountCreation,
            @JsonProperty("inherits_signup") @Nullable Boolean inheritsSignup,
            @JsonProperty("admission") @Nullable Admission admission,
            @JsonProperty("hold_seconds") @Nullable Long holdSeconds,
            @JsonProperty("on_expiry") @Nullable OnExpiry onExpiry) {
        this.stepUp = stepUp;
        this.review = review;
        this.block = Optional.ofNullable(block).orElse(JsonNullable.undefined());
        this.defaultDepth = defaultDepth;
        this.inheritsAccountCreation = inheritsAccountCreation;
        this.inheritsSignup = inheritsSignup;
        this.admission = admission;
        this.holdSeconds = holdSeconds;
        this.onExpiry = onExpiry;
    }

    public FlowChange() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public Optional<Long> stepUp() {
        return Optional.ofNullable(this.stepUp);
    }

    public Optional<Long> review() {
        return Optional.ofNullable(this.review);
    }

    /**
     * The score from which the decision is block; null turns blocking off.
     */
    public JsonNullable<Long> block() {
        return this.block;
    }

    /**
     * assessments only.
     */
    public Optional<UsageCountDepth> defaultDepth() {
        return Optional.ofNullable(this.defaultDepth);
    }

    /**
     * Login or purchase only: drop the flow's own thresholds so it uses account_creation's again. Not
     * combined with step_up, review or block. Send true; false is refused.
     */
    public Optional<Boolean> inheritsAccountCreation() {
        return Optional.ofNullable(this.inheritsAccountCreation);
    }

    /**
     * Alias of inherits_account_creation, still accepted. Send true; false is refused.
     *
     * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
     */
    @Deprecated
    public Optional<Boolean> inheritsSignup() {
        return Optional.ofNullable(this.inheritsSignup);
    }

    /**
     * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
     * later evidence may change the decision. held: while the account's current assessment of this flow
     * has not completed, the account's decision for the flow is held, and the final decision arrives as
     * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
     * flow has its own admission.
     */
    public Optional<Admission> admission() {
        return Optional.ofNullable(this.admission);
    }

    /**
     * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
     * Kept while the flow is immediate.
     */
    public Optional<Long> holdSeconds() {
        return Optional.ofNullable(this.holdSeconds);
    }

    /**
     * The decision a held assessment takes when hold_seconds pass before its investigation completes,
     * unless the evidence so far gives a stricter one (default review). It is announced as
     * account.risk_changed like any other change, so an expired hold is always announced. A hosted
     * provider hook, which cannot wait, answers a held account by it at once: deny for block, allow
     * otherwise.
     *
     * <p>Kept while the flow is immediate.
     */
    public Optional<OnExpiry> onExpiry() {
        return Optional.ofNullable(this.onExpiry);
    }

    public static Builder builder() {
        return new Builder();
    }

    public FlowChange withStepUp(@Nullable Long stepUp) {
        this.stepUp = stepUp;
        return this;
    }

    public FlowChange withReview(@Nullable Long review) {
        this.review = review;
        return this;
    }

    /**
     * The score from which the decision is block; null turns blocking off.
     */
    public FlowChange withBlock(@Nullable Long block) {
        this.block = JsonNullable.of(block);
        return this;
    }

    /**
     * assessments only.
     */
    public FlowChange withDefaultDepth(@Nullable UsageCountDepth defaultDepth) {
        this.defaultDepth = defaultDepth;
        return this;
    }

    /**
     * Login or purchase only: drop the flow's own thresholds so it uses account_creation's again. Not
     * combined with step_up, review or block. Send true; false is refused.
     */
    public FlowChange withInheritsAccountCreation(@Nullable Boolean inheritsAccountCreation) {
        this.inheritsAccountCreation = inheritsAccountCreation;
        return this;
    }

    /**
     * Alias of inherits_account_creation, still accepted. Send true; false is refused.
     *
     * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
     */
    @Deprecated
    public FlowChange withInheritsSignup(@Nullable Boolean inheritsSignup) {
        this.inheritsSignup = inheritsSignup;
        return this;
    }

    /**
     * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
     * later evidence may change the decision. held: while the account's current assessment of this flow
     * has not completed, the account's decision for the flow is held, and the final decision arrives as
     * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
     * flow has its own admission.
     */
    public FlowChange withAdmission(@Nullable Admission admission) {
        this.admission = admission;
        return this;
    }

    /**
     * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
     * Kept while the flow is immediate.
     */
    public FlowChange withHoldSeconds(@Nullable Long holdSeconds) {
        this.holdSeconds = holdSeconds;
        return this;
    }

    /**
     * The decision a held assessment takes when hold_seconds pass before its investigation completes,
     * unless the evidence so far gives a stricter one (default review). It is announced as
     * account.risk_changed like any other change, so an expired hold is always announced. A hosted
     * provider hook, which cannot wait, answers a held account by it at once: deny for block, allow
     * otherwise.
     *
     * <p>Kept while the flow is immediate.
     */
    public FlowChange withOnExpiry(@Nullable OnExpiry onExpiry) {
        this.onExpiry = onExpiry;
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
        FlowChange other = (FlowChange) o;
        return Utils.enhancedDeepEquals(this.stepUp, other.stepUp)
                && Utils.enhancedDeepEquals(this.review, other.review)
                && Utils.enhancedDeepEquals(this.block, other.block)
                && Utils.enhancedDeepEquals(this.defaultDepth, other.defaultDepth)
                && Utils.enhancedDeepEquals(this.inheritsAccountCreation, other.inheritsAccountCreation)
                && Utils.enhancedDeepEquals(this.inheritsSignup, other.inheritsSignup)
                && Utils.enhancedDeepEquals(this.admission, other.admission)
                && Utils.enhancedDeepEquals(this.holdSeconds, other.holdSeconds)
                && Utils.enhancedDeepEquals(this.onExpiry, other.onExpiry);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                stepUp,
                review,
                block,
                defaultDepth,
                inheritsAccountCreation,
                inheritsSignup,
                admission,
                holdSeconds,
                onExpiry);
    }

    @Override
    public String toString() {
        return Utils.toString(
                FlowChange.class,
                "stepUp",
                stepUp,
                "review",
                review,
                "block",
                block,
                "defaultDepth",
                defaultDepth,
                "inheritsAccountCreation",
                inheritsAccountCreation,
                "inheritsSignup",
                inheritsSignup,
                "admission",
                admission,
                "holdSeconds",
                holdSeconds,
                "onExpiry",
                onExpiry);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long stepUp;

        private Long review;

        private JsonNullable<Long> block;

        private UsageCountDepth defaultDepth;

        private Boolean inheritsAccountCreation;

        @Deprecated
        private Boolean inheritsSignup;

        private Admission admission;

        private Long holdSeconds;

        private OnExpiry onExpiry;

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

        /**
         * The score from which the decision is block; null turns blocking off.
         */
        public Builder block(@Nullable Long block) {
            this.block = JsonNullable.of(block);
            return this;
        }

        /**
         * assessments only.
         */
        public Builder defaultDepth(@Nullable UsageCountDepth defaultDepth) {
            this.defaultDepth = defaultDepth;
            return this;
        }

        /**
         * Login or purchase only: drop the flow's own thresholds so it uses account_creation's again. Not
         * combined with step_up, review or block. Send true; false is refused.
         */
        public Builder inheritsAccountCreation(@Nullable Boolean inheritsAccountCreation) {
            this.inheritsAccountCreation = inheritsAccountCreation;
            return this;
        }

        /**
         * Alias of inherits_account_creation, still accepted. Send true; false is refused.
         *
         * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
         */
        @Deprecated
        public Builder inheritsSignup(@Nullable Boolean inheritsSignup) {
            this.inheritsSignup = inheritsSignup;
            return this;
        }

        /**
         * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
         * later evidence may change the decision. held: while the account's current assessment of this flow
         * has not completed, the account's decision for the flow is held, and the final decision arrives as
         * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
         * flow has its own admission.
         */
        public Builder admission(@Nullable Admission admission) {
            this.admission = admission;
            return this;
        }

        /**
         * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
         * Kept while the flow is immediate.
         */
        public Builder holdSeconds(@Nullable Long holdSeconds) {
            this.holdSeconds = holdSeconds;
            return this;
        }

        /**
         * The decision a held assessment takes when hold_seconds pass before its investigation completes,
         * unless the evidence so far gives a stricter one (default review). It is announced as
         * account.risk_changed like any other change, so an expired hold is always announced. A hosted
         * provider hook, which cannot wait, answers a held account by it at once: deny for block, allow
         * otherwise.
         *
         * <p>Kept while the flow is immediate.
         */
        public Builder onExpiry(@Nullable OnExpiry onExpiry) {
            this.onExpiry = onExpiry;
            return this;
        }

        public FlowChange build() {
            return new FlowChange(
                    stepUp,
                    review,
                    block,
                    defaultDepth,
                    inheritsAccountCreation,
                    inheritsSignup,
                    admission,
                    holdSeconds,
                    onExpiry);
        }
    }
}
