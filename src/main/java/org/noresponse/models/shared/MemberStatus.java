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
 * MemberStatus
 *
 * <p>Listed and returned members are active.
 */
public class MemberStatus {

    public static final MemberStatus ACTIVE = new MemberStatus("active");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, MemberStatus> values = createValuesMap();
    private static final Map<String, MemberStatusEnum> enums = createEnumsMap();

    private final String value;

    private MemberStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a MemberStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as MemberStatus
     */
    @JsonCreator
    public static MemberStatus of(String value) {
        synchronized (MemberStatus.class) {
            return values.computeIfAbsent(value, v -> new MemberStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MemberStatusEnum> asEnum() {
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
        MemberStatus other = (MemberStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "MemberStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static MemberStatus[] values() {
        synchronized (MemberStatus.class) {
            return values.values().toArray(new MemberStatus[] {});
        }
    }

    private static final Map<String, MemberStatus> createValuesMap() {
        Map<String, MemberStatus> map = new LinkedHashMap<>();
        map.put("active", ACTIVE);
        return map;
    }

    private static final Map<String, MemberStatusEnum> createEnumsMap() {
        Map<String, MemberStatusEnum> map = new HashMap<>();
        map.put("active", MemberStatusEnum.ACTIVE);
        return map;
    }

    public enum MemberStatusEnum {

        ACTIVE("active"),
        ;

        private final String value;

        private MemberStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
