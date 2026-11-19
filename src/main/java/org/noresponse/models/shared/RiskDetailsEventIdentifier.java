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
public class RiskDetailsEventIdentifier {

    public static final RiskDetailsEventIdentifier EMAIL = new RiskDetailsEventIdentifier("email");
    public static final RiskDetailsEventIdentifier USERNAME = new RiskDetailsEventIdentifier("username");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RiskDetailsEventIdentifier> values = createValuesMap();
    private static final Map<String, RiskDetailsEventIdentifierEnum> enums = createEnumsMap();

    private final String value;

    private RiskDetailsEventIdentifier(String value) {
        this.value = value;
    }

    /**
     * Returns a RiskDetailsEventIdentifier with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RiskDetailsEventIdentifier
     */
    @JsonCreator
    public static RiskDetailsEventIdentifier of(String value) {
        synchronized (RiskDetailsEventIdentifier.class) {
            return values.computeIfAbsent(value, v -> new RiskDetailsEventIdentifier(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RiskDetailsEventIdentifierEnum> asEnum() {
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
        RiskDetailsEventIdentifier other = (RiskDetailsEventIdentifier) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RiskDetailsEventIdentifier [value=" + value + "]";
    }

    // return an array just like an enum
    public static RiskDetailsEventIdentifier[] values() {
        synchronized (RiskDetailsEventIdentifier.class) {
            return values.values().toArray(new RiskDetailsEventIdentifier[] {});
        }
    }

    private static final Map<String, RiskDetailsEventIdentifier> createValuesMap() {
        Map<String, RiskDetailsEventIdentifier> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("username", USERNAME);
        return map;
    }

    private static final Map<String, RiskDetailsEventIdentifierEnum> createEnumsMap() {
        Map<String, RiskDetailsEventIdentifierEnum> map = new HashMap<>();
        map.put("email", RiskDetailsEventIdentifierEnum.EMAIL);
        map.put("username", RiskDetailsEventIdentifierEnum.USERNAME);
        return map;
    }

    public enum RiskDetailsEventIdentifierEnum {

        EMAIL("email"),
        USERNAME("username"),
        ;

        private final String value;

        private RiskDetailsEventIdentifierEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
