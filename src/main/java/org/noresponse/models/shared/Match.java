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
 * Match
 *
 * <p>exact compares the original submitted value; canonical uses identifier-specific normalisation;
 * mailbox explicitly selects the email alias rules used by lists.
 */
public class Match {

    public static final Match EXACT = new Match("exact");
    public static final Match CANONICAL = new Match("canonical");
    public static final Match MAILBOX = new Match("mailbox");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Match> values = createValuesMap();
    private static final Map<String, MatchEnum> enums = createEnumsMap();

    private final String value;

    private Match(String value) {
        this.value = value;
    }

    /**
     * Returns a Match with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Match
     */
    @JsonCreator
    public static Match of(String value) {
        synchronized (Match.class) {
            return values.computeIfAbsent(value, v -> new Match(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MatchEnum> asEnum() {
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
        Match other = (Match) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Match [value=" + value + "]";
    }

    // return an array just like an enum
    public static Match[] values() {
        synchronized (Match.class) {
            return values.values().toArray(new Match[] {});
        }
    }

    private static final Map<String, Match> createValuesMap() {
        Map<String, Match> map = new LinkedHashMap<>();
        map.put("exact", EXACT);
        map.put("canonical", CANONICAL);
        map.put("mailbox", MAILBOX);
        return map;
    }

    private static final Map<String, MatchEnum> createEnumsMap() {
        Map<String, MatchEnum> map = new HashMap<>();
        map.put("exact", MatchEnum.EXACT);
        map.put("canonical", MatchEnum.CANONICAL);
        map.put("mailbox", MatchEnum.MAILBOX);
        return map;
    }

    public enum MatchEnum {

        EXACT("exact"),
        CANONICAL("canonical"),
        MAILBOX("mailbox"),
        ;

        private final String value;

        private MatchEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
