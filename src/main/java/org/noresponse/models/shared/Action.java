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
public class Action {

    public static final Action ALLOW = new Action("allow");
    public static final Action BLOCK = new Action("block");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Action> values = createValuesMap();
    private static final Map<String, ActionEnum> enums = createEnumsMap();

    private final String value;

    private Action(String value) {
        this.value = value;
    }

    /**
     * Returns a Action with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Action
     */
    @JsonCreator
    public static Action of(String value) {
        synchronized (Action.class) {
            return values.computeIfAbsent(value, v -> new Action(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ActionEnum> asEnum() {
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
        Action other = (Action) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Action [value=" + value + "]";
    }

    // return an array just like an enum
    public static Action[] values() {
        synchronized (Action.class) {
            return values.values().toArray(new Action[] {});
        }
    }

    private static final Map<String, Action> createValuesMap() {
        Map<String, Action> map = new LinkedHashMap<>();
        map.put("allow", ALLOW);
        map.put("block", BLOCK);
        return map;
    }

    private static final Map<String, ActionEnum> createEnumsMap() {
        Map<String, ActionEnum> map = new HashMap<>();
        map.put("allow", ActionEnum.ALLOW);
        map.put("block", ActionEnum.BLOCK);
        return map;
    }

    public enum ActionEnum {

        ALLOW("allow"),
        BLOCK("block"),
        ;

        private final String value;

        private ActionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
