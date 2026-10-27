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
 * ListEntryKind
 *
 * <p>email matches one address, whatever the case of its local part. email_domain matches every address
 * of the registrable domain and of its subdomains.
 */
public class ListEntryKind {

    public static final ListEntryKind EMAIL = new ListEntryKind("email");
    public static final ListEntryKind EMAIL_DOMAIN = new ListEntryKind("email_domain");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ListEntryKind> values = createValuesMap();
    private static final Map<String, ListEntryKindEnum> enums = createEnumsMap();

    private final String value;

    private ListEntryKind(String value) {
        this.value = value;
    }

    /**
     * Returns a ListEntryKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ListEntryKind
     */
    @JsonCreator
    public static ListEntryKind of(String value) {
        synchronized (ListEntryKind.class) {
            return values.computeIfAbsent(value, v -> new ListEntryKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ListEntryKindEnum> asEnum() {
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
        ListEntryKind other = (ListEntryKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ListEntryKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static ListEntryKind[] values() {
        synchronized (ListEntryKind.class) {
            return values.values().toArray(new ListEntryKind[] {});
        }
    }

    private static final Map<String, ListEntryKind> createValuesMap() {
        Map<String, ListEntryKind> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("email_domain", EMAIL_DOMAIN);
        return map;
    }

    private static final Map<String, ListEntryKindEnum> createEnumsMap() {
        Map<String, ListEntryKindEnum> map = new HashMap<>();
        map.put("email", ListEntryKindEnum.EMAIL);
        map.put("email_domain", ListEntryKindEnum.EMAIL_DOMAIN);
        return map;
    }

    public enum ListEntryKindEnum {

        EMAIL("email"),
        EMAIL_DOMAIN("email_domain"),
        ;

        private final String value;

        private ListEntryKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
