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
 * AggregateDimension
 *
 * <p>decision, recommendation and band: the first answer's. flow: the assessment's. list_match: the
 * action of the list entry that decided the account when it was first answered.
 *
 * <p>reason: a reason the first answer fired, with its impact band. lane: an evidence lane the first
 * answer listed in incomplete_sources. country and asn: the submitted IP's, from the IP data.
 */
public class AggregateDimension {

    public static final AggregateDimension DECISION = new AggregateDimension("decision");
    public static final AggregateDimension FLOW = new AggregateDimension("flow");
    public static final AggregateDimension RECOMMENDATION = new AggregateDimension("recommendation");
    public static final AggregateDimension BAND = new AggregateDimension("band");
    public static final AggregateDimension REASON = new AggregateDimension("reason");
    public static final AggregateDimension LANE = new AggregateDimension("lane");
    public static final AggregateDimension COUNTRY = new AggregateDimension("country");
    public static final AggregateDimension ASN = new AggregateDimension("asn");
    public static final AggregateDimension LIST_MATCH = new AggregateDimension("list_match");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AggregateDimension> values = createValuesMap();
    private static final Map<String, AggregateDimensionEnum> enums = createEnumsMap();

    private final String value;

    private AggregateDimension(String value) {
        this.value = value;
    }

    /**
     * Returns a AggregateDimension with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AggregateDimension
     */
    @JsonCreator
    public static AggregateDimension of(String value) {
        synchronized (AggregateDimension.class) {
            return values.computeIfAbsent(value, v -> new AggregateDimension(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AggregateDimensionEnum> asEnum() {
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
        AggregateDimension other = (AggregateDimension) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AggregateDimension [value=" + value + "]";
    }

    // return an array just like an enum
    public static AggregateDimension[] values() {
        synchronized (AggregateDimension.class) {
            return values.values().toArray(new AggregateDimension[] {});
        }
    }

    private static final Map<String, AggregateDimension> createValuesMap() {
        Map<String, AggregateDimension> map = new LinkedHashMap<>();
        map.put("decision", DECISION);
        map.put("flow", FLOW);
        map.put("recommendation", RECOMMENDATION);
        map.put("band", BAND);
        map.put("reason", REASON);
        map.put("lane", LANE);
        map.put("country", COUNTRY);
        map.put("asn", ASN);
        map.put("list_match", LIST_MATCH);
        return map;
    }

    private static final Map<String, AggregateDimensionEnum> createEnumsMap() {
        Map<String, AggregateDimensionEnum> map = new HashMap<>();
        map.put("decision", AggregateDimensionEnum.DECISION);
        map.put("flow", AggregateDimensionEnum.FLOW);
        map.put("recommendation", AggregateDimensionEnum.RECOMMENDATION);
        map.put("band", AggregateDimensionEnum.BAND);
        map.put("reason", AggregateDimensionEnum.REASON);
        map.put("lane", AggregateDimensionEnum.LANE);
        map.put("country", AggregateDimensionEnum.COUNTRY);
        map.put("asn", AggregateDimensionEnum.ASN);
        map.put("list_match", AggregateDimensionEnum.LIST_MATCH);
        return map;
    }

    public enum AggregateDimensionEnum {

        DECISION("decision"),
        FLOW("flow"),
        RECOMMENDATION("recommendation"),
        BAND("band"),
        REASON("reason"),
        LANE("lane"),
        COUNTRY("country"),
        ASN("asn"),
        LIST_MATCH("list_match"),
        ;

        private final String value;

        private AggregateDimensionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
