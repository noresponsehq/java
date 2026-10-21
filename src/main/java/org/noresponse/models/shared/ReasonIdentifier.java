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
 * ReasonIdentifier
 *
 * <p>The submitted identifier or client context field a reason concerns; absent when it concerns the
 * whole assessment. Client context is a claimed hint, not a device fingerprint.
 */
public class ReasonIdentifier {

    public static final ReasonIdentifier EMAIL = new ReasonIdentifier("email");
    public static final ReasonIdentifier USERNAME = new ReasonIdentifier("username");
    public static final ReasonIdentifier PHONE = new ReasonIdentifier("phone");
    public static final ReasonIdentifier NAME = new ReasonIdentifier("name");
    public static final ReasonIdentifier IP = new ReasonIdentifier("ip");
    public static final ReasonIdentifier CONTEXT = new ReasonIdentifier("context");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ReasonIdentifier> values = createValuesMap();
    private static final Map<String, ReasonIdentifierEnum> enums = createEnumsMap();

    private final String value;

    private ReasonIdentifier(String value) {
        this.value = value;
    }

    /**
     * Returns a ReasonIdentifier with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ReasonIdentifier
     */
    @JsonCreator
    public static ReasonIdentifier of(String value) {
        synchronized (ReasonIdentifier.class) {
            return values.computeIfAbsent(value, v -> new ReasonIdentifier(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ReasonIdentifierEnum> asEnum() {
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
        ReasonIdentifier other = (ReasonIdentifier) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ReasonIdentifier [value=" + value + "]";
    }

    // return an array just like an enum
    public static ReasonIdentifier[] values() {
        synchronized (ReasonIdentifier.class) {
            return values.values().toArray(new ReasonIdentifier[] {});
        }
    }

    private static final Map<String, ReasonIdentifier> createValuesMap() {
        Map<String, ReasonIdentifier> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("username", USERNAME);
        map.put("phone", PHONE);
        map.put("name", NAME);
        map.put("ip", IP);
        map.put("context", CONTEXT);
        return map;
    }

    private static final Map<String, ReasonIdentifierEnum> createEnumsMap() {
        Map<String, ReasonIdentifierEnum> map = new HashMap<>();
        map.put("email", ReasonIdentifierEnum.EMAIL);
        map.put("username", ReasonIdentifierEnum.USERNAME);
        map.put("phone", ReasonIdentifierEnum.PHONE);
        map.put("name", ReasonIdentifierEnum.NAME);
        map.put("ip", ReasonIdentifierEnum.IP);
        map.put("context", ReasonIdentifierEnum.CONTEXT);
        return map;
    }

    public enum ReasonIdentifierEnum {

        EMAIL("email"),
        USERNAME("username"),
        PHONE("phone"),
        NAME("name"),
        IP("ip"),
        CONTEXT("context"),
        ;

        private final String value;

        private ReasonIdentifierEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
