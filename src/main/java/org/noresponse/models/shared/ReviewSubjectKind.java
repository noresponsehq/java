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
public class ReviewSubjectKind {

    public static final ReviewSubjectKind ACCOUNT = new ReviewSubjectKind("account");
    public static final ReviewSubjectKind ASSESSMENT = new ReviewSubjectKind("assessment");
    public static final ReviewSubjectKind ISSUED_CREDENTIAL = new ReviewSubjectKind("issued_credential");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ReviewSubjectKind> values = createValuesMap();
    private static final Map<String, ReviewSubjectKindEnum> enums = createEnumsMap();

    private final String value;

    private ReviewSubjectKind(String value) {
        this.value = value;
    }

    /**
     * Returns a ReviewSubjectKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ReviewSubjectKind
     */
    @JsonCreator
    public static ReviewSubjectKind of(String value) {
        synchronized (ReviewSubjectKind.class) {
            return values.computeIfAbsent(value, v -> new ReviewSubjectKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ReviewSubjectKindEnum> asEnum() {
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
        ReviewSubjectKind other = (ReviewSubjectKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ReviewSubjectKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static ReviewSubjectKind[] values() {
        synchronized (ReviewSubjectKind.class) {
            return values.values().toArray(new ReviewSubjectKind[] {});
        }
    }

    private static final Map<String, ReviewSubjectKind> createValuesMap() {
        Map<String, ReviewSubjectKind> map = new LinkedHashMap<>();
        map.put("account", ACCOUNT);
        map.put("assessment", ASSESSMENT);
        map.put("issued_credential", ISSUED_CREDENTIAL);
        return map;
    }

    private static final Map<String, ReviewSubjectKindEnum> createEnumsMap() {
        Map<String, ReviewSubjectKindEnum> map = new HashMap<>();
        map.put("account", ReviewSubjectKindEnum.ACCOUNT);
        map.put("assessment", ReviewSubjectKindEnum.ASSESSMENT);
        map.put("issued_credential", ReviewSubjectKindEnum.ISSUED_CREDENTIAL);
        return map;
    }

    public enum ReviewSubjectKindEnum {

        ACCOUNT("account"),
        ASSESSMENT("assessment"),
        ISSUED_CREDENTIAL("issued_credential"),
        ;

        private final String value;

        private ReviewSubjectKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
