package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Decision
 *
 * <p>The decision of the assessment's latest stored revision: block when its stored risk.block is true,
 * otherwise its recommendation. This is not the account's current decision or held-admission state.
 * Combined with recommendation using AND.
 */
public enum Decision {
    ALLOW("allow"),
    STEP_UP("step_up"),
    REVIEW("review"),
    BLOCK("block");

    @JsonValue
    private final String value;

    Decision(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<Decision> fromValue(String value) {
        for (Decision o : Decision.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
