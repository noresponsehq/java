package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningCoverageSources
 *
 * <p>How many sources this search asked and how many answered, across the lookup stage (sources asked
 * whether an account exists for an identifier) and the platform stage (profiles read on each
 * platform). A source that answered may have found nothing; one that did not answer failed or ran out
 * of time.
 */
public class ScreeningCoverageSources {
    /**
     * Lookup sources plus platforms asked.
     */
    @JsonProperty("asked")
    private long asked;

    /**
     * Lookup sources plus platforms that answered.
     */
    @JsonProperty("answered")
    private long answered;

    @JsonProperty("lookups")
    private SourceCount lookups;

    @JsonProperty("platforms")
    private SourceCount platforms;

    @JsonCreator
    public ScreeningCoverageSources(
            @JsonProperty("asked") long asked,
            @JsonProperty("answered") long answered,
            @JsonProperty("lookups") @Nonnull SourceCount lookups,
            @JsonProperty("platforms") @Nonnull SourceCount platforms) {
        this.asked = asked;
        this.answered = answered;
        this.lookups =
                Optional.ofNullable(lookups).orElseThrow(() -> new IllegalArgumentException("lookups cannot be null"));
        this.platforms = Optional.ofNullable(platforms)
                .orElseThrow(() -> new IllegalArgumentException("platforms cannot be null"));
    }

    /**
     * Lookup sources plus platforms asked.
     */
    public long asked() {
        return this.asked;
    }

    /**
     * Lookup sources plus platforms that answered.
     */
    public long answered() {
        return this.answered;
    }

    public SourceCount lookups() {
        return this.lookups;
    }

    public SourceCount platforms() {
        return this.platforms;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Lookup sources plus platforms asked.
     */
    public ScreeningCoverageSources withAsked(long asked) {
        this.asked = asked;
        return this;
    }

    /**
     * Lookup sources plus platforms that answered.
     */
    public ScreeningCoverageSources withAnswered(long answered) {
        this.answered = answered;
        return this;
    }

    public ScreeningCoverageSources withLookups(@Nonnull SourceCount lookups) {
        this.lookups = Utils.checkNotNull(lookups, "lookups");
        return this;
    }

    public ScreeningCoverageSources withPlatforms(@Nonnull SourceCount platforms) {
        this.platforms = Utils.checkNotNull(platforms, "platforms");
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
        ScreeningCoverageSources other = (ScreeningCoverageSources) o;
        return Utils.enhancedDeepEquals(this.asked, other.asked)
                && Utils.enhancedDeepEquals(this.answered, other.answered)
                && Utils.enhancedDeepEquals(this.lookups, other.lookups)
                && Utils.enhancedDeepEquals(this.platforms, other.platforms);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(asked, answered, lookups, platforms);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningCoverageSources.class,
                "asked",
                asked,
                "answered",
                answered,
                "lookups",
                lookups,
                "platforms",
                platforms);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long asked;

        private long answered;

        private SourceCount lookups;

        private SourceCount platforms;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Lookup sources plus platforms asked.
         */
        public Builder asked(long asked) {
            this.asked = asked;
            return this;
        }

        /**
         * Lookup sources plus platforms that answered.
         */
        public Builder answered(long answered) {
            this.answered = answered;
            return this;
        }

        public Builder lookups(@Nonnull SourceCount lookups) {
            this.lookups = Utils.checkNotNull(lookups, "lookups");
            return this;
        }

        public Builder platforms(@Nonnull SourceCount platforms) {
            this.platforms = Utils.checkNotNull(platforms, "platforms");
            return this;
        }

        public ScreeningCoverageSources build() {
            return new ScreeningCoverageSources(asked, answered, lookups, platforms);
        }
    }
}
