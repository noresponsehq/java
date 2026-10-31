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
 * BreachConduct
 *
 * <p>What a breach table's rows record against the identifier: chargeback, one or more chargebacks;
 * declined_payment, one or more declined payments; forum_sanction, a forum ban, warning or infraction.
 * A category and not a value: the schema has no member for one.
 */
public class BreachConduct {

    public static final BreachConduct CHARGEBACK = new BreachConduct("chargeback");
    public static final BreachConduct DECLINED_PAYMENT = new BreachConduct("declined_payment");
    public static final BreachConduct FORUM_SANCTION = new BreachConduct("forum_sanction");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, BreachConduct> values = createValuesMap();
    private static final Map<String, BreachConductEnum> enums = createEnumsMap();

    private final String value;

    private BreachConduct(String value) {
        this.value = value;
    }

    /**
     * Returns a BreachConduct with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as BreachConduct
     */
    @JsonCreator
    public static BreachConduct of(String value) {
        synchronized (BreachConduct.class) {
            return values.computeIfAbsent(value, v -> new BreachConduct(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<BreachConductEnum> asEnum() {
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
        BreachConduct other = (BreachConduct) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "BreachConduct [value=" + value + "]";
    }

    // return an array just like an enum
    public static BreachConduct[] values() {
        synchronized (BreachConduct.class) {
            return values.values().toArray(new BreachConduct[] {});
        }
    }

    private static final Map<String, BreachConduct> createValuesMap() {
        Map<String, BreachConduct> map = new LinkedHashMap<>();
        map.put("chargeback", CHARGEBACK);
        map.put("declined_payment", DECLINED_PAYMENT);
        map.put("forum_sanction", FORUM_SANCTION);
        return map;
    }

    private static final Map<String, BreachConductEnum> createEnumsMap() {
        Map<String, BreachConductEnum> map = new HashMap<>();
        map.put("chargeback", BreachConductEnum.CHARGEBACK);
        map.put("declined_payment", BreachConductEnum.DECLINED_PAYMENT);
        map.put("forum_sanction", BreachConductEnum.FORUM_SANCTION);
        return map;
    }

    public enum BreachConductEnum {

        CHARGEBACK("chargeback"),
        DECLINED_PAYMENT("declined_payment"),
        FORUM_SANCTION("forum_sanction"),
        ;

        private final String value;

        private BreachConductEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
