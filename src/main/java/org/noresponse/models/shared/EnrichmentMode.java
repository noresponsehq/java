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
 * EnrichmentMode
 *
 * <p>off: enrichment starts no search and suggests none. suggest: enrichment starts no search by itself,
 * and the person report lists the searches it would start in suggested_searches. automatic: after a
 * workspace person's own investigation or a group's screening, and each time a search on that person
 * finishes, enrichment starts searches by itself.
 *
 * <p>A group takes suggest while the workspace has not set a mode.
 */
public class EnrichmentMode {

    public static final EnrichmentMode OFF = new EnrichmentMode("off");
    public static final EnrichmentMode SUGGEST = new EnrichmentMode("suggest");
    public static final EnrichmentMode AUTOMATIC = new EnrichmentMode("automatic");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, EnrichmentMode> values = createValuesMap();
    private static final Map<String, EnrichmentModeEnum> enums = createEnumsMap();

    private final String value;

    private EnrichmentMode(String value) {
        this.value = value;
    }

    /**
     * Returns a EnrichmentMode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as EnrichmentMode
     */
    @JsonCreator
    public static EnrichmentMode of(String value) {
        synchronized (EnrichmentMode.class) {
            return values.computeIfAbsent(value, v -> new EnrichmentMode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<EnrichmentModeEnum> asEnum() {
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
        EnrichmentMode other = (EnrichmentMode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "EnrichmentMode [value=" + value + "]";
    }

    // return an array just like an enum
    public static EnrichmentMode[] values() {
        synchronized (EnrichmentMode.class) {
            return values.values().toArray(new EnrichmentMode[] {});
        }
    }

    private static final Map<String, EnrichmentMode> createValuesMap() {
        Map<String, EnrichmentMode> map = new LinkedHashMap<>();
        map.put("off", OFF);
        map.put("suggest", SUGGEST);
        map.put("automatic", AUTOMATIC);
        return map;
    }

    private static final Map<String, EnrichmentModeEnum> createEnumsMap() {
        Map<String, EnrichmentModeEnum> map = new HashMap<>();
        map.put("off", EnrichmentModeEnum.OFF);
        map.put("suggest", EnrichmentModeEnum.SUGGEST);
        map.put("automatic", EnrichmentModeEnum.AUTOMATIC);
        return map;
    }

    public enum EnrichmentModeEnum {

        OFF("off"),
        SUGGEST("suggest"),
        AUTOMATIC("automatic"),
        ;

        private final String value;

        private EnrichmentModeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
