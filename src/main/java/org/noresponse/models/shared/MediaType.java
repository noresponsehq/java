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
public class MediaType {

    public static final MediaType IMAGE = new MediaType("image");
    public static final MediaType VIDEO = new MediaType("video");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, MediaType> values = createValuesMap();
    private static final Map<String, MediaTypeEnum> enums = createEnumsMap();

    private final String value;

    private MediaType(String value) {
        this.value = value;
    }

    /**
     * Returns a MediaType with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as MediaType
     */
    @JsonCreator
    public static MediaType of(String value) {
        synchronized (MediaType.class) {
            return values.computeIfAbsent(value, v -> new MediaType(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<MediaTypeEnum> asEnum() {
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
        MediaType other = (MediaType) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "MediaType [value=" + value + "]";
    }

    // return an array just like an enum
    public static MediaType[] values() {
        synchronized (MediaType.class) {
            return values.values().toArray(new MediaType[] {});
        }
    }

    private static final Map<String, MediaType> createValuesMap() {
        Map<String, MediaType> map = new LinkedHashMap<>();
        map.put("image", IMAGE);
        map.put("video", VIDEO);
        return map;
    }

    private static final Map<String, MediaTypeEnum> createEnumsMap() {
        Map<String, MediaTypeEnum> map = new HashMap<>();
        map.put("image", MediaTypeEnum.IMAGE);
        map.put("video", MediaTypeEnum.VIDEO);
        return map;
    }

    public enum MediaTypeEnum {

        IMAGE("image"),
        VIDEO("video"),
        ;

        private final String value;

        private MediaTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
