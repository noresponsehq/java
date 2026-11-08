package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * ScreeningCompletedStatus
 *
 * <p>completed reached every attendee; partial left some not fully covered (partial_coverage); failed
 * covered nobody.
 */
public enum ScreeningCompletedStatus {
    COMPLETED("completed"),
    PARTIAL("partial"),
    FAILED("failed");

    @JsonValue
    private final String value;

    ScreeningCompletedStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ScreeningCompletedStatus> fromValue(String value) {
        for (ScreeningCompletedStatus o : ScreeningCompletedStatus.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
