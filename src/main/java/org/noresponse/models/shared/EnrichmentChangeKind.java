package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum EnrichmentChangeKind {
    USERNAME("username"),
    EMAIL("email"),
    PHONE("phone"),
    PERSON_NAME("person_name");

    @JsonValue
    private final String value;

    EnrichmentChangeKind(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<EnrichmentChangeKind> fromValue(String value) {
        for (EnrichmentChangeKind o : EnrichmentChangeKind.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
