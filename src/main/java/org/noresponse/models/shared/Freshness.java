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
 * Freshness
 *
 * <p>Freshness class of the evidence behind a signal. unknown whenever no evidence was obtained.
 */
public class Freshness {

    public static final Freshness FRESH = new Freshness("fresh");
    public static final Freshness STALE = new Freshness("stale");
    public static final Freshness UNKNOWN = new Freshness("unknown");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Freshness> values = createValuesMap();
    private static final Map<String, FreshnessEnum> enums = createEnumsMap();

    private final String value;

    private Freshness(String value) {
        this.value = value;
    }

    /**
     * Returns a Freshness with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Freshness
     */
    @JsonCreator
    public static Freshness of(String value) {
        synchronized (Freshness.class) {
            return values.computeIfAbsent(value, v -> new Freshness(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<FreshnessEnum> asEnum() {
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
        Freshness other = (Freshness) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Freshness [value=" + value + "]";
    }

    // return an array just like an enum
    public static Freshness[] values() {
        synchronized (Freshness.class) {
            return values.values().toArray(new Freshness[] {});
        }
    }

    private static final Map<String, Freshness> createValuesMap() {
        Map<String, Freshness> map = new LinkedHashMap<>();
        map.put("fresh", FRESH);
        map.put("stale", STALE);
        map.put("unknown", UNKNOWN);
        return map;
    }

    private static final Map<String, FreshnessEnum> createEnumsMap() {
        Map<String, FreshnessEnum> map = new HashMap<>();
        map.put("fresh", FreshnessEnum.FRESH);
        map.put("stale", FreshnessEnum.STALE);
        map.put("unknown", FreshnessEnum.UNKNOWN);
        return map;
    }

    public enum FreshnessEnum {

        FRESH("fresh"),
        STALE("stale"),
        UNKNOWN("unknown"),
        ;

        private final String value;

        private FreshnessEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
