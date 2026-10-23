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
 * HookPoint
 *
 * <p>The provider event a provider_hook context was sent for; absent for any other transport.
 */
public class HookPoint {

    public static final HookPoint PRE_REGISTRATION = new HookPoint("pre_registration");
    public static final HookPoint POST_REGISTRATION = new HookPoint("post_registration");
    public static final HookPoint PRE_LOGIN = new HookPoint("pre_login");
    public static final HookPoint POST_LOGIN = new HookPoint("post_login");
    public static final HookPoint TOKEN_ISSUANCE = new HookPoint("token_issuance");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, HookPoint> values = createValuesMap();
    private static final Map<String, HookPointEnum> enums = createEnumsMap();

    private final String value;

    private HookPoint(String value) {
        this.value = value;
    }

    /**
     * Returns a HookPoint with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as HookPoint
     */
    @JsonCreator
    public static HookPoint of(String value) {
        synchronized (HookPoint.class) {
            return values.computeIfAbsent(value, v -> new HookPoint(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<HookPointEnum> asEnum() {
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
        HookPoint other = (HookPoint) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "HookPoint [value=" + value + "]";
    }

    // return an array just like an enum
    public static HookPoint[] values() {
        synchronized (HookPoint.class) {
            return values.values().toArray(new HookPoint[] {});
        }
    }

    private static final Map<String, HookPoint> createValuesMap() {
        Map<String, HookPoint> map = new LinkedHashMap<>();
        map.put("pre_registration", PRE_REGISTRATION);
        map.put("post_registration", POST_REGISTRATION);
        map.put("pre_login", PRE_LOGIN);
        map.put("post_login", POST_LOGIN);
        map.put("token_issuance", TOKEN_ISSUANCE);
        return map;
    }

    private static final Map<String, HookPointEnum> createEnumsMap() {
        Map<String, HookPointEnum> map = new HashMap<>();
        map.put("pre_registration", HookPointEnum.PRE_REGISTRATION);
        map.put("post_registration", HookPointEnum.POST_REGISTRATION);
        map.put("pre_login", HookPointEnum.PRE_LOGIN);
        map.put("post_login", HookPointEnum.POST_LOGIN);
        map.put("token_issuance", HookPointEnum.TOKEN_ISSUANCE);
        return map;
    }

    public enum HookPointEnum {

        PRE_REGISTRATION("pre_registration"),
        POST_REGISTRATION("post_registration"),
        PRE_LOGIN("pre_login"),
        POST_LOGIN("post_login"),
        TOKEN_ISSUANCE("token_issuance"),
        ;

        private final String value;

        private HookPointEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
