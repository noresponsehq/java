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
 * WebhookTestRequestEvent
 *
 * <p>endpoint.verification sends a fresh challenge now, in this call, and the endpoint is verified if it
 * echoes it. assessment.updated queues an event whose data is a fixed example assessment, with test
 * true, delivered and retried like any other; the endpoint must be verified.
 */
public class WebhookTestRequestEvent {

    public static final WebhookTestRequestEvent ENDPOINT_VERIFICATION =
            new WebhookTestRequestEvent("endpoint.verification");
    public static final WebhookTestRequestEvent ASSESSMENT_UPDATED = new WebhookTestRequestEvent("assessment.updated");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, WebhookTestRequestEvent> values = createValuesMap();
    private static final Map<String, WebhookTestRequestEventEnum> enums = createEnumsMap();

    private final String value;

    private WebhookTestRequestEvent(String value) {
        this.value = value;
    }

    /**
     * Returns a WebhookTestRequestEvent with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as WebhookTestRequestEvent
     */
    @JsonCreator
    public static WebhookTestRequestEvent of(String value) {
        synchronized (WebhookTestRequestEvent.class) {
            return values.computeIfAbsent(value, v -> new WebhookTestRequestEvent(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<WebhookTestRequestEventEnum> asEnum() {
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
        WebhookTestRequestEvent other = (WebhookTestRequestEvent) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "WebhookTestRequestEvent [value=" + value + "]";
    }

    // return an array just like an enum
    public static WebhookTestRequestEvent[] values() {
        synchronized (WebhookTestRequestEvent.class) {
            return values.values().toArray(new WebhookTestRequestEvent[] {});
        }
    }

    private static final Map<String, WebhookTestRequestEvent> createValuesMap() {
        Map<String, WebhookTestRequestEvent> map = new LinkedHashMap<>();
        map.put("endpoint.verification", ENDPOINT_VERIFICATION);
        map.put("assessment.updated", ASSESSMENT_UPDATED);
        return map;
    }

    private static final Map<String, WebhookTestRequestEventEnum> createEnumsMap() {
        Map<String, WebhookTestRequestEventEnum> map = new HashMap<>();
        map.put("endpoint.verification", WebhookTestRequestEventEnum.ENDPOINT_VERIFICATION);
        map.put("assessment.updated", WebhookTestRequestEventEnum.ASSESSMENT_UPDATED);
        return map;
    }

    public enum WebhookTestRequestEventEnum {

        ENDPOINT_VERIFICATION("endpoint.verification"),
        ASSESSMENT_UPDATED("assessment.updated"),
        ;

        private final String value;

        private WebhookTestRequestEventEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
