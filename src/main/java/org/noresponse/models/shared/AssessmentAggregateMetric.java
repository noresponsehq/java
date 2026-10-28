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
public class AssessmentAggregateMetric {

    public static final AssessmentAggregateMetric COUNT = new AssessmentAggregateMetric("count");
    public static final AssessmentAggregateMetric SCORE_HISTOGRAM = new AssessmentAggregateMetric("score_histogram");
    public static final AssessmentAggregateMetric SCORE_OUTCOMES = new AssessmentAggregateMetric("score_outcomes");
    public static final AssessmentAggregateMetric LATE_FLIPS = new AssessmentAggregateMetric("late_flips");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AssessmentAggregateMetric> values = createValuesMap();
    private static final Map<String, AssessmentAggregateMetricEnum> enums = createEnumsMap();

    private final String value;

    private AssessmentAggregateMetric(String value) {
        this.value = value;
    }

    /**
     * Returns a AssessmentAggregateMetric with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AssessmentAggregateMetric
     */
    @JsonCreator
    public static AssessmentAggregateMetric of(String value) {
        synchronized (AssessmentAggregateMetric.class) {
            return values.computeIfAbsent(value, v -> new AssessmentAggregateMetric(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AssessmentAggregateMetricEnum> asEnum() {
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
        AssessmentAggregateMetric other = (AssessmentAggregateMetric) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AssessmentAggregateMetric [value=" + value + "]";
    }

    // return an array just like an enum
    public static AssessmentAggregateMetric[] values() {
        synchronized (AssessmentAggregateMetric.class) {
            return values.values().toArray(new AssessmentAggregateMetric[] {});
        }
    }

    private static final Map<String, AssessmentAggregateMetric> createValuesMap() {
        Map<String, AssessmentAggregateMetric> map = new LinkedHashMap<>();
        map.put("count", COUNT);
        map.put("score_histogram", SCORE_HISTOGRAM);
        map.put("score_outcomes", SCORE_OUTCOMES);
        map.put("late_flips", LATE_FLIPS);
        return map;
    }

    private static final Map<String, AssessmentAggregateMetricEnum> createEnumsMap() {
        Map<String, AssessmentAggregateMetricEnum> map = new HashMap<>();
        map.put("count", AssessmentAggregateMetricEnum.COUNT);
        map.put("score_histogram", AssessmentAggregateMetricEnum.SCORE_HISTOGRAM);
        map.put("score_outcomes", AssessmentAggregateMetricEnum.SCORE_OUTCOMES);
        map.put("late_flips", AssessmentAggregateMetricEnum.LATE_FLIPS);
        return map;
    }

    public enum AssessmentAggregateMetricEnum {

        COUNT("count"),
        SCORE_HISTOGRAM("score_histogram"),
        SCORE_OUTCOMES("score_outcomes"),
        LATE_FLIPS("late_flips"),
        ;

        private final String value;

        private AssessmentAggregateMetricEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
