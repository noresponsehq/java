package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * RiskDetails
 *
 * <p>Detailed view only (response_mode=detailed, which needs the detailed scope); a standard answer
 * leaves it out. Merged observations without provider, source, origin, raw breach record, value or
 * score.
 */
public class RiskDetails {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone_names")
    private List<RiskDetailsPhoneName> phoneNames;

    /**
     * The matching breach tables, newest first, at most 100.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("breaches")
    private List<Breach> breaches;

    /**
     * Names found next to the identifiers in breach records, merged, with the number of distinct tables
     * each was seen in and the sites of those tables.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("breach_names")
    private List<RiskDetailsBreachName> breachNames;

    /**
     * The identity's dated footprint events, newest first, at most 300: the year and month, what happened
     * (created, last_active, breached), the site, its category, the kind of evidence and the identifier it
     * belongs to. No breach value: dates are coarsened to the month.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("events")
    private List<RiskDetailsEvent> events;

    @JsonCreator
    public RiskDetails(
            @JsonProperty("phone_names") @Nullable List<RiskDetailsPhoneName> phoneNames,
            @JsonProperty("breaches") @Nullable List<Breach> breaches,
            @JsonProperty("breach_names") @Nullable List<RiskDetailsBreachName> breachNames,
            @JsonProperty("events") @Nullable List<RiskDetailsEvent> events) {
        this.phoneNames = phoneNames;
        this.breaches = breaches;
        this.breachNames = breachNames;
        this.events = events;
    }

    public RiskDetails() {
        this(null, null, null, null);
    }

    public Optional<List<RiskDetailsPhoneName>> phoneNames() {
        return Optional.ofNullable(this.phoneNames);
    }

    /**
     * The matching breach tables, newest first, at most 100.
     */
    public Optional<List<Breach>> breaches() {
        return Optional.ofNullable(this.breaches);
    }

    /**
     * Names found next to the identifiers in breach records, merged, with the number of distinct tables
     * each was seen in and the sites of those tables.
     */
    public Optional<List<RiskDetailsBreachName>> breachNames() {
        return Optional.ofNullable(this.breachNames);
    }

    /**
     * The identity's dated footprint events, newest first, at most 300: the year and month, what happened
     * (created, last_active, breached), the site, its category, the kind of evidence and the identifier it
     * belongs to. No breach value: dates are coarsened to the month.
     */
    public Optional<List<RiskDetailsEvent>> events() {
        return Optional.ofNullable(this.events);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskDetails withPhoneNames(@Nullable List<RiskDetailsPhoneName> phoneNames) {
        this.phoneNames = phoneNames;
        return this;
    }

    /**
     * The matching breach tables, newest first, at most 100.
     */
    public RiskDetails withBreaches(@Nullable List<Breach> breaches) {
        this.breaches = breaches;
        return this;
    }

    /**
     * Names found next to the identifiers in breach records, merged, with the number of distinct tables
     * each was seen in and the sites of those tables.
     */
    public RiskDetails withBreachNames(@Nullable List<RiskDetailsBreachName> breachNames) {
        this.breachNames = breachNames;
        return this;
    }

    /**
     * The identity's dated footprint events, newest first, at most 300: the year and month, what happened
     * (created, last_active, breached), the site, its category, the kind of evidence and the identifier it
     * belongs to. No breach value: dates are coarsened to the month.
     */
    public RiskDetails withEvents(@Nullable List<RiskDetailsEvent> events) {
        this.events = events;
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
        RiskDetails other = (RiskDetails) o;
        return Utils.enhancedDeepEquals(this.phoneNames, other.phoneNames)
                && Utils.enhancedDeepEquals(this.breaches, other.breaches)
                && Utils.enhancedDeepEquals(this.breachNames, other.breachNames)
                && Utils.enhancedDeepEquals(this.events, other.events);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(phoneNames, breaches, breachNames, events);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RiskDetails.class,
                "phoneNames",
                phoneNames,
                "breaches",
                breaches,
                "breachNames",
                breachNames,
                "events",
                events);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<RiskDetailsPhoneName> phoneNames;

        private List<Breach> breaches;

        private List<RiskDetailsBreachName> breachNames;

        private List<RiskDetailsEvent> events;

        private Builder() {
            // force use of static builder() method
        }

        public Builder phoneNames(@Nullable List<RiskDetailsPhoneName> phoneNames) {
            this.phoneNames = phoneNames;
            return this;
        }

        /**
         * The matching breach tables, newest first, at most 100.
         */
        public Builder breaches(@Nullable List<Breach> breaches) {
            this.breaches = breaches;
            return this;
        }

        /**
         * Names found next to the identifiers in breach records, merged, with the number of distinct tables
         * each was seen in and the sites of those tables.
         */
        public Builder breachNames(@Nullable List<RiskDetailsBreachName> breachNames) {
            this.breachNames = breachNames;
            return this;
        }

        /**
         * The identity's dated footprint events, newest first, at most 300: the year and month, what happened
         * (created, last_active, breached), the site, its category, the kind of evidence and the identifier it
         * belongs to. No breach value: dates are coarsened to the month.
         */
        public Builder events(@Nullable List<RiskDetailsEvent> events) {
            this.events = events;
            return this;
        }

        public RiskDetails build() {
            return new RiskDetails(phoneNames, breaches, breachNames, events);
        }
    }
}
