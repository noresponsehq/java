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
 * Access
 *
 * <p>public profiles were read; a private or locked one exists but could not be read.
 */
public class Access {

    public static final Access PUBLIC = new Access("public");
    public static final Access PRIVATE = new Access("private");
    public static final Access LOCKED = new Access("locked");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Access> values = createValuesMap();
    private static final Map<String, AccessEnum> enums = createEnumsMap();

    private final String value;

    private Access(String value) {
        this.value = value;
    }

    /**
     * Returns a Access with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Access
     */
    @JsonCreator
    public static Access of(String value) {
        synchronized (Access.class) {
            return values.computeIfAbsent(value, v -> new Access(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AccessEnum> asEnum() {
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
        Access other = (Access) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Access [value=" + value + "]";
    }

    // return an array just like an enum
    public static Access[] values() {
        synchronized (Access.class) {
            return values.values().toArray(new Access[] {});
        }
    }

    private static final Map<String, Access> createValuesMap() {
        Map<String, Access> map = new LinkedHashMap<>();
        map.put("public", PUBLIC);
        map.put("private", PRIVATE);
        map.put("locked", LOCKED);
        return map;
    }

    private static final Map<String, AccessEnum> createEnumsMap() {
        Map<String, AccessEnum> map = new HashMap<>();
        map.put("public", AccessEnum.PUBLIC);
        map.put("private", AccessEnum.PRIVATE);
        map.put("locked", AccessEnum.LOCKED);
        return map;
    }

    public enum AccessEnum {

        PUBLIC("public"),
        PRIVATE("private"),
        LOCKED("locked"),
        ;

        private final String value;

        private AccessEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
