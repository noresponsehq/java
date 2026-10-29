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
public class Conclusion {

    public static final Conclusion BENIGN = new Conclusion("benign");
    public static final Conclusion ABUSE = new Conclusion("abuse");
    public static final Conclusion INCONCLUSIVE = new Conclusion("inconclusive");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Conclusion> values = createValuesMap();
    private static final Map<String, ConclusionEnum> enums = createEnumsMap();

    private final String value;

    private Conclusion(String value) {
        this.value = value;
    }

    /**
     * Returns a Conclusion with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Conclusion
     */
    @JsonCreator
    public static Conclusion of(String value) {
        synchronized (Conclusion.class) {
            return values.computeIfAbsent(value, v -> new Conclusion(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ConclusionEnum> asEnum() {
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
        Conclusion other = (Conclusion) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Conclusion [value=" + value + "]";
    }

    // return an array just like an enum
    public static Conclusion[] values() {
        synchronized (Conclusion.class) {
            return values.values().toArray(new Conclusion[] {});
        }
    }

    private static final Map<String, Conclusion> createValuesMap() {
        Map<String, Conclusion> map = new LinkedHashMap<>();
        map.put("benign", BENIGN);
        map.put("abuse", ABUSE);
        map.put("inconclusive", INCONCLUSIVE);
        return map;
    }

    private static final Map<String, ConclusionEnum> createEnumsMap() {
        Map<String, ConclusionEnum> map = new HashMap<>();
        map.put("benign", ConclusionEnum.BENIGN);
        map.put("abuse", ConclusionEnum.ABUSE);
        map.put("inconclusive", ConclusionEnum.INCONCLUSIVE);
        return map;
    }

    public enum ConclusionEnum {

        BENIGN("benign"),
        ABUSE("abuse"),
        INCONCLUSIVE("inconclusive"),
        ;

        private final String value;

        private ConclusionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
