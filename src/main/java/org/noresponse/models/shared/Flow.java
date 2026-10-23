package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Flow
 *
 * <p>The provider flow the assessment gates. Use the top-level flow instead; this member is ignored when
 * the top-level flow is present. Absent means signup.
 */
public enum Flow {
    SIGNUP("signup"),
    LOGIN("login"),
    RECOVERY("recovery"),
    PROFILE_CHANGE("profile_change");

    @JsonValue
    private final String value;

    Flow(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<Flow> fromValue(String value) {
        for (Flow o : Flow.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
