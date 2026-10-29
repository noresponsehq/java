package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * OperationalAggregateQueue
 *
 * <p>Current retained delivery queue, independent of the requested attempt window and status filter.
 * Endpoint filter applies. Does not prove customer enforcement execution.
 */
public class OperationalAggregateQueue {

    @JsonProperty("pending")
    private long pending;

    @JsonProperty("leased")
    private long leased;

    @JsonProperty("dead_retained")
    private long deadRetained;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("oldest_pending_at")
    private String oldestPendingAt;

    @JsonCreator
    public OperationalAggregateQueue(
            @JsonProperty("pending") long pending,
            @JsonProperty("leased") long leased,
            @JsonProperty("dead_retained") long deadRetained,
            @JsonProperty("oldest_pending_at") @Nullable String oldestPendingAt) {
        this.pending = pending;
        this.leased = leased;
        this.deadRetained = deadRetained;
        this.oldestPendingAt = oldestPendingAt;
    }

    public OperationalAggregateQueue(long pending, long leased, long deadRetained) {
        this(pending, leased, deadRetained, null);
    }

    public long pending() {
        return this.pending;
    }

    public long leased() {
        return this.leased;
    }

    public long deadRetained() {
        return this.deadRetained;
    }

    public Optional<String> oldestPendingAt() {
        return Optional.ofNullable(this.oldestPendingAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    public OperationalAggregateQueue withPending(long pending) {
        this.pending = pending;
        return this;
    }

    public OperationalAggregateQueue withLeased(long leased) {
        this.leased = leased;
        return this;
    }

    public OperationalAggregateQueue withDeadRetained(long deadRetained) {
        this.deadRetained = deadRetained;
        return this;
    }

    public OperationalAggregateQueue withOldestPendingAt(@Nullable String oldestPendingAt) {
        this.oldestPendingAt = oldestPendingAt;
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
        OperationalAggregateQueue other = (OperationalAggregateQueue) o;
        return Utils.enhancedDeepEquals(this.pending, other.pending)
                && Utils.enhancedDeepEquals(this.leased, other.leased)
                && Utils.enhancedDeepEquals(this.deadRetained, other.deadRetained)
                && Utils.enhancedDeepEquals(this.oldestPendingAt, other.oldestPendingAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(pending, leased, deadRetained, oldestPendingAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                OperationalAggregateQueue.class,
                "pending",
                pending,
                "leased",
                leased,
                "deadRetained",
                deadRetained,
                "oldestPendingAt",
                oldestPendingAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long pending;

        private long leased;

        private long deadRetained;

        private String oldestPendingAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder pending(long pending) {
            this.pending = pending;
            return this;
        }

        public Builder leased(long leased) {
            this.leased = leased;
            return this;
        }

        public Builder deadRetained(long deadRetained) {
            this.deadRetained = deadRetained;
            return this;
        }

        public Builder oldestPendingAt(@Nullable String oldestPendingAt) {
            this.oldestPendingAt = oldestPendingAt;
            return this;
        }

        public OperationalAggregateQueue build() {
            return new OperationalAggregateQueue(pending, leased, deadRetained, oldestPendingAt);
        }
    }
}
