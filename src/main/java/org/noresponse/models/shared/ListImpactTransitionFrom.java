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
public class ListImpactTransitionFrom {

    public static final ListImpactTransitionFrom NONE = new ListImpactTransitionFrom("none");
    public static final ListImpactTransitionFrom ALLOW = new ListImpactTransitionFrom("allow");
    public static final ListImpactTransitionFrom BLOCK = new ListImpactTransitionFrom("block");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ListImpactTransitionFrom> values = createValuesMap();
    private static final Map<String, ListImpactTransitionFromEnum> enums = createEnumsMap();

    private final String value;

    private ListImpactTransitionFrom(String value) {
        this.value = value;
    }

    /**
     * Returns a ListImpactTransitionFrom with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ListImpactTransitionFrom
     */
    @JsonCreator
    public static ListImpactTransitionFrom of(String value) {
        synchronized (ListImpactTransitionFrom.class) {
            return values.computeIfAbsent(value, v -> new ListImpactTransitionFrom(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ListImpactTransitionFromEnum> asEnum() {
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
        ListImpactTransitionFrom other = (ListImpactTransitionFrom) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ListImpactTransitionFrom [value=" + value + "]";
    }

    // return an array just like an enum
    public static ListImpactTransitionFrom[] values() {
        synchronized (ListImpactTransitionFrom.class) {
            return values.values().toArray(new ListImpactTransitionFrom[] {});
        }
    }

    private static final Map<String, ListImpactTransitionFrom> createValuesMap() {
        Map<String, ListImpactTransitionFrom> map = new LinkedHashMap<>();
        map.put("none", NONE);
        map.put("allow", ALLOW);
        map.put("block", BLOCK);
        return map;
    }

    private static final Map<String, ListImpactTransitionFromEnum> createEnumsMap() {
        Map<String, ListImpactTransitionFromEnum> map = new HashMap<>();
        map.put("none", ListImpactTransitionFromEnum.NONE);
        map.put("allow", ListImpactTransitionFromEnum.ALLOW);
        map.put("block", ListImpactTransitionFromEnum.BLOCK);
        return map;
    }

    public enum ListImpactTransitionFromEnum {

        NONE("none"),
        ALLOW("allow"),
        BLOCK("block"),
        ;

        private final String value;

        private ListImpactTransitionFromEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
