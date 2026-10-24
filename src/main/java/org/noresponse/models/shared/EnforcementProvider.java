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
 * EnforcementProvider
 *
 * <p>The identity provider the action ran in; custom for the workspace's own user store.
 */
public class EnforcementProvider {

    public static final EnforcementProvider CLERK = new EnforcementProvider("clerk");
    public static final EnforcementProvider WORKOS = new EnforcementProvider("workos");
    public static final EnforcementProvider AUTH0 = new EnforcementProvider("auth0");
    public static final EnforcementProvider CUSTOM = new EnforcementProvider("custom");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, EnforcementProvider> values = createValuesMap();
    private static final Map<String, EnforcementProviderEnum> enums = createEnumsMap();

    private final String value;

    private EnforcementProvider(String value) {
        this.value = value;
    }

    /**
     * Returns a EnforcementProvider with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as EnforcementProvider
     */
    @JsonCreator
    public static EnforcementProvider of(String value) {
        synchronized (EnforcementProvider.class) {
            return values.computeIfAbsent(value, v -> new EnforcementProvider(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EnforcementProviderEnum> asEnum() {
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
        EnforcementProvider other = (EnforcementProvider) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "EnforcementProvider [value=" + value + "]";
    }

    // return an array just like an enum
    public static EnforcementProvider[] values() {
        synchronized (EnforcementProvider.class) {
            return values.values().toArray(new EnforcementProvider[] {});
        }
    }

    private static final Map<String, EnforcementProvider> createValuesMap() {
        Map<String, EnforcementProvider> map = new LinkedHashMap<>();
        map.put("clerk", CLERK);
        map.put("workos", WORKOS);
        map.put("auth0", AUTH0);
        map.put("custom", CUSTOM);
        return map;
    }

    private static final Map<String, EnforcementProviderEnum> createEnumsMap() {
        Map<String, EnforcementProviderEnum> map = new HashMap<>();
        map.put("clerk", EnforcementProviderEnum.CLERK);
        map.put("workos", EnforcementProviderEnum.WORKOS);
        map.put("auth0", EnforcementProviderEnum.AUTH0);
        map.put("custom", EnforcementProviderEnum.CUSTOM);
        return map;
    }

    public enum EnforcementProviderEnum {

        CLERK("clerk"),
        WORKOS("workos"),
        AUTH0("auth0"),
        CUSTOM("custom"),
        ;

        private final String value;

        private EnforcementProviderEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
