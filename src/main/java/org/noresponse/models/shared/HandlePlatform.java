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
public class HandlePlatform {

    public static final HandlePlatform INSTAGRAM = new HandlePlatform("instagram");
    public static final HandlePlatform X = new HandlePlatform("x");
    public static final HandlePlatform BLUESKY = new HandlePlatform("bluesky");
    public static final HandlePlatform YOUTUBE = new HandlePlatform("youtube");
    public static final HandlePlatform TIKTOK = new HandlePlatform("tiktok");
    public static final HandlePlatform FACEBOOK = new HandlePlatform("facebook");
    public static final HandlePlatform THREADS = new HandlePlatform("threads");
    public static final HandlePlatform REDDIT = new HandlePlatform("reddit");
    public static final HandlePlatform TELEGRAM = new HandlePlatform("telegram");
    public static final HandlePlatform LINKEDIN = new HandlePlatform("linkedin");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, HandlePlatform> values = createValuesMap();
    private static final Map<String, HandlePlatformEnum> enums = createEnumsMap();

    private final String value;

    private HandlePlatform(String value) {
        this.value = value;
    }

    /**
     * Returns a HandlePlatform with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as HandlePlatform
     */
    @JsonCreator
    public static HandlePlatform of(String value) {
        synchronized (HandlePlatform.class) {
            return values.computeIfAbsent(value, v -> new HandlePlatform(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<HandlePlatformEnum> asEnum() {
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
        HandlePlatform other = (HandlePlatform) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "HandlePlatform [value=" + value + "]";
    }

    // return an array just like an enum
    public static HandlePlatform[] values() {
        synchronized (HandlePlatform.class) {
            return values.values().toArray(new HandlePlatform[] {});
        }
    }

    private static final Map<String, HandlePlatform> createValuesMap() {
        Map<String, HandlePlatform> map = new LinkedHashMap<>();
        map.put("instagram", INSTAGRAM);
        map.put("x", X);
        map.put("bluesky", BLUESKY);
        map.put("youtube", YOUTUBE);
        map.put("tiktok", TIKTOK);
        map.put("facebook", FACEBOOK);
        map.put("threads", THREADS);
        map.put("reddit", REDDIT);
        map.put("telegram", TELEGRAM);
        map.put("linkedin", LINKEDIN);
        return map;
    }

    private static final Map<String, HandlePlatformEnum> createEnumsMap() {
        Map<String, HandlePlatformEnum> map = new HashMap<>();
        map.put("instagram", HandlePlatformEnum.INSTAGRAM);
        map.put("x", HandlePlatformEnum.X);
        map.put("bluesky", HandlePlatformEnum.BLUESKY);
        map.put("youtube", HandlePlatformEnum.YOUTUBE);
        map.put("tiktok", HandlePlatformEnum.TIKTOK);
        map.put("facebook", HandlePlatformEnum.FACEBOOK);
        map.put("threads", HandlePlatformEnum.THREADS);
        map.put("reddit", HandlePlatformEnum.REDDIT);
        map.put("telegram", HandlePlatformEnum.TELEGRAM);
        map.put("linkedin", HandlePlatformEnum.LINKEDIN);
        return map;
    }

    public enum HandlePlatformEnum {

        INSTAGRAM("instagram"),
        X("x"),
        BLUESKY("bluesky"),
        YOUTUBE("youtube"),
        TIKTOK("tiktok"),
        FACEBOOK("facebook"),
        THREADS("threads"),
        REDDIT("reddit"),
        TELEGRAM("telegram"),
        LINKEDIN("linkedin"),
        ;

        private final String value;

        private HandlePlatformEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
