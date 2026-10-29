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
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * FlowSettings
 *
 * <p>One flow's policy in force. A score below step_up is allow, below review step_up, and from review
 * review; from block, when present, the decision is block. admission says when the flow's decision is
 * final.
 */
public class FlowSettings {

    @JsonProperty("step_up")
    private long stepUp;

    @JsonProperty("review")
    private long review;

    /**
     * The score from which the decision is block. Absent while blocking is off, the default.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("block")
    private Long block;

    /**
     * assessments only.
     */
    @JsonProperty("default_depth")
    private UsageCountDepth defaultDepth;

    /**
     * true while this flow has no thresholds of its own and uses account_creation's, the default for login
     * and purchase. Always false for signup.
     */
    @JsonProperty("inherits_account_creation")
    private boolean inheritsAccountCreation;

    /**
     * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
     * later evidence may change the decision. held: while the account's current assessment of this flow
     * has not completed, the account's decision for the flow is held, and the final decision arrives as
     * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
     * flow has its own admission.
     */
    @JsonProperty("admission")
    private Admission admission;

    /**
     * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
     * Kept while the flow is immediate.
     */
    @JsonProperty("hold_seconds")
    private long holdSeconds;

    /**
     * The decision a held assessment takes when hold_seconds pass before its investigation completes,
     * unless the evidence so far gives a stricter one (default review). It is announced as
     * account.risk_changed like any other change, so an expired hold is always announced. A hosted
     * provider hook, which cannot wait, answers a held account by it at once: deny for block, allow
     * otherwise.
     *
     * <p>Kept while the flow is immediate.
     */
    @JsonProperty("on_expiry")
    private OnExpiry onExpiry;

    @JsonCreator
    public FlowSettings(
            @JsonProperty("step_up") long stepUp,
            @JsonProperty("review") long review,
            @JsonProperty("block") @Nullable Long block,
            @JsonProperty("default_depth") @Nonnull UsageCountDepth defaultDepth,
            @JsonProperty("inherits_account_creation") boolean inheritsAccountCreation,
            @JsonProperty("admission") @Nonnull Admission admission,
            @JsonProperty("hold_seconds") long holdSeconds,
            @JsonProperty("on_expiry") @Nonnull OnExpiry onExpiry) {
        this.stepUp = stepUp;
        this.review = review;
        this.block = block;
        this.defaultDepth = Optional.ofNullable(defaultDepth)
                .orElseThrow(() -> new IllegalArgumentException("defaultDepth cannot be null"));
        this.inheritsAccountCreation = inheritsAccountCreation;
        this.admission = Optional.ofNullable(admission)
                .orElseThrow(() -> new IllegalArgumentException("admission cannot be null"));
        this.holdSeconds = holdSeconds;
        this.onExpiry = Optional.ofNullable(onExpiry)
                .orElseThrow(() -> new IllegalArgumentException("onExpiry cannot be null"));
    }

    public FlowSettings(
            long stepUp,
            long review,
            @Nonnull UsageCountDepth defaultDepth,
            boolean inheritsAccountCreation,
            @Nonnull Admission admission,
            long holdSeconds,
            @Nonnull OnExpiry onExpiry) {
        this(stepUp, review, null, defaultDepth, inheritsAccountCreation, admission, holdSeconds, onExpiry);
    }

    public long stepUp() {
        return this.stepUp;
    }

    public long review() {
        return this.review;
    }

    /**
     * The score from which the decision is block. Absent while blocking is off, the default.
     */
    public Optional<Long> block() {
        return Optional.ofNullable(this.block);
    }

    /**
     * assessments only.
     */
    public UsageCountDepth defaultDepth() {
        return this.defaultDepth;
    }

    /**
     * true while this flow has no thresholds of its own and uses account_creation's, the default for login
     * and purchase. Always false for signup.
     */
    public boolean inheritsAccountCreation() {
        return this.inheritsAccountCreation;
    }

    /**
     * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
     * later evidence may change the decision. held: while the account's current assessment of this flow
     * has not completed, the account's decision for the flow is held, and the final decision arrives as
     * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
     * flow has its own admission.
     */
    public Admission admission() {
        return this.admission;
    }

