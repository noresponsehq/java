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
public class Agreement {

    public static final Agreement MATCH = new Agreement("match");
    public static final Agreement PARTIAL = new Agreement("partial");
    public static final Agreement MISMATCH = new Agreement("mismatch");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Agreement> values = createValuesMap();
    private static final Map<String, AgreementEnum> enums = createEnumsMap();

    private final String value;

    private Agreement(String value) {
        this.value = value;
    }

    /**
     * Returns a Agreement with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Agreement
     */
    @JsonCreator
    public static Agreement of(String value) {
        synchronized (Agreement.class) {
            return values.computeIfAbsent(value, v -> new Agreement(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AgreementEnum> asEnum() {
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
        Agreement other = (Agreement) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Agreement [value=" + value + "]";
    }

    // return an array just like an enum
    public static Agreement[] values() {
        synchronized (Agreement.class) {
            return values.values().toArray(new Agreement[] {});
        }
    }

    private static final Map<String, Agreement> createValuesMap() {
        Map<String, Agreement> map = new LinkedHashMap<>();
        map.put("match", MATCH);
        map.put("partial", PARTIAL);
        map.put("mismatch", MISMATCH);
        return map;
    }

    private static final Map<String, AgreementEnum> createEnumsMap() {
        Map<String, AgreementEnum> map = new HashMap<>();
        map.put("match", AgreementEnum.MATCH);
        map.put("partial", AgreementEnum.PARTIAL);
        map.put("mismatch", AgreementEnum.MISMATCH);
        return map;
    }

    public enum AgreementEnum {

        MATCH("match"),
        PARTIAL("partial"),
        MISMATCH("mismatch"),
        ;

        private final String value;

        private AgreementEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
