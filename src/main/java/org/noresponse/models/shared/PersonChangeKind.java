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
 * PersonChangeKind
 *
 * <p>account_added and account_removed: an account or profile appeared or disappeared. breach_added and
 * breach_removed: a breach record appeared or disappeared. identifier_added and identifier_removed: an
 * email, phone, username, name or account id on an account the person already held.
 *
 * <p>field_changed: another field on such an account. finding_added: a new finding. posts_added: new
 * posts, counted in count.
 */
public class PersonChangeKind {

    public static final PersonChangeKind ACCOUNT_ADDED = new PersonChangeKind("account_added");
    public static final PersonChangeKind ACCOUNT_REMOVED = new PersonChangeKind("account_removed");
    public static final PersonChangeKind BREACH_ADDED = new PersonChangeKind("breach_added");
    public static final PersonChangeKind BREACH_REMOVED = new PersonChangeKind("breach_removed");
    public static final PersonChangeKind IDENTIFIER_ADDED = new PersonChangeKind("identifier_added");
    public static final PersonChangeKind IDENTIFIER_REMOVED = new PersonChangeKind("identifier_removed");
    public static final PersonChangeKind FIELD_CHANGED = new PersonChangeKind("field_changed");
    public static final PersonChangeKind FINDING_ADDED = new PersonChangeKind("finding_added");
    public static final PersonChangeKind POSTS_ADDED = new PersonChangeKind("posts_added");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, PersonChangeKind> values = createValuesMap();
    private static final Map<String, PersonChangeKindEnum> enums = createEnumsMap();

    private final String value;

    private PersonChangeKind(String value) {
        this.value = value;
    }

    /**
     * Returns a PersonChangeKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as PersonChangeKind
     */
    @JsonCreator
    public static PersonChangeKind of(String value) {
        synchronized (PersonChangeKind.class) {
            return values.computeIfAbsent(value, v -> new PersonChangeKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PersonChangeKindEnum> asEnum() {
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
        PersonChangeKind other = (PersonChangeKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "PersonChangeKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static PersonChangeKind[] values() {
        synchronized (PersonChangeKind.class) {
            return values.values().toArray(new PersonChangeKind[] {});
        }
    }

    private static final Map<String, PersonChangeKind> createValuesMap() {
        Map<String, PersonChangeKind> map = new LinkedHashMap<>();
        map.put("account_added", ACCOUNT_ADDED);
        map.put("account_removed", ACCOUNT_REMOVED);
        map.put("breach_added", BREACH_ADDED);
        map.put("breach_removed", BREACH_REMOVED);
        map.put("identifier_added", IDENTIFIER_ADDED);
        map.put("identifier_removed", IDENTIFIER_REMOVED);
        map.put("field_changed", FIELD_CHANGED);
        map.put("finding_added", FINDING_ADDED);
        map.put("posts_added", POSTS_ADDED);
        return map;
    }

    private static final Map<String, PersonChangeKindEnum> createEnumsMap() {
        Map<String, PersonChangeKindEnum> map = new HashMap<>();
        map.put("account_added", PersonChangeKindEnum.ACCOUNT_ADDED);
        map.put("account_removed", PersonChangeKindEnum.ACCOUNT_REMOVED);
        map.put("breach_added", PersonChangeKindEnum.BREACH_ADDED);
        map.put("breach_removed", PersonChangeKindEnum.BREACH_REMOVED);
        map.put("identifier_added", PersonChangeKindEnum.IDENTIFIER_ADDED);
        map.put("identifier_removed", PersonChangeKindEnum.IDENTIFIER_REMOVED);
        map.put("field_changed", PersonChangeKindEnum.FIELD_CHANGED);
        map.put("finding_added", PersonChangeKindEnum.FINDING_ADDED);
        map.put("posts_added", PersonChangeKindEnum.POSTS_ADDED);
        return map;
    }

    public enum PersonChangeKindEnum {

        ACCOUNT_ADDED("account_added"),
        ACCOUNT_REMOVED("account_removed"),
        BREACH_ADDED("breach_added"),
        BREACH_REMOVED("breach_removed"),
        IDENTIFIER_ADDED("identifier_added"),
        IDENTIFIER_REMOVED("identifier_removed"),
        FIELD_CHANGED("field_changed"),
        FINDING_ADDED("finding_added"),
        POSTS_ADDED("posts_added"),
        ;

        private final String value;

        private PersonChangeKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
