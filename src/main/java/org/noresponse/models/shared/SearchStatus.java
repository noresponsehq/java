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
 * SearchStatus
 *
 * <p>A password or hash search is counting, then counted: rows says how many breach rows hold the value,
 * and nothing runs until a person runs it (POST .../searches/{search_id}/run). Any other search is
 * queued, then running. completed: every source it asked answered.
 *
 * <p>partial: some did not. failed: none answered or none could be asked, which says nothing about
 * whether the value is held anywhere.
 */
public class SearchStatus {

    public static final SearchStatus COUNTING = new SearchStatus("counting");
    public static final SearchStatus COUNTED = new SearchStatus("counted");
    public static final SearchStatus QUEUED = new SearchStatus("queued");
    public static final SearchStatus RUNNING = new SearchStatus("running");
    public static final SearchStatus COMPLETED = new SearchStatus("completed");
    public static final SearchStatus PARTIAL = new SearchStatus("partial");
    public static final SearchStatus FAILED = new SearchStatus("failed");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, SearchStatus> values = createValuesMap();
    private static final Map<String, SearchStatusEnum> enums = createEnumsMap();

    private final String value;

    private SearchStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a SearchStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as SearchStatus
     */
    @JsonCreator
    public static SearchStatus of(String value) {
        synchronized (SearchStatus.class) {
            return values.computeIfAbsent(value, v -> new SearchStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SearchStatusEnum> asEnum() {
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
        SearchStatus other = (SearchStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "SearchStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static SearchStatus[] values() {
        synchronized (SearchStatus.class) {
            return values.values().toArray(new SearchStatus[] {});
        }
    }

    private static final Map<String, SearchStatus> createValuesMap() {
        Map<String, SearchStatus> map = new LinkedHashMap<>();
        map.put("counting", COUNTING);
        map.put("counted", COUNTED);
        map.put("queued", QUEUED);
        map.put("running", RUNNING);
        map.put("completed", COMPLETED);
        map.put("partial", PARTIAL);
        map.put("failed", FAILED);
        return map;
    }

    private static final Map<String, SearchStatusEnum> createEnumsMap() {
        Map<String, SearchStatusEnum> map = new HashMap<>();
        map.put("counting", SearchStatusEnum.COUNTING);
        map.put("counted", SearchStatusEnum.COUNTED);
        map.put("queued", SearchStatusEnum.QUEUED);
        map.put("running", SearchStatusEnum.RUNNING);
        map.put("completed", SearchStatusEnum.COMPLETED);
        map.put("partial", SearchStatusEnum.PARTIAL);
        map.put("failed", SearchStatusEnum.FAILED);
        return map;
    }

    public enum SearchStatusEnum {

        COUNTING("counting"),
        COUNTED("counted"),
        QUEUED("queued"),
        RUNNING("running"),
        COMPLETED("completed"),
        PARTIAL("partial"),
        FAILED("failed"),
        ;

        private final String value;

        private SearchStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
