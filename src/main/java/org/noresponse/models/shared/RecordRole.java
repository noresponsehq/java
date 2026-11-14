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
 * RecordRole
 *
 * <p>Which common field a value is, where many sources share it: a join date and a birth date are both
 * dates, with the roles joined and born. A platform account's field and a breach row's column with the
 * same meaning carry the same kind and role. A linked_account field is named by its network.
 *
 * <p>Absent for a field only its source returns. Each role's description names the kinds its values have.
 */
public class RecordRole {

    public static final RecordRole ACCOUNT_ID = new RecordRole("account_id");
    public static final RecordRole ACCOUNT_STATUS = new RecordRole("account_status");
    public static final RecordRole ACCOUNT_TYPE = new RecordRole("account_type");
    public static final RecordRole ALTERNATE_PHONE = new RecordRole("alternate_phone");
    public static final RecordRole BALANCE = new RecordRole("balance");
    public static final RecordRole BANNED = new RecordRole("banned");
    public static final RecordRole BIO = new RecordRole("bio");
    public static final RecordRole BORN = new RecordRole("born");
    public static final RecordRole BOT = new RecordRole("bot");
    public static final RecordRole CALLER_CATEGORY = new RecordRole("caller_category");
    public static final RecordRole CHARGEBACKS = new RecordRole("chargebacks");
    public static final RecordRole COVER_PICTURE = new RecordRole("cover_picture");
    public static final RecordRole DECLINES = new RecordRole("declines");
    public static final RecordRole DELETED = new RecordRole("deleted");
    public static final RecordRole DELETED_ON = new RecordRole("deleted_on");
    public static final RecordRole DEPARTMENT = new RecordRole("department");
    public static final RecordRole DISPLAY_NAME = new RecordRole("display_name");
    public static final RecordRole EMAIL_VERIFIED = new RecordRole("email_verified");
    public static final RecordRole EXPIRES = new RecordRole("expires");
    public static final RecordRole FIRST_NAME = new RecordRole("first_name");
    public static final RecordRole FOLLOWERS = new RecordRole("followers");
    public static final RecordRole FOLLOWING = new RecordRole("following");
    public static final RecordRole FRIENDS = new RecordRole("friends");
    public static final RecordRole FULL_ADDRESS = new RecordRole("full_address");
    public static final RecordRole FULL_NAME = new RecordRole("full_name");
    public static final RecordRole HANDLE = new RecordRole("handle");
    public static final RecordRole HEADLINE = new RecordRole("headline");
    public static final RecordRole INTERESTS = new RecordRole("interests");
    public static final RecordRole JOB_TITLE = new RecordRole("job_title");
    public static final RecordRole JOINED = new RecordRole("joined");
    public static final RecordRole LAST_ACTIVE = new RecordRole("last_active");
    public static final RecordRole LAST_IP = new RecordRole("last_ip");
    public static final RecordRole LAST_NAME = new RecordRole("last_name");
    public static final RecordRole LAST_POST = new RecordRole("last_post");
    public static final RecordRole LAST_UPDATED = new RecordRole("last_updated");
    public static final RecordRole LEVEL = new RecordRole("level");
    public static final RecordRole LIKES_GIVEN = new RecordRole("likes_given");
    public static final RecordRole LIKES_RECEIVED = new RecordRole("likes_received");
    public static final RecordRole LINE_TYPE = new RecordRole("line_type");
    public static final RecordRole LINKED_ACCOUNT = new RecordRole("linked_account");
    public static final RecordRole LOCATION = new RecordRole("location");
    public static final RecordRole MARKED_SPAM = new RecordRole("marked_spam");
    public static final RecordRole MIDDLE_NAME = new RecordRole("middle_name");
    public static final RecordRole NATIONALITY = new RecordRole("nationality");
    public static final RecordRole ORGANISATION = new RecordRole("organisation");
    public static final RecordRole OTHER_IP = new RecordRole("other_ip");
    public static final RecordRole PAID = new RecordRole("paid");
    public static final RecordRole PASSWORD_CHANGED = new RecordRole("password_changed");
    public static final RecordRole PAYMENT_METHOD = new RecordRole("payment_method");
    public static final RecordRole PICTURE = new RecordRole("picture");
    public static final RecordRole POINTS = new RecordRole("points");
    public static final RecordRole POSTS = new RecordRole("posts");
    public static final RecordRole PRIVATE = new RecordRole("private");
    public static final RecordRole PROFILE_LINK = new RecordRole("profile_link");
    public static final RecordRole PROFILE_VIEWS = new RecordRole("profile_views");
    public static final RecordRole RATING = new RecordRole("rating");
    public static final RecordRole REFERRALS = new RecordRole("referrals");
    public static final RecordRole REFERRER = new RecordRole("referrer");
    public static final RecordRole REPORT_COUNT = new RecordRole("report_count");
    public static final RecordRole REPUTATION = new RecordRole("reputation");
    public static final RecordRole REPUTATION_LABEL = new RecordRole("reputation_label");
    public static final RecordRole RESIDENCE = new RecordRole("residence");
    public static final RecordRole SALUTATION = new RecordRole("salutation");
    public static final RecordRole SCHOOL = new RecordRole("school");
    public static final RecordRole SIGNATURE = new RecordRole("signature");
    public static final RecordRole SIGNUP_IP = new RecordRole("signup_ip");
    public static final RecordRole SITE_ROLE = new RecordRole("site_role");
    public static final RecordRole SPAM_SCORE = new RecordRole("spam_score");
    public static final RecordRole STREET = new RecordRole("street");
    public static final RecordRole TRADES = new RecordRole("trades");
    public static final RecordRole VERIFIED = new RecordRole("verified");
    public static final RecordRole WARNINGS = new RecordRole("warnings");
    public static final RecordRole WEBSITE = new RecordRole("website");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, RecordRole> values = createValuesMap();
    private static final Map<String, RecordRoleEnum> enums = createEnumsMap();

