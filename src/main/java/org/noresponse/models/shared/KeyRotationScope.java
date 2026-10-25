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
public class KeyRotationScope {

    public static final KeyRotationScope ASSESS = new KeyRotationScope("assess");
    public static final KeyRotationScope HISTORY = new KeyRotationScope("history");
    public static final KeyRotationScope DELETE = new KeyRotationScope("delete");
    public static final KeyRotationScope WEBHOOKS = new KeyRotationScope("webhooks");
    public static final KeyRotationScope DETAILED = new KeyRotationScope("detailed");
    public static final KeyRotationScope INTEGRATIONS = new KeyRotationScope("integrations");
    public static final KeyRotationScope MANAGEMENT = new KeyRotationScope("management");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, KeyRotationScope> values = createValuesMap();
    private static final Map<String, KeyRotationScopeEnum> enums = createEnumsMap();

    private final String value;

    private KeyRotationScope(String value) {
        this.value = value;
    }

    /**
     * Returns a KeyRotationScope with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as KeyRotationScope
     */
    @JsonCreator
    public static KeyRotationScope of(String value) {
        synchronized (KeyRotationScope.class) {
            return values.computeIfAbsent(value, v -> new KeyRotationScope(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<KeyRotationScopeEnum> asEnum() {
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
        KeyRotationScope other = (KeyRotationScope) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "KeyRotationScope [value=" + value + "]";
    }

    // return an array just like an enum
    public static KeyRotationScope[] values() {
        synchronized (KeyRotationScope.class) {
            return values.values().toArray(new KeyRotationScope[] {});
        }
    }

    private static final Map<String, KeyRotationScope> createValuesMap() {
        Map<String, KeyRotationScope> map = new LinkedHashMap<>();
        map.put("assess", ASSESS);
        map.put("history", HISTORY);
        map.put("delete", DELETE);
        map.put("webhooks", WEBHOOKS);
        map.put("detailed", DETAILED);
        map.put("integrations", INTEGRATIONS);
        map.put("management", MANAGEMENT);
        return map;
    }

    private static final Map<String, KeyRotationScopeEnum> createEnumsMap() {
        Map<String, KeyRotationScopeEnum> map = new HashMap<>();
        map.put("assess", KeyRotationScopeEnum.ASSESS);
        map.put("history", KeyRotationScopeEnum.HISTORY);
        map.put("delete", KeyRotationScopeEnum.DELETE);
        map.put("webhooks", KeyRotationScopeEnum.WEBHOOKS);
        map.put("detailed", KeyRotationScopeEnum.DETAILED);
        map.put("integrations", KeyRotationScopeEnum.INTEGRATIONS);
        map.put("management", KeyRotationScopeEnum.MANAGEMENT);
        return map;
    }

    public enum KeyRotationScopeEnum {

        ASSESS("assess"),
        HISTORY("history"),
        DELETE("delete"),
        WEBHOOKS("webhooks"),
        DETAILED("detailed"),
        INTEGRATIONS("integrations"),
        MANAGEMENT("management"),
        ;

        private final String value;

        private KeyRotationScopeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
