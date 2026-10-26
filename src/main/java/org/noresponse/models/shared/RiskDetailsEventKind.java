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
public class RiskDetailsEventKind {

    public static final RiskDetailsEventKind CREATED = new RiskDetailsEventKind("created");
    public static final RiskDetailsEventKind LAST_ACTIVE = new RiskDetailsEventKind("last_active");
    public static final RiskDetailsEventKind BREACHED = new RiskDetailsEventKind("breached");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RiskDetailsEventKind> values = createValuesMap();
    private static final Map<String, RiskDetailsEventKindEnum> enums = createEnumsMap();

    private final String value;

    private RiskDetailsEventKind(String value) {
        this.value = value;
    }

    /**
     * Returns a RiskDetailsEventKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RiskDetailsEventKind
     */
    @JsonCreator
    public static RiskDetailsEventKind of(String value) {
        synchronized (RiskDetailsEventKind.class) {
            return values.computeIfAbsent(value, v -> new RiskDetailsEventKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RiskDetailsEventKindEnum> asEnum() {
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
        RiskDetailsEventKind other = (RiskDetailsEventKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RiskDetailsEventKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static RiskDetailsEventKind[] values() {
        synchronized (RiskDetailsEventKind.class) {
            return values.values().toArray(new RiskDetailsEventKind[] {});
        }
    }

    private static final Map<String, RiskDetailsEventKind> createValuesMap() {
        Map<String, RiskDetailsEventKind> map = new LinkedHashMap<>();
        map.put("created", CREATED);
        map.put("last_active", LAST_ACTIVE);
        map.put("breached", BREACHED);
        return map;
    }

    private static final Map<String, RiskDetailsEventKindEnum> createEnumsMap() {
        Map<String, RiskDetailsEventKindEnum> map = new HashMap<>();
        map.put("created", RiskDetailsEventKindEnum.CREATED);
        map.put("last_active", RiskDetailsEventKindEnum.LAST_ACTIVE);
        map.put("breached", RiskDetailsEventKindEnum.BREACHED);
        return map;
    }

    public enum RiskDetailsEventKindEnum {

        CREATED("created"),
        LAST_ACTIVE("last_active"),
        BREACHED("breached"),
        ;

        private final String value;

        private RiskDetailsEventKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
