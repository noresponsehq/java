package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum KeyRequestScope {
    ASSESS("assess"),
    HISTORY("history"),
    DELETE("delete"),
    WEBHOOKS("webhooks"),
    DETAILED("detailed"),
    INTEGRATIONS("integrations"),
    MANAGEMENT("management");

    @JsonValue
    private final String value;

    KeyRequestScope(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<KeyRequestScope> fromValue(String value) {
        for (KeyRequestScope o : KeyRequestScope.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
