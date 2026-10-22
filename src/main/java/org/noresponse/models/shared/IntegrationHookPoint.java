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
 * IntegrationHookPoint
 *
 * <p>pre_registration runs before the user exists; post_login runs after a successful sign-in.
 */
public class IntegrationHookPoint {

    public static final IntegrationHookPoint PRE_REGISTRATION = new IntegrationHookPoint("pre_registration");
    public static final IntegrationHookPoint POST_LOGIN = new IntegrationHookPoint("post_login");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, IntegrationHookPoint> values = createValuesMap();
    private static final Map<String, IntegrationHookPointEnum> enums = createEnumsMap();

    private final String value;

    private IntegrationHookPoint(String value) {
        this.value = value;
    }

    /**
     * Returns a IntegrationHookPoint with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as IntegrationHookPoint
     */
    @JsonCreator
    public static IntegrationHookPoint of(String value) {
        synchronized (IntegrationHookPoint.class) {
            return values.computeIfAbsent(value, v -> new IntegrationHookPoint(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<IntegrationHookPointEnum> asEnum() {
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
        IntegrationHookPoint other = (IntegrationHookPoint) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "IntegrationHookPoint [value=" + value + "]";
    }

    // return an array just like an enum
    public static IntegrationHookPoint[] values() {
        synchronized (IntegrationHookPoint.class) {
            return values.values().toArray(new IntegrationHookPoint[] {});
        }
    }

    private static final Map<String, IntegrationHookPoint> createValuesMap() {
        Map<String, IntegrationHookPoint> map = new LinkedHashMap<>();
        map.put("pre_registration", PRE_REGISTRATION);
        map.put("post_login", POST_LOGIN);
        return map;
    }

    private static final Map<String, IntegrationHookPointEnum> createEnumsMap() {
        Map<String, IntegrationHookPointEnum> map = new HashMap<>();
        map.put("pre_registration", IntegrationHookPointEnum.PRE_REGISTRATION);
        map.put("post_login", IntegrationHookPointEnum.POST_LOGIN);
        return map;
    }

    public enum IntegrationHookPointEnum {

        PRE_REGISTRATION("pre_registration"),
        POST_LOGIN("post_login"),
        ;

        private final String value;

        private IntegrationHookPointEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
