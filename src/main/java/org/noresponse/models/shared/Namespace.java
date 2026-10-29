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
 * Namespace
 *
 * <p>Required for username: the username submitted by this customer in this workspace/environment. It
 * does not name or join a source platform. Omit for email and phone.
 */
public class Namespace {

    public static final Namespace CUSTOMER = new Namespace("customer");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Namespace> values = createValuesMap();
    private static final Map<String, NamespaceEnum> enums = createEnumsMap();

    private final String value;

    private Namespace(String value) {
        this.value = value;
    }

    /**
     * Returns a Namespace with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Namespace
     */
    @JsonCreator
    public static Namespace of(String value) {
        synchronized (Namespace.class) {
            return values.computeIfAbsent(value, v -> new Namespace(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<NamespaceEnum> asEnum() {
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
        Namespace other = (Namespace) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Namespace [value=" + value + "]";
    }

    // return an array just like an enum
    public static Namespace[] values() {
        synchronized (Namespace.class) {
            return values.values().toArray(new Namespace[] {});
        }
    }

    private static final Map<String, Namespace> createValuesMap() {
        Map<String, Namespace> map = new LinkedHashMap<>();
        map.put("customer", CUSTOMER);
        return map;
    }

    private static final Map<String, NamespaceEnum> createEnumsMap() {
        Map<String, NamespaceEnum> map = new HashMap<>();
        map.put("customer", NamespaceEnum.CUSTOMER);
        return map;
    }

    public enum NamespaceEnum {

        CUSTOMER("customer"),
        ;

        private final String value;

        private NamespaceEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
