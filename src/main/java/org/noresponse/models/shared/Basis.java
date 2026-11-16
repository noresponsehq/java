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
 * Basis
 *
 * <p>stated: read from a record. inferred: reasoned from several facts. absence: every source answered
 * and none found it.
 */
public class Basis {

    public static final Basis STATED = new Basis("stated");
    public static final Basis INFERRED = new Basis("inferred");
    public static final Basis ABSENCE = new Basis("absence");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Basis> values = createValuesMap();
    private static final Map<String, BasisEnum> enums = createEnumsMap();

    private final String value;

    private Basis(String value) {
        this.value = value;
    }

    /**
     * Returns a Basis with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Basis
     */
    @JsonCreator
    public static Basis of(String value) {
        synchronized (Basis.class) {
            return values.computeIfAbsent(value, v -> new Basis(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<BasisEnum> asEnum() {
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
        Basis other = (Basis) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Basis [value=" + value + "]";
    }

    // return an array just like an enum
    public static Basis[] values() {
        synchronized (Basis.class) {
            return values.values().toArray(new Basis[] {});
        }
    }

    private static final Map<String, Basis> createValuesMap() {
        Map<String, Basis> map = new LinkedHashMap<>();
        map.put("stated", STATED);
        map.put("inferred", INFERRED);
        map.put("absence", ABSENCE);
        return map;
    }

    private static final Map<String, BasisEnum> createEnumsMap() {
        Map<String, BasisEnum> map = new HashMap<>();
        map.put("stated", BasisEnum.STATED);
        map.put("inferred", BasisEnum.INFERRED);
        map.put("absence", BasisEnum.ABSENCE);
        return map;
    }

    public enum BasisEnum {

        STATED("stated"),
        INFERRED("inferred"),
        ABSENCE("absence"),
        ;

        private final String value;

        private BasisEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
