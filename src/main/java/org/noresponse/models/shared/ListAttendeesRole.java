package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum ListAttendeesRole {
    GUEST("guest"),
    CREW("crew"),
    VENDOR("vendor"),
    PRESS("press"),
    UNSTATED("unstated");

    @JsonValue
    private final String value;

    ListAttendeesRole(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ListAttendeesRole> fromValue(String value) {
        for (ListAttendeesRole o : ListAttendeesRole.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
