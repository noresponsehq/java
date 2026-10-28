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
 * ReasonCode
 *
 * <p>A stable reason code. New codes may appear; ignore any you do not know.
 */
public class ReasonCode {

    public static final ReasonCode NO_DIGITAL_FOOTPRINT = new ReasonCode("no_digital_footprint");
    public static final ReasonCode VOIP_NUMBER = new ReasonCode("voip_number");
    public static final ReasonCode DISPOSABLE_EMAIL = new ReasonCode("disposable_email");
    public static final ReasonCode NEW_EMAIL = new ReasonCode("new_email");
    public static final ReasonCode SPAM_REPORTS = new ReasonCode("spam_reports");
    public static final ReasonCode FRAUD_FLAG = new ReasonCode("fraud_flag");
    public static final ReasonCode IDENTITY_MISMATCH = new ReasonCode("identity_mismatch");
    public static final ReasonCode COUNTRY_MISMATCH = new ReasonCode("country_mismatch");
    public static final ReasonCode PROXY_IP = new ReasonCode("proxy_ip");
    public static final ReasonCode SEEN_BEFORE = new ReasonCode("seen_before");
    public static final ReasonCode SHARED_IP = new ReasonCode("shared_ip");
    public static final ReasonCode MANY_RECENT_ACCOUNTS = new ReasonCode("many_recent_accounts");
    public static final ReasonCode ESTABLISHED_ACCOUNTS = new ReasonCode("established_accounts");
    public static final ReasonCode AGED_IDENTITY = new ReasonCode("aged_identity");
    public static final ReasonCode BREACH_NAME_MATCH = new ReasonCode("breach_name_match");
    public static final ReasonCode BREACH_COOCCURRENCE = new ReasonCode("breach_cooccurrence");
    public static final ReasonCode BREACH_NAME_MISMATCH = new ReasonCode("breach_name_mismatch");
    public static final ReasonCode BREACH_PHONE_MISMATCH = new ReasonCode("breach_phone_mismatch");
    public static final ReasonCode RECENT_PASSWORD_EXPOSURE = new ReasonCode("recent_password_exposure");
    public static final ReasonCode CREDENTIAL_LIST_EXPOSURE = new ReasonCode("credential_list_exposure");
    public static final ReasonCode ABUSE_ECOSYSTEM_MEMBER = new ReasonCode("abuse_ecosystem_member");
    public static final ReasonCode NO_BREACH_HISTORY = new ReasonCode("no_breach_history");
    public static final ReasonCode AUTOMATION_FAST_SUBMIT = new ReasonCode("automation_fast_submit");
    public static final ReasonCode CLIENT_CLOCK_SKEW = new ReasonCode("client_clock_skew");
    public static final ReasonCode BOT_OR_AUTOMATION = new ReasonCode("bot_or_automation");
    public static final ReasonCode AUTH_EMAIL_VERIFIED = new ReasonCode("auth_email_verified");
    public static final ReasonCode AUTH_PHONE_VERIFIED = new ReasonCode("auth_phone_verified");
    public static final ReasonCode AUTH_MFA_RECENT = new ReasonCode("auth_mfa_recent");
    public static final ReasonCode AUTH_NEW_SESSION = new ReasonCode("auth_new_session");
    public static final ReasonCode AUTH_NEW_ACCOUNT = new ReasonCode("auth_new_account");
    public static final ReasonCode AUTH_IMPERSONATED = new ReasonCode("auth_impersonated");
    public static final ReasonCode CONTEXT_TIMEZONE_MISMATCH = new ReasonCode("context_timezone_mismatch");
    public static final ReasonCode CONTEXT_LOCALE_MISMATCH = new ReasonCode("context_locale_mismatch");
    public static final ReasonCode CONTEXT_LINE_COUNTRY_MISMATCH = new ReasonCode("context_line_country_mismatch");
    public static final ReasonCode PRIOR_ABUSE_LINK = new ReasonCode("prior_abuse_link");
    public static final ReasonCode TEST_SCENARIO = new ReasonCode("test_scenario");
    public static final ReasonCode SHARED_API_KEY = new ReasonCode("shared_api_key");
    public static final ReasonCode LIST_MATCH = new ReasonCode("list_match");
    public static final ReasonCode YOUNG_EMAIL_DOMAIN = new ReasonCode("young_email_domain");
    public static final ReasonCode EMAIL_DOMAIN_NO_MX = new ReasonCode("email_domain_no_mx");
    public static final ReasonCode EMAIL_DOMAIN_NONEXISTENT = new ReasonCode("email_domain_nonexistent");
    public static final ReasonCode FORUM_SANCTIONED = new ReasonCode("forum_sanctioned");
    public static final ReasonCode PRIOR_PAYMENT_ABUSE = new ReasonCode("prior_payment_abuse");
    public static final ReasonCode IP_HISTORY_MATCH = new ReasonCode("ip_history_match");
    public static final ReasonCode IDENTITY_ATTRIBUTE_CONFLICT = new ReasonCode("identity_attribute_conflict");
    public static final ReasonCode IMPOSSIBLE_IDENTITY_AGE = new ReasonCode("impossible_identity_age");
    public static final ReasonCode BREACH_SHARED_IP = new ReasonCode("breach_shared_ip");
    public static final ReasonCode NATIONALITY_MISMATCH = new ReasonCode("nationality_mismatch");
    public static final ReasonCode MINOR_IDENTITY = new ReasonCode("minor_identity");
    public static final ReasonCode REPORTED_ACCOUNT_ABUSE = new ReasonCode("reported_account_abuse");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ReasonCode> values = createValuesMap();
    private static final Map<String, ReasonCodeEnum> enums = createEnumsMap();

