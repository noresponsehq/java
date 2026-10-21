package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class RiskSummaryPhone {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("line_type")
    private LineType lineType;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("country")
    private String country;

    /**
     * Highest report count any source gave for the number.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("report_count")
    private Long reportCount;

    /**
     * Carrier-network metadata from privately weighted sources; not upstream provider attribution.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("carrier")
    private String carrier;

    @JsonCreator
    public RiskSummaryPhone(
            @JsonProperty("line_type") @Nullable LineType lineType,
            @JsonProperty("country") @Nullable String country,
            @JsonProperty("report_count") @Nullable Long reportCount,
            @JsonProperty("carrier") @Nullable String carrier) {
        this.lineType = lineType;
        this.country = country;
        this.reportCount = reportCount;
        this.carrier = carrier;
    }

    public RiskSummaryPhone() {
        this(null, null, null, null);
    }

    public Optional<LineType> lineType() {
        return Optional.ofNullable(this.lineType);
    }

    public Optional<String> country() {
        return Optional.ofNullable(this.country);
    }

    /**
     * Highest report count any source gave for the number.
     */
    public Optional<Long> reportCount() {
        return Optional.ofNullable(this.reportCount);
    }

    /**
     * Carrier-network metadata from privately weighted sources; not upstream provider attribution.
     */
    public Optional<String> carrier() {
        return Optional.ofNullable(this.carrier);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskSummaryPhone withLineType(@Nullable LineType lineType) {
        this.lineType = lineType;
        return this;
    }

    public RiskSummaryPhone withCountry(@Nullable String country) {
        this.country = country;
        return this;
    }

    /**
     * Highest report count any source gave for the number.
     */
    public RiskSummaryPhone withReportCount(@Nullable Long reportCount) {
        this.reportCount = reportCount;
        return this;
    }

    /**
     * Carrier-network metadata from privately weighted sources; not upstream provider attribution.
     */
    public RiskSummaryPhone withCarrier(@Nullable String carrier) {
        this.carrier = carrier;
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
        RiskSummaryPhone other = (RiskSummaryPhone) o;
        return Utils.enhancedDeepEquals(this.lineType, other.lineType)
                && Utils.enhancedDeepEquals(this.country, other.country)
                && Utils.enhancedDeepEquals(this.reportCount, other.reportCount)
                && Utils.enhancedDeepEquals(this.carrier, other.carrier);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(lineType, country, reportCount, carrier);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RiskSummaryPhone.class,
                "lineType",
                lineType,
                "country",
                country,
                "reportCount",
                reportCount,
                "carrier",
                carrier);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private LineType lineType;

        private String country;

        private Long reportCount;

        private String carrier;

        private Builder() {
            // force use of static builder() method
        }

        public Builder lineType(@Nullable LineType lineType) {
            this.lineType = lineType;
            return this;
        }

        public Builder country(@Nullable String country) {
            this.country = country;
            return this;
        }

        /**
         * Highest report count any source gave for the number.
         */
        public Builder reportCount(@Nullable Long reportCount) {
            this.reportCount = reportCount;
            return this;
        }

        /**
         * Carrier-network metadata from privately weighted sources; not upstream provider attribution.
         */
        public Builder carrier(@Nullable String carrier) {
            this.carrier = carrier;
            return this;
        }

        public RiskSummaryPhone build() {
            return new RiskSummaryPhone(lineType, country, reportCount, carrier);
        }
    }
}
