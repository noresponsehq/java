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
 * AccountDecision
 *
 * <p>block when the score reached your block threshold (risk.block of the assessment), otherwise the
 * assessment's recommendation. Your application applies it.
 */
public class AccountDecision {

    public static final AccountDecision ALLOW = new AccountDecision("allow");
    public static final AccountDecision STEP_UP = new AccountDecision("step_up");
    public static final AccountDecision REVIEW = new AccountDecision("review");
    public static final AccountDecision BLOCK = new AccountDecision("block");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AccountDecision> values = createValuesMap();
    private static final Map<String, AccountDecisionEnum> enums = createEnumsMap();

    private final String value;

    private AccountDecision(String value) {
        this.value = value;
    }

    /**
     * Returns a AccountDecision with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AccountDecision
     */
    @JsonCreator
    public static AccountDecision of(String value) {
        synchronized (AccountDecision.class) {
            return values.computeIfAbsent(value, v -> new AccountDecision(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AccountDecisionEnum> asEnum() {
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
        AccountDecision other = (AccountDecision) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AccountDecision [value=" + value + "]";
    }

    // return an array just like an enum
    public static AccountDecision[] values() {
        synchronized (AccountDecision.class) {
            return values.values().toArray(new AccountDecision[] {});
        }
    }

    private static final Map<String, AccountDecision> createValuesMap() {
        Map<String, AccountDecision> map = new LinkedHashMap<>();
        map.put("allow", ALLOW);
        map.put("step_up", STEP_UP);
        map.put("review", REVIEW);
        map.put("block", BLOCK);
        return map;
    }

    private static final Map<String, AccountDecisionEnum> createEnumsMap() {
        Map<String, AccountDecisionEnum> map = new HashMap<>();
        map.put("allow", AccountDecisionEnum.ALLOW);
        map.put("step_up", AccountDecisionEnum.STEP_UP);
        map.put("review", AccountDecisionEnum.REVIEW);
        map.put("block", AccountDecisionEnum.BLOCK);
        return map;
    }

    public enum AccountDecisionEnum {

        ALLOW("allow"),
        STEP_UP("step_up"),
        REVIEW("review"),
        BLOCK("block"),
        ;

        private final String value;

        private AccountDecisionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
