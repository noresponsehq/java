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
 * AuthenticationErrorCode
 *
 * <p>Machine codes an HTTP 401 answer can carry.
 */
public class AuthenticationErrorCode {

    public static final AuthenticationErrorCode UNAUTHORIZED = new AuthenticationErrorCode("unauthorized");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AuthenticationErrorCode> values = createValuesMap();
    private static final Map<String, AuthenticationErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private AuthenticationErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a AuthenticationErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AuthenticationErrorCode
     */
    @JsonCreator
    public static AuthenticationErrorCode of(String value) {
        synchronized (AuthenticationErrorCode.class) {
            return values.computeIfAbsent(value, v -> new AuthenticationErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AuthenticationErrorCodeEnum> asEnum() {
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
        AuthenticationErrorCode other = (AuthenticationErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AuthenticationErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static AuthenticationErrorCode[] values() {
        synchronized (AuthenticationErrorCode.class) {
            return values.values().toArray(new AuthenticationErrorCode[] {});
        }
    }

    private static final Map<String, AuthenticationErrorCode> createValuesMap() {
        Map<String, AuthenticationErrorCode> map = new LinkedHashMap<>();
        map.put("unauthorized", UNAUTHORIZED);
        return map;
    }

    private static final Map<String, AuthenticationErrorCodeEnum> createEnumsMap() {
        Map<String, AuthenticationErrorCodeEnum> map = new HashMap<>();
        map.put("unauthorized", AuthenticationErrorCodeEnum.UNAUTHORIZED);
        return map;
    }

    public enum AuthenticationErrorCodeEnum {

        UNAUTHORIZED("unauthorized"),
        ;

        private final String value;

        private AuthenticationErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
