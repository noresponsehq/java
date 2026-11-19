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
 * RecordConflictSignal
 *
 * <p>anchors_disagree: records found by two of the identifiers you gave clash with each other, so those
 * identifiers may belong to different people.
 */
public class RecordConflictSignal {

    public static final RecordConflictSignal ANCHORS_DISAGREE = new RecordConflictSignal("anchors_disagree");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RecordConflictSignal> values = createValuesMap();
    private static final Map<String, RecordConflictSignalEnum> enums = createEnumsMap();

    private final String value;

    private RecordConflictSignal(String value) {
        this.value = value;
    }

    /**
     * Returns a RecordConflictSignal with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RecordConflictSignal
     */
    @JsonCreator
    public static RecordConflictSignal of(String value) {
        synchronized (RecordConflictSignal.class) {
            return values.computeIfAbsent(value, v -> new RecordConflictSignal(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RecordConflictSignalEnum> asEnum() {
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
        RecordConflictSignal other = (RecordConflictSignal) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RecordConflictSignal [value=" + value + "]";
    }

    // return an array just like an enum
    public static RecordConflictSignal[] values() {
        synchronized (RecordConflictSignal.class) {
            return values.values().toArray(new RecordConflictSignal[] {});
        }
    }

    private static final Map<String, RecordConflictSignal> createValuesMap() {
        Map<String, RecordConflictSignal> map = new LinkedHashMap<>();
        map.put("anchors_disagree", ANCHORS_DISAGREE);
        return map;
    }

    private static final Map<String, RecordConflictSignalEnum> createEnumsMap() {
        Map<String, RecordConflictSignalEnum> map = new HashMap<>();
        map.put("anchors_disagree", RecordConflictSignalEnum.ANCHORS_DISAGREE);
        return map;
    }

    public enum RecordConflictSignalEnum {

        ANCHORS_DISAGREE("anchors_disagree"),
        ;

        private final String value;

        private RecordConflictSignalEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
