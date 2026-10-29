package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum InviteRequestEnvironment {
    LIVE("live"),
    TEST("test");

    @JsonValue
    private final String value;

    InviteRequestEnvironment(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<InviteRequestEnvironment> fromValue(String value) {
        for (InviteRequestEnvironment o : InviteRequestEnvironment.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
