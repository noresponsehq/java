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
 * GroupType
 *
 * <p>What the group's people share: an event, an organisation, a place or another attribute.
 */
public class GroupType {

    public static final GroupType EVENT = new GroupType("event");
    public static final GroupType ORGANISATION = new GroupType("organisation");
    public static final GroupType PLACE = new GroupType("place");
    public static final GroupType CUSTOM = new GroupType("custom");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, GroupType> values = createValuesMap();
    private static final Map<String, GroupTypeEnum> enums = createEnumsMap();

    private final String value;

    private GroupType(String value) {
        this.value = value;
    }

    /**
     * Returns a GroupType with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as GroupType
     */
    @JsonCreator
    public static GroupType of(String value) {
        synchronized (GroupType.class) {
            return values.computeIfAbsent(value, v -> new GroupType(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<GroupTypeEnum> asEnum() {
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
        GroupType other = (GroupType) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "GroupType [value=" + value + "]";
    }

    // return an array just like an enum
    public static GroupType[] values() {
        synchronized (GroupType.class) {
            return values.values().toArray(new GroupType[] {});
        }
    }

    private static final Map<String, GroupType> createValuesMap() {
        Map<String, GroupType> map = new LinkedHashMap<>();
        map.put("event", EVENT);
        map.put("organisation", ORGANISATION);
        map.put("place", PLACE);
        map.put("custom", CUSTOM);
        return map;
    }

    private static final Map<String, GroupTypeEnum> createEnumsMap() {
        Map<String, GroupTypeEnum> map = new HashMap<>();
        map.put("event", GroupTypeEnum.EVENT);
        map.put("organisation", GroupTypeEnum.ORGANISATION);
        map.put("place", GroupTypeEnum.PLACE);
        map.put("custom", GroupTypeEnum.CUSTOM);
        return map;
    }

    public enum GroupTypeEnum {

        EVENT("event"),
        ORGANISATION("organisation"),
        PLACE("place"),
        CUSTOM("custom"),
        ;

        private final String value;

        private GroupTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
