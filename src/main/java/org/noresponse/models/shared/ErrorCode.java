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
 * ErrorCode
 *
 * <p>Why a failed search found nothing: its sources did not answer, none could be asked, or it could not
 * run before its attempts were spent.
 */
public class ErrorCode {

    public static final ErrorCode NOT_ANSWERED = new ErrorCode("not_answered");
    public static final ErrorCode NO_SOURCE = new ErrorCode("no_source");
    public static final ErrorCode WORKER_ATTEMPTS_EXHAUSTED = new ErrorCode("worker_attempts_exhausted");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ErrorCode> values = createValuesMap();
    private static final Map<String, ErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private ErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a ErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ErrorCode
     */
    @JsonCreator
    public static ErrorCode of(String value) {
        synchronized (ErrorCode.class) {
            return values.computeIfAbsent(value, v -> new ErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ErrorCodeEnum> asEnum() {
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
        ErrorCode other = (ErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static ErrorCode[] values() {
        synchronized (ErrorCode.class) {
            return values.values().toArray(new ErrorCode[] {});
        }
    }

    private static final Map<String, ErrorCode> createValuesMap() {
        Map<String, ErrorCode> map = new LinkedHashMap<>();
        map.put("not_answered", NOT_ANSWERED);
        map.put("no_source", NO_SOURCE);
        map.put("worker_attempts_exhausted", WORKER_ATTEMPTS_EXHAUSTED);
        return map;
    }

    private static final Map<String, ErrorCodeEnum> createEnumsMap() {
        Map<String, ErrorCodeEnum> map = new HashMap<>();
        map.put("not_answered", ErrorCodeEnum.NOT_ANSWERED);
        map.put("no_source", ErrorCodeEnum.NO_SOURCE);
        map.put("worker_attempts_exhausted", ErrorCodeEnum.WORKER_ATTEMPTS_EXHAUSTED);
        return map;
    }

    public enum ErrorCodeEnum {

        NOT_ANSWERED("not_answered"),
        NO_SOURCE("no_source"),
        WORKER_ATTEMPTS_EXHAUSTED("worker_attempts_exhausted"),
        ;

        private final String value;

        private ErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
