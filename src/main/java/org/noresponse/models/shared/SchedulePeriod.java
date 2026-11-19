package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Wrapper for an "open" enum that can handle unknown values from API responses
 * without runtime errors. Instances are immutable singletons with reference equality.
 * Use {@code asEnum()} for switch expressions.
 */
/**
 * SchedulePeriod
 *
 * <p>How often the person is investigated again: daily, weekly or monthly, counted from when the schedule
 * was set; off runs no scheduled investigation.
 */
public class SchedulePeriod {

    public static final SchedulePeriod DAILY = new SchedulePeriod("daily");
    public static final SchedulePeriod WEEKLY = new SchedulePeriod("weekly");
    public static final SchedulePeriod MONTHLY = new SchedulePeriod("monthly");
    public static final SchedulePeriod OFF = new SchedulePeriod("off");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, SchedulePeriod> values = createValuesMap();
    private static final Map<String, SchedulePeriodEnum> enums = createEnumsMap();

    private final String value;

    private SchedulePeriod(String value) {
        this.value = value;
    }

    /**
     * Returns a SchedulePeriod with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as SchedulePeriod
     */
    @JsonCreator
    public static SchedulePeriod of(String value) {
        synchronized (SchedulePeriod.class) {
            return values.computeIfAbsent(value, v -> new SchedulePeriod(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SchedulePeriodEnum> asEnum() {
        return Optional.ofNullable(enums.getOrDefault(value, null));
    }

    public boolean isKnown() {
        return asEnum().isPresent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SchedulePeriod other = (SchedulePeriod) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "SchedulePeriod [value=" + value + "]";
    }

    // return an array just like an enum
    public static SchedulePeriod[] values() {
        synchronized (SchedulePeriod.class) {
            return values.values().toArray(new SchedulePeriod[] {});
        }
    }

    private static final Map<String, SchedulePeriod> createValuesMap() {
        Map<String, SchedulePeriod> map = new LinkedHashMap<>();
        map.put("daily", DAILY);
        map.put("weekly", WEEKLY);
        map.put("monthly", MONTHLY);
        map.put("off", OFF);
        return map;
    }

    private static final Map<String, SchedulePeriodEnum> createEnumsMap() {
        Map<String, SchedulePeriodEnum> map = new HashMap<>();
        map.put("daily", SchedulePeriodEnum.DAILY);
        map.put("weekly", SchedulePeriodEnum.WEEKLY);
        map.put("monthly", SchedulePeriodEnum.MONTHLY);
        map.put("off", SchedulePeriodEnum.OFF);
        return map;
    }

    public enum SchedulePeriodEnum {

        DAILY("daily"),
        WEEKLY("weekly"),
        MONTHLY("monthly"),
        OFF("off"),
        ;

        private final String value;

        private SchedulePeriodEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
