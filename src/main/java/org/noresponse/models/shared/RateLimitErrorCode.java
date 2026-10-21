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
 * RateLimitErrorCode
 *
 * <p>Machine codes an HTTP 429 answer can carry.
 */
public class RateLimitErrorCode {

    public static final RateLimitErrorCode RATE_LIMITED = new RateLimitErrorCode("rate_limited");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RateLimitErrorCode> values = createValuesMap();
    private static final Map<String, RateLimitErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private RateLimitErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a RateLimitErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RateLimitErrorCode
     */
    @JsonCreator
    public static RateLimitErrorCode of(String value) {
        synchronized (RateLimitErrorCode.class) {
            return values.computeIfAbsent(value, v -> new RateLimitErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RateLimitErrorCodeEnum> asEnum() {
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
        RateLimitErrorCode other = (RateLimitErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RateLimitErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static RateLimitErrorCode[] values() {
        synchronized (RateLimitErrorCode.class) {
            return values.values().toArray(new RateLimitErrorCode[] {});
        }
    }

    private static final Map<String, RateLimitErrorCode> createValuesMap() {
        Map<String, RateLimitErrorCode> map = new LinkedHashMap<>();
        map.put("rate_limited", RATE_LIMITED);
        return map;
    }

    private static final Map<String, RateLimitErrorCodeEnum> createEnumsMap() {
        Map<String, RateLimitErrorCodeEnum> map = new HashMap<>();
        map.put("rate_limited", RateLimitErrorCodeEnum.RATE_LIMITED);
        return map;
    }

    public enum RateLimitErrorCodeEnum {

        RATE_LIMITED("rate_limited"),
        ;

        private final String value;

        private RateLimitErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
