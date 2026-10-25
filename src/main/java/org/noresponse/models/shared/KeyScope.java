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
public class KeyScope {

    public static final KeyScope ASSESS = new KeyScope("assess");
    public static final KeyScope HISTORY = new KeyScope("history");
    public static final KeyScope DELETE = new KeyScope("delete");
    public static final KeyScope WEBHOOKS = new KeyScope("webhooks");
    public static final KeyScope DETAILED = new KeyScope("detailed");
    public static final KeyScope INTEGRATIONS = new KeyScope("integrations");
    public static final KeyScope MANAGEMENT = new KeyScope("management");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, KeyScope> values = createValuesMap();
    private static final Map<String, KeyScopeEnum> enums = createEnumsMap();

    private final String value;

    private KeyScope(String value) {
        this.value = value;
    }

    /**
     * Returns a KeyScope with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as KeyScope
     */
    @JsonCreator
    public static KeyScope of(String value) {
        synchronized (KeyScope.class) {
            return values.computeIfAbsent(value, v -> new KeyScope(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<KeyScopeEnum> asEnum() {
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
        KeyScope other = (KeyScope) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "KeyScope [value=" + value + "]";
    }

    // return an array just like an enum
    public static KeyScope[] values() {
        synchronized (KeyScope.class) {
            return values.values().toArray(new KeyScope[] {});
        }
    }

    private static final Map<String, KeyScope> createValuesMap() {
        Map<String, KeyScope> map = new LinkedHashMap<>();
        map.put("assess", ASSESS);
        map.put("history", HISTORY);
        map.put("delete", DELETE);
        map.put("webhooks", WEBHOOKS);
        map.put("detailed", DETAILED);
        map.put("integrations", INTEGRATIONS);
        map.put("management", MANAGEMENT);
        return map;
    }

    private static final Map<String, KeyScopeEnum> createEnumsMap() {
        Map<String, KeyScopeEnum> map = new HashMap<>();
        map.put("assess", KeyScopeEnum.ASSESS);
        map.put("history", KeyScopeEnum.HISTORY);
        map.put("delete", KeyScopeEnum.DELETE);
        map.put("webhooks", KeyScopeEnum.WEBHOOKS);
        map.put("detailed", KeyScopeEnum.DETAILED);
        map.put("integrations", KeyScopeEnum.INTEGRATIONS);
        map.put("management", KeyScopeEnum.MANAGEMENT);
        return map;
    }

    public enum KeyScopeEnum {

        ASSESS("assess"),
        HISTORY("history"),
        DELETE("delete"),
        WEBHOOKS("webhooks"),
        DETAILED("detailed"),
        INTEGRATIONS("integrations"),
        MANAGEMENT("management"),
        ;

        private final String value;

        private KeyScopeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
