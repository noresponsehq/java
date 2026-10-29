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
public class AccountOutcomeActorType {

    public static final AccountOutcomeActorType API_KEY = new AccountOutcomeActorType("api_key");
    public static final AccountOutcomeActorType USER = new AccountOutcomeActorType("user");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AccountOutcomeActorType> values = createValuesMap();
    private static final Map<String, AccountOutcomeActorTypeEnum> enums = createEnumsMap();

    private final String value;

    private AccountOutcomeActorType(String value) {
        this.value = value;
    }

    /**
     * Returns a AccountOutcomeActorType with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AccountOutcomeActorType
     */
    @JsonCreator
    public static AccountOutcomeActorType of(String value) {
        synchronized (AccountOutcomeActorType.class) {
            return values.computeIfAbsent(value, v -> new AccountOutcomeActorType(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AccountOutcomeActorTypeEnum> asEnum() {
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
        AccountOutcomeActorType other = (AccountOutcomeActorType) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AccountOutcomeActorType [value=" + value + "]";
    }

    // return an array just like an enum
    public static AccountOutcomeActorType[] values() {
        synchronized (AccountOutcomeActorType.class) {
            return values.values().toArray(new AccountOutcomeActorType[] {});
        }
    }

    private static final Map<String, AccountOutcomeActorType> createValuesMap() {
        Map<String, AccountOutcomeActorType> map = new LinkedHashMap<>();
        map.put("api_key", API_KEY);
        map.put("user", USER);
        return map;
    }

    private static final Map<String, AccountOutcomeActorTypeEnum> createEnumsMap() {
        Map<String, AccountOutcomeActorTypeEnum> map = new HashMap<>();
        map.put("api_key", AccountOutcomeActorTypeEnum.API_KEY);
        map.put("user", AccountOutcomeActorTypeEnum.USER);
        return map;
    }

    public enum AccountOutcomeActorTypeEnum {

        API_KEY("api_key"),
        USER("user"),
        ;

        private final String value;

        private AccountOutcomeActorTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
