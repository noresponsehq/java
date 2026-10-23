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
 * Depth
 *
 * <p>How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
 * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
 * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
 * way.
 */
public class Depth {

    public static final Depth QUICK = new Depth("quick");
    public static final Depth FULL = new Depth("full");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Depth> values = createValuesMap();
    private static final Map<String, DepthEnum> enums = createEnumsMap();

    private final String value;

    private Depth(String value) {
        this.value = value;
    }

    /**
     * Returns a Depth with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Depth
     */
    @JsonCreator
    public static Depth of(String value) {
        synchronized (Depth.class) {
            return values.computeIfAbsent(value, v -> new Depth(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<DepthEnum> asEnum() {
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
        Depth other = (Depth) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Depth [value=" + value + "]";
    }

    // return an array just like an enum
    public static Depth[] values() {
        synchronized (Depth.class) {
            return values.values().toArray(new Depth[] {});
        }
    }

    private static final Map<String, Depth> createValuesMap() {
        Map<String, Depth> map = new LinkedHashMap<>();
        map.put("quick", QUICK);
        map.put("full", FULL);
        return map;
    }

    private static final Map<String, DepthEnum> createEnumsMap() {
        Map<String, DepthEnum> map = new HashMap<>();
        map.put("quick", DepthEnum.QUICK);
        map.put("full", DepthEnum.FULL);
        return map;
    }

    public enum DepthEnum {

        QUICK("quick"),
        FULL("full"),
        ;

        private final String value;

        private DepthEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
