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
 * EnforcementState
 *
 * <p>The account's state in the workspace's identity provider just before the action: onboarding,
 * provisional (created, not yet activated), active, restricted or rejected.
 */
public class EnforcementState {

    public static final EnforcementState ONBOARDING = new EnforcementState("onboarding");
    public static final EnforcementState PROVISIONAL = new EnforcementState("provisional");
    public static final EnforcementState ACTIVE = new EnforcementState("active");
    public static final EnforcementState RESTRICTED = new EnforcementState("restricted");
    public static final EnforcementState REJECTED = new EnforcementState("rejected");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, EnforcementState> values = createValuesMap();
    private static final Map<String, EnforcementStateEnum> enums = createEnumsMap();

    private final String value;

    private EnforcementState(String value) {
        this.value = value;
    }

    /**
     * Returns a EnforcementState with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as EnforcementState
     */
    @JsonCreator
    public static EnforcementState of(String value) {
        synchronized (EnforcementState.class) {
            return values.computeIfAbsent(value, v -> new EnforcementState(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EnforcementStateEnum> asEnum() {
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
        EnforcementState other = (EnforcementState) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "EnforcementState [value=" + value + "]";
    }

    // return an array just like an enum
    public static EnforcementState[] values() {
        synchronized (EnforcementState.class) {
            return values.values().toArray(new EnforcementState[] {});
        }
    }

    private static final Map<String, EnforcementState> createValuesMap() {
        Map<String, EnforcementState> map = new LinkedHashMap<>();
        map.put("onboarding", ONBOARDING);
        map.put("provisional", PROVISIONAL);
        map.put("active", ACTIVE);
        map.put("restricted", RESTRICTED);
        map.put("rejected", REJECTED);
        return map;
    }

    private static final Map<String, EnforcementStateEnum> createEnumsMap() {
        Map<String, EnforcementStateEnum> map = new HashMap<>();
        map.put("onboarding", EnforcementStateEnum.ONBOARDING);
        map.put("provisional", EnforcementStateEnum.PROVISIONAL);
        map.put("active", EnforcementStateEnum.ACTIVE);
        map.put("restricted", EnforcementStateEnum.RESTRICTED);
        map.put("rejected", EnforcementStateEnum.REJECTED);
        return map;
    }

    public enum EnforcementStateEnum {

        ONBOARDING("onboarding"),
        PROVISIONAL("provisional"),
        ACTIVE("active"),
        RESTRICTED("restricted"),
        REJECTED("rejected"),
        ;

        private final String value;

        private EnforcementStateEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
