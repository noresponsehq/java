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
 * Lane
 *
 * <p>An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
 * email domain's registration) and ip.
 */
public class Lane {

    public static final Lane ACCOUNTS = new Lane("accounts");
    public static final Lane PHONE = new Lane("phone");
    public static final Lane BREACH = new Lane("breach");
    public static final Lane DOMAIN = new Lane("domain");
    public static final Lane IP = new Lane("ip");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Lane> values = createValuesMap();
    private static final Map<String, LaneEnum> enums = createEnumsMap();

    private final String value;

    private Lane(String value) {
        this.value = value;
    }

    /**
     * Returns a Lane with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Lane
     */
    @JsonCreator
    public static Lane of(String value) {
        synchronized (Lane.class) {
            return values.computeIfAbsent(value, v -> new Lane(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<LaneEnum> asEnum() {
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
        Lane other = (Lane) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Lane [value=" + value + "]";
    }

    // return an array just like an enum
    public static Lane[] values() {
        synchronized (Lane.class) {
            return values.values().toArray(new Lane[] {});
        }
    }

    private static final Map<String, Lane> createValuesMap() {
        Map<String, Lane> map = new LinkedHashMap<>();
        map.put("accounts", ACCOUNTS);
        map.put("phone", PHONE);
        map.put("breach", BREACH);
        map.put("domain", DOMAIN);
        map.put("ip", IP);
        return map;
    }

    private static final Map<String, LaneEnum> createEnumsMap() {
        Map<String, LaneEnum> map = new HashMap<>();
        map.put("accounts", LaneEnum.ACCOUNTS);
        map.put("phone", LaneEnum.PHONE);
        map.put("breach", LaneEnum.BREACH);
        map.put("domain", LaneEnum.DOMAIN);
        map.put("ip", LaneEnum.IP);
        return map;
    }

    public enum LaneEnum {

        ACCOUNTS("accounts"),
        PHONE("phone"),
        BREACH("breach"),
        DOMAIN("domain"),
        IP("ip"),
        ;

        private final String value;

        private LaneEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
