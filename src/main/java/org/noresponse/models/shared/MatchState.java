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
 * MatchState
 *
 * <p>How sure the latest screening is that it found the attendee's accounts. unscreened until a screening
 * reaches the attendee, and after one that had no profile to check (no_profile_to_check).
 */
public class MatchState {

    public static final MatchState UNSCREENED = new MatchState("unscreened");
    public static final MatchState CONFIRMED = new MatchState("confirmed");
    public static final MatchState PROBABLE = new MatchState("probable");
    public static final MatchState POSSIBLE = new MatchState("possible");
    public static final MatchState NONE_FOUND = new MatchState("none_found");
    public static final MatchState INACCESSIBLE = new MatchState("inaccessible");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, MatchState> values = createValuesMap();
    private static final Map<String, MatchStateEnum> enums = createEnumsMap();

    private final String value;

    private MatchState(String value) {
        this.value = value;
    }

    /**
     * Returns a MatchState with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as MatchState
     */
    @JsonCreator
    public static MatchState of(String value) {
        synchronized (MatchState.class) {
            return values.computeIfAbsent(value, v -> new MatchState(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MatchStateEnum> asEnum() {
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
        MatchState other = (MatchState) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "MatchState [value=" + value + "]";
    }

    // return an array just like an enum
    public static MatchState[] values() {
        synchronized (MatchState.class) {
            return values.values().toArray(new MatchState[] {});
        }
    }

    private static final Map<String, MatchState> createValuesMap() {
        Map<String, MatchState> map = new LinkedHashMap<>();
        map.put("unscreened", UNSCREENED);
        map.put("confirmed", CONFIRMED);
        map.put("probable", PROBABLE);
        map.put("possible", POSSIBLE);
        map.put("none_found", NONE_FOUND);
        map.put("inaccessible", INACCESSIBLE);
        return map;
    }

    private static final Map<String, MatchStateEnum> createEnumsMap() {
        Map<String, MatchStateEnum> map = new HashMap<>();
        map.put("unscreened", MatchStateEnum.UNSCREENED);
        map.put("confirmed", MatchStateEnum.CONFIRMED);
        map.put("probable", MatchStateEnum.PROBABLE);
        map.put("possible", MatchStateEnum.POSSIBLE);
        map.put("none_found", MatchStateEnum.NONE_FOUND);
        map.put("inaccessible", MatchStateEnum.INACCESSIBLE);
        return map;
    }

    public enum MatchStateEnum {

        UNSCREENED("unscreened"),
        CONFIRMED("confirmed"),
        PROBABLE("probable"),
        POSSIBLE("possible"),
        NONE_FOUND("none_found"),
        INACCESSIBLE("inaccessible"),
        ;

        private final String value;

        private MatchStateEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
