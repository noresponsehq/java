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
public class ResultStatus {

    public static final ResultStatus NO_CHANGE = new ResultStatus("no_change");
    public static final ResultStatus PROPOSAL_REQUIRES_REVIEW = new ResultStatus("proposal_requires_review");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ResultStatus> values = createValuesMap();
    private static final Map<String, ResultStatusEnum> enums = createEnumsMap();

    private final String value;

    private ResultStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a ResultStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ResultStatus
     */
    @JsonCreator
    public static ResultStatus of(String value) {
        synchronized (ResultStatus.class) {
            return values.computeIfAbsent(value, v -> new ResultStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ResultStatusEnum> asEnum() {
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
        ResultStatus other = (ResultStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ResultStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static ResultStatus[] values() {
        synchronized (ResultStatus.class) {
            return values.values().toArray(new ResultStatus[] {});
        }
    }

    private static final Map<String, ResultStatus> createValuesMap() {
        Map<String, ResultStatus> map = new LinkedHashMap<>();
        map.put("no_change", NO_CHANGE);
        map.put("proposal_requires_review", PROPOSAL_REQUIRES_REVIEW);
        return map;
    }

    private static final Map<String, ResultStatusEnum> createEnumsMap() {
        Map<String, ResultStatusEnum> map = new HashMap<>();
        map.put("no_change", ResultStatusEnum.NO_CHANGE);
        map.put("proposal_requires_review", ResultStatusEnum.PROPOSAL_REQUIRES_REVIEW);
        return map;
    }

    public enum ResultStatusEnum {

        NO_CHANGE("no_change"),
        PROPOSAL_REQUIRES_REVIEW("proposal_requires_review"),
        ;

        private final String value;

        private ResultStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
