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
public class MemberEnvironment {

    public static final MemberEnvironment LIVE = new MemberEnvironment("live");
    public static final MemberEnvironment TEST = new MemberEnvironment("test");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, MemberEnvironment> values = createValuesMap();
    private static final Map<String, MemberEnvironmentEnum> enums = createEnumsMap();

    private final String value;

    private MemberEnvironment(String value) {
        this.value = value;
    }

    /**
     * Returns a MemberEnvironment with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as MemberEnvironment
     */
    @JsonCreator
    public static MemberEnvironment of(String value) {
        synchronized (MemberEnvironment.class) {
            return values.computeIfAbsent(value, v -> new MemberEnvironment(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MemberEnvironmentEnum> asEnum() {
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
        MemberEnvironment other = (MemberEnvironment) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "MemberEnvironment [value=" + value + "]";
    }

    // return an array just like an enum
    public static MemberEnvironment[] values() {
        synchronized (MemberEnvironment.class) {
            return values.values().toArray(new MemberEnvironment[] {});
        }
    }

    private static final Map<String, MemberEnvironment> createValuesMap() {
        Map<String, MemberEnvironment> map = new LinkedHashMap<>();
        map.put("live", LIVE);
        map.put("test", TEST);
        return map;
    }

    private static final Map<String, MemberEnvironmentEnum> createEnumsMap() {
        Map<String, MemberEnvironmentEnum> map = new HashMap<>();
        map.put("live", MemberEnvironmentEnum.LIVE);
        map.put("test", MemberEnvironmentEnum.TEST);
        return map;
    }

    public enum MemberEnvironmentEnum {

        LIVE("live"),
        TEST("test"),
        ;

        private final String value;

        private MemberEnvironmentEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
