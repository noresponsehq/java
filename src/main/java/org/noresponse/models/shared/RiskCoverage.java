package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

/**
 * RiskCoverage
 *
 * <p>Sources counted by state, not named.
 */
public class RiskCoverage {
    /**
     * Sources that gave a definite answer.
     */
    @JsonProperty("answered")
    private long answered;

    /**
     * Sources that could not answer.
     */
    @JsonProperty("failed")
    private long failed;

    /**
     * Sources still being asked.
     */
    @JsonProperty("pending")
    private long pending;

    @JsonCreator
    public RiskCoverage(
            @JsonProperty("answered") long answered,
            @JsonProperty("failed") long failed,
            @JsonProperty("pending") long pending) {
        this.answered = answered;
        this.failed = failed;
        this.pending = pending;
    }

    /**
     * Sources that gave a definite answer.
     */
    public long answered() {
        return this.answered;
    }

    /**
     * Sources that could not answer.
     */
    public long failed() {
        return this.failed;
    }

    /**
     * Sources still being asked.
     */
    public long pending() {
        return this.pending;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Sources that gave a definite answer.
     */
    public RiskCoverage withAnswered(long answered) {
        this.answered = answered;
        return this;
    }

    /**
     * Sources that could not answer.
     */
    public RiskCoverage withFailed(long failed) {
        this.failed = failed;
        return this;
    }

    /**
     * Sources still being asked.
     */
    public RiskCoverage withPending(long pending) {
        this.pending = pending;
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
        RiskCoverage other = (RiskCoverage) o;
        return Utils.enhancedDeepEquals(this.answered, other.answered)
                && Utils.enhancedDeepEquals(this.failed, other.failed)
                && Utils.enhancedDeepEquals(this.pending, other.pending);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(answered, failed, pending);
    }

    @Override
    public String toString() {
        return Utils.toString(RiskCoverage.class, "answered", answered, "failed", failed, "pending", pending);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long answered;

        private long failed;

        private long pending;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Sources that gave a definite answer.
         */
        public Builder answered(long answered) {
            this.answered = answered;
            return this;
        }

        /**
         * Sources that could not answer.
         */
        public Builder failed(long failed) {
            this.failed = failed;
            return this;
        }

        /**
         * Sources still being asked.
         */
        public Builder pending(long pending) {
            this.pending = pending;
            return this;
        }

        public RiskCoverage build() {
            return new RiskCoverage(answered, failed, pending);
        }
    }
}
