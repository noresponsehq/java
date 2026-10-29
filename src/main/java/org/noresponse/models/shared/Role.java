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
 * Role
 *
 * <p>A built-in capability set. Specialist roles are independent; effective access is returned by GET
 * /v1/workspaces/{workspace_id}/capabilities.
 */
public class Role {

    public static final Role OWNER = new Role("owner");
    public static final Role ADMIN = new Role("admin");
    public static final Role DEVELOPER = new Role("developer");
    public static final Role VIEWER = new Role("viewer");
    public static final Role INVESTIGATOR = new Role("investigator");
    public static final Role AUDITOR = new Role("auditor");
    public static final Role BILLING = new Role("billing");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Role> values = createValuesMap();
    private static final Map<String, RoleEnum> enums = createEnumsMap();

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    /**
     * Returns a Role with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Role
     */
    @JsonCreator
    public static Role of(String value) {
        synchronized (Role.class) {
            return values.computeIfAbsent(value, v -> new Role(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RoleEnum> asEnum() {
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
        Role other = (Role) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Role [value=" + value + "]";
    }

    // return an array just like an enum
    public static Role[] values() {
        synchronized (Role.class) {
            return values.values().toArray(new Role[] {});
        }
    }

    private static final Map<String, Role> createValuesMap() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("owner", OWNER);
        map.put("admin", ADMIN);
        map.put("developer", DEVELOPER);
        map.put("viewer", VIEWER);
        map.put("investigator", INVESTIGATOR);
        map.put("auditor", AUDITOR);
        map.put("billing", BILLING);
        return map;
    }

    private static final Map<String, RoleEnum> createEnumsMap() {
        Map<String, RoleEnum> map = new HashMap<>();
        map.put("owner", RoleEnum.OWNER);
        map.put("admin", RoleEnum.ADMIN);
        map.put("developer", RoleEnum.DEVELOPER);
        map.put("viewer", RoleEnum.VIEWER);
        map.put("investigator", RoleEnum.INVESTIGATOR);
        map.put("auditor", RoleEnum.AUDITOR);
        map.put("billing", RoleEnum.BILLING);
        return map;
    }

    public enum RoleEnum {

        OWNER("owner"),
        ADMIN("admin"),
        DEVELOPER("developer"),
        VIEWER("viewer"),
        INVESTIGATOR("investigator"),
        AUDITOR("auditor"),
        BILLING("billing"),
        ;

        private final String value;

        private RoleEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
