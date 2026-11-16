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
public class Topic {

    public static final Topic IDENTITY = new Topic("identity");
    public static final Topic LOCATION = new Topic("location");
    public static final Topic MOVEMENT = new Topic("movement");
    public static final Topic TIMELINE = new Topic("timeline");
    public static final Topic INTERESTS = new Topic("interests");
    public static final Topic RELATIONSHIPS = new Topic("relationships");
    public static final Topic WORK = new Topic("work");
    public static final Topic EDUCATION = new Topic("education");
    public static final Topic ACCOUNTS = new Topic("accounts");
    public static final Topic CREDENTIALS = new Topic("credentials");
    public static final Topic DEVICES = new Topic("devices");
    public static final Topic ABSENCE = new Topic("absence");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Topic> values = createValuesMap();
    private static final Map<String, TopicEnum> enums = createEnumsMap();

    private final String value;

    private Topic(String value) {
        this.value = value;
    }

    /**
     * Returns a Topic with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Topic
     */
    @JsonCreator
    public static Topic of(String value) {
        synchronized (Topic.class) {
            return values.computeIfAbsent(value, v -> new Topic(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<TopicEnum> asEnum() {
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
        Topic other = (Topic) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Topic [value=" + value + "]";
    }

    // return an array just like an enum
    public static Topic[] values() {
        synchronized (Topic.class) {
            return values.values().toArray(new Topic[] {});
        }
    }

    private static final Map<String, Topic> createValuesMap() {
        Map<String, Topic> map = new LinkedHashMap<>();
        map.put("identity", IDENTITY);
        map.put("location", LOCATION);
        map.put("movement", MOVEMENT);
        map.put("timeline", TIMELINE);
        map.put("interests", INTERESTS);
        map.put("relationships", RELATIONSHIPS);
        map.put("work", WORK);
        map.put("education", EDUCATION);
        map.put("accounts", ACCOUNTS);
        map.put("credentials", CREDENTIALS);
        map.put("devices", DEVICES);
        map.put("absence", ABSENCE);
        return map;
    }

    private static final Map<String, TopicEnum> createEnumsMap() {
        Map<String, TopicEnum> map = new HashMap<>();
        map.put("identity", TopicEnum.IDENTITY);
        map.put("location", TopicEnum.LOCATION);
        map.put("movement", TopicEnum.MOVEMENT);
        map.put("timeline", TopicEnum.TIMELINE);
        map.put("interests", TopicEnum.INTERESTS);
        map.put("relationships", TopicEnum.RELATIONSHIPS);
        map.put("work", TopicEnum.WORK);
        map.put("education", TopicEnum.EDUCATION);
        map.put("accounts", TopicEnum.ACCOUNTS);
        map.put("credentials", TopicEnum.CREDENTIALS);
        map.put("devices", TopicEnum.DEVICES);
        map.put("absence", TopicEnum.ABSENCE);
        return map;
    }

    public enum TopicEnum {

        IDENTITY("identity"),
        LOCATION("location"),
        MOVEMENT("movement"),
        TIMELINE("timeline"),
        INTERESTS("interests"),
        RELATIONSHIPS("relationships"),
        WORK("work"),
        EDUCATION("education"),
        ACCOUNTS("accounts"),
        CREDENTIALS("credentials"),
        DEVICES("devices"),
        ABSENCE("absence"),
        ;

        private final String value;

        private TopicEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
