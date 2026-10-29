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
public class PolicyEvaluationStatus {

    public static final PolicyEvaluationStatus QUEUED = new PolicyEvaluationStatus("queued");
    public static final PolicyEvaluationStatus RUNNING = new PolicyEvaluationStatus("running");
    public static final PolicyEvaluationStatus COMPLETED = new PolicyEvaluationStatus("completed");
    public static final PolicyEvaluationStatus FAILED = new PolicyEvaluationStatus("failed");
    public static final PolicyEvaluationStatus CANCELLED = new PolicyEvaluationStatus("cancelled");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, PolicyEvaluationStatus> values = createValuesMap();
    private static final Map<String, PolicyEvaluationStatusEnum> enums = createEnumsMap();

    private final String value;

    private PolicyEvaluationStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a PolicyEvaluationStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as PolicyEvaluationStatus
     */
    @JsonCreator
    public static PolicyEvaluationStatus of(String value) {
        synchronized (PolicyEvaluationStatus.class) {
            return values.computeIfAbsent(value, v -> new PolicyEvaluationStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PolicyEvaluationStatusEnum> asEnum() {
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
        PolicyEvaluationStatus other = (PolicyEvaluationStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "PolicyEvaluationStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static PolicyEvaluationStatus[] values() {
        synchronized (PolicyEvaluationStatus.class) {
            return values.values().toArray(new PolicyEvaluationStatus[] {});
        }
    }

    private static final Map<String, PolicyEvaluationStatus> createValuesMap() {
        Map<String, PolicyEvaluationStatus> map = new LinkedHashMap<>();
        map.put("queued", QUEUED);
        map.put("running", RUNNING);
        map.put("completed", COMPLETED);
        map.put("failed", FAILED);
        map.put("cancelled", CANCELLED);
        return map;
    }

    private static final Map<String, PolicyEvaluationStatusEnum> createEnumsMap() {
        Map<String, PolicyEvaluationStatusEnum> map = new HashMap<>();
        map.put("queued", PolicyEvaluationStatusEnum.QUEUED);
        map.put("running", PolicyEvaluationStatusEnum.RUNNING);
        map.put("completed", PolicyEvaluationStatusEnum.COMPLETED);
        map.put("failed", PolicyEvaluationStatusEnum.FAILED);
        map.put("cancelled", PolicyEvaluationStatusEnum.CANCELLED);
        return map;
    }

    public enum PolicyEvaluationStatusEnum {

        QUEUED("queued"),
        RUNNING("running"),
        COMPLETED("completed"),
        FAILED("failed"),
        CANCELLED("cancelled"),
        ;

        private final String value;

        private PolicyEvaluationStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
