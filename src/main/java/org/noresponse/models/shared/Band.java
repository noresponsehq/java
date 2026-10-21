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
 * Band
 *
 * <p>low 0-29, medium 30-59, high 60-84, critical 85-100.
 */
public class Band {

    public static final Band LOW = new Band("low");
    public static final Band MEDIUM = new Band("medium");
    public static final Band HIGH = new Band("high");
    public static final Band CRITICAL = new Band("critical");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Band> values = createValuesMap();
    private static final Map<String, BandEnum> enums = createEnumsMap();

    private final String value;

    private Band(String value) {
        this.value = value;
    }

    /**
     * Returns a Band with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Band
     */
    @JsonCreator
    public static Band of(String value) {
        synchronized (Band.class) {
            return values.computeIfAbsent(value, v -> new Band(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<BandEnum> asEnum() {
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
        Band other = (Band) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Band [value=" + value + "]";
    }

    // return an array just like an enum
    public static Band[] values() {
        synchronized (Band.class) {
            return values.values().toArray(new Band[] {});
        }
    }

    private static final Map<String, Band> createValuesMap() {
        Map<String, Band> map = new LinkedHashMap<>();
        map.put("low", LOW);
        map.put("medium", MEDIUM);
        map.put("high", HIGH);
        map.put("critical", CRITICAL);
        return map;
    }

    private static final Map<String, BandEnum> createEnumsMap() {
        Map<String, BandEnum> map = new HashMap<>();
        map.put("low", BandEnum.LOW);
        map.put("medium", BandEnum.MEDIUM);
        map.put("high", BandEnum.HIGH);
        map.put("critical", BandEnum.CRITICAL);
        return map;
    }

    public enum BandEnum {

        LOW("low"),
        MEDIUM("medium"),
        HIGH("high"),
        CRITICAL("critical"),
        ;

        private final String value;

        private BandEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
