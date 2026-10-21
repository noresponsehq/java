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
 * RiskConfidence
 *
 * <p>How much of the planned evidence answered; it says nothing about risk.
 */
public class RiskConfidence {

    public static final RiskConfidence LOW = new RiskConfidence("low");
    public static final RiskConfidence MEDIUM = new RiskConfidence("medium");
    public static final RiskConfidence HIGH = new RiskConfidence("high");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RiskConfidence> values = createValuesMap();
    private static final Map<String, RiskConfidenceEnum> enums = createEnumsMap();

    private final String value;

    private RiskConfidence(String value) {
        this.value = value;
    }

    /**
     * Returns a RiskConfidence with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RiskConfidence
     */
    @JsonCreator
    public static RiskConfidence of(String value) {
        synchronized (RiskConfidence.class) {
            return values.computeIfAbsent(value, v -> new RiskConfidence(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RiskConfidenceEnum> asEnum() {
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
        RiskConfidence other = (RiskConfidence) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RiskConfidence [value=" + value + "]";
    }

    // return an array just like an enum
    public static RiskConfidence[] values() {
        synchronized (RiskConfidence.class) {
            return values.values().toArray(new RiskConfidence[] {});
        }
    }

    private static final Map<String, RiskConfidence> createValuesMap() {
        Map<String, RiskConfidence> map = new LinkedHashMap<>();
        map.put("low", LOW);
        map.put("medium", MEDIUM);
        map.put("high", HIGH);
        return map;
    }

    private static final Map<String, RiskConfidenceEnum> createEnumsMap() {
        Map<String, RiskConfidenceEnum> map = new HashMap<>();
        map.put("low", RiskConfidenceEnum.LOW);
        map.put("medium", RiskConfidenceEnum.MEDIUM);
        map.put("high", RiskConfidenceEnum.HIGH);
        return map;
    }

    public enum RiskConfidenceEnum {

        LOW("low"),
        MEDIUM("medium"),
        HIGH("high"),
        ;

        private final String value;

        private RiskConfidenceEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
