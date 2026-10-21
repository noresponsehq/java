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
 * IpSummaryType
 *
 * <p>The address's network type.
 */
public class IpSummaryType {

    public static final IpSummaryType RESIDENTIAL = new IpSummaryType("residential");
    public static final IpSummaryType HOSTING = new IpSummaryType("hosting");
    public static final IpSummaryType VPN = new IpSummaryType("vpn");
    public static final IpSummaryType PROXY = new IpSummaryType("proxy");
    public static final IpSummaryType TOR = new IpSummaryType("tor");
    public static final IpSummaryType MOBILE = new IpSummaryType("mobile");
    public static final IpSummaryType UNKNOWN = new IpSummaryType("unknown");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, IpSummaryType> values = createValuesMap();
    private static final Map<String, IpSummaryTypeEnum> enums = createEnumsMap();

    private final String value;

    private IpSummaryType(String value) {
        this.value = value;
    }

    /**
     * Returns a IpSummaryType with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as IpSummaryType
     */
    @JsonCreator
    public static IpSummaryType of(String value) {
        synchronized (IpSummaryType.class) {
            return values.computeIfAbsent(value, v -> new IpSummaryType(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<IpSummaryTypeEnum> asEnum() {
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
        IpSummaryType other = (IpSummaryType) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "IpSummaryType [value=" + value + "]";
    }

    // return an array just like an enum
    public static IpSummaryType[] values() {
        synchronized (IpSummaryType.class) {
            return values.values().toArray(new IpSummaryType[] {});
        }
    }

    private static final Map<String, IpSummaryType> createValuesMap() {
        Map<String, IpSummaryType> map = new LinkedHashMap<>();
        map.put("residential", RESIDENTIAL);
        map.put("hosting", HOSTING);
        map.put("vpn", VPN);
        map.put("proxy", PROXY);
        map.put("tor", TOR);
        map.put("mobile", MOBILE);
        map.put("unknown", UNKNOWN);
        return map;
    }

    private static final Map<String, IpSummaryTypeEnum> createEnumsMap() {
        Map<String, IpSummaryTypeEnum> map = new HashMap<>();
        map.put("residential", IpSummaryTypeEnum.RESIDENTIAL);
        map.put("hosting", IpSummaryTypeEnum.HOSTING);
        map.put("vpn", IpSummaryTypeEnum.VPN);
        map.put("proxy", IpSummaryTypeEnum.PROXY);
        map.put("tor", IpSummaryTypeEnum.TOR);
        map.put("mobile", IpSummaryTypeEnum.MOBILE);
        map.put("unknown", IpSummaryTypeEnum.UNKNOWN);
        return map;
    }

    public enum IpSummaryTypeEnum {

        RESIDENTIAL("residential"),
        HOSTING("hosting"),
        VPN("vpn"),
        PROXY("proxy"),
        TOR("tor"),
        MOBILE("mobile"),
        UNKNOWN("unknown"),
        ;

        private final String value;

        private IpSummaryTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
