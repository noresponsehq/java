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
 * WebhookEndpointState
 *
 * <p>unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
 * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
 * hours of continuous delivery failure, and verified again by the same challenge.
 */
public class WebhookEndpointState {

    public static final WebhookEndpointState UNVERIFIED = new WebhookEndpointState("unverified");
    public static final WebhookEndpointState VERIFIED = new WebhookEndpointState("verified");
    public static final WebhookEndpointState DISABLED = new WebhookEndpointState("disabled");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, WebhookEndpointState> values = createValuesMap();
    private static final Map<String, WebhookEndpointStateEnum> enums = createEnumsMap();

    private final String value;

    private WebhookEndpointState(String value) {
        this.value = value;
    }

    /**
     * Returns a WebhookEndpointState with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as WebhookEndpointState
     */
    @JsonCreator
    public static WebhookEndpointState of(String value) {
        synchronized (WebhookEndpointState.class) {
            return values.computeIfAbsent(value, v -> new WebhookEndpointState(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<WebhookEndpointStateEnum> asEnum() {
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
        WebhookEndpointState other = (WebhookEndpointState) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "WebhookEndpointState [value=" + value + "]";
    }

    // return an array just like an enum
    public static WebhookEndpointState[] values() {
        synchronized (WebhookEndpointState.class) {
            return values.values().toArray(new WebhookEndpointState[] {});
        }
    }

    private static final Map<String, WebhookEndpointState> createValuesMap() {
        Map<String, WebhookEndpointState> map = new LinkedHashMap<>();
        map.put("unverified", UNVERIFIED);
        map.put("verified", VERIFIED);
        map.put("disabled", DISABLED);
        return map;
    }

    private static final Map<String, WebhookEndpointStateEnum> createEnumsMap() {
        Map<String, WebhookEndpointStateEnum> map = new HashMap<>();
        map.put("unverified", WebhookEndpointStateEnum.UNVERIFIED);
        map.put("verified", WebhookEndpointStateEnum.VERIFIED);
        map.put("disabled", WebhookEndpointStateEnum.DISABLED);
        return map;
    }

    public enum WebhookEndpointStateEnum {

        UNVERIFIED("unverified"),
        VERIFIED("verified"),
        DISABLED("disabled"),
        ;

        private final String value;

        private WebhookEndpointStateEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
