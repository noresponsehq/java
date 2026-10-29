package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Environment
 */
public enum Environment {
    TEST("test");

    @JsonValue
    private final String value;

    Environment(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<Environment> fromValue(String value) {
        for (Environment o : Environment.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
