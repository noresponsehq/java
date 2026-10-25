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
 * KeyEnvironment
 *
 * <p>The environment the key belongs to. A live key assesses with live sources; a test key answers from
 * the deterministic simulator.
 */
public class KeyEnvironment {

    public static final KeyEnvironment LIVE = new KeyEnvironment("live");
    public static final KeyEnvironment TEST = new KeyEnvironment("test");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, KeyEnvironment> values = createValuesMap();
    private static final Map<String, KeyEnvironmentEnum> enums = createEnumsMap();

    private final String value;

    private KeyEnvironment(String value) {
        this.value = value;
    }

    /**
     * Returns a KeyEnvironment with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as KeyEnvironment
     */
    @JsonCreator
    public static KeyEnvironment of(String value) {
        synchronized (KeyEnvironment.class) {
            return values.computeIfAbsent(value, v -> new KeyEnvironment(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<KeyEnvironmentEnum> asEnum() {
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
        KeyEnvironment other = (KeyEnvironment) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "KeyEnvironment [value=" + value + "]";
    }

    // return an array just like an enum
    public static KeyEnvironment[] values() {
        synchronized (KeyEnvironment.class) {
            return values.values().toArray(new KeyEnvironment[] {});
        }
    }

    private static final Map<String, KeyEnvironment> createValuesMap() {
        Map<String, KeyEnvironment> map = new LinkedHashMap<>();
        map.put("live", LIVE);
        map.put("test", TEST);
        return map;
    }

    private static final Map<String, KeyEnvironmentEnum> createEnumsMap() {
        Map<String, KeyEnvironmentEnum> map = new HashMap<>();
        map.put("live", KeyEnvironmentEnum.LIVE);
        map.put("test", KeyEnvironmentEnum.TEST);
        return map;
    }

    public enum KeyEnvironmentEnum {

        LIVE("live"),
        TEST("test"),
        ;

        private final String value;

        private KeyEnvironmentEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
