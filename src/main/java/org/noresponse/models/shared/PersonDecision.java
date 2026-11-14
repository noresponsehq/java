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
public class PersonDecision {

    public static final PersonDecision CLEARED = new PersonDecision("cleared");
    public static final PersonDecision WATCH = new PersonDecision("watch");
    public static final PersonDecision ESCALATED = new PersonDecision("escalated");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, PersonDecision> values = createValuesMap();
    private static final Map<String, PersonDecisionEnum> enums = createEnumsMap();

    private final String value;

    private PersonDecision(String value) {
        this.value = value;
    }

    /**
     * Returns a PersonDecision with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as PersonDecision
     */
    @JsonCreator
    public static PersonDecision of(String value) {
        synchronized (PersonDecision.class) {
            return values.computeIfAbsent(value, v -> new PersonDecision(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PersonDecisionEnum> asEnum() {
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
        PersonDecision other = (PersonDecision) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "PersonDecision [value=" + value + "]";
    }

    // return an array just like an enum
    public static PersonDecision[] values() {
        synchronized (PersonDecision.class) {
            return values.values().toArray(new PersonDecision[] {});
        }
    }

    private static final Map<String, PersonDecision> createValuesMap() {
        Map<String, PersonDecision> map = new LinkedHashMap<>();
        map.put("cleared", CLEARED);
        map.put("watch", WATCH);
        map.put("escalated", ESCALATED);
        return map;
    }

    private static final Map<String, PersonDecisionEnum> createEnumsMap() {
        Map<String, PersonDecisionEnum> map = new HashMap<>();
        map.put("cleared", PersonDecisionEnum.CLEARED);
        map.put("watch", PersonDecisionEnum.WATCH);
        map.put("escalated", PersonDecisionEnum.ESCALATED);
        return map;
    }

    public enum PersonDecisionEnum {

        CLEARED("cleared"),
        WATCH("watch"),
        ESCALATED("escalated"),
        ;

        private final String value;

        private PersonDecisionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
