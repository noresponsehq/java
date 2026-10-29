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
public class MemberDeniedAction {

    public static final MemberDeniedAction EVIDENCE_READ = new MemberDeniedAction("evidence.read");
    public static final MemberDeniedAction EXPORTS_READ = new MemberDeniedAction("exports.read");
    public static final MemberDeniedAction POLICY_PUBLISH = new MemberDeniedAction("policy.publish");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, MemberDeniedAction> values = createValuesMap();
    private static final Map<String, MemberDeniedActionEnum> enums = createEnumsMap();

    private final String value;

    private MemberDeniedAction(String value) {
        this.value = value;
    }

    /**
     * Returns a MemberDeniedAction with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as MemberDeniedAction
     */
    @JsonCreator
    public static MemberDeniedAction of(String value) {
        synchronized (MemberDeniedAction.class) {
            return values.computeIfAbsent(value, v -> new MemberDeniedAction(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MemberDeniedActionEnum> asEnum() {
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
        MemberDeniedAction other = (MemberDeniedAction) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "MemberDeniedAction [value=" + value + "]";
    }

    // return an array just like an enum
    public static MemberDeniedAction[] values() {
        synchronized (MemberDeniedAction.class) {
            return values.values().toArray(new MemberDeniedAction[] {});
        }
    }

    private static final Map<String, MemberDeniedAction> createValuesMap() {
        Map<String, MemberDeniedAction> map = new LinkedHashMap<>();
        map.put("evidence.read", EVIDENCE_READ);
        map.put("exports.read", EXPORTS_READ);
        map.put("policy.publish", POLICY_PUBLISH);
        return map;
    }

    private static final Map<String, MemberDeniedActionEnum> createEnumsMap() {
        Map<String, MemberDeniedActionEnum> map = new HashMap<>();
        map.put("evidence.read", MemberDeniedActionEnum.EVIDENCE_READ);
        map.put("exports.read", MemberDeniedActionEnum.EXPORTS_READ);
        map.put("policy.publish", MemberDeniedActionEnum.POLICY_PUBLISH);
        return map;
    }

    public enum MemberDeniedActionEnum {

        EVIDENCE_READ("evidence.read"),
        EXPORTS_READ("exports.read"),
        POLICY_PUBLISH("policy.publish"),
        ;

        private final String value;

        private MemberDeniedActionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
