package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * ListMatch
 *
 * <p>The list action captured at the first answer. Requires revision first. Older answers without
 * captured list state do not match, including none.
 */
public enum ListMatch {
    ALLOW("allow"),
    BLOCK("block"),
    NONE("none");

    @JsonValue
    private final String value;

    ListMatch(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ListMatch> fromValue(String value) {
        for (ListMatch o : ListMatch.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
