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
 * Metric
 *
 * <p>assessments: assessments admitted. webhook_deliveries: events sent to endpoints, one per event per
 * endpoint. key_usage_events: key-usage events accepted.
 */
public class Metric {

    public static final Metric ASSESSMENTS = new Metric("assessments");
    public static final Metric WEBHOOK_DELIVERIES = new Metric("webhook_deliveries");
    public static final Metric KEY_USAGE_EVENTS = new Metric("key_usage_events");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Metric> values = createValuesMap();
    private static final Map<String, MetricEnum> enums = createEnumsMap();

    private final String value;

    private Metric(String value) {
        this.value = value;
    }

    /**
     * Returns a Metric with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Metric
     */
    @JsonCreator
    public static Metric of(String value) {
        synchronized (Metric.class) {
            return values.computeIfAbsent(value, v -> new Metric(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MetricEnum> asEnum() {
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
        Metric other = (Metric) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Metric [value=" + value + "]";
    }

    // return an array just like an enum
    public static Metric[] values() {
        synchronized (Metric.class) {
            return values.values().toArray(new Metric[] {});
        }
    }

    private static final Map<String, Metric> createValuesMap() {
        Map<String, Metric> map = new LinkedHashMap<>();
        map.put("assessments", ASSESSMENTS);
        map.put("webhook_deliveries", WEBHOOK_DELIVERIES);
        map.put("key_usage_events", KEY_USAGE_EVENTS);
        return map;
    }

    private static final Map<String, MetricEnum> createEnumsMap() {
        Map<String, MetricEnum> map = new HashMap<>();
        map.put("assessments", MetricEnum.ASSESSMENTS);
        map.put("webhook_deliveries", MetricEnum.WEBHOOK_DELIVERIES);
        map.put("key_usage_events", MetricEnum.KEY_USAGE_EVENTS);
        return map;
    }

    public enum MetricEnum {

        ASSESSMENTS("assessments"),
        WEBHOOK_DELIVERIES("webhook_deliveries"),
        KEY_USAGE_EVENTS("key_usage_events"),
        ;

        private final String value;

        private MetricEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
