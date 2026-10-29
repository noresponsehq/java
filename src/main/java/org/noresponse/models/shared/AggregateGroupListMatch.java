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
 * AggregateGroupListMatch
 *
 * <p>The list action captured with the first answer. none means no matching entry was found. For
 * retained_first_answers an absent field means the older assessment did not capture list state; this
 * remains a separate group.
 */
public class AggregateGroupListMatch {

    public static final AggregateGroupListMatch BLOCK = new AggregateGroupListMatch("block");
    public static final AggregateGroupListMatch ALLOW = new AggregateGroupListMatch("allow");
    public static final AggregateGroupListMatch NONE = new AggregateGroupListMatch("none");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AggregateGroupListMatch> values = createValuesMap();
    private static final Map<String, AggregateGroupListMatchEnum> enums = createEnumsMap();

    private final String value;

    private AggregateGroupListMatch(String value) {
        this.value = value;
    }

    /**
     * Returns a AggregateGroupListMatch with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AggregateGroupListMatch
     */
    @JsonCreator
    public static AggregateGroupListMatch of(String value) {
        synchronized (AggregateGroupListMatch.class) {
            return values.computeIfAbsent(value, v -> new AggregateGroupListMatch(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AggregateGroupListMatchEnum> asEnum() {
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
        AggregateGroupListMatch other = (AggregateGroupListMatch) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AggregateGroupListMatch [value=" + value + "]";
    }

    // return an array just like an enum
    public static AggregateGroupListMatch[] values() {
        synchronized (AggregateGroupListMatch.class) {
            return values.values().toArray(new AggregateGroupListMatch[] {});
        }
    }

    private static final Map<String, AggregateGroupListMatch> createValuesMap() {
        Map<String, AggregateGroupListMatch> map = new LinkedHashMap<>();
        map.put("block", BLOCK);
        map.put("allow", ALLOW);
        map.put("none", NONE);
        return map;
    }

    private static final Map<String, AggregateGroupListMatchEnum> createEnumsMap() {
        Map<String, AggregateGroupListMatchEnum> map = new HashMap<>();
        map.put("block", AggregateGroupListMatchEnum.BLOCK);
        map.put("allow", AggregateGroupListMatchEnum.ALLOW);
        map.put("none", AggregateGroupListMatchEnum.NONE);
        return map;
    }

    public enum AggregateGroupListMatchEnum {

        BLOCK("block"),
        ALLOW("allow"),
        NONE("none"),
        ;

        private final String value;

        private AggregateGroupListMatchEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
