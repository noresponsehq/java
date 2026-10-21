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
 * ConsistencyValue
 *
 * <p>How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
 * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
 * comparison was not made.
 */
public class ConsistencyValue {

    public static final ConsistencyValue MATCH = new ConsistencyValue("match");
    public static final ConsistencyValue PARTIAL = new ConsistencyValue("partial");
    public static final ConsistencyValue MISMATCH = new ConsistencyValue("mismatch");
    public static final ConsistencyValue UNKNOWN = new ConsistencyValue("unknown");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ConsistencyValue> values = createValuesMap();
    private static final Map<String, ConsistencyValueEnum> enums = createEnumsMap();

    private final String value;

    private ConsistencyValue(String value) {
        this.value = value;
    }

    /**
     * Returns a ConsistencyValue with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ConsistencyValue
     */
    @JsonCreator
    public static ConsistencyValue of(String value) {
        synchronized (ConsistencyValue.class) {
            return values.computeIfAbsent(value, v -> new ConsistencyValue(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ConsistencyValueEnum> asEnum() {
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
        ConsistencyValue other = (ConsistencyValue) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ConsistencyValue [value=" + value + "]";
    }

    // return an array just like an enum
    public static ConsistencyValue[] values() {
        synchronized (ConsistencyValue.class) {
            return values.values().toArray(new ConsistencyValue[] {});
        }
    }

    private static final Map<String, ConsistencyValue> createValuesMap() {
        Map<String, ConsistencyValue> map = new LinkedHashMap<>();
        map.put("match", MATCH);
        map.put("partial", PARTIAL);
        map.put("mismatch", MISMATCH);
        map.put("unknown", UNKNOWN);
        return map;
    }

    private static final Map<String, ConsistencyValueEnum> createEnumsMap() {
        Map<String, ConsistencyValueEnum> map = new HashMap<>();
        map.put("match", ConsistencyValueEnum.MATCH);
        map.put("partial", ConsistencyValueEnum.PARTIAL);
        map.put("mismatch", ConsistencyValueEnum.MISMATCH);
        map.put("unknown", ConsistencyValueEnum.UNKNOWN);
        return map;
    }

    public enum ConsistencyValueEnum {

        MATCH("match"),
        PARTIAL("partial"),
        MISMATCH("mismatch"),
        UNKNOWN("unknown"),
        ;

        private final String value;

        private ConsistencyValueEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
