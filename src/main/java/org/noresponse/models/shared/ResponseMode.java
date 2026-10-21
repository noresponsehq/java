package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * ResponseMode
 *
 * <p>standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
 * the key has detailed scope. There is no raw mode.
 */
public enum ResponseMode {
    STANDARD("standard"),
    DETAILED("detailed");

    @JsonValue
    private final String value;

    ResponseMode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ResponseMode> fromValue(String value) {
        for (ResponseMode o : ResponseMode.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
