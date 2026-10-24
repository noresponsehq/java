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
 * EnforcementAction
 *
 * <p>What the workspace did in its identity provider: revoke_sessions (signed the account out
 * everywhere), restrict (limited what the account can do), unrestrict (lifted an earlier restriction),
 * fail_onboarding (stopped an account that was still onboarding), reject_activation (refused to
 * activate a provisional account) or none (decided to take no action).
 */
public class EnforcementAction {

    public static final EnforcementAction REVOKE_SESSIONS = new EnforcementAction("revoke_sessions");
    public static final EnforcementAction RESTRICT = new EnforcementAction("restrict");
    public static final EnforcementAction UNRESTRICT = new EnforcementAction("unrestrict");
    public static final EnforcementAction FAIL_ONBOARDING = new EnforcementAction("fail_onboarding");
    public static final EnforcementAction REJECT_ACTIVATION = new EnforcementAction("reject_activation");
    public static final EnforcementAction NONE = new EnforcementAction("none");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, EnforcementAction> values = createValuesMap();
    private static final Map<String, EnforcementActionEnum> enums = createEnumsMap();

    private final String value;

    private EnforcementAction(String value) {
        this.value = value;
    }

    /**
     * Returns a EnforcementAction with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as EnforcementAction
     */
    @JsonCreator
    public static EnforcementAction of(String value) {
        synchronized (EnforcementAction.class) {
            return values.computeIfAbsent(value, v -> new EnforcementAction(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EnforcementActionEnum> asEnum() {
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
        EnforcementAction other = (EnforcementAction) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "EnforcementAction [value=" + value + "]";
    }

    // return an array just like an enum
    public static EnforcementAction[] values() {
        synchronized (EnforcementAction.class) {
            return values.values().toArray(new EnforcementAction[] {});
        }
    }

    private static final Map<String, EnforcementAction> createValuesMap() {
        Map<String, EnforcementAction> map = new LinkedHashMap<>();
        map.put("revoke_sessions", REVOKE_SESSIONS);
        map.put("restrict", RESTRICT);
        map.put("unrestrict", UNRESTRICT);
        map.put("fail_onboarding", FAIL_ONBOARDING);
        map.put("reject_activation", REJECT_ACTIVATION);
        map.put("none", NONE);
        return map;
    }

    private static final Map<String, EnforcementActionEnum> createEnumsMap() {
        Map<String, EnforcementActionEnum> map = new HashMap<>();
        map.put("revoke_sessions", EnforcementActionEnum.REVOKE_SESSIONS);
        map.put("restrict", EnforcementActionEnum.RESTRICT);
        map.put("unrestrict", EnforcementActionEnum.UNRESTRICT);
        map.put("fail_onboarding", EnforcementActionEnum.FAIL_ONBOARDING);
        map.put("reject_activation", EnforcementActionEnum.REJECT_ACTIVATION);
        map.put("none", EnforcementActionEnum.NONE);
        return map;
    }

    public enum EnforcementActionEnum {

        REVOKE_SESSIONS("revoke_sessions"),
        RESTRICT("restrict"),
        UNRESTRICT("unrestrict"),
        FAIL_ONBOARDING("fail_onboarding"),
        REJECT_ACTIVATION("reject_activation"),
        NONE("none"),
        ;

        private final String value;

        private EnforcementActionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
