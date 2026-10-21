package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Platform
 *
 * <p>Client platform. Closed vocabulary keeps integrations stable.
 */
public enum Platform {
    WEB("web"),
    IOS("ios"),
    ANDROID("android"),
    OTHER("other");

    @JsonValue
    private final String value;

    Platform(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<Platform> fromValue(String value) {
        for (Platform o : Platform.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
