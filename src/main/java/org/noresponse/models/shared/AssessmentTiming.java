package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

public class AssessmentTiming {
    /**
     * The budget the server applied to the synchronous answer.
     */
    @JsonProperty("deadline_ms")
    private long deadlineMs;

    /**
     * Server time from HTTP ingress to this revision of the assessment.
     */
    @JsonProperty("elapsed_ms")
    private long elapsedMs;

    @JsonCreator
    public AssessmentTiming(@JsonProperty("deadline_ms") long deadlineMs, @JsonProperty("elapsed_ms") long elapsedMs) {
        this.deadlineMs = deadlineMs;
        this.elapsedMs = elapsedMs;
    }

    /**
     * The budget the server applied to the synchronous answer.
     */
    public long deadlineMs() {
        return this.deadlineMs;
    }

    /**
     * Server time from HTTP ingress to this revision of the assessment.
     */
    public long elapsedMs() {
        return this.elapsedMs;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The budget the server applied to the synchronous answer.
     */
    public AssessmentTiming withDeadlineMs(long deadlineMs) {
        this.deadlineMs = deadlineMs;
        return this;
    }

    /**
     * Server time from HTTP ingress to this revision of the assessment.
     */
    public AssessmentTiming withElapsedMs(long elapsedMs) {
        this.elapsedMs = elapsedMs;
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
        AssessmentTiming other = (AssessmentTiming) o;
        return Utils.enhancedDeepEquals(this.deadlineMs, other.deadlineMs)
                && Utils.enhancedDeepEquals(this.elapsedMs, other.elapsedMs);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(deadlineMs, elapsedMs);
    }

    @Override
    public String toString() {
        return Utils.toString(AssessmentTiming.class, "deadlineMs", deadlineMs, "elapsedMs", elapsedMs);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long deadlineMs;

        private long elapsedMs;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The budget the server applied to the synchronous answer.
         */
        public Builder deadlineMs(long deadlineMs) {
            this.deadlineMs = deadlineMs;
            return this;
        }

        /**
         * Server time from HTTP ingress to this revision of the assessment.
         */
        public Builder elapsedMs(long elapsedMs) {
            this.elapsedMs = elapsedMs;
            return this;
        }

        public AssessmentTiming build() {
            return new AssessmentTiming(deadlineMs, elapsedMs);
        }
    }
}
