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
 * Significance
 *
 * <p>One rule ranks every change: identity changes (accounts, breaches, identifiers) are high, profile
 * fields are medium, new posts are low.
 */
public class Significance {

    public static final Significance HIGH = new Significance("high");
    public static final Significance MEDIUM = new Significance("medium");
    public static final Significance LOW = new Significance("low");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Significance> values = createValuesMap();
    private static final Map<String, SignificanceEnum> enums = createEnumsMap();

    private final String value;

    private Significance(String value) {
        this.value = value;
    }

    /**
     * Returns a Significance with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Significance
     */
    @JsonCreator
    public static Significance of(String value) {
        synchronized (Significance.class) {
            return values.computeIfAbsent(value, v -> new Significance(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SignificanceEnum> asEnum() {
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
        Significance other = (Significance) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Significance [value=" + value + "]";
    }

    // return an array just like an enum
    public static Significance[] values() {
        synchronized (Significance.class) {
            return values.values().toArray(new Significance[] {});
        }
    }

    private static final Map<String, Significance> createValuesMap() {
        Map<String, Significance> map = new LinkedHashMap<>();
        map.put("high", HIGH);
        map.put("medium", MEDIUM);
        map.put("low", LOW);
        return map;
    }

    private static final Map<String, SignificanceEnum> createEnumsMap() {
        Map<String, SignificanceEnum> map = new HashMap<>();
        map.put("high", SignificanceEnum.HIGH);
        map.put("medium", SignificanceEnum.MEDIUM);
        map.put("low", SignificanceEnum.LOW);
        return map;
    }

    public enum SignificanceEnum {

        HIGH("high"),
        MEDIUM("medium"),
        LOW("low"),
        ;

        private final String value;

        private SignificanceEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
