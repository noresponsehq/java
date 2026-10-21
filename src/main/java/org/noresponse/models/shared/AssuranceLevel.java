package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * AssuranceLevel
 *
 * <p>Provider or OIDC assurance level, when the customer has mapped one.
 */
public enum AssuranceLevel {
    UNKNOWN("unknown"),
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    @JsonValue
    private final String value;

    AssuranceLevel(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<AssuranceLevel> fromValue(String value) {
        for (AssuranceLevel o : AssuranceLevel.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
