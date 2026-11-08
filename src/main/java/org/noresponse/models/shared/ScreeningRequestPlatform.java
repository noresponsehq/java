package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum ScreeningRequestPlatform {
    INSTAGRAM("instagram"),
    X("x"),
    BLUESKY("bluesky"),
    YOUTUBE("youtube"),
    TIKTOK("tiktok"),
    FACEBOOK("facebook"),
    THREADS("threads"),
    REDDIT("reddit"),
    TELEGRAM("telegram"),
    LINKEDIN("linkedin");

    @JsonValue
    private final String value;

    ScreeningRequestPlatform(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ScreeningRequestPlatform> fromValue(String value) {
        for (ScreeningRequestPlatform o : ScreeningRequestPlatform.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
