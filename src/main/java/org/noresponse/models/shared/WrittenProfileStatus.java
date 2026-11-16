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
 * WrittenProfileStatus
 *
 * <p>queued: the profile is being written. written: summary, statements and leads hold it. failed: it
 * could not be written; error_code says why and no content is returned.
 */
public class WrittenProfileStatus {

    public static final WrittenProfileStatus QUEUED = new WrittenProfileStatus("queued");
    public static final WrittenProfileStatus WRITTEN = new WrittenProfileStatus("written");
    public static final WrittenProfileStatus FAILED = new WrittenProfileStatus("failed");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, WrittenProfileStatus> values = createValuesMap();
    private static final Map<String, WrittenProfileStatusEnum> enums = createEnumsMap();

    private final String value;

    private WrittenProfileStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a WrittenProfileStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as WrittenProfileStatus
     */
    @JsonCreator
    public static WrittenProfileStatus of(String value) {
        synchronized (WrittenProfileStatus.class) {
            return values.computeIfAbsent(value, v -> new WrittenProfileStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<WrittenProfileStatusEnum> asEnum() {
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
        WrittenProfileStatus other = (WrittenProfileStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "WrittenProfileStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static WrittenProfileStatus[] values() {
        synchronized (WrittenProfileStatus.class) {
            return values.values().toArray(new WrittenProfileStatus[] {});
        }
    }

    private static final Map<String, WrittenProfileStatus> createValuesMap() {
        Map<String, WrittenProfileStatus> map = new LinkedHashMap<>();
        map.put("queued", QUEUED);
        map.put("written", WRITTEN);
        map.put("failed", FAILED);
        return map;
    }

    private static final Map<String, WrittenProfileStatusEnum> createEnumsMap() {
        Map<String, WrittenProfileStatusEnum> map = new HashMap<>();
        map.put("queued", WrittenProfileStatusEnum.QUEUED);
        map.put("written", WrittenProfileStatusEnum.WRITTEN);
        map.put("failed", WrittenProfileStatusEnum.FAILED);
        return map;
    }

    public enum WrittenProfileStatusEnum {

        QUEUED("queued"),
        WRITTEN("written"),
        FAILED("failed"),
        ;

        private final String value;

        private WrittenProfileStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
