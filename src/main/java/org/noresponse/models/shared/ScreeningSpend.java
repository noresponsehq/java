package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningSpend
 *
 * <p>What screening cost, in US cents, in total and per implementation.
 */
public class ScreeningSpend {
    /**
     * The total, in US cents.
     */
    @JsonProperty("total_minor")
    private long totalMinor;

    @JsonProperty("by_implementation")
    private List<ScreeningSpendLine> byImplementation;

    @JsonCreator
    public ScreeningSpend(
            @JsonProperty("total_minor") long totalMinor,
            @JsonProperty("by_implementation") @Nonnull List<ScreeningSpendLine> byImplementation) {
        this.totalMinor = totalMinor;
        this.byImplementation = Optional.ofNullable(byImplementation)
                .orElseThrow(() -> new IllegalArgumentException("byImplementation cannot be null"));
    }

    /**
     * The total, in US cents.
     */
    public long totalMinor() {
        return this.totalMinor;
    }

    public List<ScreeningSpendLine> byImplementation() {
        return this.byImplementation;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The total, in US cents.
     */
    public ScreeningSpend withTotalMinor(long totalMinor) {
        this.totalMinor = totalMinor;
        return this;
    }

    public ScreeningSpend withByImplementation(@Nonnull List<ScreeningSpendLine> byImplementation) {
        this.byImplementation = Utils.checkNotNull(byImplementation, "byImplementation");
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
        ScreeningSpend other = (ScreeningSpend) o;
        return Utils.enhancedDeepEquals(this.totalMinor, other.totalMinor)
                && Utils.enhancedDeepEquals(this.byImplementation, other.byImplementation);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(totalMinor, byImplementation);
    }

    @Override
    public String toString() {
        return Utils.toString(ScreeningSpend.class, "totalMinor", totalMinor, "byImplementation", byImplementation);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long totalMinor;

        private List<ScreeningSpendLine> byImplementation;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The total, in US cents.
         */
        public Builder totalMinor(long totalMinor) {
            this.totalMinor = totalMinor;
            return this;
        }

        public Builder byImplementation(@Nonnull List<ScreeningSpendLine> byImplementation) {
            this.byImplementation = Utils.checkNotNull(byImplementation, "byImplementation");
            return this;
        }

        public ScreeningSpend build() {
            return new ScreeningSpend(totalMinor, byImplementation);
        }
    }
}
