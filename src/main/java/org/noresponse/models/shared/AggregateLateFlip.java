package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AggregateLateFlip
 *
 * <p>Changes of the account decision for an assessment's flow after its first answer, as
 * account.risk_changed announces them, counted in the bucket of the assessment's creation. from is the
 * decision announced before (allow before the first announcement); an assessment that changes twice
 * counts twice.
 */
public class AggregateLateFlip {
    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    @JsonProperty("from")
    private AccountDecision from;

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    @JsonProperty("to")
    private AccountDecision to;

    /**
     * How long after the assessment's creation the change was made.
     */
    @JsonProperty("delay")
    private Delay delay;

    @JsonProperty("count")
    private long count;

    @JsonCreator
    public AggregateLateFlip(
            @JsonProperty("from") @Nonnull AccountDecision from,
            @JsonProperty("to") @Nonnull AccountDecision to,
            @JsonProperty("delay") @Nonnull Delay delay,
            @JsonProperty("count") long count) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.delay = Optional.ofNullable(delay).orElseThrow(() -> new IllegalArgumentException("delay cannot be null"));
        this.count = count;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    public AccountDecision from() {
        return this.from;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    public AccountDecision to() {
        return this.to;
    }

    /**
     * How long after the assessment's creation the change was made.
     */
    public Delay delay() {
        return this.delay;
    }

    public long count() {
        return this.count;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    public AggregateLateFlip withFrom(@Nonnull AccountDecision from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    /**
     * block when the score reached your block threshold (risk.block of the assessment), otherwise the
     * assessment's recommendation. held only for a flow whose admission is held (settings
     * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
     * investigation has not completed (status partial): the final decision arrives as account.risk_changed
     * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
     * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
     * evidence so far gives, whichever is stricter. Your application applies it.
     */
    public AggregateLateFlip withTo(@Nonnull AccountDecision to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    /**
     * How long after the assessment's creation the change was made.
     */
    public AggregateLateFlip withDelay(@Nonnull Delay delay) {
        this.delay = Utils.checkNotNull(delay, "delay");
        return this;
    }

    public AggregateLateFlip withCount(long count) {
        this.count = count;
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
        AggregateLateFlip other = (AggregateLateFlip) o;
        return Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.delay, other.delay)
                && Utils.enhancedDeepEquals(this.count, other.count);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(from, to, delay, count);
    }

    @Override
    public String toString() {
        return Utils.toString(AggregateLateFlip.class, "from", from, "to", to, "delay", delay, "count", count);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AccountDecision from;

        private AccountDecision to;

        private Delay delay;

        private long count;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * block when the score reached your block threshold (risk.block of the assessment), otherwise the
         * assessment's recommendation. held only for a flow whose admission is held (settings
         * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
         * investigation has not completed (status partial): the final decision arrives as account.risk_changed
         * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
         * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
         * evidence so far gives, whichever is stricter. Your application applies it.
         */
        public Builder from(@Nonnull AccountDecision from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        /**
         * block when the score reached your block threshold (risk.block of the assessment), otherwise the
         * assessment's recommendation. held only for a flow whose admission is held (settings
         * flows.&lt;flow&gt;.admission), when the account's current assessment was made for that flow and its
         * investigation has not completed (status partial): the final decision arrives as account.risk_changed
         * when the investigation completes, decided by its score, or when the hold's deadline (hold_seconds
         * after the assessment was created) passes first, as the flow's on_expiry action or the decision the
         * evidence so far gives, whichever is stricter. Your application applies it.
         */
        public Builder to(@Nonnull AccountDecision to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        /**
         * How long after the assessment's creation the change was made.
         */
        public Builder delay(@Nonnull Delay delay) {
            this.delay = Utils.checkNotNull(delay, "delay");
            return this;
        }

        public Builder count(long count) {
            this.count = count;
            return this;
        }

        public AggregateLateFlip build() {
            return new AggregateLateFlip(from, to, delay, count);
        }
    }
}
