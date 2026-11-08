package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum ListAttendeesBand {
    CRITICAL("critical"),
    HIGH("high"),
    MODERATE("moderate"),
    LOW("low"),
    INFO("info"),
    NONE("none");

    @JsonValue
    private final String value;

    ListAttendeesBand(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ListAttendeesBand> fromValue(String value) {
        for (ListAttendeesBand o : ListAttendeesBand.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
