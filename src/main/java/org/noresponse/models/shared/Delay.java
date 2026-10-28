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
 * Delay
 *
 * <p>How long after the assessment's creation the change was made.
 */
public class Delay {

    public static final Delay UNDER10S = new Delay("under_10s");
    public static final Delay UNDER1M = new Delay("under_1m");
    public static final Delay UNDER10M = new Delay("under_10m");
    public static final Delay UNDER1H = new Delay("under_1h");
    public static final Delay UNDER1D = new Delay("under_1d");
    public static final Delay OVER1D = new Delay("over_1d");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Delay> values = createValuesMap();
    private static final Map<String, DelayEnum> enums = createEnumsMap();

    private final String value;

    private Delay(String value) {
        this.value = value;
    }

    /**
     * Returns a Delay with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Delay
     */
    @JsonCreator
    public static Delay of(String value) {
        synchronized (Delay.class) {
            return values.computeIfAbsent(value, v -> new Delay(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<DelayEnum> asEnum() {
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
        Delay other = (Delay) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Delay [value=" + value + "]";
    }

    // return an array just like an enum
    public static Delay[] values() {
        synchronized (Delay.class) {
            return values.values().toArray(new Delay[] {});
        }
    }

    private static final Map<String, Delay> createValuesMap() {
        Map<String, Delay> map = new LinkedHashMap<>();
        map.put("under_10s", UNDER10S);
        map.put("under_1m", UNDER1M);
        map.put("under_10m", UNDER10M);
        map.put("under_1h", UNDER1H);
        map.put("under_1d", UNDER1D);
        map.put("over_1d", OVER1D);
        return map;
    }

    private static final Map<String, DelayEnum> createEnumsMap() {
        Map<String, DelayEnum> map = new HashMap<>();
        map.put("under_10s", DelayEnum.UNDER10S);
        map.put("under_1m", DelayEnum.UNDER1M);
        map.put("under_10m", DelayEnum.UNDER10M);
        map.put("under_1h", DelayEnum.UNDER1H);
        map.put("under_1d", DelayEnum.UNDER1D);
        map.put("over_1d", DelayEnum.OVER1D);
        return map;
    }

    public enum DelayEnum {

        UNDER10S("under_10s"),
        UNDER1M("under_1m"),
        UNDER10M("under_10m"),
        UNDER1H("under_1h"),
        UNDER1D("under_1d"),
        OVER1D("over_1d"),
        ;

        private final String value;

        private DelayEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
