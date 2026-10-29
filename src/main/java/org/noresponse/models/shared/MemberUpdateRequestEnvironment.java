package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum MemberUpdateRequestEnvironment {
    LIVE("live"),
    TEST("test");

    @JsonValue
    private final String value;

    MemberUpdateRequestEnvironment(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<MemberUpdateRequestEnvironment> fromValue(String value) {
        for (MemberUpdateRequestEnvironment o : MemberUpdateRequestEnvironment.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
