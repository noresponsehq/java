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

/**
 * FootprintTimeline
 *
 * <p>One identifier's dated footprint across every source: the first and last year any event was seen,
 * the years between them, the latest year a source dated activity (omitted when none did), how many
 * distinct sites and which kinds of evidence saw it.
 */
public class FootprintTimeline {

    @JsonProperty("first_seen_year")
    private long firstSeenYear;

    @JsonProperty("last_seen_year")
    private long lastSeenYear;

    @JsonProperty("span_years")
    private long spanYears;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("last_active_year")
    private Long lastActiveYear;

    @JsonProperty("sites")
    private long sites;

    @JsonProperty("families")
    private List<FootprintFamily> families;

    @JsonCreator
    public FootprintTimeline(
            @JsonProperty("first_seen_year") long firstSeenYear,
            @JsonProperty("last_seen_year") long lastSeenYear,
            @JsonProperty("span_years") long spanYears,
            @JsonProperty("last_active_year") @Nullable Long lastActiveYear,
            @JsonProperty("sites") long sites,
            @JsonProperty("families") @Nonnull List<FootprintFamily> families) {
        this.firstSeenYear = firstSeenYear;
        this.lastSeenYear = lastSeenYear;
        this.spanYears = spanYears;
        this.lastActiveYear = lastActiveYear;
        this.sites = sites;
        this.families = Optional.ofNullable(families)
                .orElseThrow(() -> new IllegalArgumentException("families cannot be null"));
    }

    public FootprintTimeline(
            long firstSeenYear,
            long lastSeenYear,
            long spanYears,
            long sites,
            @Nonnull List<FootprintFamily> families) {
        this(firstSeenYear, lastSeenYear, spanYears, null, sites, families);
    }

    public long firstSeenYear() {
        return this.firstSeenYear;
    }

    public long lastSeenYear() {
        return this.lastSeenYear;
    }

    public long spanYears() {
        return this.spanYears;
    }

    public Optional<Long> lastActiveYear() {
        return Optional.ofNullable(this.lastActiveYear);
    }

    public long sites() {
        return this.sites;
    }

    public List<FootprintFamily> families() {
        return this.families;
    }

    public static Builder builder() {
        return new Builder();
    }

    public FootprintTimeline withFirstSeenYear(long firstSeenYear) {
        this.firstSeenYear = firstSeenYear;
        return this;
    }

    public FootprintTimeline withLastSeenYear(long lastSeenYear) {
        this.lastSeenYear = lastSeenYear;
        return this;
    }

    public FootprintTimeline withSpanYears(long spanYears) {
        this.spanYears = spanYears;
        return this;
    }

    public FootprintTimeline withLastActiveYear(@Nullable Long lastActiveYear) {
        this.lastActiveYear = lastActiveYear;
        return this;
    }

    public FootprintTimeline withSites(long sites) {
        this.sites = sites;
        return this;
    }

    public FootprintTimeline withFamilies(@Nonnull List<FootprintFamily> families) {
        this.families = Utils.checkNotNull(families, "families");
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
        FootprintTimeline other = (FootprintTimeline) o;
        return Utils.enhancedDeepEquals(this.firstSeenYear, other.firstSeenYear)
                && Utils.enhancedDeepEquals(this.lastSeenYear, other.lastSeenYear)
                && Utils.enhancedDeepEquals(this.spanYears, other.spanYears)
                && Utils.enhancedDeepEquals(this.lastActiveYear, other.lastActiveYear)
                && Utils.enhancedDeepEquals(this.sites, other.sites)
                && Utils.enhancedDeepEquals(this.families, other.families);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(firstSeenYear, lastSeenYear, spanYears, lastActiveYear, sites, families);
    }

    @Override
    public String toString() {
        return Utils.toString(
                FootprintTimeline.class,
                "firstSeenYear",
                firstSeenYear,
                "lastSeenYear",
                lastSeenYear,
                "spanYears",
                spanYears,
                "lastActiveYear",
                lastActiveYear,
                "sites",
                sites,
                "families",
                families);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long firstSeenYear;

        private long lastSeenYear;

        private long spanYears;

        private Long lastActiveYear;

        private long sites;

        private List<FootprintFamily> families;

        private Builder() {
            // force use of static builder() method
        }

        public Builder firstSeenYear(long firstSeenYear) {
            this.firstSeenYear = firstSeenYear;
            return this;
        }

        public Builder lastSeenYear(long lastSeenYear) {
            this.lastSeenYear = lastSeenYear;
            return this;
        }

        public Builder spanYears(long spanYears) {
            this.spanYears = spanYears;
            return this;
        }

        public Builder lastActiveYear(@Nullable Long lastActiveYear) {
            this.lastActiveYear = lastActiveYear;
            return this;
        }

        public Builder sites(long sites) {
            this.sites = sites;
            return this;
        }

        public Builder families(@Nonnull List<FootprintFamily> families) {
            this.families = Utils.checkNotNull(families, "families");
            return this;
        }

        public FootprintTimeline build() {
            return new FootprintTimeline(firstSeenYear, lastSeenYear, spanYears, lastActiveYear, sites, families);
        }
    }
}
