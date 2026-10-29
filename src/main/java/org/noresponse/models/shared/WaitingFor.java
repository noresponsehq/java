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
public class WaitingFor {

    public static final WaitingFor OUTCOME = new WaitingFor("outcome");
    public static final WaitingFor REVIEW = new WaitingFor("review");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, WaitingFor> values = createValuesMap();
    private static final Map<String, WaitingForEnum> enums = createEnumsMap();

    private final String value;

    private WaitingFor(String value) {
        this.value = value;
    }

    /**
     * Returns a WaitingFor with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as WaitingFor
     */
    @JsonCreator
    public static WaitingFor of(String value) {
        synchronized (WaitingFor.class) {
            return values.computeIfAbsent(value, v -> new WaitingFor(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<WaitingForEnum> asEnum() {
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
        WaitingFor other = (WaitingFor) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "WaitingFor [value=" + value + "]";
    }

    // return an array just like an enum
    public static WaitingFor[] values() {
        synchronized (WaitingFor.class) {
            return values.values().toArray(new WaitingFor[] {});
        }
    }

    private static final Map<String, WaitingFor> createValuesMap() {
        Map<String, WaitingFor> map = new LinkedHashMap<>();
        map.put("outcome", OUTCOME);
        map.put("review", REVIEW);
        return map;
    }

    private static final Map<String, WaitingForEnum> createEnumsMap() {
        Map<String, WaitingForEnum> map = new HashMap<>();
        map.put("outcome", WaitingForEnum.OUTCOME);
        map.put("review", WaitingForEnum.REVIEW);
        return map;
    }

    public enum WaitingForEnum {

        OUTCOME("outcome"),
        REVIEW("review"),
        ;

        private final String value;

        private WaitingForEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
