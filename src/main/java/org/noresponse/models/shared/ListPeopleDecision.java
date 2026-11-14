package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum ListPeopleDecision {
    CLEARED("cleared"),
    WATCH("watch"),
    ESCALATED("escalated"),
    UNDECIDED("undecided");

    @JsonValue
    private final String value;

    ListPeopleDecision(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ListPeopleDecision> fromValue(String value) {
        for (ListPeopleDecision o : ListPeopleDecision.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
