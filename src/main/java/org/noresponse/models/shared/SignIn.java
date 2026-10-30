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
public class SignIn {

    public static final SignIn PASSWORD = new SignIn("password");
    public static final SignIn GOOGLE = new SignIn("google");
    public static final SignIn FACEBOOK = new SignIn("facebook");
    public static final SignIn APPLE = new SignIn("apple");
    public static final SignIn MICROSOFT = new SignIn("microsoft");
    public static final SignIn SSO = new SignIn("sso");
    public static final SignIn OTHER = new SignIn("other");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, SignIn> values = createValuesMap();
    private static final Map<String, SignInEnum> enums = createEnumsMap();

    private final String value;

    private SignIn(String value) {
        this.value = value;
    }

    /**
     * Returns a SignIn with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as SignIn
     */
    @JsonCreator
    public static SignIn of(String value) {
        synchronized (SignIn.class) {
            return values.computeIfAbsent(value, v -> new SignIn(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SignInEnum> asEnum() {
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
        SignIn other = (SignIn) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "SignIn [value=" + value + "]";
    }

    // return an array just like an enum
    public static SignIn[] values() {
        synchronized (SignIn.class) {
            return values.values().toArray(new SignIn[] {});
        }
    }

    private static final Map<String, SignIn> createValuesMap() {
        Map<String, SignIn> map = new LinkedHashMap<>();
        map.put("password", PASSWORD);
        map.put("google", GOOGLE);
        map.put("facebook", FACEBOOK);
        map.put("apple", APPLE);
        map.put("microsoft", MICROSOFT);
        map.put("sso", SSO);
        map.put("other", OTHER);
        return map;
    }

    private static final Map<String, SignInEnum> createEnumsMap() {
        Map<String, SignInEnum> map = new HashMap<>();
        map.put("password", SignInEnum.PASSWORD);
        map.put("google", SignInEnum.GOOGLE);
        map.put("facebook", SignInEnum.FACEBOOK);
        map.put("apple", SignInEnum.APPLE);
        map.put("microsoft", SignInEnum.MICROSOFT);
        map.put("sso", SignInEnum.SSO);
        map.put("other", SignInEnum.OTHER);
        return map;
    }

    public enum SignInEnum {

        PASSWORD("password"),
        GOOGLE("google"),
        FACEBOOK("facebook"),
        APPLE("apple"),
        MICROSOFT("microsoft"),
        SSO("sso"),
        OTHER("other"),
        ;

        private final String value;

        private SignInEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
