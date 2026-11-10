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
 * ScreeningMatchEvidenceKind
 *
 * <p>What matched. email, phone, supplied_handle and source_fact are hard identifiers; company, city and
 * school are soft. supplied_handle is a handle on the attendee row; source_fact is an account a
 * profile found by the attendee's lookups lists.
 */
public class ScreeningMatchEvidenceKind {

    public static final ScreeningMatchEvidenceKind EMAIL = new ScreeningMatchEvidenceKind("email");
    public static final ScreeningMatchEvidenceKind PHONE = new ScreeningMatchEvidenceKind("phone");
    public static final ScreeningMatchEvidenceKind SUPPLIED_HANDLE = new ScreeningMatchEvidenceKind("supplied_handle");
    public static final ScreeningMatchEvidenceKind SOURCE_FACT = new ScreeningMatchEvidenceKind("source_fact");
    public static final ScreeningMatchEvidenceKind NAME = new ScreeningMatchEvidenceKind("name");
    public static final ScreeningMatchEvidenceKind COMPANY = new ScreeningMatchEvidenceKind("company");
    public static final ScreeningMatchEvidenceKind CITY = new ScreeningMatchEvidenceKind("city");
    public static final ScreeningMatchEvidenceKind SCHOOL = new ScreeningMatchEvidenceKind("school");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningMatchEvidenceKind> values = createValuesMap();
    private static final Map<String, ScreeningMatchEvidenceKindEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningMatchEvidenceKind(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningMatchEvidenceKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningMatchEvidenceKind
     */
    @JsonCreator
    public static ScreeningMatchEvidenceKind of(String value) {
        synchronized (ScreeningMatchEvidenceKind.class) {
            return values.computeIfAbsent(value, v -> new ScreeningMatchEvidenceKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningMatchEvidenceKindEnum> asEnum() {
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
        ScreeningMatchEvidenceKind other = (ScreeningMatchEvidenceKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningMatchEvidenceKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningMatchEvidenceKind[] values() {
        synchronized (ScreeningMatchEvidenceKind.class) {
            return values.values().toArray(new ScreeningMatchEvidenceKind[] {});
        }
    }

    private static final Map<String, ScreeningMatchEvidenceKind> createValuesMap() {
        Map<String, ScreeningMatchEvidenceKind> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("phone", PHONE);
        map.put("supplied_handle", SUPPLIED_HANDLE);
        map.put("source_fact", SOURCE_FACT);
        map.put("name", NAME);
        map.put("company", COMPANY);
        map.put("city", CITY);
        map.put("school", SCHOOL);
        return map;
    }

    private static final Map<String, ScreeningMatchEvidenceKindEnum> createEnumsMap() {
        Map<String, ScreeningMatchEvidenceKindEnum> map = new HashMap<>();
        map.put("email", ScreeningMatchEvidenceKindEnum.EMAIL);
        map.put("phone", ScreeningMatchEvidenceKindEnum.PHONE);
        map.put("supplied_handle", ScreeningMatchEvidenceKindEnum.SUPPLIED_HANDLE);
        map.put("source_fact", ScreeningMatchEvidenceKindEnum.SOURCE_FACT);
        map.put("name", ScreeningMatchEvidenceKindEnum.NAME);
        map.put("company", ScreeningMatchEvidenceKindEnum.COMPANY);
        map.put("city", ScreeningMatchEvidenceKindEnum.CITY);
        map.put("school", ScreeningMatchEvidenceKindEnum.SCHOOL);
        return map;
    }

    public enum ScreeningMatchEvidenceKindEnum {

        EMAIL("email"),
        PHONE("phone"),
        SUPPLIED_HANDLE("supplied_handle"),
        SOURCE_FACT("source_fact"),
        NAME("name"),
        COMPANY("company"),
        CITY("city"),
        SCHOOL("school"),
        ;

        private final String value;

        private ScreeningMatchEvidenceKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
