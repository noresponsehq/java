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
public class HighestBand {

    public static final HighestBand CRITICAL = new HighestBand("critical");
    public static final HighestBand HIGH = new HighestBand("high");
    public static final HighestBand MODERATE = new HighestBand("moderate");
    public static final HighestBand LOW = new HighestBand("low");
    public static final HighestBand INFO = new HighestBand("info");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, HighestBand> values = createValuesMap();
    private static final Map<String, HighestBandEnum> enums = createEnumsMap();

    private final String value;

    private HighestBand(String value) {
        this.value = value;
    }

    /**
     * Returns a HighestBand with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as HighestBand
     */
    @JsonCreator
    public static HighestBand of(String value) {
        synchronized (HighestBand.class) {
            return values.computeIfAbsent(value, v -> new HighestBand(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<HighestBandEnum> asEnum() {
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
        HighestBand other = (HighestBand) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "HighestBand [value=" + value + "]";
    }

    // return an array just like an enum
    public static HighestBand[] values() {
        synchronized (HighestBand.class) {
            return values.values().toArray(new HighestBand[] {});
        }
    }

    private static final Map<String, HighestBand> createValuesMap() {
        Map<String, HighestBand> map = new LinkedHashMap<>();
        map.put("critical", CRITICAL);
        map.put("high", HIGH);
        map.put("moderate", MODERATE);
        map.put("low", LOW);
        map.put("info", INFO);
        return map;
    }

    private static final Map<String, HighestBandEnum> createEnumsMap() {
        Map<String, HighestBandEnum> map = new HashMap<>();
        map.put("critical", HighestBandEnum.CRITICAL);
        map.put("high", HighestBandEnum.HIGH);
        map.put("moderate", HighestBandEnum.MODERATE);
        map.put("low", HighestBandEnum.LOW);
        map.put("info", HighestBandEnum.INFO);
        return map;
    }

    public enum HighestBandEnum {

        CRITICAL("critical"),
        HIGH("high"),
        MODERATE("moderate"),
        LOW("low"),
        INFO("info"),
        ;

        private final String value;

        private HighestBandEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
