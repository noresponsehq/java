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
 * Provenance
 *
 * <p>Customer-submitted input. It does not mean the customer or NoResponse verified ownership.
 */
public class Provenance {

    public static final Provenance SUBMITTED = new Provenance("submitted");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Provenance> values = createValuesMap();
    private static final Map<String, ProvenanceEnum> enums = createEnumsMap();

    private final String value;

    private Provenance(String value) {
        this.value = value;
    }

    /**
     * Returns a Provenance with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Provenance
     */
    @JsonCreator
    public static Provenance of(String value) {
        synchronized (Provenance.class) {
            return values.computeIfAbsent(value, v -> new Provenance(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ProvenanceEnum> asEnum() {
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
        Provenance other = (Provenance) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Provenance [value=" + value + "]";
    }

    // return an array just like an enum
    public static Provenance[] values() {
        synchronized (Provenance.class) {
            return values.values().toArray(new Provenance[] {});
        }
    }

    private static final Map<String, Provenance> createValuesMap() {
        Map<String, Provenance> map = new LinkedHashMap<>();
        map.put("submitted", SUBMITTED);
        return map;
    }

    private static final Map<String, ProvenanceEnum> createEnumsMap() {
        Map<String, ProvenanceEnum> map = new HashMap<>();
        map.put("submitted", ProvenanceEnum.SUBMITTED);
        return map;
    }

    public enum ProvenanceEnum {

        SUBMITTED("submitted"),
        ;

        private final String value;

        private ProvenanceEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
