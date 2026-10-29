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
public class CustomerExportRequestFilterOutcome {

    public static final CustomerExportRequestFilterOutcome SUCCEEDED =
            new CustomerExportRequestFilterOutcome("succeeded");
    public static final CustomerExportRequestFilterOutcome DENIED = new CustomerExportRequestFilterOutcome("denied");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, CustomerExportRequestFilterOutcome> values = createValuesMap();
    private static final Map<String, CustomerExportRequestFilterOutcomeEnum> enums = createEnumsMap();

    private final String value;

    private CustomerExportRequestFilterOutcome(String value) {
        this.value = value;
    }

    /**
     * Returns a CustomerExportRequestFilterOutcome with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as CustomerExportRequestFilterOutcome
     */
    @JsonCreator
    public static CustomerExportRequestFilterOutcome of(String value) {
        synchronized (CustomerExportRequestFilterOutcome.class) {
            return values.computeIfAbsent(value, v -> new CustomerExportRequestFilterOutcome(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<CustomerExportRequestFilterOutcomeEnum> asEnum() {
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
        CustomerExportRequestFilterOutcome other = (CustomerExportRequestFilterOutcome) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "CustomerExportRequestFilterOutcome [value=" + value + "]";
    }

    // return an array just like an enum
    public static CustomerExportRequestFilterOutcome[] values() {
        synchronized (CustomerExportRequestFilterOutcome.class) {
            return values.values().toArray(new CustomerExportRequestFilterOutcome[] {});
        }
    }

    private static final Map<String, CustomerExportRequestFilterOutcome> createValuesMap() {
        Map<String, CustomerExportRequestFilterOutcome> map = new LinkedHashMap<>();
        map.put("succeeded", SUCCEEDED);
        map.put("denied", DENIED);
        return map;
    }

    private static final Map<String, CustomerExportRequestFilterOutcomeEnum> createEnumsMap() {
        Map<String, CustomerExportRequestFilterOutcomeEnum> map = new HashMap<>();
        map.put("succeeded", CustomerExportRequestFilterOutcomeEnum.SUCCEEDED);
        map.put("denied", CustomerExportRequestFilterOutcomeEnum.DENIED);
        return map;
    }

    public enum CustomerExportRequestFilterOutcomeEnum {

        SUCCEEDED("succeeded"),
        DENIED("denied"),
        ;

        private final String value;

        private CustomerExportRequestFilterOutcomeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
