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
 * PermissionDeniedErrorCode
 *
 * <p>Machine codes an HTTP 403 answer can carry.
 */
public class PermissionDeniedErrorCode {

    public static final PermissionDeniedErrorCode WORKSPACE_FORBIDDEN =
            new PermissionDeniedErrorCode("workspace_forbidden");
    public static final PermissionDeniedErrorCode ROLE_FORBIDDEN = new PermissionDeniedErrorCode("role_forbidden");
    public static final PermissionDeniedErrorCode EMAIL_UNVERIFIED = new PermissionDeniedErrorCode("email_unverified");
    public static final PermissionDeniedErrorCode ONBOARDING_IN_REVIEW =
            new PermissionDeniedErrorCode("onboarding_in_review");
    public static final PermissionDeniedErrorCode ONBOARDING_REFUSED =
            new PermissionDeniedErrorCode("onboarding_refused");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, PermissionDeniedErrorCode> values = createValuesMap();
    private static final Map<String, PermissionDeniedErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private PermissionDeniedErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a PermissionDeniedErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as PermissionDeniedErrorCode
     */
    @JsonCreator
    public static PermissionDeniedErrorCode of(String value) {
        synchronized (PermissionDeniedErrorCode.class) {
            return values.computeIfAbsent(value, v -> new PermissionDeniedErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PermissionDeniedErrorCodeEnum> asEnum() {
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
        PermissionDeniedErrorCode other = (PermissionDeniedErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "PermissionDeniedErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static PermissionDeniedErrorCode[] values() {
        synchronized (PermissionDeniedErrorCode.class) {
            return values.values().toArray(new PermissionDeniedErrorCode[] {});
        }
    }

    private static final Map<String, PermissionDeniedErrorCode> createValuesMap() {
        Map<String, PermissionDeniedErrorCode> map = new LinkedHashMap<>();
        map.put("workspace_forbidden", WORKSPACE_FORBIDDEN);
        map.put("role_forbidden", ROLE_FORBIDDEN);
        map.put("email_unverified", EMAIL_UNVERIFIED);
        map.put("onboarding_in_review", ONBOARDING_IN_REVIEW);
        map.put("onboarding_refused", ONBOARDING_REFUSED);
        return map;
    }

    private static final Map<String, PermissionDeniedErrorCodeEnum> createEnumsMap() {
        Map<String, PermissionDeniedErrorCodeEnum> map = new HashMap<>();
        map.put("workspace_forbidden", PermissionDeniedErrorCodeEnum.WORKSPACE_FORBIDDEN);
        map.put("role_forbidden", PermissionDeniedErrorCodeEnum.ROLE_FORBIDDEN);
        map.put("email_unverified", PermissionDeniedErrorCodeEnum.EMAIL_UNVERIFIED);
        map.put("onboarding_in_review", PermissionDeniedErrorCodeEnum.ONBOARDING_IN_REVIEW);
        map.put("onboarding_refused", PermissionDeniedErrorCodeEnum.ONBOARDING_REFUSED);
        return map;
    }

    public enum PermissionDeniedErrorCodeEnum {

        WORKSPACE_FORBIDDEN("workspace_forbidden"),
        ROLE_FORBIDDEN("role_forbidden"),
        EMAIL_UNVERIFIED("email_unverified"),
        ONBOARDING_IN_REVIEW("onboarding_in_review"),
        ONBOARDING_REFUSED("onboarding_refused"),
        ;

        private final String value;

        private PermissionDeniedErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
