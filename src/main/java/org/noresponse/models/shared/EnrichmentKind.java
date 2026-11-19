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
public class EnrichmentKind {

    public static final EnrichmentKind USERNAME = new EnrichmentKind("username");
    public static final EnrichmentKind EMAIL = new EnrichmentKind("email");
    public static final EnrichmentKind PHONE = new EnrichmentKind("phone");
    public static final EnrichmentKind PERSON_NAME = new EnrichmentKind("person_name");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, EnrichmentKind> values = createValuesMap();
    private static final Map<String, EnrichmentKindEnum> enums = createEnumsMap();

    private final String value;

    private EnrichmentKind(String value) {
        this.value = value;
    }

    /**
     * Returns a EnrichmentKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as EnrichmentKind
     */
    @JsonCreator
    public static EnrichmentKind of(String value) {
        synchronized (EnrichmentKind.class) {
            return values.computeIfAbsent(value, v -> new EnrichmentKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EnrichmentKindEnum> asEnum() {
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
        EnrichmentKind other = (EnrichmentKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "EnrichmentKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static EnrichmentKind[] values() {
        synchronized (EnrichmentKind.class) {
            return values.values().toArray(new EnrichmentKind[] {});
        }
    }

    private static final Map<String, EnrichmentKind> createValuesMap() {
        Map<String, EnrichmentKind> map = new LinkedHashMap<>();
        map.put("username", USERNAME);
        map.put("email", EMAIL);
        map.put("phone", PHONE);
        map.put("person_name", PERSON_NAME);
        return map;
    }

    private static final Map<String, EnrichmentKindEnum> createEnumsMap() {
        Map<String, EnrichmentKindEnum> map = new HashMap<>();
        map.put("username", EnrichmentKindEnum.USERNAME);
        map.put("email", EnrichmentKindEnum.EMAIL);
        map.put("phone", EnrichmentKindEnum.PHONE);
        map.put("person_name", EnrichmentKindEnum.PERSON_NAME);
        return map;
    }

    public enum EnrichmentKindEnum {

        USERNAME("username"),
        EMAIL("email"),
        PHONE("phone"),
        PERSON_NAME("person_name"),
        ;

        private final String value;

        private EnrichmentKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
