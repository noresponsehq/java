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
 * BreachSelector
 *
 * <p>The submitted email or username whose own search matched the table. A submitted full name is
 * searched as well, and what it finds feeds the identity's reasons rather than this list.
 */
public class BreachSelector {

    public static final BreachSelector EMAIL = new BreachSelector("email");
    public static final BreachSelector USERNAME = new BreachSelector("username");
    public static final BreachSelector IP = new BreachSelector("ip");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, BreachSelector> values = createValuesMap();
    private static final Map<String, BreachSelectorEnum> enums = createEnumsMap();

    private final String value;

    private BreachSelector(String value) {
        this.value = value;
    }

    /**
     * Returns a BreachSelector with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as BreachSelector
     */
    @JsonCreator
    public static BreachSelector of(String value) {
        synchronized (BreachSelector.class) {
            return values.computeIfAbsent(value, v -> new BreachSelector(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<BreachSelectorEnum> asEnum() {
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
        BreachSelector other = (BreachSelector) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "BreachSelector [value=" + value + "]";
    }

    // return an array just like an enum
    public static BreachSelector[] values() {
        synchronized (BreachSelector.class) {
            return values.values().toArray(new BreachSelector[] {});
        }
    }

    private static final Map<String, BreachSelector> createValuesMap() {
        Map<String, BreachSelector> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("username", USERNAME);
        map.put("ip", IP);
        return map;
    }

    private static final Map<String, BreachSelectorEnum> createEnumsMap() {
        Map<String, BreachSelectorEnum> map = new HashMap<>();
        map.put("email", BreachSelectorEnum.EMAIL);
        map.put("username", BreachSelectorEnum.USERNAME);
        map.put("ip", BreachSelectorEnum.IP);
        return map;
    }

    public enum BreachSelectorEnum {

        EMAIL("email"),
        USERNAME("username"),
        IP("ip"),
        ;

        private final String value;

        private BreachSelectorEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
