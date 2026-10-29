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
public class CustomerExportStatus {

    public static final CustomerExportStatus QUEUED = new CustomerExportStatus("queued");
    public static final CustomerExportStatus RUNNING = new CustomerExportStatus("running");
    public static final CustomerExportStatus COMPLETED = new CustomerExportStatus("completed");
    public static final CustomerExportStatus FAILED = new CustomerExportStatus("failed");
    public static final CustomerExportStatus CANCELLED = new CustomerExportStatus("cancelled");
    public static final CustomerExportStatus EXPIRED = new CustomerExportStatus("expired");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, CustomerExportStatus> values = createValuesMap();
    private static final Map<String, CustomerExportStatusEnum> enums = createEnumsMap();

    private final String value;

    private CustomerExportStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a CustomerExportStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as CustomerExportStatus
     */
    @JsonCreator
    public static CustomerExportStatus of(String value) {
        synchronized (CustomerExportStatus.class) {
            return values.computeIfAbsent(value, v -> new CustomerExportStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<CustomerExportStatusEnum> asEnum() {
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
        CustomerExportStatus other = (CustomerExportStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "CustomerExportStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static CustomerExportStatus[] values() {
        synchronized (CustomerExportStatus.class) {
            return values.values().toArray(new CustomerExportStatus[] {});
        }
    }

    private static final Map<String, CustomerExportStatus> createValuesMap() {
        Map<String, CustomerExportStatus> map = new LinkedHashMap<>();
        map.put("queued", QUEUED);
        map.put("running", RUNNING);
        map.put("completed", COMPLETED);
        map.put("failed", FAILED);
        map.put("cancelled", CANCELLED);
        map.put("expired", EXPIRED);
        return map;
    }

    private static final Map<String, CustomerExportStatusEnum> createEnumsMap() {
        Map<String, CustomerExportStatusEnum> map = new HashMap<>();
        map.put("queued", CustomerExportStatusEnum.QUEUED);
        map.put("running", CustomerExportStatusEnum.RUNNING);
        map.put("completed", CustomerExportStatusEnum.COMPLETED);
        map.put("failed", CustomerExportStatusEnum.FAILED);
        map.put("cancelled", CustomerExportStatusEnum.CANCELLED);
        map.put("expired", CustomerExportStatusEnum.EXPIRED);
        return map;
    }

    public enum CustomerExportStatusEnum {

        QUEUED("queued"),
        RUNNING("running"),
        COMPLETED("completed"),
        FAILED("failed"),
        CANCELLED("cancelled"),
        EXPIRED("expired"),
        ;

        private final String value;

        private CustomerExportStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
