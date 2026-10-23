package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Depth
 *
 * <p>How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
 * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
 * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
 * way.
 */
public enum Depth {
    QUICK("quick"),
    FULL("full");

    @JsonValue
    private final String value;

    Depth(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<Depth> fromValue(String value) {
        for (Depth o : Depth.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
