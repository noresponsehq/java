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
 * ScreeningStatus
 *
 * <p>partial finished with some people or platforms not covered; people_failed and people_partial count
 * them.
 */
public class ScreeningStatus {

    public static final ScreeningStatus QUEUED = new ScreeningStatus("queued");
    public static final ScreeningStatus RUNNING = new ScreeningStatus("running");
    public static final ScreeningStatus COMPLETED = new ScreeningStatus("completed");
    public static final ScreeningStatus PARTIAL = new ScreeningStatus("partial");
    public static final ScreeningStatus FAILED = new ScreeningStatus("failed");
    public static final ScreeningStatus CANCELLED = new ScreeningStatus("cancelled");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningStatus> values = createValuesMap();
    private static final Map<String, ScreeningStatusEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningStatus
     */
    @JsonCreator
    public static ScreeningStatus of(String value) {
        synchronized (ScreeningStatus.class) {
            return values.computeIfAbsent(value, v -> new ScreeningStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningStatusEnum> asEnum() {
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
        ScreeningStatus other = (ScreeningStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningStatus[] values() {
        synchronized (ScreeningStatus.class) {
            return values.values().toArray(new ScreeningStatus[] {});
        }
    }

    private static final Map<String, ScreeningStatus> createValuesMap() {
        Map<String, ScreeningStatus> map = new LinkedHashMap<>();
        map.put("queued", QUEUED);
        map.put("running", RUNNING);
        map.put("completed", COMPLETED);
        map.put("partial", PARTIAL);
        map.put("failed", FAILED);
        map.put("cancelled", CANCELLED);
        return map;
    }

    private static final Map<String, ScreeningStatusEnum> createEnumsMap() {
        Map<String, ScreeningStatusEnum> map = new HashMap<>();
        map.put("queued", ScreeningStatusEnum.QUEUED);
        map.put("running", ScreeningStatusEnum.RUNNING);
        map.put("completed", ScreeningStatusEnum.COMPLETED);
        map.put("partial", ScreeningStatusEnum.PARTIAL);
        map.put("failed", ScreeningStatusEnum.FAILED);
        map.put("cancelled", ScreeningStatusEnum.CANCELLED);
        return map;
    }

    public enum ScreeningStatusEnum {

        QUEUED("queued"),
        RUNNING("running"),
        COMPLETED("completed"),
        PARTIAL("partial"),
        FAILED("failed"),
        CANCELLED("cancelled"),
        ;

        private final String value;

        private ScreeningStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
