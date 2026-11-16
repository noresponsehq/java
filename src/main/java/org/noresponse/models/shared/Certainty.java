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
 * Certainty
 *
 * <p>confirmed: a linked record or the searched identifier states it directly. likely: a strong
 * inference. possible: a weak inference.
 */
public class Certainty {

    public static final Certainty CONFIRMED = new Certainty("confirmed");
    public static final Certainty LIKELY = new Certainty("likely");
    public static final Certainty POSSIBLE = new Certainty("possible");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Certainty> values = createValuesMap();
    private static final Map<String, CertaintyEnum> enums = createEnumsMap();

    private final String value;

    private Certainty(String value) {
        this.value = value;
    }

    /**
     * Returns a Certainty with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Certainty
     */
    @JsonCreator
    public static Certainty of(String value) {
        synchronized (Certainty.class) {
            return values.computeIfAbsent(value, v -> new Certainty(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<CertaintyEnum> asEnum() {
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
        Certainty other = (Certainty) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Certainty [value=" + value + "]";
    }

    // return an array just like an enum
    public static Certainty[] values() {
        synchronized (Certainty.class) {
            return values.values().toArray(new Certainty[] {});
        }
    }

    private static final Map<String, Certainty> createValuesMap() {
        Map<String, Certainty> map = new LinkedHashMap<>();
        map.put("confirmed", CONFIRMED);
        map.put("likely", LIKELY);
        map.put("possible", POSSIBLE);
        return map;
    }

    private static final Map<String, CertaintyEnum> createEnumsMap() {
        Map<String, CertaintyEnum> map = new HashMap<>();
        map.put("confirmed", CertaintyEnum.CONFIRMED);
        map.put("likely", CertaintyEnum.LIKELY);
        map.put("possible", CertaintyEnum.POSSIBLE);
        return map;
    }

    public enum CertaintyEnum {

        CONFIRMED("confirmed"),
        LIKELY("likely"),
        POSSIBLE("possible"),
        ;

        private final String value;

        private CertaintyEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
