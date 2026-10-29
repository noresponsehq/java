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
public class KeyKind {

    public static final KeyKind SERVICE = new KeyKind("service");
    public static final KeyKind PERSONAL = new KeyKind("personal");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, KeyKind> values = createValuesMap();
    private static final Map<String, KeyKindEnum> enums = createEnumsMap();

    private final String value;

    private KeyKind(String value) {
        this.value = value;
    }

    /**
     * Returns a KeyKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as KeyKind
     */
    @JsonCreator
    public static KeyKind of(String value) {
        synchronized (KeyKind.class) {
            return values.computeIfAbsent(value, v -> new KeyKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<KeyKindEnum> asEnum() {
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
        KeyKind other = (KeyKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "KeyKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static KeyKind[] values() {
        synchronized (KeyKind.class) {
            return values.values().toArray(new KeyKind[] {});
        }
    }

    private static final Map<String, KeyKind> createValuesMap() {
        Map<String, KeyKind> map = new LinkedHashMap<>();
        map.put("service", SERVICE);
        map.put("personal", PERSONAL);
        return map;
    }

    private static final Map<String, KeyKindEnum> createEnumsMap() {
        Map<String, KeyKindEnum> map = new HashMap<>();
        map.put("service", KeyKindEnum.SERVICE);
        map.put("personal", KeyKindEnum.PERSONAL);
        return map;
    }

    public enum KeyKindEnum {

        SERVICE("service"),
        PERSONAL("personal"),
        ;

        private final String value;

        private KeyKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
