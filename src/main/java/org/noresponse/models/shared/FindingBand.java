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
 * FindingBand
 *
 * <p>How severe a finding is, most severe first.
 */
public class FindingBand {

    public static final FindingBand CRITICAL = new FindingBand("critical");
    public static final FindingBand HIGH = new FindingBand("high");
    public static final FindingBand MODERATE = new FindingBand("moderate");
    public static final FindingBand LOW = new FindingBand("low");
    public static final FindingBand INFO = new FindingBand("info");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, FindingBand> values = createValuesMap();
    private static final Map<String, FindingBandEnum> enums = createEnumsMap();

    private final String value;

    private FindingBand(String value) {
        this.value = value;
    }

    /**
     * Returns a FindingBand with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as FindingBand
     */
    @JsonCreator
    public static FindingBand of(String value) {
        synchronized (FindingBand.class) {
            return values.computeIfAbsent(value, v -> new FindingBand(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<FindingBandEnum> asEnum() {
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
        FindingBand other = (FindingBand) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "FindingBand [value=" + value + "]";
    }

    // return an array just like an enum
    public static FindingBand[] values() {
        synchronized (FindingBand.class) {
            return values.values().toArray(new FindingBand[] {});
        }
    }

    private static final Map<String, FindingBand> createValuesMap() {
        Map<String, FindingBand> map = new LinkedHashMap<>();
        map.put("critical", CRITICAL);
        map.put("high", HIGH);
        map.put("moderate", MODERATE);
        map.put("low", LOW);
        map.put("info", INFO);
        return map;
    }

    private static final Map<String, FindingBandEnum> createEnumsMap() {
        Map<String, FindingBandEnum> map = new HashMap<>();
        map.put("critical", FindingBandEnum.CRITICAL);
        map.put("high", FindingBandEnum.HIGH);
        map.put("moderate", FindingBandEnum.MODERATE);
        map.put("low", FindingBandEnum.LOW);
        map.put("info", FindingBandEnum.INFO);
        return map;
    }

    public enum FindingBandEnum {

        CRITICAL("critical"),
        HIGH("high"),
        MODERATE("moderate"),
        LOW("low"),
        INFO("info"),
        ;

        private final String value;

        private FindingBandEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
