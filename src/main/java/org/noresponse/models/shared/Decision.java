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
 * Decision
 *
 * <p>An assessment's own decision: block when its stored risk.block is true, otherwise its
 * recommendation. It is not the account's decision or held-admission state.
 */
public class Decision {

    public static final Decision ALLOW = new Decision("allow");
    public static final Decision STEP_UP = new Decision("step_up");
    public static final Decision REVIEW = new Decision("review");
    public static final Decision BLOCK = new Decision("block");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Decision> values = createValuesMap();
    private static final Map<String, DecisionEnum> enums = createEnumsMap();

    private final String value;

    private Decision(String value) {
        this.value = value;
    }

    /**
     * Returns a Decision with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Decision
     */
    @JsonCreator
    public static Decision of(String value) {
        synchronized (Decision.class) {
            return values.computeIfAbsent(value, v -> new Decision(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<DecisionEnum> asEnum() {
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
        Decision other = (Decision) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Decision [value=" + value + "]";
    }

    // return an array just like an enum
    public static Decision[] values() {
        synchronized (Decision.class) {
            return values.values().toArray(new Decision[] {});
        }
    }

    private static final Map<String, Decision> createValuesMap() {
        Map<String, Decision> map = new LinkedHashMap<>();
        map.put("allow", ALLOW);
        map.put("step_up", STEP_UP);
        map.put("review", REVIEW);
        map.put("block", BLOCK);
        return map;
    }

    private static final Map<String, DecisionEnum> createEnumsMap() {
        Map<String, DecisionEnum> map = new HashMap<>();
        map.put("allow", DecisionEnum.ALLOW);
        map.put("step_up", DecisionEnum.STEP_UP);
        map.put("review", DecisionEnum.REVIEW);
        map.put("block", DecisionEnum.BLOCK);
        return map;
    }

    public enum DecisionEnum {

        ALLOW("allow"),
        STEP_UP("step_up"),
        REVIEW("review"),
        BLOCK("block"),
        ;

        private final String value;

        private DecisionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
