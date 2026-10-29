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
 * UsageCountDepth
 *
 * <p>assessments only.
 */
public class UsageCountDepth {

    public static final UsageCountDepth QUICK = new UsageCountDepth("quick");
    public static final UsageCountDepth FULL = new UsageCountDepth("full");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, UsageCountDepth> values = createValuesMap();
    private static final Map<String, UsageCountDepthEnum> enums = createEnumsMap();

    private final String value;

    private UsageCountDepth(String value) {
        this.value = value;
    }

    /**
     * Returns a UsageCountDepth with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as UsageCountDepth
     */
    @JsonCreator
    public static UsageCountDepth of(String value) {
        synchronized (UsageCountDepth.class) {
            return values.computeIfAbsent(value, v -> new UsageCountDepth(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<UsageCountDepthEnum> asEnum() {
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
        UsageCountDepth other = (UsageCountDepth) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "UsageCountDepth [value=" + value + "]";
    }

    // return an array just like an enum
    public static UsageCountDepth[] values() {
        synchronized (UsageCountDepth.class) {
            return values.values().toArray(new UsageCountDepth[] {});
        }
    }

    private static final Map<String, UsageCountDepth> createValuesMap() {
        Map<String, UsageCountDepth> map = new LinkedHashMap<>();
        map.put("quick", QUICK);
        map.put("full", FULL);
        return map;
    }

    private static final Map<String, UsageCountDepthEnum> createEnumsMap() {
        Map<String, UsageCountDepthEnum> map = new HashMap<>();
        map.put("quick", UsageCountDepthEnum.QUICK);
        map.put("full", UsageCountDepthEnum.FULL);
        return map;
    }

    public enum UsageCountDepthEnum {

        QUICK("quick"),
        FULL("full"),
        ;

        private final String value;

        private UsageCountDepthEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
