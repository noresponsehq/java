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
public class Scope {

    public static final Scope ASSESS = new Scope("assess");
    public static final Scope HISTORY = new Scope("history");
    public static final Scope DELETE = new Scope("delete");
    public static final Scope WEBHOOKS = new Scope("webhooks");
    public static final Scope DETAILED = new Scope("detailed");
    public static final Scope INTEGRATIONS = new Scope("integrations");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Scope> values = createValuesMap();
    private static final Map<String, ScopeEnum> enums = createEnumsMap();

    private final String value;

    private Scope(String value) {
        this.value = value;
    }

    /**
     * Returns a Scope with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Scope
     */
    @JsonCreator
    public static Scope of(String value) {
        synchronized (Scope.class) {
            return values.computeIfAbsent(value, v -> new Scope(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScopeEnum> asEnum() {
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
        Scope other = (Scope) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Scope [value=" + value + "]";
    }

    // return an array just like an enum
    public static Scope[] values() {
        synchronized (Scope.class) {
            return values.values().toArray(new Scope[] {});
        }
    }

    private static final Map<String, Scope> createValuesMap() {
        Map<String, Scope> map = new LinkedHashMap<>();
        map.put("assess", ASSESS);
        map.put("history", HISTORY);
        map.put("delete", DELETE);
        map.put("webhooks", WEBHOOKS);
        map.put("detailed", DETAILED);
        map.put("integrations", INTEGRATIONS);
        return map;
    }

    private static final Map<String, ScopeEnum> createEnumsMap() {
        Map<String, ScopeEnum> map = new HashMap<>();
        map.put("assess", ScopeEnum.ASSESS);
        map.put("history", ScopeEnum.HISTORY);
        map.put("delete", ScopeEnum.DELETE);
        map.put("webhooks", ScopeEnum.WEBHOOKS);
        map.put("detailed", ScopeEnum.DETAILED);
        map.put("integrations", ScopeEnum.INTEGRATIONS);
        return map;
    }

    public enum ScopeEnum {

        ASSESS("assess"),
        HISTORY("history"),
        DELETE("delete"),
        WEBHOOKS("webhooks"),
        DETAILED("detailed"),
        INTEGRATIONS("integrations"),
        ;

        private final String value;

        private ScopeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
