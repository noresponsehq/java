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
 * OnExpiry
 *
 * <p>The decision a held assessment takes when hold_seconds pass before its investigation completes,
 * unless the evidence so far gives a stricter one (default review). It is announced as
 * account.risk_changed like any other change, so an expired hold is always announced. A hosted
 * provider hook, which cannot wait, answers a held account by it at once: deny for block, allow
 * otherwise.
 *
 * <p>Kept while the flow is immediate.
 */
public class OnExpiry {

    public static final OnExpiry ALLOW = new OnExpiry("allow");
    public static final OnExpiry REVIEW = new OnExpiry("review");
    public static final OnExpiry BLOCK = new OnExpiry("block");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, OnExpiry> values = createValuesMap();
    private static final Map<String, OnExpiryEnum> enums = createEnumsMap();

    private final String value;

    private OnExpiry(String value) {
        this.value = value;
    }

    /**
     * Returns a OnExpiry with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as OnExpiry
     */
    @JsonCreator
    public static OnExpiry of(String value) {
        synchronized (OnExpiry.class) {
            return values.computeIfAbsent(value, v -> new OnExpiry(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<OnExpiryEnum> asEnum() {
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
        OnExpiry other = (OnExpiry) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "OnExpiry [value=" + value + "]";
    }

    // return an array just like an enum
    public static OnExpiry[] values() {
        synchronized (OnExpiry.class) {
            return values.values().toArray(new OnExpiry[] {});
        }
    }

    private static final Map<String, OnExpiry> createValuesMap() {
        Map<String, OnExpiry> map = new LinkedHashMap<>();
        map.put("allow", ALLOW);
        map.put("review", REVIEW);
        map.put("block", BLOCK);
        return map;
    }

    private static final Map<String, OnExpiryEnum> createEnumsMap() {
        Map<String, OnExpiryEnum> map = new HashMap<>();
        map.put("allow", OnExpiryEnum.ALLOW);
        map.put("review", OnExpiryEnum.REVIEW);
        map.put("block", OnExpiryEnum.BLOCK);
        return map;
    }

    public enum OnExpiryEnum {

        ALLOW("allow"),
        REVIEW("review"),
        BLOCK("block"),
        ;

        private final String value;

        private OnExpiryEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
