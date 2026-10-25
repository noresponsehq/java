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
 * KeyStatus
 *
 * <p>active: the key authenticates. expired: a rotation's overlap window has ended (expires_at). revoked:
 * the key was revoked (revoked_at) and answers 401.
 */
public class KeyStatus {

    public static final KeyStatus ACTIVE = new KeyStatus("active");
    public static final KeyStatus EXPIRED = new KeyStatus("expired");
    public static final KeyStatus REVOKED = new KeyStatus("revoked");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, KeyStatus> values = createValuesMap();
    private static final Map<String, KeyStatusEnum> enums = createEnumsMap();

    private final String value;

    private KeyStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a KeyStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as KeyStatus
     */
    @JsonCreator
    public static KeyStatus of(String value) {
        synchronized (KeyStatus.class) {
            return values.computeIfAbsent(value, v -> new KeyStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<KeyStatusEnum> asEnum() {
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
        KeyStatus other = (KeyStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "KeyStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static KeyStatus[] values() {
        synchronized (KeyStatus.class) {
            return values.values().toArray(new KeyStatus[] {});
        }
    }

    private static final Map<String, KeyStatus> createValuesMap() {
        Map<String, KeyStatus> map = new LinkedHashMap<>();
        map.put("active", ACTIVE);
        map.put("expired", EXPIRED);
        map.put("revoked", REVOKED);
        return map;
    }

    private static final Map<String, KeyStatusEnum> createEnumsMap() {
        Map<String, KeyStatusEnum> map = new HashMap<>();
        map.put("active", KeyStatusEnum.ACTIVE);
        map.put("expired", KeyStatusEnum.EXPIRED);
        map.put("revoked", KeyStatusEnum.REVOKED);
        return map;
    }

    public enum KeyStatusEnum {

        ACTIVE("active"),
        EXPIRED("expired"),
        REVOKED("revoked"),
        ;

        private final String value;

        private KeyStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
