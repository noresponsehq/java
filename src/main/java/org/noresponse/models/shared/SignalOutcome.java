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
 * SignalOutcome
 *
 * <p>Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
 * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
 */
public class SignalOutcome {

    public static final SignalOutcome FOUND = new SignalOutcome("found");
    public static final SignalOutcome NOT_FOUND = new SignalOutcome("not_found");
    public static final SignalOutcome INCONCLUSIVE = new SignalOutcome("inconclusive");
    public static final SignalOutcome BLOCKED = new SignalOutcome("blocked");
    public static final SignalOutcome UNAVAILABLE = new SignalOutcome("unavailable");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, SignalOutcome> values = createValuesMap();
    private static final Map<String, SignalOutcomeEnum> enums = createEnumsMap();

    private final String value;

    private SignalOutcome(String value) {
        this.value = value;
    }

    /**
     * Returns a SignalOutcome with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as SignalOutcome
     */
    @JsonCreator
    public static SignalOutcome of(String value) {
        synchronized (SignalOutcome.class) {
            return values.computeIfAbsent(value, v -> new SignalOutcome(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SignalOutcomeEnum> asEnum() {
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
        SignalOutcome other = (SignalOutcome) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "SignalOutcome [value=" + value + "]";
    }

    // return an array just like an enum
    public static SignalOutcome[] values() {
        synchronized (SignalOutcome.class) {
            return values.values().toArray(new SignalOutcome[] {});
        }
    }

    private static final Map<String, SignalOutcome> createValuesMap() {
        Map<String, SignalOutcome> map = new LinkedHashMap<>();
        map.put("found", FOUND);
        map.put("not_found", NOT_FOUND);
        map.put("inconclusive", INCONCLUSIVE);
        map.put("blocked", BLOCKED);
        map.put("unavailable", UNAVAILABLE);
        return map;
    }

    private static final Map<String, SignalOutcomeEnum> createEnumsMap() {
        Map<String, SignalOutcomeEnum> map = new HashMap<>();
        map.put("found", SignalOutcomeEnum.FOUND);
        map.put("not_found", SignalOutcomeEnum.NOT_FOUND);
        map.put("inconclusive", SignalOutcomeEnum.INCONCLUSIVE);
        map.put("blocked", SignalOutcomeEnum.BLOCKED);
        map.put("unavailable", SignalOutcomeEnum.UNAVAILABLE);
        return map;
    }

    public enum SignalOutcomeEnum {

        FOUND("found"),
        NOT_FOUND("not_found"),
        INCONCLUSIVE("inconclusive"),
        BLOCKED("blocked"),
        UNAVAILABLE("unavailable"),
        ;

        private final String value;

        private SignalOutcomeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
