package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum MemberUpdateRequestDeniedAction {
    EVIDENCE_READ("evidence.read"),
    EXPORTS_READ("exports.read"),
    POLICY_PUBLISH("policy.publish");

    @JsonValue
    private final String value;

    MemberUpdateRequestDeniedAction(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<MemberUpdateRequestDeniedAction> fromValue(String value) {
        for (MemberUpdateRequestDeniedAction o : MemberUpdateRequestDeniedAction.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
