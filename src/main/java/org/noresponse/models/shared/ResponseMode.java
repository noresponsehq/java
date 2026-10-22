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
 * ResponseMode
 *
 * <p>standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
 * the key has detailed scope. There is no raw mode.
 */
public class ResponseMode {

    public static final ResponseMode STANDARD = new ResponseMode("standard");
    public static final ResponseMode DETAILED = new ResponseMode("detailed");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ResponseMode> values = createValuesMap();
    private static final Map<String, ResponseModeEnum> enums = createEnumsMap();

    private final String value;

    private ResponseMode(String value) {
        this.value = value;
    }

    /**
     * Returns a ResponseMode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ResponseMode
     */
    @JsonCreator
    public static ResponseMode of(String value) {
        synchronized (ResponseMode.class) {
            return values.computeIfAbsent(value, v -> new ResponseMode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ResponseModeEnum> asEnum() {
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
        ResponseMode other = (ResponseMode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ResponseMode [value=" + value + "]";
    }

    // return an array just like an enum
    public static ResponseMode[] values() {
        synchronized (ResponseMode.class) {
            return values.values().toArray(new ResponseMode[] {});
        }
    }

    private static final Map<String, ResponseMode> createValuesMap() {
        Map<String, ResponseMode> map = new LinkedHashMap<>();
        map.put("standard", STANDARD);
        map.put("detailed", DETAILED);
        return map;
    }

    private static final Map<String, ResponseModeEnum> createEnumsMap() {
        Map<String, ResponseModeEnum> map = new HashMap<>();
        map.put("standard", ResponseModeEnum.STANDARD);
        map.put("detailed", ResponseModeEnum.DETAILED);
        return map;
    }

    public enum ResponseModeEnum {

        STANDARD("standard"),
        DETAILED("detailed"),
        ;

        private final String value;

        private ResponseModeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
