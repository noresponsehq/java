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
public class CustomerExportRequestKind {

    public static final CustomerExportRequestKind ACCOUNT = new CustomerExportRequestKind("account");
    public static final CustomerExportRequestKind AUDIT = new CustomerExportRequestKind("audit");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, CustomerExportRequestKind> values = createValuesMap();
    private static final Map<String, CustomerExportRequestKindEnum> enums = createEnumsMap();

    private final String value;

    private CustomerExportRequestKind(String value) {
        this.value = value;
    }

    /**
     * Returns a CustomerExportRequestKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as CustomerExportRequestKind
     */
    @JsonCreator
    public static CustomerExportRequestKind of(String value) {
        synchronized (CustomerExportRequestKind.class) {
            return values.computeIfAbsent(value, v -> new CustomerExportRequestKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<CustomerExportRequestKindEnum> asEnum() {
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
        CustomerExportRequestKind other = (CustomerExportRequestKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "CustomerExportRequestKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static CustomerExportRequestKind[] values() {
        synchronized (CustomerExportRequestKind.class) {
            return values.values().toArray(new CustomerExportRequestKind[] {});
        }
    }

    private static final Map<String, CustomerExportRequestKind> createValuesMap() {
        Map<String, CustomerExportRequestKind> map = new LinkedHashMap<>();
        map.put("account", ACCOUNT);
        map.put("audit", AUDIT);
        return map;
    }

    private static final Map<String, CustomerExportRequestKindEnum> createEnumsMap() {
        Map<String, CustomerExportRequestKindEnum> map = new HashMap<>();
        map.put("account", CustomerExportRequestKindEnum.ACCOUNT);
        map.put("audit", CustomerExportRequestKindEnum.AUDIT);
        return map;
    }

    public enum CustomerExportRequestKindEnum {

        ACCOUNT("account"),
        AUDIT("audit"),
        ;

        private final String value;

        private CustomerExportRequestKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
