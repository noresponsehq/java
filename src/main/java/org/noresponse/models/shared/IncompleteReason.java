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
 * IncompleteReason
 *
 * <p>Why a lane did not settle. deferred means the lane is asynchronous and still running; a continuation
 * settles it. timed_out means the lane missed its deadline.
 *
 * <p>insufficient_evidence is a settled inconclusive signal rather than an incomplete lane.
 */
public class IncompleteReason {

    public static final IncompleteReason TIMED_OUT = new IncompleteReason("timed_out");
    public static final IncompleteReason RATE_LIMITED = new IncompleteReason("rate_limited");
    public static final IncompleteReason BLOCKED = new IncompleteReason("blocked");
    public static final IncompleteReason SOURCE_ERROR = new IncompleteReason("source_error");
    public static final IncompleteReason UNAVAILABLE = new IncompleteReason("unavailable");
    public static final IncompleteReason DEFERRED = new IncompleteReason("deferred");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, IncompleteReason> values = createValuesMap();
    private static final Map<String, IncompleteReasonEnum> enums = createEnumsMap();

    private final String value;

    private IncompleteReason(String value) {
        this.value = value;
    }

    /**
     * Returns a IncompleteReason with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as IncompleteReason
     */
    @JsonCreator
    public static IncompleteReason of(String value) {
        synchronized (IncompleteReason.class) {
            return values.computeIfAbsent(value, v -> new IncompleteReason(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<IncompleteReasonEnum> asEnum() {
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
        IncompleteReason other = (IncompleteReason) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "IncompleteReason [value=" + value + "]";
    }

    // return an array just like an enum
    public static IncompleteReason[] values() {
        synchronized (IncompleteReason.class) {
            return values.values().toArray(new IncompleteReason[] {});
        }
    }

    private static final Map<String, IncompleteReason> createValuesMap() {
        Map<String, IncompleteReason> map = new LinkedHashMap<>();
        map.put("timed_out", TIMED_OUT);
        map.put("rate_limited", RATE_LIMITED);
        map.put("blocked", BLOCKED);
        map.put("source_error", SOURCE_ERROR);
        map.put("unavailable", UNAVAILABLE);
        map.put("deferred", DEFERRED);
        return map;
    }

    private static final Map<String, IncompleteReasonEnum> createEnumsMap() {
        Map<String, IncompleteReasonEnum> map = new HashMap<>();
        map.put("timed_out", IncompleteReasonEnum.TIMED_OUT);
        map.put("rate_limited", IncompleteReasonEnum.RATE_LIMITED);
        map.put("blocked", IncompleteReasonEnum.BLOCKED);
        map.put("source_error", IncompleteReasonEnum.SOURCE_ERROR);
        map.put("unavailable", IncompleteReasonEnum.UNAVAILABLE);
        map.put("deferred", IncompleteReasonEnum.DEFERRED);
        return map;
    }

    public enum IncompleteReasonEnum {

        TIMED_OUT("timed_out"),
        RATE_LIMITED("rate_limited"),
        BLOCKED("blocked"),
        SOURCE_ERROR("source_error"),
        UNAVAILABLE("unavailable"),
        DEFERRED("deferred"),
        ;

        private final String value;

        private IncompleteReasonEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
