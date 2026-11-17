package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Double;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * EnrichmentChange
 *
 * <p>A change to enrichment. A field left out keeps the value in force; max_spend or deadline null
 * removes the bound.
 */
public class EnrichmentChange {
    /**
     * off: enrichment starts no search. suggest: enrichment starts no search by itself. automatic: after a
     * workspace person's own investigation, and each time a search on that person finishes, enrichment
     * starts searches by itself.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("mode")
    private EnrichmentMode mode;

    /**
     * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("min_likelihood")
    private Double minLikelihood;

    /**
     * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
     * numbers, which belong to one person each.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("kinds")
    private List<EnrichmentChangeKind> kinds;

    /**
     * The most enrichment may spend on a person from one investigation, in minor units of USD (cents):
     * each search it started after that investigation started counts its estimate or what it spent,
     * whichever is more. At the bound enrichment starts nothing more for that investigation. null: only
     * the environment's screening_daily_cap bounds it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("max_spend")
    private JsonNullable<Long> maxSpend;

    /**
     * How long enrichment may start searches after an investigation starts, 60 to 2592000 seconds. null:
     * no deadline.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("deadline")
    private JsonNullable<Long> deadline;

    @JsonCreator
    public EnrichmentChange(
            @JsonProperty("mode") @Nullable EnrichmentMode mode,
            @JsonProperty("min_likelihood") @Nullable Double minLikelihood,
            @JsonProperty("kinds") @Nullable List<EnrichmentChangeKind> kinds,
            @JsonProperty("max_spend") @Nullable JsonNullable<Long> maxSpend,
            @JsonProperty("deadline") @Nullable JsonNullable<Long> deadline) {
        this.mode = mode;
        this.minLikelihood = minLikelihood;
        this.kinds = kinds;
        this.maxSpend = Optional.ofNullable(maxSpend).orElse(JsonNullable.undefined());
        this.deadline = Optional.ofNullable(deadline).orElse(JsonNullable.undefined());
    }

    public EnrichmentChange() {
        this(null, null, null, null, null);
    }

    /**
     * off: enrichment starts no search. suggest: enrichment starts no search by itself. automatic: after a
     * workspace person's own investigation, and each time a search on that person finishes, enrichment
     * starts searches by itself.
     */
    public Optional<EnrichmentMode> mode() {
        return Optional.ofNullable(this.mode);
    }

    /**
     * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
     */
    public Optional<Double> minLikelihood() {
        return Optional.ofNullable(this.minLikelihood);
    }

    /**
     * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
     * numbers, which belong to one person each.
     */
    public Optional<List<EnrichmentChangeKind>> kinds() {
        return Optional.ofNullable(this.kinds);
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
     * off: enrichment starts no search. suggest: enrichment starts no search by itself. automatic: after a
     * workspace person's own investigation, and each time a search on that person finishes, enrichment
     * starts searches by itself.
     */
    public EnrichmentChange withMode(@Nullable EnrichmentMode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
     */
    public EnrichmentChange withMinLikelihood(@Nullable Double minLikelihood) {
        this.minLikelihood = minLikelihood;
        return this;
    }

    /**
     * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
     * numbers, which belong to one person each.
     */
    public EnrichmentChange withKinds(@Nullable List<EnrichmentChangeKind> kinds) {
        this.kinds = kinds;
        return this;
    }

    /**
     * The most enrichment may spend on a person from one investigation, in minor units of USD (cents):
     * each search it started after that investigation started counts its estimate or what it spent,
     * whichever is more. At the bound enrichment starts nothing more for that investigation. null: only
     * the environment's screening_daily_cap bounds it.
     */
    public EnrichmentChange withMaxSpend(@Nullable Long maxSpend) {
        this.maxSpend = JsonNullable.of(maxSpend);
        return this;
    }

    /**
     * How long enrichment may start searches after an investigation starts, 60 to 2592000 seconds. null:
     * no deadline.
     */
    public EnrichmentChange withDeadline(@Nullable Long deadline) {
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
        EnrichmentChange other = (EnrichmentChange) o;
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
                EnrichmentChange.class,
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

        private Double minLikelihood;

        private List<EnrichmentChangeKind> kinds;

        private JsonNullable<Long> maxSpend;

        private JsonNullable<Long> deadline;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * off: enrichment starts no search. suggest: enrichment starts no search by itself. automatic: after a
         * workspace person's own investigation, and each time a search on that person finishes, enrichment
         * starts searches by itself.
         */
        public Builder mode(@Nullable EnrichmentMode mode) {
            this.mode = mode;
            return this;
        }

        /**
         * Enrichment searches values only from records more likely than this to be the person's, 0 to 0.99.
         */
        public Builder minLikelihood(@Nullable Double minLikelihood) {
            this.minLikelihood = minLikelihood;
            return this;
        }

        /**
         * The kinds of value enrichment searches: distinctive usernames, and email addresses and phone
         * numbers, which belong to one person each.
         */
        public Builder kinds(@Nullable List<EnrichmentChangeKind> kinds) {
            this.kinds = kinds;
            return this;
        }

        /**
         * The most enrichment may spend on a person from one investigation, in minor units of USD (cents):
         * each search it started after that investigation started counts its estimate or what it spent,
         * whichever is more. At the bound enrichment starts nothing more for that investigation. null: only
         * the environment's screening_daily_cap bounds it.
         */
        public Builder maxSpend(@Nullable Long maxSpend) {
            this.maxSpend = JsonNullable.of(maxSpend);
            return this;
        }

        /**
         * How long enrichment may start searches after an investigation starts, 60 to 2592000 seconds. null:
         * no deadline.
         */
        public Builder deadline(@Nullable Long deadline) {
            this.deadline = JsonNullable.of(deadline);
            return this;
        }

        public EnrichmentChange build() {
            return new EnrichmentChange(mode, minLikelihood, kinds, maxSpend, deadline);
        }
    }
}
