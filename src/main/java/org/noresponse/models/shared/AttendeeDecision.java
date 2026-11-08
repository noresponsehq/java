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
public class AttendeeDecision {

    public static final AttendeeDecision CLEARED = new AttendeeDecision("cleared");
    public static final AttendeeDecision WATCH = new AttendeeDecision("watch");
    public static final AttendeeDecision ESCALATED = new AttendeeDecision("escalated");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AttendeeDecision> values = createValuesMap();
    private static final Map<String, AttendeeDecisionEnum> enums = createEnumsMap();

    private final String value;

    private AttendeeDecision(String value) {
        this.value = value;
    }

    /**
     * Returns a AttendeeDecision with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AttendeeDecision
     */
    @JsonCreator
    public static AttendeeDecision of(String value) {
        synchronized (AttendeeDecision.class) {
            return values.computeIfAbsent(value, v -> new AttendeeDecision(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AttendeeDecisionEnum> asEnum() {
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
        AttendeeDecision other = (AttendeeDecision) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AttendeeDecision [value=" + value + "]";
    }

    // return an array just like an enum
    public static AttendeeDecision[] values() {
        synchronized (AttendeeDecision.class) {
            return values.values().toArray(new AttendeeDecision[] {});
        }
    }

    private static final Map<String, AttendeeDecision> createValuesMap() {
        Map<String, AttendeeDecision> map = new LinkedHashMap<>();
        map.put("cleared", CLEARED);
        map.put("watch", WATCH);
        map.put("escalated", ESCALATED);
        return map;
    }

    private static final Map<String, AttendeeDecisionEnum> createEnumsMap() {
        Map<String, AttendeeDecisionEnum> map = new HashMap<>();
        map.put("cleared", AttendeeDecisionEnum.CLEARED);
        map.put("watch", AttendeeDecisionEnum.WATCH);
        map.put("escalated", AttendeeDecisionEnum.ESCALATED);
        return map;
    }

    public enum AttendeeDecisionEnum {

        CLEARED("cleared"),
        WATCH("watch"),
        ESCALATED("escalated"),
        ;

        private final String value;

        private AttendeeDecisionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
