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
 * SourceId
 *
 * <p>A NoResponse source: github (GitHub accounts), phone (the phone lookup), breach (breach exposure),
 * rdap (the email domain's registration), mx (the email domain's mail servers), ipintel (IP
 * intelligence) or an email account source that searches one platform for an account by email address
 * (adobe, nvidia, wix, life360, zapier).
 */
public class SourceId {

    public static final SourceId GITHUB = new SourceId("github");
    public static final SourceId PHONE = new SourceId("phone");
    public static final SourceId BREACH = new SourceId("breach");
    public static final SourceId RDAP = new SourceId("rdap");
    public static final SourceId MX = new SourceId("mx");
    public static final SourceId IPINTEL = new SourceId("ipintel");
    public static final SourceId ADOBE = new SourceId("adobe");
    public static final SourceId NVIDIA = new SourceId("nvidia");
    public static final SourceId WIX = new SourceId("wix");
    public static final SourceId LIFE360 = new SourceId("life360");
    public static final SourceId ZAPIER = new SourceId("zapier");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, SourceId> values = createValuesMap();
    private static final Map<String, SourceIdEnum> enums = createEnumsMap();

    private final String value;

    private SourceId(String value) {
        this.value = value;
    }

    /**
     * Returns a SourceId with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as SourceId
     */
    @JsonCreator
    public static SourceId of(String value) {
        synchronized (SourceId.class) {
            return values.computeIfAbsent(value, v -> new SourceId(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<SourceIdEnum> asEnum() {
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
        SourceId other = (SourceId) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "SourceId [value=" + value + "]";
    }

    // return an array just like an enum
    public static SourceId[] values() {
        synchronized (SourceId.class) {
            return values.values().toArray(new SourceId[] {});
        }
    }

    private static final Map<String, SourceId> createValuesMap() {
        Map<String, SourceId> map = new LinkedHashMap<>();
        map.put("github", GITHUB);
        map.put("phone", PHONE);
        map.put("breach", BREACH);
        map.put("rdap", RDAP);
        map.put("mx", MX);
        map.put("ipintel", IPINTEL);
        map.put("adobe", ADOBE);
        map.put("nvidia", NVIDIA);
        map.put("wix", WIX);
        map.put("life360", LIFE360);
        map.put("zapier", ZAPIER);
        return map;
    }

    private static final Map<String, SourceIdEnum> createEnumsMap() {
        Map<String, SourceIdEnum> map = new HashMap<>();
        map.put("github", SourceIdEnum.GITHUB);
        map.put("phone", SourceIdEnum.PHONE);
        map.put("breach", SourceIdEnum.BREACH);
        map.put("rdap", SourceIdEnum.RDAP);
        map.put("mx", SourceIdEnum.MX);
        map.put("ipintel", SourceIdEnum.IPINTEL);
        map.put("adobe", SourceIdEnum.ADOBE);
        map.put("nvidia", SourceIdEnum.NVIDIA);
        map.put("wix", SourceIdEnum.WIX);
        map.put("life360", SourceIdEnum.LIFE360);
        map.put("zapier", SourceIdEnum.ZAPIER);
        return map;
    }

    public enum SourceIdEnum {

        GITHUB("github"),
        PHONE("phone"),
        BREACH("breach"),
        RDAP("rdap"),
        MX("mx"),
        IPINTEL("ipintel"),
        ADOBE("adobe"),
        NVIDIA("nvidia"),
        WIX("wix"),
        LIFE360("life360"),
        ZAPIER("zapier"),
        ;

        private final String value;

        private SourceIdEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
