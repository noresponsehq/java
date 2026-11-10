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
 * ScreeningCoverage
 *
 * <p>The denominator of a screening: accounts found, how many could be read, items read and the period
 * they span, so a clean result is distinct from a person nobody could see.
 */
public class ScreeningCoverage {

    @JsonProperty("accounts_found")
    private long accountsFound;

    @JsonProperty("accounts_accessible")
    private long accountsAccessible;

    @JsonProperty("items_read")
    private long itemsRead;

    /**
     * Items read that the analyser could not analyse. Above zero, the attendee is partial: a finding may
     * be missing from them.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("items_not_analysed")
    private Long itemsNotAnalysed;

    /**
     * The oldest item read on any platform.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("period_from")
    private String periodFrom;

    /**
     * The newest item read on any platform.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("period_to")
    private String periodTo;

    @JsonProperty("platforms")
    private List<ScreeningPlatformCoverage> platforms;

    @JsonCreator
    public ScreeningCoverage(
            @JsonProperty("accounts_found") long accountsFound,
            @JsonProperty("accounts_accessible") long accountsAccessible,
            @JsonProperty("items_read") long itemsRead,
            @JsonProperty("items_not_analysed") @Nullable Long itemsNotAnalysed,
            @JsonProperty("period_from") @Nullable String periodFrom,
            @JsonProperty("period_to") @Nullable String periodTo,
            @JsonProperty("platforms") @Nonnull List<ScreeningPlatformCoverage> platforms) {
        this.accountsFound = accountsFound;
        this.accountsAccessible = accountsAccessible;
        this.itemsRead = itemsRead;
        this.itemsNotAnalysed = itemsNotAnalysed;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.platforms = Optional.ofNullable(platforms)
                .orElseThrow(() -> new IllegalArgumentException("platforms cannot be null"));
    }

    public ScreeningCoverage(
            long accountsFound,
            long accountsAccessible,
            long itemsRead,
            @Nonnull List<ScreeningPlatformCoverage> platforms) {
        this(accountsFound, accountsAccessible, itemsRead, null, null, null, platforms);
    }

    public long accountsFound() {
        return this.accountsFound;
    }

    public long accountsAccessible() {
        return this.accountsAccessible;
    }

    public long itemsRead() {
        return this.itemsRead;
    }

    /**
     * Items read that the analyser could not analyse. Above zero, the attendee is partial: a finding may
     * be missing from them.
     */
    public Optional<Long> itemsNotAnalysed() {
        return Optional.ofNullable(this.itemsNotAnalysed);
    }

    /**
     * The oldest item read on any platform.
     */
    public Optional<String> periodFrom() {
        return Optional.ofNullable(this.periodFrom);
    }

    /**
     * The newest item read on any platform.
     */
    public Optional<String> periodTo() {
        return Optional.ofNullable(this.periodTo);
    }

    public List<ScreeningPlatformCoverage> platforms() {
        return this.platforms;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningCoverage withAccountsFound(long accountsFound) {
        this.accountsFound = accountsFound;
        return this;
    }

    public ScreeningCoverage withAccountsAccessible(long accountsAccessible) {
        this.accountsAccessible = accountsAccessible;
        return this;
    }

    public ScreeningCoverage withItemsRead(long itemsRead) {
        this.itemsRead = itemsRead;
        return this;
    }

    /**
     * Items read that the analyser could not analyse. Above zero, the attendee is partial: a finding may
     * be missing from them.
     */
    public ScreeningCoverage withItemsNotAnalysed(@Nullable Long itemsNotAnalysed) {
        this.itemsNotAnalysed = itemsNotAnalysed;
        return this;
    }

    /**
     * The oldest item read on any platform.
     */
    public ScreeningCoverage withPeriodFrom(@Nullable String periodFrom) {
        this.periodFrom = periodFrom;
        return this;
    }

    /**
     * The newest item read on any platform.
     */
    public ScreeningCoverage withPeriodTo(@Nullable String periodTo) {
        this.periodTo = periodTo;
        return this;
    }

    public ScreeningCoverage withPlatforms(@Nonnull List<ScreeningPlatformCoverage> platforms) {
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
        ScreeningCoverage other = (ScreeningCoverage) o;
        return Utils.enhancedDeepEquals(this.accountsFound, other.accountsFound)
                && Utils.enhancedDeepEquals(this.accountsAccessible, other.accountsAccessible)
                && Utils.enhancedDeepEquals(this.itemsRead, other.itemsRead)
                && Utils.enhancedDeepEquals(this.itemsNotAnalysed, other.itemsNotAnalysed)
                && Utils.enhancedDeepEquals(this.periodFrom, other.periodFrom)
                && Utils.enhancedDeepEquals(this.periodTo, other.periodTo)
                && Utils.enhancedDeepEquals(this.platforms, other.platforms);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                accountsFound, accountsAccessible, itemsRead, itemsNotAnalysed, periodFrom, periodTo, platforms);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningCoverage.class,
                "accountsFound",
                accountsFound,
                "accountsAccessible",
                accountsAccessible,
                "itemsRead",
                itemsRead,
                "itemsNotAnalysed",
                itemsNotAnalysed,
                "periodFrom",
                periodFrom,
                "periodTo",
                periodTo,
                "platforms",
                platforms);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long accountsFound;

        private long accountsAccessible;

        private long itemsRead;

        private Long itemsNotAnalysed;

        private String periodFrom;

        private String periodTo;

        private List<ScreeningPlatformCoverage> platforms;

        private Builder() {
            // force use of static builder() method
        }

        public Builder accountsFound(long accountsFound) {
            this.accountsFound = accountsFound;
            return this;
        }

        public Builder accountsAccessible(long accountsAccessible) {
            this.accountsAccessible = accountsAccessible;
            return this;
        }

        public Builder itemsRead(long itemsRead) {
            this.itemsRead = itemsRead;
            return this;
        }

        /**
         * Items read that the analyser could not analyse. Above zero, the attendee is partial: a finding may
         * be missing from them.
         */
        public Builder itemsNotAnalysed(@Nullable Long itemsNotAnalysed) {
            this.itemsNotAnalysed = itemsNotAnalysed;
            return this;
        }

        /**
         * The oldest item read on any platform.
         */
        public Builder periodFrom(@Nullable String periodFrom) {
            this.periodFrom = periodFrom;
            return this;
        }

        /**
         * The newest item read on any platform.
         */
        public Builder periodTo(@Nullable String periodTo) {
            this.periodTo = periodTo;
            return this;
        }

        public Builder platforms(@Nonnull List<ScreeningPlatformCoverage> platforms) {
            this.platforms = Utils.checkNotNull(platforms, "platforms");
            return this;
        }

        public ScreeningCoverage build() {
            return new ScreeningCoverage(
                    accountsFound, accountsAccessible, itemsRead, itemsNotAnalysed, periodFrom, periodTo, platforms);
        }
    }
}
