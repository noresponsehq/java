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
 * WebhookDeliveryAttemptStatus
 *
 * <p>delivered when the endpoint answered 2xx; failed otherwise, with failure naming why.
 */
public class WebhookDeliveryAttemptStatus {

    public static final WebhookDeliveryAttemptStatus DELIVERED = new WebhookDeliveryAttemptStatus("delivered");
    public static final WebhookDeliveryAttemptStatus FAILED = new WebhookDeliveryAttemptStatus("failed");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, WebhookDeliveryAttemptStatus> values = createValuesMap();
    private static final Map<String, WebhookDeliveryAttemptStatusEnum> enums = createEnumsMap();

    private final String value;

    private WebhookDeliveryAttemptStatus(String value) {
        this.value = value;
    }

    /**
     * Returns a WebhookDeliveryAttemptStatus with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as WebhookDeliveryAttemptStatus
     */
    @JsonCreator
    public static WebhookDeliveryAttemptStatus of(String value) {
        synchronized (WebhookDeliveryAttemptStatus.class) {
            return values.computeIfAbsent(value, v -> new WebhookDeliveryAttemptStatus(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<WebhookDeliveryAttemptStatusEnum> asEnum() {
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
        WebhookDeliveryAttemptStatus other = (WebhookDeliveryAttemptStatus) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "WebhookDeliveryAttemptStatus [value=" + value + "]";
    }

    // return an array just like an enum
    public static WebhookDeliveryAttemptStatus[] values() {
        synchronized (WebhookDeliveryAttemptStatus.class) {
            return values.values().toArray(new WebhookDeliveryAttemptStatus[] {});
        }
    }

    private static final Map<String, WebhookDeliveryAttemptStatus> createValuesMap() {
        Map<String, WebhookDeliveryAttemptStatus> map = new LinkedHashMap<>();
        map.put("delivered", DELIVERED);
        map.put("failed", FAILED);
        return map;
    }

    private static final Map<String, WebhookDeliveryAttemptStatusEnum> createEnumsMap() {
        Map<String, WebhookDeliveryAttemptStatusEnum> map = new HashMap<>();
        map.put("delivered", WebhookDeliveryAttemptStatusEnum.DELIVERED);
        map.put("failed", WebhookDeliveryAttemptStatusEnum.FAILED);
        return map;
    }

    public enum WebhookDeliveryAttemptStatusEnum {

        DELIVERED("delivered"),
        FAILED("failed"),
        ;

        private final String value;

        private WebhookDeliveryAttemptStatusEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
