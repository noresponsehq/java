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
 * FootprintFamily
 *
 * <p>The source-neutral kind of evidence: account_presence (an account found on a site), auth_provider
 * (the account at your auth provider), breach (breach and credential tables), breach_abuse (abuse and
 * fraud communities' tables) and code_hosting (a code hosting profile).
 */
public class FootprintFamily {

    public static final FootprintFamily ACCOUNT_PRESENCE = new FootprintFamily("account_presence");
    public static final FootprintFamily AUTH_PROVIDER = new FootprintFamily("auth_provider");
    public static final FootprintFamily BREACH = new FootprintFamily("breach");
    public static final FootprintFamily BREACH_ABUSE = new FootprintFamily("breach_abuse");
    public static final FootprintFamily CODE_HOSTING = new FootprintFamily("code_hosting");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, FootprintFamily> values = createValuesMap();
    private static final Map<String, FootprintFamilyEnum> enums = createEnumsMap();

    private final String value;

    private FootprintFamily(String value) {
        this.value = value;
    }

    /**
     * Returns a FootprintFamily with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as FootprintFamily
     */
    @JsonCreator
    public static FootprintFamily of(String value) {
        synchronized (FootprintFamily.class) {
            return values.computeIfAbsent(value, v -> new FootprintFamily(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<FootprintFamilyEnum> asEnum() {
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
        FootprintFamily other = (FootprintFamily) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "FootprintFamily [value=" + value + "]";
    }

    // return an array just like an enum
    public static FootprintFamily[] values() {
        synchronized (FootprintFamily.class) {
            return values.values().toArray(new FootprintFamily[] {});
        }
    }

    private static final Map<String, FootprintFamily> createValuesMap() {
        Map<String, FootprintFamily> map = new LinkedHashMap<>();
        map.put("account_presence", ACCOUNT_PRESENCE);
        map.put("auth_provider", AUTH_PROVIDER);
        map.put("breach", BREACH);
        map.put("breach_abuse", BREACH_ABUSE);
        map.put("code_hosting", CODE_HOSTING);
        return map;
    }

    private static final Map<String, FootprintFamilyEnum> createEnumsMap() {
        Map<String, FootprintFamilyEnum> map = new HashMap<>();
        map.put("account_presence", FootprintFamilyEnum.ACCOUNT_PRESENCE);
        map.put("auth_provider", FootprintFamilyEnum.AUTH_PROVIDER);
        map.put("breach", FootprintFamilyEnum.BREACH);
        map.put("breach_abuse", FootprintFamilyEnum.BREACH_ABUSE);
        map.put("code_hosting", FootprintFamilyEnum.CODE_HOSTING);
        return map;
    }

    public enum FootprintFamilyEnum {

        ACCOUNT_PRESENCE("account_presence"),
        AUTH_PROVIDER("auth_provider"),
        BREACH("breach"),
        BREACH_ABUSE("breach_abuse"),
        CODE_HOSTING("code_hosting"),
        ;

        private final String value;

        private FootprintFamilyEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
