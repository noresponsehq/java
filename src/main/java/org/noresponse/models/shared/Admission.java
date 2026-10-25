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
 * Admission
 *
 * <p>immediate (the default): an assessment of this flow is decided from the evidence its answer has, and
 * later evidence may change the decision. held: while the account's current assessment of this flow
 * has not completed, the account's decision for the flow is held, and the final decision arrives as
 * account.risk_changed when the investigation completes or hold_seconds pass, whichever is first. Each
 * flow has its own admission.
 */
public class Admission {

    public static final Admission IMMEDIATE = new Admission("immediate");
    public static final Admission HELD = new Admission("held");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Admission> values = createValuesMap();
    private static final Map<String, AdmissionEnum> enums = createEnumsMap();

    private final String value;

    private Admission(String value) {
        this.value = value;
    }

    /**
     * Returns a Admission with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Admission
     */
    @JsonCreator
    public static Admission of(String value) {
        synchronized (Admission.class) {
            return values.computeIfAbsent(value, v -> new Admission(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AdmissionEnum> asEnum() {
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
        Admission other = (Admission) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Admission [value=" + value + "]";
    }

    // return an array just like an enum
    public static Admission[] values() {
        synchronized (Admission.class) {
            return values.values().toArray(new Admission[] {});
        }
    }

    private static final Map<String, Admission> createValuesMap() {
        Map<String, Admission> map = new LinkedHashMap<>();
        map.put("immediate", IMMEDIATE);
        map.put("held", HELD);
        return map;
    }

    private static final Map<String, AdmissionEnum> createEnumsMap() {
        Map<String, AdmissionEnum> map = new HashMap<>();
        map.put("immediate", AdmissionEnum.IMMEDIATE);
        map.put("held", AdmissionEnum.HELD);
        return map;
    }

    public enum AdmissionEnum {

        IMMEDIATE("immediate"),
        HELD("held"),
        ;

        private final String value;

        private AdmissionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
