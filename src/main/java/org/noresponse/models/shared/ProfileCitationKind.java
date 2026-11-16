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
 * ProfileCitationKind
 *
 * <p>record: a record in lookup.records or profiles[].record. post: an item in posts. check: an item in
 * lookup.checks.
 */
public class ProfileCitationKind {

    public static final ProfileCitationKind RECORD = new ProfileCitationKind("record");
    public static final ProfileCitationKind POST = new ProfileCitationKind("post");
    public static final ProfileCitationKind CHECK = new ProfileCitationKind("check");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ProfileCitationKind> values = createValuesMap();
    private static final Map<String, ProfileCitationKindEnum> enums = createEnumsMap();

    private final String value;

    private ProfileCitationKind(String value) {
        this.value = value;
    }

    /**
     * Returns a ProfileCitationKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ProfileCitationKind
     */
    @JsonCreator
    public static ProfileCitationKind of(String value) {
        synchronized (ProfileCitationKind.class) {
            return values.computeIfAbsent(value, v -> new ProfileCitationKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ProfileCitationKindEnum> asEnum() {
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
        ProfileCitationKind other = (ProfileCitationKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ProfileCitationKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static ProfileCitationKind[] values() {
        synchronized (ProfileCitationKind.class) {
            return values.values().toArray(new ProfileCitationKind[] {});
        }
    }

    private static final Map<String, ProfileCitationKind> createValuesMap() {
        Map<String, ProfileCitationKind> map = new LinkedHashMap<>();
        map.put("record", RECORD);
        map.put("post", POST);
        map.put("check", CHECK);
        return map;
    }

    private static final Map<String, ProfileCitationKindEnum> createEnumsMap() {
        Map<String, ProfileCitationKindEnum> map = new HashMap<>();
        map.put("record", ProfileCitationKindEnum.RECORD);
        map.put("post", ProfileCitationKindEnum.POST);
        map.put("check", ProfileCitationKindEnum.CHECK);
        return map;
    }

    public enum ProfileCitationKindEnum {

        RECORD("record"),
        POST("post"),
        CHECK("check"),
        ;

        private final String value;

        private ProfileCitationKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
