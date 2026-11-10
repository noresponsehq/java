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
 * ScreeningLookupCheckStatus
 *
 * <p>found: a source found the identifier. not_found: every source answered and none found it. partial:
 * the sources that answered found nothing and the others did not answer.
 *
 * <p>not_covered: no source answered. A lookup that failed reads partial or not_covered, not not_found.
 */
public class ScreeningLookupCheckStatus {

    public static final ScreeningLookupCheckStatus FOUND = new ScreeningLookupCheckStatus("found");
    public static final ScreeningLookupCheckStatus NOT_FOUND = new ScreeningLookupCheckStatus("not_found");
    public static final ScreeningLookupCheckStatus PARTIAL = new ScreeningLookupCheckStatus("partial");
    public static final ScreeningLookupCheckStatus NOT_COVERED = new ScreeningLookupCheckStatus("not_covered");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningLookupCheckStatus> values = createValuesMap();
    private static final Map<String, ScreeningLookupCheckStatusEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningLookupCheckStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningLookupCheckStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningLookupCheckStatus
     */
    @JsonCreator
    public static ScreeningLookupCheckStatus of(String value) {
        synchronized (ScreeningLookupCheckStatus.class) {
            return values.computeIfAbsent(value, v -> new ScreeningLookupCheckStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningLookupCheckStatusEnum> asEnum() {
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
        ScreeningLookupCheckStatus other = (ScreeningLookupCheckStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningLookupCheckStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningLookupCheckStatus[] values() {
        synchronized (ScreeningLookupCheckStatus.class) {
            return values.values().toArray(new ScreeningLookupCheckStatus[] {});
        }
    }

    private static final Map<String, ScreeningLookupCheckStatus> createValuesMap() {
        Map<String, ScreeningLookupCheckStatus> map = new LinkedHashMap<>();
        map.put("found", FOUND);
        map.put("not_found", NOT_FOUND);
        map.put("partial", PARTIAL);
        map.put("not_covered", NOT_COVERED);
        return map;
    }

    private static final Map<String, ScreeningLookupCheckStatusEnum> createEnumsMap() {
        Map<String, ScreeningLookupCheckStatusEnum> map = new HashMap<>();
        map.put("found", ScreeningLookupCheckStatusEnum.FOUND);
        map.put("not_found", ScreeningLookupCheckStatusEnum.NOT_FOUND);
        map.put("partial", ScreeningLookupCheckStatusEnum.PARTIAL);
        map.put("not_covered", ScreeningLookupCheckStatusEnum.NOT_COVERED);
        return map;
    }

    public enum ScreeningLookupCheckStatusEnum {

        FOUND("found"),
        NOT_FOUND("not_found"),
        PARTIAL("partial"),
        NOT_COVERED("not_covered"),
        ;

        private final String value;

        private ScreeningLookupCheckStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
