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
public class MatchedOn {

    public static final MatchedOn EMAIL = new MatchedOn("email");
    public static final MatchedOn PHONE = new MatchedOn("phone");
    public static final MatchedOn EXTERNAL_ID = new MatchedOn("external_id");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, MatchedOn> values = createValuesMap();
    private static final Map<String, MatchedOnEnum> enums = createEnumsMap();

    private final String value;

    private MatchedOn(String value) {
        this.value = value;
    }

    /**
     * Returns a MatchedOn with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as MatchedOn
     */
    @JsonCreator
    public static MatchedOn of(String value) {
        synchronized (MatchedOn.class) {
            return values.computeIfAbsent(value, v -> new MatchedOn(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MatchedOnEnum> asEnum() {
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
        MatchedOn other = (MatchedOn) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "MatchedOn [value=" + value + "]";
    }

    // return an array just like an enum
    public static MatchedOn[] values() {
        synchronized (MatchedOn.class) {
            return values.values().toArray(new MatchedOn[] {});
        }
    }

    private static final Map<String, MatchedOn> createValuesMap() {
        Map<String, MatchedOn> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("phone", PHONE);
        map.put("external_id", EXTERNAL_ID);
        return map;
    }

    private static final Map<String, MatchedOnEnum> createEnumsMap() {
        Map<String, MatchedOnEnum> map = new HashMap<>();
        map.put("email", MatchedOnEnum.EMAIL);
        map.put("phone", MatchedOnEnum.PHONE);
        map.put("external_id", MatchedOnEnum.EXTERNAL_ID);
        return map;
    }

    public enum MatchedOnEnum {

        EMAIL("email"),
        PHONE("phone"),
        EXTERNAL_ID("external_id"),
        ;

        private final String value;

        private MatchedOnEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
