package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PersonScheduleRequest
 *
 * <p>The schedule to set.
 */
public class PersonScheduleRequest {
    /**
     * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
     * was set; off runs no scheduled investigation.
     */
    @JsonProperty("period")
    private SchedulePeriod period;

    @JsonCreator
    public PersonScheduleRequest(@JsonProperty("period") @Nonnull SchedulePeriod period) {
        this.period =
                Optional.ofNullable(period).orElseThrow(() -> new IllegalArgumentException("period cannot be null"));
    }

    /**
     * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
     * was set; off runs no scheduled investigation.
     */
    public SchedulePeriod period() {
        return this.period;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
     * was set; off runs no scheduled investigation.
     */
    public PersonScheduleRequest withPeriod(@Nonnull SchedulePeriod period) {
        this.period = Utils.checkNotNull(period, "period");
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
        PersonScheduleRequest other = (PersonScheduleRequest) o;
        return Utils.enhancedDeepEquals(this.period, other.period);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(period);
    }

    @Override
    public String toString() {
        return Utils.toString(PersonScheduleRequest.class, "period", period);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private SchedulePeriod period;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
         * was set; off runs no scheduled investigation.
         */
        public Builder period(@Nonnull SchedulePeriod period) {
            this.period = Utils.checkNotNull(period, "period");
            return this;
        }

        public PersonScheduleRequest build() {
            return new PersonScheduleRequest(period);
        }
    }
}
