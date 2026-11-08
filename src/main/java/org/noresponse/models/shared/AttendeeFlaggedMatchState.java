package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * AttendeeFlaggedMatchState
 *
 * <p>How sure the screening is that the accounts it read are the attendee's. A finding on a possible
 * match is at most moderate, on a probable match at most high.
 */
public enum AttendeeFlaggedMatchState {
    CONFIRMED("confirmed"),
    PROBABLE("probable"),
    POSSIBLE("possible"),
    NONE_FOUND("none_found"),
    INACCESSIBLE("inaccessible");

    @JsonValue
    private final String value;

    AttendeeFlaggedMatchState(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<AttendeeFlaggedMatchState> fromValue(String value) {
        for (AttendeeFlaggedMatchState o : AttendeeFlaggedMatchState.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
