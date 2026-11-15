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
 * (adobe, nvidia, wix, life360, zapier, google, duolingo_email). google runs only for Gmail addresses.
 * scratch, dailymotion, substack, duolingo, dockerhub, wikipedia, internetarchive, telegram, spotify,
 * patreon, wordpress, okru and flickr each search that platform for an account by username; telegram
 * also searches by phone number, through a logged-in Telegram lookup account.
 *
 * <p>whatsapp searches WhatsApp for an account by phone number. youtube searches YouTube for a channel by
 * username.
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
    public static final SourceId GOOGLE = new SourceId("google");
    public static final SourceId SCRATCH = new SourceId("scratch");
    public static final SourceId DAILYMOTION = new SourceId("dailymotion");
    public static final SourceId SUBSTACK = new SourceId("substack");
    public static final SourceId DUOLINGO = new SourceId("duolingo");
    public static final SourceId DUOLINGO_EMAIL = new SourceId("duolingo_email");
    public static final SourceId DOCKERHUB = new SourceId("dockerhub");
    public static final SourceId WIKIPEDIA = new SourceId("wikipedia");
    public static final SourceId INTERNETARCHIVE = new SourceId("internetarchive");
    public static final SourceId TELEGRAM = new SourceId("telegram");
    public static final SourceId YOUTUBE = new SourceId("youtube");
    public static final SourceId SPOTIFY = new SourceId("spotify");
    public static final SourceId PATREON = new SourceId("patreon");
    public static final SourceId WORDPRESS = new SourceId("wordpress");
    public static final SourceId OKRU = new SourceId("okru");
    public static final SourceId FLICKR = new SourceId("flickr");
    public static final SourceId WHATSAPP = new SourceId("whatsapp");

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
        map.put("google", GOOGLE);
        map.put("scratch", SCRATCH);
        map.put("dailymotion", DAILYMOTION);
        map.put("substack", SUBSTACK);
        map.put("duolingo", DUOLINGO);
        map.put("duolingo_email", DUOLINGO_EMAIL);
        map.put("dockerhub", DOCKERHUB);
        map.put("wikipedia", WIKIPEDIA);
        map.put("internetarchive", INTERNETARCHIVE);
        map.put("telegram", TELEGRAM);
        map.put("youtube", YOUTUBE);
        map.put("spotify", SPOTIFY);
        map.put("patreon", PATREON);
        map.put("wordpress", WORDPRESS);
        map.put("okru", OKRU);
        map.put("flickr", FLICKR);
        map.put("whatsapp", WHATSAPP);
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
        map.put("google", SourceIdEnum.GOOGLE);
        map.put("scratch", SourceIdEnum.SCRATCH);
        map.put("dailymotion", SourceIdEnum.DAILYMOTION);
        map.put("substack", SourceIdEnum.SUBSTACK);
        map.put("duolingo", SourceIdEnum.DUOLINGO);
        map.put("duolingo_email", SourceIdEnum.DUOLINGO_EMAIL);
        map.put("dockerhub", SourceIdEnum.DOCKERHUB);
        map.put("wikipedia", SourceIdEnum.WIKIPEDIA);
        map.put("internetarchive", SourceIdEnum.INTERNETARCHIVE);
        map.put("telegram", SourceIdEnum.TELEGRAM);
        map.put("youtube", SourceIdEnum.YOUTUBE);
        map.put("spotify", SourceIdEnum.SPOTIFY);
        map.put("patreon", SourceIdEnum.PATREON);
        map.put("wordpress", SourceIdEnum.WORDPRESS);
        map.put("okru", SourceIdEnum.OKRU);
        map.put("flickr", SourceIdEnum.FLICKR);
        map.put("whatsapp", SourceIdEnum.WHATSAPP);
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
        GOOGLE("google"),
        SCRATCH("scratch"),
        DAILYMOTION("dailymotion"),
        SUBSTACK("substack"),
        DUOLINGO("duolingo"),
        DUOLINGO_EMAIL("duolingo_email"),
        DOCKERHUB("dockerhub"),
        WIKIPEDIA("wikipedia"),
        INTERNETARCHIVE("internetarchive"),
        TELEGRAM("telegram"),
        YOUTUBE("youtube"),
        SPOTIFY("spotify"),
        PATREON("patreon"),
        WORDPRESS("wordpress"),
        OKRU("okru"),
        FLICKR("flickr"),
        WHATSAPP("whatsapp"),
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
