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
 * Outcome
 *
 * <p>What the provider later learned about the assessed account. confirmed_abuse, account_takeover and
 * chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this
 * tenant for the assessment; unknown is recorded only. Outcomes are append-only and the latest one
 * recorded for an assessment wins.
 */
public class Outcome {

    public static final Outcome CONFIRMED_ABUSE = new Outcome("confirmed_abuse");
    public static final Outcome ACCOUNT_TAKEOVER = new Outcome("account_takeover");
    public static final Outcome CHARGEBACK = new Outcome("chargeback");
    public static final Outcome FALSE_POSITIVE = new Outcome("false_positive");
    public static final Outcome LEGITIMATE = new Outcome("legitimate");
    public static final Outcome UNKNOWN = new Outcome("unknown");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Outcome> values = createValuesMap();
    private static final Map<String, OutcomeEnum> enums = createEnumsMap();

    private final String value;

    private Outcome(String value) {
        this.value = value;
    }

    /**
     * Returns a Outcome with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Outcome
     */
    @JsonCreator
    public static Outcome of(String value) {
        synchronized (Outcome.class) {
            return values.computeIfAbsent(value, v -> new Outcome(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<OutcomeEnum> asEnum() {
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
        Outcome other = (Outcome) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Outcome [value=" + value + "]";
    }

    // return an array just like an enum
    public static Outcome[] values() {
        synchronized (Outcome.class) {
            return values.values().toArray(new Outcome[] {});
        }
    }

    private static final Map<String, Outcome> createValuesMap() {
        Map<String, Outcome> map = new LinkedHashMap<>();
        map.put("confirmed_abuse", CONFIRMED_ABUSE);
        map.put("account_takeover", ACCOUNT_TAKEOVER);
        map.put("chargeback", CHARGEBACK);
        map.put("false_positive", FALSE_POSITIVE);
        map.put("legitimate", LEGITIMATE);
        map.put("unknown", UNKNOWN);
        return map;
    }

    private static final Map<String, OutcomeEnum> createEnumsMap() {
        Map<String, OutcomeEnum> map = new HashMap<>();
        map.put("confirmed_abuse", OutcomeEnum.CONFIRMED_ABUSE);
        map.put("account_takeover", OutcomeEnum.ACCOUNT_TAKEOVER);
        map.put("chargeback", OutcomeEnum.CHARGEBACK);
        map.put("false_positive", OutcomeEnum.FALSE_POSITIVE);
        map.put("legitimate", OutcomeEnum.LEGITIMATE);
        map.put("unknown", OutcomeEnum.UNKNOWN);
        return map;
    }

    public enum OutcomeEnum {

        CONFIRMED_ABUSE("confirmed_abuse"),
        ACCOUNT_TAKEOVER("account_takeover"),
        CHARGEBACK("chargeback"),
        FALSE_POSITIVE("false_positive"),
        LEGITIMATE("legitimate"),
        UNKNOWN("unknown"),
        ;

        private final String value;

        private OutcomeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
