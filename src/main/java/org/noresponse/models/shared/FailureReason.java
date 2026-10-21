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
 * FailureReason
 *
 * <p>Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
 * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
 * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
 */
public class FailureReason {

    public static final FailureReason TIMED_OUT = new FailureReason("timed_out");
    public static final FailureReason RATE_LIMITED = new FailureReason("rate_limited");
    public static final FailureReason BLOCKED = new FailureReason("blocked");
    public static final FailureReason SOURCE_ERROR = new FailureReason("source_error");
    public static final FailureReason UNAVAILABLE = new FailureReason("unavailable");
    public static final FailureReason DEFERRED = new FailureReason("deferred");
    public static final FailureReason INSUFFICIENT_EVIDENCE = new FailureReason("insufficient_evidence");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, FailureReason> values = createValuesMap();
    private static final Map<String, FailureReasonEnum> enums = createEnumsMap();

    private final String value;

    private FailureReason(String value) {
        this.value = value;
    }

    /**
     * Returns a FailureReason with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as FailureReason
     */
    @JsonCreator
    public static FailureReason of(String value) {
        synchronized (FailureReason.class) {
            return values.computeIfAbsent(value, v -> new FailureReason(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<FailureReasonEnum> asEnum() {
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
        FailureReason other = (FailureReason) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "FailureReason [value=" + value + "]";
    }

    // return an array just like an enum
    public static FailureReason[] values() {
        synchronized (FailureReason.class) {
            return values.values().toArray(new FailureReason[] {});
        }
    }

    private static final Map<String, FailureReason> createValuesMap() {
        Map<String, FailureReason> map = new LinkedHashMap<>();
        map.put("timed_out", TIMED_OUT);
        map.put("rate_limited", RATE_LIMITED);
        map.put("blocked", BLOCKED);
        map.put("source_error", SOURCE_ERROR);
        map.put("unavailable", UNAVAILABLE);
        map.put("deferred", DEFERRED);
        map.put("insufficient_evidence", INSUFFICIENT_EVIDENCE);
        return map;
    }

    private static final Map<String, FailureReasonEnum> createEnumsMap() {
        Map<String, FailureReasonEnum> map = new HashMap<>();
        map.put("timed_out", FailureReasonEnum.TIMED_OUT);
        map.put("rate_limited", FailureReasonEnum.RATE_LIMITED);
        map.put("blocked", FailureReasonEnum.BLOCKED);
        map.put("source_error", FailureReasonEnum.SOURCE_ERROR);
        map.put("unavailable", FailureReasonEnum.UNAVAILABLE);
        map.put("deferred", FailureReasonEnum.DEFERRED);
        map.put("insufficient_evidence", FailureReasonEnum.INSUFFICIENT_EVIDENCE);
        return map;
    }

    public enum FailureReasonEnum {

        TIMED_OUT("timed_out"),
        RATE_LIMITED("rate_limited"),
        BLOCKED("blocked"),
        SOURCE_ERROR("source_error"),
        UNAVAILABLE("unavailable"),
        DEFERRED("deferred"),
        INSUFFICIENT_EVIDENCE("insufficient_evidence"),
        ;

        private final String value;

        private FailureReasonEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
