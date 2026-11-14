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
 * Verdict
 *
 * <p>A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
 * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
 * it.
 */
public class Verdict {

    public static final Verdict JOINED = new Verdict("joined");
    public static final Verdict REJECTED = new Verdict("rejected");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Verdict> values = createValuesMap();
    private static final Map<String, VerdictEnum> enums = createEnumsMap();

    private final String value;

    private Verdict(String value) {
        this.value = value;
    }

    /**
     * Returns a Verdict with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Verdict
     */
    @JsonCreator
    public static Verdict of(String value) {
        synchronized (Verdict.class) {
            return values.computeIfAbsent(value, v -> new Verdict(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<VerdictEnum> asEnum() {
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
        Verdict other = (Verdict) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Verdict [value=" + value + "]";
    }

    // return an array just like an enum
    public static Verdict[] values() {
        synchronized (Verdict.class) {
            return values.values().toArray(new Verdict[] {});
        }
    }

    private static final Map<String, Verdict> createValuesMap() {
        Map<String, Verdict> map = new LinkedHashMap<>();
        map.put("joined", JOINED);
        map.put("rejected", REJECTED);
        return map;
    }

    private static final Map<String, VerdictEnum> createEnumsMap() {
        Map<String, VerdictEnum> map = new HashMap<>();
        map.put("joined", VerdictEnum.JOINED);
        map.put("rejected", VerdictEnum.REJECTED);
        return map;
    }

    public enum VerdictEnum {

        JOINED("joined"),
        REJECTED("rejected"),
        ;

        private final String value;

        private VerdictEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
