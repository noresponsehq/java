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
public class ListImpactKind {

    public static final ListImpactKind EMAIL = new ListImpactKind("email");
    public static final ListImpactKind EMAIL_DOMAIN = new ListImpactKind("email_domain");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ListImpactKind> values = createValuesMap();
    private static final Map<String, ListImpactKindEnum> enums = createEnumsMap();

    private final String value;

    private ListImpactKind(String value) {
        this.value = value;
    }

    /**
     * Returns a ListImpactKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ListImpactKind
     */
    @JsonCreator
    public static ListImpactKind of(String value) {
        synchronized (ListImpactKind.class) {
            return values.computeIfAbsent(value, v -> new ListImpactKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ListImpactKindEnum> asEnum() {
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
        ListImpactKind other = (ListImpactKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ListImpactKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static ListImpactKind[] values() {
        synchronized (ListImpactKind.class) {
            return values.values().toArray(new ListImpactKind[] {});
        }
    }

    private static final Map<String, ListImpactKind> createValuesMap() {
        Map<String, ListImpactKind> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("email_domain", EMAIL_DOMAIN);
        return map;
    }

    private static final Map<String, ListImpactKindEnum> createEnumsMap() {
        Map<String, ListImpactKindEnum> map = new HashMap<>();
        map.put("email", ListImpactKindEnum.EMAIL);
        map.put("email_domain", ListImpactKindEnum.EMAIL_DOMAIN);
        return map;
    }

    public enum ListImpactKindEnum {

        EMAIL("email"),
        EMAIL_DOMAIN("email_domain"),
        ;

        private final String value;

        private ListImpactKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
