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
 * IdentifierKind
 *
 * <p>Which submitted identifier a signal concerns. ip is a selector of breach_exposure only.
 */
public class IdentifierKind {

    public static final IdentifierKind EMAIL = new IdentifierKind("email");
    public static final IdentifierKind USERNAME = new IdentifierKind("username");
    public static final IdentifierKind PHONE = new IdentifierKind("phone");
    public static final IdentifierKind IP = new IdentifierKind("ip");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, IdentifierKind> values = createValuesMap();
    private static final Map<String, IdentifierKindEnum> enums = createEnumsMap();

    private final String value;

    private IdentifierKind(String value) {
        this.value = value;
    }

    /**
     * Returns a IdentifierKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as IdentifierKind
     */
    @JsonCreator
    public static IdentifierKind of(String value) {
        synchronized (IdentifierKind.class) {
            return values.computeIfAbsent(value, v -> new IdentifierKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<IdentifierKindEnum> asEnum() {
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
        IdentifierKind other = (IdentifierKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "IdentifierKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static IdentifierKind[] values() {
        synchronized (IdentifierKind.class) {
            return values.values().toArray(new IdentifierKind[] {});
        }
    }

    private static final Map<String, IdentifierKind> createValuesMap() {
        Map<String, IdentifierKind> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("username", USERNAME);
        map.put("phone", PHONE);
        map.put("ip", IP);
        return map;
    }

    private static final Map<String, IdentifierKindEnum> createEnumsMap() {
        Map<String, IdentifierKindEnum> map = new HashMap<>();
        map.put("email", IdentifierKindEnum.EMAIL);
        map.put("username", IdentifierKindEnum.USERNAME);
        map.put("phone", IdentifierKindEnum.PHONE);
        map.put("ip", IdentifierKindEnum.IP);
        return map;
    }

    public enum IdentifierKindEnum {

        EMAIL("email"),
        USERNAME("username"),
        PHONE("phone"),
        IP("ip"),
        ;

        private final String value;

        private IdentifierKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
