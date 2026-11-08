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
public class ScreeningPlatform {

    public static final ScreeningPlatform INSTAGRAM = new ScreeningPlatform("instagram");
    public static final ScreeningPlatform X = new ScreeningPlatform("x");
    public static final ScreeningPlatform BLUESKY = new ScreeningPlatform("bluesky");
    public static final ScreeningPlatform YOUTUBE = new ScreeningPlatform("youtube");
    public static final ScreeningPlatform TIKTOK = new ScreeningPlatform("tiktok");
    public static final ScreeningPlatform FACEBOOK = new ScreeningPlatform("facebook");
    public static final ScreeningPlatform THREADS = new ScreeningPlatform("threads");
    public static final ScreeningPlatform REDDIT = new ScreeningPlatform("reddit");
    public static final ScreeningPlatform TELEGRAM = new ScreeningPlatform("telegram");
    public static final ScreeningPlatform LINKEDIN = new ScreeningPlatform("linkedin");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningPlatform> values = createValuesMap();
    private static final Map<String, ScreeningPlatformEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningPlatform(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningPlatform with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningPlatform
     */
    @JsonCreator
    public static ScreeningPlatform of(String value) {
        synchronized (ScreeningPlatform.class) {
            return values.computeIfAbsent(value, v -> new ScreeningPlatform(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningPlatformEnum> asEnum() {
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
        ScreeningPlatform other = (ScreeningPlatform) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningPlatform [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningPlatform[] values() {
        synchronized (ScreeningPlatform.class) {
            return values.values().toArray(new ScreeningPlatform[] {});
        }
    }

    private static final Map<String, ScreeningPlatform> createValuesMap() {
        Map<String, ScreeningPlatform> map = new LinkedHashMap<>();
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

    private static final Map<String, ScreeningPlatformEnum> createEnumsMap() {
        Map<String, ScreeningPlatformEnum> map = new HashMap<>();
        map.put("instagram", ScreeningPlatformEnum.INSTAGRAM);
        map.put("x", ScreeningPlatformEnum.X);
        map.put("bluesky", ScreeningPlatformEnum.BLUESKY);
        map.put("youtube", ScreeningPlatformEnum.YOUTUBE);
        map.put("tiktok", ScreeningPlatformEnum.TIKTOK);
        map.put("facebook", ScreeningPlatformEnum.FACEBOOK);
        map.put("threads", ScreeningPlatformEnum.THREADS);
        map.put("reddit", ScreeningPlatformEnum.REDDIT);
        map.put("telegram", ScreeningPlatformEnum.TELEGRAM);
        map.put("linkedin", ScreeningPlatformEnum.LINKEDIN);
        return map;
    }

    public enum ScreeningPlatformEnum {

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

        private ScreeningPlatformEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
