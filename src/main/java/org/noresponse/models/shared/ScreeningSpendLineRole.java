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
public class ScreeningSpendLineRole {

    public static final ScreeningSpendLineRole RESOLVER = new ScreeningSpendLineRole("resolver");
    public static final ScreeningSpendLineRole COLLECTOR = new ScreeningSpendLineRole("collector");
    public static final ScreeningSpendLineRole ANALYSER = new ScreeningSpendLineRole("analyser");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningSpendLineRole> values = createValuesMap();
    private static final Map<String, ScreeningSpendLineRoleEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningSpendLineRole(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningSpendLineRole with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningSpendLineRole
     */
    @JsonCreator
    public static ScreeningSpendLineRole of(String value) {
        synchronized (ScreeningSpendLineRole.class) {
            return values.computeIfAbsent(value, v -> new ScreeningSpendLineRole(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningSpendLineRoleEnum> asEnum() {
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
        ScreeningSpendLineRole other = (ScreeningSpendLineRole) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningSpendLineRole [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningSpendLineRole[] values() {
        synchronized (ScreeningSpendLineRole.class) {
            return values.values().toArray(new ScreeningSpendLineRole[] {});
        }
    }

    private static final Map<String, ScreeningSpendLineRole> createValuesMap() {
        Map<String, ScreeningSpendLineRole> map = new LinkedHashMap<>();
        map.put("resolver", RESOLVER);
        map.put("collector", COLLECTOR);
        map.put("analyser", ANALYSER);
        return map;
    }

    private static final Map<String, ScreeningSpendLineRoleEnum> createEnumsMap() {
        Map<String, ScreeningSpendLineRoleEnum> map = new HashMap<>();
        map.put("resolver", ScreeningSpendLineRoleEnum.RESOLVER);
        map.put("collector", ScreeningSpendLineRoleEnum.COLLECTOR);
        map.put("analyser", ScreeningSpendLineRoleEnum.ANALYSER);
        return map;
    }

    public enum ScreeningSpendLineRoleEnum {

        RESOLVER("resolver"),
        COLLECTOR("collector"),
        ANALYSER("analyser"),
        ;

        private final String value;

        private ScreeningSpendLineRoleEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
