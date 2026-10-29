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
 * AuditEventTargetType
 *
 * <p>The kind of resource the change acted on.
 */
public class AuditEventTargetType {

    public static final AuditEventTargetType WORKSPACE = new AuditEventTargetType("workspace");
    public static final AuditEventTargetType MEMBER = new AuditEventTargetType("member");
    public static final AuditEventTargetType INVITE = new AuditEventTargetType("invite");
    public static final AuditEventTargetType KEY = new AuditEventTargetType("key");
    public static final AuditEventTargetType SETTING = new AuditEventTargetType("setting");
    public static final AuditEventTargetType WEBHOOK_ENDPOINT = new AuditEventTargetType("webhook_endpoint");
    public static final AuditEventTargetType INTEGRATION = new AuditEventTargetType("integration");
    public static final AuditEventTargetType BILLING = new AuditEventTargetType("billing");
    public static final AuditEventTargetType REVIEW = new AuditEventTargetType("review");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AuditEventTargetType> values = createValuesMap();
    private static final Map<String, AuditEventTargetTypeEnum> enums = createEnumsMap();

    private final String value;

    private AuditEventTargetType(String value) {
        this.value = value;
    }

    /**
     * Returns a AuditEventTargetType with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AuditEventTargetType
     */
    @JsonCreator
    public static AuditEventTargetType of(String value) {
        synchronized (AuditEventTargetType.class) {
            return values.computeIfAbsent(value, v -> new AuditEventTargetType(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AuditEventTargetTypeEnum> asEnum() {
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
        AuditEventTargetType other = (AuditEventTargetType) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AuditEventTargetType [value=" + value + "]";
    }

    // return an array just like an enum
    public static AuditEventTargetType[] values() {
        synchronized (AuditEventTargetType.class) {
            return values.values().toArray(new AuditEventTargetType[] {});
        }
    }

    private static final Map<String, AuditEventTargetType> createValuesMap() {
        Map<String, AuditEventTargetType> map = new LinkedHashMap<>();
        map.put("workspace", WORKSPACE);
        map.put("member", MEMBER);
        map.put("invite", INVITE);
        map.put("key", KEY);
        map.put("setting", SETTING);
        map.put("webhook_endpoint", WEBHOOK_ENDPOINT);
        map.put("integration", INTEGRATION);
        map.put("billing", BILLING);
        map.put("review", REVIEW);
        return map;
    }

    private static final Map<String, AuditEventTargetTypeEnum> createEnumsMap() {
        Map<String, AuditEventTargetTypeEnum> map = new HashMap<>();
        map.put("workspace", AuditEventTargetTypeEnum.WORKSPACE);
        map.put("member", AuditEventTargetTypeEnum.MEMBER);
        map.put("invite", AuditEventTargetTypeEnum.INVITE);
        map.put("key", AuditEventTargetTypeEnum.KEY);
        map.put("setting", AuditEventTargetTypeEnum.SETTING);
        map.put("webhook_endpoint", AuditEventTargetTypeEnum.WEBHOOK_ENDPOINT);
        map.put("integration", AuditEventTargetTypeEnum.INTEGRATION);
        map.put("billing", AuditEventTargetTypeEnum.BILLING);
        map.put("review", AuditEventTargetTypeEnum.REVIEW);
        return map;
    }

    public enum AuditEventTargetTypeEnum {

        WORKSPACE("workspace"),
        MEMBER("member"),
        INVITE("invite"),
        KEY("key"),
        SETTING("setting"),
        WEBHOOK_ENDPOINT("webhook_endpoint"),
        INTEGRATION("integration"),
        BILLING("billing"),
        REVIEW("review"),
        ;

        private final String value;

        private AuditEventTargetTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
