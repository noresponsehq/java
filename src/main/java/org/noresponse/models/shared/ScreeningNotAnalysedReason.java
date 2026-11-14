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
 * ScreeningNotAnalysedReason
 *
 * <p>Why an item read was not analysed. video: a video, whose caption alone is read. image_unavailable:
 * the image could not be fetched from the platform.
 *
 * <p>image_unsupported: not a JPEG, PNG, GIF or WebP image, or too large to send. image_limit: past the
 * most images one person's analysis reads. refused: the model declined to read it.
 *
 * <p>unfinished: the model cut its batch short or answered outside the schema, or the request was
 * rejected. unverified: a finding cited it with words it does not contain or an image it did not
 * describe. unspecified: the analyser gave no reason.
 */
public class ScreeningNotAnalysedReason {

    public static final ScreeningNotAnalysedReason VIDEO = new ScreeningNotAnalysedReason("video");
    public static final ScreeningNotAnalysedReason IMAGE_UNAVAILABLE =
            new ScreeningNotAnalysedReason("image_unavailable");
    public static final ScreeningNotAnalysedReason IMAGE_UNSUPPORTED =
            new ScreeningNotAnalysedReason("image_unsupported");
    public static final ScreeningNotAnalysedReason IMAGE_LIMIT = new ScreeningNotAnalysedReason("image_limit");
    public static final ScreeningNotAnalysedReason REFUSED = new ScreeningNotAnalysedReason("refused");
    public static final ScreeningNotAnalysedReason UNFINISHED = new ScreeningNotAnalysedReason("unfinished");
    public static final ScreeningNotAnalysedReason UNVERIFIED = new ScreeningNotAnalysedReason("unverified");
    public static final ScreeningNotAnalysedReason UNSPECIFIED = new ScreeningNotAnalysedReason("unspecified");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ScreeningNotAnalysedReason> values = createValuesMap();
    private static final Map<String, ScreeningNotAnalysedReasonEnum> enums = createEnumsMap();

    private final String value;

    private ScreeningNotAnalysedReason(String value) {
        this.value = value;
    }

    /**
     * Returns a ScreeningNotAnalysedReason with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ScreeningNotAnalysedReason
     */
    @JsonCreator
    public static ScreeningNotAnalysedReason of(String value) {
        synchronized (ScreeningNotAnalysedReason.class) {
            return values.computeIfAbsent(value, v -> new ScreeningNotAnalysedReason(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ScreeningNotAnalysedReasonEnum> asEnum() {
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
        ScreeningNotAnalysedReason other = (ScreeningNotAnalysedReason) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ScreeningNotAnalysedReason [value=" + value + "]";
    }

    // return an array just like an enum
    public static ScreeningNotAnalysedReason[] values() {
        synchronized (ScreeningNotAnalysedReason.class) {
            return values.values().toArray(new ScreeningNotAnalysedReason[] {});
        }
    }

    private static final Map<String, ScreeningNotAnalysedReason> createValuesMap() {
        Map<String, ScreeningNotAnalysedReason> map = new LinkedHashMap<>();
        map.put("video", VIDEO);
        map.put("image_unavailable", IMAGE_UNAVAILABLE);
        map.put("image_unsupported", IMAGE_UNSUPPORTED);
        map.put("image_limit", IMAGE_LIMIT);
        map.put("refused", REFUSED);
        map.put("unfinished", UNFINISHED);
        map.put("unverified", UNVERIFIED);
        map.put("unspecified", UNSPECIFIED);
        return map;
    }

    private static final Map<String, ScreeningNotAnalysedReasonEnum> createEnumsMap() {
        Map<String, ScreeningNotAnalysedReasonEnum> map = new HashMap<>();
        map.put("video", ScreeningNotAnalysedReasonEnum.VIDEO);
        map.put("image_unavailable", ScreeningNotAnalysedReasonEnum.IMAGE_UNAVAILABLE);
        map.put("image_unsupported", ScreeningNotAnalysedReasonEnum.IMAGE_UNSUPPORTED);
        map.put("image_limit", ScreeningNotAnalysedReasonEnum.IMAGE_LIMIT);
        map.put("refused", ScreeningNotAnalysedReasonEnum.REFUSED);
        map.put("unfinished", ScreeningNotAnalysedReasonEnum.UNFINISHED);
        map.put("unverified", ScreeningNotAnalysedReasonEnum.UNVERIFIED);
        map.put("unspecified", ScreeningNotAnalysedReasonEnum.UNSPECIFIED);
        return map;
    }

    public enum ScreeningNotAnalysedReasonEnum {

        VIDEO("video"),
        IMAGE_UNAVAILABLE("image_unavailable"),
        IMAGE_UNSUPPORTED("image_unsupported"),
        IMAGE_LIMIT("image_limit"),
        REFUSED("refused"),
        UNFINISHED("unfinished"),
        UNVERIFIED("unverified"),
        UNSPECIFIED("unspecified"),
        ;

        private final String value;

        private ScreeningNotAnalysedReasonEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
