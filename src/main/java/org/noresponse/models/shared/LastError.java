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
public class LastError {

    public static final LastError SCREENING_CAP_EXCEEDED = new LastError("screening_cap_exceeded");
    public static final LastError SCREENING_UNAVAILABLE = new LastError("screening_unavailable");
    public static final LastError PERSON_NOT_FOUND = new LastError("person_not_found");
    public static final LastError IDEMPOTENCY_KEY_REUSED = new LastError("idempotency_key_reused");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, LastError> values = createValuesMap();
    private static final Map<String, LastErrorEnum> enums = createEnumsMap();

    private final String value;

    private LastError(String value) {
        this.value = value;
    }

    /**
     * Returns a LastError with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as LastError
     */
    @JsonCreator
    public static LastError of(String value) {
        synchronized (LastError.class) {
            return values.computeIfAbsent(value, v -> new LastError(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<LastErrorEnum> asEnum() {
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
        LastError other = (LastError) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "LastError [value=" + value + "]";
    }

    // return an array just like an enum
    public static LastError[] values() {
        synchronized (LastError.class) {
            return values.values().toArray(new LastError[] {});
        }
    }

    private static final Map<String, LastError> createValuesMap() {
        Map<String, LastError> map = new LinkedHashMap<>();
        map.put("screening_cap_exceeded", SCREENING_CAP_EXCEEDED);
        map.put("screening_unavailable", SCREENING_UNAVAILABLE);
        map.put("person_not_found", PERSON_NOT_FOUND);
        map.put("idempotency_key_reused", IDEMPOTENCY_KEY_REUSED);
        return map;
    }

    private static final Map<String, LastErrorEnum> createEnumsMap() {
        Map<String, LastErrorEnum> map = new HashMap<>();
        map.put("screening_cap_exceeded", LastErrorEnum.SCREENING_CAP_EXCEEDED);
        map.put("screening_unavailable", LastErrorEnum.SCREENING_UNAVAILABLE);
        map.put("person_not_found", LastErrorEnum.PERSON_NOT_FOUND);
        map.put("idempotency_key_reused", LastErrorEnum.IDEMPOTENCY_KEY_REUSED);
        return map;
    }

    public enum LastErrorEnum {

        SCREENING_CAP_EXCEEDED("screening_cap_exceeded"),
        SCREENING_UNAVAILABLE("screening_unavailable"),
        PERSON_NOT_FOUND("person_not_found"),
        IDEMPOTENCY_KEY_REUSED("idempotency_key_reused"),
        ;

        private final String value;

        private LastErrorEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
