package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Transport
 *
 * <p>Where this context was derived. Backend is the trusted integration path; browser values are treated
 * as weak hints. provider_hook is a hosted provider hook (an Auth0 or WorkOS Action) calling at
 * hook_point; it needs hook_point and verified false, and its authentication facts are not scored.
 */
public enum Transport {
    BACKEND("backend"),
    BROWSER("browser"),
    PROVIDER_HOOK("provider_hook");

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
