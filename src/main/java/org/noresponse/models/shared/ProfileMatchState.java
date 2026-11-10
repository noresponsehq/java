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
 * ProfileMatchState
 *
 * <p>How sure the screening is that a profile is the attendee's, from its evidence: confirmed is two or
 * more hard identifiers (email, phone, a supplied handle, or a fact an existing source returned),
 * probable is one identifier beyond the name, possible is the name alone.
 */
public class ProfileMatchState {

    public static final ProfileMatchState CONFIRMED = new ProfileMatchState("confirmed");
    public static final ProfileMatchState PROBABLE = new ProfileMatchState("probable");
    public static final ProfileMatchState POSSIBLE = new ProfileMatchState("possible");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ProfileMatchState> values = createValuesMap();
    private static final Map<String, ProfileMatchStateEnum> enums = createEnumsMap();

    private final String value;

    private ProfileMatchState(String value) {
        this.value = value;
    }

    /**
     * Returns a ProfileMatchState with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ProfileMatchState
     */
    @JsonCreator
    public static ProfileMatchState of(String value) {
        synchronized (ProfileMatchState.class) {
            return values.computeIfAbsent(value, v -> new ProfileMatchState(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ProfileMatchStateEnum> asEnum() {
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
        ProfileMatchState other = (ProfileMatchState) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ProfileMatchState [value=" + value + "]";
    }

    // return an array just like an enum
    public static ProfileMatchState[] values() {
        synchronized (ProfileMatchState.class) {
            return values.values().toArray(new ProfileMatchState[] {});
        }
    }

    private static final Map<String, ProfileMatchState> createValuesMap() {
        Map<String, ProfileMatchState> map = new LinkedHashMap<>();
        map.put("confirmed", CONFIRMED);
        map.put("probable", PROBABLE);
        map.put("possible", POSSIBLE);
        return map;
    }

    private static final Map<String, ProfileMatchStateEnum> createEnumsMap() {
        Map<String, ProfileMatchStateEnum> map = new HashMap<>();
        map.put("confirmed", ProfileMatchStateEnum.CONFIRMED);
        map.put("probable", ProfileMatchStateEnum.PROBABLE);
        map.put("possible", ProfileMatchStateEnum.POSSIBLE);
        return map;
    }

    public enum ProfileMatchStateEnum {

        CONFIRMED("confirmed"),
        PROBABLE("probable"),
        POSSIBLE("possible"),
        ;

        private final String value;

        private ProfileMatchStateEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
