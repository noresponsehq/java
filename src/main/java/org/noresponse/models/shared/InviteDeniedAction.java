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
public class InviteDeniedAction {

    public static final InviteDeniedAction EVIDENCE_READ = new InviteDeniedAction("evidence.read");
    public static final InviteDeniedAction EXPORTS_READ = new InviteDeniedAction("exports.read");
    public static final InviteDeniedAction POLICY_PUBLISH = new InviteDeniedAction("policy.publish");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, InviteDeniedAction> values = createValuesMap();
    private static final Map<String, InviteDeniedActionEnum> enums = createEnumsMap();

    private final String value;

    private InviteDeniedAction(String value) {
        this.value = value;
    }

    /**
     * Returns a InviteDeniedAction with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as InviteDeniedAction
     */
    @JsonCreator
    public static InviteDeniedAction of(String value) {
        synchronized (InviteDeniedAction.class) {
            return values.computeIfAbsent(value, v -> new InviteDeniedAction(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<InviteDeniedActionEnum> asEnum() {
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
        InviteDeniedAction other = (InviteDeniedAction) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "InviteDeniedAction [value=" + value + "]";
    }

    // return an array just like an enum
    public static InviteDeniedAction[] values() {
        synchronized (InviteDeniedAction.class) {
            return values.values().toArray(new InviteDeniedAction[] {});
        }
    }

    private static final Map<String, InviteDeniedAction> createValuesMap() {
        Map<String, InviteDeniedAction> map = new LinkedHashMap<>();
        map.put("evidence.read", EVIDENCE_READ);
        map.put("exports.read", EXPORTS_READ);
        map.put("policy.publish", POLICY_PUBLISH);
        return map;
    }

    private static final Map<String, InviteDeniedActionEnum> createEnumsMap() {
        Map<String, InviteDeniedActionEnum> map = new HashMap<>();
        map.put("evidence.read", InviteDeniedActionEnum.EVIDENCE_READ);
        map.put("exports.read", InviteDeniedActionEnum.EXPORTS_READ);
        map.put("policy.publish", InviteDeniedActionEnum.POLICY_PUBLISH);
        return map;
    }

    public enum InviteDeniedActionEnum {

        EVIDENCE_READ("evidence.read"),
        EXPORTS_READ("exports.read"),
        POLICY_PUBLISH("policy.publish"),
        ;

        private final String value;

        private InviteDeniedActionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
