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
 * EnforcementStatus
 *
 * <p>applied when the identity provider accepted the action, failed when it refused it or could not be
 * reached. Only applied counts as enforced.
 */
public class EnforcementStatus {

    public static final EnforcementStatus APPLIED = new EnforcementStatus("applied");
    public static final EnforcementStatus FAILED = new EnforcementStatus("failed");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, EnforcementStatus> values = createValuesMap();
    private static final Map<String, EnforcementStatusEnum> enums = createEnumsMap();

    private final String value;

    private EnforcementStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a EnforcementStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as EnforcementStatus
     */
    @JsonCreator
    public static EnforcementStatus of(String value) {
        synchronized (EnforcementStatus.class) {
            return values.computeIfAbsent(value, v -> new EnforcementStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EnforcementStatusEnum> asEnum() {
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
        EnforcementStatus other = (EnforcementStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "EnforcementStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static EnforcementStatus[] values() {
        synchronized (EnforcementStatus.class) {
            return values.values().toArray(new EnforcementStatus[] {});
        }
    }

    private static final Map<String, EnforcementStatus> createValuesMap() {
        Map<String, EnforcementStatus> map = new LinkedHashMap<>();
        map.put("applied", APPLIED);
        map.put("failed", FAILED);
        return map;
    }

    private static final Map<String, EnforcementStatusEnum> createEnumsMap() {
        Map<String, EnforcementStatusEnum> map = new HashMap<>();
        map.put("applied", EnforcementStatusEnum.APPLIED);
        map.put("failed", EnforcementStatusEnum.FAILED);
        return map;
    }

    public enum EnforcementStatusEnum {

        APPLIED("applied"),
        FAILED("failed"),
        ;

        private final String value;

        private EnforcementStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
