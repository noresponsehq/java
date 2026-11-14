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
 * RecordKind
 *
 * <p>What sort of value a field holds, which decides how it is cleaned, compared and searched again. It
 * comes from the value itself where its shape is certain (an email, an IP, a URL, an international
 * phone number), else from the answer stored for the source's column, else text. A value of any kind
 * is returned.
 *
 * <p>Each kind's description says what it holds and the form value takes.
 */
public class RecordKind {

    public static final RecordKind EMAIL = new RecordKind("email");
    public static final RecordKind PHONE = new RecordKind("phone");
    public static final RecordKind USERNAME = new RecordKind("username");
    public static final RecordKind ACCOUNT_ID = new RecordKind("account_id");
    public static final RecordKind URL = new RecordKind("url");
    public static final RecordKind DOMAIN = new RecordKind("domain");
    public static final RecordKind IMAGE = new RecordKind("image");
    public static final RecordKind PERSON_NAME = new RecordKind("person_name");
    public static final RecordKind GENDER = new RecordKind("gender");
    public static final RecordKind AGE = new RecordKind("age");
    public static final RecordKind LANGUAGE = new RecordKind("language");
    public static final RecordKind ORGANISATION = new RecordKind("organisation");
    public static final RecordKind JOB_TITLE = new RecordKind("job_title");
    public static final RecordKind COUNTRY = new RecordKind("country");
    public static final RecordKind REGION = new RecordKind("region");
    public static final RecordKind CITY = new RecordKind("city");
    public static final RecordKind ADDRESS = new RecordKind("address");
    public static final RecordKind POSTCODE = new RecordKind("postcode");
    public static final RecordKind COORDINATES = new RecordKind("coordinates");
    public static final RecordKind TIMEZONE = new RecordKind("timezone");
    public static final RecordKind DATE = new RecordKind("date");
    public static final RecordKind DATE_TIME = new RecordKind("date_time");
    public static final RecordKind DURATION = new RecordKind("duration");
    public static final RecordKind IP = new RecordKind("ip");
    public static final RecordKind MAC_ADDRESS = new RecordKind("mac_address");
    public static final RecordKind DEVICE_ID = new RecordKind("device_id");
    public static final RecordKind USER_AGENT = new RecordKind("user_agent");
    public static final RecordKind PASSWORD = new RecordKind("password");
    public static final RecordKind HASH = new RecordKind("hash");
    public static final RecordKind SALT = new RecordKind("salt");
    public static final RecordKind TOKEN = new RecordKind("token");
    public static final RecordKind SECURITY_ANSWER = new RecordKind("security_answer");
    public static final RecordKind CARD_NUMBER = new RecordKind("card_number");
    public static final RecordKind BANK_ACCOUNT = new RecordKind("bank_account");
    public static final RecordKind CRYPTO_ADDRESS = new RecordKind("crypto_address");
    public static final RecordKind AMOUNT = new RecordKind("amount");
    public static final RecordKind ID_NUMBER = new RecordKind("id_number");
    public static final RecordKind VEHICLE = new RecordKind("vehicle");
    public static final RecordKind LABEL = new RecordKind("label");
    public static final RecordKind LONG_TEXT = new RecordKind("long_text");
    public static final RecordKind COUNT = new RecordKind("count");
    public static final RecordKind SCORE = new RecordKind("score");
    public static final RecordKind FLAG = new RecordKind("flag");
    public static final RecordKind STRUCTURED = new RecordKind("structured");
    public static final RecordKind TEXT = new RecordKind("text");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RecordKind> values = createValuesMap();
    private static final Map<String, RecordKindEnum> enums = createEnumsMap();

    private final String value;

    private RecordKind(String value) {
        this.value = value;
    }

