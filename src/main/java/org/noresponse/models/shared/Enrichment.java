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
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * Enrichment
 *
 * <p>How enrichment searches the values that a workspace person's own investigation (POST
 * /v1/investigations) and a group's screening find. Defaults: mode automatic, min_likelihood 0.5,
 * kinds username, max_spend null and deadline null. Group screening follows the same setting, but
 * takes mode suggest while the workspace has not set a mode.
 *
 * <p>An investigation request may override each field for that investigation. A change applies to the
 * next search enrichment starts, investigations already run included. Enrichment also keeps to limits
 * of its own: each value once per person (once more if that search failed), at most 5 searches from
 * one search's records, 20 per person and 6 searches deep.
 */
public class Enrichment {
    /**
     * off: enrichment starts no search and suggests none. suggest: enrichment starts no search by itself,
     * and the person report lists the searches it would start in suggested_searches. automatic: after a
     * workspace person's own investigation or a group's screening, and each time a search on that person
     * finishes, enrichment starts searches by itself.
     *
     * <p>A group takes suggest while the workspace has not set a mode.
     */
    @JsonProperty("mode")
    private EnrichmentMode mode;

    /**
     * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
     */
    @JsonProperty("min_likelihood")
    private double minLikelihood;

    /**
     * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
     * numbers, which belong to one person each.
     */
    @JsonProperty("kinds")
    private List<EnrichmentKind> kinds;

    /**
     * The most enrichment may spend on a person from one investigation, in minor units of USD (cents):
     * each search it started after that investigation started counts its estimate or what it spent,
     * whichever is more. At the bound enrichment starts nothing more for that investigation. null: only
     * the environment's screening_daily_cap bounds it.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("max_spend")
    private JsonNullable<Long> maxSpend;

    /**
     * How long enrichment may start searches after an investigation starts, 60 to 2592000 seconds. null:
     * no deadline.
     */
    @JsonInclude(Include.ALWAYS)
    @JsonProperty("deadline")
    private JsonNullable<Long> deadline;

    @JsonCreator
    public Enrichment(
            @JsonProperty("mode") @Nonnull EnrichmentMode mode,
            @JsonProperty("min_likelihood") double minLikelihood,
            @JsonProperty("kinds") @Nonnull List<EnrichmentKind> kinds,
            @JsonProperty("max_spend") @Nullable Long maxSpend,
            @JsonProperty("deadline") @Nullable Long deadline) {
        this.mode = Optional.ofNullable(mode).orElseThrow(() -> new IllegalArgumentException("mode cannot be null"));
        this.minLikelihood = minLikelihood;
        this.kinds = Optional.ofNullable(kinds).orElseThrow(() -> new IllegalArgumentException("kinds cannot be null"));
        this.maxSpend = JsonNullable.of(maxSpend);
        this.deadline = JsonNullable.of(deadline);
    }

    public Enrichment(@Nonnull EnrichmentMode mode, double minLikelihood, @Nonnull List<EnrichmentKind> kinds) {
        this(mode, minLikelihood, kinds, null, null);
    }

    /**
     * off: enrichment starts no search and suggests none. suggest: enrichment starts no search by itself,
     * and the person report lists the searches it would start in suggested_searches. automatic: after a
     * workspace person's own investigation or a group's screening, and each time a search on that person
     * finishes, enrichment starts searches by itself.
     *
     * <p>A group takes suggest while the workspace has not set a mode.
     */
    public EnrichmentMode mode() {
        return this.mode;
    }

    /**
     * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
     */
    public double minLikelihood() {
        return this.minLikelihood;
    }

    /**
     * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
     * numbers, which belong to one person each.
     */
    public List<EnrichmentKind> kinds() {
        return this.kinds;
    }

    /**
     * The most enrichment may spend on a person from one investigation, in minor units of USD (cents):
     * each search it started after that investigation started counts its estimate or what it spent,
     * whichever is more. At the bound enrichment starts nothing more for that investigation. null: only
     * the environment's screening_daily_cap bounds it.
     */
    public JsonNullable<Long> maxSpend() {
        return this.maxSpend;
    }

