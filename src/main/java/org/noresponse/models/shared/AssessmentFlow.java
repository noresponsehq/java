package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * AssessmentFlow
 *
 * <p>The customer event an assessment is made for: signup (a new account), login (a returning sign-in) or
 * purchase (a payment or credit grant). The account's risk is reported with the decision for this
 * flow.
 */
public enum AssessmentFlow {
    SIGNUP("signup"),
    LOGIN("login"),
    PURCHASE("purchase");

    @JsonValue
    private final String value;

    AssessmentFlow(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<AssessmentFlow> fromValue(String value) {
        for (AssessmentFlow o : AssessmentFlow.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
