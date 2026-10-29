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
public class ReviewStatus {

    public static final ReviewStatus OPEN = new ReviewStatus("open");
    public static final ReviewStatus IN_PROGRESS = new ReviewStatus("in_progress");
    public static final ReviewStatus RESOLVED = new ReviewStatus("resolved");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ReviewStatus> values = createValuesMap();
    private static final Map<String, ReviewStatusEnum> enums = createEnumsMap();

    private final String value;

    private ReviewStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a ReviewStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ReviewStatus
     */
    @JsonCreator
    public static ReviewStatus of(String value) {
        synchronized (ReviewStatus.class) {
            return values.computeIfAbsent(value, v -> new ReviewStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ReviewStatusEnum> asEnum() {
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
        ReviewStatus other = (ReviewStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ReviewStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static ReviewStatus[] values() {
        synchronized (ReviewStatus.class) {
            return values.values().toArray(new ReviewStatus[] {});
        }
    }

    private static final Map<String, ReviewStatus> createValuesMap() {
        Map<String, ReviewStatus> map = new LinkedHashMap<>();
        map.put("open", OPEN);
        map.put("in_progress", IN_PROGRESS);
        map.put("resolved", RESOLVED);
        return map;
    }

    private static final Map<String, ReviewStatusEnum> createEnumsMap() {
        Map<String, ReviewStatusEnum> map = new HashMap<>();
        map.put("open", ReviewStatusEnum.OPEN);
        map.put("in_progress", ReviewStatusEnum.IN_PROGRESS);
        map.put("resolved", ReviewStatusEnum.RESOLVED);
        return map;
    }

    public enum ReviewStatusEnum {

        OPEN("open"),
        IN_PROGRESS("in_progress"),
        RESOLVED("resolved"),
        ;

        private final String value;

        private ReviewStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
