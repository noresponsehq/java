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
 * Population
 *
 * <p>first_answers uses anonymous counters that can include erased detail. retained_first_answers
 * computes exact intersections over retained first answers within 31 days. Requests exceeding the
 * 100000-record work budget are refused.
 */
public class Population {

    public static final Population FIRST_ANSWERS = new Population("first_answers");
    public static final Population RETAINED_FIRST_ANSWERS = new Population("retained_first_answers");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Population> values = createValuesMap();
    private static final Map<String, PopulationEnum> enums = createEnumsMap();

    private final String value;

    private Population(String value) {
        this.value = value;
    }

    /**
     * Returns a Population with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Population
     */
    @JsonCreator
    public static Population of(String value) {
        synchronized (Population.class) {
            return values.computeIfAbsent(value, v -> new Population(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PopulationEnum> asEnum() {
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
        Population other = (Population) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Population [value=" + value + "]";
    }

    // return an array just like an enum
    public static Population[] values() {
        synchronized (Population.class) {
            return values.values().toArray(new Population[] {});
        }
    }

    private static final Map<String, Population> createValuesMap() {
        Map<String, Population> map = new LinkedHashMap<>();
        map.put("first_answers", FIRST_ANSWERS);
        map.put("retained_first_answers", RETAINED_FIRST_ANSWERS);
        return map;
    }

    private static final Map<String, PopulationEnum> createEnumsMap() {
        Map<String, PopulationEnum> map = new HashMap<>();
        map.put("first_answers", PopulationEnum.FIRST_ANSWERS);
        map.put("retained_first_answers", PopulationEnum.RETAINED_FIRST_ANSWERS);
        return map;
    }

    public enum PopulationEnum {

        FIRST_ANSWERS("first_answers"),
        RETAINED_FIRST_ANSWERS("retained_first_answers"),
        ;

        private final String value;

        private PopulationEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
