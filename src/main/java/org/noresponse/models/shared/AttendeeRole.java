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
 * AttendeeRole
 *
 * <p>The attendee's role at the event: guest (a ticket holder), crew, vendor or press. Leave it out when
 * you do not know it: an attendee without one has no role, not guest. A later row naming the same
 * attendee replaces it, and a change of role alone keeps the attendee's screening results.
 */
public class AttendeeRole {

    public static final AttendeeRole GUEST = new AttendeeRole("guest");
    public static final AttendeeRole CREW = new AttendeeRole("crew");
    public static final AttendeeRole VENDOR = new AttendeeRole("vendor");
    public static final AttendeeRole PRESS = new AttendeeRole("press");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, AttendeeRole> values = createValuesMap();
    private static final Map<String, AttendeeRoleEnum> enums = createEnumsMap();

    private final String value;

    private AttendeeRole(String value) {
        this.value = value;
    }

    /**
     * Returns a AttendeeRole with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as AttendeeRole
     */
    @JsonCreator
    public static AttendeeRole of(String value) {
        synchronized (AttendeeRole.class) {
            return values.computeIfAbsent(value, v -> new AttendeeRole(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<AttendeeRoleEnum> asEnum() {
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
        AttendeeRole other = (AttendeeRole) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "AttendeeRole [value=" + value + "]";
    }

    // return an array just like an enum
    public static AttendeeRole[] values() {
        synchronized (AttendeeRole.class) {
            return values.values().toArray(new AttendeeRole[] {});
        }
    }

    private static final Map<String, AttendeeRole> createValuesMap() {
        Map<String, AttendeeRole> map = new LinkedHashMap<>();
        map.put("guest", GUEST);
        map.put("crew", CREW);
        map.put("vendor", VENDOR);
        map.put("press", PRESS);
        return map;
    }

    private static final Map<String, AttendeeRoleEnum> createEnumsMap() {
        Map<String, AttendeeRoleEnum> map = new HashMap<>();
        map.put("guest", AttendeeRoleEnum.GUEST);
        map.put("crew", AttendeeRoleEnum.CREW);
        map.put("vendor", AttendeeRoleEnum.VENDOR);
        map.put("press", AttendeeRoleEnum.PRESS);
        return map;
    }

    public enum AttendeeRoleEnum {

        GUEST("guest"),
        CREW("crew"),
        VENDOR("vendor"),
        PRESS("press"),
        ;

        private final String value;

        private AttendeeRoleEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
