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
public class EventType {

    public static final EventType ASSESSMENT_UPDATED = new EventType("assessment.updated");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, EventType> values = createValuesMap();
    private static final Map<String, EventTypeEnum> enums = createEnumsMap();

    private final String value;

    private EventType(String value) {
        this.value = value;
    }

    /**
     * Returns a EventType with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as EventType
     */
    @JsonCreator
    public static EventType of(String value) {
        synchronized (EventType.class) {
            return values.computeIfAbsent(value, v -> new EventType(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EventTypeEnum> asEnum() {
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
        EventType other = (EventType) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "EventType [value=" + value + "]";
    }

    // return an array just like an enum
    public static EventType[] values() {
        synchronized (EventType.class) {
            return values.values().toArray(new EventType[] {});
        }
    }

    private static final Map<String, EventType> createValuesMap() {
        Map<String, EventType> map = new LinkedHashMap<>();
        map.put("assessment.updated", ASSESSMENT_UPDATED);
        return map;
    }

    private static final Map<String, EventTypeEnum> createEnumsMap() {
        Map<String, EventTypeEnum> map = new HashMap<>();
        map.put("assessment.updated", EventTypeEnum.ASSESSMENT_UPDATED);
        return map;
    }

    public enum EventTypeEnum {

        ASSESSMENT_UPDATED("assessment.updated"),
        ;

        private final String value;

        private EventTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
