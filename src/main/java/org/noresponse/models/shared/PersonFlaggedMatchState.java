package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * PersonFlaggedMatchState
 *
 * <p>How sure the screening is that the accounts it read are the person's. A finding on a possible match
 * is at most moderate, on a probable match at most high.
 */
public enum PersonFlaggedMatchState {
    CONFIRMED("confirmed"),
    PROBABLE("probable"),
    POSSIBLE("possible"),
    NONE_FOUND("none_found"),
    INACCESSIBLE("inaccessible");

    @JsonValue
    private final String value;

    PersonFlaggedMatchState(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<PersonFlaggedMatchState> fromValue(String value) {
        for (PersonFlaggedMatchState o : PersonFlaggedMatchState.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
