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
 * Failure
 *
 * <p>Why the attempt failed: the URL is no longer valid, the host did not resolve, it resolved to an
 * address that is not public, no answer within 5 seconds, the connection failed, a 3xx (redirects are
 * not followed) or another non-2xx status. Absent for a delivered attempt.
 */
public class Failure {

    public static final Failure INVALID_URL = new Failure("invalid_url");
    public static final Failure RESOLVE_FAILED = new Failure("resolve_failed");
    public static final Failure ADDRESS_REFUSED = new Failure("address_refused");
    public static final Failure TIMEOUT = new Failure("timeout");
    public static final Failure CONNECTION_FAILED = new Failure("connection_failed");
    public static final Failure REDIRECT = new Failure("redirect");
    public static final Failure NON2XX = new Failure("non_2xx");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Failure> values = createValuesMap();
    private static final Map<String, FailureEnum> enums = createEnumsMap();

    private final String value;

    private Failure(String value) {
        this.value = value;
    }

    /**
     * Returns a Failure with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Failure
     */
    @JsonCreator
    public static Failure of(String value) {
        synchronized (Failure.class) {
            return values.computeIfAbsent(value, v -> new Failure(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<FailureEnum> asEnum() {
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
        Failure other = (Failure) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Failure [value=" + value + "]";
    }

    // return an array just like an enum
    public static Failure[] values() {
        synchronized (Failure.class) {
            return values.values().toArray(new Failure[] {});
        }
    }

    private static final Map<String, Failure> createValuesMap() {
        Map<String, Failure> map = new LinkedHashMap<>();
        map.put("invalid_url", INVALID_URL);
        map.put("resolve_failed", RESOLVE_FAILED);
        map.put("address_refused", ADDRESS_REFUSED);
        map.put("timeout", TIMEOUT);
        map.put("connection_failed", CONNECTION_FAILED);
        map.put("redirect", REDIRECT);
        map.put("non_2xx", NON2XX);
        return map;
    }

    private static final Map<String, FailureEnum> createEnumsMap() {
        Map<String, FailureEnum> map = new HashMap<>();
        map.put("invalid_url", FailureEnum.INVALID_URL);
        map.put("resolve_failed", FailureEnum.RESOLVE_FAILED);
        map.put("address_refused", FailureEnum.ADDRESS_REFUSED);
        map.put("timeout", FailureEnum.TIMEOUT);
        map.put("connection_failed", FailureEnum.CONNECTION_FAILED);
        map.put("redirect", FailureEnum.REDIRECT);
        map.put("non_2xx", FailureEnum.NON2XX);
        return map;
    }

    public enum FailureEnum {

        INVALID_URL("invalid_url"),
        RESOLVE_FAILED("resolve_failed"),
        ADDRESS_REFUSED("address_refused"),
        TIMEOUT("timeout"),
        CONNECTION_FAILED("connection_failed"),
        REDIRECT("redirect"),
        NON2XX("non_2xx"),
        ;

        private final String value;

        private FailureEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
