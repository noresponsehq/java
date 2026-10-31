package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * Origin
 *
 * <p>api (the default) searches the assessments your integration sent. lookup searches the assessments
 * your team ran by hand on the console's Lookup page. The API sets an assessment's origin from the
 * credential that sent it.
 *
 * <p>A lookup is not in any other list, count, webhook event or account.
 */
public enum Origin {
    API("api"),
    LOOKUP("lookup");

    @JsonValue
    private final String value;

    Origin(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<Origin> fromValue(String value) {
        for (Origin o : Origin.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
