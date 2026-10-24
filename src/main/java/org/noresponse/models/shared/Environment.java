package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Environment
 *
 * <p>The environment whose endpoints receive this event: live, or test for the workspace's test
 * environment.
 */
public enum Environment {
    LIVE("live"),
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
