package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Transport
 *
 * <p>Where the customer derived this context. Backend is the trusted integration path; browser values are
 * treated as weak hints.
 */
public enum Transport {
    BACKEND("backend"),
    BROWSER("browser");

    @JsonValue
    private final String value;

    Transport(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<Transport> fromValue(String value) {
        for (Transport o : Transport.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
