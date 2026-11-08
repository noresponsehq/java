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
 * AttendeeBatchItemOutcome
 *
 * <p>unchanged: the row matched an attendee with the same details.
 */
public class AttendeeBatchItemOutcome {

    public static final AttendeeBatchItemOutcome CREATED = new AttendeeBatchItemOutcome("created");
    public static final AttendeeBatchItemOutcome UPDATED = new AttendeeBatchItemOutcome("updated");
    public static final AttendeeBatchItemOutcome UNCHANGED = new AttendeeBatchItemOutcome("unchanged");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AttendeeBatchItemOutcome> values = createValuesMap();
    private static final Map<String, AttendeeBatchItemOutcomeEnum> enums = createEnumsMap();

    private final String value;

    private AttendeeBatchItemOutcome(String value) {
        this.value = value;
    }

    /**
     * Returns a AttendeeBatchItemOutcome with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AttendeeBatchItemOutcome
     */
    @JsonCreator
    public static AttendeeBatchItemOutcome of(String value) {
        synchronized (AttendeeBatchItemOutcome.class) {
            return values.computeIfAbsent(value, v -> new AttendeeBatchItemOutcome(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AttendeeBatchItemOutcomeEnum> asEnum() {
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
        AttendeeBatchItemOutcome other = (AttendeeBatchItemOutcome) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AttendeeBatchItemOutcome [value=" + value + "]";
    }

    // return an array just like an enum
    public static AttendeeBatchItemOutcome[] values() {
        synchronized (AttendeeBatchItemOutcome.class) {
            return values.values().toArray(new AttendeeBatchItemOutcome[] {});
        }
    }

    private static final Map<String, AttendeeBatchItemOutcome> createValuesMap() {
        Map<String, AttendeeBatchItemOutcome> map = new LinkedHashMap<>();
        map.put("created", CREATED);
        map.put("updated", UPDATED);
        map.put("unchanged", UNCHANGED);
        return map;
    }

    private static final Map<String, AttendeeBatchItemOutcomeEnum> createEnumsMap() {
        Map<String, AttendeeBatchItemOutcomeEnum> map = new HashMap<>();
        map.put("created", AttendeeBatchItemOutcomeEnum.CREATED);
        map.put("updated", AttendeeBatchItemOutcomeEnum.UPDATED);
        map.put("unchanged", AttendeeBatchItemOutcomeEnum.UNCHANGED);
        return map;
    }

    public enum AttendeeBatchItemOutcomeEnum {

        CREATED("created"),
        UPDATED("updated"),
        UNCHANGED("unchanged"),
        ;

        private final String value;

        private AttendeeBatchItemOutcomeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
