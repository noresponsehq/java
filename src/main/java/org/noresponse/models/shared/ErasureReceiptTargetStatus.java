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
public class ErasureReceiptTargetStatus {

    public static final ErasureReceiptTargetStatus PENDING = new ErasureReceiptTargetStatus("pending");
    public static final ErasureReceiptTargetStatus COMPLETED = new ErasureReceiptTargetStatus("completed");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ErasureReceiptTargetStatus> values = createValuesMap();
    private static final Map<String, ErasureReceiptTargetStatusEnum> enums = createEnumsMap();

    private final String value;

    private ErasureReceiptTargetStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a ErasureReceiptTargetStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ErasureReceiptTargetStatus
     */
    @JsonCreator
    public static ErasureReceiptTargetStatus of(String value) {
        synchronized (ErasureReceiptTargetStatus.class) {
            return values.computeIfAbsent(value, v -> new ErasureReceiptTargetStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ErasureReceiptTargetStatusEnum> asEnum() {
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
        ErasureReceiptTargetStatus other = (ErasureReceiptTargetStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ErasureReceiptTargetStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static ErasureReceiptTargetStatus[] values() {
        synchronized (ErasureReceiptTargetStatus.class) {
            return values.values().toArray(new ErasureReceiptTargetStatus[] {});
        }
    }

    private static final Map<String, ErasureReceiptTargetStatus> createValuesMap() {
        Map<String, ErasureReceiptTargetStatus> map = new LinkedHashMap<>();
        map.put("pending", PENDING);
        map.put("completed", COMPLETED);
        return map;
    }

    private static final Map<String, ErasureReceiptTargetStatusEnum> createEnumsMap() {
        Map<String, ErasureReceiptTargetStatusEnum> map = new HashMap<>();
        map.put("pending", ErasureReceiptTargetStatusEnum.PENDING);
        map.put("completed", ErasureReceiptTargetStatusEnum.COMPLETED);
        return map;
    }

    public enum ErasureReceiptTargetStatusEnum {

        PENDING("pending"),
        COMPLETED("completed"),
        ;

        private final String value;

        private ErasureReceiptTargetStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
