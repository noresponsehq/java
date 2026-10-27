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
 * ListEntryAction
 *
 * <p>block decides block for the accounts the entry matches; allow decides allow. Block wins when both
 * match.
 */
public class ListEntryAction {

    public static final ListEntryAction BLOCK = new ListEntryAction("block");
    public static final ListEntryAction ALLOW = new ListEntryAction("allow");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, ListEntryAction> values = createValuesMap();
    private static final Map<String, ListEntryActionEnum> enums = createEnumsMap();

    private final String value;

    private ListEntryAction(String value) {
        this.value = value;
    }

    /**
     * Returns a ListEntryAction with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as ListEntryAction
     */
    @JsonCreator
    public static ListEntryAction of(String value) {
        synchronized (ListEntryAction.class) {
            return values.computeIfAbsent(value, v -> new ListEntryAction(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ListEntryActionEnum> asEnum() {
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
        ListEntryAction other = (ListEntryAction) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "ListEntryAction [value=" + value + "]";
    }

    // return an array just like an enum
    public static ListEntryAction[] values() {
        synchronized (ListEntryAction.class) {
            return values.values().toArray(new ListEntryAction[] {});
        }
    }

    private static final Map<String, ListEntryAction> createValuesMap() {
        Map<String, ListEntryAction> map = new LinkedHashMap<>();
        map.put("block", BLOCK);
        map.put("allow", ALLOW);
        return map;
    }

    private static final Map<String, ListEntryActionEnum> createEnumsMap() {
        Map<String, ListEntryActionEnum> map = new HashMap<>();
        map.put("block", ListEntryActionEnum.BLOCK);
        map.put("allow", ListEntryActionEnum.ALLOW);
        return map;
    }

    public enum ListEntryActionEnum {

        BLOCK("block"),
        ALLOW("allow"),
        ;

        private final String value;

        private ListEntryActionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
