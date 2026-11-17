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
 * SuggestedSearchKind
 *
 * <p>The kind of the value: one of the kinds the environment's enrichment setting names.
 */
public class SuggestedSearchKind {

    public static final SuggestedSearchKind USERNAME = new SuggestedSearchKind("username");
    public static final SuggestedSearchKind EMAIL = new SuggestedSearchKind("email");
    public static final SuggestedSearchKind PHONE = new SuggestedSearchKind("phone");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, SuggestedSearchKind> values = createValuesMap();
    private static final Map<String, SuggestedSearchKindEnum> enums = createEnumsMap();

    private final String value;

    private SuggestedSearchKind(String value) {
        this.value = value;
    }

    /**
     * Returns a SuggestedSearchKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as SuggestedSearchKind
     */
    @JsonCreator
    public static SuggestedSearchKind of(String value) {
        synchronized (SuggestedSearchKind.class) {
            return values.computeIfAbsent(value, v -> new SuggestedSearchKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SuggestedSearchKindEnum> asEnum() {
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
        SuggestedSearchKind other = (SuggestedSearchKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "SuggestedSearchKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static SuggestedSearchKind[] values() {
        synchronized (SuggestedSearchKind.class) {
            return values.values().toArray(new SuggestedSearchKind[] {});
        }
    }

    private static final Map<String, SuggestedSearchKind> createValuesMap() {
        Map<String, SuggestedSearchKind> map = new LinkedHashMap<>();
        map.put("username", USERNAME);
        map.put("email", EMAIL);
        map.put("phone", PHONE);
        return map;
    }

    private static final Map<String, SuggestedSearchKindEnum> createEnumsMap() {
        Map<String, SuggestedSearchKindEnum> map = new HashMap<>();
        map.put("username", SuggestedSearchKindEnum.USERNAME);
        map.put("email", SuggestedSearchKindEnum.EMAIL);
        map.put("phone", SuggestedSearchKindEnum.PHONE);
        return map;
    }

    public enum SuggestedSearchKindEnum {

        USERNAME("username"),
        EMAIL("email"),
        PHONE("phone"),
        ;

        private final String value;

        private SuggestedSearchKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