    private final String value;

    private ReasonCode(String value) {
        this.value = value;
    }

    /**
     * Returns a ReasonCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ReasonCode
     */
    @JsonCreator
    public static ReasonCode of(String value) {
        synchronized (ReasonCode.class) {
            return values.computeIfAbsent(value, v -> new ReasonCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ReasonCodeEnum> asEnum() {
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
        ReasonCode other = (ReasonCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ReasonCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static ReasonCode[] values() {
        synchronized (ReasonCode.class) {
            return values.values().toArray(new ReasonCode[] {});
        }
    }

    private static final Map<String, ReasonCode> createValuesMap() {
        Map<String, ReasonCode> map = new LinkedHashMap<>();
        map.put("no_digital_footprint", NO_DIGITAL_FOOTPRINT);
        map.put("voip_number", VOIP_NUMBER);
        map.put("disposable_email", DISPOSABLE_EMAIL);
        map.put("new_email", NEW_EMAIL);
        map.put("spam_reports", SPAM_REPORTS);
        map.put("fraud_flag", FRAUD_FLAG);
        map.put("identity_mismatch", IDENTITY_MISMATCH);
        map.put("country_mismatch", COUNTRY_MISMATCH);
        map.put("proxy_ip", PROXY_IP);
        map.put("seen_before", SEEN_BEFORE);
        map.put("shared_ip", SHARED_IP);
        map.put("many_recent_accounts", MANY_RECENT_ACCOUNTS);
        map.put("established_accounts", ESTABLISHED_ACCOUNTS);
        map.put("aged_identity", AGED_IDENTITY);
        map.put("breach_name_match", BREACH_NAME_MATCH);
        map.put("breach_cooccurrence", BREACH_COOCCURRENCE);
        map.put("breach_name_mismatch", BREACH_NAME_MISMATCH);
        map.put("breach_phone_mismatch", BREACH_PHONE_MISMATCH);
        map.put("recent_password_exposure", RECENT_PASSWORD_EXPOSURE);
        map.put("credential_list_exposure", CREDENTIAL_LIST_EXPOSURE);
        map.put("abuse_ecosystem_member", ABUSE_ECOSYSTEM_MEMBER);
        map.put("no_breach_history", NO_BREACH_HISTORY);
        map.put("automation_fast_submit", AUTOMATION_FAST_SUBMIT);
        map.put("client_clock_skew", CLIENT_CLOCK_SKEW);
        map.put("bot_or_automation", BOT_OR_AUTOMATION);
        map.put("auth_email_verified", AUTH_EMAIL_VERIFIED);
        map.put("auth_phone_verified", AUTH_PHONE_VERIFIED);
        map.put("auth_mfa_recent", AUTH_MFA_RECENT);
        map.put("auth_new_session", AUTH_NEW_SESSION);
        map.put("auth_new_account", AUTH_NEW_ACCOUNT);
        map.put("auth_impersonated", AUTH_IMPERSONATED);
        map.put("context_timezone_mismatch", CONTEXT_TIMEZONE_MISMATCH);
        map.put("context_locale_mismatch", CONTEXT_LOCALE_MISMATCH);
        map.put("context_line_country_mismatch", CONTEXT_LINE_COUNTRY_MISMATCH);
        map.put("prior_abuse_link", PRIOR_ABUSE_LINK);
        map.put("test_scenario", TEST_SCENARIO);
        map.put("shared_api_key", SHARED_API_KEY);
        map.put("list_match", LIST_MATCH);
        map.put("young_email_domain", YOUNG_EMAIL_DOMAIN);
        map.put("email_domain_no_mx", EMAIL_DOMAIN_NO_MX);
        map.put("email_domain_nonexistent", EMAIL_DOMAIN_NONEXISTENT);
        map.put("forum_sanctioned", FORUM_SANCTIONED);
        map.put("prior_payment_abuse", PRIOR_PAYMENT_ABUSE);
        map.put("ip_history_match", IP_HISTORY_MATCH);
        map.put("identity_attribute_conflict", IDENTITY_ATTRIBUTE_CONFLICT);
        map.put("impossible_identity_age", IMPOSSIBLE_IDENTITY_AGE);
        map.put("breach_shared_ip", BREACH_SHARED_IP);
        map.put("nationality_mismatch", NATIONALITY_MISMATCH);
        map.put("minor_identity", MINOR_IDENTITY);
        map.put("reported_account_abuse", REPORTED_ACCOUNT_ABUSE);
        return map;
    }

    private static final Map<String, ReasonCodeEnum> createEnumsMap() {
        Map<String, ReasonCodeEnum> map = new HashMap<>();
        map.put("no_digital_footprint", ReasonCodeEnum.NO_DIGITAL_FOOTPRINT);
        map.put("voip_number", ReasonCodeEnum.VOIP_NUMBER);
        map.put("disposable_email", ReasonCodeEnum.DISPOSABLE_EMAIL);
        map.put("new_email", ReasonCodeEnum.NEW_EMAIL);
        map.put("spam_reports", ReasonCodeEnum.SPAM_REPORTS);
        map.put("fraud_flag", ReasonCodeEnum.FRAUD_FLAG);
        map.put("identity_mismatch", ReasonCodeEnum.IDENTITY_MISMATCH);
        map.put("country_mismatch", ReasonCodeEnum.COUNTRY_MISMATCH);
        map.put("proxy_ip", ReasonCodeEnum.PROXY_IP);
        map.put("seen_before", ReasonCodeEnum.SEEN_BEFORE);
        map.put("shared_ip", ReasonCodeEnum.SHARED_IP);
        map.put("many_recent_accounts", ReasonCodeEnum.MANY_RECENT_ACCOUNTS);
        map.put("established_accounts", ReasonCodeEnum.ESTABLISHED_ACCOUNTS);
        map.put("aged_identity", ReasonCodeEnum.AGED_IDENTITY);
        map.put("breach_name_match", ReasonCodeEnum.BREACH_NAME_MATCH);
        map.put("breach_cooccurrence", ReasonCodeEnum.BREACH_COOCCURRENCE);
        map.put("breach_name_mismatch", ReasonCodeEnum.BREACH_NAME_MISMATCH);
        map.put("breach_phone_mismatch", ReasonCodeEnum.BREACH_PHONE_MISMATCH);
        map.put("recent_password_exposure", ReasonCodeEnum.RECENT_PASSWORD_EXPOSURE);
        map.put("credential_list_exposure", ReasonCodeEnum.CREDENTIAL_LIST_EXPOSURE);
        map.put("abuse_ecosystem_member", ReasonCodeEnum.ABUSE_ECOSYSTEM_MEMBER);
        map.put("no_breach_history", ReasonCodeEnum.NO_BREACH_HISTORY);
        map.put("automation_fast_submit", ReasonCodeEnum.AUTOMATION_FAST_SUBMIT);
        map.put("client_clock_skew", ReasonCodeEnum.CLIENT_CLOCK_SKEW);
        map.put("bot_or_automation", ReasonCodeEnum.BOT_OR_AUTOMATION);
        map.put("auth_email_verified", ReasonCodeEnum.AUTH_EMAIL_VERIFIED);
        map.put("auth_phone_verified", ReasonCodeEnum.AUTH_PHONE_VERIFIED);
        map.put("auth_mfa_recent", ReasonCodeEnum.AUTH_MFA_RECENT);
        map.put("auth_new_session", ReasonCodeEnum.AUTH_NEW_SESSION);
        map.put("auth_new_account", ReasonCodeEnum.AUTH_NEW_ACCOUNT);
        map.put("auth_impersonated", ReasonCodeEnum.AUTH_IMPERSONATED);
        map.put("context_timezone_mismatch", ReasonCodeEnum.CONTEXT_TIMEZONE_MISMATCH);
        map.put("context_locale_mismatch", ReasonCodeEnum.CONTEXT_LOCALE_MISMATCH);
        map.put("context_line_country_mismatch", ReasonCodeEnum.CONTEXT_LINE_COUNTRY_MISMATCH);
        map.put("prior_abuse_link", ReasonCodeEnum.PRIOR_ABUSE_LINK);
        map.put("test_scenario", ReasonCodeEnum.TEST_SCENARIO);
        map.put("shared_api_key", ReasonCodeEnum.SHARED_API_KEY);
        map.put("list_match", ReasonCodeEnum.LIST_MATCH);
        map.put("young_email_domain", ReasonCodeEnum.YOUNG_EMAIL_DOMAIN);
        map.put("email_domain_no_mx", ReasonCodeEnum.EMAIL_DOMAIN_NO_MX);
        map.put("email_domain_nonexistent", ReasonCodeEnum.EMAIL_DOMAIN_NONEXISTENT);
        map.put("forum_sanctioned", ReasonCodeEnum.FORUM_SANCTIONED);
        map.put("prior_payment_abuse", ReasonCodeEnum.PRIOR_PAYMENT_ABUSE);
        map.put("ip_history_match", ReasonCodeEnum.IP_HISTORY_MATCH);
        map.put("identity_attribute_conflict", ReasonCodeEnum.IDENTITY_ATTRIBUTE_CONFLICT);
        map.put("impossible_identity_age", ReasonCodeEnum.IMPOSSIBLE_IDENTITY_AGE);
        map.put("breach_shared_ip", ReasonCodeEnum.BREACH_SHARED_IP);
        map.put("nationality_mismatch", ReasonCodeEnum.NATIONALITY_MISMATCH);
        map.put("minor_identity", ReasonCodeEnum.MINOR_IDENTITY);
        map.put("reported_account_abuse", ReasonCodeEnum.REPORTED_ACCOUNT_ABUSE);
        return map;
    }

    public enum ReasonCodeEnum {

        NO_DIGITAL_FOOTPRINT("no_digital_footprint"),
        VOIP_NUMBER("voip_number"),
        DISPOSABLE_EMAIL("disposable_email"),
        NEW_EMAIL("new_email"),
        SPAM_REPORTS("spam_reports"),
        FRAUD_FLAG("fraud_flag"),
        IDENTITY_MISMATCH("identity_mismatch"),
        COUNTRY_MISMATCH("country_mismatch"),
        PROXY_IP("proxy_ip"),
        SEEN_BEFORE("seen_before"),
        SHARED_IP("shared_ip"),
        MANY_RECENT_ACCOUNTS("many_recent_accounts"),
        ESTABLISHED_ACCOUNTS("established_accounts"),
        AGED_IDENTITY("aged_identity"),
        BREACH_NAME_MATCH("breach_name_match"),
        BREACH_COOCCURRENCE("breach_cooccurrence"),
        BREACH_NAME_MISMATCH("breach_name_mismatch"),
        BREACH_PHONE_MISMATCH("breach_phone_mismatch"),
        RECENT_PASSWORD_EXPOSURE("recent_password_exposure"),
        CREDENTIAL_LIST_EXPOSURE("credential_list_exposure"),
        ABUSE_ECOSYSTEM_MEMBER("abuse_ecosystem_member"),
        NO_BREACH_HISTORY("no_breach_history"),
        AUTOMATION_FAST_SUBMIT("automation_fast_submit"),
        CLIENT_CLOCK_SKEW("client_clock_skew"),
        BOT_OR_AUTOMATION("bot_or_automation"),
        AUTH_EMAIL_VERIFIED("auth_email_verified"),
        AUTH_PHONE_VERIFIED("auth_phone_verified"),
        AUTH_MFA_RECENT("auth_mfa_recent"),
        AUTH_NEW_SESSION("auth_new_session"),
        AUTH_NEW_ACCOUNT("auth_new_account"),
        AUTH_IMPERSONATED("auth_impersonated"),
        CONTEXT_TIMEZONE_MISMATCH("context_timezone_mismatch"),
        CONTEXT_LOCALE_MISMATCH("context_locale_mismatch"),
        CONTEXT_LINE_COUNTRY_MISMATCH("context_line_country_mismatch"),
        PRIOR_ABUSE_LINK("prior_abuse_link"),
        TEST_SCENARIO("test_scenario"),
        SHARED_API_KEY("shared_api_key"),
        LIST_MATCH("list_match"),
        YOUNG_EMAIL_DOMAIN("young_email_domain"),
        EMAIL_DOMAIN_NO_MX("email_domain_no_mx"),
        EMAIL_DOMAIN_NONEXISTENT("email_domain_nonexistent"),
        FORUM_SANCTIONED("forum_sanctioned"),
        PRIOR_PAYMENT_ABUSE("prior_payment_abuse"),
        IP_HISTORY_MATCH("ip_history_match"),
        IDENTITY_ATTRIBUTE_CONFLICT("identity_attribute_conflict"),
        IMPOSSIBLE_IDENTITY_AGE("impossible_identity_age"),
        BREACH_SHARED_IP("breach_shared_ip"),
        NATIONALITY_MISMATCH("nationality_mismatch"),
        MINOR_IDENTITY("minor_identity"),
        REPORTED_ACCOUNT_ABUSE("reported_account_abuse"),
        ;

        private final String value;

        private ReasonCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
