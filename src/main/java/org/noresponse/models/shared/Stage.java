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
public class Stage {

    public static final Stage LOOKUP = new Stage("lookup");
    public static final Stage LOOKUP_TYPING = new Stage("lookup_typing");
    public static final Stage PLATFORMS = new Stage("platforms");
    public static final Stage WRITTEN = new Stage("written");
    public static final Stage LATE_PLATFORMS = new Stage("late_platforms");
    public static final Stage LATE_LOOKUP = new Stage("late_lookup");
    public static final Stage AI_TYPING = new Stage("ai_typing");
    public static final Stage ANALYSIS = new Stage("analysis");
    public static final Stage RATING = new Stage("rating");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Stage> values = createValuesMap();
    private static final Map<String, StageEnum> enums = createEnumsMap();

    private final String value;

    private Stage(String value) {
        this.value = value;
    }

    /**
     * Returns a Stage with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Stage
     */
    @JsonCreator
    public static Stage of(String value) {
        synchronized (Stage.class) {
            return values.computeIfAbsent(value, v -> new Stage(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<StageEnum> asEnum() {
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
        Stage other = (Stage) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Stage [value=" + value + "]";
    }

    // return an array just like an enum
    public static Stage[] values() {
        synchronized (Stage.class) {
            return values.values().toArray(new Stage[] {});
        }
    }

    private static final Map<String, Stage> createValuesMap() {
        Map<String, Stage> map = new LinkedHashMap<>();
        map.put("lookup", LOOKUP);
        map.put("lookup_typing", LOOKUP_TYPING);
        map.put("platforms", PLATFORMS);
        map.put("written", WRITTEN);
        map.put("late_platforms", LATE_PLATFORMS);
        map.put("late_lookup", LATE_LOOKUP);
        map.put("ai_typing", AI_TYPING);
        map.put("analysis", ANALYSIS);
        map.put("rating", RATING);
        return map;
    }

    private static final Map<String, StageEnum> createEnumsMap() {
        Map<String, StageEnum> map = new HashMap<>();
        map.put("lookup", StageEnum.LOOKUP);
        map.put("lookup_typing", StageEnum.LOOKUP_TYPING);
        map.put("platforms", StageEnum.PLATFORMS);
        map.put("written", StageEnum.WRITTEN);
        map.put("late_platforms", StageEnum.LATE_PLATFORMS);
        map.put("late_lookup", StageEnum.LATE_LOOKUP);
        map.put("ai_typing", StageEnum.AI_TYPING);
        map.put("analysis", StageEnum.ANALYSIS);
        map.put("rating", StageEnum.RATING);
        return map;
    }

    public enum StageEnum {

        LOOKUP("lookup"),
        LOOKUP_TYPING("lookup_typing"),
        PLATFORMS("platforms"),
        WRITTEN("written"),
        LATE_PLATFORMS("late_platforms"),
        LATE_LOOKUP("late_lookup"),
        AI_TYPING("ai_typing"),
        ANALYSIS("analysis"),
        RATING("rating"),
        ;

        private final String value;

        private StageEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
