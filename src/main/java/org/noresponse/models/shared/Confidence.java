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
 * Confidence
 *
 * <p>Ordinal confidence in the recommendation under the current policy: low, medium, high or unavailable.
 */
public class Confidence {

    public static final Confidence LOW = new Confidence("low");
    public static final Confidence MEDIUM = new Confidence("medium");
    public static final Confidence HIGH = new Confidence("high");
    public static final Confidence UNAVAILABLE = new Confidence("unavailable");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Confidence> values = createValuesMap();
    private static final Map<String, ConfidenceEnum> enums = createEnumsMap();

    private final String value;

    private Confidence(String value) {
        this.value = value;
    }

    /**
     * Returns a Confidence with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Confidence
     */
    @JsonCreator
    public static Confidence of(String value) {
        synchronized (Confidence.class) {
            return values.computeIfAbsent(value, v -> new Confidence(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ConfidenceEnum> asEnum() {
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
        Confidence other = (Confidence) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Confidence [value=" + value + "]";
    }

    // return an array just like an enum
    public static Confidence[] values() {
        synchronized (Confidence.class) {
            return values.values().toArray(new Confidence[] {});
        }
    }

    private static final Map<String, Confidence> createValuesMap() {
        Map<String, Confidence> map = new LinkedHashMap<>();
        map.put("low", LOW);
        map.put("medium", MEDIUM);
        map.put("high", HIGH);
        map.put("unavailable", UNAVAILABLE);
        return map;
    }

    private static final Map<String, ConfidenceEnum> createEnumsMap() {
        Map<String, ConfidenceEnum> map = new HashMap<>();
        map.put("low", ConfidenceEnum.LOW);
        map.put("medium", ConfidenceEnum.MEDIUM);
        map.put("high", ConfidenceEnum.HIGH);
        map.put("unavailable", ConfidenceEnum.UNAVAILABLE);
        return map;
    }

    public enum ConfidenceEnum {

        LOW("low"),
        MEDIUM("medium"),
        HIGH("high"),
        UNAVAILABLE("unavailable"),
        ;

        private final String value;

        private ConfidenceEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
