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
 * Country
 *
 * <p>IP country, phone country, breach countries and policy_context.region together. When the breach
 * records give a country, the timezone and locale countries count too. The IP country of a proxy or
 * hosting network does not count.
 */
public class Country {

    public static final Country CONSISTENT = new Country("consistent");
    public static final Country MISMATCH = new Country("mismatch");
    public static final Country UNKNOWN = new Country("unknown");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Country> values = createValuesMap();
    private static final Map<String, CountryEnum> enums = createEnumsMap();

    private final String value;

    private Country(String value) {
        this.value = value;
    }

    /**
     * Returns a Country with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Country
     */
    @JsonCreator
    public static Country of(String value) {
        synchronized (Country.class) {
            return values.computeIfAbsent(value, v -> new Country(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<CountryEnum> asEnum() {
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
        Country other = (Country) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Country [value=" + value + "]";
    }

    // return an array just like an enum
    public static Country[] values() {
        synchronized (Country.class) {
            return values.values().toArray(new Country[] {});
        }
    }

    private static final Map<String, Country> createValuesMap() {
        Map<String, Country> map = new LinkedHashMap<>();
        map.put("consistent", CONSISTENT);
        map.put("mismatch", MISMATCH);
        map.put("unknown", UNKNOWN);
        return map;
    }

    private static final Map<String, CountryEnum> createEnumsMap() {
        Map<String, CountryEnum> map = new HashMap<>();
        map.put("consistent", CountryEnum.CONSISTENT);
        map.put("mismatch", CountryEnum.MISMATCH);
        map.put("unknown", CountryEnum.UNKNOWN);
        return map;
    }

    public enum CountryEnum {

        CONSISTENT("consistent"),
        MISMATCH("mismatch"),
        UNKNOWN("unknown"),
        ;

        private final String value;

        private CountryEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
