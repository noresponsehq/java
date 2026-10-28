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
 * ConflictErrorCode
 *
 * <p>Machine codes an HTTP 409 answer can carry.
 */
public class ConflictErrorCode {

    public static final ConflictErrorCode IDEMPOTENCY_KEY_REUSED = new ConflictErrorCode("idempotency_key_reused");
    public static final ConflictErrorCode KEY_ROTATION_IN_PROGRESS = new ConflictErrorCode("key_rotation_in_progress");
    public static final ConflictErrorCode WEBHOOK_ENDPOINT_LIMIT_REACHED =
            new ConflictErrorCode("webhook_endpoint_limit_reached");
    public static final ConflictErrorCode WEBHOOK_ENDPOINT_UNVERIFIED =
            new ConflictErrorCode("webhook_endpoint_unverified");
    public static final ConflictErrorCode TOO_MANY_INTEGRATIONS = new ConflictErrorCode("too_many_integrations");
    public static final ConflictErrorCode RISK_VERSION_AHEAD = new ConflictErrorCode("risk_version_ahead");
    public static final ConflictErrorCode KEY_LIMIT_REACHED = new ConflictErrorCode("key_limit_reached");
    public static final ConflictErrorCode SELF_REVOCATION = new ConflictErrorCode("self_revocation");
    public static final ConflictErrorCode OUTCOME_CONFLICT = new ConflictErrorCode("outcome_conflict");
    public static final ConflictErrorCode CREDENTIAL_CONFLICT = new ConflictErrorCode("credential_conflict");
    public static final ConflictErrorCode BINDING_CONFLICT = new ConflictErrorCode("binding_conflict");
    public static final ConflictErrorCode LIST_ENTRY_EXISTS = new ConflictErrorCode("list_entry_exists");
    public static final ConflictErrorCode LIST_LIMIT_REACHED = new ConflictErrorCode("list_limit_reached");
    public static final ConflictErrorCode LIST_ENTRY_TOO_BROAD = new ConflictErrorCode("list_entry_too_broad");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ConflictErrorCode> values = createValuesMap();
    private static final Map<String, ConflictErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private ConflictErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a ConflictErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ConflictErrorCode
     */
    @JsonCreator
    public static ConflictErrorCode of(String value) {
        synchronized (ConflictErrorCode.class) {
            return values.computeIfAbsent(value, v -> new ConflictErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ConflictErrorCodeEnum> asEnum() {
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
        ConflictErrorCode other = (ConflictErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ConflictErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static ConflictErrorCode[] values() {
        synchronized (ConflictErrorCode.class) {
            return values.values().toArray(new ConflictErrorCode[] {});
        }
    }

    private static final Map<String, ConflictErrorCode> createValuesMap() {
        Map<String, ConflictErrorCode> map = new LinkedHashMap<>();
        map.put("idempotency_key_reused", IDEMPOTENCY_KEY_REUSED);
        map.put("key_rotation_in_progress", KEY_ROTATION_IN_PROGRESS);
        map.put("webhook_endpoint_limit_reached", WEBHOOK_ENDPOINT_LIMIT_REACHED);
        map.put("webhook_endpoint_unverified", WEBHOOK_ENDPOINT_UNVERIFIED);
        map.put("too_many_integrations", TOO_MANY_INTEGRATIONS);
        map.put("risk_version_ahead", RISK_VERSION_AHEAD);
        map.put("key_limit_reached", KEY_LIMIT_REACHED);
        map.put("self_revocation", SELF_REVOCATION);
        map.put("outcome_conflict", OUTCOME_CONFLICT);
        map.put("credential_conflict", CREDENTIAL_CONFLICT);
        map.put("binding_conflict", BINDING_CONFLICT);
        map.put("list_entry_exists", LIST_ENTRY_EXISTS);
        map.put("list_limit_reached", LIST_LIMIT_REACHED);
        map.put("list_entry_too_broad", LIST_ENTRY_TOO_BROAD);
        return map;
    }

    private static final Map<String, ConflictErrorCodeEnum> createEnumsMap() {
        Map<String, ConflictErrorCodeEnum> map = new HashMap<>();
        map.put("idempotency_key_reused", ConflictErrorCodeEnum.IDEMPOTENCY_KEY_REUSED);
        map.put("key_rotation_in_progress", ConflictErrorCodeEnum.KEY_ROTATION_IN_PROGRESS);
        map.put("webhook_endpoint_limit_reached", ConflictErrorCodeEnum.WEBHOOK_ENDPOINT_LIMIT_REACHED);
        map.put("webhook_endpoint_unverified", ConflictErrorCodeEnum.WEBHOOK_ENDPOINT_UNVERIFIED);
        map.put("too_many_integrations", ConflictErrorCodeEnum.TOO_MANY_INTEGRATIONS);
        map.put("risk_version_ahead", ConflictErrorCodeEnum.RISK_VERSION_AHEAD);
        map.put("key_limit_reached", ConflictErrorCodeEnum.KEY_LIMIT_REACHED);
        map.put("self_revocation", ConflictErrorCodeEnum.SELF_REVOCATION);
        map.put("outcome_conflict", ConflictErrorCodeEnum.OUTCOME_CONFLICT);
        map.put("credential_conflict", ConflictErrorCodeEnum.CREDENTIAL_CONFLICT);
        map.put("binding_conflict", ConflictErrorCodeEnum.BINDING_CONFLICT);
        map.put("list_entry_exists", ConflictErrorCodeEnum.LIST_ENTRY_EXISTS);
        map.put("list_limit_reached", ConflictErrorCodeEnum.LIST_LIMIT_REACHED);
        map.put("list_entry_too_broad", ConflictErrorCodeEnum.LIST_ENTRY_TOO_BROAD);
        return map;
    }

    public enum ConflictErrorCodeEnum {

        IDEMPOTENCY_KEY_REUSED("idempotency_key_reused"),
        KEY_ROTATION_IN_PROGRESS("key_rotation_in_progress"),
        WEBHOOK_ENDPOINT_LIMIT_REACHED("webhook_endpoint_limit_reached"),
        WEBHOOK_ENDPOINT_UNVERIFIED("webhook_endpoint_unverified"),
        TOO_MANY_INTEGRATIONS("too_many_integrations"),
        RISK_VERSION_AHEAD("risk_version_ahead"),
        KEY_LIMIT_REACHED("key_limit_reached"),
        SELF_REVOCATION("self_revocation"),
        OUTCOME_CONFLICT("outcome_conflict"),
        CREDENTIAL_CONFLICT("credential_conflict"),
        BINDING_CONFLICT("binding_conflict"),
        LIST_ENTRY_EXISTS("list_entry_exists"),
        LIST_LIMIT_REACHED("list_limit_reached"),
        LIST_ENTRY_TOO_BROAD("list_entry_too_broad"),
        ;

        private final String value;

        private ConflictErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
