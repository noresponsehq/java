package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * IncompleteSource
 *
 * <p>One evidence lane that did not settle, and why. It names a lane, not a source. A lane that missed
 * its deadline is timed_out rather than not_found.
 */
public class IncompleteSource {
    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    @JsonProperty("lane")
    private Lane lane;

    /**
     * Why a lane did not settle. deferred means the lane is asynchronous and still running; a continuation
     * settles it. timed_out means the lane missed its deadline.
     *
     * <p>insufficient_evidence is a settled inconclusive signal rather than an incomplete lane.
     */
    @JsonProperty("reason")
    private IncompleteReason reason;

    @JsonCreator
    public IncompleteSource(
            @JsonProperty("lane") @Nonnull Lane lane, @JsonProperty("reason") @Nonnull IncompleteReason reason) {
        this.lane = Optional.ofNullable(lane).orElseThrow(() -> new IllegalArgumentException("lane cannot be null"));
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public Lane lane() {
        return this.lane;
    }

    /**
     * Why a lane did not settle. deferred means the lane is asynchronous and still running; a continuation
     * settles it. timed_out means the lane missed its deadline.
     *
     * <p>insufficient_evidence is a settled inconclusive signal rather than an incomplete lane.
     */
    public IncompleteReason reason() {
        return this.reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public IncompleteSource withLane(@Nonnull Lane lane) {
        this.lane = Utils.checkNotNull(lane, "lane");
        return this;
    }

    /**
     * Why a lane did not settle. deferred means the lane is asynchronous and still running; a continuation
     * settles it. timed_out means the lane missed its deadline.
     *
     * <p>insufficient_evidence is a settled inconclusive signal rather than an incomplete lane.
     */
    public IncompleteSource withReason(@Nonnull IncompleteReason reason) {
        this.reason = Utils.checkNotNull(reason, "reason");
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
        IncompleteSource other = (IncompleteSource) o;
        return Utils.enhancedDeepEquals(this.lane, other.lane) && Utils.enhancedDeepEquals(this.reason, other.reason);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(lane, reason);
    }

    @Override
    public String toString() {
        return Utils.toString(IncompleteSource.class, "lane", lane, "reason", reason);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Lane lane;

        private IncompleteReason reason;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
         * email domain's registration) and ip.
         */
        public Builder lane(@Nonnull Lane lane) {
            this.lane = Utils.checkNotNull(lane, "lane");
            return this;
        }

        /**
         * Why a lane did not settle. deferred means the lane is asynchronous and still running; a continuation
         * settles it. timed_out means the lane missed its deadline.
         *
         * <p>insufficient_evidence is a settled inconclusive signal rather than an incomplete lane.
         */
        public Builder reason(@Nonnull IncompleteReason reason) {
            this.reason = Utils.checkNotNull(reason, "reason");
            return this;
        }

        public IncompleteSource build() {
            return new IncompleteSource(lane, reason);
        }
    }
}
