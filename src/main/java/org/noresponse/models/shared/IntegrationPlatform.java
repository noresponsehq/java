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
 * IntegrationPlatform
 *
 * <p>The authentication provider that calls the endpoint.
 */
public class IntegrationPlatform {

    public static final IntegrationPlatform WORKOS = new IntegrationPlatform("workos");
    public static final IntegrationPlatform AUTH0 = new IntegrationPlatform("auth0");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, IntegrationPlatform> values = createValuesMap();
    private static final Map<String, IntegrationPlatformEnum> enums = createEnumsMap();

    private final String value;

    private IntegrationPlatform(String value) {
        this.value = value;
    }

    /**
     * Returns a IntegrationPlatform with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as IntegrationPlatform
     */
    @JsonCreator
    public static IntegrationPlatform of(String value) {
        synchronized (IntegrationPlatform.class) {
            return values.computeIfAbsent(value, v -> new IntegrationPlatform(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<IntegrationPlatformEnum> asEnum() {
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
        IntegrationPlatform other = (IntegrationPlatform) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "IntegrationPlatform [value=" + value + "]";
    }

    // return an array just like an enum
    public static IntegrationPlatform[] values() {
        synchronized (IntegrationPlatform.class) {
            return values.values().toArray(new IntegrationPlatform[] {});
        }
    }

    private static final Map<String, IntegrationPlatform> createValuesMap() {
        Map<String, IntegrationPlatform> map = new LinkedHashMap<>();
        map.put("workos", WORKOS);
        map.put("auth0", AUTH0);
        return map;
    }

    private static final Map<String, IntegrationPlatformEnum> createEnumsMap() {
        Map<String, IntegrationPlatformEnum> map = new HashMap<>();
        map.put("workos", IntegrationPlatformEnum.WORKOS);
        map.put("auth0", IntegrationPlatformEnum.AUTH0);
        return map;
    }

    public enum IntegrationPlatformEnum {

        WORKOS("workos"),
        AUTH0("auth0"),
        ;

        private final String value;

        private IntegrationPlatformEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