    /**
     * How long enrichment may start searches after an investigation starts, 60 to 2592000 seconds. null:
     * no deadline.
     */
    public JsonNullable<Long> deadline() {
        return this.deadline;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * off: enrichment starts no search and suggests none. suggest: enrichment starts no search by itself,
     * and the person report lists the searches it would start in suggested_searches. automatic: after a
     * workspace person's own investigation or a group's screening, and each time a search on that person
     * finishes, enrichment starts searches by itself.
     *
     * <p>A group takes suggest while the workspace has not set a mode.
     */
    public Enrichment withMode(@Nonnull EnrichmentMode mode) {
        this.mode = Utils.checkNotNull(mode, "mode");
        return this;
    }

    /**
     * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
     */
    public Enrichment withMinLikelihood(double minLikelihood) {
        this.minLikelihood = minLikelihood;
        return this;
    }

    /**
     * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
     * numbers, which belong to one person each.
     */
    public Enrichment withKinds(@Nonnull List<EnrichmentKind> kinds) {
        this.kinds = Utils.checkNotNull(kinds, "kinds");
        return this;
    }

    /**
     * The most enrichment may spend on a person from one investigation, in minor units of USD (cents):
     * each search it started after that investigation started counts its estimate or what it spent,
     * whichever is more. At the bound enrichment starts nothing more for that investigation. null: only
     * the environment's screening_daily_cap bounds it.
     */
    public Enrichment withMaxSpend(@Nullable Long maxSpend) {
        this.maxSpend = JsonNullable.of(maxSpend);
        return this;
    }

    /**
     * How long enrichment may start searches after an investigation starts, 60 to 2592000 seconds. null:
     * no deadline.
     */
    public Enrichment withDeadline(@Nullable Long deadline) {
        this.deadline = JsonNullable.of(deadline);
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
        Enrichment other = (Enrichment) o;
        return Utils.enhancedDeepEquals(this.mode, other.mode)
                && Utils.enhancedDeepEquals(this.minLikelihood, other.minLikelihood)
                && Utils.enhancedDeepEquals(this.kinds, other.kinds)
                && Utils.enhancedDeepEquals(this.maxSpend, other.maxSpend)
                && Utils.enhancedDeepEquals(this.deadline, other.deadline);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(mode, minLikelihood, kinds, maxSpend, deadline);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Enrichment.class,
                "mode",
                mode,
                "minLikelihood",
                minLikelihood,
                "kinds",
                kinds,
                "maxSpend",
                maxSpend,
                "deadline",
                deadline);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private EnrichmentMode mode;

        private double minLikelihood;

        private List<EnrichmentKind> kinds;

        private Long maxSpend;

        private Long deadline;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * off: enrichment starts no search and suggests none. suggest: enrichment starts no search by itself,
         * and the person report lists the searches it would start in suggested_searches. automatic: after a
         * workspace person's own investigation or a group's screening, and each time a search on that person
         * finishes, enrichment starts searches by itself.
         *
         * <p>A group takes suggest while the workspace has not set a mode.
         */
        public Builder mode(@Nonnull EnrichmentMode mode) {
            this.mode = Utils.checkNotNull(mode, "mode");
            return this;
        }

        /**
         * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
         */
        public Builder minLikelihood(double minLikelihood) {
            this.minLikelihood = minLikelihood;
            return this;
        }

        /**
         * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
         * numbers, which belong to one person each.
         */
        public Builder kinds(@Nonnull List<EnrichmentKind> kinds) {
            this.kinds = Utils.checkNotNull(kinds, "kinds");
            return this;
        }

        /**
         * The most enrichment may spend on a person from one investigation, in minor units of USD (cents):
         * each search it started after that investigation started counts its estimate or what it spent,
         * whichever is more. At the bound enrichment starts nothing more for that investigation. null: only
         * the environment's screening_daily_cap bounds it.
         */
        public Builder maxSpend(@Nullable Long maxSpend) {
            this.maxSpend = maxSpend;
            return this;
        }

        /**
         * How long enrichment may start searches after an investigation starts, 60 to 2592000 seconds. null:
         * no deadline.
         */
        public Builder deadline(@Nullable Long deadline) {
            this.deadline = deadline;
            return this;
        }

        public Enrichment build() {
            return new Enrichment(mode, minLikelihood, kinds, maxSpend, deadline);
        }
    }
}
