package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum ReviewUpdateRequestStatus {
    OPEN("open"),
    IN_PROGRESS("in_progress");

    @JsonValue
    private final String value;

    ReviewUpdateRequestStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ReviewUpdateRequestStatus> fromValue(String value) {
        for (ReviewUpdateRequestStatus o : ReviewUpdateRequestStatus.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
