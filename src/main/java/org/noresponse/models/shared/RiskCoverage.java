package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

/**
 * RiskCoverage
 *
 * <p>Counts of planned source-identifier pairs by evidence outcome, deduplicated across signals and
 * incomplete entries. These counts do not measure identity agreement or prove that processing is still
 * running. A lane that was not searched is not an answered source.
 */
public class RiskCoverage {
    /**
     * Source-identifier pairs that gave a definite found or not_found answer.
     */
    @JsonProperty("answered")
    private long answered;

    /**
     * Source-identifier pairs that could not answer, including unsupported coverage markers and source
     * failures. Read detailed signals to distinguish Not covered from Failed.
     */
    @JsonProperty("failed")
    private long failed;

    /**
     * Source-identifier pairs with deferred or timed_out evidence. A terminal timeout can remain here
     * after processing finishes; use assessment status and continuation metadata to determine whether work
     * is still running.
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
     * Source-identifier pairs that gave a definite found or not_found answer.
     */
    public long answered() {
        return this.answered;
    }

    /**
     * Source-identifier pairs that could not answer, including unsupported coverage markers and source
     * failures. Read detailed signals to distinguish Not covered from Failed.
     */
    public long failed() {
        return this.failed;
    }

    /**
     * Source-identifier pairs with deferred or timed_out evidence. A terminal timeout can remain here
     * after processing finishes; use assessment status and continuation metadata to determine whether work
     * is still running.
     */
    public long pending() {
        return this.pending;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Source-identifier pairs that gave a definite found or not_found answer.
     */
    public RiskCoverage withAnswered(long answered) {
        this.answered = answered;
        return this;
    }

    /**
     * Source-identifier pairs that could not answer, including unsupported coverage markers and source
     * failures. Read detailed signals to distinguish Not covered from Failed.
     */
    public RiskCoverage withFailed(long failed) {
        this.failed = failed;
        return this;
    }

    /**
     * Source-identifier pairs with deferred or timed_out evidence. A terminal timeout can remain here
     * after processing finishes; use assessment status and continuation metadata to determine whether work
     * is still running.
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
         * Source-identifier pairs that gave a definite found or not_found answer.
         */
        public Builder answered(long answered) {
            this.answered = answered;
            return this;
        }

        /**
         * Source-identifier pairs that could not answer, including unsupported coverage markers and source
         * failures. Read detailed signals to distinguish Not covered from Failed.
         */
        public Builder failed(long failed) {
            this.failed = failed;
            return this;
        }

        /**
         * Source-identifier pairs with deferred or timed_out evidence. A terminal timeout can remain here
         * after processing finishes; use assessment status and continuation metadata to determine whether work
         * is still running.
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
