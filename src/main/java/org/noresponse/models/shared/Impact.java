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
public class Impact {

    public static final Impact NONE = new Impact("none");
    public static final Impact LOW = new Impact("low");
    public static final Impact MEDIUM = new Impact("medium");
    public static final Impact HIGH = new Impact("high");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Impact> values = createValuesMap();
    private static final Map<String, ImpactEnum> enums = createEnumsMap();

    private final String value;

    private Impact(String value) {
        this.value = value;
    }

    /**
     * Returns a Impact with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Impact
     */
    @JsonCreator
    public static Impact of(String value) {
        synchronized (Impact.class) {
            return values.computeIfAbsent(value, v -> new Impact(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ImpactEnum> asEnum() {
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
        Impact other = (Impact) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Impact [value=" + value + "]";
    }

    // return an array just like an enum
    public static Impact[] values() {
        synchronized (Impact.class) {
            return values.values().toArray(new Impact[] {});
        }
    }

    private static final Map<String, Impact> createValuesMap() {
        Map<String, Impact> map = new LinkedHashMap<>();
        map.put("none", NONE);
        map.put("low", LOW);
        map.put("medium", MEDIUM);
        map.put("high", HIGH);
        return map;
    }

    private static final Map<String, ImpactEnum> createEnumsMap() {
        Map<String, ImpactEnum> map = new HashMap<>();
        map.put("none", ImpactEnum.NONE);
        map.put("low", ImpactEnum.LOW);
        map.put("medium", ImpactEnum.MEDIUM);
        map.put("high", ImpactEnum.HIGH);
        return map;
    }

    public enum ImpactEnum {

        NONE("none"),
        LOW("low"),
        MEDIUM("medium"),
        HIGH("high"),
        ;

        private final String value;

        private ImpactEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
