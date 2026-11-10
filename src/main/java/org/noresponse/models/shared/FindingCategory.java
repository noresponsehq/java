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
 * FindingCategory
 *
 * <p>What a finding is about: a direct threat to the principal or the event, intent to harm without a
 * named target, fixation on the principal, a grievance against the host, research into the venue,
 * route or security, weapons tied to any of these, disruption or protest planning toward this event,
 * violent extremist content or identification, prior harassment or stalking of staff or principals, or
 * posting the principal's addresses or movements.
 */
public class FindingCategory {

    public static final FindingCategory DIRECT_THREAT = new FindingCategory("direct_threat");
    public static final FindingCategory HARM_INTENT = new FindingCategory("harm_intent");
    public static final FindingCategory FIXATION = new FindingCategory("fixation");
    public static final FindingCategory GRIEVANCE = new FindingCategory("grievance");
    public static final FindingCategory SECURITY_RESEARCH = new FindingCategory("security_research");
    public static final FindingCategory WEAPONS = new FindingCategory("weapons");
    public static final FindingCategory DISRUPTION = new FindingCategory("disruption");
    public static final FindingCategory VIOLENT_EXTREMISM = new FindingCategory("violent_extremism");
    public static final FindingCategory HARASSMENT = new FindingCategory("harassment");
    public static final FindingCategory MOVEMENT_EXPOSURE = new FindingCategory("movement_exposure");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, FindingCategory> values = createValuesMap();
    private static final Map<String, FindingCategoryEnum> enums = createEnumsMap();

    private final String value;

    private FindingCategory(String value) {
        this.value = value;
    }

    /**
     * Returns a FindingCategory with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as FindingCategory
     */
    @JsonCreator
    public static FindingCategory of(String value) {
        synchronized (FindingCategory.class) {
            return values.computeIfAbsent(value, v -> new FindingCategory(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<FindingCategoryEnum> asEnum() {
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
        FindingCategory other = (FindingCategory) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "FindingCategory [value=" + value + "]";
    }

    // return an array just like an enum
    public static FindingCategory[] values() {
        synchronized (FindingCategory.class) {
            return values.values().toArray(new FindingCategory[] {});
        }
    }

    private static final Map<String, FindingCategory> createValuesMap() {
        Map<String, FindingCategory> map = new LinkedHashMap<>();
        map.put("direct_threat", DIRECT_THREAT);
        map.put("harm_intent", HARM_INTENT);
        map.put("fixation", FIXATION);
        map.put("grievance", GRIEVANCE);
        map.put("security_research", SECURITY_RESEARCH);
        map.put("weapons", WEAPONS);
        map.put("disruption", DISRUPTION);
        map.put("violent_extremism", VIOLENT_EXTREMISM);
        map.put("harassment", HARASSMENT);
        map.put("movement_exposure", MOVEMENT_EXPOSURE);
        return map;
    }

    private static final Map<String, FindingCategoryEnum> createEnumsMap() {
        Map<String, FindingCategoryEnum> map = new HashMap<>();
        map.put("direct_threat", FindingCategoryEnum.DIRECT_THREAT);
        map.put("harm_intent", FindingCategoryEnum.HARM_INTENT);
        map.put("fixation", FindingCategoryEnum.FIXATION);
        map.put("grievance", FindingCategoryEnum.GRIEVANCE);
        map.put("security_research", FindingCategoryEnum.SECURITY_RESEARCH);
        map.put("weapons", FindingCategoryEnum.WEAPONS);
        map.put("disruption", FindingCategoryEnum.DISRUPTION);
        map.put("violent_extremism", FindingCategoryEnum.VIOLENT_EXTREMISM);
        map.put("harassment", FindingCategoryEnum.HARASSMENT);
        map.put("movement_exposure", FindingCategoryEnum.MOVEMENT_EXPOSURE);
        return map;
    }

    public enum FindingCategoryEnum {

        DIRECT_THREAT("direct_threat"),
        HARM_INTENT("harm_intent"),
        FIXATION("fixation"),
        GRIEVANCE("grievance"),
        SECURITY_RESEARCH("security_research"),
        WEAPONS("weapons"),
        DISRUPTION("disruption"),
        VIOLENT_EXTREMISM("violent_extremism"),
        HARASSMENT("harassment"),
        MOVEMENT_EXPOSURE("movement_exposure"),
        ;

        private final String value;

        private FindingCategoryEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
