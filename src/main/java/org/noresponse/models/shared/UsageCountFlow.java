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
 * UsageCountFlow
 *
 * <p>assessments only.
 */
public class UsageCountFlow {

    public static final UsageCountFlow ACCOUNT_CREATION = new UsageCountFlow("account_creation");
    public static final UsageCountFlow LOGIN = new UsageCountFlow("login");
    public static final UsageCountFlow PURCHASE = new UsageCountFlow("purchase");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, UsageCountFlow> values = createValuesMap();
    private static final Map<String, UsageCountFlowEnum> enums = createEnumsMap();

    private final String value;

    private UsageCountFlow(String value) {
        this.value = value;
    }

    /**
     * Returns a UsageCountFlow with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as UsageCountFlow
     */
    @JsonCreator
    public static UsageCountFlow of(String value) {
        synchronized (UsageCountFlow.class) {
            return values.computeIfAbsent(value, v -> new UsageCountFlow(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<UsageCountFlowEnum> asEnum() {
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
        UsageCountFlow other = (UsageCountFlow) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "UsageCountFlow [value=" + value + "]";
    }

    // return an array just like an enum
    public static UsageCountFlow[] values() {
        synchronized (UsageCountFlow.class) {
            return values.values().toArray(new UsageCountFlow[] {});
        }
    }

    private static final Map<String, UsageCountFlow> createValuesMap() {
        Map<String, UsageCountFlow> map = new LinkedHashMap<>();
        map.put("account_creation", ACCOUNT_CREATION);
        map.put("login", LOGIN);
        map.put("purchase", PURCHASE);
        return map;
    }

    private static final Map<String, UsageCountFlowEnum> createEnumsMap() {
        Map<String, UsageCountFlowEnum> map = new HashMap<>();
        map.put("account_creation", UsageCountFlowEnum.ACCOUNT_CREATION);
        map.put("login", UsageCountFlowEnum.LOGIN);
        map.put("purchase", UsageCountFlowEnum.PURCHASE);
        return map;
    }

    public enum UsageCountFlowEnum {

        ACCOUNT_CREATION("account_creation"),
        LOGIN("login"),
        PURCHASE("purchase"),
        ;

        private final String value;

        private UsageCountFlowEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
