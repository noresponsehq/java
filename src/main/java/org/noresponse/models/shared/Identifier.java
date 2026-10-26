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
public class Identifier {

    public static final Identifier EMAIL = new Identifier("email");
    public static final Identifier USERNAME = new Identifier("username");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Identifier> values = createValuesMap();
    private static final Map<String, IdentifierEnum> enums = createEnumsMap();

    private final String value;

    private Identifier(String value) {
        this.value = value;
    }

    /**
     * Returns a Identifier with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Identifier
     */
    @JsonCreator
    public static Identifier of(String value) {
        synchronized (Identifier.class) {
            return values.computeIfAbsent(value, v -> new Identifier(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<IdentifierEnum> asEnum() {
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
        Identifier other = (Identifier) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Identifier [value=" + value + "]";
    }

    // return an array just like an enum
    public static Identifier[] values() {
        synchronized (Identifier.class) {
            return values.values().toArray(new Identifier[] {});
        }
    }

    private static final Map<String, Identifier> createValuesMap() {
        Map<String, Identifier> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("username", USERNAME);
        return map;
    }

    private static final Map<String, IdentifierEnum> createEnumsMap() {
        Map<String, IdentifierEnum> map = new HashMap<>();
        map.put("email", IdentifierEnum.EMAIL);
        map.put("username", IdentifierEnum.USERNAME);
        return map;
    }

    public enum IdentifierEnum {

        EMAIL("email"),
        USERNAME("username"),
        ;

        private final String value;

        private IdentifierEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
