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
public class ListChangeState {

    public static final ListChangeState PROPAGATING = new ListChangeState("propagating");
    public static final ListChangeState COMPLETED = new ListChangeState("completed");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ListChangeState> values = createValuesMap();
    private static final Map<String, ListChangeStateEnum> enums = createEnumsMap();

    private final String value;

    private ListChangeState(String value) {
        this.value = value;
    }

    /**
     * Returns a ListChangeState with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ListChangeState
     */
    @JsonCreator
    public static ListChangeState of(String value) {
        synchronized (ListChangeState.class) {
            return values.computeIfAbsent(value, v -> new ListChangeState(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ListChangeStateEnum> asEnum() {
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
        ListChangeState other = (ListChangeState) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ListChangeState [value=" + value + "]";
    }

    // return an array just like an enum
    public static ListChangeState[] values() {
        synchronized (ListChangeState.class) {
            return values.values().toArray(new ListChangeState[] {});
        }
    }

    private static final Map<String, ListChangeState> createValuesMap() {
        Map<String, ListChangeState> map = new LinkedHashMap<>();
        map.put("propagating", PROPAGATING);
        map.put("completed", COMPLETED);
        return map;
    }

    private static final Map<String, ListChangeStateEnum> createEnumsMap() {
        Map<String, ListChangeStateEnum> map = new HashMap<>();
        map.put("propagating", ListChangeStateEnum.PROPAGATING);
        map.put("completed", ListChangeStateEnum.COMPLETED);
        return map;
    }

    public enum ListChangeStateEnum {

        PROPAGATING("propagating"),
        COMPLETED("completed"),
        ;

        private final String value;

        private ListChangeStateEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
