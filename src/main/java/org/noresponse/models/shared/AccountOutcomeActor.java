package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

public class AccountOutcomeActor {

    @JsonProperty("type")
    private String type;

    @JsonProperty("key_id")
    private String keyId;

    @JsonCreator
    public AccountOutcomeActor(@JsonProperty("key_id") @Nonnull String keyId) {
        this.type = Builder._SINGLETON_VALUE_Type.value();
        this.keyId = Optional.ofNullable(keyId).orElseThrow(() -> new IllegalArgumentException("keyId cannot be null"));
    }

    public String type() {
        return this.type;
    }

    public String keyId() {
        return this.keyId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AccountOutcomeActor withKeyId(@Nonnull String keyId) {
        this.keyId = Utils.checkNotNull(keyId, "keyId");
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountOutcomeActor other = (AccountOutcomeActor) o;
        return Utils.enhancedDeepEquals(this.type, other.type) && Utils.enhancedDeepEquals(this.keyId, other.keyId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(type, keyId);
    }

    @Override
    public String toString() {
        return Utils.toString(AccountOutcomeActor.class, "type", type, "keyId", keyId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String keyId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder keyId(@Nonnull String keyId) {
            this.keyId = Utils.checkNotNull(keyId, "keyId");
            return this;
        }

        public AccountOutcomeActor build() {
            return new AccountOutcomeActor(keyId);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Type =
                new LazySingletonValue<>("type", "\"api_key\"", new TypeReference<String>() {});
    }
}
