package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * ScopeEnum
 *
 * <p>assessment requires one submission to satisfy the complete expression. account permits witnesses
 * across retained submissions of one account and is supported only by POST /v1/accounts/search. No
 * default: select scope explicitly.
 */
public enum ScopeEnum {
    ASSESSMENT("assessment"),
    ACCOUNT("account");

    @JsonValue
    private final String value;

    ScopeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<ScopeEnum> fromValue(String value) {
        for (ScopeEnum o : ScopeEnum.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
