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
public class ScreeningDepth {

    public static final ScreeningDepth STANDARD = new ScreeningDepth("standard");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningDepth> values = createValuesMap();
    private static final Map<String, ScreeningDepthEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningDepth(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningDepth with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningDepth
     */
    @JsonCreator
    public static ScreeningDepth of(String value) {
        synchronized (ScreeningDepth.class) {
            return values.computeIfAbsent(value, v -> new ScreeningDepth(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningDepthEnum> asEnum() {
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
        ScreeningDepth other = (ScreeningDepth) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningDepth [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningDepth[] values() {
        synchronized (ScreeningDepth.class) {
            return values.values().toArray(new ScreeningDepth[] {});
        }
    }

    private static final Map<String, ScreeningDepth> createValuesMap() {
        Map<String, ScreeningDepth> map = new LinkedHashMap<>();
        map.put("standard", STANDARD);
        return map;
    }

    private static final Map<String, ScreeningDepthEnum> createEnumsMap() {
        Map<String, ScreeningDepthEnum> map = new HashMap<>();
        map.put("standard", ScreeningDepthEnum.STANDARD);
        return map;
    }

    public enum ScreeningDepthEnum {

        STANDARD("standard"),
        ;

        private final String value;

        private ScreeningDepthEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
