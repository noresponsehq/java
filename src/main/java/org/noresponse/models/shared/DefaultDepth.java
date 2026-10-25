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
 * DefaultDepth
 *
 * <p>The depth a request for this flow that names none is served at. Defaults: full for account_creation
 * and purchase, quick for login.
 */
public class DefaultDepth {

    public static final DefaultDepth QUICK = new DefaultDepth("quick");
    public static final DefaultDepth FULL = new DefaultDepth("full");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, DefaultDepth> values = createValuesMap();
    private static final Map<String, DefaultDepthEnum> enums = createEnumsMap();

    private final String value;

    private DefaultDepth(String value) {
        this.value = value;
    }

    /**
     * Returns a DefaultDepth with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as DefaultDepth
     */
    @JsonCreator
    public static DefaultDepth of(String value) {
        synchronized (DefaultDepth.class) {
            return values.computeIfAbsent(value, v -> new DefaultDepth(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<DefaultDepthEnum> asEnum() {
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
        DefaultDepth other = (DefaultDepth) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "DefaultDepth [value=" + value + "]";
    }

    // return an array just like an enum
    public static DefaultDepth[] values() {
        synchronized (DefaultDepth.class) {
            return values.values().toArray(new DefaultDepth[] {});
        }
    }

    private static final Map<String, DefaultDepth> createValuesMap() {
        Map<String, DefaultDepth> map = new LinkedHashMap<>();
        map.put("quick", QUICK);
        map.put("full", FULL);
        return map;
    }

    private static final Map<String, DefaultDepthEnum> createEnumsMap() {
        Map<String, DefaultDepthEnum> map = new HashMap<>();
        map.put("quick", DefaultDepthEnum.QUICK);
        map.put("full", DefaultDepthEnum.FULL);
        return map;
    }

    public enum DefaultDepthEnum {

        QUICK("quick"),
        FULL("full"),
        ;

        private final String value;

        private DefaultDepthEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
