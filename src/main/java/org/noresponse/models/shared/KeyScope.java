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
public class KeyScope {

    public static final KeyScope ASSESS = new KeyScope("assess");
    public static final KeyScope HISTORY = new KeyScope("history");
    public static final KeyScope DELETE = new KeyScope("delete");
    public static final KeyScope WEBHOOKS = new KeyScope("webhooks");
    public static final KeyScope DETAILED = new KeyScope("detailed");
    public static final KeyScope INTEGRATIONS = new KeyScope("integrations");
    public static final KeyScope MANAGEMENT = new KeyScope("management");
    public static final KeyScope AUDIT = new KeyScope("audit");
    public static final KeyScope USAGE = new KeyScope("usage");
    public static final KeyScope INVESTIGATE = new KeyScope("investigate");
    public static final KeyScope EVIDENCE = new KeyScope("evidence");
    public static final KeyScope EXPORTS = new KeyScope("exports");
    public static final KeyScope AUDIT_MANAGE = new KeyScope("audit_manage");
    public static final KeyScope SETTINGS = new KeyScope("settings");
    public static final KeyScope POLICY_PUBLISH = new KeyScope("policy_publish");
    public static final KeyScope MEMBERS = new KeyScope("members");
    public static final KeyScope MEMBERS_MANAGE = new KeyScope("members_manage");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, KeyScope> values = createValuesMap();
    private static final Map<String, KeyScopeEnum> enums = createEnumsMap();

    private final String value;

    private KeyScope(String value) {
        this.value = value;
    }

    /**
     * Returns a KeyScope with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as KeyScope
     */
    @JsonCreator
    public static KeyScope of(String value) {
        synchronized (KeyScope.class) {
            return values.computeIfAbsent(value, v -> new KeyScope(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<KeyScopeEnum> asEnum() {
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
        KeyScope other = (KeyScope) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "KeyScope [value=" + value + "]";
    }

    // return an array just like an enum
    public static KeyScope[] values() {
        synchronized (KeyScope.class) {
            return values.values().toArray(new KeyScope[] {});
        }
    }

    private static final Map<String, KeyScope> createValuesMap() {
        Map<String, KeyScope> map = new LinkedHashMap<>();
        map.put("assess", ASSESS);
        map.put("history", HISTORY);
        map.put("delete", DELETE);
        map.put("webhooks", WEBHOOKS);
        map.put("detailed", DETAILED);
        map.put("integrations", INTEGRATIONS);
        map.put("management", MANAGEMENT);
        map.put("audit", AUDIT);
        map.put("usage", USAGE);
        map.put("investigate", INVESTIGATE);
        map.put("evidence", EVIDENCE);
        map.put("exports", EXPORTS);
        map.put("audit_manage", AUDIT_MANAGE);
        map.put("settings", SETTINGS);
        map.put("policy_publish", POLICY_PUBLISH);
        map.put("members", MEMBERS);
        map.put("members_manage", MEMBERS_MANAGE);
        return map;
    }

    private static final Map<String, KeyScopeEnum> createEnumsMap() {
        Map<String, KeyScopeEnum> map = new HashMap<>();
        map.put("assess", KeyScopeEnum.ASSESS);
        map.put("history", KeyScopeEnum.HISTORY);
        map.put("delete", KeyScopeEnum.DELETE);
        map.put("webhooks", KeyScopeEnum.WEBHOOKS);
        map.put("detailed", KeyScopeEnum.DETAILED);
        map.put("integrations", KeyScopeEnum.INTEGRATIONS);
        map.put("management", KeyScopeEnum.MANAGEMENT);
        map.put("audit", KeyScopeEnum.AUDIT);
        map.put("usage", KeyScopeEnum.USAGE);
        map.put("investigate", KeyScopeEnum.INVESTIGATE);
        map.put("evidence", KeyScopeEnum.EVIDENCE);
        map.put("exports", KeyScopeEnum.EXPORTS);
        map.put("audit_manage", KeyScopeEnum.AUDIT_MANAGE);
        map.put("settings", KeyScopeEnum.SETTINGS);
        map.put("policy_publish", KeyScopeEnum.POLICY_PUBLISH);
        map.put("members", KeyScopeEnum.MEMBERS);
        map.put("members_manage", KeyScopeEnum.MEMBERS_MANAGE);
        return map;
    }

    public enum KeyScopeEnum {

        ASSESS("assess"),
        HISTORY("history"),
        DELETE("delete"),
        WEBHOOKS("webhooks"),
        DETAILED("detailed"),
        INTEGRATIONS("integrations"),
        MANAGEMENT("management"),
        AUDIT("audit"),
        USAGE("usage"),
        INVESTIGATE("investigate"),
        EVIDENCE("evidence"),
        EXPORTS("exports"),
        AUDIT_MANAGE("audit_manage"),
        SETTINGS("settings"),
        POLICY_PUBLISH("policy_publish"),
        MEMBERS("members"),
        MEMBERS_MANAGE("members_manage"),
        ;

        private final String value;

        private KeyScopeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
