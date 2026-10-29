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
public class InviteEnvironment {

    public static final InviteEnvironment LIVE = new InviteEnvironment("live");
    public static final InviteEnvironment TEST = new InviteEnvironment("test");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, InviteEnvironment> values = createValuesMap();
    private static final Map<String, InviteEnvironmentEnum> enums = createEnumsMap();

    private final String value;

    private InviteEnvironment(String value) {
        this.value = value;
    }

    /**
     * Returns a InviteEnvironment with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as InviteEnvironment
     */
    @JsonCreator
    public static InviteEnvironment of(String value) {
        synchronized (InviteEnvironment.class) {
            return values.computeIfAbsent(value, v -> new InviteEnvironment(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<InviteEnvironmentEnum> asEnum() {
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
        InviteEnvironment other = (InviteEnvironment) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "InviteEnvironment [value=" + value + "]";
    }

    // return an array just like an enum
    public static InviteEnvironment[] values() {
        synchronized (InviteEnvironment.class) {
            return values.values().toArray(new InviteEnvironment[] {});
        }
    }

    private static final Map<String, InviteEnvironment> createValuesMap() {
        Map<String, InviteEnvironment> map = new LinkedHashMap<>();
        map.put("live", LIVE);
        map.put("test", TEST);
        return map;
    }

    private static final Map<String, InviteEnvironmentEnum> createEnumsMap() {
        Map<String, InviteEnvironmentEnum> map = new HashMap<>();
        map.put("live", InviteEnvironmentEnum.LIVE);
        map.put("test", InviteEnvironmentEnum.TEST);
        return map;
    }

    public enum InviteEnvironmentEnum {

        LIVE("live"),
        TEST("test"),
        ;

        private final String value;

        private InviteEnvironmentEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
