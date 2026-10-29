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
    MANAGEMENT("management"),
    AUDIT("audit"),
    USAGE("usage"),
    INVESTIGATE("investigate"),
    EVIDENCE("evidence"),
    EXPORTS("exports"),
    AUDIT_MANAGE("audit_manage"),
    SETTINGS("settings"),
    POLICY_PUBLISH("policy_publish"),
    MEMBERS("members"),
    MEMBERS_MANAGE("members_manage");

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
