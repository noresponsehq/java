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
public class RequestAggregateRequestBucket {

    public static final RequestAggregateRequestBucket HOUR = new RequestAggregateRequestBucket("hour");
    public static final RequestAggregateRequestBucket DAY = new RequestAggregateRequestBucket("day");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RequestAggregateRequestBucket> values = createValuesMap();
    private static final Map<String, RequestAggregateRequestBucketEnum> enums = createEnumsMap();

    private final String value;

    private RequestAggregateRequestBucket(String value) {
        this.value = value;
    }

    /**
     * Returns a RequestAggregateRequestBucket with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RequestAggregateRequestBucket
     */
    @JsonCreator
    public static RequestAggregateRequestBucket of(String value) {
        synchronized (RequestAggregateRequestBucket.class) {
            return values.computeIfAbsent(value, v -> new RequestAggregateRequestBucket(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RequestAggregateRequestBucketEnum> asEnum() {
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
        RequestAggregateRequestBucket other = (RequestAggregateRequestBucket) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RequestAggregateRequestBucket [value=" + value + "]";
    }

    // return an array just like an enum
    public static RequestAggregateRequestBucket[] values() {
        synchronized (RequestAggregateRequestBucket.class) {
            return values.values().toArray(new RequestAggregateRequestBucket[] {});
        }
    }

    private static final Map<String, RequestAggregateRequestBucket> createValuesMap() {
        Map<String, RequestAggregateRequestBucket> map = new LinkedHashMap<>();
        map.put("hour", HOUR);
        map.put("day", DAY);
        return map;
    }

    private static final Map<String, RequestAggregateRequestBucketEnum> createEnumsMap() {
        Map<String, RequestAggregateRequestBucketEnum> map = new HashMap<>();
        map.put("hour", RequestAggregateRequestBucketEnum.HOUR);
        map.put("day", RequestAggregateRequestBucketEnum.DAY);
        return map;
    }

    public enum RequestAggregateRequestBucketEnum {

        HOUR("hour"),
        DAY("day"),
        ;

        private final String value;

        private RequestAggregateRequestBucketEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
