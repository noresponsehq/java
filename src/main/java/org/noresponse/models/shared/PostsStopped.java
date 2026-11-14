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
 * PostsStopped
 *
 * <p>Why fewer posts were read than the screening's depth asks for while the account holds more.
 * platform_limit: the platform shows the screening no more of them. page_limit: the pages a screening
 * reads ran out first.
 *
 * <p>rate_limited or unavailable: a later page was held back or failed; the posts before it are kept and
 * the person is partial. Absent when the account was read to its end or to the depth.
 */
public class PostsStopped {

    public static final PostsStopped PLATFORM_LIMIT = new PostsStopped("platform_limit");
    public static final PostsStopped PAGE_LIMIT = new PostsStopped("page_limit");
    public static final PostsStopped RATE_LIMITED = new PostsStopped("rate_limited");
    public static final PostsStopped UNAVAILABLE = new PostsStopped("unavailable");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, PostsStopped> values = createValuesMap();
    private static final Map<String, PostsStoppedEnum> enums = createEnumsMap();

    private final String value;

    private PostsStopped(String value) {
        this.value = value;
    }

    /**
     * Returns a PostsStopped with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as PostsStopped
     */
    @JsonCreator
    public static PostsStopped of(String value) {
        synchronized (PostsStopped.class) {
            return values.computeIfAbsent(value, v -> new PostsStopped(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<PostsStoppedEnum> asEnum() {
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
        PostsStopped other = (PostsStopped) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "PostsStopped [value=" + value + "]";
    }

    // return an array just like an enum
    public static PostsStopped[] values() {
        synchronized (PostsStopped.class) {
            return values.values().toArray(new PostsStopped[] {});
        }
    }

    private static final Map<String, PostsStopped> createValuesMap() {
        Map<String, PostsStopped> map = new LinkedHashMap<>();
        map.put("platform_limit", PLATFORM_LIMIT);
        map.put("page_limit", PAGE_LIMIT);
        map.put("rate_limited", RATE_LIMITED);
        map.put("unavailable", UNAVAILABLE);
        return map;
    }

    private static final Map<String, PostsStoppedEnum> createEnumsMap() {
        Map<String, PostsStoppedEnum> map = new HashMap<>();
        map.put("platform_limit", PostsStoppedEnum.PLATFORM_LIMIT);
        map.put("page_limit", PostsStoppedEnum.PAGE_LIMIT);
        map.put("rate_limited", PostsStoppedEnum.RATE_LIMITED);
        map.put("unavailable", PostsStoppedEnum.UNAVAILABLE);
        return map;
    }

    public enum PostsStoppedEnum {

        PLATFORM_LIMIT("platform_limit"),
        PAGE_LIMIT("page_limit"),
        RATE_LIMITED("rate_limited"),
        UNAVAILABLE("unavailable"),
        ;

        private final String value;

        private PostsStoppedEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
