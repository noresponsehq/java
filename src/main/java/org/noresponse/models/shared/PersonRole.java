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
 * PersonRole
 *
 * <p>The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when
 * you do not know it: a person without one has no role, not guest. A later row naming the same person
 * replaces it, and a change of role alone keeps the person's screening results.
 */
public class PersonRole {

    public static final PersonRole GUEST = new PersonRole("guest");
    public static final PersonRole CREW = new PersonRole("crew");
    public static final PersonRole VENDOR = new PersonRole("vendor");
    public static final PersonRole PRESS = new PersonRole("press");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, PersonRole> values = createValuesMap();
    private static final Map<String, PersonRoleEnum> enums = createEnumsMap();

    private final String value;

    private PersonRole(String value) {
        this.value = value;
    }

    /**
     * Returns a PersonRole with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as PersonRole
     */
    @JsonCreator
    public static PersonRole of(String value) {
        synchronized (PersonRole.class) {
            return values.computeIfAbsent(value, v -> new PersonRole(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PersonRoleEnum> asEnum() {
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
        PersonRole other = (PersonRole) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "PersonRole [value=" + value + "]";
    }

    // return an array just like an enum
    public static PersonRole[] values() {
        synchronized (PersonRole.class) {
            return values.values().toArray(new PersonRole[] {});
        }
    }

    private static final Map<String, PersonRole> createValuesMap() {
        Map<String, PersonRole> map = new LinkedHashMap<>();
        map.put("guest", GUEST);
        map.put("crew", CREW);
        map.put("vendor", VENDOR);
        map.put("press", PRESS);
        return map;
    }

    private static final Map<String, PersonRoleEnum> createEnumsMap() {
        Map<String, PersonRoleEnum> map = new HashMap<>();
        map.put("guest", PersonRoleEnum.GUEST);
        map.put("crew", PersonRoleEnum.CREW);
        map.put("vendor", PersonRoleEnum.VENDOR);
        map.put("press", PersonRoleEnum.PRESS);
        return map;
    }

    public enum PersonRoleEnum {

        GUEST("guest"),
        CREW("crew"),
        VENDOR("vendor"),
        PRESS("press"),
        ;

        private final String value;

        private PersonRoleEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
