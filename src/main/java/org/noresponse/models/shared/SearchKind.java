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
 * SearchKind
 *
 * <p>The kind of the value searched.
 */
public class SearchKind {

    public static final SearchKind EMAIL = new SearchKind("email");
    public static final SearchKind PHONE = new SearchKind("phone");
    public static final SearchKind USERNAME = new SearchKind("username");
    public static final SearchKind PERSON_NAME = new SearchKind("person_name");
    public static final SearchKind PASSWORD = new SearchKind("password");
    public static final SearchKind HASH = new SearchKind("hash");
    public static final SearchKind IP = new SearchKind("ip");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, SearchKind> values = createValuesMap();
    private static final Map<String, SearchKindEnum> enums = createEnumsMap();

    private final String value;

    private SearchKind(String value) {
        this.value = value;
    }

    /**
     * Returns a SearchKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as SearchKind
     */
    @JsonCreator
    public static SearchKind of(String value) {
        synchronized (SearchKind.class) {
            return values.computeIfAbsent(value, v -> new SearchKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SearchKindEnum> asEnum() {
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
        SearchKind other = (SearchKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "SearchKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static SearchKind[] values() {
        synchronized (SearchKind.class) {
            return values.values().toArray(new SearchKind[] {});
        }
    }

    private static final Map<String, SearchKind> createValuesMap() {
        Map<String, SearchKind> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("phone", PHONE);
        map.put("username", USERNAME);
        map.put("person_name", PERSON_NAME);
        map.put("password", PASSWORD);
        map.put("hash", HASH);
        map.put("ip", IP);
        return map;
    }

    private static final Map<String, SearchKindEnum> createEnumsMap() {
        Map<String, SearchKindEnum> map = new HashMap<>();
        map.put("email", SearchKindEnum.EMAIL);
        map.put("phone", SearchKindEnum.PHONE);
        map.put("username", SearchKindEnum.USERNAME);
        map.put("person_name", SearchKindEnum.PERSON_NAME);
        map.put("password", SearchKindEnum.PASSWORD);
        map.put("hash", SearchKindEnum.HASH);
        map.put("ip", SearchKindEnum.IP);
        return map;
    }

    public enum SearchKindEnum {

        EMAIL("email"),
        PHONE("phone"),
        USERNAME("username"),
        PERSON_NAME("person_name"),
        PASSWORD("password"),
        HASH("hash"),
        IP("ip"),
        ;

        private final String value;

        private SearchKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
