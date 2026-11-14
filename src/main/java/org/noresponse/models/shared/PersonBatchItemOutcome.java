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
 * PersonBatchItemOutcome
 *
 * <p>unchanged: the row matched a person with the same details.
 */
public class PersonBatchItemOutcome {

    public static final PersonBatchItemOutcome CREATED = new PersonBatchItemOutcome("created");
    public static final PersonBatchItemOutcome UPDATED = new PersonBatchItemOutcome("updated");
    public static final PersonBatchItemOutcome UNCHANGED = new PersonBatchItemOutcome("unchanged");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, PersonBatchItemOutcome> values = createValuesMap();
    private static final Map<String, PersonBatchItemOutcomeEnum> enums = createEnumsMap();

    private final String value;

    private PersonBatchItemOutcome(String value) {
        this.value = value;
    }

    /**
     * Returns a PersonBatchItemOutcome with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as PersonBatchItemOutcome
     */
    @JsonCreator
    public static PersonBatchItemOutcome of(String value) {
        synchronized (PersonBatchItemOutcome.class) {
            return values.computeIfAbsent(value, v -> new PersonBatchItemOutcome(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PersonBatchItemOutcomeEnum> asEnum() {
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
        PersonBatchItemOutcome other = (PersonBatchItemOutcome) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "PersonBatchItemOutcome [value=" + value + "]";
    }

    // return an array just like an enum
    public static PersonBatchItemOutcome[] values() {
        synchronized (PersonBatchItemOutcome.class) {
            return values.values().toArray(new PersonBatchItemOutcome[] {});
        }
    }

    private static final Map<String, PersonBatchItemOutcome> createValuesMap() {
        Map<String, PersonBatchItemOutcome> map = new LinkedHashMap<>();
        map.put("created", CREATED);
        map.put("updated", UPDATED);
        map.put("unchanged", UNCHANGED);
        return map;
    }

    private static final Map<String, PersonBatchItemOutcomeEnum> createEnumsMap() {
        Map<String, PersonBatchItemOutcomeEnum> map = new HashMap<>();
        map.put("created", PersonBatchItemOutcomeEnum.CREATED);
        map.put("updated", PersonBatchItemOutcomeEnum.UPDATED);
        map.put("unchanged", PersonBatchItemOutcomeEnum.UNCHANGED);
        return map;
    }

    public enum PersonBatchItemOutcomeEnum {

        CREATED("created"),
        UPDATED("updated"),
        UNCHANGED("unchanged"),
        ;

        private final String value;

        private PersonBatchItemOutcomeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
