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
public class From {

    public static final From ALLOW = new From("allow");
    public static final From STEP_UP = new From("step_up");
    public static final From REVIEW = new From("review");
    public static final From BLOCK = new From("block");
    public static final From HELD = new From("held");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, From> values = createValuesMap();
    private static final Map<String, FromEnum> enums = createEnumsMap();

    private final String value;

    private From(String value) {
        this.value = value;
    }

    /**
     * Returns a From with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as From
     */
    @JsonCreator
    public static From of(String value) {
        synchronized (From.class) {
            return values.computeIfAbsent(value, v -> new From(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<FromEnum> asEnum() {
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
        From other = (From) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "From [value=" + value + "]";
    }

    // return an array just like an enum
    public static From[] values() {
        synchronized (From.class) {
            return values.values().toArray(new From[] {});
        }
    }

    private static final Map<String, From> createValuesMap() {
        Map<String, From> map = new LinkedHashMap<>();
        map.put("allow", ALLOW);
        map.put("step_up", STEP_UP);
        map.put("review", REVIEW);
        map.put("block", BLOCK);
        map.put("held", HELD);
        return map;
    }

    private static final Map<String, FromEnum> createEnumsMap() {
        Map<String, FromEnum> map = new HashMap<>();
        map.put("allow", FromEnum.ALLOW);
        map.put("step_up", FromEnum.STEP_UP);
        map.put("review", FromEnum.REVIEW);
        map.put("block", FromEnum.BLOCK);
        map.put("held", FromEnum.HELD);
        return map;
    }

    public enum FromEnum {

        ALLOW("allow"),
        STEP_UP("step_up"),
        REVIEW("review"),
        BLOCK("block"),
        HELD("held"),
        ;

        private final String value;

        private FromEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
