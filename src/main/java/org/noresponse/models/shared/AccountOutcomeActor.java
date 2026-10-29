package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AccountOutcomeActor {

    @JsonProperty("type")
    private AccountOutcomeActorType type;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("key_id")
    private String keyId;

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_user_id")
    private String externalUserId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("console_credential_id")
    private String consoleCredentialId;

    /**
     * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
     * member is invited back.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("principal_id")
    private String principalId;

    @JsonCreator
    public AccountOutcomeActor(
            @JsonProperty("type") @Nonnull AccountOutcomeActorType type,
            @JsonProperty("key_id") @Nullable String keyId,
            @JsonProperty("external_user_id") @Nullable String externalUserId,
            @JsonProperty("console_credential_id") @Nullable String consoleCredentialId,
            @JsonProperty("principal_id") @Nullable String principalId) {
        this.type = Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("type cannot be null"));
        this.keyId = keyId;
        this.externalUserId = externalUserId;
        this.consoleCredentialId = consoleCredentialId;
        this.principalId = principalId;
    }

    public AccountOutcomeActor(@Nonnull AccountOutcomeActorType type) {
        this(type, null, null, null, null);
    }

    public AccountOutcomeActorType type() {
        return this.type;
    }

    public Optional<String> keyId() {
        return Optional.ofNullable(this.keyId);
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public Optional<String> externalUserId() {
        return Optional.ofNullable(this.externalUserId);
    }

    public Optional<String> consoleCredentialId() {
        return Optional.ofNullable(this.consoleCredentialId);
    }

    /**
     * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
     * member is invited back.
     */
    public Optional<String> principalId() {
        return Optional.ofNullable(this.principalId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AccountOutcomeActor withType(@Nonnull AccountOutcomeActorType type) {
        this.type = Utils.checkNotNull(type, "type");
        return this;
    }

    public AccountOutcomeActor withKeyId(@Nullable String keyId) {
        this.keyId = keyId;
        return this;
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public AccountOutcomeActor withExternalUserId(@Nullable String externalUserId) {
        this.externalUserId = externalUserId;
        return this;
    }

    public AccountOutcomeActor withConsoleCredentialId(@Nullable String consoleCredentialId) {
        this.consoleCredentialId = consoleCredentialId;
        return this;
    }

    /**
     * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
     * member is invited back.
     */
    public AccountOutcomeActor withPrincipalId(@Nullable String principalId) {
        this.principalId = principalId;
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
        return Utils.enhancedDeepEquals(this.type, other.type)
                && Utils.enhancedDeepEquals(this.keyId, other.keyId)
                && Utils.enhancedDeepEquals(this.externalUserId, other.externalUserId)
                && Utils.enhancedDeepEquals(this.consoleCredentialId, other.consoleCredentialId)
                && Utils.enhancedDeepEquals(this.principalId, other.principalId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(type, keyId, externalUserId, consoleCredentialId, principalId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountOutcomeActor.class,
                "type",
                type,
                "keyId",
                keyId,
                "externalUserId",
                externalUserId,
                "consoleCredentialId",
                consoleCredentialId,
                "principalId",
                principalId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AccountOutcomeActorType type;

        private String keyId;

        private String externalUserId;

        private String consoleCredentialId;

        private String principalId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder type(@Nonnull AccountOutcomeActorType type) {
            this.type = Utils.checkNotNull(type, "type");
            return this;
        }

        public Builder keyId(@Nullable String keyId) {
            this.keyId = keyId;
            return this;
        }

        /**
         * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
         * characters with no space, compared exactly.
         */
        public Builder externalUserId(@Nullable String externalUserId) {
            this.externalUserId = externalUserId;
            return this;
        }

        public Builder consoleCredentialId(@Nullable String consoleCredentialId) {
            this.consoleCredentialId = consoleCredentialId;
            return this;
        }

        /**
         * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
         * member is invited back.
         */
        public Builder principalId(@Nullable String principalId) {
            this.principalId = principalId;
            return this;
        }

        public AccountOutcomeActor build() {
            return new AccountOutcomeActor(type, keyId, externalUserId, consoleCredentialId, principalId);
        }
    }
}
