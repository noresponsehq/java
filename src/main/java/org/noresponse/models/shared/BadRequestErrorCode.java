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
 * BadRequestErrorCode
 *
 * <p>Machine codes an HTTP 400 answer can carry.
 */
public class BadRequestErrorCode {

    public static final BadRequestErrorCode INVALID_REQUEST = new BadRequestErrorCode("invalid_request");
    public static final BadRequestErrorCode INVALID_IDEMPOTENCY_KEY =
            new BadRequestErrorCode("invalid_idempotency_key");
    public static final BadRequestErrorCode INVALID_CURSOR = new BadRequestErrorCode("invalid_cursor");
    public static final BadRequestErrorCode WEBHOOK_URL_REFUSED = new BadRequestErrorCode("webhook_url_refused");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, BadRequestErrorCode> values = createValuesMap();
    private static final Map<String, BadRequestErrorCodeEnum> enums = createEnumsMap();

    private final String value;

    private BadRequestErrorCode(String value) {
        this.value = value;
    }

    /**
     * Returns a BadRequestErrorCode with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as BadRequestErrorCode
     */
    @JsonCreator
    public static BadRequestErrorCode of(String value) {
        synchronized (BadRequestErrorCode.class) {
            return values.computeIfAbsent(value, v -> new BadRequestErrorCode(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<BadRequestErrorCodeEnum> asEnum() {
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
        BadRequestErrorCode other = (BadRequestErrorCode) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "BadRequestErrorCode [value=" + value + "]";
    }

    // return an array just like an enum
    public static BadRequestErrorCode[] values() {
        synchronized (BadRequestErrorCode.class) {
            return values.values().toArray(new BadRequestErrorCode[] {});
        }
    }

    private static final Map<String, BadRequestErrorCode> createValuesMap() {
        Map<String, BadRequestErrorCode> map = new LinkedHashMap<>();
        map.put("invalid_request", INVALID_REQUEST);
        map.put("invalid_idempotency_key", INVALID_IDEMPOTENCY_KEY);
        map.put("invalid_cursor", INVALID_CURSOR);
        map.put("webhook_url_refused", WEBHOOK_URL_REFUSED);
        return map;
    }

    private static final Map<String, BadRequestErrorCodeEnum> createEnumsMap() {
        Map<String, BadRequestErrorCodeEnum> map = new HashMap<>();
        map.put("invalid_request", BadRequestErrorCodeEnum.INVALID_REQUEST);
        map.put("invalid_idempotency_key", BadRequestErrorCodeEnum.INVALID_IDEMPOTENCY_KEY);
        map.put("invalid_cursor", BadRequestErrorCodeEnum.INVALID_CURSOR);
        map.put("webhook_url_refused", BadRequestErrorCodeEnum.WEBHOOK_URL_REFUSED);
        return map;
    }

    public enum BadRequestErrorCodeEnum {

        INVALID_REQUEST("invalid_request"),
        INVALID_IDEMPOTENCY_KEY("invalid_idempotency_key"),
        INVALID_CURSOR("invalid_cursor"),
        WEBHOOK_URL_REFUSED("webhook_url_refused"),
        ;

        private final String value;

        private BadRequestErrorCodeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
