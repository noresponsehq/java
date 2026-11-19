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
 * RecordEvidenceSignal
 *
 * <p>found_by: what found the record, which sets where its likelihood starts. agrees: a value the record
 * shares with what the investigator gave or with a joined record. clashes: a birth date, full name,
 * gender or country that differs from theirs.
 */
public class RecordEvidenceSignal {

    public static final RecordEvidenceSignal FOUND_BY = new RecordEvidenceSignal("found_by");
    public static final RecordEvidenceSignal AGREES = new RecordEvidenceSignal("agrees");
    public static final RecordEvidenceSignal CLASHES = new RecordEvidenceSignal("clashes");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RecordEvidenceSignal> values = createValuesMap();
    private static final Map<String, RecordEvidenceSignalEnum> enums = createEnumsMap();

    private final String value;

    private RecordEvidenceSignal(String value) {
        this.value = value;
    }

    /**
     * Returns a RecordEvidenceSignal with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RecordEvidenceSignal
     */
    @JsonCreator
    public static RecordEvidenceSignal of(String value) {
        synchronized (RecordEvidenceSignal.class) {
            return values.computeIfAbsent(value, v -> new RecordEvidenceSignal(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RecordEvidenceSignalEnum> asEnum() {
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
        RecordEvidenceSignal other = (RecordEvidenceSignal) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RecordEvidenceSignal [value=" + value + "]";
    }

    // return an array just like an enum
    public static RecordEvidenceSignal[] values() {
        synchronized (RecordEvidenceSignal.class) {
            return values.values().toArray(new RecordEvidenceSignal[] {});
        }
    }

    private static final Map<String, RecordEvidenceSignal> createValuesMap() {
        Map<String, RecordEvidenceSignal> map = new LinkedHashMap<>();
        map.put("found_by", FOUND_BY);
        map.put("agrees", AGREES);
        map.put("clashes", CLASHES);
        return map;
    }

    private static final Map<String, RecordEvidenceSignalEnum> createEnumsMap() {
        Map<String, RecordEvidenceSignalEnum> map = new HashMap<>();
        map.put("found_by", RecordEvidenceSignalEnum.FOUND_BY);
        map.put("agrees", RecordEvidenceSignalEnum.AGREES);
        map.put("clashes", RecordEvidenceSignalEnum.CLASHES);
        return map;
    }

    public enum RecordEvidenceSignalEnum {

        FOUND_BY("found_by"),
        AGREES("agrees"),
        CLASHES("clashes"),
        ;

        private final String value;

        private RecordEvidenceSignalEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