    /**
     * Returns a RecordKind with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RecordKind
     */
    @JsonCreator
    public static RecordKind of(String value) {
        synchronized (RecordKind.class) {
            return values.computeIfAbsent(value, v -> new RecordKind(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RecordKindEnum> asEnum() {
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
        RecordKind other = (RecordKind) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RecordKind [value=" + value + "]";
    }

    // return an array just like an enum
    public static RecordKind[] values() {
        synchronized (RecordKind.class) {
            return values.values().toArray(new RecordKind[] {});
        }
    }

    private static final Map<String, RecordKind> createValuesMap() {
        Map<String, RecordKind> map = new LinkedHashMap<>();
        map.put("email", EMAIL);
        map.put("phone", PHONE);
        map.put("username", USERNAME);
        map.put("account_id", ACCOUNT_ID);
        map.put("url", URL);
        map.put("domain", DOMAIN);
        map.put("image", IMAGE);
        map.put("person_name", PERSON_NAME);
        map.put("gender", GENDER);
        map.put("age", AGE);
        map.put("language", LANGUAGE);
        map.put("organisation", ORGANISATION);
        map.put("job_title", JOB_TITLE);
        map.put("country", COUNTRY);
        map.put("region", REGION);
        map.put("city", CITY);
        map.put("address", ADDRESS);
        map.put("postcode", POSTCODE);
        map.put("coordinates", COORDINATES);
        map.put("timezone", TIMEZONE);
        map.put("date", DATE);
        map.put("date_time", DATE_TIME);
        map.put("duration", DURATION);
        map.put("ip", IP);
        map.put("mac_address", MAC_ADDRESS);
        map.put("device_id", DEVICE_ID);
        map.put("user_agent", USER_AGENT);
        map.put("password", PASSWORD);
        map.put("hash", HASH);
        map.put("salt", SALT);
        map.put("token", TOKEN);
        map.put("security_answer", SECURITY_ANSWER);
        map.put("card_number", CARD_NUMBER);
        map.put("bank_account", BANK_ACCOUNT);
        map.put("crypto_address", CRYPTO_ADDRESS);
        map.put("amount", AMOUNT);
        map.put("id_number", ID_NUMBER);
        map.put("vehicle", VEHICLE);
        map.put("label", LABEL);
        map.put("long_text", LONG_TEXT);
        map.put("count", COUNT);
        map.put("score", SCORE);
        map.put("flag", FLAG);
        map.put("structured", STRUCTURED);
        map.put("text", TEXT);
        return map;
    }

    private static final Map<String, RecordKindEnum> createEnumsMap() {
        Map<String, RecordKindEnum> map = new HashMap<>();
        map.put("email", RecordKindEnum.EMAIL);
        map.put("phone", RecordKindEnum.PHONE);
        map.put("username", RecordKindEnum.USERNAME);
        map.put("account_id", RecordKindEnum.ACCOUNT_ID);
        map.put("url", RecordKindEnum.URL);
        map.put("domain", RecordKindEnum.DOMAIN);
        map.put("image", RecordKindEnum.IMAGE);
        map.put("person_name", RecordKindEnum.PERSON_NAME);
        map.put("gender", RecordKindEnum.GENDER);
        map.put("age", RecordKindEnum.AGE);
        map.put("language", RecordKindEnum.LANGUAGE);
        map.put("organisation", RecordKindEnum.ORGANISATION);
        map.put("job_title", RecordKindEnum.JOB_TITLE);
        map.put("country", RecordKindEnum.COUNTRY);
        map.put("region", RecordKindEnum.REGION);
        map.put("city", RecordKindEnum.CITY);
        map.put("address", RecordKindEnum.ADDRESS);
        map.put("postcode", RecordKindEnum.POSTCODE);
        map.put("coordinates", RecordKindEnum.COORDINATES);
        map.put("timezone", RecordKindEnum.TIMEZONE);
        map.put("date", RecordKindEnum.DATE);
        map.put("date_time", RecordKindEnum.DATE_TIME);
        map.put("duration", RecordKindEnum.DURATION);
        map.put("ip", RecordKindEnum.IP);
        map.put("mac_address", RecordKindEnum.MAC_ADDRESS);
        map.put("device_id", RecordKindEnum.DEVICE_ID);
        map.put("user_agent", RecordKindEnum.USER_AGENT);
        map.put("password", RecordKindEnum.PASSWORD);
        map.put("hash", RecordKindEnum.HASH);
        map.put("salt", RecordKindEnum.SALT);
        map.put("token", RecordKindEnum.TOKEN);
        map.put("security_answer", RecordKindEnum.SECURITY_ANSWER);
        map.put("card_number", RecordKindEnum.CARD_NUMBER);
        map.put("bank_account", RecordKindEnum.BANK_ACCOUNT);
        map.put("crypto_address", RecordKindEnum.CRYPTO_ADDRESS);
        map.put("amount", RecordKindEnum.AMOUNT);
        map.put("id_number", RecordKindEnum.ID_NUMBER);
        map.put("vehicle", RecordKindEnum.VEHICLE);
        map.put("label", RecordKindEnum.LABEL);
        map.put("long_text", RecordKindEnum.LONG_TEXT);
        map.put("count", RecordKindEnum.COUNT);
        map.put("score", RecordKindEnum.SCORE);
        map.put("flag", RecordKindEnum.FLAG);
        map.put("structured", RecordKindEnum.STRUCTURED);
        map.put("text", RecordKindEnum.TEXT);
        return map;
    }

    public enum RecordKindEnum {

        EMAIL("email"),
        PHONE("phone"),
        USERNAME("username"),
        ACCOUNT_ID("account_id"),
        URL("url"),
        DOMAIN("domain"),
        IMAGE("image"),
        PERSON_NAME("person_name"),
        GENDER("gender"),
        AGE("age"),
        LANGUAGE("language"),
        ORGANISATION("organisation"),
        JOB_TITLE("job_title"),
        COUNTRY("country"),
        REGION("region"),
        CITY("city"),
        ADDRESS("address"),
        POSTCODE("postcode"),
        COORDINATES("coordinates"),
        TIMEZONE("timezone"),
        DATE("date"),
        DATE_TIME("date_time"),
        DURATION("duration"),
        IP("ip"),
        MAC_ADDRESS("mac_address"),
        DEVICE_ID("device_id"),
        USER_AGENT("user_agent"),
        PASSWORD("password"),
        HASH("hash"),
        SALT("salt"),
        TOKEN("token"),
        SECURITY_ANSWER("security_answer"),
        CARD_NUMBER("card_number"),
        BANK_ACCOUNT("bank_account"),
        CRYPTO_ADDRESS("crypto_address"),
        AMOUNT("amount"),
        ID_NUMBER("id_number"),
        VEHICLE("vehicle"),
        LABEL("label"),
        LONG_TEXT("long_text"),
        COUNT("count"),
        SCORE("score"),
        FLAG("flag"),
        STRUCTURED("structured"),
        TEXT("text"),
        ;

        private final String value;

        private RecordKindEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
