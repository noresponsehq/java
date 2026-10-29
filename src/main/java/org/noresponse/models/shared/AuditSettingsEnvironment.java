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
public class AuditSettingsEnvironment {

    public static final AuditSettingsEnvironment LIVE = new AuditSettingsEnvironment("live");
    public static final AuditSettingsEnvironment TEST = new AuditSettingsEnvironment("test");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AuditSettingsEnvironment> values = createValuesMap();
    private static final Map<String, AuditSettingsEnvironmentEnum> enums = createEnumsMap();

    private final String value;

    private AuditSettingsEnvironment(String value) {
        this.value = value;
    }

    /**
     * Returns a AuditSettingsEnvironment with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AuditSettingsEnvironment
     */
    @JsonCreator
    public static AuditSettingsEnvironment of(String value) {
        synchronized (AuditSettingsEnvironment.class) {
            return values.computeIfAbsent(value, v -> new AuditSettingsEnvironment(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AuditSettingsEnvironmentEnum> asEnum() {
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
        AuditSettingsEnvironment other = (AuditSettingsEnvironment) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AuditSettingsEnvironment [value=" + value + "]";
    }

    // return an array just like an enum
    public static AuditSettingsEnvironment[] values() {
        synchronized (AuditSettingsEnvironment.class) {
            return values.values().toArray(new AuditSettingsEnvironment[] {});
        }
    }

    private static final Map<String, AuditSettingsEnvironment> createValuesMap() {
        Map<String, AuditSettingsEnvironment> map = new LinkedHashMap<>();
        map.put("live", LIVE);
        map.put("test", TEST);
        return map;
    }

    private static final Map<String, AuditSettingsEnvironmentEnum> createEnumsMap() {
        Map<String, AuditSettingsEnvironmentEnum> map = new HashMap<>();
        map.put("live", AuditSettingsEnvironmentEnum.LIVE);
        map.put("test", AuditSettingsEnvironmentEnum.TEST);
        return map;
    }

    public enum AuditSettingsEnvironmentEnum {

        LIVE("live"),
        TEST("test"),
        ;

        private final String value;

        private AuditSettingsEnvironmentEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
