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
public class InvestigationStatus {

    public static final InvestigationStatus QUEUED = new InvestigationStatus("queued");
    public static final InvestigationStatus RUNNING = new InvestigationStatus("running");
    public static final InvestigationStatus COMPLETED = new InvestigationStatus("completed");
    public static final InvestigationStatus PARTIAL = new InvestigationStatus("partial");
    public static final InvestigationStatus FAILED = new InvestigationStatus("failed");
    public static final InvestigationStatus CANCELLED = new InvestigationStatus("cancelled");
    public static final InvestigationStatus UNKNOWN = new InvestigationStatus("unknown");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, InvestigationStatus> values = createValuesMap();
    private static final Map<String, InvestigationStatusEnum> enums = createEnumsMap();

    private final String value;

    private InvestigationStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a InvestigationStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as InvestigationStatus
     */
    @JsonCreator
    public static InvestigationStatus of(String value) {
        synchronized (InvestigationStatus.class) {
            return values.computeIfAbsent(value, v -> new InvestigationStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<InvestigationStatusEnum> asEnum() {
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
        InvestigationStatus other = (InvestigationStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "InvestigationStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static InvestigationStatus[] values() {
        synchronized (InvestigationStatus.class) {
            return values.values().toArray(new InvestigationStatus[] {});
        }
    }

    private static final Map<String, InvestigationStatus> createValuesMap() {
        Map<String, InvestigationStatus> map = new LinkedHashMap<>();
        map.put("queued", QUEUED);
        map.put("running", RUNNING);
        map.put("completed", COMPLETED);
        map.put("partial", PARTIAL);
        map.put("failed", FAILED);
        map.put("cancelled", CANCELLED);
        map.put("unknown", UNKNOWN);
        return map;
    }

    private static final Map<String, InvestigationStatusEnum> createEnumsMap() {
        Map<String, InvestigationStatusEnum> map = new HashMap<>();
        map.put("queued", InvestigationStatusEnum.QUEUED);
        map.put("running", InvestigationStatusEnum.RUNNING);
        map.put("completed", InvestigationStatusEnum.COMPLETED);
        map.put("partial", InvestigationStatusEnum.PARTIAL);
        map.put("failed", InvestigationStatusEnum.FAILED);
        map.put("cancelled", InvestigationStatusEnum.CANCELLED);
        map.put("unknown", InvestigationStatusEnum.UNKNOWN);
        return map;
    }

    public enum InvestigationStatusEnum {

        QUEUED("queued"),
        RUNNING("running"),
        COMPLETED("completed"),
        PARTIAL("partial"),
        FAILED("failed"),
        CANCELLED("cancelled"),
        UNKNOWN("unknown"),
        ;

        private final String value;

        private InvestigationStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
