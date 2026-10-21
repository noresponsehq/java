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
 * BreachKind
 *
 * <p>Who is in the table: site_breach, a site's own users; credential_list, a combolist or compilation;
 * stealer_log, malware or botnet victims; scrape; hacking_forum, a criminal hacking, cracking or
 * carding community; abuse_service, the customers of DDoS-for-hire, account generators, dump or card
 * shops, darknet markets, stalkerware or breach-lookup services; darkweb_site, an onion site of
 * unknown purpose.
 */
public class BreachKind {

    public static final BreachKind SITE_BREACH = new BreachKind("site_breach");
    public static final BreachKind CREDENTIAL_LIST = new BreachKind("credential_list");
    public static final BreachKind STEALER_LOG = new BreachKind("stealer_log");
    public static final BreachKind SCRAPE = new BreachKind("scrape");
    public static final BreachKind HACKING_FORUM = new BreachKind("hacking_forum");
    public static final BreachKind ABUSE_SERVICE = new BreachKind("abuse_service");
    public static final BreachKind DARKWEB_SITE = new BreachKind("darkweb_site");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, BreachKind> values = createValuesMap();
    private static final Map<String, BreachKindEnum> enums = createEnumsMap();

    private final String value;

    private BreachKind(String value) {
        this.value = value;
    }

    /**
     * Returns a BreachKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as BreachKind
     */
    @JsonCreator
    public static BreachKind of(String value) {
        synchronized (BreachKind.class) {
            return values.computeIfAbsent(value, v -> new BreachKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<BreachKindEnum> asEnum() {
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
        BreachKind other = (BreachKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "BreachKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static BreachKind[] values() {
        synchronized (BreachKind.class) {
            return values.values().toArray(new BreachKind[] {});
        }
    }

    private static final Map<String, BreachKind> createValuesMap() {
        Map<String, BreachKind> map = new LinkedHashMap<>();
        map.put("site_breach", SITE_BREACH);
        map.put("credential_list", CREDENTIAL_LIST);
        map.put("stealer_log", STEALER_LOG);
        map.put("scrape", SCRAPE);
        map.put("hacking_forum", HACKING_FORUM);
        map.put("abuse_service", ABUSE_SERVICE);
        map.put("darkweb_site", DARKWEB_SITE);
        return map;
    }

    private static final Map<String, BreachKindEnum> createEnumsMap() {
        Map<String, BreachKindEnum> map = new HashMap<>();
        map.put("site_breach", BreachKindEnum.SITE_BREACH);
        map.put("credential_list", BreachKindEnum.CREDENTIAL_LIST);
        map.put("stealer_log", BreachKindEnum.STEALER_LOG);
        map.put("scrape", BreachKindEnum.SCRAPE);
        map.put("hacking_forum", BreachKindEnum.HACKING_FORUM);
        map.put("abuse_service", BreachKindEnum.ABUSE_SERVICE);
        map.put("darkweb_site", BreachKindEnum.DARKWEB_SITE);
        return map;
    }

    public enum BreachKindEnum {

        SITE_BREACH("site_breach"),
        CREDENTIAL_LIST("credential_list"),
        STEALER_LOG("stealer_log"),
        SCRAPE("scrape"),
        HACKING_FORUM("hacking_forum"),
        ABUSE_SERVICE("abuse_service"),
        DARKWEB_SITE("darkweb_site"),
        ;

        private final String value;

        private BreachKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
