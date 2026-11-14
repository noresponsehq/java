package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

/**
 * ScreeningCompletedByBand
 *
 * <p>Screened people by the band of their most severe finding.
 */
public class ScreeningCompletedByBand {

    @JsonProperty("critical")
    private long critical;

    @JsonProperty("high")
    private long high;

    @JsonProperty("moderate")
    private long moderate;

    @JsonProperty("low")
    private long low;

    @JsonProperty("info")
    private long info;

    @JsonCreator
    public ScreeningCompletedByBand(
            @JsonProperty("critical") long critical,
            @JsonProperty("high") long high,
            @JsonProperty("moderate") long moderate,
            @JsonProperty("low") long low,
            @JsonProperty("info") long info) {
        this.critical = critical;
        this.high = high;
        this.moderate = moderate;
        this.low = low;
        this.info = info;
    }

    public long critical() {
        return this.critical;
    }

    public long high() {
        return this.high;
    }

    public long moderate() {
        return this.moderate;
    }

    public long low() {
        return this.low;
    }

    public long info() {
        return this.info;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningCompletedByBand withCritical(long critical) {
        this.critical = critical;
        return this;
    }

    public ScreeningCompletedByBand withHigh(long high) {
        this.high = high;
        return this;
    }

    public ScreeningCompletedByBand withModerate(long moderate) {
        this.moderate = moderate;
        return this;
    }

    public ScreeningCompletedByBand withLow(long low) {
        this.low = low;
        return this;
    }

    public ScreeningCompletedByBand withInfo(long info) {
        this.info = info;
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
        ScreeningCompletedByBand other = (ScreeningCompletedByBand) o;
        return Utils.enhancedDeepEquals(this.critical, other.critical)
                && Utils.enhancedDeepEquals(this.high, other.high)
                && Utils.enhancedDeepEquals(this.moderate, other.moderate)
                && Utils.enhancedDeepEquals(this.low, other.low)
                && Utils.enhancedDeepEquals(this.info, other.info);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(critical, high, moderate, low, info);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningCompletedByBand.class,
                "critical",
                critical,
                "high",
                high,
                "moderate",
                moderate,
                "low",
                low,
                "info",
                info);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long critical;

        private long high;

        private long moderate;

        private long low;

        private long info;

        private Builder() {
            // force use of static builder() method
        }

        public Builder critical(long critical) {
            this.critical = critical;
            return this;
        }

        public Builder high(long high) {
            this.high = high;
            return this;
        }

        public Builder moderate(long moderate) {
            this.moderate = moderate;
            return this;
        }

        public Builder low(long low) {
            this.low = low;
            return this;
        }

        public Builder info(long info) {
            this.info = info;
            return this;
        }

        public ScreeningCompletedByBand build() {
            return new ScreeningCompletedByBand(critical, high, moderate, low, info);
        }
    }
}
