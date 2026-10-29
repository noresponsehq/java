package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ConsoleLookups
 *
 * <p>The live environment's daily cap on assessments members make through the console (the playground),
 * as spent on the current UTC day. Each console submission with a new Idempotency-Key spends one
 * lookup; a replay spends nothing, and API keys and the test environment spend none. When used reaches
 * limit, the next lookup answers 429 lookup_limit_reached until resets_at.
 *
 * <p>The count is the one the cap enforces.
 */
public class ConsoleLookups {
    /**
     * Lookups spent since the last reset.
     */
    @JsonProperty("used")
    private long used;

    /**
     * Lookups allowed per UTC day.
     */
    @JsonProperty("limit")
    private long limit;

    /**
     * The next reset: the coming midnight UTC, when used returns to 0.
     */
    @JsonProperty("resets_at")
    private String resetsAt;

    @JsonCreator
    public ConsoleLookups(
            @JsonProperty("used") long used,
            @JsonProperty("limit") long limit,
            @JsonProperty("resets_at") @Nonnull String resetsAt) {
        this.used = used;
        this.limit = limit;
        this.resetsAt = Optional.ofNullable(resetsAt)
                .orElseThrow(() -> new IllegalArgumentException("resetsAt cannot be null"));
    }

    /**
     * Lookups spent since the last reset.
     */
    public long used() {
        return this.used;
    }

    /**
     * Lookups allowed per UTC day.
     */
    public long limit() {
        return this.limit;
    }

    /**
     * The next reset: the coming midnight UTC, when used returns to 0.
     */
    public String resetsAt() {
        return this.resetsAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Lookups spent since the last reset.
     */
    public ConsoleLookups withUsed(long used) {
        this.used = used;
        return this;
    }

    /**
     * Lookups allowed per UTC day.
     */
    public ConsoleLookups withLimit(long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * The next reset: the coming midnight UTC, when used returns to 0.
     */
    public ConsoleLookups withResetsAt(@Nonnull String resetsAt) {
        this.resetsAt = Utils.checkNotNull(resetsAt, "resetsAt");
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
        ConsoleLookups other = (ConsoleLookups) o;
        return Utils.enhancedDeepEquals(this.used, other.used)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.resetsAt, other.resetsAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(used, limit, resetsAt);
    }

    @Override
    public String toString() {
        return Utils.toString(ConsoleLookups.class, "used", used, "limit", limit, "resetsAt", resetsAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long used;

        private long limit;

        private String resetsAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Lookups spent since the last reset.
         */
        public Builder used(long used) {
            this.used = used;
            return this;
        }

        /**
         * Lookups allowed per UTC day.
         */
        public Builder limit(long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * The next reset: the coming midnight UTC, when used returns to 0.
         */
        public Builder resetsAt(@Nonnull String resetsAt) {
            this.resetsAt = Utils.checkNotNull(resetsAt, "resetsAt");
            return this;
        }

        public ConsoleLookups build() {
            return new ConsoleLookups(used, limit, resetsAt);
        }
    }
}