    /**
     * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
     * Kept while the flow is immediate.
     */
    public long holdSeconds() {
        return this.holdSeconds;
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
    public OnExpiry onExpiry() {
        return this.onExpiry;
    }

    public static Builder builder() {
        return new Builder();
    }

    public FlowSettings withStepUp(long stepUp) {
        this.stepUp = stepUp;
        return this;
    }

    public FlowSettings withReview(long review) {
        this.review = review;
        return this;
    }

    /**
     * The score from which the decision is block. Absent while blocking is off, the default.
     */
    public FlowSettings withBlock(@Nullable Long block) {
        this.block = block;
        return this;
    }

    /**
     * assessments only.
     */
    public FlowSettings withDefaultDepth(@Nonnull UsageCountDepth defaultDepth) {
        this.defaultDepth = Utils.checkNotNull(defaultDepth, "defaultDepth");
        return this;
    }

    /**
     * true while this flow has no thresholds of its own and uses account_creation's, the default for login
     * and purchase. Always false for signup.
     */
    public FlowSettings withInheritsAccountCreation(boolean inheritsAccountCreation) {
        this.inheritsAccountCreation = inheritsAccountCreation;
        return this;
    }

    /**
     * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
     * later evidence may change the decision. held: while the account's current assessment of this flow
     * has not completed, the account's decision for the flow is held, and the final decision arrives as
     * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
     * flow has its own admission.
     */
    public FlowSettings withAdmission(@Nonnull Admission admission) {
        this.admission = Utils.checkNotNull(admission, "admission");
        return this;
    }

    /**
     * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
     * Kept while the flow is immediate.
     */
    public FlowSettings withHoldSeconds(long holdSeconds) {
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
    public FlowSettings withOnExpiry(@Nonnull OnExpiry onExpiry) {
        this.onExpiry = Utils.checkNotNull(onExpiry, "onExpiry");
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
        FlowSettings other = (FlowSettings) o;
        return Utils.enhancedDeepEquals(this.stepUp, other.stepUp)
                && Utils.enhancedDeepEquals(this.review, other.review)
                && Utils.enhancedDeepEquals(this.block, other.block)
                && Utils.enhancedDeepEquals(this.defaultDepth, other.defaultDepth)
                && Utils.enhancedDeepEquals(this.inheritsAccountCreation, other.inheritsAccountCreation)
                && Utils.enhancedDeepEquals(this.admission, other.admission)
                && Utils.enhancedDeepEquals(this.holdSeconds, other.holdSeconds)
                && Utils.enhancedDeepEquals(this.onExpiry, other.onExpiry);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                stepUp, review, block, defaultDepth, inheritsAccountCreation, admission, holdSeconds, onExpiry);
    }

    @Override
    public String toString() {
        return Utils.toString(
                FlowSettings.class,
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
                "admission",
                admission,
                "holdSeconds",
                holdSeconds,
                "onExpiry",
                onExpiry);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long stepUp;

        private long review;

        private Long block;

        private UsageCountDepth defaultDepth;

        private boolean inheritsAccountCreation;

        private Admission admission;

        private long holdSeconds;

        private OnExpiry onExpiry;

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

        /**
         * The score from which the decision is block. Absent while blocking is off, the default.
         */
        public Builder block(@Nullable Long block) {
            this.block = block;
            return this;
        }

        /**
         * assessments only.
         */
        public Builder defaultDepth(@Nonnull UsageCountDepth defaultDepth) {
            this.defaultDepth = Utils.checkNotNull(defaultDepth, "defaultDepth");
            return this;
        }

        /**
         * true while this flow has no thresholds of its own and uses account_creation's, the default for login
         * and purchase. Always false for signup.
         */
        public Builder inheritsAccountCreation(boolean inheritsAccountCreation) {
            this.inheritsAccountCreation = inheritsAccountCreation;
            return this;
        }

        /**
         * immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
         * later evidence may change the decision. held: while the account's current assessment of this flow
         * has not completed, the account's decision for the flow is held, and the final decision arrives as
         * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
         * flow has its own admission.
         */
        public Builder admission(@Nonnull Admission admission) {
            this.admission = Utils.checkNotNull(admission, "admission");
            return this;
        }

        /**
         * How long a held flow's assessment is held after it was created, 30 to 86400 seconds (default 300).
         * Kept while the flow is immediate.
         */
        public Builder holdSeconds(long holdSeconds) {
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
        public Builder onExpiry(@Nonnull OnExpiry onExpiry) {
            this.onExpiry = Utils.checkNotNull(onExpiry, "onExpiry");
            return this;
        }

        public FlowSettings build() {
            return new FlowSettings(
                    stepUp, review, block, defaultDepth, inheritsAccountCreation, admission, holdSeconds, onExpiry);
        }
    }
}
