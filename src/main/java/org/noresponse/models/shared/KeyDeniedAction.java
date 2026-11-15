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
public class KeyDeniedAction {

    public static final KeyDeniedAction EVIDENCE_READ = new KeyDeniedAction("evidence.read");
    public static final KeyDeniedAction EXPORTS_READ = new KeyDeniedAction("exports.read");
    public static final KeyDeniedAction POLICY_PUBLISH = new KeyDeniedAction("policy.publish");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, KeyDeniedAction> values = createValuesMap();
    private static final Map<String, KeyDeniedActionEnum> enums = createEnumsMap();

    private final String value;

    private KeyDeniedAction(String value) {
        this.value = value;
    }

    /**
     * Returns a KeyDeniedAction with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as KeyDeniedAction
     */
    @JsonCreator
    public static KeyDeniedAction of(String value) {
        synchronized (KeyDeniedAction.class) {
            return values.computeIfAbsent(value, v -> new KeyDeniedAction(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<KeyDeniedActionEnum> asEnum() {
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
        KeyDeniedAction other = (KeyDeniedAction) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "KeyDeniedAction [value=" + value + "]";
    }

    // return an array just like an enum
    public static KeyDeniedAction[] values() {
        synchronized (KeyDeniedAction.class) {
            return values.values().toArray(new KeyDeniedAction[] {});
        }
    }

    private static final Map<String, KeyDeniedAction> createValuesMap() {
        Map<String, KeyDeniedAction> map = new LinkedHashMap<>();
        map.put("evidence.read", EVIDENCE_READ);
        map.put("exports.read", EXPORTS_READ);
        map.put("policy.publish", POLICY_PUBLISH);
        return map;
    }

    private static final Map<String, KeyDeniedActionEnum> createEnumsMap() {
        Map<String, KeyDeniedActionEnum> map = new HashMap<>();
        map.put("evidence.read", KeyDeniedActionEnum.EVIDENCE_READ);
        map.put("exports.read", KeyDeniedActionEnum.EXPORTS_READ);
        map.put("policy.publish", KeyDeniedActionEnum.POLICY_PUBLISH);
        return map;
    }

    public enum KeyDeniedActionEnum {

        EVIDENCE_READ("evidence.read"),
        EXPORTS_READ("exports.read"),
        POLICY_PUBLISH("policy.publish"),
        ;

        private final String value;

        private KeyDeniedActionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
