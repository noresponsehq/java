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
 * WrittenProfileErrorCode
 *
 * <p>Present only when status is failed. no_writer: no profile writer serves this environment. refused:
 * the model refused.
 *
 * <p>not_written: the answer was not a profile, or no statement survived grounding. uncited_statement: a
 * statement or lead cited a record the person does not hold. not_answered: the writer did not answer.
 *
 * <p>worker_attempts_exhausted: the worker stopped retrying.
 */
public class WrittenProfileErrorCode {

    public static final WrittenProfileErrorCode NO_WRITER = new WrittenProfileErrorCode("no_writer");
    public static final WrittenProfileErrorCode REFUSED = new WrittenProfileErrorCode("refused");
    public static final WrittenProfileErrorCode NOT_WRITTEN = new WrittenProfileErrorCode("not_written");
    public static final WrittenProfileErrorCode UNCITED_STATEMENT = new WrittenProfileErrorCode("uncited_statement");
    public static final WrittenProfileErrorCode NOT_ANSWERED = new WrittenProfileErrorCode("not_answered");
    public static final WrittenProfileErrorCode WORKER_ATTEMPTS_EXHAUSTED =
            new WrittenProfileErrorCode("worker_attempts_exhausted");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, WrittenProfileErrorCode> values = createValuesMap();
    private static final Map<String, WrittenProfileErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private WrittenProfileErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a WrittenProfileErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as WrittenProfileErrorCode
     */
    @JsonCreator
    public static WrittenProfileErrorCode of(String value) {
        synchronized (WrittenProfileErrorCode.class) {
            return values.computeIfAbsent(value, v -> new WrittenProfileErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<WrittenProfileErrorCodeEnum> asEnum() {
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
        WrittenProfileErrorCode other = (WrittenProfileErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "WrittenProfileErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static WrittenProfileErrorCode[] values() {
        synchronized (WrittenProfileErrorCode.class) {
            return values.values().toArray(new WrittenProfileErrorCode[] {});
        }
    }

    private static final Map<String, WrittenProfileErrorCode> createValuesMap() {
        Map<String, WrittenProfileErrorCode> map = new LinkedHashMap<>();
        map.put("no_writer", NO_WRITER);
        map.put("refused", REFUSED);
        map.put("not_written", NOT_WRITTEN);
        map.put("uncited_statement", UNCITED_STATEMENT);
        map.put("not_answered", NOT_ANSWERED);
        map.put("worker_attempts_exhausted", WORKER_ATTEMPTS_EXHAUSTED);
        return map;
    }

    private static final Map<String, WrittenProfileErrorCodeEnum> createEnumsMap() {
        Map<String, WrittenProfileErrorCodeEnum> map = new HashMap<>();
        map.put("no_writer", WrittenProfileErrorCodeEnum.NO_WRITER);
        map.put("refused", WrittenProfileErrorCodeEnum.REFUSED);
        map.put("not_written", WrittenProfileErrorCodeEnum.NOT_WRITTEN);
        map.put("uncited_statement", WrittenProfileErrorCodeEnum.UNCITED_STATEMENT);
        map.put("not_answered", WrittenProfileErrorCodeEnum.NOT_ANSWERED);
        map.put("worker_attempts_exhausted", WrittenProfileErrorCodeEnum.WORKER_ATTEMPTS_EXHAUSTED);
        return map;
    }

    public enum WrittenProfileErrorCodeEnum {

        NO_WRITER("no_writer"),
        REFUSED("refused"),
        NOT_WRITTEN("not_written"),
        UNCITED_STATEMENT("uncited_statement"),
        NOT_ANSWERED("not_answered"),
        WORKER_ATTEMPTS_EXHAUSTED("worker_attempts_exhausted"),
        ;

        private final String value;

        private WrittenProfileErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
