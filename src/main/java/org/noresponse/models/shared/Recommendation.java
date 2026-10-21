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
 * Recommendation
 *
 * <p>The recommended action: allow, step_up or review. Your application applies it.
 */
public class Recommendation {

    public static final Recommendation ALLOW = new Recommendation("allow");
    public static final Recommendation STEP_UP = new Recommendation("step_up");
    public static final Recommendation REVIEW = new Recommendation("review");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Recommendation> values = createValuesMap();
    private static final Map<String, RecommendationEnum> enums = createEnumsMap();

    private final String value;

    private Recommendation(String value) {
        this.value = value;
    }

    /**
     * Returns a Recommendation with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Recommendation
     */
    @JsonCreator
    public static Recommendation of(String value) {
        synchronized (Recommendation.class) {
            return values.computeIfAbsent(value, v -> new Recommendation(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RecommendationEnum> asEnum() {
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
        Recommendation other = (Recommendation) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Recommendation [value=" + value + "]";
    }

    // return an array just like an enum
    public static Recommendation[] values() {
        synchronized (Recommendation.class) {
            return values.values().toArray(new Recommendation[] {});
        }
    }

    private static final Map<String, Recommendation> createValuesMap() {
        Map<String, Recommendation> map = new LinkedHashMap<>();
        map.put("allow", ALLOW);
        map.put("step_up", STEP_UP);
        map.put("review", REVIEW);
        return map;
    }

    private static final Map<String, RecommendationEnum> createEnumsMap() {
        Map<String, RecommendationEnum> map = new HashMap<>();
        map.put("allow", RecommendationEnum.ALLOW);
        map.put("step_up", RecommendationEnum.STEP_UP);
        map.put("review", RecommendationEnum.REVIEW);
        return map;
    }

    public enum RecommendationEnum {

        ALLOW("allow"),
        STEP_UP("step_up"),
        REVIEW("review"),
        ;

        private final String value;

        private RecommendationEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
