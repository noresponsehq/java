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
 * ScreeningPlatformCoverageStatus
 *
 * <p>covered read at least one profile; none_found looked and found no account; inaccessible found
 * accounts that are private or locked; failed could not finish looking; unavailable has no resolver or
 * collector enabled.
 */
public class ScreeningPlatformCoverageStatus {

    public static final ScreeningPlatformCoverageStatus COVERED = new ScreeningPlatformCoverageStatus("covered");
    public static final ScreeningPlatformCoverageStatus NONE_FOUND = new ScreeningPlatformCoverageStatus("none_found");
    public static final ScreeningPlatformCoverageStatus INACCESSIBLE =
            new ScreeningPlatformCoverageStatus("inaccessible");
    public static final ScreeningPlatformCoverageStatus FAILED = new ScreeningPlatformCoverageStatus("failed");
    public static final ScreeningPlatformCoverageStatus UNAVAILABLE =
            new ScreeningPlatformCoverageStatus("unavailable");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningPlatformCoverageStatus> values = createValuesMap();
    private static final Map<String, ScreeningPlatformCoverageStatusEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningPlatformCoverageStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningPlatformCoverageStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningPlatformCoverageStatus
     */
    @JsonCreator
    public static ScreeningPlatformCoverageStatus of(String value) {
        synchronized (ScreeningPlatformCoverageStatus.class) {
            return values.computeIfAbsent(value, v -> new ScreeningPlatformCoverageStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningPlatformCoverageStatusEnum> asEnum() {
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
        ScreeningPlatformCoverageStatus other = (ScreeningPlatformCoverageStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningPlatformCoverageStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningPlatformCoverageStatus[] values() {
        synchronized (ScreeningPlatformCoverageStatus.class) {
            return values.values().toArray(new ScreeningPlatformCoverageStatus[] {});
        }
    }

    private static final Map<String, ScreeningPlatformCoverageStatus> createValuesMap() {
        Map<String, ScreeningPlatformCoverageStatus> map = new LinkedHashMap<>();
        map.put("covered", COVERED);
        map.put("none_found", NONE_FOUND);
        map.put("inaccessible", INACCESSIBLE);
        map.put("failed", FAILED);
        map.put("unavailable", UNAVAILABLE);
        return map;
    }

    private static final Map<String, ScreeningPlatformCoverageStatusEnum> createEnumsMap() {
        Map<String, ScreeningPlatformCoverageStatusEnum> map = new HashMap<>();
        map.put("covered", ScreeningPlatformCoverageStatusEnum.COVERED);
        map.put("none_found", ScreeningPlatformCoverageStatusEnum.NONE_FOUND);
        map.put("inaccessible", ScreeningPlatformCoverageStatusEnum.INACCESSIBLE);
        map.put("failed", ScreeningPlatformCoverageStatusEnum.FAILED);
        map.put("unavailable", ScreeningPlatformCoverageStatusEnum.UNAVAILABLE);
        return map;
    }

    public enum ScreeningPlatformCoverageStatusEnum {

        COVERED("covered"),
        NONE_FOUND("none_found"),
        INACCESSIBLE("inaccessible"),
        FAILED("failed"),
        UNAVAILABLE("unavailable"),
        ;

        private final String value;

        private ScreeningPlatformCoverageStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
