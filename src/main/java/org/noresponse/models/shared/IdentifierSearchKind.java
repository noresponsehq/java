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
public class IdentifierSearchKind {

    public static final IdentifierSearchKind EMAIL = new IdentifierSearchKind("email");
    public static final IdentifierSearchKind PHONE = new IdentifierSearchKind("phone");
    public static final IdentifierSearchKind USERNAME = new IdentifierSearchKind("username");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, IdentifierSearchKind> values = createValuesMap();
    private static final Map<String, IdentifierSearchKindEnum> enums = createEnumsMap();

    private final String value;

    private IdentifierSearchKind(String value) {
        this.value = value;
    }

    /**
     * Returns a IdentifierSearchKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as IdentifierSearchKind
     */
    @JsonCreator
    public static IdentifierSearchKind of(String value) {
        synchronized (IdentifierSearchKind.class) {
            return values.computeIfAbsent(value, v -> new IdentifierSearchKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<IdentifierSearchKindEnum> asEnum() {
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
        IdentifierSearchKind other = (IdentifierSearchKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "IdentifierSearchKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static IdentifierSearchKind[] values() {
        synchronized (IdentifierSearchKind.class) {
            return values.values().toArray(new IdentifierSearchKind[] {});
        }
    }

    private static final Map<String, IdentifierSearchKind> createValuesMap() {
        Map<String, IdentifierSearchKind> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("phone", PHONE);
        map.put("username", USERNAME);
        return map;
    }

    private static final Map<String, IdentifierSearchKindEnum> createEnumsMap() {
        Map<String, IdentifierSearchKindEnum> map = new HashMap<>();
        map.put("email", IdentifierSearchKindEnum.EMAIL);
        map.put("phone", IdentifierSearchKindEnum.PHONE);
        map.put("username", IdentifierSearchKindEnum.USERNAME);
        return map;
    }

    public enum IdentifierSearchKindEnum {

        EMAIL("email"),
        PHONE("phone"),
        USERNAME("username"),
        ;

        private final String value;

        private IdentifierSearchKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
