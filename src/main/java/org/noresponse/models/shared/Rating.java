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
 * Rating
 *
 * <p>unique: an email or phone, which belongs to one person by design. distinctive: rare enough that a
 * new search on it likely finds one person. common: shared by many people, so a search on it finds
 * strangers.
 *
 * <p>A new search is suggested for unique and distinctive values only; any value can still be searched.
 */
public class Rating {

    public static final Rating UNIQUE = new Rating("unique");
    public static final Rating DISTINCTIVE = new Rating("distinctive");
    public static final Rating COMMON = new Rating("common");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Rating> values = createValuesMap();
    private static final Map<String, RatingEnum> enums = createEnumsMap();

    private final String value;

    private Rating(String value) {
        this.value = value;
    }

    /**
     * Returns a Rating with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Rating
     */
    @JsonCreator
    public static Rating of(String value) {
        synchronized (Rating.class) {
            return values.computeIfAbsent(value, v -> new Rating(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RatingEnum> asEnum() {
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
        Rating other = (Rating) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Rating [value=" + value + "]";
    }

    // return an array just like an enum
    public static Rating[] values() {
        synchronized (Rating.class) {
            return values.values().toArray(new Rating[] {});
        }
    }

    private static final Map<String, Rating> createValuesMap() {
        Map<String, Rating> map = new LinkedHashMap<>();
        map.put("unique", UNIQUE);
        map.put("distinctive", DISTINCTIVE);
        map.put("common", COMMON);
        return map;
    }

    private static final Map<String, RatingEnum> createEnumsMap() {
        Map<String, RatingEnum> map = new HashMap<>();
        map.put("unique", RatingEnum.UNIQUE);
        map.put("distinctive", RatingEnum.DISTINCTIVE);
        map.put("common", RatingEnum.COMMON);
        return map;
    }

    public enum RatingEnum {

        UNIQUE("unique"),
        DISTINCTIVE("distinctive"),
        COMMON("common"),
        ;

        private final String value;

        private RatingEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
