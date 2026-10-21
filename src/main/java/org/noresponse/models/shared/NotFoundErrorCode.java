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
 * NotFoundErrorCode
 *
 * <p>Machine codes an HTTP 404 answer can carry.
 */
public class NotFoundErrorCode {

    public static final NotFoundErrorCode NOT_FOUND = new NotFoundErrorCode("not_found");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, NotFoundErrorCode> values = createValuesMap();
    private static final Map<String, NotFoundErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private NotFoundErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a NotFoundErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as NotFoundErrorCode
     */
    @JsonCreator
    public static NotFoundErrorCode of(String value) {
        synchronized (NotFoundErrorCode.class) {
            return values.computeIfAbsent(value, v -> new NotFoundErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<NotFoundErrorCodeEnum> asEnum() {
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
        NotFoundErrorCode other = (NotFoundErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "NotFoundErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static NotFoundErrorCode[] values() {
        synchronized (NotFoundErrorCode.class) {
            return values.values().toArray(new NotFoundErrorCode[] {});
        }
    }

    private static final Map<String, NotFoundErrorCode> createValuesMap() {
        Map<String, NotFoundErrorCode> map = new LinkedHashMap<>();
        map.put("not_found", NOT_FOUND);
        return map;
    }

    private static final Map<String, NotFoundErrorCodeEnum> createEnumsMap() {
        Map<String, NotFoundErrorCodeEnum> map = new HashMap<>();
        map.put("not_found", NotFoundErrorCodeEnum.NOT_FOUND);
        return map;
    }

    public enum NotFoundErrorCodeEnum {

        NOT_FOUND("not_found"),
        ;

        private final String value;

        private NotFoundErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
