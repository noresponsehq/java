package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum ListPeopleBand {
    CRITICAL("critical"),
    HIGH("high"),
    MODERATE("moderate"),
    LOW("low"),
    INFO("info"),
    NONE("none");

    @JsonValue
    private final String value;

    ListPeopleBand(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ListPeopleBand> fromValue(String value) {
        for (ListPeopleBand o : ListPeopleBand.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
