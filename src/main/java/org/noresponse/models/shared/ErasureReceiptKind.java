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
public class ErasureReceiptKind {

    public static final ErasureReceiptKind ASSESSMENT = new ErasureReceiptKind("assessment");
    public static final ErasureReceiptKind ACCOUNT = new ErasureReceiptKind("account");
    public static final ErasureReceiptKind WORKSPACE = new ErasureReceiptKind("workspace");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ErasureReceiptKind> values = createValuesMap();
    private static final Map<String, ErasureReceiptKindEnum> enums = createEnumsMap();

    private final String value;

    private ErasureReceiptKind(String value) {
        this.value = value;
    }

    /**
     * Returns a ErasureReceiptKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ErasureReceiptKind
     */
    @JsonCreator
    public static ErasureReceiptKind of(String value) {
        synchronized (ErasureReceiptKind.class) {
            return values.computeIfAbsent(value, v -> new ErasureReceiptKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ErasureReceiptKindEnum> asEnum() {
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
        ErasureReceiptKind other = (ErasureReceiptKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ErasureReceiptKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static ErasureReceiptKind[] values() {
        synchronized (ErasureReceiptKind.class) {
            return values.values().toArray(new ErasureReceiptKind[] {});
        }
    }

    private static final Map<String, ErasureReceiptKind> createValuesMap() {
        Map<String, ErasureReceiptKind> map = new LinkedHashMap<>();
        map.put("assessment", ASSESSMENT);
        map.put("account", ACCOUNT);
        map.put("workspace", WORKSPACE);
        return map;
    }

    private static final Map<String, ErasureReceiptKindEnum> createEnumsMap() {
        Map<String, ErasureReceiptKindEnum> map = new HashMap<>();
        map.put("assessment", ErasureReceiptKindEnum.ASSESSMENT);
        map.put("account", ErasureReceiptKindEnum.ACCOUNT);
        map.put("workspace", ErasureReceiptKindEnum.WORKSPACE);
        return map;
    }

    public enum ErasureReceiptKindEnum {

        ASSESSMENT("assessment"),
        ACCOUNT("account"),
        WORKSPACE("workspace"),
        ;

        private final String value;

        private ErasureReceiptKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