    private final String value;

    private RecordRole(String value) {
        this.value = value;
    }

    /**
     * Returns a RecordRole with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as RecordRole
     */
    @JsonCreator
    public static RecordRole of(String value) {
        synchronized (RecordRole.class) {
            return values.computeIfAbsent(value, v -> new RecordRole(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<RecordRoleEnum> asEnum() {
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
        RecordRole other = (RecordRole) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "RecordRole [value=" + value + "]";
    }

    // return an array just like an enum
    public static RecordRole[] values() {
        synchronized (RecordRole.class) {
            return values.values().toArray(new RecordRole[] {});
        }
    }

    private static final Map<String, RecordRole> createValuesMap() {
        Map<String, RecordRole> map = new LinkedHashMap<>();
        map.put("account_id", ACCOUNT_ID);
        map.put("account_status", ACCOUNT_STATUS);
        map.put("account_type", ACCOUNT_TYPE);
        map.put("alternate_phone", ALTERNATE_PHONE);
        map.put("balance", BALANCE);
        map.put("banned", BANNED);
        map.put("bio", BIO);
        map.put("born", BORN);
        map.put("bot", BOT);
        map.put("caller_category", CALLER_CATEGORY);
        map.put("chargebacks", CHARGEBACKS);
        map.put("cover_picture", COVER_PICTURE);
        map.put("declines", DECLINES);
        map.put("deleted", DELETED);
        map.put("deleted_on", DELETED_ON);
        map.put("department", DEPARTMENT);
        map.put("display_name", DISPLAY_NAME);
        map.put("email_verified", EMAIL_VERIFIED);
        map.put("expires", EXPIRES);
        map.put("first_name", FIRST_NAME);
        map.put("followers", FOLLOWERS);
        map.put("following", FOLLOWING);
        map.put("friends", FRIENDS);
        map.put("full_address", FULL_ADDRESS);
        map.put("full_name", FULL_NAME);
        map.put("handle", HANDLE);
        map.put("headline", HEADLINE);
        map.put("interests", INTERESTS);
        map.put("job_title", JOB_TITLE);
        map.put("joined", JOINED);
        map.put("last_active", LAST_ACTIVE);
        map.put("last_ip", LAST_IP);
        map.put("last_name", LAST_NAME);
        map.put("last_post", LAST_POST);
        map.put("last_updated", LAST_UPDATED);
        map.put("level", LEVEL);
        map.put("likes_given", LIKES_GIVEN);
        map.put("likes_received", LIKES_RECEIVED);
        map.put("line_type", LINE_TYPE);
        map.put("linked_account", LINKED_ACCOUNT);
        map.put("location", LOCATION);
        map.put("marked_spam", MARKED_SPAM);
        map.put("middle_name", MIDDLE_NAME);
        map.put("nationality", NATIONALITY);
        map.put("organisation", ORGANISATION);
        map.put("other_ip", OTHER_IP);
        map.put("paid", PAID);
        map.put("password_changed", PASSWORD_CHANGED);
        map.put("payment_method", PAYMENT_METHOD);
        map.put("picture", PICTURE);
        map.put("points", POINTS);
        map.put("posts", POSTS);
        map.put("private", PRIVATE);
        map.put("profile_link", PROFILE_LINK);
        map.put("profile_views", PROFILE_VIEWS);
        map.put("rating", RATING);
        map.put("referrals", REFERRALS);
        map.put("referrer", REFERRER);
        map.put("report_count", REPORT_COUNT);
        map.put("reputation", REPUTATION);
        map.put("reputation_label", REPUTATION_LABEL);
        map.put("residence", RESIDENCE);
        map.put("salutation", SALUTATION);
        map.put("school", SCHOOL);
        map.put("signature", SIGNATURE);
        map.put("signup_ip", SIGNUP_IP);
        map.put("site_role", SITE_ROLE);
        map.put("spam_score", SPAM_SCORE);
        map.put("street", STREET);
        map.put("trades", TRADES);
        map.put("verified", VERIFIED);
        map.put("warnings", WARNINGS);
        map.put("website", WEBSITE);
        return map;
    }

    private static final Map<String, RecordRoleEnum> createEnumsMap() {
        Map<String, RecordRoleEnum> map = new HashMap<>();
        map.put("account_id", RecordRoleEnum.ACCOUNT_ID);
        map.put("account_status", RecordRoleEnum.ACCOUNT_STATUS);
        map.put("account_type", RecordRoleEnum.ACCOUNT_TYPE);
        map.put("alternate_phone", RecordRoleEnum.ALTERNATE_PHONE);
        map.put("balance", RecordRoleEnum.BALANCE);
        map.put("banned", RecordRoleEnum.BANNED);
        map.put("bio", RecordRoleEnum.BIO);
        map.put("born", RecordRoleEnum.BORN);
        map.put("bot", RecordRoleEnum.BOT);
        map.put("caller_category", RecordRoleEnum.CALLER_CATEGORY);
        map.put("chargebacks", RecordRoleEnum.CHARGEBACKS);
        map.put("cover_picture", RecordRoleEnum.COVER_PICTURE);
        map.put("declines", RecordRoleEnum.DECLINES);
        map.put("deleted", RecordRoleEnum.DELETED);
        map.put("deleted_on", RecordRoleEnum.DELETED_ON);
        map.put("department", RecordRoleEnum.DEPARTMENT);
        map.put("display_name", RecordRoleEnum.DISPLAY_NAME);
        map.put("email_verified", RecordRoleEnum.EMAIL_VERIFIED);
        map.put("expires", RecordRoleEnum.EXPIRES);
        map.put("first_name", RecordRoleEnum.FIRST_NAME);
        map.put("followers", RecordRoleEnum.FOLLOWERS);
        map.put("following", RecordRoleEnum.FOLLOWING);
        map.put("friends", RecordRoleEnum.FRIENDS);
        map.put("full_address", RecordRoleEnum.FULL_ADDRESS);
        map.put("full_name", RecordRoleEnum.FULL_NAME);
        map.put("handle", RecordRoleEnum.HANDLE);
        map.put("headline", RecordRoleEnum.HEADLINE);
        map.put("interests", RecordRoleEnum.INTERESTS);
        map.put("job_title", RecordRoleEnum.JOB_TITLE);
        map.put("joined", RecordRoleEnum.JOINED);
        map.put("last_active", RecordRoleEnum.LAST_ACTIVE);
        map.put("last_ip", RecordRoleEnum.LAST_IP);
        map.put("last_name", RecordRoleEnum.LAST_NAME);
        map.put("last_post", RecordRoleEnum.LAST_POST);
        map.put("last_updated", RecordRoleEnum.LAST_UPDATED);
        map.put("level", RecordRoleEnum.LEVEL);
        map.put("likes_given", RecordRoleEnum.LIKES_GIVEN);
        map.put("likes_received", RecordRoleEnum.LIKES_RECEIVED);
        map.put("line_type", RecordRoleEnum.LINE_TYPE);
        map.put("linked_account", RecordRoleEnum.LINKED_ACCOUNT);
        map.put("location", RecordRoleEnum.LOCATION);
        map.put("marked_spam", RecordRoleEnum.MARKED_SPAM);
        map.put("middle_name", RecordRoleEnum.MIDDLE_NAME);
        map.put("nationality", RecordRoleEnum.NATIONALITY);
        map.put("organisation", RecordRoleEnum.ORGANISATION);
        map.put("other_ip", RecordRoleEnum.OTHER_IP);
        map.put("paid", RecordRoleEnum.PAID);
        map.put("password_changed", RecordRoleEnum.PASSWORD_CHANGED);
        map.put("payment_method", RecordRoleEnum.PAYMENT_METHOD);
        map.put("picture", RecordRoleEnum.PICTURE);
        map.put("points", RecordRoleEnum.POINTS);
        map.put("posts", RecordRoleEnum.POSTS);
        map.put("private", RecordRoleEnum.PRIVATE);
        map.put("profile_link", RecordRoleEnum.PROFILE_LINK);
        map.put("profile_views", RecordRoleEnum.PROFILE_VIEWS);
        map.put("rating", RecordRoleEnum.RATING);
        map.put("referrals", RecordRoleEnum.REFERRALS);
        map.put("referrer", RecordRoleEnum.REFERRER);
        map.put("report_count", RecordRoleEnum.REPORT_COUNT);
        map.put("reputation", RecordRoleEnum.REPUTATION);
        map.put("reputation_label", RecordRoleEnum.REPUTATION_LABEL);
        map.put("residence", RecordRoleEnum.RESIDENCE);
        map.put("salutation", RecordRoleEnum.SALUTATION);
        map.put("school", RecordRoleEnum.SCHOOL);
        map.put("signature", RecordRoleEnum.SIGNATURE);
        map.put("signup_ip", RecordRoleEnum.SIGNUP_IP);
        map.put("site_role", RecordRoleEnum.SITE_ROLE);
        map.put("spam_score", RecordRoleEnum.SPAM_SCORE);
        map.put("street", RecordRoleEnum.STREET);
        map.put("trades", RecordRoleEnum.TRADES);
        map.put("verified", RecordRoleEnum.VERIFIED);
        map.put("warnings", RecordRoleEnum.WARNINGS);
        map.put("website", RecordRoleEnum.WEBSITE);
        return map;
    }

    public enum RecordRoleEnum {

        ACCOUNT_ID("account_id"),
        ACCOUNT_STATUS("account_status"),
        ACCOUNT_TYPE("account_type"),
        ALTERNATE_PHONE("alternate_phone"),
        BALANCE("balance"),
        BANNED("banned"),
        BIO("bio"),
        BORN("born"),
        BOT("bot"),
        CALLER_CATEGORY("caller_category"),
        CHARGEBACKS("chargebacks"),
        COVER_PICTURE("cover_picture"),
        DECLINES("declines"),
        DELETED("deleted"),
        DELETED_ON("deleted_on"),
        DEPARTMENT("department"),
        DISPLAY_NAME("display_name"),
        EMAIL_VERIFIED("email_verified"),
        EXPIRES("expires"),
        FIRST_NAME("first_name"),
        FOLLOWERS("followers"),
        FOLLOWING("following"),
        FRIENDS("friends"),
        FULL_ADDRESS("full_address"),
        FULL_NAME("full_name"),
        HANDLE("handle"),
        HEADLINE("headline"),
        INTERESTS("interests"),
        JOB_TITLE("job_title"),
        JOINED("joined"),
        LAST_ACTIVE("last_active"),
        LAST_IP("last_ip"),
        LAST_NAME("last_name"),
        LAST_POST("last_post"),
        LAST_UPDATED("last_updated"),
        LEVEL("level"),
        LIKES_GIVEN("likes_given"),
        LIKES_RECEIVED("likes_received"),
        LINE_TYPE("line_type"),
        LINKED_ACCOUNT("linked_account"),
        LOCATION("location"),
        MARKED_SPAM("marked_spam"),
        MIDDLE_NAME("middle_name"),
        NATIONALITY("nationality"),
        ORGANISATION("organisation"),
        OTHER_IP("other_ip"),
        PAID("paid"),
        PASSWORD_CHANGED("password_changed"),
        PAYMENT_METHOD("payment_method"),
        PICTURE("picture"),
        POINTS("points"),
        POSTS("posts"),
        PRIVATE("private"),
        PROFILE_LINK("profile_link"),
        PROFILE_VIEWS("profile_views"),
        RATING("rating"),
        REFERRALS("referrals"),
        REFERRER("referrer"),
        REPORT_COUNT("report_count"),
        REPUTATION("reputation"),
        REPUTATION_LABEL("reputation_label"),
        RESIDENCE("residence"),
        SALUTATION("salutation"),
        SCHOOL("school"),
        SIGNATURE("signature"),
        SIGNUP_IP("signup_ip"),
        SITE_ROLE("site_role"),
        SPAM_SCORE("spam_score"),
        STREET("street"),
        TRADES("trades"),
        VERIFIED("verified"),
        WARNINGS("warnings"),
        WEBSITE("website"),
        ;

        private final String value;

        private RecordRoleEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
