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
 * Attribute
 *
 * <p>name_gender is the records' gender against the submitted first name's usual one.
 */
public class Attribute {

    public static final Attribute BIRTH_DATE = new Attribute("birth_date");
    public static final Attribute GENDER = new Attribute("gender");
    public static final Attribute NATIONALITY = new Attribute("nationality");
    public static final Attribute NAME_GENDER = new Attribute("name_gender");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Attribute> values = createValuesMap();
    private static final Map<String, AttributeEnum> enums = createEnumsMap();

    private final String value;

    private Attribute(String value) {
        this.value = value;
    }

    /**
     * Returns a Attribute with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Attribute
     */
    @JsonCreator
    public static Attribute of(String value) {
        synchronized (Attribute.class) {
            return values.computeIfAbsent(value, v -> new Attribute(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AttributeEnum> asEnum() {
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
        Attribute other = (Attribute) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Attribute [value=" + value + "]";
    }

    // return an array just like an enum
    public static Attribute[] values() {
        synchronized (Attribute.class) {
            return values.values().toArray(new Attribute[] {});
        }
    }

    private static final Map<String, Attribute> createValuesMap() {
        Map<String, Attribute> map = new LinkedHashMap<>();
        map.put("birth_date", BIRTH_DATE);
        map.put("gender", GENDER);
        map.put("nationality", NATIONALITY);
        map.put("name_gender", NAME_GENDER);
        return map;
    }

    private static final Map<String, AttributeEnum> createEnumsMap() {
        Map<String, AttributeEnum> map = new HashMap<>();
        map.put("birth_date", AttributeEnum.BIRTH_DATE);
        map.put("gender", AttributeEnum.GENDER);
        map.put("nationality", AttributeEnum.NATIONALITY);
        map.put("name_gender", AttributeEnum.NAME_GENDER);
        return map;
    }

    public enum AttributeEnum {

        BIRTH_DATE("birth_date"),
        GENDER("gender"),
        NATIONALITY("nationality"),
        NAME_GENDER("name_gender"),
        ;

        private final String value;

        private AttributeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
