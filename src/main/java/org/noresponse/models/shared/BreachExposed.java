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
 * BreachExposed
 *
 * <p>The kind of data a breach table held beside the identifier. password means a plaintext password was
 * present and password_hash means a hash, salt or token was present. The value itself is not stored or
 * returned; the schema has no member for one.
 */
public class BreachExposed {

    public static final BreachExposed EMAIL = new BreachExposed("email");
    public static final BreachExposed USERNAME = new BreachExposed("username");
    public static final BreachExposed NAME = new BreachExposed("name");
    public static final BreachExposed PASSWORD = new BreachExposed("password");
    public static final BreachExposed PASSWORD_HASH = new BreachExposed("password_hash");
    public static final BreachExposed PHONE = new BreachExposed("phone");
    public static final BreachExposed ADDRESS = new BreachExposed("address");
    public static final BreachExposed DOB = new BreachExposed("dob");
    public static final BreachExposed IP = new BreachExposed("ip");
    public static final BreachExposed PAYMENT = new BreachExposed("payment");
    public static final BreachExposed SECURITY_QA = new BreachExposed("security_qa");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, BreachExposed> values = createValuesMap();
    private static final Map<String, BreachExposedEnum> enums = createEnumsMap();

    private final String value;

    private BreachExposed(String value) {
        this.value = value;
    }

    /**
     * Returns a BreachExposed with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as BreachExposed
     */
    @JsonCreator
    public static BreachExposed of(String value) {
        synchronized (BreachExposed.class) {
            return values.computeIfAbsent(value, v -> new BreachExposed(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<BreachExposedEnum> asEnum() {
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
        BreachExposed other = (BreachExposed) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "BreachExposed [value=" + value + "]";
    }

    // return an array just like an enum
    public static BreachExposed[] values() {
        synchronized (BreachExposed.class) {
            return values.values().toArray(new BreachExposed[] {});
        }
    }

    private static final Map<String, BreachExposed> createValuesMap() {
        Map<String, BreachExposed> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("username", USERNAME);
        map.put("name", NAME);
        map.put("password", PASSWORD);
        map.put("password_hash", PASSWORD_HASH);
        map.put("phone", PHONE);
        map.put("address", ADDRESS);
        map.put("dob", DOB);
        map.put("ip", IP);
        map.put("payment", PAYMENT);
        map.put("security_qa", SECURITY_QA);
        return map;
    }

    private static final Map<String, BreachExposedEnum> createEnumsMap() {
        Map<String, BreachExposedEnum> map = new HashMap<>();
        map.put("email", BreachExposedEnum.EMAIL);
        map.put("username", BreachExposedEnum.USERNAME);
        map.put("name", BreachExposedEnum.NAME);
        map.put("password", BreachExposedEnum.PASSWORD);
        map.put("password_hash", BreachExposedEnum.PASSWORD_HASH);
        map.put("phone", BreachExposedEnum.PHONE);
        map.put("address", BreachExposedEnum.ADDRESS);
        map.put("dob", BreachExposedEnum.DOB);
        map.put("ip", BreachExposedEnum.IP);
        map.put("payment", BreachExposedEnum.PAYMENT);
        map.put("security_qa", BreachExposedEnum.SECURITY_QA);
        return map;
    }

    public enum BreachExposedEnum {

        EMAIL("email"),
        USERNAME("username"),
        NAME("name"),
        PASSWORD("password"),
        PASSWORD_HASH("password_hash"),
        PHONE("phone"),
        ADDRESS("address"),
        DOB("dob"),
        IP("ip"),
        PAYMENT("payment"),
        SECURITY_QA("security_qa"),
        ;

        private final String value;

        private BreachExposedEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
