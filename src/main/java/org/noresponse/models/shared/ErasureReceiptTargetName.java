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
public class ErasureReceiptTargetName {

    public static final ErasureReceiptTargetName SERVING = new ErasureReceiptTargetName("serving");
    public static final ErasureReceiptTargetName OUTBOX = new ErasureReceiptTargetName("outbox");
    public static final ErasureReceiptTargetName CACHE = new ErasureReceiptTargetName("cache");
    public static final ErasureReceiptTargetName DOWNSTREAM_PROJECTION =
            new ErasureReceiptTargetName("downstream_projection");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ErasureReceiptTargetName> values = createValuesMap();
    private static final Map<String, ErasureReceiptTargetNameEnum> enums = createEnumsMap();

    private final String value;

    private ErasureReceiptTargetName(String value) {
        this.value = value;
    }

    /**
     * Returns a ErasureReceiptTargetName with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ErasureReceiptTargetName
     */
    @JsonCreator
    public static ErasureReceiptTargetName of(String value) {
        synchronized (ErasureReceiptTargetName.class) {
            return values.computeIfAbsent(value, v -> new ErasureReceiptTargetName(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ErasureReceiptTargetNameEnum> asEnum() {
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
        ErasureReceiptTargetName other = (ErasureReceiptTargetName) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ErasureReceiptTargetName [value=" + value + "]";
    }

    // return an array just like an enum
    public static ErasureReceiptTargetName[] values() {
        synchronized (ErasureReceiptTargetName.class) {
            return values.values().toArray(new ErasureReceiptTargetName[] {});
        }
    }

    private static final Map<String, ErasureReceiptTargetName> createValuesMap() {
        Map<String, ErasureReceiptTargetName> map = new LinkedHashMap<>();
        map.put("serving", SERVING);
        map.put("outbox", OUTBOX);
        map.put("cache", CACHE);
        map.put("downstream_projection", DOWNSTREAM_PROJECTION);
        return map;
    }

    private static final Map<String, ErasureReceiptTargetNameEnum> createEnumsMap() {
        Map<String, ErasureReceiptTargetNameEnum> map = new HashMap<>();
        map.put("serving", ErasureReceiptTargetNameEnum.SERVING);
        map.put("outbox", ErasureReceiptTargetNameEnum.OUTBOX);
        map.put("cache", ErasureReceiptTargetNameEnum.CACHE);
        map.put("downstream_projection", ErasureReceiptTargetNameEnum.DOWNSTREAM_PROJECTION);
        return map;
    }

    public enum ErasureReceiptTargetNameEnum {

        SERVING("serving"),
        OUTBOX("outbox"),
        CACHE("cache"),
        DOWNSTREAM_PROJECTION("downstream_projection"),
        ;

        private final String value;

        private ErasureReceiptTargetNameEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
