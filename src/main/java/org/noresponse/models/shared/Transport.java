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
 * Transport
 *
 * <p>Where the context was derived: backend (also when the request named no transport), browser, or
 * provider_hook for a hosted provider hook.
 */
public class Transport {

    public static final Transport BACKEND = new Transport("backend");
    public static final Transport BROWSER = new Transport("browser");
    public static final Transport PROVIDER_HOOK = new Transport("provider_hook");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Transport> values = createValuesMap();
    private static final Map<String, TransportEnum> enums = createEnumsMap();

    private final String value;

    private Transport(String value) {
        this.value = value;
    }

    /**
     * Returns a Transport with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Transport
     */
    @JsonCreator
    public static Transport of(String value) {
        synchronized (Transport.class) {
            return values.computeIfAbsent(value, v -> new Transport(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<TransportEnum> asEnum() {
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
        Transport other = (Transport) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Transport [value=" + value + "]";
    }

    // return an array just like an enum
    public static Transport[] values() {
        synchronized (Transport.class) {
            return values.values().toArray(new Transport[] {});
        }
    }

    private static final Map<String, Transport> createValuesMap() {
        Map<String, Transport> map = new LinkedHashMap<>();
        map.put("backend", BACKEND);
        map.put("browser", BROWSER);
        map.put("provider_hook", PROVIDER_HOOK);
        return map;
    }

    private static final Map<String, TransportEnum> createEnumsMap() {
        Map<String, TransportEnum> map = new HashMap<>();
        map.put("backend", TransportEnum.BACKEND);
        map.put("browser", TransportEnum.BROWSER);
        map.put("provider_hook", TransportEnum.PROVIDER_HOOK);
        return map;
    }

    public enum TransportEnum {

        BACKEND("backend"),
        BROWSER("browser"),
        PROVIDER_HOOK("provider_hook"),
        ;

        private final String value;

        private TransportEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
