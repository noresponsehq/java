package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class OperationalAggregateBucket {

    @JsonProperty("start")
    private String start;

    @JsonProperty("attempts")
    private long attempts;

    @JsonProperty("succeeded")
    private long succeeded;

    @JsonProperty("failed")
    private long failed;

    @JsonProperty("client_errors")
    private long clientErrors;

    @JsonProperty("server_errors")
    private long serverErrors;

    @JsonProperty("replays")
    private long replays;

    @JsonProperty("incomplete_writes")
    private long incompleteWrites;

    @JsonProperty("unattributed_keys")
    private long unattributedKeys;

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("p50_ms")
    private Double p50Ms;

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("p95_ms")
    private Double p95Ms;

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("p99_ms")
    private Double p99Ms;

    @JsonCreator
    public OperationalAggregateBucket(
            @JsonProperty("start") @Nonnull String start,
            @JsonProperty("attempts") long attempts,
            @JsonProperty("succeeded") long succeeded,
            @JsonProperty("failed") long failed,
            @JsonProperty("client_errors") long clientErrors,
            @JsonProperty("server_errors") long serverErrors,
            @JsonProperty("replays") long replays,
            @JsonProperty("incomplete_writes") long incompleteWrites,
            @JsonProperty("unattributed_keys") long unattributedKeys,
            @JsonProperty("p50_ms") @Nullable Double p50Ms,
            @JsonProperty("p95_ms") @Nullable Double p95Ms,
            @JsonProperty("p99_ms") @Nullable Double p99Ms) {
        this.start = Optional.ofNullable(start).orElseThrow(() -> new IllegalArgumentException("start cannot be null"));
        this.attempts = attempts;
        this.succeeded = succeeded;
        this.failed = failed;
        this.clientErrors = clientErrors;
        this.serverErrors = serverErrors;
        this.replays = replays;
        this.incompleteWrites = incompleteWrites;
        this.unattributedKeys = unattributedKeys;
        this.p50Ms = p50Ms;
        this.p95Ms = p95Ms;
        this.p99Ms = p99Ms;
    }

    public OperationalAggregateBucket(
            @Nonnull String start,
            long attempts,
            long succeeded,
            long failed,
            long clientErrors,
            long serverErrors,
            long replays,
            long incompleteWrites,
            long unattributedKeys) {
        this(
                start,
                attempts,
                succeeded,
                failed,
                clientErrors,
                serverErrors,
                replays,
                incompleteWrites,
                unattributedKeys,
                null,
                null,
                null);
    }

    public String start() {
        return this.start;
    }

    public long attempts() {
        return this.attempts;
    }

    public long succeeded() {
        return this.succeeded;
    }

    public long failed() {
        return this.failed;
    }

    public long clientErrors() {
        return this.clientErrors;
    }

    public long serverErrors() {
        return this.serverErrors;
    }

    public long replays() {
        return this.replays;
    }

    public long incompleteWrites() {
        return this.incompleteWrites;
    }

    public long unattributedKeys() {
        return this.unattributedKeys;
    }

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    public Optional<Double> p50Ms() {
        return Optional.ofNullable(this.p50Ms);
    }

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    public Optional<Double> p95Ms() {
        return Optional.ofNullable(this.p95Ms);
    }

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    public Optional<Double> p99Ms() {
        return Optional.ofNullable(this.p99Ms);
    }

    public static Builder builder() {
        return new Builder();
    }

    public OperationalAggregateBucket withStart(@Nonnull String start) {
        this.start = Utils.checkNotNull(start, "start");
        return this;
    }

    public OperationalAggregateBucket withAttempts(long attempts) {
        this.attempts = attempts;
        return this;
    }

    public OperationalAggregateBucket withSucceeded(long succeeded) {
        this.succeeded = succeeded;
        return this;
    }

    public OperationalAggregateBucket withFailed(long failed) {
        this.failed = failed;
        return this;
    }

    public OperationalAggregateBucket withClientErrors(long clientErrors) {
        this.clientErrors = clientErrors;
        return this;
    }

    public OperationalAggregateBucket withServerErrors(long serverErrors) {
        this.serverErrors = serverErrors;
        return this;
    }

    public OperationalAggregateBucket withReplays(long replays) {
        this.replays = replays;
        return this;
    }

    public OperationalAggregateBucket withIncompleteWrites(long incompleteWrites) {
        this.incompleteWrites = incompleteWrites;
        return this;
    }

    public OperationalAggregateBucket withUnattributedKeys(long unattributedKeys) {
        this.unattributedKeys = unattributedKeys;
        return this;
    }

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    public OperationalAggregateBucket withP50Ms(@Nullable Double p50Ms) {
        this.p50Ms = p50Ms;
        return this;
    }

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    public OperationalAggregateBucket withP95Ms(@Nullable Double p95Ms) {
        this.p95Ms = p95Ms;
        return this;
    }

    /**
     * Continuous percentile over retained attempts. Omitted when there are no captured samples.
     */
    public OperationalAggregateBucket withP99Ms(@Nullable Double p99Ms) {
        this.p99Ms = p99Ms;
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
        OperationalAggregateBucket other = (OperationalAggregateBucket) o;
        return Utils.enhancedDeepEquals(this.start, other.start)
                && Utils.enhancedDeepEquals(this.attempts, other.attempts)
                && Utils.enhancedDeepEquals(this.succeeded, other.succeeded)
                && Utils.enhancedDeepEquals(this.failed, other.failed)
                && Utils.enhancedDeepEquals(this.clientErrors, other.clientErrors)
                && Utils.enhancedDeepEquals(this.serverErrors, other.serverErrors)
                && Utils.enhancedDeepEquals(this.replays, other.replays)
                && Utils.enhancedDeepEquals(this.incompleteWrites, other.incompleteWrites)
                && Utils.enhancedDeepEquals(this.unattributedKeys, other.unattributedKeys)
                && Utils.enhancedDeepEquals(this.p50Ms, other.p50Ms)
                && Utils.enhancedDeepEquals(this.p95Ms, other.p95Ms)
                && Utils.enhancedDeepEquals(this.p99Ms, other.p99Ms);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                start,
                attempts,
                succeeded,
                failed,
                clientErrors,
                serverErrors,
                replays,
                incompleteWrites,
                unattributedKeys,
                p50Ms,
                p95Ms,
                p99Ms);
    }

    @Override
    public String toString() {
        return Utils.toString(
                OperationalAggregateBucket.class,
                "start",
                start,
                "attempts",
                attempts,
                "succeeded",
                succeeded,
                "failed",
                failed,
                "clientErrors",
                clientErrors,
                "serverErrors",
                serverErrors,
                "replays",
                replays,
                "incompleteWrites",
                incompleteWrites,
                "unattributedKeys",
                unattributedKeys,
                "p50Ms",
                p50Ms,
                "p95Ms",
                p95Ms,
                "p99Ms",
                p99Ms);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String start;

        private long attempts;

        private long succeeded;

        private long failed;

        private long clientErrors;

        private long serverErrors;

        private long replays;

        private long incompleteWrites;

        private long unattributedKeys;

        private Double p50Ms;

        private Double p95Ms;

        private Double p99Ms;

        private Builder() {
            // force use of static builder() method
        }

        public Builder start(@Nonnull String start) {
            this.start = Utils.checkNotNull(start, "start");
            return this;
        }

        public Builder attempts(long attempts) {
            this.attempts = attempts;
            return this;
        }

        public Builder succeeded(long succeeded) {
            this.succeeded = succeeded;
            return this;
        }

        public Builder failed(long failed) {
            this.failed = failed;
            return this;
        }

        public Builder clientErrors(long clientErrors) {
            this.clientErrors = clientErrors;
            return this;
        }

        public Builder serverErrors(long serverErrors) {
            this.serverErrors = serverErrors;
            return this;
        }

        public Builder replays(long replays) {
            this.replays = replays;
            return this;
        }

        public Builder incompleteWrites(long incompleteWrites) {
            this.incompleteWrites = incompleteWrites;
            return this;
        }

        public Builder unattributedKeys(long unattributedKeys) {
            this.unattributedKeys = unattributedKeys;
            return this;
        }

        /**
         * Continuous percentile over retained attempts. Omitted when there are no captured samples.
         */
        public Builder p50Ms(@Nullable Double p50Ms) {
            this.p50Ms = p50Ms;
            return this;
        }

        /**
         * Continuous percentile over retained attempts. Omitted when there are no captured samples.
         */
        public Builder p95Ms(@Nullable Double p95Ms) {
            this.p95Ms = p95Ms;
            return this;
        }

        /**
         * Continuous percentile over retained attempts. Omitted when there are no captured samples.
         */
        public Builder p99Ms(@Nullable Double p99Ms) {
            this.p99Ms = p99Ms;
            return this;
        }

        public OperationalAggregateBucket build() {
            return new OperationalAggregateBucket(
                    start,
                    attempts,
                    succeeded,
                    failed,
                    clientErrors,
                    serverErrors,
                    replays,
                    incompleteWrites,
                    unattributedKeys,
                    p50Ms,
                    p95Ms,
                    p99Ms);
        }
    }
}
