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
public class Environment {

    public static final Environment LIVE = new Environment("live");
    public static final Environment TEST = new Environment("test");
    public static final Environment WORKSPACE = new Environment("workspace");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Environment> values = createValuesMap();
    private static final Map<String, EnvironmentEnum> enums = createEnumsMap();

    private final String value;

    private Environment(String value) {
        this.value = value;
    }

    /**
     * Returns a Environment with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Environment
     */
    @JsonCreator
    public static Environment of(String value) {
        synchronized (Environment.class) {
            return values.computeIfAbsent(value, v -> new Environment(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EnvironmentEnum> asEnum() {
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
        Environment other = (Environment) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Environment [value=" + value + "]";
    }

    // return an array just like an enum
    public static Environment[] values() {
        synchronized (Environment.class) {
            return values.values().toArray(new Environment[] {});
        }
    }

    private static final Map<String, Environment> createValuesMap() {
        Map<String, Environment> map = new LinkedHashMap<>();
        map.put("live", LIVE);
        map.put("test", TEST);
        map.put("workspace", WORKSPACE);
        return map;
    }

    private static final Map<String, EnvironmentEnum> createEnumsMap() {
        Map<String, EnvironmentEnum> map = new HashMap<>();
        map.put("live", EnvironmentEnum.LIVE);
        map.put("test", EnvironmentEnum.TEST);
        map.put("workspace", EnvironmentEnum.WORKSPACE);
        return map;
    }

    public enum EnvironmentEnum {

        LIVE("live"),
        TEST("test"),
        WORKSPACE("workspace"),
        ;

        private final String value;

        private EnvironmentEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
